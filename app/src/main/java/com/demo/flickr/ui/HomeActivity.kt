package com.demo.flickr.ui

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.ViewModelProvider
import com.demo.flickr.R
import com.demo.flickr.factory.HomeViewModelFactory
import com.demo.flickr.ui.composables.ImagesGallery
import com.demo.flickr.ui.composables.SearchField
import com.demo.flickr.ui.state.HomeScreenState
import com.demo.flickr.utils.Constants
import com.demo.flickr.utils.Util
import com.demo.flickr.ui.viewmodel.HomeViewModel
import dagger.android.support.DaggerAppCompatActivity
import com.demo.flickr.ui.composables.theme.AppTheme
import com.demo.flickr.ui.composables.theme.defaultPadding
import javax.inject.Inject


class HomeActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent{
            HomeContent()
        }
        init()

    }

    private fun init() {
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        if (Util.isNetworkConnected(this)) {
            viewModel.getPhotosList(Constants.DEFAULT_SEARCH_TAG)
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun onCardClick(photoId : String){
        Toast.makeText(this,getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun HomeContent(){
        val currentFocus = LocalFocusManager.current
        val state = viewModel.state.observeAsState().value
        AppTheme {
            if (state != null) {
                HomeComposable(
                    state = state,
                    searchTextInput = Constants.DEFAULT_SEARCH_TAG,
                    onSearchTextInputChange = viewModel::changeSearchTextInput,
                    onSearchClick = {
                        viewModel.onSearchClick()
                        currentFocus.clearFocus()
                    },
                    onCardClick = this::onCardClick,
                )
            }
        }
    }

    @Composable
    fun HomeComposable(
        state: HomeScreenState,
        searchTextInput: String = "",
        onSearchTextInputChange: (String) -> Unit = {},
        onSearchClick: () -> Unit = {},
        onCardClick: (String) -> Unit = {},
    ){
        Box {
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxSize()
                    .padding(defaultPadding)
            ) {
                SearchField(
                    textInput = searchTextInput,
                    hint = resources.getString(R.string.search_hint),
                    onTextInputChange = onSearchTextInputChange,
                    onSearchClick = onSearchClick,
                )
                Spacer(modifier = Modifier.size(defaultPadding))
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    HomeState(state, onCardClick)
                }
            }
        }
    }

    @Composable
    private fun HomeState(state: HomeScreenState, onCardClick: (String) -> Unit) {
        when (state) {
            is HomeScreenState.Loading -> CircularProgressIndicator()
            is HomeScreenState.Images -> ImagesGallery(
                state.photos,
                onPhotoClick = onCardClick
            )
            is HomeScreenState.Error -> Text(state.errorMessage)
        }
    }


}