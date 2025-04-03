package com.island.entities.predators;

import com.island.entities.functionality.Predator;

public class Wolf extends Predator {
    private final String image = "C:\\Users\\Тимур\\Downloads\\волк.jpg";
    private final double weight = 50;
    private final int maxItemsPerCell = 30;
    private final int cellMovesPerCycle = 3;
    private final double saturationAmount = 8;

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxItemsPerCell() {
        return maxItemsPerCell;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public int getCellMovesPerCycle() {
        return cellMovesPerCycle;
    }

    @Override
    public double getSaturationAmount() {
        return saturationAmount;
    }
}