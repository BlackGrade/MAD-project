package com.example.expenda;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expenda.databinding.RowCategoryBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdapterIncome extends RecyclerView.Adapter<AdapterIncome.HolderCategory> {

    private Context context;
    private ArrayList<IncomeModel> incomeArrayList;

    private RowCategoryBinding binding;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;
    private static final String TAG = "Income";



    public AdapterIncome(Context context, ArrayList<IncomeModel> incomeArrayList) {
        this.context = context;
        this.incomeArrayList = incomeArrayList;
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HolderCategory(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        IncomeModel model = incomeArrayList.get(position);
        String id = model.getId();
        String amount = model.getAmount();
        String note = model.getNote();
        String type = model.getType();
        long timestamp = model.getTimestamp();


        holder.categoryTv.setText(amount);
        holder.categoryTv.setText(note);
        holder.categoryTv.setText(type);

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Are you sure ?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Toast.makeText(context,"Deleting..",Toast.LENGTH_SHORT).show();
                                deleteCategory(model,holder);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();

                            }
                        })
                        .show();


            }
        });


    }


    private void deleteCategory(IncomeModel model, HolderCategory holder) {
        String id = firebaseAuth.getUid();
        String id2 = model.getId();
        long timestamp = model.getTimestamp();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("incomeTable");
        reference.child(id).child(id2)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context,"SuccessFully Deleted..",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }


    @Override
    public int getItemCount() {
        return incomeArrayList.size();
    }

    class HolderCategory extends RecyclerView.ViewHolder{
        TextView categoryTv;
        ImageButton updateBtn,delBtn;



        public HolderCategory(@NonNull View itemView) {
            super(itemView);
            categoryTv = binding.categoryTv;
            updateBtn = binding.updateBtn;
            delBtn = binding.delBtn;
        }
    }
}
