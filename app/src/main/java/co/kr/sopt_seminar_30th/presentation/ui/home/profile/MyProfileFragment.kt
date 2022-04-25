package co.kr.sopt_seminar_30th.presentation.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentMyProfileBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel

class MyProfileFragment : BaseFragment<FragmentMyProfileBinding>() {
    override val TAG: String
        get() = MyProfileFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_my_profile

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getUserInformation()
        editProfile()
    }

    private fun editProfile() {
        binding.btnEdit.setOnClickListener {
            parentFragment?.let { parent ->
                parent.parentFragmentManager.commit {
                    replace<EditProfileFragment>(R.id.fcv_home, EDIT_PROFILE_FRAGMENT)
                    addToBackStack(EDIT_PROFILE_FRAGMENT)
                }
            }
        }
    }

    companion object {
        val EDIT_PROFILE_FRAGMENT: String = EditProfileFragment::class.java.simpleName
    }
}