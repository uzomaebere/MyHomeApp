package ng.tagithub.myhomeapp.views

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ng.tagithub.myhomeapp.R
import ng.tagithub.myhomeapp.data.PreferenceHelper
import ng.tagithub.myhomeapp.databinding.ActivityHomeBinding
import ng.tagithub.myhomeapp.utils.isAppDefaultLauncher
import ng.tagithub.myhomeapp.viewmodels.HomeViewmodel
import ng.tagithub.myhomeapp.views.adapter.AppAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: AppAdapter
    private lateinit var viewModel: HomeViewmodel
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    private val sharedPref: PreferenceHelper by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLauncherSetup()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewmodel::class.java]
        binding.lifecycleOwner = this

        // Display apps if app is default launcher
        showAppDrawer()

    }

    private fun showAppDrawer() {

        adapter = AppAdapter(emptyList(), packageManager) { app ->
            val intent = packageManager.getLaunchIntentForPackage(app.activityInfo.packageName)
            intent?.let { startActivity(it) }
        }

        binding.rvApps.layoutManager = GridLayoutManager(this, 4)
        binding.rvApps.adapter = adapter

        viewModel.appList.observe(this) { apps ->
            adapter.updateData(apps)
        }

        if (viewModel.appList.value == null) {
            val apps = getApps()
            viewModel.appList.value = apps
        }
    }

    private fun checkLauncherSetup() {

        val isNowDefault = `isAppDefaultLauncher`()

        val intent = Intent(this, OnboardingActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

        // For first time launch
        if (!sharedPref.isFirstLaunch()) {
            Log.d("HomeActivity", "First time launch")
            sharedPref.setFirstLaunchDone(true)
            startActivity(intent)
            finish()
            //  return
        } else if (isNowDefault && !sharedPref.getSetupCompleteStatus()) {
            Log.d("HomeActivity", "App set as default")
            // go directly to the Step 3 if launcher is default but setup not complete
            intent.putExtra("stepNo", 3)
            startActivity(intent)
            finish()
        } else if (!isNowDefault && !sharedPref.getSetupCompleteStatus()) {
            Log.d("HomeActivity", "App set as default")
            //  Go to step 2
            intent.putExtra("stepNo", 2)
            startActivity(intent)
            finish()
        }


    }

    private fun getApps(): List<ResolveInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        return packageManager.queryIntentActivities(intent, 0)
    }

}