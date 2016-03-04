package com.clicklabs.shopkeeperassignmentshared.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clicklabs.shopkeeperassignmentshared.activities.CustomerDetails;
import com.clicklabs.shopkeeperassignmentshared.Models.shopkeeper.GetCustomer.CustomerData;
import com.clicklabs.shopkeeperassignmentshared.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Krishika on 12-02-2016.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    List<CustomerData> data = Collections.emptyList();

    public CustomerAdapter(Context context, List<CustomerData> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CustomerData driverData = data.get(position);
        holder.displayDriverName.setText(driverData.getName());
        holder.pos = position;
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(this);

        // holder.displayImage.set


    }
    public void resetData(List<CustomerData> newData) {

        this.data = newData;
        notifyDataSetChanged();
    }


        @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        MyViewHolder holder = (MyViewHolder) v.getTag();
        Intent intent = new Intent(context, CustomerDetails.class);
        intent.putExtra("DisplayDetails", data.get(holder.pos));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView displayDriverName;
        ImageView displayImage;
        int pos;

        public MyViewHolder(View itemView) {
            super(itemView);
            displayDriverName = (TextView) itemView.findViewById(R.id.tv_label_caption);
            //  displayImage = (ImageView) itemView.findViewById(R.id.picture);

        }
    }
}


