package com.insecureshop

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.insecureshop.databinding.ActivityLoginBinding
import com.insecureshop.util.Prefs
import com.insecureshop.util.Util

class LoginActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityLoginBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        when {
            else -> {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
        }

    }


    fun onLogin(view: View) {
        val username = mBinding.edtUserName.text.toString()
        val password = mBinding.edtPassword.text.toString()

        Log.d("userName", username)
        Log.d("password", password)


        var auth = Util.verifyUserNamePassword(username, password)
        if (auth) {
            Prefs.getInstance(applicationContext).username = username
            Prefs.getInstance(applicationContext).password = password
            Util.saveProductList(this)
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        } else {
            for (info in packageManager.getInstalledPackages(0)) {
                var packageName = info.packageName
                if (packageName.startsWith("com.insecureshopapp")) {
                    try {
                        val packageContext = createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE or Context.CONTEXT_IGNORE_SECURITY)
                        val value: Any = packageContext.classLoader
                            .loadClass("com.insecureshopapp.MainInterface")
                            .getMethod("getInstance", Context::class.java)
                            .invoke(null, this)
                        Log.d("object_value", value.toString())
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                }
            }

            Toast.makeText(applicationContext, "Invalid username and password", Toast.LENGTH_LONG)
                .show()
        }
    }
}
