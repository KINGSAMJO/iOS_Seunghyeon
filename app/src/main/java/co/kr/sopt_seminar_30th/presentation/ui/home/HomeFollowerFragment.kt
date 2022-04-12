package co.kr.sopt_seminar_30th.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeFollowerBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.HomeFollowerAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel

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
        homeFollowerAdapter = HomeFollowerAdapter()
        binding.rvHomeFollower.adapter = homeFollowerAdapter
        homeViewModel.follower.observe(viewLifecycleOwner) {
            homeFollowerAdapter.replaceItem(it)
        }
    }
}