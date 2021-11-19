package core_car_sim;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldSim
{
	private AbstractCell[][] world;
	private ArrayList<CarAddedListener> carAddedListeners = new ArrayList<CarAddedListener>();
	private ArrayList<AbstractCar> cars = new ArrayList<AbstractCar>();
	private HashMap<AbstractCar, Point> carPositions = new HashMap<AbstractCar, Point>();
	private int width;
	private int height;
	private int visability = 3;
	
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
			carPositions.put(car, car.getStartingPosition());
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
			AbstractCell[][] visibleWorld = getVisibleWorldForPosition(carPositions.get(car));
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
			carPositions.put(car, finalPositions.get(car));
		}
		
	}

	private AbstractCell[][] getVisibleWorldForPosition(Point currentPosition) {
		AbstractCell[][] visWorld = new AbstractCell[visability * 2][visability * 2];
		int worldX;
		int worldY;
		for (int x = 0-visability; x < visability; x++)
		{
			for (int y = 0-visability; y < visability; y++)
			{
				worldX = currentPosition.getX() + x;
				worldY = currentPosition.getY() + y;
				if (worldX < 0 || worldX >= world.length || worldY < 0 || worldY >= world[0].length)
				{
					visWorld[x+visability][y+visability] = new NonVisibleCell();
				}
				else
				{
					visWorld[x+visability][y+visability] = world[worldX][worldY];
				}
			}
		}
		return visWorld;
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

	public void addCar(String name, Point point)
	{
		for (CarAddedListener cal : carAddedListeners)
		{
			AbstractCar createdCar = cal.createCar(name);
			cars.add(createdCar);
			carPositions.put(createdCar, point);
		}
	}

	public void addCar(String name, Point point, String[] info)
	{
		for (CarAddedListener cal : carAddedListeners)
		{
			AbstractCar createdCar = cal.createCar(name, info);
			cars.add(createdCar);
			carPositions.put(createdCar, point);
		}
		
	}

	public boolean allFinished()
	{
		for (AbstractCar car : cars)
		{
			if (!car.isFinished(carPositions.get(car)))
			{
				return false;
			}
		}
		return true;
	}
}
