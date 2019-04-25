package com.example.pokemongo1;

public class Pokemonp {
    private   int Image;
    private String name;
    private String power;
    public  Pokemonp(){}

    public Pokemonp(int image, String name, String power) {
        Image = image;
        this.name = name;
        this.power = power;
    }


    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
