package com.exercise.testcompose.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.exercise.testcompose.R

@Composable
fun StatusScreen(isSuccess: Boolean, navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()

    ) {

        val (image, title, closeBtn) = createRefs()
        createVerticalChain(image, title, chainStyle = ChainStyle.Packed)

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.constrainAs(closeBtn) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "closeBtn",
                tint = MaterialTheme.colors.surface
            )
        }

        Image(
            painter = painterResource(
                if (isSuccess) R.drawable.ic_success
                else R.drawable.ic_failure
            ),
            contentDescription = "image",
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(title.top)
                    verticalChainWeight = 0f
                }
                .padding(16.dp)
        )

        Text(
            text = stringResource(
                if (isSuccess) R.string.success_msg
                else R.string.failure_msg
            ),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(image.bottom)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}