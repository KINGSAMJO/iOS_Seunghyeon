package co.kr.sopt_seminar_30th.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentOnBoardingSecondBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingSecondFragment : BaseFragment<FragmentOnBoardingSecondBinding>() {
    override val TAG: String
        get() = OnBoardingSecondFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_on_boarding_second

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBtnNextClickListener()
    }

    private fun setOnBtnNextClickListener() {
        binding.btnOnBoardingNext.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_on_boarding_second_to_fragment_on_boarding_third)
        }
    }
}