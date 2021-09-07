package com.trianglz.test_doubles.common.posts.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.common.posts.presentation.recyclerview.PostsAdapter
import com.trianglz.test_doubles.databinding.FragmentPostsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    //region Members
    private var _binding: FragmentPostsBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var adapter: PostsAdapter

    //endregion

    //region Private Api

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()

    }

    private fun initObservers() {
        viewModel.postsList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun initViews() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = this@PostsFragment.adapter
        }
    }

    //endregion
    //regionPublic Api
    companion object{
        private const val TAG = "PostsFragment"
    }
    //endregion
}