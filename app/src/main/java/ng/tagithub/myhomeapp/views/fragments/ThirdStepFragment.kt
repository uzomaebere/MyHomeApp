package ng.tagithub.myhomeapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ng.tagithub.myhomeapp.R
import ng.tagithub.myhomeapp.data.PreferenceHelper
import ng.tagithub.myhomeapp.databinding.FragmentThirdStepBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdStepFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdStepFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var binding: FragmentThirdStepBinding
    private val sharedPreferences: PreferenceHelper by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third_step, container, false)

        binding.btnContinueStep3.setOnClickListener {
            // Save setup completion
            sharedPreferences.isSetupComplete(true)
            activity?.finish()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}