package com.island.entities.plants;

import com.island.entities.functionality.Plant;

public class grass extends Plant {
    private final String image = "\uD83C\uDF31";
    private final double weight = 1;
    private final int maxItemsPerCell = 200;

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxItemsPerCell() {
        return maxItemsPerCell;
    }
}