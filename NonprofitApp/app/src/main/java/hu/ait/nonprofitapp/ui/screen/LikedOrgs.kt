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

        /*val nonprofit2: NonprofitItem = NonprofitItem(
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

         */

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

        /* nonprofitViewModel.addTodoList(
             nonprofit2
         )

         */

        nonprofitViewModel.addTodoList(
            nonprofit3
        )

        nonprofitViewModel.addTodoList(
            nonprofit4
        )
    }

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
        TopAppBar(
            title = {
                Text("OURAPPNAME")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                IconButton(onClick = {
                    nonprofitViewModel.clearAll()
                }) {
                    Icon(Icons.Filled.Delete, null)
                }

                //add item button
                IconButton(onClick = {
                    shoppingToEdit = null //not editing tho!
                    showAddDialog = true //dialog will pop up
                }) {
                    Icon(Icons.Filled.AddCircle, null)
                }
            })


        Column(modifier = modifier.padding(10.dp)) {
            if (showAddDialog) {
                AddNewShoppingForm(
                    nonprofitViewModel,
                    { showAddDialog = false },
                    shoppingToEdit
                )
            }

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
                /*
                TODO: CATEGORY ICON
                Image(
                    painter = painterResource(id = shoppingItem.category.getIcon()),
                    contentDescription = "Category",
                    modifier = Modifier
                        .size(55.dp)
                        .padding(end = 10.dp)
                )

                 */


                Column() {
                    Text(shoppingItem.title, style = TextStyle(fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold, color = Color.Black))
                    Text(shoppingItem.description)
                }
                /*
                Text(shoppingItem.title, modifier = Modifier.fillMaxWidth(0.2f))
                Spacer(modifier = Modifier.fillMaxSize(0.35f))

                Checkbox(
                    checked = shoppingItem.isLiked,
                    onCheckedChange = { onShoppingCheckChange(it) }
                )
                 */
               Spacer(modifier = Modifier.width(150.dp))
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.clickable {
                        onRemoveItem()
                    },
                    tint = Color.Red,
                )

                Button(onClick = { openDialogue = !openDialogue }) {
                    Text(text = "Donate")
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

//TODO is this needed
@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun AddToLiked(
    nonprofitViewModel: OrgViewModel = hiltViewModel(),
    //TODO figure out if you are passing in name or the full nonprofitItem?
    //or are you creating the item here
    //actually i think you should pass in the full item
    nonprofitToAdd: NonprofitItem? = null
) {
    AddNewShoppingForm(nonprofitViewModel = nonprofitViewModel,
        {}, nonprofitToAdd
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddNewShoppingForm(
    nonprofitViewModel: OrgViewModel,
    onDialogDismiss: () -> Unit = {},
    nonprofitToEdit: NonprofitItem? = null
) {
    Dialog(
        onDismissRequest = onDialogDismiss
    ) {

        var shoppingTitle by rememberSaveable {
            mutableStateOf(nonprofitToEdit?.title ?: "")
        }

        var shoppingDesc by rememberSaveable {
            mutableStateOf(nonprofitToEdit?.description ?: "")
        }


        var shoppingCategory by rememberSaveable {
            mutableStateOf(
                nonprofitToEdit?.category ?: NonprofitType.None //initial value
            )
        }

        var inputErrorState by rememberSaveable {
            mutableStateOf(false)
        }

        var errorField by rememberSaveable {
            mutableStateOf("")
        }

        var expanded by rememberSaveable { mutableStateOf(false) } // initial value

        fun validate() {
            if (shoppingTitle == "") {
                inputErrorState = true
                errorField = "Title"
            }

            if (shoppingCategory == NonprofitType.None) {
                inputErrorState = true
                errorField = "Category"
            }

            if (shoppingDesc == "") {
                inputErrorState = true
                errorField = "Description"
            }
        }

        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = shoppingTitle,
                onValueChange = {
                    shoppingTitle = it
                },
                label = { Text(text = "Item name") }
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = shoppingDesc,
                onValueChange = {
                    shoppingDesc = it
                },
                label = { Text(text = "Description") }
            )

            var preselected = ""
            if (nonprofitToEdit != null) {
                preselected = nonprofitToEdit.category.toString()
            }

            Row {
                SpinnerSample(
                    list = listOf("Food", "Book", "Electronic"),
                    //if existing item and editing take that one
                    preselected = preselected,
                    onSelectionChanged = { selected ->
                        if (selected == "Food") {
                            shoppingCategory = NonprofitType.Environmental
                        } else if (selected == "Book") {
                            shoppingCategory = NonprofitType.Poverty
                        } else if (selected == "Electronic"){
                            shoppingCategory = NonprofitType.Rights_Justice
                        } else {
                            shoppingCategory = NonprofitType.None
                        }
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {
                    validate()
                    if (!inputErrorState) {
                        if (nonprofitToEdit == null) {
                            nonprofitViewModel.addTodoList(
                                NonprofitItem(
                                    0, //id
                                    shoppingTitle,
                                    shoppingDesc,
                                    shoppingCategory,
                                    false
                                )
                            )
                        } else {
                            var shoppingEdited = nonprofitToEdit.copy(
                                title = shoppingTitle,
                                description = shoppingDesc,
                                category = shoppingCategory
                            )
                           nonprofitViewModel.editShoppingItem(shoppingEdited)
                        }
                        onDialogDismiss()
                    }
                }) {
                    Text(text = "Save")
                }

                if (inputErrorState) {
                    AlertDialog(
                        onDismissRequest = {
                            inputErrorState = false
                            errorField = ""},
                        title = {
                            Text(errorField)
                        },
                        confirmButton = {
                            Button(
                                onClick = {inputErrorState = false
                                    errorField = ""})
                            {
                                Text(text = "OK")
                            }})
                }
            }
        }
    }
}

@Composable
fun SpinnerSample(
    list: List<String>,
    preselected: String,
    onSelectionChanged: (myData: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by rememberSaveable { mutableStateOf(preselected) }
    var expanded by rememberSaveable { mutableStateOf(false) } // initial value
    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = selected,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Icon(
                Icons.Outlined.ArrowDropDown, null, modifier =
                Modifier.padding(8.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                list.forEach { listEntry ->
                    DropdownMenuItem(
                        onClick = {
                            selected = listEntry
                            expanded = false
                            onSelectionChanged(selected)
                        },
                        text = {
                            Text(
                                text = listEntry,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            )
                        },
                    )
                }
            }
        }
    }
}

