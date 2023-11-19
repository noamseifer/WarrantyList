package com.example.warrantylist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.warrantylist.data.WarrantyItem
import com.example.warrantylist.data.warranties
import com.example.warrantylist.ui.theme.UIConstants
import com.example.warrantylist.ui.theme.WarrantyListTheme
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarrantyListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    WarrantyApp()
                }
            }
        }
    }
}

/**
* Composable that displays a warranty information.
*
* @param warrantyItem is the resource for the information of a specific warranty
* @param modifier modifiers to set to this composable
*/

@Composable
fun WarrantyInformationCard(
    warrantyItem: WarrantyItem,
    modifier: Modifier = Modifier
) {
    // date format
    val formattedDate = warrantyItem.purchaseDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    val expirationDate = warrantyItem.expirationDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))


    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = warrantyItem.productName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Text(
            text = "Purchase Date: $formattedDate",
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Text(
            text = "Expiration Date: $expirationDate",
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}

@Composable
fun WarrantiesScreenTopBar()
{

    var selectedItem by remember{ mutableStateOf(0) }

    NavigationBar {
        UIConstants.topNavBarConstants.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = (selectedItem == index),
                onClick = { selectedItem = index },
                icon = {},
                label = { Text(text = item)},
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarrantyColumnScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WarrantiesScreenTopBar()
        },
        bottomBar = {
            WarrantiesScreenTopBar()
        },
//        snackbarHost = {
//            // Content for the snackbar host
//        },
//        floatingActionButton = {
//            // Content for the floating action button
//        },
//        floatingActionButtonPosition = FabPosition.End,

        contentColor = MaterialTheme.colorScheme.secondary, // use themed colors later
//        contentWindowInsets = LocalWindowInsets.current,
        content = { paddingValues ->
            // Main content area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                LazyColumn {
                    items(warranties) { warrantyItem ->
                        WarrantyInformationCard(
                            warrantyItem = warrantyItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun WarrantyApp()
{
    WarrantyColumnScreen()
}

// for preview purposes
@Preview(showBackground = true)
@Composable
fun WarrantyPreview() {
    WarrantyApp()
}