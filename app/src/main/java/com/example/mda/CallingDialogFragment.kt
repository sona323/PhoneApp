package com.example.mda

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class CallingDialogFragment : DialogFragment() {

    private var phoneNumber: String? = null
    private val handler = Handler()

    companion object {
        private const val ARG_PHONE = "phone_number"

        fun newInstance(phoneNumber: String): CallingDialogFragment {
            val fragment = CallingDialogFragment()
            val args = Bundle()
            args.putString(ARG_PHONE, phoneNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneNumber = arguments?.getString(ARG_PHONE)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calling, container, false)

        val tvPhoneNumber = view.findViewById<TextView>(R.id.tvPhoneNumber)
        val tvCallingStatus = view.findViewById<TextView>(R.id.tvCallingStatus)
        val btnEndCall = view.findViewById<Button>(R.id.btnEndCall)

        tvPhoneNumber.text = phoneNumber
        tvCallingStatus.text = "Calling..."

        btnEndCall.setOnClickListener {
            dismiss()
        }

        // Auto dismiss after 5 seconds (optional)
        handler.postDelayed({ dismiss() }, 5000)

        return view
    }
}
