package jp.kztproject.rewardedtodo.domain.reward.repository

import kotlinx.coroutines.flow.Flow
import jp.kztproject.rewardedtodo.domain.reward.Reward
import jp.kztproject.rewardedtodo.domain.reward.RewardInput

interface IRewardRepository {

    suspend fun createOrUpdate(reward: RewardInput)

    suspend fun delete(reward: Reward)

    suspend fun findBy(id: Int): Reward?

    suspend fun findAll(): List<Reward>

    suspend fun findAllAsFlow(): Flow<List<Reward>>
}