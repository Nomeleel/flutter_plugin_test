package com.choonlay.plugin.flutter_plugin_test

import android.annotation.TargetApi
import android.content.pm.PackageManager
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.Intent
import android.app.Activity
import android.content.pm.PackageInfo
import android.os.Build


class FlutterPluginTestPlugin(activity: Activity): MethodCallHandler {
  private var activity: Activity
  private var packageManager: PackageManager

  init {
      this.activity = activity
      packageManager = this.activity.getPackageManager()
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
      "installedPackageNameList" -> result.success(installedPackageNameList())
      else -> result.notImplemented()
    }
  }

  @TargetApi(Build.VERSION_CODES.CUPCAKE)
  private fun callApp(packageName: String) : String {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    if (intent != null) {
      activity.startActivity(intent);
    } else {
      // TODO open in app store
    }
    return "Package Name: " + packageName
  }

  private fun installedPackageNameList() : List<String> {
    var installAppList = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES)
    return installAppList.map<PackageInfo, String>{ it.packageName }
  }

}
