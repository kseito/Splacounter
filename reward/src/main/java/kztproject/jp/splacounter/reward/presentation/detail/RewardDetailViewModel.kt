package kztproject.jp.splacounter.reward.presentation.detail

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kztproject.jp.splacounter.reward.application.model.Failure
import kztproject.jp.splacounter.reward.application.model.Success
import kztproject.jp.splacounter.reward.application.usecase.DeleteRewardUseCase
import kztproject.jp.splacounter.reward.application.usecase.GetRewardUseCase
import kztproject.jp.splacounter.reward.application.usecase.SaveRewardUseCase
import kztproject.jp.splacounter.reward.domain.model.Reward
import kztproject.jp.splacounter.reward.domain.model.RewardId
import kztproject.jp.splacounter.reward.domain.model.RewardInput
import kztproject.jp.splacounter.reward.domain.model.RewardName
import project.seito.reward.R
import javax.inject.Inject

class RewardDetailViewModel @Inject constructor(
        private val deleteRewardUseCase: DeleteRewardUseCase,
        private val getRewardUseCase: GetRewardUseCase,
        private val saveRewardUseCase: SaveRewardUseCase
) : ViewModel() {

    private var reward: Reward? = null
    private var mutableRewardInput = MutableLiveData<RewardInput>()
    var rewardInput: LiveData<RewardInput> = mutableRewardInput
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)
    val canDeleteReward: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        mutableRewardInput.value = RewardInput()
    }

    private lateinit var callback: RewardDetailViewModelCallback

    fun initialize(id: RewardId) {
        viewModelScope.launch {
            val reward = getRewardUseCase.execute(id) ?: throw Resources.NotFoundException()
            mutableRewardInput.value = RewardInput.from(reward)
            this@RewardDetailViewModel.reward = reward
            canDeleteReward.value = true
        }
    }

    fun setCallback(callback: RewardDetailViewModelCallback) {
        this.callback = callback
    }

    fun saveReward() {
        val reward = this.rewardInput.value ?: throw IllegalStateException("mutableReward is null")
        if (reward.name.isNullOrEmpty()) {
            callback.onError(R.string.error_empty_title)
            return
        } else if (reward.consumePoint == 0) {
            callback.onError(R.string.error_empty_point)
            return
        }

        viewModelScope.launch {
            val result = saveRewardUseCase.execute(reward)
            when (result) {
                is Success -> callback.onSaveCompleted(reward.name!!)
                is Failure -> callback.onError(result.reason.messageId)
            }
        }
    }

    fun confirmToRewardDeletion() {
        reward?.let {
            callback.onConfirmToRewardDeletion(it)
        }
    }

    fun deleteReward() {
        viewModelScope.launch {
            reward?.let {
                deleteRewardUseCase.execute(it)
                callback.onDeleteCompleted(it.name)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

interface RewardDetailViewModelCallback {

    fun onSaveCompleted(rewardName: String)

    fun onConfirmToRewardDeletion(reward: Reward)

    fun onDeleteCompleted(rewardName: RewardName)

    fun onError(@StringRes resourceId: Int)
}