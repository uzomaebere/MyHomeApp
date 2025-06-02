package ng.tagithub.myhomeapp.viewmodels

import android.content.pm.ResolveInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewmodel : ViewModel() {

    private val _appList = MutableLiveData<List<ResolveInfo>>()
    val appList: LiveData<List<ResolveInfo>> get() = _appList

    fun updateAppList(newList: List<ResolveInfo>) {
        _appList.value = newList
    }


}