package CoreSim;

import java.util.ArrayList;

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

	@Override
	public void drawCell()
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

}
