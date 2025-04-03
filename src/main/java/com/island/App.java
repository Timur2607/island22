package com.island;


import com.island.entities.functionality.Animal;
import com.island.entities.functionality.Plant;
import com.island.entities.functionality.PrintStatistics;
import com.island.entities.functionality.SimulationItem;
import com.island.entities.board.Board;
import com.island.entities.utils.LifeSim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {

        LifeSim liveSimulator = new LifeSim();
        if (!liveSimulator.isReady()) {
            System.out.println("liveSimulator is not ready! error occurred!");
        }
        Board board = liveSimulator.getBoard();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new PrintStatistics(board), 0, 1, TimeUnit.SECONDS);

        HashMap<String, ArrayList<? extends SimulationItem>> allAliveItemsMap = board.getAllAliveItemsMap();
        ArrayList<Animal> predators = (ArrayList<Animal>) allAliveItemsMap.get("predators");
        ArrayList<Animal> herbivores = (ArrayList<Animal>) allAliveItemsMap.get("herbivores");
        ArrayList<Plant> plants = (ArrayList<Plant>) allAliveItemsMap.get("plants");

        int ticks = 0;
        while (predators.size() > 0 && herbivores.size() > 0) {
            plants = liveSimulator.startPlantsGrowOneTick(plants);

            predators = liveSimulator.startAnimalsLiveOneTick(predators);

            herbivores = liveSimulator.startAnimalsLiveOneTick(herbivores);

            System.out.println("ticks=" + ticks);
            Thread.sleep(100);
            ticks++;
        }

        board.printScheme();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}