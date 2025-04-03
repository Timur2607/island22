package com.island.entities.functionality;


public abstract class Herbivore extends Animal {
    private int howMuchTickCouldLiveWithoutSaturation = 10;

    public int getHowMuchTickCouldLiveWithoutSaturation() {
        return howMuchTickCouldLiveWithoutSaturation;
    }

    public void decrementHowMuchTickCouldLiveWithoutSaturation() {
        howMuchTickCouldLiveWithoutSaturation--;
    }
}