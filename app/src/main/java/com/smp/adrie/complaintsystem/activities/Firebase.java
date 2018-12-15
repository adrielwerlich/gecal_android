package com.smp.adrie.complaintsystem.activities;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    private static DatabaseReference firebase;

    public static  DatabaseReference getFirebase() {

        if (firebase == null) firebase = FirebaseDatabase.getInstance().getReference();

            return firebase;
    }

}
