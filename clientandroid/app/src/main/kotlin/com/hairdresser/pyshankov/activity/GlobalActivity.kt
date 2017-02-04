package com.hairdresser.pyshankov.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hairdresser.pyshankov.FragmentFactory
import com.hairdresser.pyshankov.fragment.LogInFragment
import com.hairdresser.pyshankov.fragment.ProfileFragment
import com.hairdresser.pyshankov.fragment.SearchFragment
import com.hairdresser.pyshankov.hairdresser.R
import com.hairdresser.pyshankov.service.TokenService
import com.hairdresser.pyshankov.service.ApiService

class GlobalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global)

        initializeDrawer()
        tokenService = TokenService(applicationContext)
        apiService = ApiService(tokenService!!)

        if (tokenService!!.token == "") {
            replaceFragment(FragmentFactory.getFragment(LogInFragment.NAME))
        } else {
            replaceFragment(FragmentFactory.getFragment(SearchFragment.NAME))
        }

    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.global, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_profile) {
            replaceFragment(FragmentFactory.getFragment(ProfileFragment.NAME))
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            tokenService!!.addToken("");
            replaceFragment(FragmentFactory.getFragment(LogInFragment.NAME))
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initializeDrawer() {
        drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

     fun replaceFragment(fragment: Fragment?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, "HELLO")
        fragmentTransaction.commit()
    }

    companion object {

        private var drawer: DrawerLayout? = null

         var apiService: ApiService? = null

        var tokenService: TokenService? = null


        fun disableDrawer() {
            if (drawer != null) {
                drawer!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        fun enableDrawer() {
            if (drawer != null) {
                drawer!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }

}
