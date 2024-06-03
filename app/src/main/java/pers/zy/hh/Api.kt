package pers.zy.hh

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author: zy
 * @date: 2024/6/3
 */
interface Api {

  @FormUrlEncoded
  @POST("api/send_location")
  suspend fun sendLocation(@Field("location") locate: String)

  @GET("api/get_locations")
  suspend fun getLocations(): List<String>
}