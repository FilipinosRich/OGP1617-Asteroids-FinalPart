package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class MinorPlanet extends Entity {
	public MinorPlanet(double x, double y, double velocityx, double velocityy, double radius) throws IllegalNumberException, IllegalArgumentException {
		super(x,y,velocityx,velocityy,radius);
		if (! canHaveAsRadius(radius))
			throw new IllegalNumberException(radius);
		this.radius = radius;
		setMass(mass);
	}
	 //methods about the radius of the MinorPlanet

    /**
     * @return the radius of this MinorPlanet.
     */
    @Basic @Raw @Immutable @Override
    public double getRadius() {
    	return this.radius;
    }

    /**
     * Check whether this MinorPlanet can have the given radius as its radius.
     *  
     * @param  radius
     *         The radius to check.
     * @return true if the radius of the MinorPlanet is larger than or equal to 10 and the radius is a valid number.
     *       | result == (radius>=10 && isValidNumber(radius)
     */
    @Raw
    public boolean canHaveAsRadius(double radius) {
    	return (radius >=MinorPlanet.radiusLowerBound && isValidNumber(radius));
    }
    
    /**
     * 
     * @return the lowerbound for the radius of each MinorPlanet.
     */
    public static double getLowerBound(){
    	return MinorPlanet.radiusLowerBound;
    }
    
    /**
     * set the lowerbound for the radius of each MinorPlanet to the given lowerbound
     * 
     * @param lowerbound
     * 		  the new lowerbound for any MinorPlanet.
     * @post  If the given lowerbound is a valid lowerbound for any MinorPlanet,
     *        the lowerbound of the radius of this new MinorPlanet is equal to the given lowerbound.
     *        Otherwise, the lowerbound is equal to 10.
     *       | if (isValidLowerbound(lowerbound))
     *       |   then new.getLowerbound() == lowerbound
     *       | else new.getLowerbound() == 10
     */
    public static void setLowerBound(double lowerbound){
    	if (! MinorPlanet.isValidLowerBound(lowerbound))
    		MinorPlanet.radiusLowerBound=10;
    	MinorPlanet.radiusLowerBound=lowerbound;
    }
    
    /**
     * 
     * @param lowerbound
     * 		  the lowerbound to check
     * @return true if the lowerbound of the radius for any MinorPlanet is greater than zero.
     * 		   | result == (lowerbound >0) 
     */
    private static boolean isValidLowerBound(double lowerbound) {
		return (lowerbound>0);
	}

	/**
     * Variable registering the radius of this MinorPlanet.
     */
    private final double radius;
   
    /**
     * variable registering the lowerbound of the radius for each MinorPlanet.
     */
    private static double radiusLowerBound=5;
    /**
     * @return the mass of this MinorPlanet.
     */
    @Basic @Raw
    public double getMass() {
    	return this.mass;
    }
    
    /**
     * Check whether the given mass is a valid mass for
     * any MinorPlanet.
     *  
     *  
     * @param  mass
     *         The mass to check.
     * @return true if the mass is greater than or equal to (4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity()
     *       | result == (mass>=(4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity())
     */
    public boolean isValidMass(double mass) {
    	return (mass>=((double)(4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity()));
    }
    
    /**
     * Set the mass of this MinorPlanet to the given mass.
     * 
     * @param  mass
     *         The new mass for this MinorPlanet.
     * @post   If the given mass is a valid mass for any MinorPlanet,
     *         the mass of this new MinorPlanet is equal to the given
     *         mass.
     *       | if (isValidMass(mass))
     *       |   then new.getMass() == mass
     */
    @Raw
    private void setMass(double mass) {
    	if (!isValidMass(mass)){
    		this.mass=(double)(4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();}
    	else {
    		this.mass = mass;
    	}     	
    }
    
	
	/**
     * 
     * @return the lowerbound for the density of any MinorPlanet.
     */
    public abstract double getDensity();
    
    /**
     * Variable registering the mass of this MinorPlanet.
     */
    private double mass;
    
  //method move
    
    /**
     * Change the position of the MinorPlanet based on the current position, current velocity and on a given time duration.
     * @param  duration
     *         the duration of the movement of this ship
     * @post   If the velocity of this ship is zero or the given duration is zero,
     * 		  the new position is equal to the current position of the ship.
     *         | new.getPosition() == this.getPosition()
     * @post	  If the velocity is not zero and the duration is greater than zero, the new position is given by
     *		  new.position=this.velocity*duration
     *		  | new.getPosition()== duration*this.getVelocity()
     * @throws IllegalNumberException
     *         the given duration is less than zero.
     *         | duration < 0
     */
     public void move(double duration) throws IllegalNumberException {
         if (duration < 0)
             throw new IllegalNumberException(duration);
         if ( (this.getSpeed() == 0) || (duration == 0) )
             super.setPosition(getPosition()[0],getPosition()[1]);
         else{
         super.setPosition(getPosition()[0] + (duration*getVelocity()[0]), getPosition()[1] + (duration*getVelocity()[1]));}
     }
}
