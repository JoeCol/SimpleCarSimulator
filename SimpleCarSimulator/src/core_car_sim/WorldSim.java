package core_car_sim;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

enum Direction
{
	north,
	south,
	east,
	west
}

interface CarAddedListener {
	AbstractCar addControlledCar(int i, int x, int y);
}

public class WorldSim
{
	private ArrayList<ArrayList<AbstractCell>> world = new ArrayList<ArrayList<AbstractCell>>();
	private ArrayList<CarAddedListener> carAddedListeners = new ArrayList<CarAddedListener>();
	private ArrayList<AbstractCar> cars = new ArrayList<AbstractCar>();
	private int width;
	private int height;
	
	private int trackCellHeight = 0; //
	private int trackCellWidth = 0;
	private int defaultSpeedLimit = 1; //Measured in squares per sim step

	public void setSize(int x, int y)
	{
		width = x;
		height = y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void wipeWorld()
	{
		trackCellHeight = 0;
		trackCellWidth = 0;
		world.clear();
	}

	public void addCell(AbstractCell cell)
	{
		if (trackCellWidth % width == 0)
		{
			world.add(new ArrayList<AbstractCell>());
			trackCellWidth = 0;
			trackCellHeight++;
		}
		world.get(trackCellHeight).add(cell);
	}

	public void addControlledCar(int nextInt)
	{
		ArrayList<Direction> tmp = new ArrayList<Direction>();
		tmp.add(Direction.north);
		tmp.add(Direction.south);
		tmp.add(Direction.east);
		tmp.add(Direction.west);
		addCell(new RoadCell(tmp, false, null, defaultSpeedLimit));
		for (CarAddedListener cal : carAddedListeners)
		{
			cal.addControlledCar(nextInt, trackCellWidth, trackCellHeight);
		}
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
			ArrayList<ArrayList<AbstractCell>> visibleWorld;
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
			car.setPosition(finalPositions.get(car));
		}
		
	}

	private void updateCells()
	{
		for (ArrayList<AbstractCell> row : world)
		{
			for (AbstractCell cell : row)
			{
				cell.stepSim();
			}
		}
		
	}
}
