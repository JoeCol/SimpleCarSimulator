package core_car_sim;

public class Point implements Comparable<Point>
{
	private int x;
	private int y;
	
	public Point(int _x, int _y)
	{
		x = _x;
		y = _y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public int compareTo(Point p) {
		if (x != p.getX())
		{
			return Integer.compare(x, p.getX());
		}
		return Integer.compare(y, p.getY());
	}
	
	@Override
	public boolean equals(Object p)
	{
		if (p.getClass() == getClass())
		{
			return (x == ((Point)p).getX()) && (y == ((Point)p).getY());
		}
		else
		{
			return false;
		}
	}

	public void moveDirection(Direction nextDirection)
	{
		switch (nextDirection)
		{
			case east:
				x++;
				break;
			case north:
				y--;
				break;
			case south:
				y++;
				break;
			case west:
				x--;
				break;
		}
	}

	//Loop around
	public void moveDirection(Direction nextDirection, int width, int height)
	{
		moveDirection(nextDirection);
		if (x < 0)
		{
			x = width - 1;
		}
		else if (x >= width)
		{
			x = 0;
		}
		
		if (y < 0)
		{
			y = height - 1;
		}
		else if (y >= height)
		{
			y = 0;
		}
	}
}
