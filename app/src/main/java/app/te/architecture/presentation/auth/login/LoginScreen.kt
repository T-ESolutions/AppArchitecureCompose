package app.te.architecture.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.architecture.R
import app.te.architecture.presentation.auth.login.events.LoginFormEvent
import app.te.architecture.presentation.auth.login.state.LoginFormState
import app.te.architecture.presentation.auth.login.view_model.LogInViewModel
import app.te.architecture.presentation.auth.nav_graph.AuthScreens
import app.te.architecture.presentation.base.extensions.navigateSafe
import app.te.architecture.presentation.base.ShowLottieLoading
import app.te.architecture.presentation.base.extensions.HandleApiError
import app.te.architecture.presentation.base.extensions.findActivity
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun LoginScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: LogInViewModel = viewModel()
) {
    val validateState = viewModel.state
    val loginState = viewModel.loginState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.login),
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }, content = {

            if (loginState.value.isLoading)
                ShowLottieLoading()

            if (loginState.value.failureStatus != null) {
                HandleApiError(
                    activity = LocalContext.current.findActivity(),
                    loginState.value.failureStatus!!
                )
            }

            if (loginState.value.data != null) {
            }
            // open Home activity


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
                InputSection(validateState, viewModel)
                BottomSection(navHostController, viewModel)
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

    Text(
        text = stringResource(id = R.string.welcome_to_stolen_phone),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
    Text(
        text = stringResource(id = R.string.find_your_phone),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelMedium,
        color = colorResource(id = R.color.colordark)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun InputSection(
    state: LoginFormState = LoginFormState(),
    viewModel: LogInViewModel = viewModel(),
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = state.phone,
        onValueChange = {
            viewModel.onEvent(LoginFormEvent.PhoneChanged(it))
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
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "Phone number"
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
            viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
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
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
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

}

@Composable
@Preview(showBackground = true)
fun BottomSection(
    navHostController: NavHostController = rememberNavController(),
    viewModel: LogInViewModel = viewModel()
) {
    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = { viewModel.onEvent(LoginFormEvent.Submit) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.create_an_account),
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(id = R.color.medium_color),
            modifier = Modifier.clickable {
                navHostController.navigateSafe(AuthScreens.SignUpScreen.route)
            }
        )

        Text(
            text = stringResource(id = R.string.new_account),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.black),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                navHostController.navigateSafe(AuthScreens.SignUpScreen.route)
            }
        )

    }
}