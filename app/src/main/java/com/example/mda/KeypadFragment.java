package com.example.mda;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class KeypadFragment extends Fragment {

    private EditText numberDisplay;
    private Button btnDial;
    private ImageButton deleteButton;
    private TextView callingStatus;

    private Handler handler = new Handler();
    private boolean isCalling = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_keypad, container, false);

        numberDisplay = view.findViewById(R.id.numberDisplay);
        btnDial = view.findViewById(R.id.btnDial);
        deleteButton = view.findViewById(R.id.deleteButton);
        callingStatus = view.findViewById(R.id.callingStatus); // Must be in your XML layout

        setupButton(view, R.id.btn0, "0");
        setupButton(view, R.id.btn1, "1");
        setupButton(view, R.id.btn2, "2");
        setupButton(view, R.id.btn3, "3");
        setupButton(view, R.id.btn4, "4");
        setupButton(view, R.id.btn5, "5");
        setupButton(view, R.id.btn6, "6");
        setupButton(view, R.id.btn7, "7");
        setupButton(view, R.id.btn8, "8");
        setupButton(view, R.id.btn9, "9");
        setupButton(view, R.id.btnStar, "*");
        setupButton(view, R.id.btnHash, "#");

        deleteButton.setOnClickListener(v -> {
            String currentText = numberDisplay.getText().toString();
            if (!currentText.isEmpty()) {
                numberDisplay.setText(currentText.substring(0, currentText.length() - 1));
            }
            checkDialButtonState();
        });

        deleteButton.setOnLongClickListener(v -> {
            numberDisplay.setText("");
            checkDialButtonState();
            return true;
        });

        btnDial.setOnClickListener(v -> {
            if (!isCalling) {
                startCall();
            } else {
                endCall();
            }
        });

        checkDialButtonState();

        return view;
    }

    private void setupButton(View parent, int buttonId, String value) {
        Button button = parent.findViewById(buttonId);
        button.setOnClickListener(v -> {
            numberDisplay.append(value);
            checkDialButtonState();
        });
    }

    private void checkDialButtonState() {
        btnDial.setEnabled(!numberDisplay.getText().toString().isEmpty());
    }

    private void startCall() {
        String number = numberDisplay.getText().toString().trim();
        if (number.isEmpty()) return;

        isCalling = true;
        callingStatus.setText("Calling " + number + "...");
        callingStatus.setVisibility(View.VISIBLE);
        btnDial.setText("End Call");
        deleteButton.setEnabled(false);

        // Simulate 3 seconds call
        handler.postDelayed(() -> {
            endCall();
        }, 3000);
    }

    private void endCall() {
        isCalling = false;
        callingStatus.setVisibility(View.GONE);
        btnDial.setText("Dial");
        deleteButton.setEnabled(true);
    }
}
