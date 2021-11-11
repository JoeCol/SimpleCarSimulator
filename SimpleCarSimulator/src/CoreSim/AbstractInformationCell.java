package CoreSim;

import java.util.ArrayList;

public abstract class AbstractInformationCell extends AbstractCell
{

	private ArrayList<Direction> faces = new ArrayList<Direction>();//Which way the information faces
	
	public AbstractInformationCell()
	{
		super(CellType.ct_information);
	}

}
