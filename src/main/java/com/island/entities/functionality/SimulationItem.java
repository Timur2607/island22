package com.island.entities.functionality;

import com.island.entities.board.Board;
import com.island.entities.utils.Coords;
import com.island.entities.utils.ItemsFactory;
import com.island.entities.utils.ItemsFactory;

public abstract class SimulationItem {
    private Board.Cell currentCell;
    private static int globalId = 0;
    private int id;
    public static ItemsFactory factory = new ItemsFactory();
    public boolean died = false;
    public Coords coords;

    abstract public String getImage();

    abstract public double getWeight();

    abstract public int getMaxItemsPerCell();

    public int getId() {
        return id;
    }

    public SimulationItem() {
        id = globalId++;
    }

    public Board.Cell getCell() {
        return currentCell;
    }

    public void setCell(Board.Cell cell) {
        currentCell = cell;
    }

    public void die() {
        Board.Cell currCell = this.getCell();
        currCell.removeSimulationItem(this);
    }

    public boolean isAlive() {
        return this.getCell() != null;
    }
}