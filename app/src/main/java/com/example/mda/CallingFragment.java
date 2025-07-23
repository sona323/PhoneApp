package com.example.mda;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CallingFragment extends Fragment {

    private static final String ARG_PHONE_NUMBER = "phone_number";
    private String phoneNumber;
    private Handler handler = new Handler();

    public static CallingFragment newInstance(String number) {
        CallingFragment fragment = new CallingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHONE_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phoneNumber = getArguments().getString(ARG_PHONE_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calling, container, false);

        TextView tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
        TextView tvCallingStatus = view.findViewById(R.id.tvCallingStatus);
        Button btnEndCall = view.findViewById(R.id.btnEndCall);

        tvPhoneNumber.setText(phoneNumber);
        tvCallingStatus.setText("Calling...");

        btnEndCall.setOnClickListener(v -> endCall());

        // Optional: Automatically end call after 5 seconds
        handler.postDelayed(this::endCall, 5000);

        return view;
    }

    private void endCall() {
        // Go back to keypad after call ends
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new KeypadFragment())
                    .commit();
        }
    }
}
