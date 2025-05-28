package ng.tagithub.myhomeapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel: ViewModel()  {

    private val _isDefaultLauncher = MutableLiveData<Boolean>()
    val isDefaultLauncher: LiveData<Boolean> = _isDefaultLauncher

    /** Call this when you detect the app has become the default launcher. */
    fun setAsDefaultLauncher(isDefault: Boolean) {
        _isDefaultLauncher.value = isDefault
    }



}