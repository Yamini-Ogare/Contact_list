package ognora.contactlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class ListAdapters extends  RecyclerView.Adapter<ListAdapters.MyViewHolder>{

    ArrayList<ContactModel> arrayList = new ArrayList<>();
    Context context ;

    public ListAdapters(ArrayList<ContactModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

      View v ;
      v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

       return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(arrayList.get(position).getName());
        holder.phno.setText(arrayList.get(position).getPhno());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , phno ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phno = itemView.findViewById(R.id.phone);


        }
    }
}
