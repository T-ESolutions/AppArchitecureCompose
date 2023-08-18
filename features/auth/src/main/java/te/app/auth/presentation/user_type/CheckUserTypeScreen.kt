package te.app.auth.presentation.user_type

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import te.app.auth.R

@Composable
fun UserTypeScreen(
    viewModel: CheckUserTypeViewModel = viewModel()
) {
    UserTypeUi(
        onNextClicked =
        viewModel::openLogin
    )
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun UserTypeUi(
    onNextClicked: (userType: Boolean) -> Unit = {}
) {
    var typeChecked by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (textTitle, carOwner, user, continueButton) = createRefs()
        Text(
            text = stringResource(id = R.string.select_user),
            modifier = Modifier
                .constrainAs(textTitle) {
                    start.linkTo(parent.start, 10.dp)
                    top.linkTo(parent.top, 10.dp)
                }
                .padding(top = 40.dp),
            color = MaterialTheme.colorScheme.inversePrimary,
            fontWeight = FontWeight(600),
        )
        ElevatedCard(
            modifier = Modifier
                .constrainAs(carOwner) {
                    start.linkTo(parent.start, 10.dp)
                    end.linkTo(user.start, 4.dp)
                    top.linkTo(textTitle.bottom, 10.dp)
                    width = Dimension.fillToConstraints
                }
                .clickable { typeChecked = true },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .fillMaxWidth(),
            ) {
                val (radio, carIcon, text) = createRefs()
                RadioButton(
                    selected = typeChecked,
                    onClick = { typeChecked = true },
                    modifier = Modifier
                        .semantics { contentDescription = "Client" }
                        .constrainAs(radio) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.user_car),
                    contentDescription = "user_car",
                    modifier = Modifier.constrainAs(carIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(radio.bottom, 4.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                )
                Text(
                    text = stringResource(id = R.string.need_car),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colorScheme.inversePrimary
                    ),
                    modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                        top.linkTo(carIcon.bottom, 15.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
        ElevatedCard(
            modifier = Modifier
                .constrainAs(user) {
                    start.linkTo(carOwner.end, 4.dp)
                    end.linkTo(parent.end, 10.dp)
                    top.linkTo(textTitle.bottom, 10.dp)
                    width = Dimension.fillToConstraints
                }
                .clickable { typeChecked = false },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .fillMaxWidth(),
            ) {
                val (radio2, owner_icon, text2) = createRefs()
                RadioButton(
                    selected = !typeChecked,
                    onClick = { typeChecked = false },
                    modifier = Modifier
                        .semantics { contentDescription = "Car Owner" }
                        .constrainAs(radio2) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary,
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.car_owner),
                    contentDescription = "user_car",
                    modifier = Modifier.constrainAs(owner_icon) {
                        start.linkTo(parent.start)
                        top.linkTo(radio2.bottom, 4.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                )
                Text(
                    text = stringResource(id = R.string.car_owner),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colorScheme.inversePrimary
                    ),
                    modifier = Modifier.constrainAs(text2) {
                        start.linkTo(parent.start)
                        top.linkTo(owner_icon.bottom, 15.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
        ElevatedButton(
            onClick = {
                onNextClicked.invoke(typeChecked)
            },
            modifier = Modifier.constrainAs(continueButton) {
                bottom.linkTo(parent.bottom, 80.dp)
                start.linkTo(parent.start, 40.dp)
                end.linkTo(parent.end, 40.dp)
                width = Dimension.fillToConstraints
            },
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background,
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.next), style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight(600)
                )
            )
        }
    }

}
