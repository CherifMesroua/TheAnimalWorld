package com.example.theanimalworld;

import java.util.ArrayList;

public class Level_s_Item_Bank {
    public ArrayList<Item> wild_imageList(){
        ArrayList<Item> wild=new ArrayList<>();
        wild.add(new Item("Dog", R.drawable.dog,R.raw.dog,R.raw.dog_name));
        wild.add(new Item("Horse", R.drawable.horse,R.raw.horse,R.raw.horse_name));
        wild.add(new Item("Chicken", R.drawable.chicken,R.raw.chicken,R.raw.chicken_name));
        wild.add(new Item("Turky", R.drawable.turky,R.raw.turkey,R.raw.turkey_name));
        wild.add(new Item("danky", R.drawable.danky,R.raw.donkey,R.raw.donkey_name));
        return wild;
    }
    public ArrayList<Item> house_imageList(){
        ArrayList<Item> house=new ArrayList<>();
        house.add(new Item("Dog", R.drawable.dog,R.raw.dog,R.raw.dog_name));
        house.add(new Item("Horse", R.drawable.horse,R.raw.horse,R.raw.horse_name));
        house.add(new Item("Chicken", R.drawable.chicken,R.raw.chicken,R.raw.chicken_name));
        house.add(new Item("Turky", R.drawable.turky,R.raw.turkey,R.raw.turkey_name));
        house.add(new Item("danky", R.drawable.danky,R.raw.donkey,R.raw.donkey_name));
        return house;
    }

    public ArrayList<Item> farm_imageList(){
        ArrayList<Item> farm=new ArrayList<>();
        farm.add(new Item("Dog", R.drawable.dog,R.raw.dog,R.raw.dog_name));
        farm.add(new Item("Horse", R.drawable.horse,R.raw.horse,R.raw.horse_name));
        farm.add(new Item("Chicken", R.drawable.chicken,R.raw.chicken,R.raw.chicken_name));
        farm.add(new Item("Turky", R.drawable.turky,R.raw.turkey,R.raw.turkey_name));
        farm.add(new Item("danky", R.drawable.danky,R.raw.donkey,R.raw.donkey_name));
        return farm;
    }

}
