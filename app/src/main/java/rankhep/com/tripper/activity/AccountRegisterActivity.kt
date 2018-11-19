package rankhep.com.tripper.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_account_register.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import rankhep.com.dhlwn.utils.NetworkHelper
import rankhep.com.tripper.R
import rankhep.com.tripper.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_register)

        accountRegisterFinishBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.accountRegisterFinishBtn -> {
                checkBlank()
            }
        }
    }

    private fun checkBlank() {
        when {
            nameEditText.text.toString() == "" ||
                    emailEditText.text.toString() == "" ||
                    pwdEditText.text.toString() == "" ||
                    pwdCheckEditText.text.toString() == "" -> {
                Toast.makeText(applicationContext, "빈 칸을 빠짐없이 채워주세요.", Toast.LENGTH_SHORT).show()
            }
            pwdCheckEditText.text.toString() != pwdEditText.text.toString() ->
                Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            else -> makeAccount()
        }

    }

    private fun makeAccount() {
        val paramObject = JSONObject()
        paramObject.put("email", emailEditText.text.toString())
        paramObject.put("password", pwdEditText.text.toString())
        paramObject.put("name", nameEditText.text.toString())
        paramObject.put("device_token", "string")
        paramObject.put("sex", if (man.isChecked) 0 else 1)

        NetworkHelper.networkInstance.registerUser(RequestBody.create(MediaType.parse("application/json"), paramObject.toString()))
                .enqueue(object : Callback<User> {
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Log.e("sd", "" + response.code() + "" + response.message())
                        when (response.code()) {
                            400 -> Toast.makeText(applicationContext, "이메일 형식을 확인해주세요", Toast.LENGTH_SHORT).show()
                            403 -> Toast.makeText(applicationContext, "권한이 없습니다.", Toast.LENGTH_SHORT).show()
                            409 -> Toast.makeText(applicationContext, "중복된 메시지입니다.", Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(applicationContext, "서버 에러입니다.", Toast.LENGTH_SHORT).show()
                            201 -> {
                                Toast.makeText(applicationContext, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    }

                })
    }

}