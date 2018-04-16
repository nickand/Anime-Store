package test.android.testmerlin.interfaces

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar

import test.android.testmerlin.BaseFragment

interface OnClickActivityListener {

    fun setTitleToolbar(title: String)
    fun navigateTo(fragment: BaseFragment)
    fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean)
    fun openWebView(activity: Activity, url: String)
}