package com.amany.contactbook.adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amany.contactbook.databinding.ContactsListItemBinding;
import com.amany.contactbook.db.DBSqlite;
import com.amany.contactbook.model.ContactModel;
import com.amany.contactbook.ui.ContactProfileActivity;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.MyViewHolder> {

    private final List<ContactModel> contacts;
    private ContactsListItemBinding binding;
    private DBSqlite db;

    public ContactsListAdapter(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ContactsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        db = new DBSqlite(binding.getRoot().getContext());
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ContactModel contact = contacts.get(position);
        binding.tvName.setText(contact.getName());
        binding.tvChar.setText(contact.getName().substring(0, 1));

        binding.layout.setOnClickListener(view -> {
            Intent intent = new Intent(binding.getRoot().getContext(), ContactProfileActivity.class);
            intent.putExtra("contact", (Parcelable) contact);
            Log.d("contact", contact.getEmail());
            binding.getRoot().getContext().startActivity(intent);
        });
        binding.layout.setOnLongClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(binding.getRoot().getContext());

            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                if (db.delete(contact.getToken())) {
                    contacts.remove(position);
                    notifyItemChanged(position);
                    notifyItemRangeChanged(position, contacts.size());
                } else
                    Toast.makeText(binding.getRoot().getContext(), "Error Happened Try Again!", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("NO", (dialog, id) -> {

            });
            builder.setMessage("Do you want to delete this Contact?");
            builder.create();
            builder.show();


            return true;
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ContactsListItemBinding binding;

        public MyViewHolder(@NonNull ContactsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
