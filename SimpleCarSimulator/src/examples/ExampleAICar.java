package examples;

import java.util.ArrayList;

import core_car_sim.AbstractCar;
import core_car_sim.AbstractCell;
import core_car_sim.Direction;
import core_car_sim.Point;

public class ExampleAICar extends AbstractCar
{
	private ArrayList<Direction> goLeft = new ArrayList<Direction>();

	public ExampleAICar(Point startPos, String imageLoc)
	{
		super(startPos, 2, imageLoc);
		goLeft.add(Direction.west);
		goLeft.add(Direction.west);
	}

	@Override
	protected void visibleWorldUpdate(AbstractCell[][] visibleWorld)
	{
		//Ignore everything
	}

	@Override
	protected ArrayList<Direction> getSimulationRoute()
	{
		return goLeft;
	}

	@Override
	protected boolean isFinished(Point point)
	{
		// Dont care where the car is when finished
		return true;
	}

}
