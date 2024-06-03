package pers.zy.hh

import android.app.Application
import com.amap.api.location.AMapLocationClient
import retrofit2.Retrofit

class App : Application() {

  companion object {
    lateinit var INSTANCE: App
    lateinit var api: Api
  }

  override fun onCreate() {
    super.onCreate()
    INSTANCE = this
    val retrofit = Retrofit.Builder()
        .baseUrl("https://gfzyxia.com/")
        .build()
    api = retrofit.create(Api::class.java)
    AMapLocationClient.updatePrivacyShow(this, true, true)
    AMapLocationClient.updatePrivacyAgree(this, true)
  }
}