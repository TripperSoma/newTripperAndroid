package rankhep.com.tripper.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_bottom.*
import kotlinx.android.synthetic.main.menu_header.*
import rankhep.com.tripper.R
import rankhep.com.tripper.fragment.*
import rankhep.com.tripper.model.User
import rankhep.com.tripper.util.CustomApplication
import rankhep.com.tripper.util.SharedPrefManager


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var dataManager: SharedPrefManager
    private var user: User? = null
    private lateinit var nowFragment: Fragment
    private lateinit var hotelReservationFragment: HotelReservationFragment
    private lateinit var airplaneReservationFragment: AirplaneReservationFragment
    private lateinit var mainFragment: MainFragment
    private lateinit var trippingFragment: TrippingFragment
    private lateinit var settingFragment: SettingFragment
    private lateinit var customApplication: CustomApplication
    private val FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customApplication = application as CustomApplication
        dataManager = SharedPrefManager(applicationContext)

        mainFragment = MainFragment.newInstance()
        hotelReservationFragment = HotelReservationFragment.newInstance()
        airplaneReservationFragment = AirplaneReservationFragment.newInstance()
        trippingFragment = TrippingFragment.newInstance()
        settingFragment = SettingFragment.newInstance()

        initView()

        supportFragmentManager.addOnBackStackChangedListener {
            nowFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            changeMenuTextStyle(nowFragment)
        }

    }

    private fun initView() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment).commit()
        nowFragment = mainFragment
        registerBtn.setOnClickListener(this)
        hotelReservationBtn.setOnClickListener(this)
        airplaneReservationBtn.setOnClickListener(this)
        settingBtn.setOnClickListener(this)
        myPlanBtn.setOnClickListener(this)
        homeBtn.setOnClickListener(this)
        logoutBtn.setOnClickListener(this)
        checkUser()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.logoutBtn -> {
                dataManager.setLoginState(false, null)
                checkUser()
            }
            R.id.registerBtn -> {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                }
                startActivityForResult(intent, customApplication.LOGIN_REQUEST_CODE)
            }
            R.id.myPlanBtn -> {
                if (dataManager.isLogin())
                    replaceFragment(trippingFragment)
                else
                    Toast.makeText(applicationContext, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
            R.id.homeBtn -> {
                replaceFragment(mainFragment)
            }
            R.id.airplaneReservationBtn -> {
                replaceFragment(airplaneReservationFragment)
            }
            R.id.hotelReservationBtn -> {
                replaceFragment(hotelReservationFragment)
            }
            R.id.settingBtn -> {
                replaceFragment(settingFragment)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            333 -> {
                checkUser()
            }
            900 -> {
                replaceFragment(trippingFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (nowFragment != fragment) {
            val fragmentManager = supportFragmentManager
            if (fragment is MainFragment) {
                val count = fragmentManager.backStackEntryCount
                for (i in 0 until count) {
                    fragmentManager.popBackStack()
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit()
            } else {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragment).commit()
            }
        }
        closeDrawer()
    }


    private fun checkUser() {
        if (dataManager.isLogin()) {
            user = dataManager.getUserData()
            headerContainer.visibility = View.VISIBLE
            idText.text = user?.email
            nameText.text = user?.name

            registerBtn.visibility = View.GONE
            logoutBtn.visibility = View.VISIBLE
        } else {
            headerContainer.visibility = View.GONE
            registerBtn.visibility = View.VISIBLE
            logoutBtn.visibility = View.GONE
        }
    }

    fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    private fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun changeMenuTextStyle(nowFragment: Fragment) {
        homeBtn.setTypeface(null, Typeface.NORMAL)
        myPlanBtn.setTypeface(null, Typeface.NORMAL)
        hotelReservationBtn.setTypeface(null, Typeface.NORMAL)
        airplaneReservationBtn.setTypeface(null, Typeface.NORMAL)

        when (nowFragment) {
            is AirplaneReservationFragment -> airplaneReservationBtn.setTypeface(null, Typeface.BOLD)
            is TrippingFragment -> myPlanBtn.setTypeface(null, Typeface.BOLD)
            is MainFragment -> homeBtn.setTypeface(null, Typeface.BOLD)
        }
    }


    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer()
        } else if (nowFragment is MainFragment) {
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
