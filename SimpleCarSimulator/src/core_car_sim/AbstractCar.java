package core_car_sim;

import java.util.ArrayList;

public abstract class AbstractCar
{
	private Point startingPosition;
	private Point finishedPosition;
	private Point currentPosition;
	private int speed; 
	
	protected abstract void visibleWorldUpdate(ArrayList<ArrayList<AbstractCell>> visibleWorld);
	protected abstract ArrayList<Direction> getSimulationRoute();
	
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

	public Point getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(Point startingPosition) {
		this.startingPosition = startingPosition;
	}

	public Point getFinishedPosition() {
		return finishedPosition;
	}

	public void setFinishedPosition(Point finishedPosition) {
		this.finishedPosition = finishedPosition;
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Point currentPosition) {
		this.currentPosition = currentPosition;
	}

}
