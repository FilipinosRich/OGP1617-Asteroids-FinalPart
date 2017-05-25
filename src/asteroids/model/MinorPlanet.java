package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class MinorPlanet extends Entity {
	public MinorPlanet(double x, double y, double velocityx, double velocityy, double radius) throws IllegalNumberException, IllegalArgumentException {
		super(x,y,velocityx,velocityy,radius);
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
    
    /**
     * 
     * @return the lowerbound for the radius of each MinorPlanet.
     */
    public double getRadiusLowerBound(){
    	return this.radiusLowerBound;
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
    public void setLowerBound(double lowerbound){
    	if (! MinorPlanet.isValidLowerBound(lowerbound))
    		this.radiusLowerBound=5;
    	this.radiusLowerBound=lowerbound;
    }
    /*
     *  @return true if the radius of the MinorPlanet is larger than or equal to 10 and the radius is a valid number.
    *       | result == (radius>=10 && isValidNumber(radius)
    */
   @Raw
   public boolean canHaveAsRadius(double radius) {
   	return (radius >= 5 && isValidNumber(radius));
   }
    
    /**
     * 
     * @param lowerbound
     * 		  the lowerbound to check
     * @return true if the lowerbound of the radius for any MinorPlanet is greater than zero.
     * 		   | result == (lowerbound >0) 
     */
    private static boolean isValidLowerBound(double lowerbound) {
		return (lowerbound>=5);
	}

    /**
     * variable registering the lowerbound of the radius for each MinorPlanet.
     */
    private double radiusLowerBound=5;
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

    public abstract double getDensity();
    
}
