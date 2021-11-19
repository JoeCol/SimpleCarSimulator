package core_car_sim;

public interface CarAddedListener 
{
	AbstractCar createCar(String name);
	AbstractCar createCar(String name, String[] information);
}