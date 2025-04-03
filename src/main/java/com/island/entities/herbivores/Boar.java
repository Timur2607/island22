package com.island.entities.herbivores;
import com.island.entities.functionality.Herbivore;

public class Boar extends Herbivore {
    private final String image = "\uD83D\uDC17";
    private final double weight = 400;
    private final int maxItemsPerCell = 50;
    private final int cellMovesPerCycle = 2;
    private final double saturationAmount = 50;

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