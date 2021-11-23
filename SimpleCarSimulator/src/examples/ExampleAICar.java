package examples;

import java.util.ArrayDeque;

import core_car_sim.AbstractCar;
import core_car_sim.AbstractCell;
import core_car_sim.Direction;
import core_car_sim.Point;

public class ExampleAICar extends AbstractCar
{
	private ArrayDeque<Direction> goLeft = new ArrayDeque<Direction>();

	public ExampleAICar(Point startPos, String imageLoc)
	{
		super(startPos, 2, imageLoc);
	}

	@Override
	protected void visibleWorldUpdate(AbstractCell[][] visibleWorld, Point location)
	{
		//Ignore everything
	}

	@Override
	protected ArrayDeque<Direction> getSimulationRoute()
	{
		goLeft.push(Direction.west);
		goLeft.push(Direction.west);
		return goLeft;
	}

	@Override
	protected boolean isFinished(Point point)
	{
		// Dont care where the car is when finished
		return false;
	}

}
