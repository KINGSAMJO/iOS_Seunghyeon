package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeRepositoryBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.HomeRepositoryAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import co.kr.sopt_seminar_30th.util.MyItemDecoration

class HomeRepositoryFragment : BaseFragment<FragmentHomeRepositoryBinding>() {
    override val TAG: String
        get() = HomeRepositoryFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home_repository

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var homeRepositoryAdapter: HomeRepositoryAdapter

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

    private fun initRecyclerView() {
        binding.rvHomeRepository.addItemDecoration(MyItemDecoration(5, 10, R.color.purple_100))
        homeRepositoryAdapter = HomeRepositoryAdapter()
        binding.rvHomeRepository.adapter = homeRepositoryAdapter
        homeViewModel.repository.observe(viewLifecycleOwner) {
            homeRepositoryAdapter.replaceItem(it)
        }
    }
}