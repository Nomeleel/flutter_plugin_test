package com.choonlay.plugin.flutter_plugin_test_example

import android.os.Bundle
import com.choonlay.plugin.flutter_plugin_test.FlutterPluginTestPlugin

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
    FlutterPluginTestPlugin.registerWith(this);
  }
}
