package ng.tagithub.myhomeapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ng.tagithub.myhomeapp.R
import ng.tagithub.myhomeapp.databinding.FragmentSecondStepBinding
import ng.tagithub.myhomeapp.utils.isAppDefaultLauncher
import ng.tagithub.myhomeapp.viewmodels.OnboardingViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * A simple [Fragment] subclass.
 * Use the [SecondStepFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondStepFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: FragmentSecondStepBinding
    private val viewModelFactory: ViewModelProvider.Factory by instance()
    private val viewModel: OnboardingViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[OnboardingViewModel::class.java]
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val isAppDefault = requireContext().isAppDefaultLauncher()
            viewModel.setAsDefaultLauncher(isAppDefault)

            if (isAppDefault){
                // Go to Step 3
                val action = SecondStepFragmentDirections.actionSecondStepFragmentToThirdStepFragment()
                findNavController().navigate(action)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second_step, container, false)

        binding.btnContinueStep2.setOnClickListener {
            val intent = Intent(Settings.ACTION_HOME_SETTINGS)
            launcher.launch(intent)
        }

        // Inflate the layout for this fragment
        return binding.root
    }



}