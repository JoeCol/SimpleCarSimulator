package core_car_sim;

import java.util.ArrayList;
import java.awt.Point;

public class TrafficLightCell extends AbstractInformationCell
{
	public class TrafficLightCellInformation
	{
		public boolean redOn = true;
		public boolean yellowOn = false;
		public boolean greenOn = false;
	}
	
	private Point stopLocation;
	private TrafficLightCellInformation lightSituation = new TrafficLightCellInformation();
	private int timeToChange = 2;
	private int currentTime = 0;
	
	public TrafficLightCell(Direction _faces, int _visibleFrom, Point roadEffectLocation)
	{
		super(_faces, _visibleFrom);
		stopLocation = roadEffectLocation;
	}
	
	@Override
	public TrafficLightCellInformation getInformation()
	{
		return lightSituation;
	}

	@Override
	public void stepSim()
	{
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
		else if (currentTime >= timeToChange)
		{
			lightSituation.yellowOn = true;
			lightSituation.greenOn = false;
		}
		currentTime++;
	}

	@Override
	public void drawCell()
	{
		// TODO Auto-generated method stub

	}

}
