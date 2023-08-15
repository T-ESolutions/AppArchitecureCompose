package app.te.hero_cars.presentation.home.ui_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import app.te.hero_cars.R
import app.te.core.utils.LoadAsyncImage
import app.te.hero_cars.presentation.home.ui_state.StolenUiItemState

@Composable
fun ItemSearchScreen(
    data: StolenUiItemState,
    onCallClick: (String) -> Unit,
    onImagePreview: (String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        val (t_serial_number, t_serial_number_value, t_model, t_model_value,
            t_brand, t_brand_value, t_gov, t_gov_value, t_city, t_city_value, t_desc, img, btn_call) = createRefs()

        Text(
            text = stringResource(id = R.string.serial_number),
            modifier = Modifier.constrainAs(t_serial_number) {
                start.linkTo(parent.start, 5.dp)
                top.linkTo(parent.top, 5.dp)
                end.linkTo(t_serial_number_value.start, 5.dp)
                width = Dimension.fillToConstraints
            }, color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = data.searchData.serial,
            modifier = Modifier.constrainAs(t_serial_number_value) {
                end.linkTo(parent.end, 5.dp)
                top.linkTo(parent.top, 5.dp)
            }, color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(id = R.string.model),
            modifier = Modifier.constrainAs(t_model) {
                start.linkTo(parent.start, 5.dp)
                top.linkTo(t_serial_number.bottom, 5.dp)
                end.linkTo(t_model_value.start, 5.dp)
                width = Dimension.fillToConstraints
            }, color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = data.searchData.modell_name,
            modifier = Modifier.constrainAs(t_model_value) {
                end.linkTo(parent.end, 5.dp)
                top.linkTo(t_serial_number.bottom, 5.dp)
            }, color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(id = R.string.brand),
            modifier = Modifier.constrainAs(t_brand) {
                start.linkTo(parent.start, 5.dp)
                top.linkTo(t_model.bottom, 5.dp)
                end.linkTo(t_brand_value.start, 5.dp)
                width = Dimension.fillToConstraints
            }, color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = data.searchData.brand_name,
            modifier = Modifier.constrainAs(t_brand_value) {
                end.linkTo(parent.end, 5.dp)
                top.linkTo(t_model.bottom, 5.dp)
            }, color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        if (data.governVisibility()) {
            Text(
                text = stringResource(id = R.string.govern),
                modifier = Modifier.constrainAs(t_gov) {
                    start.linkTo(parent.start, 5.dp)
                    top.linkTo(t_brand.bottom, 5.dp)
                    end.linkTo(t_gov_value.start, 5.dp)
                    width = Dimension.fillToConstraints
                }, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = data.searchData.gov_name,
                modifier = Modifier.constrainAs(t_gov_value) {
                    end.linkTo(parent.end, 5.dp)
                    top.linkTo(t_brand.bottom, 5.dp)
                }, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (data.cityVisibility()) {
            Text(
                text = stringResource(id = R.string.city),
                modifier = Modifier.constrainAs(t_city) {
                    start.linkTo(parent.start, 5.dp)
                    top.linkTo(t_gov.bottom, 5.dp)
                    end.linkTo(t_city_value.start, 5.dp)
                    width = Dimension.fillToConstraints
                }, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = data.searchData.city_name,
                modifier = Modifier.constrainAs(t_city_value) {
                    end.linkTo(parent.end, 5.dp)
                    top.linkTo(t_gov.bottom, 5.dp)
                }, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = data.searchData.notes,
            modifier = Modifier.constrainAs(t_desc) {
                start.linkTo(parent.start, 5.dp)
                top.linkTo(t_city.bottom, 5.dp)
                end.linkTo(parent.end, 5.dp)
                width = Dimension.fillToConstraints
            }, color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
        if (data.searchData.image.isNotEmpty())
            LoadAsyncImage(
                url = data.searchData.image,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .constrainAs(img) {
                        top.linkTo(t_desc.bottom, 5.dp)
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        width = Dimension.fillToConstraints
                    }
                    .height(150.dp)
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        MaterialTheme.shapes.medium
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable { onImagePreview(data.searchData.image) },
            )
        if (data.callVisibility()) {
            Button(
                onClick = { onCallClick(data.getPhoneNumber()) },
                modifier = Modifier
                    .constrainAs(btn_call) {
                        top.linkTo(img.bottom, 5.dp)
                        start.linkTo(parent.start, 5.dp)
                        end.linkTo(parent.end, 5.dp)
                        bottom.linkTo(parent.bottom, 10.dp)
                        width = Dimension.fillToConstraints
                    },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.phone_call),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }

}