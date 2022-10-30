package com.example.expenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.expenda.databinding.ActivityEditCatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditCatActivity extends AppCompatActivity {

    private String id;
    private ActivityEditCatBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCatBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        id= getIntent().getStringExtra("id");

        loadCategories();
    }

    private void loadCategories() {





        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ExpensesC");
        ref.child(firebaseAuth.getUid()).child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String cName = ""+snapshot.child("category").getValue();
                        binding.upTxt.setText(cName);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}