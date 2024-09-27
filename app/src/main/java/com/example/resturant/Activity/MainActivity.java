package com.example.resturant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resturant.Activity.Adapter.BestFoodsAdapter;
import com.example.resturant.Activity.Adapter.CategoryAdapter;
import com.example.resturant.Activity.Domain.Category;
import com.example.resturant.Activity.Domain.Foods;
import com.example.resturant.Activity.Domain.Location;
import com.example.resturant.Activity.Domain.Price;
import com.example.resturant.Activity.Domain.Time;
import com.example.resturant.R;
import com.example.resturant.databinding.ActivityLoginBinding;
import com.example.resturant.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialLocation();
        initialTime();
        initialPrice();
        initBestFood();
        initCategory();
        setVariable();
    }

    private void setVariable() {
        binding.logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=binding.editTextsearch.getText().toString();
                if(text.isEmpty())
                {
                    Intent intent=new Intent(MainActivity.this, ListFoodActivity.class);
                    intent.putExtra("text",text);
                    intent.putExtra("isSearch",true);
                    startActivity(intent);
                }
            }
        });
    }

    private void initialLocation() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");

        ArrayList<Location> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren())
                    {
                        Location location = issue.getValue(Location.class);
                        if (location != null) {
                            list.add(location);
                        }

                    }
                }
                ArrayAdapter<Location> adapter=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.locationSp.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initialTime() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Time");

        ArrayList<Time> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren())
                    {
                        Time time = issue.getValue(Time.class);
                        if (time != null) {
                            list.add(time);
                        }

                    }
                }
                ArrayAdapter<Time> adapterTime=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.timeSp.setAdapter(adapterTime);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initialPrice() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Price");

        ArrayList<Price> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren())
                    {
                        Price price = issue.getValue(Price.class);
                        if (price != null) {
                            list.add(price);
                        }

                    }
                }
                ArrayAdapter<Price> adapterPrice=new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.pricesp.setAdapter(adapterPrice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initBestFood() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Foods.class));
                    }
                }
                if (list.size() > 0) {
                    binding.bestFoodView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    RecyclerView.Adapter adapter = new BestFoodsAdapter(list);
                    binding.bestFoodView.setAdapter(adapter);
                }
                binding.progressBarBestFood.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
       // Query query = myRef.orderByChild("BestFood").equalTo(true);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));
                    }
                }
                if (list.size() > 0) {
                    binding.CategoryView.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                    RecyclerView.Adapter adapter = new CategoryAdapter(list);
                    binding.CategoryView.setAdapter(adapter);
                }
                binding.progressBarCategory.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}