package com.test.testrxjavaretpofitroom;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.test.testrxjavaretpofitroom.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;


@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isFavoriteListVisible = false;
    private Home home;
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

   // public void updateBase(){

     /*   Observable.interval(60, TimeUnit.SECONDS)
                .flatMap(n -> { return dataViewModel.getDataBaseTransport();})
                .repeat()
                .subscribe();
 Subscription subscription = Observable.interval(1000, 5000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    public void call(Long aLong) {
                        // here is the task that should repeat
                    }
                });
    }*/




}
