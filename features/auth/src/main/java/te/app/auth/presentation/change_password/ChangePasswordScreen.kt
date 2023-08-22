package te.app.auth.presentation.change_password

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
import app.te.core.extension.isBackPressed
import app.te.core.utils.InputError
import app.te.core.utils.mirror
import te.app.auth.R

@Composable
fun ChangePasswordScreen(
    viewModel: LogInViewModel = viewModel()
) {
    val validateState = viewModel.state
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    val activity = LocalContext.current.findActivity()
    val context = LocalContext.current

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
            ChangePasswordUi(
                paddingValues,
                validateState,
                onPhoneChanged = viewModel::onEvent,
                onPasswordChanged = viewModel::onEvent,
                onLoginSubmittedChanged = viewModel::onEvent,
                context = context
            )

        }
    )
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun ChangePasswordUi(
    paddingValues: PaddingValues = PaddingValues(6.dp),
    loginFormState: LoginFormState = LoginFormState(),
    onPhoneChanged: (event: LoginFormEvent) -> Unit = {},
    onPasswordChanged: (event: LoginFormEvent.PasswordChanged) -> Unit = {},
    onLoginSubmittedChanged: (event: LoginFormEvent.Login) -> Unit = {},
    context: Context = LocalContext.current,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(10.dp)
                .mirror()
                .clickable { context.isBackPressed() }
        )
        Spacer(modifier = Modifier.height(15.dp))
        TopScreen()
        ElevatedCard(
            modifier = Modifier.padding(20.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                InputPasswordSection(
                    loginFormState.phone,
                    loginFormState.phoneError,
                    onPhoneChanged
                )
                InputPasswordConfirmedSection(
                    loginFormState.password,
                    loginFormState.passwordError,
                    onPasswordChanged
                )
                LoginButtonSection(
                    onLoginClicked = onLoginSubmittedChanged
                )
            }
        }

    }
}

@Composable
fun TopScreen() {
    Text(
        text = stringResource(id = R.string.enter_new_password),
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight(600),
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    )
}

@Composable
fun InputPasswordSection(
    password: String = "",
    passwordError: String? = null,
    onPasswordChanged: (event: LoginFormEvent.PhoneChanged) -> Unit = {},
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChanged.invoke(LoginFormEvent.PhoneChanged(it))
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
                    contentDescription = "password"
                )
            }
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
    // Showing error for email
    InputError(passwordError)
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun InputPasswordConfirmedSection(
    passwordConfirm: String = "",
    passwordError: String? = null,
    onPasswordChanged: (event: LoginFormEvent.PasswordChanged) -> Unit = {}
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = passwordConfirm,
        onValueChange = {
            onPasswordChanged.invoke(LoginFormEvent.PasswordChanged(it))
        },
        isError = passwordError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.new_password),
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
    InputError(passwordError)

}

@Composable
fun LoginButtonSection(
    onLoginClicked: (event: LoginFormEvent.Login) -> Unit = {},
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
            text = stringResource(id = R.string.confirm),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}