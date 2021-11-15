package core_car_sim;

import java.awt.Point;
import java.util.ArrayList;

public abstract class AbstractCar
{
	private Point startingPosition;
	private Point finishedPosition;
	private Point currentPosition;
	private int speed; 
	
	public AbstractCar(Point startPos, Point finishPosition, int startingSpeed)
	{
		finishedPosition = finishPosition;
		startingPosition = startPos;
		currentPosition = startPos;
		speed = startingSpeed;
	}
	
	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public void resetPosition()
	{
		currentPosition = startingPosition;
	}
	
	public void setPosition(Point point) 
	{
		// TODO Auto-generated method stub
		
	}

	protected abstract void visibleWorldUpdate(ArrayList<ArrayList<AbstractCell>> visibleWorld);

	protected abstract ArrayList<Direction> getSimulationRoute();

}
