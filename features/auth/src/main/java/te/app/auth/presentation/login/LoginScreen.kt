package te.app.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import te.app.auth.presentation.login.events.LoginFormEvent
import te.app.auth.presentation.login.state.LoginFormState
import te.app.auth.presentation.login.view_model.LogInViewModel
import app.te.core.utils.ShowLottieLoading
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
        content = { paddingValues ->
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
                paddingValues,
                validateState,
                onPhoneChanged = viewModel::onEvent,
                onPasswordChanged = viewModel::onEvent,
                onLoginSubmittedChanged = viewModel::onEvent,
                onSignUpSubmittedChanged = viewModel::onEvent,
                onResetClicked = viewModel::onEvent,
                onFaceBookClicked = viewModel::onEvent,
                onGoogleClicked = viewModel::onEvent
            )

        }
    )
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun LogInView(
    paddingValues: PaddingValues = PaddingValues(6.dp),
    loginFormState: LoginFormState = LoginFormState(),
    onPhoneChanged: (event: LoginFormEvent) -> Unit = {},
    onPasswordChanged: (event: LoginFormEvent.PasswordChanged) -> Unit = {},
    onResetClicked: (event: LoginFormEvent.ResetPassword) -> Unit = {},
    onFaceBookClicked: (event: LoginFormEvent.LoginFaceBook) -> Unit = {},
    onGoogleClicked: (event: LoginFormEvent.LoginGoogle) -> Unit = {},
    onLoginSubmittedChanged: (event: LoginFormEvent.Login) -> Unit = {},
    onSignUpSubmittedChanged: (event: LoginFormEvent.SignUp) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopSection()
        Spacer(modifier = Modifier.height(15.dp))
        InputPhoneSection(loginFormState.phone, loginFormState.phoneError, onPhoneChanged)
        InputPasswordSection(
            loginFormState.password,
            loginFormState.passwordError,
            onPasswordChanged
        )
        ForgetPasswordSection(onResetClicked)
        if (loginFormState.isSocialAvailable)
            SocialButtons(onFaceBookClicked, onGoogleClicked)
        LoginButtonSection(onLoginSubmittedChanged, onSignUpSubmittedChanged)
    }
}

@Composable
fun TopSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_header),
            contentDescription = "Login Header",
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
        )

        Text(
            text = stringResource(id = R.string.login),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight(900)
        )

    }

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
                text = stringResource(id = R.string.phone_hint),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight(300)
                ),
                modifier = Modifier
                    .fillMaxWidth()
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
            style = TextStyle(
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight(200)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 2.dp)
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
                text = stringResource(id = R.string.password),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight(300)
                ),
                modifier = Modifier
                    .fillMaxWidth()
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
            textAlign = TextAlign.Start,
            style = TextStyle(
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight(200)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 15.dp, top = 2.dp)
        )
    }

}

@Composable
fun ForgetPasswordSection(
    onResetClicked: (event: LoginFormEvent.ResetPassword) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.forget_password_login),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(top = 11.dp, start = 15.dp, end = 15.dp)
                .clickable { onResetClicked }
        )
    }

}

@Composable
fun SocialButtons(
    onFaceBookClicked: (event: LoginFormEvent.LoginFaceBook) -> Unit = {},
    onGoogleClicked: (event: LoginFormEvent.LoginGoogle) -> Unit = {}
) {

    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_facebook),
            contentDescription = "logo",
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
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
            .padding(start = 15.dp, end = 15.dp, top = 5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        shape = MaterialTheme.shapes.small
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "logo",
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = stringResource(id = R.string.login_google),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }

}

@Composable
fun LoginButtonSection(
    onLoginClicked: (event: LoginFormEvent.Login) -> Unit = {},
    onSignUpClicked: (event: LoginFormEvent.SignUp) -> Unit = {},
) {
    Spacer(modifier = Modifier.height(80.dp))

    Button(
        onClick = { onLoginClicked.invoke(LoginFormEvent.Login) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
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
            text = buildAnnotatedString {
                append(stringResource(id = R.string.create_an_account))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.sign_up))
                }
            },
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable {
                onSignUpClicked.invoke(LoginFormEvent.SignUp)
//                navHostController.navigateSafe(AuthenticationDirections.SignUpScreen().destination)
            }
        )


    }
}