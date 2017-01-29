package com.hairdresser.pyshankov;

import android.support.v4.app.Fragment;

import com.hairdresser.pyshankov.fragment.LogInFragment;
import com.hairdresser.pyshankov.fragment.SearchFragment;

/**
 * Created by pyshankov on 1/29/17.
 */

public class FragmentFactory {

    private static LogInFragment logInFragment;

    private static SearchFragment searchFragment;

    public static Fragment getLogInFragment(){
        if(logInFragment == null)
            logInFragment = new LogInFragment();
        return logInFragment;
    }

    public static Fragment getSearchFragment(){
        if(searchFragment == null)
            searchFragment = new SearchFragment();
        return searchFragment;
    }

    public static Fragment getFragment(String name){
        if(name.equals(LogInFragment.NAME))
            return getLogInFragment();
        if(name.equals(SearchFragment.NAME))
            return getSearchFragment();
       return null;
    }

}
