package com.test.testrxjavaretpofitroom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.testrxjavaretpofitroom.APIupdateService;
import com.test.testrxjavaretpofitroom.Truck;
import com.test.testrxjavaretpofitroom.UpdateDataTruck;
import com.test.testrxjavaretpofitroom.databinding.ListItemBinding;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
        private Context mContext;
        private ArrayList<Truck> mList;
        private ListItemBinding binding;
      private String beforeTextChanged;

        public DataAdapter(Context mContext, ArrayList<Truck> mList) {
            this.mContext = mContext;
            this.mList = mList;

        }

        @NonNull
        @Override
        public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            binding = ListItemBinding.inflate(inflater,parent,false);
            return new DataViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

    holder.itemBinding.name.setText("ID " + mList.get(position).getIdTruck().toString());
    holder.itemBinding.editQuery.setText(mList.get(position).getCompany());
            holder.itemBinding.getRoot().setCardBackgroundColor(Color.parseColor("#f39fb1"));
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
                    /*if (!editable.toString().startsWith("***")) {
                        editable.insert(0, "***"); }*/
                  //  System.out.println("ПРОВЕРКА  адаптер редактируемый текст" );
                    updateData(editable);
                }});


        }

        public void updateData(CharSequence s){

            System.out.println("ПРОВЕРКА  адаптер ретрофит не деловые линии " + s.toString() );
            if(s.toString().equals(beforeTextChanged)){}
            else{
                UpdateDataTruck tr = new UpdateDataTruck();
                tr.setCompany(s.toString());
                    Call<UpdateDataTruck> call= APIupdateService.getTransport()
                            .updateTruck(tr.getCompany());
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

           return mList == null ? 0 : mList.size();
       }



       class DataViewHolder extends RecyclerView.ViewHolder{
            private ListItemBinding itemBinding;

            public DataViewHolder(ListItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
            }
        }

      public  void updateList(ArrayList<Truck> updatedList){
            mList = updatedList;
          System.out.println("ПРОВЕРКА  DataAdapter updateList" +mList);
            notifyDataSetChanged();
        }

       public  Truck getPokemonAt(int position){
            return mList.get(position);
        }

}
