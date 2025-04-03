package com.island.entities.utils;

import com.island.entities.functionality.Animal;
import com.island.entities.functionality.SimulationItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EatingProcess {
    private final HashMap<String, HashMap<String, Integer>> chanceToBeEatenMap = ChanceEaten.getConfig();

    public boolean isAttackerEatsVictim(Animal attacker, SimulationItem victim) {
        String attackerClassName = attacker.getClass().getName();

        if (!chanceToBeEatenMap.containsKey(attackerClassName)) {
            return false;
        }

        HashMap<String, Integer> attackersPossibleVictimsMap = chanceToBeEatenMap.get(attackerClassName);
        String victimClassName = victim.getClass().getName();

        if (!attackersPossibleVictimsMap.containsKey(victimClassName)) {
            return false;
        }

        int randomChanceToEatVictim = new Random().nextInt(0, 101);
        int configurationChanceToEatVictim = attackersPossibleVictimsMap.get(victimClassName);
        int resultChanceToEatVictim = randomChanceToEatVictim + configurationChanceToEatVictim;
        int randomChanceNotToBeEatenByAttacker = new Random().nextInt(0, 101);
        int resultChanceNotToBeEatenByAttacker = randomChanceNotToBeEatenByAttacker + (100 - configurationChanceToEatVictim);


        return resultChanceToEatVictim >= resultChanceNotToBeEatenByAttacker;
    }

    public ArrayList<String> getVictimClassNamesByAttackerName(String attackerClassName) {
        ArrayList<String> victims = new ArrayList<>();

        if (!chanceToBeEatenMap.containsKey(attackerClassName)) {
            return victims;
        }

        victims.addAll(chanceToBeEatenMap.get(attackerClassName).keySet());

        return victims;
    }
}