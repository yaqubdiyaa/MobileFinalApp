package hu.ait.nonprofitapp.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.lhoyong.swiper.Swiper
import com.github.lhoyong.swiper.rememberSwiperState
import hu.ait.nonprofitapp.R

@ExperimentalMaterial3Api
@Composable
fun MySwipePage() {

    var items by remember { mutableStateOf(SwipeConst.initialItems) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SwiperTopAppBar(
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            Controller(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .navigationBarsPadding(),
                onResetClick = { items = SwipeConst.newItems }
            )
        }
    ) { innerPaddings ->
        var text by remember(items) { mutableStateOf("0") }
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
                    text = swiperState.currentIndex.toString()
                    Log.i("Swiper", "swipe done, current index : ${swiperState.currentIndex}")
                }
            ) { index ->
                SwipeCard(items[index])
            }

            Spacer(modifier = Modifier.heightIn(min = 24.dp))
            Text(text = "totalCount ${items.size}")
            Spacer(modifier = Modifier.heightIn(min = 8.dp))
            Text(text = "current Index $text")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwiperTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = { Text(stringResource(id = R.string.app_name)) },
    )
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
private fun SwipeCard(
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = color, shape = RoundedCornerShape(12.dp))
    )
}

@Preview(widthDp = 80, heightDp = 160)
@Composable
private fun SwipeItemPreview() {
    Surface {
        SwipeCard(Color.Red)
    }
}