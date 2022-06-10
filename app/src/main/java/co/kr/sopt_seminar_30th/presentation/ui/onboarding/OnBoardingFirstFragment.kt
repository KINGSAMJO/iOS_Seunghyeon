package co.kr.sopt_seminar_30th.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.data.datasource.local.SopthubDataStore
import co.kr.sopt_seminar_30th.databinding.FragmentOnBoardingFirstBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFirstFragment : BaseFragment<FragmentOnBoardingFirstBinding>() {
    override val TAG: String
        get() = OnBoardingFirstFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_on_boarding_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBtnNextClickListener()
    }

    private fun setOnBtnNextClickListener() {
        binding.btnOnBoardingNext.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_on_boarding_first_to_fragment_on_boarding_second)
        }
    }
}