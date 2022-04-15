package co.kr.sopt_seminar_30th.presentation.ui.home

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentEditProfileBinding
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import com.bumptech.glide.Glide

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {
    override val TAG: String
        get() = EditProfileFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_edit_profile

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                homeViewModel.userImage.value = imageUri
            } else if(it.resultCode == RESULT_CANCELED) {
                Toast.makeText(requireContext(), "사진 선택이 취소되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

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
        showImageToast()
        changeProfileImage()
        clickSave()
        observeEditProfile()
    }

    private fun showImageToast() {
        Toast.makeText(requireContext(), "프로필 사진을 변경하려면 사진을 클릭하세요", Toast.LENGTH_SHORT).show()
    }

    private fun clickSave() {
        binding.btnSave.setOnClickListener {
            homeViewModel.editProfile()
        }
    }

    private fun observeEditProfile() {
        homeViewModel.updateSuccess.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    parentFragmentManager.popBackStack()
                }
                else -> {
                    Toast.makeText(requireContext(), "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun changeProfileImage() {
        binding.ivProfileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            galleryLauncher.launch(intent)
        }
    }
}