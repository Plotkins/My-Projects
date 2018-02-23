package com.example.george.shopping4.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private int imgId;
    private int quatity;
    public Product(String name, int price,int imgId)
    {
        this.name = name;
        this.price = price;
        this.imgId=imgId;
        this.quatity=0;
    }

    public int getQuatity() {
        return quatity;
    }
    public void addItem(){
        this.quatity+=1;
    }

    public void removeItem(){
        this.quatity-=1;
    }

    public void setQuatity(int q){
        this.quatity=q;
    }
    public int getImgId() {
        return imgId;
    }

    public String getName()
    {
        return this.name;
    }
    public int getPrice()
    {
        return this.price;
    }
}