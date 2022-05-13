package co.kr.sopt_seminar_30th.presentation.ui.home.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentHomeFollowerBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.ProfileFollowerAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.ui.detail.DetailActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import co.kr.sopt_seminar_30th.util.MyItemDecoration
import co.kr.sopt_seminar_30th.util.MyItemTouchHelperForFollower

class HomeFollowerFragment : BaseFragment<FragmentHomeFollowerBinding>() {
    override val TAG: String
        get() = HomeFollowerFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_home_follower

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var _profileFollowerAdapter: ProfileFollowerAdapter? = null
    private val profileFollowerAdapter get() = _profileFollowerAdapter ?: error("Adapter not initialized")

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
        observeLiveData()
    }

    override fun onDestroyView() {
        _profileFollowerAdapter = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.rvHomeFollower.addItemDecoration(
            MyItemDecoration(
                5,
                10,
                ContextCompat.getColor(requireContext(), R.color.purple_100)
            )
        )
        _profileFollowerAdapter = ProfileFollowerAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.apply {
                putExtra("name", it.userId)
                putExtra("image", it.profileImageUrl)
            }
            startActivity(intent)
        }
        binding.rvHomeFollower.adapter = profileFollowerAdapter
        ItemTouchHelper(
            MyItemTouchHelperForFollower(profileFollowerAdapter, {
                homeViewModel.updateFollowerList(profileFollowerAdapter.getItemList())
            }, {
                // TODO: 5, 6차 때 서버에서 받은 데이터와 Room 데이터를 맞춰나가도록 구현
            })
        ).attachToRecyclerView(
            binding.rvHomeFollower
        )
    }

    private fun observeLiveData() {
        homeViewModel.follower.observe(viewLifecycleOwner) {
            profileFollowerAdapter.updateItemList(it.map { follower -> follower.copy() })
        }
    }
}