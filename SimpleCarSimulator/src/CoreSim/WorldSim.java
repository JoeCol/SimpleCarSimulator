package CoreSim;

import java.util.ArrayList;

enum Direction
{
	north,
	south,
	east,
	west
}

public class WorldSim
{
	ArrayList<ArrayList<AbstractCell>> world = new ArrayList<ArrayList<AbstractCell>>();
}
