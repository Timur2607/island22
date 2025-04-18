package com.island.entities.utils;

import com.island.entities.functionality.*;
import com.island.entities.board.Board;
import com.island.entities.herbivores.Caterpillar;
import com.island.entities.plants.grass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BoardInit {

    public int getRandomCountOfItemsToCreate(SimulationItem simulationItem, Random random) {
        int maxItemsPerCell = simulationItem.getMaxItemsPerCell();

        if (simulationItem instanceof Caterpillar) {
            return random.nextInt(1, 1000);
        }

        if (simulationItem instanceof grass) {
            return random.nextInt(1, maxItemsPerCell);
        }

        if (simulationItem instanceof Predator) {
            return random.nextInt(maxItemsPerCell, maxItemsPerCell + 1);
        }

        if (simulationItem instanceof Herbivore) {
            return random.nextInt(maxItemsPerCell / 2, maxItemsPerCell * 10);
        }
        return maxItemsPerCell;
    }

    public void randomlyShuffleItemsOnBoard(
            MovingProcess movementManagerUtils,
            ArrayList<? extends SimulationItem> simulationItems
    ) {
        Random random = new Random();
        Board board = movementManagerUtils.board;
        simulationItems.forEach(item -> {
            Coords coords = new Coords(
                    random.nextInt(0, board.getRightBoundX() + 1),
                    random.nextInt(0, board.getDownBoundY() + 1)
            );

            movementManagerUtils.moveByCoords(item, coords);
        });
        for (SimulationItem item : simulationItems) {

            for (int i = 0; i < 10; i++) {
                Coords possibleCoords = new Coords(
                        random.nextInt(0, board.getRightBoundX() + 1),
                        random.nextInt(0, board.getDownBoundY() + 1)
                );
                Board.Cell possibleCell = board.getCellByCoords(possibleCoords);
                List<SimulationItem> similarItems = possibleCell.getCurrentSimulationItems()
                        .stream()
                        .filter(curr -> curr.getId() != item.getId())
                        .collect(Collectors.toList());

                boolean limitIsNotReached = item.getMaxItemsPerCell() > similarItems.size();
                if (limitIsNotReached) {
                    movementManagerUtils.moveByCoords(item, possibleCoords);
                    break;
                }
            }
        }
    }


    public boolean init(MovingProcess movementManagerUtils) {
        ArrayList<SimulationItem> simulationItems = new ArrayList<>();
        SimulationEntitiesLoader simulationClassesLoader = new SimulationEntitiesLoader();
        ItemsFactory simulationItemsFactory = new ItemsFactory();
        Random random = new Random();

        ArrayList<SimulationItem> simulationClasses = simulationClassesLoader.getLoadedClasses();
        try {
            for (int i = 0; i < simulationClasses.size(); i++) {
                SimulationItem simulationItem = simulationClasses.get(i);
                int countOfItemsToCreate = getRandomCountOfItemsToCreate(simulationItem, random);

                if (simulationItem instanceof Animal) {
                    Class animalClazz = simulationItem.getClass();
                    ArrayList<Animal> animals = simulationItemsFactory.createAnimalsByType(animalClazz, countOfItemsToCreate);
                    simulationItems.addAll(animals);
                } else if (simulationItem instanceof Plant) {
                    Class plantClazz = simulationItem.getClass();
                    ArrayList<Plant> plants = simulationItemsFactory.createPlantsByType(plantClazz, countOfItemsToCreate);
                    simulationItems.addAll(plants);
                }
            }
        } catch (Exception e) {
            System.out.println("Board init failed\\n" + Arrays.toString(e.getStackTrace()));
            return false;
        }

        randomlyShuffleItemsOnBoard(movementManagerUtils, simulationItems);

        return true;
    }
}