package com.island.entities.utils;

import com.island.entities.functionality.*;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class SimulationEntitiesLoader {

    private final Path pathToUserDirectory = Path.of(System.getProperty("src/main/java/com/island/entities/{predators, plants, herbivores}"));
    private final Path pathToClasses = pathToUserDirectory.resolve("src/main/java/com/island/entities");
    private final String classesPackageName = "com.island.entities";
    private final ArrayList<String> classesToLoadDirNames = new ArrayList<>(asList("predators", "plants", "herbivores"));


    public ArrayList<SimulationItem> getLoadedClasses() {
        ArrayList<SimulationItem> loadedClasses = new ArrayList<>();
        ItemsFactory simulationItemsFactory = new ItemsFactory();

        classesToLoadDirNames.forEach(dirName -> {
            Path pathToLoadClassDir = pathToClasses.resolve(dirName);
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(pathToLoadClassDir)) {
                for (Path path : directoryStream) {
                    if (Files.isRegularFile(path)) {
                        String className = path.getFileName().toString().split(".java")[0];
                        String fullClassName = classesPackageName + "." + dirName + "." + className;
                        Class clazz = Class.forName(fullClassName);
                        Class clazzSuperclassName = clazz.getSuperclass();

                        SimulationItem simulationItemInstance = null;
                        if (clazzSuperclassName.equals(Predator.class) || clazzSuperclassName.equals(Herbivore.class)) {
                            simulationItemInstance = simulationItemsFactory.createAnimalByType(clazz);
                        } else if (clazzSuperclassName.equals(Plant.class)) {
                            simulationItemInstance = simulationItemsFactory.createPlantByType(clazz);
                        }
                        loadedClasses.add(simulationItemInstance);
                    }
                }
            } catch (Exception e) {
                System.out.println("Could not load classes from directory " + dirName);
            }
        });

        return loadedClasses;
    }
}
