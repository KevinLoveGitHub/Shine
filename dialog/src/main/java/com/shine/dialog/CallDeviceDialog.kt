package com.shine.dialog

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.shine.dialog.databinding.DialogCallDeviceBinding

/**
 * @author Kevin
 * @data 2018/9/6
 */
class CallDeviceDialog : DialogFragment() {

    private lateinit var binding: DialogCallDeviceBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_call_device, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val window = dialog.window
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(R.drawable.call_device_bg)
        // 指定宽高
        window?.setLayout(500,300)
    }

    companion object {
       private const val TAG = "CallDeviceDialog"
    }
}