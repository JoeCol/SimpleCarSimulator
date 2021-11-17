package core_car_sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadWorld
{
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
						createdSim.addCell(new RoadCell(Direction.east, false, null, defaultSpeedLimit));
						break;
					case '<':
						createdSim.addCell(new RoadCell(Direction.west, false, null, defaultSpeedLimit));
						break;
					case '^':
						createdSim.addCell(new RoadCell(Direction.north, false, null, defaultSpeedLimit));
						break;
					case 'V':
						createdSim.addCell(new RoadCell(Direction.south, false, null, defaultSpeedLimit));
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
		return createdSim;
	}
}
