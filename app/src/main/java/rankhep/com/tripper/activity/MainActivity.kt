package rankhep.com.tripper.activity

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_bottom.*
import kotlinx.android.synthetic.main.menu_header.*
import rankhep.com.tripper.R
import rankhep.com.tripper.fragment.MainFragment
import rankhep.com.tripper.model.User
import rankhep.com.tripper.utils.SharedPrefManager

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val LOGIN_REQUEST_CODE = 333
    lateinit var dataManager: SharedPrefManager
    var user: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataManager = SharedPrefManager(applicationContext)
        initView()
    }

    private fun initView() {
        replaceFragment(MainFragment.newInstance())
        checkUser()
        loginBtn.setOnClickListener(this)
        hotelReservationBtn.setOnClickListener(this)
        airplaneReservationBtn.setOnClickListener(this)
        settingBtn.setOnClickListener(this)
        myPlanBtn.setOnClickListener(this)
        homeBtn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.loginBtn -> {
                val intent = Intent(this@MainActivity, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                }
                startActivityForResult(intent, LOGIN_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            333 -> {
                checkUser()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
    }


    private fun checkUser() {
        if (dataManager.isLogin()) {
            user = dataManager.getUserData()
            headerContainer.visibility = View.VISIBLE
            loginBtn.visibility = View.GONE
            idText.text = user?.email
            nameText.text = user?.name
        } else {
            headerContainer.visibility = View.GONE
            loginBtn.visibility = View.VISIBLE
        }
    }

    fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)

    }

}
