package com.gojek.waetherdemo.views

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.gojek.waetherdemo.R
import com.gojek.waetherdemo.di.component.DaggerDataComponent
import com.gojek.waetherdemo.di.module.NetworkModule
import com.gojek.waetherdemo.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), LifecycleOwner {


    protected fun checkPermissionFlag(perm: Array<String>, permissionId: Int): Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(this, it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            ActivityCompat.requestPermissions(this, perm, permissionId)
        }

        return havePermissions
    }


    fun showProgressBar() {

        if (loading.visibility == View.GONE) {

            Utils.animateRotation(this, loading)

        }

    }


    fun hideProgressBar() {
        if (loading.visibility == View.VISIBLE) {
            loading.visibility = View.GONE
        }
    }


    fun showErrorPage() {
        error_parent.visibility = View.VISIBLE
        mainParent.visibility = View.GONE
    }

    fun showMainLayout(){
        error_parent.visibility = View.GONE
        mainParent.visibility = View.VISIBLE
    }


}