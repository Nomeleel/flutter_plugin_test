import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginTest {
  static const MethodChannel _channel =
      const MethodChannel('flutter_plugin_test');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> callApp(String packageName) async {
    Map<String, Object> argumentMap = {"packageName": packageName};
    final String result = await _channel.invokeMethod('callApp', argumentMap);
    return result;
  }

}
