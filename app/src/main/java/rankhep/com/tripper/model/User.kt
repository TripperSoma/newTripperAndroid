package rankhep.com.tripper.model

import com.google.gson.Gson
import java.io.Serializable

data class User(var device_token: String = "",
                var email: String = "",
                var name: String = "",
                var sex: Int = 0,
                var user_num: Int = 0) : Serializable {
    companion object {
        fun toJson(user: User): String = Gson().toJson(user)
    }
}