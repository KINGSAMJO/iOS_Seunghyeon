package co.kr.sopt_seminar_30th.presentation.ui.home.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentProfileRepositoryBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.ProfileRepositoryAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import co.kr.sopt_seminar_30th.util.MyItemTouchHelperForRepository

class ProfileRepositoryFragment : BaseFragment<FragmentProfileRepositoryBinding>() {
    override val TAG: String
        get() = ProfileRepositoryFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_profile_repository

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var _profileRepositoryAdapter: ProfileRepositoryAdapter? = null
    private val profileRepositoryAdapter get() = _profileRepositoryAdapter ?: error("Adapter not initialized")
    private var _myItemTouchHelperForRepository: MyItemTouchHelperForRepository? = null
    private val myItemTouchHelperForRepository get() = _myItemTouchHelperForRepository ?: error("ItemTouchHelper not initialized")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeLiveData()
        homeViewModel.getRepositoryList()
    }

    override fun onDestroyView() {
        _profileRepositoryAdapter = null
        _myItemTouchHelperForRepository = null
        super.onDestroyView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initRecyclerView() {
        _profileRepositoryAdapter = ProfileRepositoryAdapter {
            homeViewModel.deleteRepository(it)
        }
        binding.rvProfileRepository.adapter = profileRepositoryAdapter
        _myItemTouchHelperForRepository =
            MyItemTouchHelperForRepository(profileRepositoryAdapter) {
                homeViewModel.updateRepositoryList(profileRepositoryAdapter.getItemList())
            }.apply {
                setClamp((resources.displayMetrics.widthPixels.toFloat() - 30) / 4)
            }
        ItemTouchHelper(myItemTouchHelperForRepository).attachToRecyclerView(
            binding.rvProfileRepository
        )
        binding.rvProfileRepository.setOnTouchListener { _, _ ->
            myItemTouchHelperForRepository.removePreviousClamp(binding.rvProfileRepository)
            false
        }
    }

    private fun observeLiveData() {
        homeViewModel.repository.observe(
            viewLifecycleOwner,
            profileRepositoryAdapter::updateItemList
        )
    }
}