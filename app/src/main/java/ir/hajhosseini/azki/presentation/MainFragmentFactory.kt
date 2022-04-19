package ir.hajhosseini.azki.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ir.hajhosseini.azki.presentation.insurancelist.InsuranceListFragment
import javax.inject.Inject

/**
 * MainFragmentFactory
 */

class MainFragmentFactory
@Inject
constructor() : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return  when(className){
            InsuranceListFragment::class.java.name ->{
                InsuranceListFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}
