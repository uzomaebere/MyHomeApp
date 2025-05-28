package ng.tagithub.myhomeapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ng.tagithub.myhomeapp.R
import ng.tagithub.myhomeapp.databinding.ActivityOnboardingBinding
import ng.tagithub.myhomeapp.utils.isDefaultLauncher
import ng.tagithub.myhomeapp.viewmodels.HomeViewmodel
import ng.tagithub.myhomeapp.viewmodels.OnboardingViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class OnboardingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewModel: OnboardingViewModel
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        viewModel = ViewModelProvider(this, viewModelFactory)[OnboardingViewModel::class.java]

        binding.lifecycleOwner = this

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerOnboarding) as NavHostFragment
        navController = navHostFragment.navController

        // Check
        viewModel.isDefaultLauncher.observe(this) { isDefault ->
            if (isDefault && navController.currentDestination?.id != R.id.thirdStepFragment) {
                Log.d("OnboardingActivity", "Load Step 3")
                navController.navigate(R.id.action_firstStepFragment_to_thirdStepFragment)
            }
            else if (!isDefault && navController.currentDestination?.id != R.id.secondStepFragment){
                Log.d("OnboardingActivity", "Load Step 2")
                navController.navigate(R.id.action_firstStepFragment_to_secondStepFragment)
            }
        }

        // Check for current step no
        checkCurrentStep()

    }

    private fun checkCurrentStep() {
        val stepNo = intent.getIntExtra("stepNo", 0)

        if (stepNo == 3) {
            // Set LiveData to true to handle navigation to Step 3
            viewModel.setAsDefaultLauncher(true)
        } else if (stepNo == 2) {
            // Go to step 2 if app not set
            viewModel.setAsDefaultLauncher(false)
        //
        }
    }

}