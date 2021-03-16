package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextNumber;
    EditText editTextAddress;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.Name);
        editTextName = findViewById(R.id.Number);
        editTextName = findViewById(R.id.Address);
    }

    public void saveToFirebase(View v) {
        String name = editTextName.getText().toString();
        String number = editTextNumber.getText().toString();
        String address = editTextAddress.getText().toString();


        Map<String, Object> data = new HashMap<>();
        data.put("Name",name);
        data.put("Number",number);
        data.put("Address",address);
        db.collection("All data")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG" , "Data Added successfully To DB");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG" , "failed To add To DB "+e);
                    }
                });

    }
}