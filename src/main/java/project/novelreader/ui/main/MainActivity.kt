package project.novelreader.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_main.*
import project.novelreader.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        setListeners()
    }

    private fun changeFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, it)
                .commit()
            println("Debug: Changing Fragment...")
        }
    }

//SETUPS
    private fun initialize() {
        changeFragment(null)
    }

    private fun setListeners() {
        bottomNavigationContainer.setOnNavigationItemSelectedListener(navigationListener)
    }

//LISTENERS
    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_home -> selectedFragment = HomeFragment()
            R.id.nav_list -> selectedFragment = NovelListFragment()
            R.id.nav_downloads -> selectedFragment = DownloadsFragment()
        }
        changeFragment(selectedFragment)
        true
    }
}
