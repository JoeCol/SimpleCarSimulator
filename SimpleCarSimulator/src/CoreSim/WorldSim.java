package CoreSim;

import java.util.ArrayList;

enum Direction
{
	north,
	south,
	east,
	west
}

public class WorldSim
{
	ArrayList<ArrayList<AbstractCell>> world = new ArrayList<ArrayList<AbstractCell>>();
	private int width;
	private int height;
	
	private int trackCellHeight = 0; //
	private int trackCellWidth = 0;

	public void setSize(int x, int y)
	{
		width = x;
		height = y;
	}

	public void addNonDrivingCell()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void wipeWorld()
	{
		trackCellHeight = 0;
		trackCellWidth = 0;
		world.clear();
	}

	public void addCell(AbstractCell cell)
	{
		if (trackCellWidth % width == 0)
		{
			world.add(new ArrayList<AbstractCell>());
			trackCellWidth = 0;
			trackCellHeight++;
		}
		world.get(trackCellHeight).add(cell);
	}
}
