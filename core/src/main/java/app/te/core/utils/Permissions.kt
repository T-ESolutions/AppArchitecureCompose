package app.te.core.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.te.core.R
const val MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS: Int = 200

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun checkStorage13Permissions(activity: Activity): Boolean {
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_MEDIA_IMAGES
        )
        != PackageManager.PERMISSION_GRANTED
    ) {
        Log.e("checkStoragePermissions", "checkStoragePermissions: ")
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        ) {
            Log.e("checkStoragePermissions", "shouldShowRequestPermissionRationale: ")
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

            //to simplify, call requestPermissions again
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                1
            )
        } else {
            Log.e("checkStoragePermissions", "else: ")
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                1
            )
        }
        return false
    } else {
        // permission granted
        return true
    }
}

fun checkStoragePermissions(activity: Activity): Boolean {
    if (ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        != PackageManager.PERMISSION_GRANTED
    ) {
        Log.e("checkStoragePermissions", "checkStoragePermissions: ")
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            Log.e("checkStoragePermissions", "shouldShowRequestPermissionRationale: ")
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

            //to simplify, call requestPermissions again
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            Log.e("checkStoragePermissions", "else: ")
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
        return false
    } else {
        return true
    }
}

fun checkNotificationsPermissions(activity: Activity, operation: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //to simplify, call requestPermissions again
                Toast.makeText(
                    activity,
                    activity.getString(R.string.permission_dialog_content1),
                    Toast.LENGTH_LONG
                ).show()

                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
                )
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS
                )
            }
        } else {
            // permission granted
            operation()
        }
    }
}
