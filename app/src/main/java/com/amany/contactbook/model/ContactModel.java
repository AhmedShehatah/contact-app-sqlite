package com.amany.contactbook.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactModel implements Parcelable {

    protected ContactModel(Parcel in) {
        token = in.readString();
        name = in.readString();
        email = in.readString();
        notes = in.readString();
        phone = in.readString();
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(token);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(notes);
        parcel.writeString(phone);
    }

    private String token;
    private String name;
    private String email;
    private String notes;
    private String phone;


    public ContactModel(String token, String name, String email, String notes, String phone) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
