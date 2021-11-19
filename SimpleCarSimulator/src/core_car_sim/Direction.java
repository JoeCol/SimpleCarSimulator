package core_car_sim;

public enum Direction
{
	north("^"),
	south("V"),
	east(">"),
	west("<");
	
	private final String dir;
	private Direction(String s)
	{
		dir = s;
	}
	
	public String toString()
	{
		return dir;
	}
}