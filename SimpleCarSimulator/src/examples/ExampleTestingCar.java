package examples;

import java.awt.Canvas;
import java.util.ArrayList;

import core_car_sim.AbstractCar;
import core_car_sim.AbstractCell;
import core_car_sim.Direction;
import core_car_sim.Point;

public class ExampleTestingCar extends AbstractCar
{
	boolean trafficLightRed;
	boolean atWhiteLine;
	
	ArrayList<Direction> directions = new ArrayList<Direction>();
	
	public ExampleTestingCar(Point startPos)
	{
		super(startPos, 0);
	}

	@Override
	protected void visibleWorldUpdate(AbstractCell[][] visibleWorld)
	{
		for (int x = 0; x < visibleWorld.length; x++)
		{
			for (int y = 0; y < visibleWorld[x].length; y++)
			{
				if (visibleWorld[x][y].getCellType() == ct_information)
				{
					
				}
			}
		}
	}

	@Override
	protected ArrayList<Direction> getSimulationRoute()
	{
		if (getSpeed() == 0 || (trafficLightRed && atWhiteLine))
		{
			directions.clear();
		}
		else if (directions.size() == 0)
		{
			directions.add(Direction.north);
		}
		return directions;
	}

	@Override
	protected boolean isFinished(Point point)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
