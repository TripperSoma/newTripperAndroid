package rankhep.com.tripper.util


import okhttp3.RequestBody
import rankhep.com.tripper.model.*
import retrofit2.Call
import retrofit2.http.*


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
    fun getMainReviewData(@Path("page") page: Int, @Path("version") version: Int): Call<List<MainReviewListModel>>


    @GET("/schedule/SearchingByCategory/{version}/{seqnum}/{daynum}/{page}")
    fun searchPlaceByCategory(@Path("version") version: Int,
                              @Path("seqnum") seqnum: Int,
                              @Path("daynum") dayNum: Int,
                              @Path("page") page: Int = 1)
            : Call<List<PlaceSearchModel>>


    @GET("/place/detail/{num}/{page}")
    fun getPlaceDetailInfo(@Path("num") placeNum: Int, @Path("page") page: Int = 0): Call<PlaceDetailInfo>

    @POST("/schedule/inputPurpose")
    fun sendTaste(@Body body: RequestBody): Call<PlanModel>

    @POST("/schedule/add")
    fun addSchedule(@Body body: RequestBody): Call<PlanModel>

    @GET("/schedule/load/{userid}")
    fun getScheduleList(@Path("userid") userid: String): Call<List<PlanModel>>

    @GET("/review/load/{reviewnum}")
    fun getReviewDetail(@Path("reviewnum") reviewNum: Int = 1): Call<Review>

    @PUT("/schedule/update")
    fun uploadSchedule(@Body body: RequestBody): Call<PlanModel>


}
