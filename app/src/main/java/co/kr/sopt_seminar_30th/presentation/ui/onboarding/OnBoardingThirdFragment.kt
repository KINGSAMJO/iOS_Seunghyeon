package co.kr.sopt_seminar_30th.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.databinding.FragmentOnBoardingThirdBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingThirdFragment : BaseFragment<FragmentOnBoardingThirdBinding>() {
    override val TAG: String
        get() = OnBoardingThirdFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_on_boarding_third

    @Inject
    lateinit var dataStore: SopthubDataStore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBtnStartClickListener()
    }

    private fun setOnBtnStartClickListener() {
        binding.btnOnBoardingStart.setOnClickListener {
            dataStore.onBoardingEnabled = true
            findNavController().navigate(R.id.action_fragment_on_boarding_third_to_activity_sign_in)
            requireActivity().finish()
        }
    }
}