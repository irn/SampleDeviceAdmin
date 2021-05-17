package uat.medstar.sampledeviceadmin

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent

class MedstarDeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        println("------------- ${intent.extras}")
    }
}