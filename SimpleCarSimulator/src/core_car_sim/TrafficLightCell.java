package core_car_sim;

import java.awt.*;

public class TrafficLightCell extends AbstractInformationCell
{
	/**
	 * Auto generated serialID
	 */
	private static final long serialVersionUID = -686102171772509852L;

	public class TrafficLightCellInformation
	{
		public boolean redOn = true;
		public boolean yellowOn = false;
		public boolean greenOn = false;
		public Point stopAt;
	}
	
	private TrafficLightCellInformation lightSituation = new TrafficLightCellInformation();
	private int timeToChange = 2;
	private int currentTime = 0;
	
	public TrafficLightCell(Direction _faces, int _visibleFrom, Point roadEffectLocation)
	{
		super(_faces, _visibleFrom);
		lightSituation.stopAt = roadEffectLocation;
	}
	
	@Override
	public TrafficLightCellInformation getInformation()
	{
		return lightSituation;
	}

	@Override
	public void stepSim()
	{
		currentTime++;
		if (lightSituation.yellowOn)
		{
			if (lightSituation.redOn)
			{
				lightSituation.greenOn = true;
				lightSituation.redOn = false;
				lightSituation.yellowOn = false;
			}
			else
			{
				lightSituation.greenOn = false;
				lightSituation.redOn = true;
				lightSituation.yellowOn = false;	
			}
		}
		else if (currentTime > timeToChange)
		{
			lightSituation.yellowOn = true;
			lightSituation.greenOn = false;
			currentTime = 0;
		}
		
	}

	@Override
	public void paintComponent(Graphics g)
	{
		if (lightSituation.greenOn)
		{
			g.setColor(Color.GREEN);
		}
		else if (lightSituation.redOn && !lightSituation.yellowOn)
		{
			g.setColor(Color.RED);
		}
		else if (!lightSituation.redOn && lightSituation.yellowOn)
		{
			g.setColor(Color.YELLOW);
		}
		else
		{
			g.setColor(Color.BLUE);
		}
		g.fillOval(0, 0, getWidth()-1, getHeight()-1);
	}

}
