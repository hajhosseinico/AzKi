package ir.hajhosseini.azki.presentation

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Used for navigation component bug in screen rotation and fragment constructor injection
 */
@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {
    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}