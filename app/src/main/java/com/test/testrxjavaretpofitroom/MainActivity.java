package com.test.testrxjavaretpofitroom;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.test.testrxjavaretpofitroom.Activity.Favorites;
import com.test.testrxjavaretpofitroom.Activity.Home;
import com.test.testrxjavaretpofitroom.databinding.ActivityMainBinding;


@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isFavoriteListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Aвтовизиты");

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                .commit();


        binding.changeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavoriteListVisible){
                    isFavoriteListVisible = false;
                    binding.changeFragment.setText("Избранное");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                            .commit();

                }
                else {
                    isFavoriteListVisible = true;
                    binding.changeFragment.setText("Вернуться к списку");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Favorites())
                            .commit();
                }
            }
        });


    }






}
