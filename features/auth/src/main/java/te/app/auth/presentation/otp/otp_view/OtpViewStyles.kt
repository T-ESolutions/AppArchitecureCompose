package te.app.auth.presentation.otp.otp_view

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import javax.annotation.concurrent.Immutable

object OtpViewStyles {
    fun default(
        textStyle: TextStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = FontSize
        ),
        shape: Shape = RoundedShape,
        otpSize: OtpSize = OtpSize.FOUR
    ): OtpViewStyle {
        return OtpViewStyle(
            textStyle = textStyle,
            shape = shape,
            otpSize = otpSize
        )
    }

    fun rounded(
        textStyle: TextStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = FontSize
        ),
        shape: Shape = RoundedShape,
        otpSize: OtpSize = OtpSize.FOUR
    ): OtpViewStyle {
        return OtpViewStyle(
            textStyle = textStyle,
            shape = shape,
            otpSize = otpSize
        )
    }

    fun circle(
        textStyle: TextStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = FontSize
        ),
        shape: Shape = CircleShape,
        otpSize: OtpSize = OtpSize.FOUR
    ): OtpViewStyle {
        return OtpViewStyle(
            textStyle = textStyle,
            shape = shape,
            otpSize = otpSize
        )
    }

    fun rect(
        textStyle: TextStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = FontSize
        ),
        shape: Shape = RectangleShape,
        otpSize: OtpSize = OtpSize.FOUR
    ): OtpViewStyle {
        return OtpViewStyle(
            textStyle = textStyle,
            shape = shape,
            otpSize = otpSize
        )
    }
}

private val FontSize = 20.sp
private val RoundedShape = RoundedCornerShape(8.dp)

@Immutable
data class OtpViewStyle(
    val textStyle: TextStyle,
    val shape: Shape,
    val otpSize: OtpSize
)