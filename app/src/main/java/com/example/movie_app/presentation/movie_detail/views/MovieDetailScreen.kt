package com.example.movie_app.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.movie_app.presentation.movie_detail.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        contentAlignment = Center, modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        state.movieDetail?.let { movieDetail ->
            // Veri gelmezse diye
            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = movieDetail.poster),
                    contentDescription = movieDetail.title,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(400.dp, 400.dp)
                        .clip(RectangleShape)
                        .align(CenterHorizontally)
                )

                MovieDetailText(movieDetail = movieDetail.title)
                MovieDetailText(movieDetail = movieDetail.year)
                MovieDetailText(movieDetail = movieDetail.country)
                MovieDetailText(movieDetail = movieDetail.director)
                MovieDetailText(movieDetail = movieDetail.imdbRating)
            }
        }

        if (state.error.isNotBlank()) {
            // Hata varsa
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Center)
            )
        }

        if (state.isLoading) {
            // yükleniyorsa, daha veriler gelmemişse
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}

@Composable
fun MovieDetailText(movieDetail: String) {
    Text(
        text = movieDetail,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(14.dp),
        color = Color.White
    )
}