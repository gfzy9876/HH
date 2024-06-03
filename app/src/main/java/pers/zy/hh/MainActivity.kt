package pers.zy.hh

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amap.api.location.AMapLocationClient
import com.google.gson.Gson
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pers.zy.hh.databinding.ActMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.btnTest.setOnClickListener {
      startLocation()
    }
  }

  @SuppressLint("CheckResult")
  private fun startLocation() {
    RxPermissions(this)
        .requestEachCombined(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        .subscribe {
          if (it.granted) {
            val client = AMapLocationClient(this)
            client.setLocationListener {
              client.stopLocation()
              lifecycleScope.launch(Dispatchers.IO) {
                App.api.sendLocation(Gson().toJson(it))
                withContext(Dispatchers.Main) {
                  Toast.makeText(this@MainActivity, "发送成功", Toast.LENGTH_SHORT).show()
                }
              }
            }
            client.startLocation()
          }
        }
  }

  override fun onResume() {
    super.onResume()
    startLocation()
  }
}