package rankhep.com.tripper.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import rankhep.com.tripper.model.User
import com.google.gson.Gson


class SharedPrefManager(private var context: Context) {
    private val pref = context.getSharedPreferences("pref", MODE_PRIVATE)
    private val editor = pref.edit()

    fun isLogin(): Boolean = pref.getBoolean("isLogin", false)

    fun setLoginState(isLogin: Boolean, user: User?) {
        editor.run {
            putBoolean("isLogin", isLogin)
            user?.let {
                putString("user", User.toJson(it))
            }
            apply()
        }
    }

    fun getUserData(): User {
        val json = pref.getString("user", "")
        return Gson().fromJson(json, User::class.java)
    }
}