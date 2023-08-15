package app.te.hero_cars.presentation.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.core.extension.navigateSafe
import app.te.hero_cars.R

@Composable
fun MoreScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: MoreViewModel = viewModel()
) {

    val moreListScreen = viewModel.moreListScreen.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.initMoreListScreen()
        }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LOGO",
            modifier = Modifier
                .width(130.dp)
                .height(130.dp)
                .padding(top = 20.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            items(moreListScreen.value) { item ->
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    val (item_icon, item_title, v_divider) = createRefs()
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "icon",
                        modifier = Modifier.constrainAs(item_icon) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )
                    Text(
                        text = stringResource(id = item.title),
                        modifier = Modifier
                            .constrainAs(item_title) {
                                top.linkTo(parent.top)
                                start.linkTo(item_icon.end, 4.dp)
                                end.linkTo(parent.end, 4.dp)
                                width = Dimension.fillToConstraints
                            }
                            .clickable {
                                navHostController.navigateSafe(item.route)
                            },
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Divider(
                        color = MaterialTheme.colorScheme.outline,
                        thickness = 1.dp,
                        modifier = Modifier
                            .constrainAs(v_divider) {
                                top.linkTo(item_title.bottom, 10.dp)

                            }
                            .fillMaxWidth()
                    )

                }
            }
        }

    }
}