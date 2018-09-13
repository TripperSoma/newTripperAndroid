package rankhep.com.tripper.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import rankhep.com.tripper.fragment.MainFragment
import rankhep.com.tripper.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MainFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
    }

    public fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)

    }

    public fun closeDrawer() {
        drawer_layout.closeDrawers()
    }
}
