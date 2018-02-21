package com.example.george.shopping4;

import android.annotation.SuppressLint;
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
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */

@SuppressLint("ValidFragment")
public class ShoppingFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private List<Product> products;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_fragment,container,false);
        ListView list=(ListView) view.findViewById(R.id.basketList);
        products=new ArrayList<>();
        this.setupProducts();
        this.populateProductList(list);

        return view;
    }

    private void populateProductList(ListView list) {
        ArrayAdapter<Product> adapter=new MyListAdapter();
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Product>{
        public MyListAdapter(){
            super(ShoppingFragment.super.getContext(), R.layout.item_view, products);
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
        Product p1=new Product("Coca Cola",8,R.drawable.a);
        Product p2=new Product("Fanta",10,R.drawable.b);
        Product p3=new Product("Pepsi",10,R.drawable.c);
        Product p4=new Product("Sprite",10,R.drawable.d);
        Product p5=new Product("Pizza",10,R.drawable.e);
        Product p6=new Product("Shaorma",10,R.drawable.f);
        Product p7=new Product("Kurtos",10,R.drawable.g);
        Product p8=new Product("Hotdog",10,R.drawable.h);
        Product p9=new Product("Clatite",10,R.drawable.i);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);
        products.add(p7);
        products.add(p8);
        products.add(p9);
    }
}