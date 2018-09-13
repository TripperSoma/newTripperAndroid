package rankhep.com.tripper.model

import java.io.Serializable

data class User(var device_token: String = "",
                var email: String = "",
                var name: String = "",
                var sex: Int = 0,
                var user_num: Int = 0) : Serializable