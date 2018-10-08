package rankhep.com.tripper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_calendar.*
import rankhep.com.tripper.AddCompleteActivity
import rankhep.com.tripper.R

class CalenderActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)


        iniView()

    }

    private fun iniView() {
        addContainer.setOnClickListener {
            it.visibility = View.GONE
            addFab.visibility = View.VISIBLE
        }

        addFab.setOnClickListener {
            it.visibility = View.GONE
            addContainer.visibility = View.VISIBLE
        }

        backBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
        cafeBtn.setOnClickListener(this)
        playingBtn.setOnClickListener(this)
        restaurantBtn.setOnClickListener(this)
        touristBtn.setOnClickListener(this)
        shoppingBtn.setOnClickListener(this)
        museumBtn.setOnClickListener(this)

        finishBtn.setOnClickListener {
            startActivity(Intent(this@CalenderActivity, AddCompleteActivity::class.java))
            finish()
        }
    }


    override fun onClick(v: View) {
        when (v) {
            cafeBtn -> {

            }
            playingBtn -> {

            }
            restaurantBtn -> {

            }
            touristBtn -> {

            }
            shoppingBtn -> {

            }
            museumBtn -> {

            }
        }
    }

    override fun onBackPressed() {
        if (addContainer.visibility == View.VISIBLE){

            addContainer.visibility = View.GONE
            addFab.visibility = View.VISIBLE
        }else {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
    }
}