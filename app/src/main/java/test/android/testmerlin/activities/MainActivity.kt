package test.android.testmerlin.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.activity_main.*

import test.android.testmerlin.BaseActivity
import test.android.testmerlin.BaseFragment
import test.android.testmerlin.R
import test.android.testmerlin.fragments.AboutMeFragment
import test.android.testmerlin.fragments.AppsFragment
import test.android.testmerlin.interfaces.OnClickActivityListener
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : BaseActivity(), OnClickActivityListener {

    private var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        navigateToAppsFragment()

        checkNetworkConnection()
    }

    private fun navigateToAppsFragment() {
        var fragment: Fragment? = null
        fragment = AppsFragment.newInstance()
        navigateTo((fragment as BaseFragment?)!!)
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)

        setTitleToolbar(getString(R.string.app_name))
        setSupportActionBar(containerToolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            containerToolbar.visibility = View.GONE
            navigateTo(AboutMeFragment())
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setTitleToolbar(title: String) {
        toolbarTitle.text = title
    }

    override fun navigateTo(fragment: BaseFragment) {
        navigateTo(fragment, true)
    }

    override fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean) {
        val manager = supportFragmentManager

        if (!addToBackStack) {
            manager.popBackStackImmediate()
        }

        val fragmentTransaction = manager.beginTransaction()

        if (mFragment == null) {
            fragmentTransaction.add(R.id.fragment_container, fragment).commit()

        } else {

            fragmentTransaction.setCustomAnimations(
                    R.anim.enter_from_right, R.anim.exit_to_left,
                    R.anim.enter_from_left, R.anim.exit_to_right)
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }

        mFragment = fragment
    }

    override fun openWebView(activity: Activity, url: String) {
        FinestWebView.Builder(activity).show(url)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        containerToolbar.visibility = View.VISIBLE
    }

    companion object {

        private val CLASS_TAG = MainActivity::class.java.simpleName
    }
}
