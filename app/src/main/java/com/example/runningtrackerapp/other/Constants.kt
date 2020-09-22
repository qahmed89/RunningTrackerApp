package com.example.runningtrackerapp.other

import android.graphics.Color

object Constants {
    const val RUNNING_DATABASE_NAME = "running_db.db"
    const val REQUESt_Code_LOCATION_PERMISSION = 0
    const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
    const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"
    const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE"

    const val NOTIFICATION_CHANNEL_ID = "tracking_Channel"
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_NAME = "Tracking"
    const val ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT"

    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_LOCATION_INTERVAL = 2000L

    const val POLYLINE_COLOR = Color.RED
    const val POLYLINE_WIDTH = 8f
    const val CAMERA_ZOOM = 15f

    const val TIME_INTERVEL = 50L


    const val SHARED_PREFERENCES_NAME = "sharedPref"
    const val KEY_FIRST_TOGGLE = "KEY_FIRST_TOGGLE"
    const val KEY_NAME = "KEY_NAME"
    const val KEY_WEIGHT = "KEY_WEIGHT"


}