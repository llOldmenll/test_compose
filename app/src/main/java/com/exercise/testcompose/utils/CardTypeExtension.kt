package com.exercise.testcompose.utils

import androidx.annotation.DrawableRes
import com.exercise.testcompose.R
import com.exercise.domain.entity.CardType
import com.exercise.domain.entity.CardType.MasterCard
import com.exercise.domain.entity.CardType.JCB
import com.exercise.domain.entity.CardType.Amex
import com.exercise.domain.entity.CardType.DinersClub
import com.exercise.domain.entity.CardType.Visa
import com.exercise.domain.entity.CardType.Discover
import com.exercise.domain.entity.CardType.Maestro

@DrawableRes
fun CardType.logo(): Int = when (this) {
    MasterCard -> R.drawable.ic_master_card
    JCB -> R.drawable.ic_jcb
    Amex -> R.drawable.ic_amex
    DinersClub -> R.drawable.ic_diners_club
    Visa -> R.drawable.ic_visa
    Discover -> R.drawable.ic_discover
    Maestro -> R.drawable.ic_maestro
    else -> R.drawable.ic_empty
}