package com.hairdresser.pyshankov

import android.support.v4.app.Fragment

import com.hairdresser.pyshankov.fragment.LogInFragment
import com.hairdresser.pyshankov.fragment.ProfileFragment
import com.hairdresser.pyshankov.fragment.SearchFragment

/**
 * Created by pyshankov on 1/29/17.
 */

object FragmentFactory {

    private var logInFragment: LogInFragment? = null

    private var searchFragment: SearchFragment? = null

    private var profileFragment: ProfileFragment? =null

    fun getLogInFragment(): LogInFragment {
        if (logInFragment == null)
            logInFragment = LogInFragment()
        return logInFragment as LogInFragment
    }

    fun getProfileFragment(): ProfileFragment{
        if(profileFragment == null)
            profileFragment = ProfileFragment();
        return profileFragment as ProfileFragment;
    }

    fun getSearchFragment(): SearchFragment {
        if (searchFragment == null)
            searchFragment = SearchFragment()
        return searchFragment as SearchFragment
    }

    fun getFragment(name: String): Fragment? {
        if (name == LogInFragment.NAME)
            return getLogInFragment()
        if (name == SearchFragment.NAME)
            return getSearchFragment()
        if (name == ProfileFragment.NAME)
            return getProfileFragment()
        return null
    }

}
