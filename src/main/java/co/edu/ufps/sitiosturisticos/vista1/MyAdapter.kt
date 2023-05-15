package co.edu.ufps.sitiosturisticos.vista1

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import co.edu.ufps.sitiosturisticos.vista.MisSitiosFragment
import co.edu.ufps.sitiosturisticos.vista.PerfilFragment
import co.edu.ufps.sitiosturisticos.vista.SitiosFragment

class MyAdapter(var context: Context,
                fm: FragmentManager,
                val totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                SitiosFragment()
            }
            1 -> {
                MisSitiosFragment()
            }
            2 -> {
                PerfilFragment()
            }
            else -> getItem(position)
        }

    }

}