package com.amany.contactbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amany.contactbook.adapters.ContactsListAdapter;
import com.amany.contactbook.databinding.ActivityContactListBinding;
import com.amany.contactbook.db.DBSqlite;
import com.amany.contactbook.model.ContactModel;

import java.util.ArrayList;
import java.util.Locale;

public class ContactListActivity extends AppCompatActivity {
    private ActivityContactListBinding binding;
    private DBSqlite db = new DBSqlite(this);
    ArrayList<ContactModel> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecycler();
        onKeyListener();
        binding.btnAdd.setOnClickListener(view -> startActivity(new Intent(this, AddContactActivity.class)));

    }

    private void setupRecycler() {
        contacts = db.getAllContacts();
        if (contacts.size() > 0) {
            ContactsListAdapter adapter = new ContactsListAdapter(contacts);
            binding.rvAllContacts.setLayoutManager(new LinearLayoutManager(this));
            binding.rvAllContacts.setAdapter(adapter);
            binding.tvNoThing.setVisibility(View.GONE);
        } else {
            binding.tvNoThing.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecycler();
    }

    private void search(String name) {
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        for (ContactModel contact : contacts) {
            if (contact.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                arrayList.add(contact);
        }
        if (arrayList.isEmpty())
            binding.tvNoThing.setVisibility(View.VISIBLE);
        else binding.tvNoThing.setVisibility(View.GONE);
        binding.rvAllContacts.setAdapter(new ContactsListAdapter(arrayList));
    }

    private void onKeyListener() {

        binding.etSearch.clearFocus();
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}