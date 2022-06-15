package id.iglo.common.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections
import id.iglo.common.R
import id.iglo.common.base_response.AppResponse
import id.iglo.common.ext.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationEvent = SingleLiveEvent<NavDirections>()
    val showDialogEvent = SingleLiveEvent<DialogData>()
    val popBackStackEvent = SingleLiveEvent<Any>()
    var parent:BaseViewModel? = null
}