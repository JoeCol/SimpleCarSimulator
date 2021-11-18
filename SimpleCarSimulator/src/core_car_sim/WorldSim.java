package core_car_sim;

import java.util.ArrayList;
import java.util.HashMap;

enum Direction
{
	north("^"),
	south("V"),
	east(">"),
	west("<");
	
	private final String dir;
	private Direction(String s)
	{
		dir = s;
	}
	
	public String toString()
	{
		return dir;
	}
}

interface CarAddedListener {
	AbstractCar addControlledCar(int i, int x, int y);
}

public class WorldSim
{
	private AbstractCell[][] world;
	private ArrayList<CarAddedListener> carAddedListeners = new ArrayList<CarAddedListener>();
	private ArrayList<AbstractCar> cars = new ArrayList<AbstractCar>();
	private int width;
	private int height;
	
	private int defaultSpeedLimit = 1; //Measured in squares per sim step

	public WorldSim(int x, int y)
	{
		width = x;
		height = y;
		world = new AbstractCell[x][y];
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void addCarAddedListener(CarAddedListener cal)
	{
		carAddedListeners.add(cal);
	}

	public void setDefaultSpeedLimit(int _defaultSpeedLimit)
	{
		defaultSpeedLimit = _defaultSpeedLimit;
	}
	
	public void resetCarPositions()
	{
		for (AbstractCar car : cars)
		{
			car.resetPosition();
		}
	}
	
	public void simulate(int numOfSteps)
	{
		for (int i = 0; i < numOfSteps; i++)
		{
			updateCells();
			carMovementPhase();
		}
	}

	private void carMovementPhase()
	{
		//Let car view world
		HashMap<AbstractCar, ArrayList<Direction>> allRoutes = new HashMap<AbstractCar, ArrayList<Direction>>();
		for (AbstractCar car : cars)
		{
			ArrayList<ArrayList<AbstractCell>> visibleWorld = getVisibleWorldForPosition(car.getCurrentPosition());
			car.visibleWorldUpdate(visibleWorld);
			ArrayList<Direction> route = car.getSimulationRoute();
			allRoutes.put(car, route);
		}
		
		//Check for invalid routes / crashes 
		//for each route entry add position as key, then add car id as pair
		HashMap<AbstractCar, Point> finalPositions = new HashMap<AbstractCar, Point>();
		//Move to new position
		for (AbstractCar car : cars)
		{
			car.setCurrentPosition(finalPositions.get(car));
		}
		
	}

	private ArrayList<ArrayList<AbstractCell>> getVisibleWorldForPosition(Point currentPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	private void updateCells()
	{
		for (AbstractCell[] row : world)
		{
			for (AbstractCell cell : row)
			{
				cell.stepSim();
			}
		}
		
	}

	public AbstractCell getCell(int x, int y) 
	{
		return world[x][y];
	}

	public void setCell(AbstractCell cell, Point pt)
	{
		world[pt.getX()][pt.getX()] = cell;
	}
	
	public void setCell(AbstractCell cell, int x, int y)
	{
		world[x][y] = cell;
	}
}
