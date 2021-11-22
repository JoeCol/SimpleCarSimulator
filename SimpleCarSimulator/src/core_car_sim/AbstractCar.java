package core_car_sim;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class AbstractCar
{
	private Point startingPosition;
	private int speed;
	protected ImageIcon carIcon = null;
	
	protected abstract void visibleWorldUpdate(AbstractCell[][] visibleWorld);
	protected abstract ArrayList<Direction> getSimulationRoute();
	protected abstract boolean isFinished(Point point);
	
	public AbstractCar(Point startPos, int startingSpeed, String fileImage)
	{
		startingPosition = startPos;
		speed = startingSpeed;
		carIcon = new ImageIcon(fileImage);
	}
	
	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public Point getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(Point startingPosition) {
		this.startingPosition = startingPosition;
	}
	
	public ImageIcon getCarIcon()
	{
		return carIcon;
	}

}
