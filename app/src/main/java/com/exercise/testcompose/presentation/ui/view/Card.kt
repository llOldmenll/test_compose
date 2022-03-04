package com.exercise.testcompose.presentation.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.exercise.testcompose.presentation.state.CardState
import com.exercise.testcompose.R
import com.exercise.testcompose.presentation.ui.theme.Shapes
import com.exercise.testcompose.utils.logo
import java.util.*

@ExperimentalAnimationApi
@Composable
fun Card(cardState: CardState) {

    val cardNumber = cardState.cardNumber.value
    val cardHolderName = cardState.cardHolderName.value
    val expiryDate = cardState.expiryDate.value
    val cardType = cardState.cardType.value

    val length = if (cardNumber.length > 16) 16 else cardNumber.length
    val initial = remember { "*****************" }.replaceRange(0..length, cardNumber.take(16))

    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = Shapes.medium,
            elevation = 16.dp,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ConstraintLayout(
                    modifier = Modifier
                        .background(cardGradient())
                        .padding(24.dp)
                        .fillMaxSize()

                ) {
                    val (cardTypeImg, cardNameField, cardNameLabel, numberField, expiryDateField, expiryLabel) = createRefs()

                    /* Card type image */
                    Image(
                        painter = painterResource(cardType.logo()),
                        contentDescription = "logo",
                        modifier = Modifier.constrainAs(cardTypeImg) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    /* Card Number */
                    Text(
                        text = initial.chunked(4).joinToString(" "),
                        style = MaterialTheme.typography.h5,
                        maxLines = 1,
                        modifier = Modifier
                            .animateContentSize(spring())
                            .constrainAs(numberField) {
                                linkTo(
                                    start = parent.start,
                                    end = parent.end,
                                    bias = 0.0f
                                )
                                linkTo(
                                    top = parent.top,
                                    bottom = parent.bottom
                                )
                            }
                    )

                    /* Card Holder Name */
                    Text(
                        text = stringResource(R.string.card_holder).uppercase(Locale.getDefault()),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.constrainAs(cardNameLabel) {
                            start.linkTo(parent.start)
                            bottom.linkTo(cardNameField.top)
                        }
                    )
                    Text(
                        text = cardHolderName,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .constrainAs(cardNameField) {
                                linkTo(
                                    start = parent.start,
                                    end = expiryDateField.start
                                )
                                bottom.linkTo(parent.bottom)
                                width = Dimension.fillToConstraints
                            }
                    )

                    /* Expiry Date */
                    Text(
                        text = stringResource(R.string.expiry_date).uppercase(Locale.getDefault()),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.constrainAs(expiryLabel) {
                            end.linkTo(parent.end)
                            bottom.linkTo(expiryDateField.top)
                        }
                    )
                    Text(
                        text = expiryDate.take(4).chunked(2).joinToString(" / "),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .animateContentSize(TweenSpec(300))
                            .constrainAs(expiryDateField) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
            }
        }
    }
}

@Composable
private fun cardGradient() = Brush.linearGradient(
    colors = listOf(
        MaterialTheme.colors.secondary,
        MaterialTheme.colors.primary
    ),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)

@SuppressLint("UnrememberedMutableState")
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewPaymentCard() {
    Card(
        CardState(
            cardNumber = mutableStateOf("***************"),
            cardHolderName = mutableStateOf("John Doe"),
            expiryDate = mutableStateOf("0426")
        )
    )
}