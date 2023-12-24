package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.artspace.model.AN1963_10_102
import com.example.artspace.model.AN1963_10_109
import com.example.artspace.model.AN2014_136_117
import com.example.artspace.model.ArtSpaceImage
import com.example.artspace.ui.theme.ArtSpaceTheme


class MainActivity : ComponentActivity() {

  private val images = arrayOf(AN1963_10_102, AN2014_136_117, AN1963_10_109)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ArtSpaceTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          ArtSpaceApp(images = images)
        }
      }
    }
  }

}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp(
  modifier: Modifier = Modifier,
  images: Array<ArtSpaceImage> = arrayOf(AN1963_10_102, AN2014_136_117, AN1963_10_109),
  ) {

  val n = images.size
  var i by remember { mutableIntStateOf(0) }
  val image = images[i]

  val previous: () -> Unit = { i = if (i == 0) (n - 1) else (i - 1) % n }
  val next: () -> Unit = { i = (i + 1) % n }

  Column(modifier = modifier) {
    ArtSpaceImage(id = image.id, description = "${image.title}, ${image.year}")
    ArtSpaceImageDescription(
      title = image.title,
      artistName=image.artist,
      year = image.year,
    )
    ArtSpaceControls(
      onPrevious = previous,
      onNext = next,
    )
  }
}

@Composable
fun ArtSpaceImage(
  @DrawableRes id: Int,
  description: String,
  ) {
  Image(painter = painterResource(id), contentDescription = description)
}

@Composable
fun ArtSpaceImageDescription(
  title: String,
  artistName: String = "Artwork Artist",
  year: String = "Year"
  ) {
  Column {
    Text(text = title)
    Text(text = "$artistName ($year)")
  }
}

@Composable
fun ArtSpaceControls(
  onPrevious: () -> Unit,
  onNext: () -> Unit,
) {
  Row {
    Button(onClick = onPrevious) {
      Text(text = "Previous")
    }
    Button(onClick = onNext) {
      Text(text = "Next")
    }
  }
}
