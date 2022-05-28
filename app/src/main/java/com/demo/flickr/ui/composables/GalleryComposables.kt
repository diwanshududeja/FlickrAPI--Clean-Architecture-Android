package com.demo.flickr.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.*
import coil.request.CachePolicy
import com.demo.flickr.R
import com.demo.flickr.data.vo.PhotoDetail
import com.demo.flickr.ui.composables.theme.defaultPadding

@Composable
fun ImagesGallery(photos: List<PhotoDetail>, onPhotoClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(photos) { photo ->
            ImageItem(photo, onPhotoClick)
        }
    }

}

@Composable
fun ImageItem(photo: PhotoDetail, onPhotoClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(defaultPadding)
            .clickable {
                photo.id?.let { onPhotoClick(it) }
            }
    ) {
        Column {
            val painter =
                rememberImagePainter(
                    data = photo.url_m,
                    builder = {
                        diskCachePolicy(CachePolicy.ENABLED)
                        crossfade(true)
                        error(R.drawable.error_placeholder)
                        placeholder(R.drawable.loader)
                    }

                )
            ShowImage(photo, painter)
        }
    }
}

@Composable
private fun ShowImage(
    photo: PhotoDetail,
    painter: ImagePainter
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = photo.title,
    )
}



