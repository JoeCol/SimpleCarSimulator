package core_car_sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
		String widthStr = reader.readLine();
		String heightStr = reader.readLine();
		WorldSim createdSim = new WorldSim(Integer.parseInt(widthStr), Integer.parseInt(heightStr));
		createdSim.addCarAddedListener(cal);
		int defaultSpeedLimit = Integer.parseInt(reader.readLine());
		createdSim.setDefaultSpeedLimit(defaultSpeedLimit);
		String line;
		ArrayList<Direction> tmp = new ArrayList<Direction>();
		for (int y = 0; y < createdSim.getHeight(); y++)
		{
			line = reader.readLine();
			for (int x = 0; x < createdSim.getWidth(); x++)
			{
				switch (line.charAt(x))
				{
					default:
					case '|':
					case '-':
						createdSim.setCell(new NonDrivingCell(), x, y);
						break;
					case '>':
					case '<':
					case '^':
					case 'V':
						createdSim.setCell(new RoadCell(charToDirection(line.charAt(x)), false, null, defaultSpeedLimit), x, y);
						break;
					case '+':
						tmp.clear();
						tmp.add(Direction.north);
						tmp.add(Direction.south);
						tmp.add(Direction.east);
						tmp.add(Direction.west);
						createdSim.setCell(new RoadCell(tmp, false, null, defaultSpeedLimit), x, y);
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
					int trlx = Integer.parseInt(items[2]);
					int trly = Integer.parseInt(items[3]);
					int stopsX = Integer.parseInt(items[4]);
					int stopsY = Integer.parseInt(items[5]);
					createdSim.setCell(new TrafficLightCell(charToDirection(items[6]), 3, new Point(stopsX, stopsY), new Point(trlx - stopsX, trly - stopsY)), new Point(trlx, trly));
					//((RoadCell)createdSim.getCell(stopsX, stopsY)).setMarking(RoadMarking.rm_HorizonalWhiteLine);
					break;
				case "car":
					if (items.length <= 4)
					{
						createdSim.addCar(items[1], new Point(Integer.parseInt(items[2]), Integer.parseInt(items[3])));
					}
					else
					{
						createdSim.addCar(items[1], new Point(Integer.parseInt(items[2]), Integer.parseInt(items[3])), Arrays.copyOfRange(items, 4, items.length));
					}
					break;
			}
			line = reader.readLine();
		}
		return createdSim;
	}
}
