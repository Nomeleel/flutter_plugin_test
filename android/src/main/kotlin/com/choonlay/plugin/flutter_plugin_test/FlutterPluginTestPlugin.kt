package com.choonlay.plugin.flutter_plugin_test

import android.content.pm.PackageManager
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.Intent
import android.app.Activity



class FlutterPluginTestPlugin(activity: Activity): MethodCallHandler {
  private var activity: Activity

  init {
      this.activity = activity
  }

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "flutter_plugin_test")
      channel.setMethodCallHandler(FlutterPluginTestPlugin(registrar.activity()))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when(call.method){
      "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
      "callApp" -> result.success(callApp(call.argument<String>("packageName")!!))
      else -> result.notImplemented()
    }
  }

  private fun callApp(packageName: String) : String {
    val pm = activity.getPackageManager()
    val intent = pm.getLaunchIntentForPackage(packageName)
    if (intent != null) {
      activity.startActivity(intent);
    } else {
      // TODO open in app store
    }
    return "Package Name: " + packageName
  }

}
