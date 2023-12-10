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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.lhoyong.swiper.Swiper
import com.github.lhoyong.swiper.rememberSwiperState
import hu.ait.nonprofitapp.R
import hu.ait.nonprofitapp.data.NonprofitItem
import hu.ait.nonprofitapp.data.NonprofitType


//TODO
//plan: try to run the orgsliked page and makes sure NOTHING APPEARS
//then try to swipe, after you swipe on something, it should appear in the likedorgs
//for testing, make it so that as soon as you rightswipe ONCE, it takes u to the orgsliked page so u can see
//the thing you swiped right on appear on there!
//move forward from there

@ExperimentalMaterial3Api
@Composable
fun MySwipePage(
    navController: NavController,
    nonprofitViewModel: OrgViewModel = hiltViewModel(),
    ) {

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        //create all the nonprofits and populate the app

        val nonprofit1: NonprofitItem = NonprofitItem(
            0, //id
            "EcoImpact",
            "this is a description of the nonprofit this " +
                    "is a description of the nonprofitthis is a " +
                    "description of the nonprofitthis is a description" +
                    " of the nonprofitthis is a description of the " +
                    "nonprofitthis is a description of the nonprofitthis is " +
                    "a description of the nonprofitthis is a description of the " +
                    "nonprofitthis is a description of the nonprofitthis is a " +
                    "description of the nonprofitthis is a description of the nonprofit" +
                    "this is a description of the nonprofitthis is a description of the " +
                    "nonprofitthis is a description of the nonprofit",
            NonprofitType.Environmental,
            false
        )

        val nonprofit2: NonprofitItem = NonprofitItem(
            1, //id
            "Nonprofit name 2",
            "this is a description of the nonprofit this " +
                    "is a description of the nonprofitthis is a " +
                    "description of the nonprofitthis is a description" +
                    " of the nonprofitthis is a description of the " +
                    "nonprofitthis is a description of the nonprofitthis is " +
                    "a description of the nonprofitthis is a description of the " +
                    "nonprofitthis is a description of the nonprofitthis is a " +
                    "description of the nonprofitthis is a description of the nonprofit" +
                    "this is a description of the nonprofitthis is a description of the " +
                    "nonprofitthis is a description of the nonprofit",
            NonprofitType.Arts_Culture,
            false
        )

        val nonprofit3: NonprofitItem = NonprofitItem(
            2, //id
            "TechEd Innovators",
            "TechEd Innovators is an education nonprofit specializing in technology-driven learning experiences " +
                    "for underserved youth in urban communities. We .... more description here  more description here more description here " +
                    "more description here more description here" +
                    "more description here" +
                    "more description here" +
                    "more description here",
            NonprofitType.Education,
            false
        )


        val nonprofit4: NonprofitItem = NonprofitItem(
            3, //id
            "ThriveTogether",
            "EmpowerPlus is a poverty-focused nonprofit" +
                    " committed to breaking the cycle of poverty by providing " +
                    "comprehensive support and resources to individuals " +
                    "and families in economically disadvantaged communities.",
            NonprofitType.Poverty,
            false
        )

        nonprofitViewModel.addTodoList(
            nonprofit1
        )

        nonprofitViewModel.addTodoList(
            nonprofit2
        )

        nonprofitViewModel.addTodoList(
            nonprofit3)

        nonprofitViewModel.addTodoList(
            nonprofit4
        )
    }

    val items by
    nonprofitViewModel.getAllShoppingList().collectAsState(emptyList())

    for (item in items) {
        nonprofitViewModel.setFalse(item)
    }

   // var items by remember { mutableStateOf(SwipeConst.initialItems) }
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
                onResetClick = {} //changed
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
                    //TODO i think here try to connect it to the likedorgs page
                    //TODO: fix this
                    //wait actually what you should do is populate the items list by getting allt he values from the viewmodel
                    //those are the things that you should display, so here you should really just make it true
                    //when u make it true, the things that appear on the likedorgs hsould change

                    //TODO: whats the diff between a right and left swipe

                    //nonprofitViewModel.changeShoppingState(items[swiperState.currentIndex], value = true)
                    //text = items[swiperState.currentIndex].title

                    // text = swiperState.currentIndex.toString()
                    Log.i("Swiper", "swipe done, current index : ${swiperState.currentIndex}")

                    navController.navigate("likedorgs")


                }
            ) { index ->
                SwipeCard(Color.Blue) //changed
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