package examples;

import java.awt.Canvas;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core_car_sim.AbstractCar;
import core_car_sim.AbstractCell;
import core_car_sim.Direction;
import core_car_sim.Point;

public class ExampleAICar extends AbstractCar
{
	private ArrayList<Direction> goLeft;

	public ExampleAICar(Point startPos)
	{
		super(startPos, 2);
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
