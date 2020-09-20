package kztproject.jp.splacounter.reward.application.usecase

import kztproject.jp.splacounter.reward.application.model.Result
import kztproject.jp.splacounter.reward.domain.model.RewardInput

interface SaveRewardUseCase {
    suspend fun execute(reward: RewardInput): Result<Unit>
}