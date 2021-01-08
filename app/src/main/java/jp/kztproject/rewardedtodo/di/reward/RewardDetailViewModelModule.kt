package jp.kztproject.rewardedtodo.di.reward

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jp.kztproject.rewardedtodo.application.reward.DeleteRewardInteractor
import jp.kztproject.rewardedtodo.application.reward.DeleteRewardUseCase
import jp.kztproject.rewardedtodo.application.reward.GetRewardInteractor
import jp.kztproject.rewardedtodo.application.reward.GetRewardUseCase
import jp.kztproject.rewardedtodo.reward.application.usecase.*
import jp.kztproject.rewardedtodo.reward.presentation.detail.RewardDetailViewModel
import project.seito.screen_transition.di.ViewModelKey

@Module
interface RewardDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RewardDetailViewModel::class)
    fun bindVideoTrimMuteViewModel(viewModel: RewardDetailViewModel): ViewModel

    @Binds
    fun bindDeleteRewardUseCase(useCase: DeleteRewardInteractor): DeleteRewardUseCase

    @Binds
    fun bindGetRewardUseCase(useCase: GetRewardInteractor): GetRewardUseCase

    @Binds
    fun bindSaveRewardUseCase(useCase: SaveRewardInteractor): SaveRewardUseCase
}