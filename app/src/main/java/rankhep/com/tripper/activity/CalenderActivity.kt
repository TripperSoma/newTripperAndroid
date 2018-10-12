package rankhep.com.tripper.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_calendar.*
import rankhep.com.tripper.R

class CalenderActivity : AppCompatActivity(), View.OnClickListener {
    val PLACE_SEARCH_REQUEST_CODE = 111
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
        nightPlaceBtn.setOnClickListener(this)
        playingBtn.setOnClickListener(this)
        restaurantBtn.setOnClickListener(this)
        touristBtn.setOnClickListener(this)
        shoppingBtn.setOnClickListener(this)
        parkBtn.setOnClickListener(this)

        finishBtn.setOnClickListener {
            startActivity(Intent(this@CalenderActivity, AddCompleteActivity::class.java))
            finish()
        }
    }


    override fun onClick(v: View) {
        when (v) {
            nightPlaceBtn -> {
                startSearchActivity(7)
            }
            playingBtn -> {
                startSearchActivity(3)
            }
            restaurantBtn -> {
                startSearchActivity(2)
            }
            touristBtn -> {
                startSearchActivity(1)
            }
            shoppingBtn -> {
                startSearchActivity(4)
            }
            parkBtn -> {
                startSearchActivity(6)
            }
        }
    }

    override fun onBackPressed() {
        if (addContainer.visibility == View.VISIBLE) {

            addContainer.visibility = View.GONE
            addFab.visibility = View.VISIBLE
        } else {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
    }

    private fun startSearchActivity(category: Int){
        val intent = Intent(this@CalenderActivity, PlaceSearchActivity::class.java)
        intent.putExtra("version", category)
        startActivityForResult(intent, PLACE_SEARCH_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PLACE_SEARCH_REQUEST_CODE->{
                if (resultCode == Activity.RESULT_OK){

                }else{

                }
            }
        }
    }
}