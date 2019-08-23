package com.example.aski_integrated

import android.hardware.Camera
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var fragobjrepair: RepairingFragment? = null



    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.letak_fragment, fragment)
        fragmentTransaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_andon -> {
                replaceFragment(AndonFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_repairing -> {
                replaceFragment(RepairingFragment())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(AndonFragment())
        fragobjrepair = RepairingFragment()
    }

    }


