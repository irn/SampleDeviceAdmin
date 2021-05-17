package uat.medstar.sampledeviceadmin

import android.app.admin.DevicePolicyManager
import android.app.admin.SystemUpdatePolicy
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkAdminPolicies()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun checkAdminPolicies() {
        val devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val receiverComponentName = ComponentName(applicationContext, MedstarDeviceAdminReceiver::class.java)
        val isAdminActive = devicePolicyManager.isAdminActive(receiverComponentName)
        if (!isAdminActive) {
            println("Start settings")
            Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, receiverComponentName)
                startActivityForResult(this, 1000) }
        } else {
            println("Disable Auto upgrade")
            val systemUpdatePolicy = devicePolicyManager.systemUpdatePolicy
            devicePolicyManager.setSystemUpdatePolicy(receiverComponentName, SystemUpdatePolicy.createPostponeInstallPolicy())
        }
    }
}