package com.reactnativecommunity.rnpermissions;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNPermissionsModule extends ReactContextBaseJavaModule {

    public static final String MODULE_NAME = "RNPermissions"

    private final ReactApplicationContext reactContext;

    public RNPermissionsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

private WritableMap internalCheckNotifications() {
    final ReactApplicationContext reactContext = getReactApplicationContext();
    final boolean enabled = NotificationManagerCompat.from(reactContext).areNotificationsEnabled();
    final WritableMap map = Arguments.createMap();
    final WritableMap settings = Arguments.createMap();

    if (enabled) {
      map.putString("status", "granted");
    } else {
      map.putString("status", "denied");
    }

    map.putMap("settings", settings);
    return map;
  }

  @ReactMethod
  public void checkNotifications(final Promise promise) {
    promise.resolve(internalCheckNotifications());
  }

  @ReactMethod
  public void requestNotifications(ReadableArray options, final Promise promise) {
    promise.resolve(internalCheckNotifications());
  }}
