package core_car_sim;

import java.awt.Color;
import java.awt.Graphics;

public class NonDrivingCell extends AbstractCell
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3155750680055909034L;
	
	public NonDrivingCell()
	{
		super(CellType.ct_blank);
	}

	@Override
	public void stepSim()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
