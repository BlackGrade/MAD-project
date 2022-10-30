package com.example.expenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.expenda.databinding.ActivityHomeBinding;
import com.example.expenda.databinding.ActivityIncomCatergoryBinding;
import com.example.expenda.databinding.ActivityIncomeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class IncomeActivity extends AppCompatActivity {

    private ActivityIncomeBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();

            }

        });
    }


    private  String amount ="";
    private String note="";
    private String type="";


    private void validateData() {


        amount = binding.amountDt.getText().toString().trim();
        note = binding.noteDt.getText().toString().trim();
        type=binding.typeDt.getText().toString().trim();


        if(TextUtils.isEmpty(amount)){
            Toast.makeText(this,"Enter Your details..",Toast.LENGTH_SHORT).show();
        }

        else{
            createIncome();
        }

    }

    private void createIncome() {

        progressDialog.setTitle("Saving");
        progressDialog.show();

        long timestamp = System.currentTimeMillis();

        String  uid = firebaseAuth.getUid();


        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("id",""+timestamp);
        hashMap.put("Amount",amount);
        hashMap.put("uid",uid);
        hashMap.put("noteId",note);
        hashMap.put("typeId",type);



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("incomeTable");
        ref.child(firebaseAuth.getUid()).child(""+timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(IncomeActivity.this,"Saved",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(IncomeActivity.this,IncomeTable.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(IncomeActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

    }

}