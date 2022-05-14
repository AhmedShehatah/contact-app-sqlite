package com.amany.contactbook.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amany.contactbook.databinding.ActivityAddContactBinding;
import com.amany.contactbook.db.DBSqlite;
import com.amany.contactbook.model.ContactModel;
import com.amany.contactbook.utlis.HelperFunctions;

public class AddContactActivity extends AppCompatActivity {
    private DBSqlite db = new DBSqlite(this);
    private ActivityAddContactBinding binding;
    private HelperFunctions helper = new HelperFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(view -> {
            if (validateInputs()) {
                String name = helper.getText(binding.etName);
                String email = helper.getText(binding.etEmail);
                String phone = helper.getText(binding.etPhone);
                String notes = helper.getText(binding.etNotes);
                ContactModel contact = new ContactModel(helper.generateToken(), name, email, notes, phone);
                boolean result = db.insertContact(contact);
                if (result) Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "Error happened try again!", Toast.LENGTH_SHORT).show();
                finish();

            } else Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(helper.getText(binding.etName)))
            return false;
        if (!Patterns.PHONE.matcher(helper.getText(binding.etPhone)).matches()) return false;
        if (!Patterns.EMAIL_ADDRESS.matcher(helper.getText(binding.etEmail)).matches())
            return false;
        return !TextUtils.isEmpty(helper.getText(binding.etNotes));
    }


}