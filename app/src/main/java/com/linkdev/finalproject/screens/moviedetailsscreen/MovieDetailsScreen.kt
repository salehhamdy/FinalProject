package com.linkdev.finalproject.screens.moviedetailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.linkdev.finalproject.data.remote.networkformoviesapi.response.MovieDetailsResponse
import java.text.NumberFormat
import java.util.*

@Composable
fun MovieDetailsScreen(modifier: Modifier = Modifier, moviedetails: MovieDetailsResponse?) {
    moviedetails?.let { details ->
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 56.dp)  // To account for the bottom navigation bar
        ) {
            // Movie Poster
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${details.poster_path}"),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Movie Title
            Text(
                text = details.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Release Date and Rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Release Date: ${details.release_date}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Rating: ${details.vote_average} / 10",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Genres
            if (details.genres.isNotEmpty()) {
                Text(
                    text = "Genres",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(details.genres.size) { index ->
                        Text(
                            text = details.genres[index].name,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Overview
            Text(
                text = "Overview",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = details.overview,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Details
            Text(
                text = "Additional Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Budget: ${formatCurrency(details.budget)}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Revenue: ${formatCurrency(details.revenue)}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Profit: ${numberFormat.format((details.revenue - details.budget))}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Runtime: ${formatRuntime(details.runtime)}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )


            Text(
                text = "Status: ${details.status}",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    } ?: run{
        // If movie details are null, display a message
        Text(
            text = "Movie details are unavailable",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

// format runtime function
fun formatRuntime(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60
    return "${hours}h ${minutes}m"
}

// format currency function
fun formatCurrency(value: Int): String {
    return when {
        value >= 1_000_000_000 -> String.format(Locale.US, "$%.1fB", value / 1_000_000_000.0)
        value >= 1_000_000 -> String.format(Locale.US, "$%.1fM", value / 1_000_000.0)
        value >= 1_000 -> String.format(Locale.US, "$%,d", value)
        else -> String.format(Locale.US, "$%d", value)
    }
}
