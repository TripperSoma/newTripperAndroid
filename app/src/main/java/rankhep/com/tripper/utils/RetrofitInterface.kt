package rankhep.com.dhlwn.utils


import okhttp3.RequestBody
import rankhep.com.tripper.model.MainReviewListData
import rankhep.com.tripper.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by choi on 2017. 7. 15..
 */

interface RetrofitInterface {

    @POST("user/create")
    fun registerUser(@Body body: RequestBody): Call<User>
//    @Header("Content-Type") contentType: String = "application/json",
//    @Header("Accept") Accept: String = "*/*"
//    {
//        "device_token": "string",
//        "email": "string",
//        "name": "string",
//        "password": "string",
//        "sex": 0
//    }

    @POST("user/login/")
    fun loginUser(@Body body: RequestBody): Call<User>

    @GET("/review/loadMainReviewByPaging/{page}/{version}")
    fun getMainReviewData(@Path("page") page: Int, @Path("version") version: Int): Call<List<MainReviewListData>>

//    {
//        "user_num": 23,
//        "email": "string",
//        "name": "string",
//        "sex": 0,
//        "device_token": "string"
//    }
}
