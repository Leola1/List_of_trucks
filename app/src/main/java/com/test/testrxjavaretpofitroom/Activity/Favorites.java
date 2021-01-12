package com.test.testrxjavaretpofitroom.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.testrxjavaretpofitroom.Adapter.DataAdapter;
import com.test.testrxjavaretpofitroom.Adapter.TexAdapter;
import com.test.testrxjavaretpofitroom.ViewModel.DataViewModel;
import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.Truck;
import com.test.testrxjavaretpofitroom.databinding.FavoriteBinding;

import java.util.ArrayList;
import java.util.List;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;



@AndroidEntryPoint
public class Favorites extends Fragment {

    private DataViewModel viewModel;
    private DataAdapter adapter;
    private ArrayList<Truck> truckList;
    private ArrayList<Tex> texList;
    private TexAdapter texAdapter;
    private ConcatAdapter adapterAll;

    private FavoriteBinding fBinding;
   @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {


     //  fBinding = DataBindingUtil.inflate(inflater, R.layout.favorite,container, false);
       fBinding = FavoriteBinding.inflate(inflater,container,false);
      return fBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);

        initRecyclerView();
        setUpItemTouchHelper();
        observeData();
       viewModel.getFavoriteTruck();
       viewModel.getFavoriteTex();
    }

    private void observeData() {
        viewModel.getFavoriteList().observe(getViewLifecycleOwner(), new Observer<List<Truck>>() {
            @Override
            public void onChanged(List<Truck> trucks) {

             // if(truck == null || truck.size() == 0)
             //      fBinding.noFavoritesText.setVisibility(View.VISIBLE);}

                    ArrayList<Truck> list = new ArrayList<>();
                    list.addAll(trucks);
                    adapter.updateList(list);
                 // adapter.notifyDataSetChanged();

            }
        });
        viewModel.getFavoriteListTex().observe(getViewLifecycleOwner(), new Observer<List<Tex>>() {
            @Override
            public void onChanged(List<Tex> tex) {

                    ArrayList<Tex> list = new ArrayList<>();
                    list.addAll(tex);
                    texAdapter.updateListTex(list);
                   // texAdapter.notifyDataSetChanged();

            }
        });
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPosition = viewHolder.getLayoutPosition();
                System.out.println("ПРОВЕРКА  Swipe, Home swipedPosition " +swipedPosition);

               if(swipedPosition<adapterAll.getItemCount()-texAdapter.getItemCount()){

                    Truck truck = adapter.getTruckAt(swipedPosition);

                    viewModel.deleteTruck(truck.getCompany());
                    adapter.notifyDataSetChanged();
                    adapterAll.notifyDataSetChanged();
                    Toast.makeText(getContext(),"Транспорт удален из избранных",Toast.LENGTH_SHORT).show();
                }
                else{
                 int  swipedTexPosition = swipedPosition-adapter.getItemCount();

                    Tex tex = texAdapter.getTex(swipedTexPosition);
                    viewModel.deleteTex(tex.getDescription());
                    texAdapter.notifyDataSetChanged();
                    adapterAll.notifyDataSetChanged();
                    Toast.makeText(getContext(),"Транспорт удален из избранных",Toast.LENGTH_SHORT).show();
                }

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(fBinding.favoritesRecyclerView);
    }


    private void initRecyclerView() {
        fBinding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DataAdapter(getContext(),truckList);
        texAdapter = new TexAdapter(getContext(),texList);
        adapterAll = new ConcatAdapter(adapter, texAdapter);

        fBinding.favoritesRecyclerView.setAdapter(adapterAll);
    }
}
