package com.example.george.shopping4;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.george.shopping4.Model.Product;

import java.util.ArrayList;

/**
 * Created by User on 2/28/2017.
 */

public class MyBasketFragment extends Fragment {
    private ArrayList<Product> products;
    private int totalPrice=50;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mybasket_fragment,container,false);
        ListView list=(ListView) view.findViewById(R.id.mybasketList);
        products=new ArrayList<>();
        this.setupProducts();
        this.populateProductList(list);

        TextView totalpriceEdit=(TextView)view.findViewById(R.id.totalpriceEdit);
        totalpriceEdit.setText(this.totalPrice+"");
        return view;
    }

    private void populateProductList(ListView list) {
        ArrayAdapter<Product> adapter=new MyListAdapter();
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Product>{
        public MyListAdapter(){
            super(MyBasketFragment.super.getContext(), R.layout.item_view, products);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView =convertView;
            if(itemView==null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            final Product currentProduct=products.get(position);

            TextView nameEdit=(TextView)itemView.findViewById(R.id.nameEdit);
            nameEdit.setText(currentProduct.getName());

            TextView priceEdit=(TextView)itemView.findViewById(R.id.totalpriceEdit);
            priceEdit.setText(currentProduct.getPrice()+"");

            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);
            imageView.setImageResource(currentProduct.getImgId());

            final TextView quantityEdit=(TextView)itemView.findViewById(R.id.quantityEdit);
            quantityEdit.setText(currentProduct.getQuatity()+"");

            Button addButton=(Button) itemView.findViewById(R.id.addButton);
            Button removeButton=(Button) itemView.findViewById(R.id.removeButton);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //madafakaaaa
                    currentProduct.addItem();
                    quantityEdit.setText(currentProduct.getQuatity()+"");
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //madafakaaaa
                    if(currentProduct.getQuatity()>0)
                        currentProduct.removeItem();
                    quantityEdit.setText(currentProduct.getQuatity()+"");
                }
            });

            return itemView;

        }
    }

    private void setupProducts(){
        Product p2=new Product("Fanta",10,R.drawable.b);
        Product p6=new Product("Shaorma",10,R.drawable.f);
        Product p8=new Product("Hotdog",10,R.drawable.h);
        p2.addItem();
        p2.addItem();
        products.add(p2);
        p6.addItem();
        products.add(p6);
        p8.addItem();
        p8.addItem();
        products.add(p8);
    }
}