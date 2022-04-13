package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemHomeRepositoryBinding
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation

class HomeRepositoryAdapter: RecyclerView.Adapter<HomeRepositoryAdapter.HomeRepositoryViewHolder>() {
    private val asyncDiffer = AsyncListDiffer(this, diffCallback)

    class HomeRepositoryViewHolder(
        private val binding: ItemHomeRepositoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryInformation) {
            binding.repository = repository
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRepositoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeRepositoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_repository,
            parent,
            false
        )
        return HomeRepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRepositoryViewHolder, position: Int) {
        holder.bind(asyncDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncDiffer.currentList.size

    fun replaceItem(itemList: List<RepositoryInformation>) {
        asyncDiffer.submitList(itemList)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RepositoryInformation>() {
            override fun areItemsTheSame(
                oldItem: RepositoryInformation,
                newItem: RepositoryInformation
            ): Boolean = oldItem.repositoryName == newItem.repositoryName

            override fun areContentsTheSame(
                oldItem: RepositoryInformation,
                newItem: RepositoryInformation
            ): Boolean = oldItem == newItem
        }
    }
}