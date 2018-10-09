package rankhep.com.tripper.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_complete.*
import rankhep.com.tripper.R

class AddCompleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_complete)

        finishBtn.setOnClickListener {
            finish()
        }
    }
}
