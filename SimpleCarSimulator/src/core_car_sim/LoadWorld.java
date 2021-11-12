package core_car_sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadWorld
{
	public WorldSim loadWorldFromFile(BufferedReader reader, CarAddedListener cal) throws IOException
	{
		WorldSim createdSim = new WorldSim();
		createdSim.addCarAddedListener(cal);
		createdSim.setSize(Integer.getInteger(reader.readLine()), Integer.getInteger(reader.readLine()));
		int defaultSpeedLimit = Integer.getInteger(reader.readLine());
		createdSim.setDefaultSpeedLimit(defaultSpeedLimit);
		String line;
		ArrayList<Direction> tmp = new ArrayList<Direction>();
		while ((line = reader.readLine()) != null)
		{
			for (int i = 0; i < line.length(); i++)
			{
				String nextChar = line.substring(i, i + 1);
				Integer nextInt = Integer.getInteger(nextChar);
				if (nextInt == null)
				{
					switch (line.charAt(i))
					{
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
				else
				{
					createdSim.addControlledCar(nextInt);
				}
			}
		}
		return createdSim;
	}
}
