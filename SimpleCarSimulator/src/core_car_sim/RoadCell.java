package core_car_sim;

import java.util.ArrayList;
import java.awt.*;

/*
 * List of possible markings on the tarmac
 */
enum RoadMarking
{
	rm_Zebra,
	rm_Pelican,
	rm_HorizonalWhiteLine //Holds 
}

/*
 * Represents tarmac
 */

public class RoadCell extends AbstractCell
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3908736198953808153L;
	private ArrayList<Direction> travelDirection = new ArrayList<Direction>();
	private boolean pavement;
	private RoadMarking roadMarkings;
	private int speedLimit;
	
	public RoadCell(ArrayList<Direction> directions, boolean _pavement, RoadMarking markings, int _speedLimit)
	{
		super(CellType.ct_road);
		travelDirection.addAll(directions);
		pavement = _pavement;
		roadMarkings = markings;
		speedLimit = _speedLimit;
	}
	
	public RoadCell(Direction _direction, boolean _pavement, RoadMarking markings, int _speedLimit)
	{
		super(CellType.ct_road);
		travelDirection.add(_direction);
		pavement = _pavement;
		roadMarkings = markings;
		speedLimit = _speedLimit;
	}

	@Override
	public void stepSim()
	{
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Direction> getTravelDirection()
	{
		return travelDirection;
	}

	public boolean isPavement()
	{
		return pavement;
	}

	public RoadMarking getRoadMarkings()
	{
		return roadMarkings;
	}

	public int getSpeedLimit()
	{
		return speedLimit;
	}
	
	public void setMarking(RoadMarking rm)
	{
		roadMarkings = rm; 
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		if (roadMarkings == RoadMarking.rm_HorizonalWhiteLine)
		{	
			g.drawLine(0, 1, getWidth(), 1);
		}
		g.drawString(travelDirection.get(0).toString(), 5, g.getFontMetrics().getHeight() + 2);
	}
}
