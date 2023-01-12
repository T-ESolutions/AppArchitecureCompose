package app.te.architecture.presentation.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.te.architecture.R
import app.te.architecture.presentation.auth.AuthComposeActivity
import app.te.architecture.presentation.auth.nav_graph.LOGIN_ROUTE
import app.te.architecture.presentation.base.extensions.findActivity
import app.te.architecture.presentation.base.extensions.openActivity
import app.te.architecture.presentation.base.extensions.openIntentActivity

@Composable
@Preview(showBackground = true)
fun AccountScreen(
    navHostController: NavHostController = rememberNavController(),
    viewModel: AccountViewModel = viewModel()
) {
    val accountState = viewModel.userData.collectAsState()
    val activity = LocalContext.current.findActivity()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LaunchedEffect(key1 = true) {
            viewModel.getUserFromLocal()
        }
        val (ic_logo, ic_login_logout) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LOGO",
            modifier = Modifier
                .constrainAs(ic_logo) {
                    top.linkTo(parent.top, 60.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .width(130.dp)
                .height(130.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(ic_login_logout) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(ic_logo.bottom, 10.dp)
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = "log"
            )
            Text(
                text = stringResource(id = accountState.value.getLogUser()),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 6.dp)
                    .clickable {
                        accountState.value.checkLogAction(
                            onLoginAction = {
                                activity.openIntentActivity(
                                    AuthComposeActivity::class.java,
                                    LOGIN_ROUTE
                                )
                            }, onLoginOutAction = {

                            }
                        )
                    }
            )
        }


    }
}