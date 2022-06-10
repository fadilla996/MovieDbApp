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

    fun navigate(nav: NavDirections) {
        navigationEvent.postValue(nav)
    }

    fun showDialog(
        title: String,
        message: String,
        posButton: Pair<String, () -> Unit>? = getString(R.string.app_common_error_positive_button_text) to {},
        negButton: Pair<String, () -> Unit> = getString(R.string.app_common_error_negative_button_text) to {}
    ) {
        showDialogEvent.postValue(
            DialogData(
                title, message, posButton, negButton
            )
        )
    }

    fun <T> observeRensponseData(
        response: AppResponse<T>,
        success: ((T) -> Unit)?,
        error: ((Throwable) -> Unit)? = {
            showDialog(
                getString(R.string.app_common_error_dialog_title),
                it.message.orEmpty()
            )
        },
        loading: (() -> Unit)? = {}
    ) {
        when (response) {
            is AppResponse.AppResponseSuccess -> {
                response.data?.let { success?.invoke(it) }
            }
            is AppResponse.AppResponseError -> {
                response.error?.let {
                    error?.invoke(it)
                }
            }
            is AppResponse.AppResponseLoading -> {
                loading?.invoke()
            }
        }
    }

    fun getString(id: Int) =
        getApplication<Application>().getString(id)

    fun popBackStack(){
        popBackStackEvent.postValue(Any())
    }
}