package CoreSim;

/*
 * Abstract cell of the car simulator
 */

enum CellType
{
	ct_blank, //Non drivable
	ct_road, //Drivable
	ct_information //Traffic lights, road signs etc
}

public abstract class AbstractCell 
{
	private CellType cellType;
	public abstract CellType getCellType();
	public abstract void stepSim();
	
	public AbstractCell(CellType ct)
	{
		cellType = ct;
	}
	
	public abstract void drawCell();
}
