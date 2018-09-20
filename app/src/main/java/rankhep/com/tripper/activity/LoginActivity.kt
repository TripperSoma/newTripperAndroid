package rankhep.com.tripper.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.model.User
import rankhep.com.tripper.utils.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerBtn.setOnClickListener {
            checkBlank(loginEmailEditText.text.toString(), loginPwdEditText.text.toString())
        }


    }

    private fun checkBlank(email: String, pwd: String) {
        when {
            email == "" -> Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            pwd == "" -> Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            else -> sendRequest(email, pwd)
        }

    }

    private fun sendRequest(email: String, pwd: String) {
        val paramObject = JSONObject()
        paramObject.put("email", email)
        paramObject.put("password", pwd)
        NetworkHelper.networkInstance
                .loginUser(RequestBody.create(MediaType.parse("application/json"), paramObject.toString()))
                .enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Toast.makeText(applicationContext, "${t?.message}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        if (response?.code() == 200) {
                            SharedPrefManager(applicationContext).setLoginState(true, response.body())
                            Toast.makeText(applicationContext,"로그인 성공", Toast.LENGTH_SHORT).show()
                            setResult(Activity.RESULT_OK)
                            finish()
                        } else {
                            Toast.makeText(applicationContext,"로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }
}
