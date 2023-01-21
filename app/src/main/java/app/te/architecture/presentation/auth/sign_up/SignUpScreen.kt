package app.te.architecture.presentation.auth.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.architecture.R
import app.te.architecture.presentation.auth.sign_up.events.SignUpFormEvent
import app.te.architecture.presentation.auth.sign_up.state.SignUpFormState
import app.te.architecture.presentation.auth.sign_up.view_model.SignUpViewModel
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.data.general.data_source.dto.countries.Government
import app.te.architecture.presentation.base.ShowLottieLoading
import app.te.architecture.presentation.base.custom_views.AlerterPosition
import app.te.architecture.presentation.base.custom_views.AlerterSuccess
import app.te.architecture.presentation.base.extensions.*
import app.te.architecture.presentation.base.utils.textFieldInteractionSource
import app.te.architecture.presentation.general.screens.CITIES_ROUTE
import app.te.architecture.presentation.general.screens.GOVERNMENT_ROUTE
import app.te.architecture.presentation.general.screens.LocationsScreens
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun SignUpScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: SignUpViewModel = viewModel()
) {
    val validateState = viewModel.state
    val signUpState = viewModel.signUpState.collectAsState()

    navHostController.GetOnceResult<Government>(keyResult = GOVERNMENT_ROUTE) { government ->
        viewModel.updateGovern(government)
    }

    navHostController.GetOnceResult<CityModel>(keyResult = CITIES_ROUTE) { city ->
        viewModel.updateCity(city)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                navHostController = navHostController,
                title = R.string.sign_up
            )
        }, content = {

            if (signUpState.value.isLoading)
                ShowLottieLoading()

            if (signUpState.value.failureStatus != null) {
                HandleApiError(
                    activity = LocalContext.current.findActivity(),
                    signUpState.value.failureStatus!!
                )
            }

            if (signUpState.value.data != null) {
                AlerterSuccess(message = signUpState.value.msg, position = AlerterPosition.Bottom)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TopSection()
                Spacer(modifier = Modifier.height(15.dp))
                InputSection(validateState, viewModel, navHostController)
                BottomSection(viewModel)
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun TopSection() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.location))
    val progress by animateLottieCompositionAsState(composition, iterations = Int.MAX_VALUE)

    Spacer(modifier = Modifier.height(15.dp))
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .width(180.dp)
            .height(180.dp)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun InputSection(
    state: SignUpFormState = SignUpFormState(),
    viewModel: SignUpViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {

    var showPassword by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = state.govern,
        onValueChange = {
            viewModel.onEvent(SignUpFormEvent.GovernChanged(it))
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
    // Showing error for email
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
            viewModel.onEvent(SignUpFormEvent.CityChanged(it))
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
        value = state.name,
        onValueChange = {
            viewModel.onEvent(SignUpFormEvent.NameChanged(it))
        },
        isError = state.nameError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.first_name)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Name"
            )
        },
    )
    // Showing error for email
    if (state.nameError != null) {
        Text(
            text = state.nameError,
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
            viewModel.onEvent(SignUpFormEvent.PhoneChanged(it))
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
        value = state.password,
        onValueChange = {
            viewModel.onEvent(SignUpFormEvent.PasswordChanged(it))
        },
        isError = state.passwordError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.password)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),

        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_password),
                contentDescription = "Phone number"
            )
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = "Phone number"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
    // Showing error for password
    if (state.passwordError != null) {
        Text(
            text = state.passwordError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }

    OutlinedTextField(
        value = state.address,
        onValueChange = {
            viewModel.onEvent(SignUpFormEvent.AddressChanged(it))
        },
        isError = state.addressError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.address)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.MyLocation,
                contentDescription = "Name"
            )
        },
    )
    // Showing error for email
    if (state.addressError != null) {
        Text(
            text = state.addressError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }

}

@Composable
@Preview(showBackground = true)
fun BottomSection(
    viewModel: SignUpViewModel = viewModel()
) {
    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = {
            viewModel.onEvent(SignUpFormEvent.Submit)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimaryDark)),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}