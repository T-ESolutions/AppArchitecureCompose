package te.app.settings.presentation.more

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.te.core.extension.findActivity
import app.te.core.utils.rateApp
import app.te.core.utils.shareApp
import app.te.navigation.NavigationCommand
import app.te.settings.RateAppNav
import app.te.settings.ShareAppNav
import kotlinx.coroutines.flow.collectLatest
import te.app.settings.R

@Composable
fun MoreScreen(
    viewModel: MoreViewModel = viewModel()
) {
    val validateState = viewModel.state
    val context = LocalContext.current.findActivity()
    val moreListScreen by viewModel.moreListScreen.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = validateState) {
        validateState.collectLatest {
            if (it == RateAppNav()) rateApp(context)
            if (it == ShareAppNav()) shareApp(context)
        }
    }
    MoreScreenUi(moreListScreen, viewModel::moreNavigation)

}

@Composable
@Preview(showBackground = true, locale = "ar")
fun MoreScreenUi(
    moreListScreen: List<MoreItem> = emptyList(),
    onItemClicked: (NavigationCommand) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopSection()
        BodySection(moreListScreen,onItemClicked)

    }
}

@Composable
fun TopSection() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val (userImage, userName, handIcon) = createRefs()
        Image(
            painter = painterResource(id = app.te.core.R.drawable.tes),
            contentDescription = "LOGO",
            modifier = Modifier
                .constrainAs(userImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 20.dp)

                }
                .width(130.dp)
                .height(130.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.background, CircleShape)
        )
        Text(
            modifier = Modifier
                .constrainAs(userName) {
                    start.linkTo(userImage.start, 8.dp)
                    top.linkTo(userImage.bottom, 8.dp)
                    bottom.linkTo(parent.bottom, 10.dp)

                },
            text = "مرحبا مصطفي", style = TextStyle(
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight(700),
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_hand),
            contentDescription = "",
            modifier = Modifier.constrainAs(handIcon) {
                start.linkTo(userName.end, 6.dp)
                top.linkTo(userImage.bottom, 2.dp)
                bottom.linkTo(parent.bottom, 10.dp)
            }
        )
    }

}

@Composable
fun BodySection(
    moreListScreen: List<MoreItem> = emptyList(),
    onItemClicked: (NavigationCommand) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        moreListScreen.forEach { item ->
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()

                ) {
                    val (itemIcon, itemTitle, vDivider) = createRefs()
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        modifier = Modifier
                            .size(20.dp)
                            .constrainAs(itemIcon) {
                                start.linkTo(parent.start)
                                top.linkTo(itemTitle.top)
                                bottom.linkTo(itemTitle.bottom)
                            }
                    )

                    Text(
                        text = stringResource(id = item.title),
                        modifier = Modifier
                            .constrainAs(itemTitle) {
                                top.linkTo(parent.top)
                                start.linkTo(itemIcon.end, 4.dp)
                                end.linkTo(parent.end, 4.dp)
                                width = Dimension.fillToConstraints
                            }
                            .clickable {
                                onItemClicked.invoke(item.route)
                            },
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Image(
                        painterResource(id = R.drawable.horizental_line),
                        contentDescription = "LINE",
                        modifier = Modifier
                            .constrainAs(vDivider) {
                                top.linkTo(itemTitle.bottom, 10.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                            .fillMaxWidth()
                    )


                }
            }

        }
    }

}