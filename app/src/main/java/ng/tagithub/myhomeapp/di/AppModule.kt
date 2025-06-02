package ng.tagithub.myhomeapp.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import ng.tagithub.myhomeapp.data.PreferenceHelper
import ng.tagithub.myhomeapp.viewmodels.HomeViewmodel
import ng.tagithub.myhomeapp.viewmodels.KodeinViewModelFactory
import ng.tagithub.myhomeapp.viewmodels.OnboardingViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppModule: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy{
        import(androidXModule(this@AppModule))

        bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(kodein) }
        bind<HomeViewmodel>() with provider { HomeViewmodel() }
        bind<OnboardingViewModel>() with provider { OnboardingViewModel() }
        bind<PreferenceHelper>() with singleton { PreferenceHelper(instance()) }
    }
}