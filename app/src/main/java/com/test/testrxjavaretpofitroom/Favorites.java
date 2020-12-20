package com.test.testrxjavaretpofitroom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.testrxjavaretpofitroom.Adapter.DataAdapter;
import com.test.testrxjavaretpofitroom.Adapter.TexAdapter;
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

     //  binding = ListItemBinding.inflate(inflater,container,false);
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
       viewModel.getFavoritePokemon();
       viewModel.getFavoriteTex();
    }

    private void observeData() {
        viewModel.getFavoritePokemonList().observe(getViewLifecycleOwner(), new Observer<List<Truck>>() {
            @Override
            public void onChanged(List<Truck> pokemons) {

             // if(truck == null || truck.size() == 0)
             //      fBinding.noFavoritesText.setVisibility(View.VISIBLE);}

                    ArrayList<Truck> list = new ArrayList<>();
                    list.addAll(pokemons);
                    adapter.updateList(list);
                 // adapter.notifyDataSetChanged();

            }
        });
        viewModel.getFavoriteListTex().observe(getViewLifecycleOwner(), new Observer<List<Tex>>() {
            @Override
            public void onChanged(List<Tex> pokemons) {

                    ArrayList<Tex> list = new ArrayList<>();
                    list.addAll(pokemons);
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
                System.out.println("ПРОВЕРКА  Swipe, Home swipedPokemonPosition " +swipedPosition);

               if(swipedPosition<adapterAll.getItemCount()-texAdapter.getItemCount()){

                    Truck pokemon = adapter.getPokemonAt(swipedPosition);

                    viewModel.deleteTruck(pokemon.getCompany());
                    adapter.notifyDataSetChanged();
                    adapterAll.notifyDataSetChanged();
                    Toast.makeText(getContext(),"Транспорт удален из избранных",Toast.LENGTH_SHORT).show();
                }
                else{
                 int  swipedTexPosition = swipedPosition-adapter.getItemCount();

                    Tex pokemon = texAdapter.getPokemonTex(swipedTexPosition);
                    viewModel.deleteTex(pokemon.getDescription());
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
