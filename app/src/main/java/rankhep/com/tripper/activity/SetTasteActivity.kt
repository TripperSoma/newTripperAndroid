package rankhep.com.tripper.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_set_taste.*
import rankhep.com.tripper.R
import rankhep.com.tripper.model.TasteSendModel

class SetTasteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_taste)
        backBtn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }

        nextFab.setOnClickListener {
            val tasteSendModel = TasteSendModel(intent.getIntExtra("date", 3),
                    cultureProgressbar.getTaste(),
                    entertainmentProgressbar.getTaste(),
                    foodProgressbar.getTaste(),
                    0,
                    shoppingProgressbar.getTaste(),
                    touristProgressbar.getTaste(),
                    "string",
                    withChild.getIsChecked(),
                    withOld.getIsChecked())
            val intent = Intent(this@SetTasteActivity, CalenderActivity::class.java)
            intent.putExtra("taste", tasteSendModel)
            startActivity(intent)
            overridePendingTransition(R.anim.left_in, R.anim.left_out)
            finish()
        }

    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.right_in, R.anim.right_out)
    }
}