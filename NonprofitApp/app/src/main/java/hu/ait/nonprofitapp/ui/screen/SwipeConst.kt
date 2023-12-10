package hu.ait.nonprofitapp.ui.screen

import androidx.compose.ui.graphics.Color
import hu.ait.nonprofitapp.data.Profile

object SwipeConst {
    val initialItems = listOf(
        Color.Red,
        Color.Cyan,
        Color.Blue,
        Color.Green,
        Color.Magenta,
        Color.Yellow
    )

    val newItems = listOf(
        Color.Blue,
        Color.Green,
        Color.Magenta,
        Color.Yellow
    )

    val profileList = listOf(
        Profile(id = "1", name = "ArtAid", imageUrl = "https://meadowsoutpatient.com/wp-content/uploads/2020/12/group-of-people-in-expressive-arts-therapy.jpg"),
        Profile(id = "2", name = "EcoHelp", imageUrl = "https://i.pinimg.com/736x/da/d1/02/dad102d1b94320812cc9c1f207f58390.jpg"),
        Profile(id = "3", name = "HealHub", imageUrl = "https://media.euobserver.com/b3c9e875da4550acd9ed0ee1b75dde02.jpg"),
    )
}