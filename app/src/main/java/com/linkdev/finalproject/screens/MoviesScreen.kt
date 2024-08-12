package com.linkdev.finalproject.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.linkdev.finalproject.fullImageURL
import com.linkdev.finalproject.data.remote.networkformoviesapi.response.MoviesResponse
import com.linkdev.finalproject.viewmodel.MoviesViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviesScreen(modifier: Modifier = Modifier, viewModel: MoviesViewModel = hiltViewModel()) {
    val moviesState by viewModel.moviesState.observeAsState()

    val context = LocalContext.current


    if (moviesState?.results.isNullOrEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(moviesState?.results ?: arrayListOf()) { movie ->
                movie?.let {
                    MovieItem(movie = it, context = context)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movie: MoviesResponse, context: android.content.Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, color = Color.Black), RoundedCornerShape(8.dp))
            .padding(bottom = 8.dp)
            .clickable {
                Toast.makeText(context, movie.title, Toast.LENGTH_LONG).show()
            }
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = movie.posterPath?.fullImageURL(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = movie.title ?: "MOVIE TITLE",
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(8.dp),
            text = movie.overview ?: "overview",
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
