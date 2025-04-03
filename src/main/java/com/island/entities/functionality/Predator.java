package com.island.entities.functionality;


public abstract class Predator extends Animal {
    private int howMuchTickCouldLiveWithoutSaturation = 50;

    public int getHowMuchTickCouldLiveWithoutSaturation() {
        return howMuchTickCouldLiveWithoutSaturation;
    }

    public void decrementHowMuchTickCouldLiveWithoutSaturation() {
        howMuchTickCouldLiveWithoutSaturation--;
    }
}