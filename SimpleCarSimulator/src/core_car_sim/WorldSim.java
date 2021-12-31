package core_car_sim;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import core_car_sim.AbstractCell.CellType;

public class WorldSim
{
	private AbstractCell[][] world;
	private ArrayList<CarAddedListener> carAddedListeners = new ArrayList<CarAddedListener>();
	private ArrayList<AbstractCar> cars = new ArrayList<AbstractCar>();
	private HashMap<AbstractCar, Point> carPositions = new HashMap<AbstractCar, Point>();
	private int width;
	private int height;
	private int visability = 3;

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
	
	public int speedLimit(int x, int y)
	{
		if (getCell(x, y).getCellType() == CellType.ct_road)
		{
			return ((RoadCell)getCell(x, y)).getSpeedLimit();
		}
		return 0;
	}
	
	private AbstractCar getCarAtPosition(int x, int y)
	{
		for (AbstractCar c : cars)
		{
			if (carPositions.get(c).compareTo(new Point(x, y)) == 0)
			{
				return c;
			}
		}
		return null;
	}
	
	public boolean containsCar(int x, int y)
	{
		for (Point p : carPositions.values())
		{
			if (p.getX() ==  x && p.getY() == y)
			{
				return true;
			}
		}
		return false;
	}

	private void carMovementPhase()
	{
		//Let car view world
		HashMap<AbstractCar, Deque<Direction>> allRoutes = new HashMap<AbstractCar, Deque<Direction>>();
		for (AbstractCar car : cars)
		{
			if (!car.isCrashed())
			{
				WorldSim visibleWorld = getVisibleWorldForPosition(carPositions.get(car), true);
				Point carReferencePoint = new Point(visability,visability);
				car.visibleWorldUpdate(visibleWorld, carReferencePoint);
				Deque<Direction> route = car.getSimulationRoute();
				allRoutes.put(car, route);
			}
		}
		//for each route entry add position as key, then add car id as pair
		HashMap<AbstractCar, Point> finalPositions = carPositions;
		//Check for invalid routes / crashes 
		HashMap<Point, AbstractCar> checkPositions = new HashMap<Point, AbstractCar>();
		boolean finishedChecking = false;
		HashSet<AbstractCar> carsFinished = new HashSet<AbstractCar>();
		while (!finishedChecking)
		{
			//Positions only need to be checked at a point in time
			checkPositions.clear();
			for (AbstractCar car : cars)
			{
				if (!car.isCrashed() && !allRoutes.get(car).isEmpty())
				{
					Point currentPosition = finalPositions.get(car);
					Direction nextDirection = allRoutes.get(car).pop();
					currentPosition.moveDirection(nextDirection, getWidth(), getHeight());
					
					if (checkPositions.containsKey(currentPosition))
					{
						//Crash
						car.setCrashed(true);
						checkPositions.get(currentPosition).setCrashed(true);
					}
					else if (!getCell(currentPosition.getX(),currentPosition.getY()).isDriveable())
					{
						//Crash
						car.setCrashed(true);
					}
					else
					{
						checkPositions.put(currentPosition, car);
					}
					finalPositions.put(car, currentPosition);
				}
				else
				{
					carsFinished.add(car);
				}
			}
			finishedChecking = carsFinished.size() == cars.size();
		}
		
		//Move to new position
		for (AbstractCar car : cars)
		{
			carPositions.put(car, finalPositions.get(car));
		}
		
	}

	
	private WorldSim getVisibleWorldForPosition(Point currentPosition, boolean looped)
	{
		WorldSim visWorld = new WorldSim((visability * 2) + 1,(visability * 2) + 1);
		visWorld.carAddedListeners = carAddedListeners;
		visWorld.cars = cars;
		//car positions need to be adjusted to visible world
		visWorld.carPositions = new HashMap<AbstractCar, Point>();
		int worldX;
		int worldY;
		for (Entry<AbstractCar, Point> cp : carPositions.entrySet())
		{
			//Adjust car positions based on current cars position
			visWorld.carPositions.put(cp.getKey(), new Point((cp.getValue().getX() - currentPosition.getX()) + visability, 
																(cp.getValue().getY() - currentPosition.getY()) + visability));
		}
		for (int x = 0-visability; x <= visability; x++)
		{
			for (int y = 0-visability; y <= visability; y++)
			{
				worldX = currentPosition.getX() + x;
				worldY = currentPosition.getY() + y;
				if (worldX < 0 || worldX >= getWidth() || worldY < 0 || worldY >= getHeight())
				{
					if (!looped)
					{
						visWorld.setCell(new NonVisibleCell(), x+visability, y+visability);
					}
					else
					{
						worldX = (worldX < 0) ? getWidth() + worldX : worldX % getWidth();
						worldY = (worldY < 0) ? getHeight() + worldY : worldY % getHeight();
						visWorld.setCell(getCell(worldX, worldY), x+visability, y+visability);
					}
				}
				else
				{
					visWorld.setCell(getCell(worldX, worldY), x+visability, y+visability);
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
		setCell(cell, pt.getX(), pt.getY());
	}
	
	public void setCell(AbstractCell cell, int x, int y)
	{
		world[x][y] = cell;
	}

	public void addCar(String name, Point point)
	{
		for (CarAddedListener cal : carAddedListeners)
		{
			AbstractCar createdCar = cal.createCar(name, point);
			cars.add(createdCar);
			carPositions.put(createdCar, point);
		}
	}

	public void addCar(String name, Point point, String[] info)
	{
		for (CarAddedListener cal : carAddedListeners)
		{
			AbstractCar createdCar = cal.createCar(name, point, info);
			cars.add(createdCar);
			carPositions.put(createdCar, point);
		}
		
	}

	public boolean allFinished()
	{
		for (AbstractCar car : cars)
		{
			if (!car.isCrashed() && !car.isFinished(carPositions.get(car)))
			{
				return false;
			}
		}
		return true;
	}

	public ArrayList<AbstractCar> getCars() 
	{
		return cars;
	}

	public Point getCarPosition(AbstractCar car) 
	{
		return carPositions.get(car);
	}
}
