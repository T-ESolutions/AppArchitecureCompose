package te.app.auth.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.auth.AuthenticationDirections
import te.app.auth.presentation.login.events.LoginFormEvent
import te.app.auth.presentation.login.state.LoginFormState
import te.app.auth.presentation.login.view_model.LogInViewModel
import app.te.core.extension.navigateSafe
import app.te.core.utils.ShowLottieLoading
import app.te.core.utils.CenterAlignedTopAppBarCustom
import app.te.core.utils.HandleApiError
import app.te.core.extension.findActivity
import te.app.auth.R

@Composable
fun LoginScreen(
    viewModel: LogInViewModel = viewModel()
) {
    val validateState = viewModel.state
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    val activity = LocalContext.current.findActivity()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                title = R.string.login
            ) {
                activity.finish()
            }
        }, content = { paddingValues ->
            if (loginState.isLoading)
                ShowLottieLoading()

            if (loginState.failureStatus != null) {
                HandleApiError(
                    activity = LocalContext.current.findActivity(),
                    loginState.failureStatus!!
                )
            }

            if (loginState.data != null) {
                activity.finish()
            }
            LogInView(
                paddingValues, validateState,
                onPhoneChanged = viewModel::onEvent,
                onPasswordChanged = viewModel::onEvent,
                onLoginSubmittedChanged = viewModel::onEvent
            )

        }
    )
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun LogInView(
    paddingValues: PaddingValues = PaddingValues(6.dp),
    validateState: LoginFormState = LoginFormState(),
    onPhoneChanged: (event: LoginFormEvent) -> Unit = {},
    onPasswordChanged: (event: LoginFormEvent.PasswordChanged) -> Unit = {},
    onLoginSubmittedChanged: (event: LoginFormEvent.Submit) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .imePadding()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopSection()
        Spacer(modifier = Modifier.height(15.dp))
        InputPhoneSection(validateState.phone, validateState.phoneError, onPhoneChanged)
        InputPasswordSection(validateState.password, validateState.passwordError, onPasswordChanged)
        ForgetPasswordSection()
        SocialButtons()
        //        LoginButtonSection()
    }
}

@Composable
fun TopSection() {

    Spacer(modifier = Modifier.height(15.dp))

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
        color = MaterialTheme.colorScheme.primary
    )

}

@Composable
fun InputPhoneSection(
    state: String = "",
    phoneError: String? = null,
    onPhoneChanged: (event: LoginFormEvent.PhoneChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = state,
        onValueChange = { onPhoneChanged.invoke(LoginFormEvent.PhoneChanged(it)) },
        isError = phoneError != null,
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
    if (phoneError != null) {
        Text(
            text = phoneError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))


}

@Composable
fun InputPasswordSection(
    password: String = "",
    passwordError: String? = null,
    onPasswordChanged: (event: LoginFormEvent.PasswordChanged) -> Unit = {}
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChanged.invoke(LoginFormEvent.PasswordChanged(it))
        },
        isError = passwordError != null,
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
    if (passwordError != null) {
        Text(
            text = passwordError,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        )
    }

}

@Composable
fun ForgetPasswordSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.forget_password_login),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 11.dp, start = 15.dp, end = 15.dp)
        )
    }

}

@Composable
fun SocialButtons() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = stringResource(id = R.string.login_facebook),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }

    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSecondary),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = stringResource(id = R.string.login_facebook),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }

}

@Composable
fun LoginButtonSection(
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
        shape = MaterialTheme.shapes.small
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
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navHostController.navigateSafe(AuthenticationDirections.SignUpScreen().destination)
            }
        )

        Text(
            text = stringResource(id = R.string.new_account),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                navHostController.navigateSafe(AuthenticationDirections.SignUpScreen().destination)
            }
        )

    }
}