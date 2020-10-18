package kztproject.jp.splacounter.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kztproject.jp.splacounter.presentation.HomeActivity
import kztproject.jp.splacounter.auth.di.AuthModule
import kztproject.jp.splacounter.presentation.HomeViewModel
import kztproject.jp.splacounter.reward.di.RewardModule
import project.seito.screen_transition.di.ViewModelKey

@Module
internal abstract class HomeActivityModule {
    @ContributesAndroidInjector(modules = [RewardModule::class, AuthModule::class, TodoModule::class])
    internal abstract fun contributeBaseActivity(): HomeActivity

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
