package rankhep.com.tripper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
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
        registerBtn.setOnClickListener(this)
        hotelReservationBtn.setOnClickListener(this)
        airplaneReservationBtn.setOnClickListener(this)
        settingBtn.setOnClickListener(this)
        myPlanBtn.setOnClickListener(this)
        homeBtn.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registerBtn -> {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java).apply {
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
            registerBtn.visibility = View.GONE
            idText.text = user?.email
            nameText.text = user?.name
        } else {
            headerContainer.visibility = View.GONE
            registerBtn.visibility = View.VISIBLE
        }
    }

    fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)

    }

}
