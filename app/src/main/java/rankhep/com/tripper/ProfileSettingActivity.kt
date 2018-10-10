package rankhep.com.tripper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile_setting.*

class ProfileSettingActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when(v.id){
            R.id.toolbarBackBtn->{
                finish()
                overridePendingTransition(R.anim.right_in, R.anim.right_out)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setting)

        initView()
    }

    private fun initView() {
        toolbarBackBtn.setOnClickListener(this)
        facebookLinkBtn.setOnClickListener(this)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.right_in, R.anim.right_out)
    }
}
