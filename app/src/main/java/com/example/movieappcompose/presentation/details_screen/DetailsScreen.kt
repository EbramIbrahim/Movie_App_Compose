package com.example.movieappcompose.presentation.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.movieappcompose.domain.model.Media
import com.example.movieappcompose.utils.Constant
import com.example.movieappcompose.utils.RatingBar
import com.example.movieappcompose.utils.getAverageColor

@Composable
fun DetailsScreen(
    mediaDetails: Media?,
    navController: NavController
) {

    val viewModel = hiltViewModel<DetailsViewModel>()
    val similarList = viewModel.movieDetails.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        mediaDetails?.let {
            viewModel.getMovieDetails(
                type = it.mediaType,
                id = it.id,
                page = 1
            )
        }
    }

    val mainImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constant.IMAGE_BASE_URL + mediaDetails?.backdropPath)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val posterImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constant.IMAGE_BASE_URL + mediaDetails?.posterPath)
            .size(Size.ORIGINAL)
            .build()
    ).state


    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        defaultColor,
                        dominantColor
                    )
                )
            ),
    ) {



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            // MainImageSection
            if (mainImageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Rounded.ImageNotSupported,
                        contentDescription = mediaDetails?.title
                    )
                }
            }

            if (mainImageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    painter = mainImageState.painter,
                    contentDescription = mediaDetails?.title,
                    contentScale = ContentScale.Crop
                )
                dominantColor = getAverageColor(
                    imageBitmap = mainImageState.result.drawable.toBitmap().asImageBitmap()
                )

            }
            // MainImageSection

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                // PosterImageSection
                Column {
                    // PosterImageSection

                    Spacer(modifier = Modifier.height(200.dp))
                    Card(
                        modifier = Modifier
                            .width(180.dp)
                            .height(250.dp)
                            .padding(start = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(11.dp)
                    ) {
                        if (posterImageState is AsyncImagePainter.State.Error) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(70.dp),
                                    imageVector = Icons.Rounded.ImageNotSupported,
                                    contentDescription = mediaDetails?.title
                                )
                            }
                        }
                        if (posterImageState is AsyncImagePainter.State.Success) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp)),
                                painter = posterImageState.painter,
                                contentDescription = mediaDetails?.title,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                // PosterImageSection


                Spacer(modifier = Modifier.width(8.dp))

                // MovieInfoSection
                Column {
                    Spacer(modifier = Modifier.height(250.dp))
                    mediaDetails.let { media ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = media?.title ?: "",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                RatingBar(
                                    starsModifier = Modifier.size(18.dp),
                                    rating = media?.voteAverage?.div(2) ?: 0.0
                                )

                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    text = media?.voteAverage.toString().take(3),
                                    color = Color.LightGray,
                                    fontSize = 14.sp,
                                    maxLines = 1,
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Language: ${media?.originalLanguage}"
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Release Date: ${media?.releaseDate}"
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "${media?.releaseDate} Votes"
                            )
                        }
                    }
                }
                // PosterImageSection
            }
        }
        Spacer(modifier = Modifier.height(26.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "OverView:",
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = mediaDetails?.overview ?: "",
            fontSize = 16.sp,
        )


        SimilarListSection(
            detailsState = similarList,
            navController = navController
        )




    }


}













