# A website and also a frontend app t to gen android app as gift to others. Based on angular, angular-material , spring boot


> To run server backend, `server/quickstart/run-server-backend.bat`
> To run server frontend,
 `server/quickstart/run-server-frontend.bat`

> To run front apk:
  `adb -d install frontend/build/outputs/apk/frontend-debug.apk`
  `adb shell am start -n "com.willbe.giftapp/com.willbe.giftapp.WebViewActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -D`
  