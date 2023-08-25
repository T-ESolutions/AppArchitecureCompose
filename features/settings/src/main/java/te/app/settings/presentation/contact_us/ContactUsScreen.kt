package te.app.settings.presentation.contact_us

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.te.core.utils.ShowLottieLoading
import app.te.core.utils.HandleApiError
import app.te.core.extension.findActivity
import app.te.core.extension.isBackPressed
import app.te.core.utils.InputError
import app.te.core.utils.mirror
import te.app.settings.R

@Composable
fun ContactUsScreen(
    viewModel: ContactUsViewModel = viewModel()
) {
    val contactState = viewModel.contactState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (contactState.value.isLoading)
            ShowLottieLoading()

        if (contactState.value.failureStatus != null) {
            HandleApiError(
                activity = LocalContext.current.findActivity(),
                contactState.value.failureStatus!!
            )
        }
        if (contactState.value.data != null) {

        }
        ContactUsUi(context)
    }
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun ContactUsUi(context: Context = LocalContext.current) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        TopContactSection(context)
        ContactNameInput()
        ContactInputPhoneSection()
        ContactInputMessageSection()
        SubmitSection()
    }
}

@Composable
fun TopContactSection(context: Context = LocalContext.current) {
    Icon(
        imageVector = Icons.Filled.ArrowBackIosNew,
        contentDescription = "Back",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .padding(10.dp)
            .mirror()
            .clickable {
                context.isBackPressed()
            }
    )
    Text(
        text = stringResource(id = R.string.suggestions),
        style = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight(700),
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ContactNameInput(
    name: String = "",
    nameError: String? = null,
//    onNameChanged: (event: SignUpFormEvent.NameChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = name,
        onValueChange = { /*onNameChanged.invoke(SignUpFormEvent.NameChanged(it)) */ },
        isError = nameError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.name),
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
        leadingIcon = {
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
fun ContactInputPhoneSection(
    phone: String = "",
    phoneError: String? = null,
//    onPhoneChanged: (event: SignUpFormEvent.PhoneChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = phone,
        onValueChange = { /*onPhoneChanged.invoke(SignUpFormEvent.PhoneChanged(it)) */ },
        isError = phoneError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.phone),
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
                imageVector = Icons.Outlined.PhoneIphone,
                contentDescription = "Phone number",
                tint = MaterialTheme.colorScheme.outline
            )
        },
    )
    // Showing error for email
    InputError(phoneError)
    Spacer(modifier = Modifier.height(10.dp))


}

@Composable
fun ContactInputMessageSection(
    message: String = "",
    messageError: String? = null,
//    onPhoneChanged: (event: SignUpFormEvent.PhoneChanged) -> Unit = {},
) {
    OutlinedTextField(
        value = message,
        onValueChange = { /*onPhoneChanged.invoke(SignUpFormEvent.PhoneChanged(it)) */ },
        isError = messageError != null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        label = {
            Text(
                text = stringResource(id = R.string.leave_message),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight(300)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                maxLines = 5,
                minLines = 5
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
    )
    // Showing error for email
    InputError(messageError)
    Spacer(modifier = Modifier.height(10.dp))


}

@Composable
fun SubmitSection(
//    onSignUpClicked: (event: SignUpFormEvent.Submit) -> Unit = {},
) {
    Spacer(modifier = Modifier.height(40.dp))

    Button(
        onClick = { /*onSignUpClicked.invoke(SignUpFormEvent.Submit) */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = stringResource(id = app.te.core.R.string.send),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}


