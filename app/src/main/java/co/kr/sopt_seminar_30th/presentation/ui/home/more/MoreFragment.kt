package co.kr.sopt_seminar_30th.presentation.ui.home.more

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentMoreBinding
import co.kr.sopt_seminar_30th.presentation.ui.auth.SignInActivity
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel

class MoreFragment : BaseFragment<FragmentMoreBinding>() {
    override val TAG: String
        get() = MoreFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_more

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                navigateGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "갤러리 접근 권한이 없습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val imageUri = it.data?.data
                homeViewModel.userImage.value = imageUri
            } else if (it.resultCode == RESULT_CANCELED) {
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
        setOnTurnOffAutoLoginClickListener()
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

    private fun setOnTurnOffAutoLoginClickListener() {
        binding.btnTurnOffAutoLogin.setOnClickListener {
            homeViewModel.turnOffAutoLogin()
            homeViewModel.turnOffSuccess.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(requireContext(), "자동로그인 해제", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), SignInActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
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
            when {
                checkSelfPermissionGranted() -> {
                    navigateGallery()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showInContextUI()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun checkSelfPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun navigateGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        galleryLauncher.launch(intent)
    }

    private fun showInContextUI() {
        AlertDialog.Builder(requireContext())
            .setTitle("권한 동의 필요")
            .setMessage("프로필 사진 수정을 위해 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton("거부") { _, _ ->
                Toast.makeText(requireContext(), "갤러리 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }
}