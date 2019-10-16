/*
package com.example.aski_integrated

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import com.android4dev.pushnotification.base.BaseActivity
import com.google.firebase.iid.FirebaseInstanceId
import java.io.IOException

class FirebasePushNotificationActivity : BaseActivity() {
    private val TAG = "MyFirebaseToken"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_push_notification)
        initView()
    }

    private fun initView() {
        //This method will use for fetching Token
        Thread(Runnable {
            try {
                Log.i(TAG, FirebaseInstanceId.getInstance().getToken(getString(R.string.271583063764), "FCM"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()
    }
}*/
