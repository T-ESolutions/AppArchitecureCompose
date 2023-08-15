package app.te.hero_cars.presentation.add_stolen_phone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.core.utils.CenterAlignedTopAppBarCustom
import app.te.core.utils.HandleApiError
import app.te.core.utils.checkPickPermission
import app.te.core.extension.findActivity
import app.te.hero_cars.R
import app.te.hero_cars.data.general.data_source.dto.countries.CityModel
import app.te.hero_cars.data.general.data_source.dto.countries.Government
import app.te.hero_cars.data.home.dto.Brand
import app.te.hero_cars.data.home.dto.Model
import app.te.hero_cars.presentation.add_stolen_phone.events.AddStolenFormEvent
import app.te.hero_cars.presentation.add_stolen_phone.states.AddStolenFormState
import app.te.hero_cars.presentation.add_stolen_phone.states.AddStolenResultState
import app.te.hero_cars.presentation.add_stolen_phone.view_model.AddStolenPhoneViewModel
import app.te.core.utils.ShowLottieLoading
import app.te.core.custom_views.alert.AlerterError
import app.te.core.custom_views.alert.AlerterPosition
import app.te.core.extension.GetOnceResult
import app.te.core.extension.navigateSafe
import app.te.core.custom_views.dialogs.CustomAlertDialog
import app.te.hero_cars.presentation.base.extensions.*
import app.te.core.utils.textFieldInteractionSource
import app.te.hero_cars.presentation.general.screens.*
import app.te.hero_cars.presentation.home.HomeComposeActivity
import app.te.hero_cars.presentation.home.nav_graph.BottomBarScreen
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import java.io.File

const val ADD_STOLEN_PHONE_ROUTE = "add_stolen_phone_screen"

@Composable
fun AddStolenPhoneScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: AddStolenPhoneViewModel = viewModel(),
) {
    val validateState = viewModel.state
    val resultState = viewModel.resultState.collectAsState()

    GetResult(navHostController, viewModel)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                title = R.string.add_new_stolen_phone,
                navHostController = navHostController
            )
        }, content = {
            SetupObservers(validateState, resultState, navHostController)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(it)
            ) {
                item {
                    TopSection()
                }
                item {
                    InputSection(
                        state = validateState,
                        viewModel = viewModel,
                        navHostController = navHostController
                    )
                }
                item {
                    BottomSection(viewModel = viewModel)
                }
            }
        })
}

@Composable
fun SetupObservers(
    validateState: AddStolenFormState,
    resultState: State<AddStolenResultState>,
    navHostController: NavHostController
) {
    val openDialog = remember { mutableStateOf(false) }

    if (validateState.checkBoxCheckedError != null)
        AlerterError(
            message = LocalContext.current.getString(R.string.please_accept_terms),
            position = AlerterPosition.Float
        )

    if (resultState.value.isLoading)
        ShowLottieLoading()

    if (resultState.value.failureStatus != null) {
        HandleApiError(
            activity = LocalContext.current.findActivity(),
            resultState.value.failureStatus!!
        )
    }

    if (resultState.value.data != null) {
        openDialog.value = true
    }

    if (openDialog.value) {
        CustomAlertDialog(
            image = R.drawable.ic_success,
            body = R.string.successfully_sent,
            executeButtonText = app.te.core.R.string.done,
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            onDismiss = {
                openDialog.value = false
                navHostController.popBackStack(BottomBarScreen.Home.route, inclusive = false)
            }
        ) {
            openDialog.value = false
            navHostController.popBackStack(BottomBarScreen.Home.route, inclusive = false)
        }
    }
}

@Composable
fun GetResult(navHostController: NavHostController, viewModel: AddStolenPhoneViewModel) {
    navHostController.GetOnceResult<Government>(keyResult = GOVERNMENT_ROUTE) { government ->
        viewModel.updateGovern(government)
    }

    navHostController.GetOnceResult<CityModel>(keyResult = CITIES_ROUTE) { city ->
        viewModel.updateCity(city)
    }
    navHostController.GetOnceResult<Brand>(keyResult = BRANDS_ROUTE) { brand ->
        viewModel.updateBrand(brand)
    }
    navHostController.GetOnceResult<Model>(keyResult = MODELS_ROUTE) { model ->
        viewModel.updateModel(model)
    }
}

