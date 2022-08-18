package com.app.addvertisementapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.app.addvertisementapp.Model.GetAddsObject;
import com.app.addvertisementapp.R;


public class GetAddsAdapter extends RecyclerView.Adapter<GetAddsAdapter.GetAddsViewHolder> {

    ArrayList<GetAddsObject> gamelist;

    public GetAddsAdapter(ArrayList<GetAddsObject> gamelist) {
        this.gamelist = gamelist;
    }

    @NonNull
    @Override
    public GetAddsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_adds, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        GetAddsViewHolder rcv = new GetAddsViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull GetAddsViewHolder holder, int position) {

        holder.Add.setText( gamelist.get(position).getAddText());
        holder.City.setText("شهر :" + gamelist.get(position).getCity());
        holder.CellPhone.setText("شماره تماس :" + gamelist.get(position).getCellPhoneNumber());

        holder.Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return gamelist.size();
    }

    public class GetAddsViewHolder extends RecyclerView.ViewHolder {
        public TextView Add, City, CellPhone;

        public GetAddsViewHolder(View view) {
            super(view);
            Add = view.findViewById(R.id.AddText);
            City = view.findViewById(R.id.City);
            CellPhone = view.findViewById(R.id.CellPhone);

        }
    }


}
