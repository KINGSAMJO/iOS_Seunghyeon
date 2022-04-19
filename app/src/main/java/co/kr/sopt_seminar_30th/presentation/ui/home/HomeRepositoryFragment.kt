package co.kr.sopt_seminar_30th.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeRepositoryBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.HomeRepositoryAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import co.kr.sopt_seminar_30th.util.MyItemDecoration
import co.kr.sopt_seminar_30th.util.MyItemTouchHelperForRepository

class HomeRepositoryFragment : BaseFragment<FragmentHomeRepositoryBinding>() {
    override val TAG: String
        get() = HomeRepositoryFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home_repository

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var homeRepositoryAdapter: HomeRepositoryAdapter
    private lateinit var myItemTouchHelperForRepository: MyItemTouchHelperForRepository

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
        homeViewModel.getRepositoryList()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initRecyclerView() {
        homeRepositoryAdapter = HomeRepositoryAdapter {
            homeViewModel.deleteRepository(it)
        }
        binding.rvHomeRepository.adapter = homeRepositoryAdapter
        myItemTouchHelperForRepository =
            MyItemTouchHelperForRepository(homeRepositoryAdapter) {
                homeViewModel.updateRepositoryList(homeRepositoryAdapter.getItemList())
            }.apply {
                setClamp((resources.displayMetrics.widthPixels.toFloat() - 30) / 4)
            }
        ItemTouchHelper(myItemTouchHelperForRepository).attachToRecyclerView(
            binding.rvHomeRepository
        )
        binding.rvHomeRepository.setOnTouchListener { _, _ ->
            myItemTouchHelperForRepository.removePreviousClamp(binding.rvHomeRepository)
            false
        }
        homeViewModel.repository.observe(viewLifecycleOwner) {
            homeRepositoryAdapter.updateItemList(it)
        }
    }
}