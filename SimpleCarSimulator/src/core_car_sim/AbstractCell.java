package core_car_sim;

import javax.swing.JPanel;

/*
 * Abstract cell of the car simulator
 */

public abstract class AbstractCell extends JPanel
{
	/**
	 * Auto generated serial
	 */
	private static final long serialVersionUID = -1866861919859124549L;

	public enum CellType
	{
		ct_blank, //Non drivable
		ct_road, //Drivable
		ct_information, //Traffic lights, road signs etc
		ct_non_visible
	}
	private CellType cellType;
	public abstract void stepSim();
	
	public AbstractCell(CellType ct)
	{
		cellType = ct;
	}
	
	public CellType getCellType()
	{
		return cellType;
	}
	
	
}
