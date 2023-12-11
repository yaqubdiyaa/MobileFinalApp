package hu.ait.nonprofitapp.ui.screen

import androidx.compose.foundation.lazy.items
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import hu.ait.nonprofitapp.data.NonprofitItem
import hu.ait.nonprofitapp.data.NonprofitType

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedOrgs(
    modifier: Modifier = Modifier,
    nonprofitViewModel: OrgViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    //TODO this is only supposed to get the liked stuff... confused...
    //its currently returning way more than the isliked
    val shoppingList by
    nonprofitViewModel.getAllLiked().collectAsState(emptyList())

    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var shoppingToEdit: NonprofitItem? by rememberSaveable {
        mutableStateOf(null)
    }


    Column {
        SwiperTopAppBar(navController = rememberNavController(), modifier = Modifier)

            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(shoppingList) {
                    ShoppingCard(
                        shoppingItem = it,
                        onRemoveItem = { nonprofitViewModel.removeShoppingItem(it) },
                    )
                }
            }
        }
    }

@Composable
fun ShoppingCard(
                shoppingItem: NonprofitItem,
                 onRemoveItem: () -> Unit
)
{
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp)
    ) {

        //extra function
        var openDialogue by rememberSaveable {
            mutableStateOf((false))
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .animateContentSize(
                    animationSpec = spring()
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween,
            ) {
//
                Image(
                    painter = painterResource(id = shoppingItem.category.getIcon()),
                    contentDescription = "Category",
                    modifier = Modifier
                        .size(55.dp)
                        .padding(end = 30.dp)
                )

                Column(Modifier
                    .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp) // Space between items
                ) {
                    Text(shoppingItem.title, style = TextStyle(fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold, color = Color.Black))
                    Text(shoppingItem.description)
                    Button(
                        onClick = { openDialogue = !openDialogue },
                        colors = ButtonDefaults.buttonColors(Color(0xFFeb586e))
                    )
                     {
                        Text(text = "Donate")
                    }
                }
            }

            if (openDialogue) {
                openDonate( shoppingItem
                ) { openDialogue = false }
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun openDonate(
    shoppingItem: NonprofitItem,
    onDialogDismiss: () -> Unit = {},
) { Dialog(
    onDismissRequest =  onDialogDismiss
) {
    var shoppingTitle by rememberSaveable {
        mutableStateOf(shoppingItem.title ?: "")
    }

    //TODO: decide if we need this
    var shoppingCategory by rememberSaveable {
        mutableStateOf(
            shoppingItem.category ?: NonprofitType.None //initial value
        )}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Donate ")

                OutlinedTextField(
                    value = "1",
                    onValueChange = {},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .width(40.dp)
                        .alignByBaseline()
                )

                Text(" dollar to " + shoppingItem.title)
            }
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { onDialogDismiss()}) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { onDialogDismiss()}) {
                        Text(text = "Confirm")
                    }
                }
        }
    }
}
}
