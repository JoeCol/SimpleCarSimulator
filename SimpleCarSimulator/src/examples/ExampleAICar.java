package examples;

import java.util.ArrayDeque;

import core_car_sim.AbstractCar;
import core_car_sim.Direction;
import core_car_sim.Point;
import core_car_sim.WorldSim;

public class ExampleAICar extends AbstractCar
{
	private ArrayDeque<Direction> goLeft = new ArrayDeque<Direction>();

	public ExampleAICar(Point startPos, String imageLoc)
	{
		super(startPos, 2, imageLoc);
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

	@Override
	protected void visibleWorldUpdate(WorldSim visibleWorld, Point location)
	{
		// TODO Auto-generated method stub
		
	}

}
