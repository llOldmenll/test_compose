package com.exercise.testcompose.presentation.view_model

import androidx.lifecycle.ViewModel
import com.exercise.data.utils.FAILURE_URL
import com.exercise.data.utils.SUCCESS_URL
import com.exercise.testcompose.entity.RedirectUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThreeDSViewModel @Inject constructor() : ViewModel() {

    fun checkRedirectUrl(url: String?): RedirectUrl = when {
        url?.contains(SUCCESS_URL) == true -> RedirectUrl.SUCCESS
        url?.contains(FAILURE_URL) == true -> RedirectUrl.FAILURE
        else -> RedirectUrl.UNKNOWN
    }
}