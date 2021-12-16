package examples;

import java.util.ArrayDeque;

import core_car_sim.AbstractCar;
import core_car_sim.AbstractCell.CellType;
import core_car_sim.AbstractInformationCell;
import core_car_sim.AbstractInformationCell.InformationCell;
import core_car_sim.TrafficLightCell.TrafficLightCellInformation;
import core_car_sim.WorldSim;
import core_car_sim.Direction;
import core_car_sim.Point;
import core_car_sim.TrafficLightCell;

public class ExampleTestingCar extends AbstractCar
{
	boolean trafficLightRed;
	boolean atWhiteLine;
	boolean finished = false;
	Point finishedLocation;
	
	ArrayDeque<Direction> directions = new ArrayDeque<Direction>();
	
	public ExampleTestingCar(Point startPos, String imageLoc, Point finishLoca)
	{
		super(startPos, 0, imageLoc);
		setSpeed(1);
		finishedLocation = finishLoca;
	}

	@Override
	protected void visibleWorldUpdate(WorldSim visibleWorld, Point location)
	{
		for (int y = 0; y < visibleWorld.getHeight(); y++)
		{
			for (int x = 0; x < visibleWorld.getWidth(); x++)
			{
				if (visibleWorld.getCell(x, y).getCellType() == CellType.ct_information)
				{
					if (((AbstractInformationCell)visibleWorld.getCell(x, y)).getInformationType() == InformationCell.ic_trafficLight)
					{
						TrafficLightCellInformation tlci = ((TrafficLightCell)visibleWorld.getCell(x, y)).getInformation();
						trafficLightRed = tlci.redOn;
						Point visibleWorldStopPoint = new Point(x + tlci.stopAtReference.getX(), y + tlci.stopAtReference.getY());
						atWhiteLine = visibleWorldStopPoint == location;
					}
				}
			}
		}
	}

	@Override
	protected ArrayDeque<Direction> getSimulationRoute()
	{
		if (getSpeed() == 0 || (trafficLightRed && atWhiteLine) || finished)
		{
			directions.clear();
		}
		else if (directions.size() == 0)
		{
			directions.push(Direction.north);
		}
		return directions;
	}

	@Override
	protected boolean isFinished(Point point)
	{
		finished = point == finishedLocation;
		return finished;
	}

}
