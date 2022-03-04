package com.exercise.testcompose.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.exercise.domain.entity.ThreeDS
import com.exercise.testcompose.presentation.view_model.PaymentViewModel
import com.exercise.testcompose.R
import com.exercise.testcompose.presentation.routing.Screen
import com.exercise.testcompose.presentation.ui.theme.Shapes
import com.exercise.testcompose.presentation.ui.view.CardInputFields
import com.exercise.testcompose.presentation.ui.view.Card
import java.util.*

@ExperimentalAnimationApi
@Composable
fun PaymentScreen(navController: NavController, viewModel: PaymentViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(R.string.payment),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Card(viewModel.cardState)

        CardInputFields(
            viewModel.cardState,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        )

        Button(
            onClick = { viewModel.pay { routeToNextScreen(navController, it) } },
            modifier = Modifier.fillMaxWidth(),
            shape = Shapes.medium
        ) {
            Text(
                text = stringResource(id = R.string.pay).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

    }

    AnimatedVisibility(
        visible = viewModel.cardState.isLoading.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background.copy(alpha = 0.7f))
                .clickable(enabled = true) { }
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}

private fun routeToNextScreen(navController: NavController, threeDS: ThreeDS) {
    if (threeDS.url.isNotEmpty()) navController.navigate(Screen.ThreeDS.createRoute(threeDS.url))
    else navController.navigate(Screen.Status.createRoute(false))
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewPaymentScreen() {
    PaymentScreen(rememberNavController())
}