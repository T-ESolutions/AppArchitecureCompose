package app.te.architecture.presentation.auth.intro

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.te.architecture.R
import app.te.architecture.presentation.auth.intro.view_model.TutorialViewModel
import app.te.architecture.domain.intro.entity.AppTutorialModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.lifecycle.viewmodel.compose.viewModel
import app.te.architecture.presentation.auth.nav_graph.AuthScreens
import app.te.architecture.presentation.base.extensions.navigateSafe
import app.te.architecture.presentation.base.ShowLottieLoading
import app.te.architecture.presentation.base.extensions.HandleApiError
import app.te.architecture.presentation.base.extensions.LoadAsyncImage
import app.te.architecture.presentation.base.extensions.findActivity

@Composable
fun OnBoarding(
    navHostController: NavHostController,
    viewModel: TutorialViewModel = viewModel()
) {

    val introData = viewModel.appTutorialState.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getIntro()
    }
    if (introData.value.isLoading)
        ShowLottieLoading()

    if (introData.value.failureStatus != null) {
        HandleApiError(
            activity = LocalContext.current.findActivity(),
            introData.value.failureStatus!!
        )
    }
    if (introData.value.data != null)
        IntroContent(appTutorialModel = introData.value.data!!, navHostController)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroContent(appTutorialModel: List<AppTutorialModel>, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        val pageState = rememberPagerState()

        HorizontalPager(
            count = appTutorialModel.size,
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()

        ) { page ->

            OnBoardingItem(items = appTutorialModel[page])
        }
        BottomSection(size = appTutorialModel.size, index = pageState.currentPage, onButtonClick = {
            navHostController.navigateSafe(
                destination = AuthScreens.LoginScreen.route,
                popUpTo = AuthScreens.OnBoardingScreen.route
            )
        })
    }
}

@Composable
@Preview(showBackground = true)
fun TopSection(onSkipClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Skip Button
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.skip),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomSection(size: Int = 3, index: Int = 0, onButtonClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Indicators
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Indicators(size, index)
            Button(
                onClick = {
                    onButtonClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorPrimaryDark)),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.open_app).uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Icon(
                    Icons.Outlined.KeyboardArrowRight,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        }


    }
}

@Composable
fun Indicators(size: Int = 3, index: Int = 1) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Indicator(isSelected: Boolean = false) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
            )
    ) {

    }
}

@Composable
fun OnBoardingItem(items: AppTutorialModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        LoadAsyncImage(
            url = items.image,
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
                .width(200.dp)
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = items.title,
            style = MaterialTheme.typography.headlineMedium,
            // fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = items.body,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
        )
    }
}