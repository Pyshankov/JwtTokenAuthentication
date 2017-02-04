package com.hairdresser.pyshankov.fragment

import android.support.v4.app.Fragment

/**
 * Created by pyshankov on 1/29/17.
 */

abstract class NamedFragment : Fragment() {

    var name: String? = null
        protected set
}
