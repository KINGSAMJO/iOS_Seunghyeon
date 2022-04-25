package co.kr.sopt_seminar_30th.presentation.ui.home.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.FragmentProfileFollowerBinding
import co.kr.sopt_seminar_30th.presentation.ui.adapter.ProfileFollowerAdapter
import co.kr.sopt_seminar_30th.presentation.ui.base.BaseFragment
import co.kr.sopt_seminar_30th.presentation.ui.detail.DetailActivity
import co.kr.sopt_seminar_30th.presentation.viewmodel.HomeViewModel
import co.kr.sopt_seminar_30th.util.MyItemDecoration
import co.kr.sopt_seminar_30th.util.MyItemTouchHelperForFollower

class ProfileFollowerFragment : BaseFragment<FragmentProfileFollowerBinding>() {
    override val TAG: String
        get() = ProfileFollowerFragment::class.java.simpleName
    override val layoutRes: Int
        get() = R.layout.fragment_profile_follower

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var profileFollowerAdapter: ProfileFollowerAdapter

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

    private fun initRecyclerView() {
        binding.rvProfileFollower.addItemDecoration(
            MyItemDecoration(
                5,
                10,
                ContextCompat.getColor(requireContext(), R.color.purple_100)
            )
        )
        profileFollowerAdapter = ProfileFollowerAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.apply {
                putExtra("name", it.followerName)
                putExtra("description", it.followerDescription)
                putExtra("image", it.followerImage)
            }
            startActivity(intent)
        }
        binding.rvProfileFollower.adapter = profileFollowerAdapter
        ItemTouchHelper(
            MyItemTouchHelperForFollower(profileFollowerAdapter, {
                homeViewModel.updateFollowerList(profileFollowerAdapter.getItemList())
            }, {
                homeViewModel.deleteFollower(it)
            })
        ).attachToRecyclerView(
            binding.rvProfileFollower
        )
    }

    private fun observeLiveData() {
        homeViewModel.follower.observe(viewLifecycleOwner) {
            profileFollowerAdapter.updateItemList(it.map { follower -> follower.copy() })
        }
    }
}