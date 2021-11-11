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
	public abstract void stepSim();
	public abstract void drawCell();
	
	public AbstractCell(CellType ct)
	{
		cellType = ct;
	}
	
	public CellType getCellType()
	{
		return cellType;
	}
	
	
}
