package CoreSim;

import java.io.BufferedReader;
import java.io.IOException;

public class LoadWorld
{
	public WorldSim loadWorldFromFile(BufferedReader reader) throws IOException
	{
		WorldSim createdSim = new WorldSim();
		createdSim.setSize(Integer.getInteger(reader.readLine()), Integer.getInteger(reader.readLine()));
		int defaultSpeedLimit = Integer.getInteger(reader.readLine());
		String line;
		while ((line = reader.readLine()) != null)
		{
			for (int i = 0; i < line.length(); i++)
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
						break;
					
				}
			}
		}
		return createdSim;
	}
}
