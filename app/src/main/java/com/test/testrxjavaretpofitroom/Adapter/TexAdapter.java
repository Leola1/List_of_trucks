package com.test.testrxjavaretpofitroom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.test.testrxjavaretpofitroom.ApiService.APIupdateService;
import com.test.testrxjavaretpofitroom.Transport.Tex;
import com.test.testrxjavaretpofitroom.Transport.UpdateDataTex;
import com.test.testrxjavaretpofitroom.Transport.UpdateDataTruck;
import com.test.testrxjavaretpofitroom.databinding.ListItemBinding;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TexAdapter extends RecyclerView.Adapter<TexAdapter.TexViewHolder> {
    private Context mContext;
    private ArrayList<Tex> texList;
    private ListItemBinding binding;
    private String beforeTextChanged;

    public TexAdapter(Context mContext, ArrayList<Tex> texList) {
        this.mContext = mContext;
        this.texList = texList;

    }

    @NonNull
    @Override
    public TexAdapter.TexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
       binding = ListItemBinding.inflate(inflater, parent, false);
       // binding = DataBindingUtil.inflate(
       //         LayoutInflater.from(parent.getContext()), R.layout.list_item_tex,parent,false);
        return new TexAdapter.TexViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TexAdapter.TexViewHolder holder, int position) {

            holder.itemBinding.name.setText("ID #" + texList.get(position).getIdTex().toString());
        holder.itemBinding.editQuery.setText(texList.get(position).getDescription());
        holder.itemBinding.getRoot().setCardBackgroundColor(Color.parseColor("#3BD98C"));
        holder.itemBinding.editQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                 beforeTextChanged = s.toString();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                updateData(editable);
                 }});
    }
    public void updateData(CharSequence s){

        System.out.println("ПРОВЕРКА  адаптер ретрофит не деловые линии " + s.toString() );
        if(s.toString().equals(beforeTextChanged)){}
        else{
            UpdateDataTex tr = new UpdateDataTex(s.toString());
            tr.setDescription(s.toString());
            Call<UpdateDataTruck> call= APIupdateService.getTransport()
                    .updateTruck(tr.getDescription());
            call.enqueue(new Callback<UpdateDataTruck>() {
                @Override
                public void onResponse(Call<UpdateDataTruck> call, Response<UpdateDataTruck> response) {
                    System.out.println("ПРОВЕРКА  EditText callbackRetrofit " + response.body());
                    //updateData

                }
                @Override
                public void onFailure(Call<UpdateDataTruck> call, Throwable t) {
                    System.out.println("ПРОВЕРКА  EditText callbackRetrofit Throwable" + t);

                }});}

    }

    @Override
    public int getItemCount() {

        return texList == null ? 0 : texList.size();
    }



    class TexViewHolder extends RecyclerView.ViewHolder {
        private ListItemBinding itemBinding;

        public TexViewHolder(ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    public  void updateListTex(ArrayList<Tex> updatedList){
        texList = updatedList;
        System.out.println("ПРОВЕРКА  DataAdapter updateList" +texList);
        notifyDataSetChanged();
    }


    public  Tex getTex(int position){
        return texList.get(position);
    }
}
