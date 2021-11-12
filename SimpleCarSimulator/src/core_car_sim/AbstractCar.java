package core_car_sim;

import java.awt.Point;

public abstract class AbstractCar
{
	private Point startingPosition;
	private Point currentPosition;
	private int speed; 
	
	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public AbstractCar(Point startPos, int startingSpeed)
	{
		startingPosition = startPos;
		currentPosition = currentPosition;
		speed = startingSpeed;
	}

	public void resetPosition()
	{
		currentPosition = startingPosition;
	}

}
