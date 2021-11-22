package core_car_sim;

public interface CarAddedListener 
{
	AbstractCar createCar(String name, Point startingLoca);
	AbstractCar createCar(String name, Point startingLoca, String[] information);
}