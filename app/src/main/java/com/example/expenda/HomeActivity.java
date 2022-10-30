package com.example.expenda;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.expenda.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.incomeidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,IncomeActivity.class));
            }
        });



//        Toolbar toolbar = findViewById(R.id.my_toolbar);
//        toolbar.setTitle("Expense Manager");
//        setSupportActionBar(toolbar);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.income);
//        bottomNavigationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this,IncomeActivity.class));
//
//            }
//        });

        //DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
//        );

//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = findViewById(R.id.naView);
//        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed(){

//        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//
//        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
//            drawerLayout.closeDrawer(GravityCompat.END);
//        }else{
//            super.onBackPressed();
//        }

    }

    public void displaySelectedListener(int itemId){
        Fragment fragment = null;

        switch (itemId){
            case R.id.dashboard:
                break;

            case R.id.income:
                break;

            case R.id.expense:
                break;
        }


//        if (fragment!=null){
//            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.main_frame,fragment);
//            ft.commit();
//        }
//        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
//        drawerLayout.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedListener(item.getItemId());
        return true;
    }
}