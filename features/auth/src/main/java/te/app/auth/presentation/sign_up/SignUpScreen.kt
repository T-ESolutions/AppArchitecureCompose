package te.app.auth.presentation.sign_up

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.te.core.utils.HandleApiError
import app.te.core.extension.findActivity
import te.app.auth.presentation.sign_up.events.SignUpFormEvent
import te.app.auth.presentation.sign_up.state.SignUpFormState
import te.app.auth.presentation.sign_up.view_model.SignUpViewModel
import app.te.core.utils.ShowLottieLoading
import app.te.core.custom_views.alert.AlerterPosition
import app.te.core.custom_views.alert.AlerterSuccess
import app.te.core.extension.isBackPressed
import app.te.core.utils.InputError
import app.te.core.utils.mirror
import te.app.auth.R
import te.app.auth.presentation.login.TopSection
import te.app.storage.domain.entity.UserType
import te.app.storage.domain.use_case.UserTypeSingle

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel()
) {
    val signUpFormState = viewModel.state
    val signUpState by viewModel.signUpState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {

            if (signUpState.isLoading)
                ShowLottieLoading()

            if (signUpState.failureStatus != null) {
                HandleApiError(
                    activity = LocalContext.current.findActivity(),
                    signUpState.failureStatus!!
                )
            }

            if (signUpState.data != null) {
                AlerterSuccess(message = signUpState.msg, position = AlerterPosition.Bottom)
            }

            RegisterUi(
                context,
                it, signUpFormState,
                onNameChanged = viewModel::onEvent,
                onPhoneChanged = viewModel::onEvent,
                onIdentityChanged = viewModel::onEvent,
                onPasswordChanged = viewModel::onEvent,
                onGenderChanged = viewModel::onEvent,
                onSignUpClicked = viewModel::onEvent,
            )
        }
    )
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun RegisterUi(
    context: Context = LocalContext.current,
    paddingValues: PaddingValues = PaddingValues(),
    signUpFormState: SignUpFormState = SignUpFormState(),
    onNameChanged: (event: SignUpFormEvent) -> Unit = {},
    onPhoneChanged: (event: SignUpFormEvent) -> Unit = {},
    onIdentityChanged: (event: SignUpFormEvent) -> Unit = {},
    onPasswordChanged: (event: SignUpFormEvent) -> Unit = {},
    onGenderChanged: (event: SignUpFormEvent) -> Unit = {},
    onSignUpClicked: (event: SignUpFormEvent) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
            .imePadding()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
    ) {
        TopSection(headerImage = R.drawable.login_header, headerText = R.string.sign_up)
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(10.dp)
                .mirror()
                .clickable { context.isBackPressed() }
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp, start = 25.dp, end = 25.dp, bottom = 25.dp),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            elevation = CardDefaults.cardElevation(14.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                NameInput(
                    name = signUpFormState.name,
                    nameError = signUpFormState.nameError,
                    onNameChanged
                )
                InputPhoneSection(
                    phone = signUpFormState.phone,
                    phoneError = signUpFormState.phoneError,
                    onPhoneChanged
                )
                if (UserTypeSingle.userType == UserType.CAR_OWNER.userType)
                    InputIdentitySection(
                        signUpFormState.identity,
                        signUpFormState.identityError,
                        onIdentityChanged
                    )
                InputPasswordSection(
                    signUpFormState.password,
                    signUpFormState.passwordError,
                    onPasswordChanged
                )
                InputGender(onGenderChanged)
                ProfileImageSection()
                BottomSection(onSignUpClicked, context = context)
            }
        }

    }
}

@Composable
fun NameInput(
    name: String = "",
    nameError: String? = null,
    onNameChanged: (event: SignUpFormEvent.NameChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = name,
        onValueChange = { onNameChanged.invoke(SignUpFormEvent.NameChanged(it)) },
        isError = nameError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.first_name),
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
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Name"
            )
        },
    )
    // Showing error for Name
    InputError(nameError)

    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
