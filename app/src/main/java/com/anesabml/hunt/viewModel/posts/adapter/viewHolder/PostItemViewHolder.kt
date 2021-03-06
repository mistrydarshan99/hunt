package com.anesabml.hunt.viewModel.posts.adapter.viewHolder

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.anesabml.hunt.R
import com.anesabml.hunt.databinding.ItemPostBinding
import com.anesabml.hunt.model.PostEdge
import com.anesabml.hunt.viewModel.posts.PostsListInteractions

class PostItemViewHolder(
    private val binding: ItemPostBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        postsListInteractions: PostsListInteractions,
        postEdge: PostEdge
    ) {
        val post = postEdge.node
        with(binding) {
            thumbnail.load(post.thumbnail?.url) {
                crossfade(true)
                transformations(RoundedCornersTransformation(16f))
            }
            name.text = post.name
            tagline.text = post.tagline
            votes.text = post.votesCount.toString()

            val animation = AnimationUtils.loadAnimation(
                root.context,
                R.anim.item_animation_from_bottom
            )
            root.animation = animation
            root.transitionName = post.id
            root.setOnClickListener {
                postsListInteractions.onClickPost(this.root, post)
            }
        }
    }
}
