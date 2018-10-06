package rankhep.com.tripper.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_bottom.*
import kotlinx.android.synthetic.main.menu_header.*
import rankhep.com.tripper.fragment.MainFragment
import rankhep.com.tripper.fragment.TrippingFragment
import rankhep.com.tripper.model.User
import rankhep.com.tripper.utils.SharedPrefManager
import android.widget.Toast
import rankhep.com.tripper.R
import rankhep.com.tripper.fragment.AirplaneReservationFragment


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val LOGIN_REQUEST_CODE = 333
    lateinit var dataManager: SharedPrefManager
    var user: User? = null
    lateinit var nowFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataManager = SharedPrefManager(applicationContext)
        initView()

        supportFragmentManager.addOnBackStackChangedListener {
            nowFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        }
    }

    private fun initView() {
        nowFragment = MainFragment.newInstance()
        replaceFragment(nowFragment)
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
            R.id.myPlanBtn -> {
                if (nowFragment !is TrippingFragment)
                    replaceFragment(TrippingFragment.newInstance())
                changeMenuTextStyle(myPlanBtn)
                closeDrawer()
            }
            R.id.homeBtn -> {
                if (nowFragment !is MainFragment)
                    replaceFragment(MainFragment.newInstance())
                changeMenuTextStyle(homeBtn)
                closeDrawer()
            }
            R.id.airplaneReservationBtn -> {
                if (nowFragment !is AirplaneReservationFragment)
                    replaceFragment(AirplaneReservationFragment.newInstance())
                changeMenuTextStyle(airplaneReservationBtn)
                closeDrawer()
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
        if (fragment is MainFragment) {
            val count = fragmentManager.backStackEntryCount
            for (i in 0 until count) {
                fragmentManager.popBackStack()
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit()
        } else {
            fragmentManager.popBackStack()
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment).commit()
        }

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

    private fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun changeMenuTextStyle(view: TextView) {
        homeBtn.setTypeface(null, Typeface.NORMAL)
        myPlanBtn.setTypeface(null, Typeface.NORMAL)
        hotelReservationBtn.setTypeface(null, Typeface.NORMAL)
        airplaneReservationBtn.setTypeface(null, Typeface.NORMAL)

        view.setTypeface(null, Typeface.BOLD)
    }


    private val FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime
        if (nowFragment is MainFragment) {
            if (intervalTime in 0..FINISH_INTERVAL_TIME) super.onBackPressed()
            else {
                backPressedTime = tempTime
                Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onBackPressed()
        }
    }
}
