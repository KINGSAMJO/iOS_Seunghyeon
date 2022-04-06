package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentEditProfileBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {
    override val TAG: String
        get() = EditProfileFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_edit_profile

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickSave()
        observeEditProfile()
    }

    private fun clickSave() {
        binding.btnSave.setOnClickListener {
            homeViewModel.editProfile()
        }
    }

    private fun observeEditProfile() {
        homeViewModel.updateSuccess.observe(viewLifecycleOwner) {
            when(it) {
                true -> {
                    parentFragmentManager.popBackStack()
                }
                else -> {
                    Toast.makeText(requireContext(), "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}