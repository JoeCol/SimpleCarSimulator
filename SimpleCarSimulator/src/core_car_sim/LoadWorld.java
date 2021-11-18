package core_car_sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;

public class LoadWorld
{
	public static Direction charToDirection(char dir)
	{
		switch (dir)
		{
			case '>':
				return Direction.east;
			case '<':
				return Direction.west;
			case '^':
				return Direction.north;
			case 'V':
				return Direction.south;
		}
		return null;
	}
	
	public static Direction charToDirection(String dir)
	{
		return charToDirection(dir.charAt(0));
	}
	
	public static WorldSim loadWorldFromFile(BufferedReader reader, CarAddedListener cal) throws IOException
	{
		WorldSim createdSim = new WorldSim();
		createdSim.addCarAddedListener(cal);
		String x = reader.readLine();
		String y = reader.readLine();
		createdSim.setSize(Integer.parseInt(x), Integer.parseInt(y));
		int defaultSpeedLimit = Integer.parseInt(reader.readLine());
		createdSim.setDefaultSpeedLimit(defaultSpeedLimit);
		String line;
		ArrayList<Direction> tmp = new ArrayList<Direction>();
		for (int row = 0; row < createdSim.getHeight(); row++)
		{
			line = reader.readLine();
			for (int i = 0; i < line.length(); i++)
			{
				switch (line.charAt(i))
				{
					default:
					case '|':
					case '-':
						createdSim.addCell(new NonDrivingCell());
						break;
					case '>':
					case '<':
					case '^':
					case 'V':
						createdSim.addCell(new RoadCell(charToDirection(line.charAt(i)), false, null, defaultSpeedLimit));
						break;
					case '+':
						tmp.clear();
						tmp.add(Direction.north);
						tmp.add(Direction.south);
						tmp.add(Direction.east);
						tmp.add(Direction.west);
						createdSim.addCell(new RoadCell(tmp, false, null, defaultSpeedLimit));
						break;
				}
			}
		}
		line = reader.readLine();
		while (line != null)
		{
			String[] items = line.split(" ");
			switch (items[0].toLowerCase())
			{
				case "trl":
					int trlx = Integer.parseInt(items[1]);
					int trly = Integer.parseInt(items[2]);
					int stopsX = Integer.parseInt(items[3]);
					int stopsY = Integer.parseInt(items[4]);
					createdSim.replaceCell(new TrafficLightCell(charToDirection(items[5]), 3, new Point(stopsX, stopsY)), new Point(trlx, trly));
					break;
				case "car":
					
					break;
			}
			line = reader.readLine();
		}
		return createdSim;
	}
}
