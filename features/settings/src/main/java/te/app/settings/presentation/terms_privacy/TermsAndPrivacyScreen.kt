package te.app.settings.presentation.terms_privacy

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.te.core.extension.findActivity
import app.te.core.extension.isBackPressed
import app.te.core.utils.HandleApiError
import app.te.core.utils.ShowLottieLoading
import app.te.core.utils.TextHtml
import app.te.core.utils.mirror
import app.te.settings.TermsNav
import te.app.settings.R
import te.app.settings.presentation.settings.SettingsViewModel

@Composable
fun TermsAndPrivacyScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val settingState = viewModel.settingState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.savedStateHandle.get<String>(TermsNav().PAGEARGUMENT)?.let { page ->
            viewModel.pages(page)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        SettingsTop(context, viewModel.title, settingState.value.data)

        if (settingState.value.isLoading)
            ShowLottieLoading()

        if (settingState.value.failureStatus != null) {
            HandleApiError(
                activity = LocalContext.current.findActivity(),
                settingState.value.failureStatus!!
            )
        }
    }

}

@Composable
@Preview(showBackground = true, locale = "ar")
fun SettingsTop(
    context: Context = LocalContext.current,
    title: Int = R.string.terms,
    settingState: String? = null
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (backIcon, titlePage, headerImage, dataColumn) = createRefs()
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .constrainAs(backIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(10.dp)
                .mirror()
                .clickable {
                    context.isBackPressed()
                }
        )
        Text(
            text = stringResource(id = title),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight(700),
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(titlePage) {
                    start.linkTo(parent.start, 10.dp)
                    end.linkTo(parent.end, 10.dp)
                    top.linkTo(backIcon.bottom, 10.dp)
                }
                .fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.about_image),
            contentDescription = "header",
            modifier = Modifier
                .constrainAs(headerImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(titlePage.bottom)
                }
                .size(300.dp)
                .fillMaxWidth(),
            alignment = Alignment.Center
        )

        if (settingState != null) {
            Column(
                modifier = Modifier
                    .constrainAs(dataColumn) {
                        start.linkTo(parent.start, 10.dp)
                        end.linkTo(parent.end, 10.dp)
                        top.linkTo(headerImage.bottom, 10.dp)
                        bottom.linkTo(parent.bottom, 10.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .verticalScroll(rememberScrollState())

            ) {
                TextHtml(
                    text = settingState ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                )
            }
        }
    }

}
