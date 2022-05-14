package com.amany.contactbook.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amany.contactbook.R;
import com.amany.contactbook.databinding.ActivityContactProfileBinding;
import com.amany.contactbook.db.DBSqlite;
import com.amany.contactbook.model.ContactModel;
import com.amany.contactbook.utlis.HelperFunctions;

public class ContactProfileActivity extends AppCompatActivity {
    private ActivityContactProfileBinding binding;
    private boolean enabled = false;
    private Drawable normalBackground;
    private HelperFunctions helper = new HelperFunctions();
    private ContactModel contact;
    private DBSqlite db = new DBSqlite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.etName.clearFocus();
        Bundle extras = getIntent().getExtras();
        contact = extras.getParcelable("contact");
        binding.etName.setText(contact.getName());
        binding.etEmail.setText(contact.getEmail());
        binding.etNotes.setText(contact.getNotes());
        binding.etPhone.setText(contact.getPhone());
        normalBackground = binding.etEmail.getBackground();
        helper.setBackground(binding.etName, null);
        helper.setBackground(binding.etEmail, null);
        helper.setBackground(binding.etNotes, null);
        helper.setBackground(binding.etPhone, null);
        binding.btnEdit.setOnClickListener(view -> {
            setEditTextState();
            if (!enabled) updateContact();
        });

    }


    private void setEditTextState() {
        enabled = !enabled;
        if (enabled) {
            binding.btnEdit.setImageResource(R.drawable.check);
            helper.setBackground(binding.etName, normalBackground);
            helper.setBackground(binding.etEmail, normalBackground);
            helper.setBackground(binding.etNotes, normalBackground);
            helper.setBackground(binding.etPhone, normalBackground);
            helper.setEnable(binding.etName, true);
            helper.setEnable(binding.etEmail, true);
            helper.setEnable(binding.etNotes, true);
            helper.setEnable(binding.etPhone, true);

        } else {
            binding.btnEdit.setImageResource(R.drawable.edit);
            helper.setBackground(binding.etName, null);
            helper.setBackground(binding.etEmail, null);
            helper.setBackground(binding.etNotes, null);
            helper.setBackground(binding.etPhone, null);
            helper.setEnable(binding.etName, false);
            helper.setEnable(binding.etEmail, false);
            helper.setEnable(binding.etNotes, false);
            helper.setEnable(binding.etPhone, false);
        }
    }

    private void updateContact() {
        String name = helper.getText(binding.etName);
        String email = helper.getText(binding.etEmail);
        String phone = helper.getText(binding.etPhone);
        String notes = helper.getText(binding.etNotes);
        ContactModel updatedContact = new ContactModel(contact.getToken(), name, email, notes, phone);
        boolean result = db.updateContact(updatedContact);
        if (result)
            Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Error Try Again!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}