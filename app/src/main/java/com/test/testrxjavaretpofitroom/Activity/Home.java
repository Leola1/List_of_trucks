package com.test.testrxjavaretpofitroom.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.testrxjavaretpofitroom.Adapter.DataAdapter;
import com.test.testrxjavaretpofitroom.Adapter.TexAdapter;
import com.test.testrxjavaretpofitroom.ApiService.TruckApiService;
import com.test.testrxjavaretpofitroom.ViewModel.DataViewModel;
import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.Truck;
import com.test.testrxjavaretpofitroom.databinding.HomeBinding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import androidx.lifecycle.Observer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class Home extends Fragment {
    private static final String TAG = "Home";
    private HomeBinding binding;
    private DataViewModel viewModel;
    private DataAdapter adapter;
    private TexAdapter texAdapter;
    private ConcatAdapter adapterAll;
    private ArrayList<Truck> truckList;
    private ArrayList<Tex> texList;
    private TruckApiService apiService;
    private Disposable disposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);

        initRecyclerView();
        observeData();
        setUpItemTouchHelper();
        viewModel.getDataBaseTransport();
        updateBase();
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               int swipedPosition = viewHolder.getLayoutPosition();
                System.out.println("ПРОВЕРКА  Swipe, Home swipedPosition " +swipedPosition);
                int swipedTexPosition;

               if(swipedPosition<adapterAll.getItemCount()-texAdapter.getItemCount()){
               //swipedTruckPosition = viewHolder.getBindingAdapter()
                //        .findRelativeAdapterPositionIn(adapter,viewHolder,swipedPosition);
                   Truck truck = adapter.getTruckAt(swipedPosition);
                   viewModel.insertTruck(truck);
                   adapter.notifyDataSetChanged();
                   adapterAll.notifyDataSetChanged();
                   Toast.makeText(getContext()," Добавлено в избранные .",Toast.LENGTH_SHORT).show();
               }
               else{

                swipedTexPosition = swipedPosition-adapter.getItemCount();
                   Tex tex = texAdapter.getTex(swipedTexPosition);
                   viewModel.insertTex(tex);
                   texAdapter.notifyDataSetChanged();
                   adapterAll.notifyDataSetChanged();
                   Toast.makeText(getContext(),"Добавлено в избранные",Toast.LENGTH_SHORT).show();
               }


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.truckRecyclerView);
    }




  private void observeData() {


       viewModel.getTruckList()
               .observe(getViewLifecycleOwner(), new Observer<ArrayList<Truck>>() {
            @Override
            public void onChanged(ArrayList<Truck> trucks) {
                Log.e(TAG, "onChanged: " + trucks.size() );
                adapter.updateList(trucks);

            }
        });
       viewModel.getTexList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tex>>() {
           @Override
           public void onChanged(ArrayList<Tex> tex) {
               Log.e(TAG, "onChanged: " + tex.size() );
               texAdapter.updateListTex(tex);

           }
       });

    }

    private void initRecyclerView() {
        binding.truckRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      adapter = new DataAdapter(getContext(),truckList);
      texAdapter = new TexAdapter(getContext(),texList);
   adapterAll = new ConcatAdapter(adapter, texAdapter);

       // binding.pokemonRecyclerView.setAdapter(adapter);
        binding.truckRecyclerView.setAdapter(adapterAll);
    }



    public void updateBase(){
//каждые 30секунд
        disposable = Observable.interval(1000, 30000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::callEndpoint, this::onError);}

    private void callEndpoint(Long aLong) {
        viewModel.getDataBaseTransport();
        System.out.println("ПРОВЕРКА  Home обновление каждые 30 секунд");


    }

    @Override
    public void onResume() {
        super.onResume();

        if (disposable.isDisposed()) {
            disposable = Observable.interval(1000, 30000,
                    TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::callEndpoint, this::onError);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        disposable.dispose();
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, "Ошибка обновления базы: " + throwable );
    }


}
