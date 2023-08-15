package app.te.hero_cars.presentation.home.ui_screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.te.hero_cars.R
import app.te.hero_cars.presentation.add_stolen_phone.ADD_STOLEN_PHONE_ROUTE
import app.te.core.utils.ShowLottieLoading
import app.te.core.custom_views.dialogs.PreviewImageDialog
import app.te.core.utils.HandleApiError
import app.te.core.extension.findActivity
import app.te.core.extension.navigateSafe
import app.te.core.utils.hideSoftInput
import app.te.core.utils.openDial
import app.te.hero_cars.presentation.home.ui_state.SearchState
import app.te.hero_cars.presentation.home.ui_state.StolenUiItemState
import app.te.hero_cars.presentation.home.view_model.HomeViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val searchResult = viewModel.searchResponse.collectAsState()
    val searchState = viewModel.stateSearch
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SearchSection(searchState, viewModel, navHostController)
        }, content = { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (searchResult.value.isLoading) {
                    hideSoftInput(context.findActivity())
                    ShowLottieLoading()
                }
                if (searchResult.value.failureStatus != null) {
                    HandleApiError(
                        activity = LocalContext.current.findActivity(),
                        searchResult.value.failureStatus!!
                    )
                }

                ResultSection(searchResult.value.data, context)
            }
        })
}

@Composable
fun ResultSection(data: List<StolenUiItemState>?, context: Context) {
    val openDialog = remember { mutableStateOf("") }

    if (data != null)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            items(data) { item ->
                ItemSearchScreen(data = item, onCallClick = { phoneNumber ->
                    openDial(context = context, phoneNumber)
                }, onImagePreview = { imageUrl ->
                    openDialog.value = imageUrl
                })
            }
        }
    else
        EmptyContent()

    if (openDialog.value.isNotEmpty())
        PreviewImageDialog(image = openDialog.value) {
            openDialog.value = ""
        }
}

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_content))
        val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.no_stolen_phones),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    state: SearchState, viewModel: HomeViewModel, navHostController: NavHostController,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val (btn_search, search_container, add_new_post) = createRefs()
        IconButton(
            onClick = { viewModel.searchForStolen() },
            modifier = Modifier
                .border(.5.dp, color = MaterialTheme.colorScheme.background, shape = CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
                .constrainAs(btn_search) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(parent.top, 10.dp)
                }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search",
                tint = MaterialTheme.colorScheme.background,

                )
        }
        OutlinedTextField(
            value = state.serial,
            onValueChange = {
                viewModel.onSerialChange(it)
            },
            modifier = Modifier
                .constrainAs(search_container) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(btn_search.end, 4.dp)
                    end.linkTo(parent.end, 10.dp)
                    width = Dimension.fillToConstraints
                }
                .border(
                    .5.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.large
                ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.home_search_hint),
                    color = MaterialTheme.colorScheme.background
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { viewModel.searchForStolen() }
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Key,
                    contentDescription = "Phone number",
                    tint = MaterialTheme.colorScheme.background
                )
            },
            shape = MaterialTheme.shapes.large,

            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.Unspecified,
                cursorColor = MaterialTheme.colorScheme.background,
//                textColor = MaterialTheme.colorScheme.background
            )
        )

        OutlinedButton(
            onClick = { navHostController.navigateSafe(ADD_STOLEN_PHONE_ROUTE) },
            modifier = Modifier
                .constrainAs(add_new_post) {
                    start.linkTo(btn_search.start)
                    end.linkTo(search_container.end)
                    top.linkTo(search_container.bottom, 6.dp)
                    bottom.linkTo(parent.bottom, 7.dp)
                    width = Dimension.fillToConstraints
                },
            border = BorderStroke(.5.dp, color = MaterialTheme.colorScheme.background)

        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "plus",
                    tint = MaterialTheme.colorScheme.background
                )
                Text(
                    text = stringResource(id = app.te.hero_cars.R.string.add_new_stolen_phone),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}
