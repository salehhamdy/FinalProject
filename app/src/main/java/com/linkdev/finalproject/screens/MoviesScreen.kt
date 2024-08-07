package com.linkdev.finalproject.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.linkdev.finalproject.fullImageURL
import com.linkdev.finalproject.network_for_api_1.RetrofitBuilder
import com.linkdev.finalproject.network_for_api_1.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviesScreen(modifier: Modifier = Modifier) {
    var MoviesState: MoviesResponse? by remember {
        mutableStateOf(null)
    }

//   val context= LocalContext.current

    LaunchedEffect(key1 = Unit) {
        launch(Dispatchers.IO) {
            delay(1000)
            try {

                MoviesState = RetrofitBuilder.getAPIService().getMoviesTrending().body()

            } catch (e: Exception) {

                Log.d("ERROR", "mi4 4a8ala ya 8aby: ${e.message}")
            }
        }
    }

        LazyColumn(modifier = modifier) {
            items(MoviesState?.results ?: emptyList()) { movie ->
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke(1.dp, color = Color.Black), RoundedCornerShape(8.dp))
                        .padding(bottom = 8.dp)
//                        .clickable { Toast.makeText(context,movie.title,Toast.LENGTH_LONG).show() }

                ) {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        model = movie.posterPath.fullImageURL(),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        text = movie.title ?:"MOVIET-TITLE",
                        fontWeight =FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = movie.overview ?:"overview",
                        fontWeight =FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
}

