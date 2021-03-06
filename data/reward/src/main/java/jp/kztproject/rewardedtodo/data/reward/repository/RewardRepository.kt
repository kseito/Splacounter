package jp.kztproject.rewardedtodo.data.reward.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import jp.kztproject.rewardedtodo.domain.reward.repository.IRewardRepository
import jp.kztproject.rewardedtodo.domain.reward.Reward
import jp.kztproject.rewardedtodo.domain.reward.RewardInput
import jp.kztproject.rewardedtodo.data.reward.database.RewardDao
import jp.kztproject.rewardedtodo.data.reward.database.model.RewardEntity
import javax.inject.Inject

class RewardRepository @Inject constructor(private val rewardDao: RewardDao) : IRewardRepository {

    override suspend fun createOrUpdate(reward: RewardInput) {
        withContext(Dispatchers.IO) {
            rewardDao.insertReward(RewardEntity.from(reward))
        }
    }

    override suspend fun delete(reward: Reward) {
        withContext(Dispatchers.IO) {
            val rewardEntity = RewardEntity.from(reward)
            rewardDao.deleteReward(rewardEntity)
        }
    }

    override suspend fun findBy(id: Int): Reward? {
        return withContext(Dispatchers.IO) {
            rewardDao.findBy(id)?.convert()
        }
    }

    override suspend fun findAll(): List<Reward> {
        return withContext(Dispatchers.IO) {
            rewardDao.findAll()
                    .map { it.convert() }
        }
    }

    override suspend fun findAllAsFlow(): Flow<List<Reward>> {
        return withContext(Dispatchers.IO) {
            rewardDao.findAllAsFlow()
                    .map { rewardEntityList ->
                        val rewardList = mutableListOf<Reward>()
                        rewardEntityList.forEach { entity -> rewardList.add(entity.convert()) }
                        rewardList.toList()
                    }
        }
    }

}