@Composable
@Preview(showBackground = true)
fun TopSection() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.news))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE)
    Spacer(modifier = Modifier.height(15.dp))
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    )
    Text(
        text = stringResource(id = R.string.help_to_find),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSection(
    state: AddStolenFormState = AddStolenFormState(),
    viewModel: AddStolenPhoneViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val activity = LocalContext.current.findActivity()
    val focusManager = LocalFocusManager.current

    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = state.govern,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.GovernChanged(it))
        },
        isError = state.governError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.govern)
            )
        },
        singleLine = true,
        readOnly = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "Govern"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Synagogue,
                contentDescription = "Govern"
            )
        },
        interactionSource = textFieldInteractionSource {
            navHostController.navigateSafe(LocationsScreens.GovernmentScreen.route)
        }
    )
    // Showing error
    if (state.governError != null) {
        Text(
            text = state.governError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = state.city,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.CityChanged(it))
        },
        isError = state.cityError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.city)
            )
        },
        singleLine = true,
        readOnly = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "Arrow Down"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.LocationCity,
                contentDescription = "City"
            )
        },
        interactionSource = textFieldInteractionSource {
            if (viewModel.state.governId.isNotEmpty())
                navHostController.navigateSafe(
                    destination = LocationsScreens.CitiesScreen.passGovId(
                        viewModel.state.governId
                    )
                )
        }
    )

    if (state.cityError != null) {
        Text(
            text = state.cityError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = state.brand,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.BrandChanged(it))
        },
        isError = state.brandError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.brand)
            )
        },
        readOnly = true,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "brand"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.BrandingWatermark,
                contentDescription = "brand"
            )
        },
        interactionSource = textFieldInteractionSource {
            navHostController.navigateSafe(
                destination = BrandModelScreens.BrandsScreen.route
            )
        }
    )
    // Showing error
    if (state.brandError != null) {
        Text(
            text = state.brandError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = state.model,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.ModelChanged(it))
        },
        isError = state.modelError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.model)
            )
        },
        readOnly = true,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "model"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.ModelTraining,
                contentDescription = "model"
            )
        },
        interactionSource = textFieldInteractionSource {
            if (viewModel.state.brandId.isNotEmpty())
                navHostController.navigateSafe(
                    destination = BrandModelScreens.ModelsScreen.passBrandId(
                        viewModel.state.brandId
                    )
                )
        }
    )
    // Showing error
    if (state.modelError != null) {
        Text(
            text = state.modelError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = state.phone,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.PhoneChanged(it))
        },
        isError = state.phoneError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.phone_hint)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = "Name"
            )
        },
    )
    // Showing error for email
    if (state.phoneError != null) {
        Text(
            text = state.phoneError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = state.serial,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.SerialChanged(it))
        },
        isError = state.serialError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.serial_number)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),

        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.ConfirmationNumber,
                contentDescription = "Phone number"
            )
        },
    )
    // Showing error for password
    if (state.serialError != null) {
        Text(
            text = state.serialError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
        value = stringResource(id = state.imageText ?: R.string.choose_image),
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.ImageChanged(it))
        },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.choose_image)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),

        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.InsertPhoto,
                contentDescription = "InsertPhoto"
            )
        },
        interactionSource = textFieldInteractionSource {
            if (checkPickPermission(activity))
                selectImage(activity as HomeComposeActivity, viewModel)
        }
    )

    OutlinedTextField(
        value = state.notes,
        onValueChange = {
            viewModel.onEvent(AddStolenFormEvent.NotesChanged(it))
        },
        isError = state.notesError != null,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.notes)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
    )
    // Showing error for email
    if (state.notesError != null) {
        Text(
            text = state.notesError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.checkBoxChecked,
            onCheckedChange = { viewModel.onEvent(AddStolenFormEvent.CheckBoxChanged(it)) })
        Text(
            text = stringResource(id = R.string.add_new_stolen_phone_warning),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelMedium
        )
    }

}

private fun selectImage(
    homeComposeActivity: HomeComposeActivity,
    viewModel: AddStolenPhoneViewModel
) {
    homeComposeActivity.filePicker.pickImage() { result ->
        val file: File? = result?.second
        if (file != null) {
            viewModel.updateSelectedImage(file.path, R.string.image_selected)
        }
    }
}

@Composable
fun BottomSection(
    viewModel: AddStolenPhoneViewModel = viewModel()
) {
    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = {
            viewModel.onEvent(AddStolenFormEvent.Submit)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimaryDark)),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = stringResource(id = R.string.send),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }

}
