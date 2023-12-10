package hu.ait.nonprofitapp.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.github.lhoyong.swiper.Swiper
import com.github.lhoyong.swiper.rememberSwiperState
import hu.ait.nonprofitapp.R
import hu.ait.nonprofitapp.data.Profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySwipePage() {
    var items by remember { mutableStateOf(SwipeConst.profileList) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SwiperTopAppBar(
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val swiperState = rememberSwiperState()
            Swiper(
                count = items.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(horizontal = 24.dp)
                    .semantics { contentDescription = "swiper" },
                state = swiperState,
                onSwiped = {
                    Log.i("Swiper", "swipe done, current index : ${swiperState.currentIndex}")
                }
            ) { index ->
                SwipeCard(profile = items[index])

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                        .align(Alignment.BottomEnd),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                )
            }
        }
    }
}

@Composable
private fun Controller(
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ControllerButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(56.dp),
            text = "reset",
            onClick = onResetClick,
        )
        ControllerButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(56.dp),
            text = "reset",
            onClick = {},
        )
        ControllerButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(56.dp),
            text = "reset",
            onClick = {},
        )
    }
}

@Composable
private fun RowScope.ControllerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f)
            .height(56.dp),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "topAppbar-dark")
@Composable
private fun TopAppBarPreview() {
    MaterialTheme {
        SwiperTopAppBar()
    }
}


@Composable
private fun SwipeCard(profile: Profile) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = rememberImagePainter(
                data = profile.imageUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )

        // Display the profile name on the bottom left corner
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = profile.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp
            )
        }
    }
}

@Preview(widthDp = 80, heightDp = 80)
@Composable
private fun SwipeItemPreview() {
    val profile = Profile("1", "Robin", "https://meadowsoutpatient.com/wp-content/uploads/2020/12/group-of-people-in-expressive-arts-therapy.jpg")
    Surface {
        SwipeCard(profile)
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "topAppbar-dark")
@Composable
private fun MySwipePagePreview() {
    MaterialTheme {
        MySwipePage()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwiperTopAppBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileIcon(modifier = Modifier.clickable { /* Handle profile icon click */ })
        AppIcon(modifier = Modifier.clickable { /* Handle app icon click */ })
        HeartIcon(modifier = Modifier.clickable { /* Handle heart icon click */ })
    }
}

@Composable
private fun ProfileIcon(modifier: Modifier = Modifier, iconColor: Color = Color(0xFFeb586e)) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_person_24),
        contentDescription = null,
        tint = iconColor,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp),
    )
}

@Composable
private fun AppIcon(modifier: Modifier = Modifier, iconColor: Color = Color(0xFFeb586e)) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_local_fire_department_24),
        contentDescription = null,
        tint = iconColor,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
    )
}

@Composable
private fun HeartIcon(modifier: Modifier = Modifier, iconColor: Color = Color(0xFFeb586e)) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_favorite_24),
        contentDescription = null,
        tint = iconColor,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
    )
}

