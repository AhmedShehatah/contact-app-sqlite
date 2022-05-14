package com.amany.contactbook.utlis;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import java.security.SecureRandom;

public class HelperFunctions {


    public void setBackground(EditText editText, Drawable background) {
        editText.setBackground(background);
    }

    public void setEnable(EditText editText, boolean enable) {
        editText.setEnabled(enable);
    }

    public String getText(EditText editText) {
        return editText.getText().toString();
    }


    public String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }

}
