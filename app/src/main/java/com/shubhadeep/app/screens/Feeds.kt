package com.shubhadeep.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.shubhadeep.app.screens.destinations.DisplayFeedItemDestination
import com.shubhadeep.app.services.apiService
import com.shubhadeep.app.types.FeedItem

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Destination
@Composable
fun Feeds(navigator: DestinationsNavigator) {
    var feeds by remember { mutableStateOf<List<FeedItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        val response = apiService.getFeeds()
        feeds = response
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(feeds) { feed ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    navigator.navigate(DisplayFeedItemDestination(feed = feed))
                }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = feed.title, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = rememberImagePainter(feed.thumbnailUrl),
                        contentDescription = feed.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
