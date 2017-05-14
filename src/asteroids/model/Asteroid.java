package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Asteroid extends MinorPlanet{
	public Asteroid(double x,double y,double velocityx, double velocityy, double radius) throws IllegalArgumentException,IllegalNumberException{
		super(x,y,velocityx,velocityy,radius);
		
	}
	
	/**
     * 
     * @return the lowerbound for the density of any Asteroid.
     */
	@Basic 
    public double getDensity(){
    	return this.density;
    }
    
    /**
     * Variable registering the lowerbound of the density of each Asteroid.
     */
    private final double density = 2.65*Math.pow(10, 12);
    
    public void collideWithShip(Ship ship) throws IllegalNumberException,IllegalArgumentException {
    	ship.die();
    }
}