fun InputPhoneSection(
    phone: String = "",
    phoneError: String? = null,
    onPhoneChanged: (event: SignUpFormEvent.PhoneChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = phone,
        onValueChange = { onPhoneChanged.invoke(SignUpFormEvent.PhoneChanged(it)) },
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
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "Phone number"
            )
        },
    )
    // Showing error for email
    InputError(phoneError)
    Spacer(modifier = Modifier.height(10.dp))


}

@Composable
fun InputPasswordSection(
    password: String = "",
    passwordError: String? = null,
    onPasswordChanged: (event: SignUpFormEvent.PasswordChanged) -> Unit = {}
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChanged.invoke(SignUpFormEvent.PasswordChanged(it))
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
    InputError(passwordError)
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun InputIdentitySection(
    identity: String = "",
    identityError: String? = null,
    onIdentityChanged: (event: SignUpFormEvent.IdentityChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = identity,
        onValueChange = { onIdentityChanged.invoke(SignUpFormEvent.IdentityChanged(it)) },
        isError = identityError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.identity_hint),
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
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_identity),
                contentDescription = "ic_identity"
            )
        },
    )
    // Showing error for email
    InputError(identityError)
    Spacer(modifier = Modifier.height(10.dp))


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputGender(
    onGenderChanged: (event: SignUpFormEvent.GenderChanged) -> Unit = {}
) {
    val options = stringArrayResource(id = R.array.gender_list)
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        modifier = Modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { onGenderChanged.invoke(SignUpFormEvent.GenderChanged(it)) },
            label = {
                Text(
                    stringResource(id = R.string.gender), style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight(300)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "Arrow",
                    modifier = Modifier.rotate(if (expanded) 180f else 0f)
                )
            },
        )

        ExposedDropdownMenu(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Composable
fun ProfileImageSection() {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        PickProfileImage()
        if (UserTypeSingle.userType == UserType.CAR_OWNER.userType)
            PickLicenseImage()
    }
}

@Composable
fun PickProfileImage() {
    ElevatedCard(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 12.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
        ) {
            val (userImage, grayLayer, uploadIcon, uploadHint) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.login_header),
                contentDescription = "Profile",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(grayLayer.bottom)
                    }

            )

            Box(
                modifier = Modifier
                    .constrainAs(grayLayer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(140.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = .8f))
            )


            Image(
                painter = painterResource(id = R.drawable.upload),
                contentDescription = "UPLOAd",
                modifier = Modifier.constrainAs(uploadIcon) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(grayLayer.bottom, 4.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.select_profile_image),
                modifier = Modifier
                    .constrainAs(uploadHint) {
                        start.linkTo(parent.start, 4.dp)
                        end.linkTo(parent.end, 4.dp)
                        top.linkTo(grayLayer.bottom)
                        bottom.linkTo(parent.bottom, 4.dp)
                    }
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight(500)
            )
        }
    }

}

@Composable
fun PickLicenseImage() {
    ElevatedCard(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = 12.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
        ) {
            val (userImage, grayLayer, uploadIcon, uploadHint) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_license),
                contentDescription = "Profile",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .constrainAs(userImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(grayLayer.bottom)
                    }

            )

            Box(
                modifier = Modifier
                    .constrainAs(grayLayer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(140.dp)
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = .8f))
            )


            Image(
                painter = painterResource(id = R.drawable.upload),
                contentDescription = "UPLOAd",
                modifier = Modifier.constrainAs(uploadIcon) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(grayLayer.bottom, 8.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.select_license_image),
                modifier = Modifier
                    .constrainAs(uploadHint) {
                        start.linkTo(parent.start, 4.dp)
                        end.linkTo(parent.end, 4.dp)
                        top.linkTo(grayLayer.bottom)
                        bottom.linkTo(parent.bottom, 4.dp)
                    }
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight(500)
            )
        }
    }

}

@Composable
fun BottomSection(
    onSignUpClicked: (event: SignUpFormEvent.Submit) -> Unit = {},
    context: Context,
) {
    Spacer(modifier = Modifier.height(40.dp))

    Button(
        onClick = { onSignUpClicked.invoke(SignUpFormEvent.Submit) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = stringResource(id = R.string.sign_up),
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
                append(stringResource(id = R.string.have_account))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.login))
                }
            },
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.clickable {
                context.isBackPressed()
            }
        )


    }
}