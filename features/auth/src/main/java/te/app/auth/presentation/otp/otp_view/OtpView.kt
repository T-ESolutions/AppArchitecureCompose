package te.app.auth.presentation.otp.otp_view

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpView(
    modifier: Modifier,
    colors: OtpColors,
    styles: OtpViewStyle = OtpViewStyles.rounded(),
    isSecret: Boolean = false,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    onAllDigitsFilled: (String) -> Unit,
    onPressedDelKey: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val animateError = remember { Animatable(initialValue = 0.0F) }
    LaunchedEffect(key1 = isError) {
        if (isError) {
            animateError.animateTo(
                targetValue = 0.dp.value,
                animationSpec = ShakeAnimation,
            )
        }
    }
    val textFieldPins = getClearFields(otpSize = styles.otpSize)

    BoxWithConstraints(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 0.dp,
                Alignment.CenterHorizontally
            ),
            modifier = Modifier.otpFocusManager(
                focusManager = focusManager,
                onPressedDelKey = onPressedDelKey
            ),
        ) {
            textFieldPins.forEachIndexed { index, textField ->
                OtpDigit(
                    textInput = textFieldPins[index].value,
                    isSecret = isSecret,
                    isError = isError,
                    isEnabled = isEnabled,
                    colors = colors,
                    styles = styles,
                    position = index,
                    size = textFieldPins.size,
                    modifier = modifier
                        .offset(x = animateError.value.dp)
                        .vibrateFeedback(isError = isError)
                        .weight(1f),
                    keyboardType = keyboardType,
                    visualTransformation = visualTransformation,
                    onTextChange = { clearPressed, textChanged ->
                        if (clearPressed) {
                            textField.value = textChanged
                        } else {
                            if (textChanged.isNotEmpty()) {
                                textField.value = textChanged
                                when (keyboardType) {
                                    KeyboardType.Text, KeyboardType.Ascii,
                                    KeyboardType.NumberPassword, KeyboardType.Uri -> {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    }

                                    else -> Unit
                                }
                            }
                        }
                        allFieldsIsFilled(fieldList = textFieldPins, otpSize = styles.otpSize) {
                            onAllDigitsFilled(it)
                        }
                    },
                    onPasteEvent = {
                        pasteText(token = it, fieldList = textFieldPins)
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OtpDigit(
    textInput: String,
    isSecret: Boolean,
    isError: Boolean,
    isEnabled: Boolean,
    position: Int,
    size: Int,
    modifier: Modifier,
    styles: OtpViewStyle,
    keyboardType: KeyboardType = KeyboardType.Number,
    visualTransformation: VisualTransformation,
    colors: OtpColors,
    onTextChange: (Boolean, String) -> Unit,
    onPasteEvent: (String) -> Unit
) {
    var text by remember {
        mutableStateOf(textInput)
    }
    var clearPressed by remember {
        mutableStateOf(false)
    }

    val fieldSemantic = Modifier.semantics {
        contentDescription = "$position / $size." +
                if (isSecret && text.isNotEmpty()) "Marcador" else ""
    }

    val interactionSource = remember { MutableInteractionSource() }

    val textStyle = styles.textStyle.copy(
        textAlign = TextAlign.Center,
        color = if (isError) colors.errorLabelColor else colors.cursorColor,
        fontSize = if (isSecret) 14.sp else styles.textStyle.fontSize
    )
    BasicTextField(
        value = textInput,
        onValueChange = {
            if (it.length >= 4) {
                onPasteEvent(it)
            } else {
                text = it.takeLast(1)
            }
            onTextChange(clearPressed, text)
        },
        enabled = isEnabled,
        textStyle = textStyle,
        modifier = modifier
            .then(fieldSemantic)
            .onKeyEvent {
                clearPressed = it.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL
                if (it.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DEL) {
                    text = ""
                    onTextChange(clearPressed, text)
                    clearPressed = false
                }
                false
            }
            .size(56.dp)
            .background(
                color = colors.backgroundColor,
                shape = styles.shape
            )
            .borderFocusActive(
                shape = styles.shape,
                color = if (isError) colors.backgroundColor else colors.backgroundColor
            ),
        visualTransformation = if (isSecret) OtpViewVisualTransformation() else VisualTransformation.None,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        readOnly = false,
        cursorBrush = SolidColor(if (isError) colors.errorCursorColor else colors.backgroundColor),
    ) { innerTextField ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier,
        ) {
            TextFieldDefaults.DecorationBox(
                value = text,
                visualTransformation = if (isSecret) visualTransformation else VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                isError = isError,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(0.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colors.backgroundColor,
                    unfocusedContainerColor = colors.backgroundColor,
                    errorContainerColor = colors.backgroundColor,
                    errorLabelColor = colors.errorLabelColor,
                    errorTrailingIconColor = colors.errorTrailingIconColor,
                    errorCursorColor = colors.errorCursorColor,
                    errorIndicatorColor = colors.errorIndicatorColor,
                    errorLeadingIconColor = colors.errorLeadingIconColor
                ),
            )
        }
    }
}

val ShakeAnimation = keyframes {
    0.0F at 0
    20.0F at 80
    -20.0F at 120
    10.0F at 160
    -10.0F at 200
    5.0F at 240
    0.0F at 280
}

@Composable
internal fun getClearFields(otpSize: OtpSize) = remember {
    when (otpSize) {
        OtpSize.SIX -> listOf(
            mutableStateOf(""),
            mutableStateOf(""),
            mutableStateOf(""),
            mutableStateOf(""),
            mutableStateOf(""),
            mutableStateOf(""),
        )

        OtpSize.FOUR -> listOf(
            mutableStateOf(""),
            mutableStateOf(""),
            mutableStateOf(""),
            mutableStateOf(""),
        )
    }
}

internal fun allFieldsIsFilled(
    fieldList: List<MutableState<String>>,
    otpSize: OtpSize,
    onComplete: (String) -> Unit,
) {
    val filled = fieldList.filter {
        it.value.isNotEmpty()
    }
    if (filled.size == otpSize.size) {
        var text = ""
        fieldList.forEach {
            text += it.value
        }
        onComplete(text)
    }
}

internal fun pasteText(token: String?, fieldList: List<MutableState<String>>) {
    token ?: return
    for (i in token.indices) {
        fieldList[i].value = token[i].toString()
    }
}

fun Modifier.otpFocusManager(
    focusManager: FocusManager,
    onPressedDelKey: () -> Unit
) = composed {
    onKeyEvent {
        when {
            it.nativeKeyEvent.keyCode != android.view.KeyEvent.KEYCODE_DEL -> {
                focusManager.moveFocus(FocusDirection.Right)
                return@onKeyEvent false
            }

            else -> {
                onPressedDelKey()
                focusManager.moveFocus(FocusDirection.Left)
                return@onKeyEvent false
            }
        }
    }
}

fun Modifier.borderFocusActive(
    shape: Shape,
    color: Color
) = composed {
    val isFocusable = remember {
        mutableStateOf(false)
    }
    Modifier
        .onFocusEvent {
            isFocusable.value = it.isFocused
        }
        .border(
            width = if (isFocusable.value) 2.dp else 1.dp,
            color = color,
            shape = shape,
        )
}

fun Modifier.vibrateFeedback(
    duration: Long = 300L,
    isError: Boolean
) = composed {
    val context = LocalContext.current
    if (isError) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).vibrate(
                CombinedVibration.createParallel(
                    VibrationEffect.createOneShot(
                        duration,
                        VibrationEffect.DEFAULT_AMPLITUDE,
                    ),
                ),
            )
        } else {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    it.vibrate(
                        VibrationEffect.createOneShot(
                            duration,
                            VibrationEffect.DEFAULT_AMPLITUDE,
                        ),
                    )
                } else {
                    it.vibrate(duration)
                }
            }
        }
    }
    this
}

enum class OtpSize(val size: Int) {
    SIX(6),
    FOUR(4),
}