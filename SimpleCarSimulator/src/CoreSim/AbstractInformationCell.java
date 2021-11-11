package CoreSim;

import java.util.ArrayList;

public abstract class AbstractInformationCell extends AbstractCell
{

	private ArrayList<Direction> faces = new ArrayList<Direction>();//Which way the information faces
	private ArrayList<Integer> visibleFrom = new ArrayList<Integer>();
	private int visibilityChange = 0;
	
	public AbstractInformationCell(ArrayList<Direction> _faces, ArrayList<Integer> _visibleFrom)
	{
		super(CellType.ct_information);
		faces = _faces;
		visibleFrom = _visibleFrom;
	}
	
	public int isVisibleFrom(Direction direction)
	{
		return Math.max(visibleFrom.get(faces.indexOf(direction)) - visibilityChange, 0); 
	}
	
	public void reduceVisibilityBy(int amount)
	{
		visibilityChange = amount;
	}
	
	public abstract Object getInformation();

}
