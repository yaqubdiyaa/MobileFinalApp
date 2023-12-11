package hu.ait.nonprofitapp.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.github.lhoyong.swiper.Swiper
import com.github.lhoyong.swiper.rememberSwiperState
import hu.ait.nonprofitapp.R
import hu.ait.nonprofitapp.data.NonprofitItem
import hu.ait.nonprofitapp.data.NonprofitType
import hu.ait.nonprofitapp.data.Profile


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
            "Empowering positive change for a sustainable future. Join us in making a lasting impact on the environment through innovative initiatives and community engagement."
                    ,
            NonprofitType.Environmental,
            false,
            "https://i.pinimg.com/736x/da/d1/02/dad102d1b94320812cc9c1f207f58390.jpg"
        )

        val nonprofit3: NonprofitItem = NonprofitItem(
            2, //id
            "TechEd Innovators",
            "Empowering Minds, Transforming Futures. We're a nonprofit dedicated to providing innovative tech education, fostering knowledge, and shaping the next generation of tech leaders.",
            NonprofitType.Education,
            false,
            "https://wellbeingmagazine.s3.eu-west-1.amazonaws.com/wp-content/uploads/2019/11/Unicef-%E2%80%93-A-Vital-Education-Charity-in-Thailand.jpg"
        )


        val nonprofit4: NonprofitItem = NonprofitItem(
            3, //id
            "ThriveTogether",
            "Breaking the Chains of Poverty. We're a nonprofit committed to empowering communities through education, resources, and sustainable initiatives. Join us in creating a world where everyone has the opportunity to thrive.",
            NonprofitType.Poverty,
            false,
            "https://st4.depositphotos.com/26566316/28347/i/450/depositphotos_283476720-stock-photo-charity-food-feeding-poor-hands.jpg"
        )

        nonprofitViewModel.addTodoList(
            nonprofit1
        )

        nonprofitViewModel.addTodoList(
            nonprofit3
        )

        nonprofitViewModel.addTodoList(
            nonprofit4
        )
    }





   // var items by remember { mutableStateOf(SwipeConst.profileList) }

    val items by nonprofitViewModel.getAllShoppingList().collectAsState(emptyList())

    /* TODO
    for (item in items) {
        nonprofitViewModel.setFalse(item)
    }

     */

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SwiperTopAppBar(
                navController,
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
                    .height(600.dp)
                    .padding(horizontal = 24.dp)
                    .semantics { contentDescription = "swiper" },
                state = swiperState,
                onSwiped = {
                    //if right swipe:
                    nonprofitViewModel.changeShoppingState(items[swiperState.currentIndex], value = true)
                    Log.i("Swiper", "swipe done, current index : ${swiperState.currentIndex}")
                }
            )
            { index ->
                Box {
                    // Layer for SwipeCard
                    SwipeCard(nonprofit = items[index])

                    // Layer for Spacer (adjust height as needed)
                    Spacer(modifier = Modifier.height(100.dp))
                }
                Text(
                    text = items[index].description,
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier
//                        .fillMaxSize()
                        .fillMaxWidth()
                        .padding(50.dp)
                        .background(Color(0xFFeb586e))
                        .border(10.dp, Color(0xFFeb586e), RoundedCornerShape(32.dp))
                        .padding(18.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Cancel Button
                IconButton(
                    onClick = { /* Add your cancel button logic here */ },
                    modifier = Modifier
                        .size(100.dp)
                        .background(shape = CircleShape, color = Color.Gray)
                ) {
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = "Cancel")
                }

                // Love Button
                IconButton(
                    onClick = { /* Add your love button logic here */ },
                    modifier = Modifier
                        .size(100.dp)
                        .background(shape = CircleShape, color = Color(0xFFeb586e))
                ) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Love", tint = Color.White)
                }
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

@Composable
private fun SwipeCard(nonprofit:NonprofitItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(32.dp))
    ) {
        Image(
            painter = rememberImagePainter(
                data = nonprofit.image,
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
                text = nonprofit.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                lineHeight = 50.sp
            )
        }
    }
}


/*
@Preview(widthDp = 80, heightDp = 80)
@Composable
private fun SwipeItemPreview() {
    val profile = Profile("1", "Robin", "https://meadowsoutpatient.com/wp-content/uploads/2020/12/group-of-people-in-expressive-arts-therapy.jpg")
    Surface {
        SwipeCard(profile)
    }
}

 */



/*
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "topAppbar-dark")
@Composable
private fun MySwipePagePreview() {
    MaterialTheme {
        MySwipePage()
    }
}

 */




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwiperTopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileIcon(modifier = Modifier.clickable { /* Handle profile icon click */ })
        AppIcon(modifier = Modifier.clickable { /* Handle app icon click */ })
        HeartIcon(modifier = Modifier.clickable { navController.navigate("likedorgs") })
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

