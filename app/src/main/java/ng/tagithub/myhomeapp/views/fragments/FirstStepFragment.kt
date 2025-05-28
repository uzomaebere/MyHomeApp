package ng.tagithub.myhomeapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import ng.tagithub.myhomeapp.R
import ng.tagithub.myhomeapp.databinding.FragmentFirstStepBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FirstStepFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstStepFragment : Fragment() {

    private lateinit var binding: FragmentFirstStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_step, container, false)

        binding.btnContinueStep1.setOnClickListener {
            val action = FirstStepFragmentDirections.actionFirstStepFragmentToSecondStepFragment()
            findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}