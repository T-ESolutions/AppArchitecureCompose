package te.app.auth.presentation.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.te.core.extension.findActivity
import app.te.core.extension.isBackPressed
import app.te.core.utils.mirror
import te.app.auth.R
import te.app.auth.presentation.otp.otp_view.OtpColors
import te.app.auth.presentation.otp.otp_view.OtpSize
import te.app.auth.presentation.otp.otp_view.OtpView
import te.app.auth.presentation.otp.otp_view.OtpViewStyle
import te.app.auth.presentation.otp.otp_view.OtpViewStyles


@Composable
@Preview(showBackground = true)
fun OtpVerificationScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
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
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight(700),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                ) {
                    append(stringResource(id = R.string.verify_phone))
                }

                append("\n \n")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight(600),
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                ) {
                    append(stringResource(id = R.string.enter_code))
                }
            }, textAlign = TextAlign.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        )
        OtpView(
            modifier = Modifier
                .padding(OtpSpacing)
                .imePadding(),
            isError = false,
            styles = OtpStyle(),
            colors = OtpColors(),
            onAllDigitsFilled = {},
            onPressedDelKey = {},
        )
        Button(
            onClick = { //onSignUpClicked.invoke(SignUpFormEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = stringResource(id = R.string.verify),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.background
            )
        }

    }


}

private val OtpSpacing = 8.dp
private val OtpStyle: @Composable () -> OtpViewStyle = {
    OtpViewStyles.default(
        otpSize = OtpSize.FOUR, textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
    )
}

private val OtpColors: @Composable () -> OtpColors = {
    OtpColors(
        backgroundColor = MaterialTheme.colorScheme.background,
        errorLabelColor = MaterialTheme.colorScheme.error,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        errorCursorColor = MaterialTheme.colorScheme.error,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorIndicatorColor = MaterialTheme.colorScheme.error,
        errorLeadingIconColor = MaterialTheme.colorScheme.error
    )
}