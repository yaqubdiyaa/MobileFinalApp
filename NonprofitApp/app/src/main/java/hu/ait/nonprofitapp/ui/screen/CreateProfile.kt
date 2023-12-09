package hu.ait.nonprofitapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import hu.ait.nonprofitapp.R
import java.util.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen() {
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("")}

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.create_profile)) },
            navigationIcon = {
                IconButton(onClick = { /* Handle back button click */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        Column(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = "bio",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                textStyle = TextStyle.Default,
                label = { Text("Write something interesting") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                maxLines = 5,)
        }
        Spacer(modifier = Modifier.height(16.dp))
        NonProfitCategoryDropdown()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NonProfitCategoryDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    val categories = listOf("Environment", "Education", "Health", "Social Services", "Arts and Culture")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Button(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedCategory ?: "Select Category")
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category) },
                    onClick = {
                        selectedCategory = category
                        expanded = false
                    },
                    modifier = Modifier
                        .clickable { /* empty clickable modifier to handle onClick */ },
                    leadingIcon = { /* optional leading icon */ },
                    trailingIcon = { /* optional trailing icon */ },
                    enabled = true,
                    contentPadding = PaddingValues(16.dp)
                )
            }
        }
    }
}