package ir.hajhosseini.azki.util

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import javax.inject.Inject


/**
 * Checking if device is connected into internet of not
 */
class InternetStatus
@Inject
constructor(private val context: Context) {
    fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}