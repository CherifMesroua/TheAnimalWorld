package com.example.theanimalworld;

public class Item {
    private String name;
    private int image,sound1,sound2;


    public Item(String name, int image, int sound1, int sound2) {
        this.name = name;
        this.image = image;
        this.sound1 = sound1;
        this.sound2 = sound2;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getSound1() {
        return sound1;
    }

    public int getSound2() {
        return sound2;
    }

}
