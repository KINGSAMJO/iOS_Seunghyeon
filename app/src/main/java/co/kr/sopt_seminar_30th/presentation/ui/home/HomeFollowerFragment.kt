package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeFollowerBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.HomeFollowerAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import co.kr.sopt_seminar_30th.util.MyItemDecoration
import co.kr.sopt_seminar_30th.util.MyItemTouchHelper

class HomeFollowerFragment : BaseFragment<FragmentHomeFollowerBinding>() {
    override val TAG: String
        get() = HomeFollowerFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home_follower

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var homeFollowerAdapter: HomeFollowerAdapter

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
        homeViewModel.getFollowerList()
    }

    private fun initRecyclerView() {
        binding.rvHomeFollower.addItemDecoration(MyItemDecoration(5, 10, R.color.purple_100))
        homeFollowerAdapter = HomeFollowerAdapter {

        }
        binding.rvHomeFollower.adapter = homeFollowerAdapter
        ItemTouchHelper(MyItemTouchHelper(homeFollowerAdapter)).attachToRecyclerView(binding.rvHomeFollower)
        homeViewModel.follower.observe(viewLifecycleOwner) {
            homeFollowerAdapter.replaceItem(it)
        }
    }
}