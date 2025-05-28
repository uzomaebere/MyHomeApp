package ng.tagithub.myhomeapp.viewmodels

import android.content.pm.ResolveInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewmodel : ViewModel() {
    val appList = MutableLiveData<List<ResolveInfo>>()


}