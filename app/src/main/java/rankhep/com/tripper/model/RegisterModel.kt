package rankhep.com.tripper.model

import com.google.gson.Gson
import java.io.Serializable

data class RegisterModel(var device_token: String = "",
                         var email: String = "",
                         var name: String = "",
                         var sex: Int = 0,
                         var password: String) : Serializable {
    companion object {
        fun toJson(user: User): String = Gson().toJson(user)
    }
}