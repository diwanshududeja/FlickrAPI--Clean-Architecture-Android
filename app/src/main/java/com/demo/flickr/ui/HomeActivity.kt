package com.demo.flickr.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.flickr.R
import com.demo.flickr.ui.adapter.PhotosAdapter
import com.demo.flickr.databinding.ActivityHomeBinding
import com.demo.flickr.factory.HomeViewModelFactory
import com.demo.flickr.utils.Constants
import com.demo.flickr.utils.Util
import com.demo.flickr.ui.viewmodel.HomeViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class HomeActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        viewModel.photosList.observe(this, Observer {

            binding.progressLoader.visibility = GONE
            binding.recyclerViewPhotos.visibility = VISIBLE
            binding.recyclerViewPhotos.layoutManager = (GridLayoutManager(this, 2))
            val adapter = binding.recyclerViewPhotos.adapter as PhotosAdapter
            adapter.photosList = it
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, getString(R.string.api_issue), Toast.LENGTH_SHORT).show()
            finish()
        })

    }

    private fun init() {
        binding.recyclerViewPhotos.adapter = PhotosAdapter()
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        if (Util.isNetworkConnected(this)) {
            viewModel.getPhotosList(Constants.SEARCH_QUERY)
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }

    }

}