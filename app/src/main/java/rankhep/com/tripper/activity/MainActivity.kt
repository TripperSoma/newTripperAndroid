package rankhep.com.tripper.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import rankhep.com.tripper.fragment.MainFragment
import rankhep.com.tripper.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "로그인"->{
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivityForResult(intent, 333)

            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MainFragment.newInstance())

        navigation_view.setNavigationItemSelectedListener(this)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
    }

    fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)

    }

    fun closeDrawer() {
        drawer_layout.closeDrawers()
    }


}
