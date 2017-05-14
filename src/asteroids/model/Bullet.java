package asteroids.model;

import asteroids.model.Entity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class Bullet extends Entity {
	   
    /**
     * Initialize this new bullet with given x- and y-coordinates for the position, x-and y-coordinate
     * for the velocity, given orientation and the given radius.
     *
     * @param  x
     * 	      the x-coordinate of the position for this new bullet.
     * @param  y
     *         the y-coordinate of the position for this new bullet.
     * @param  velocityx
     *         The x-coordinate of the velocity for this new bullet.
     * @param  velocityy
     *         The y-coordinate of the velocity for this new bullet.
     * @param  orientation
     *         The orientation for this new bullet.
     * @param  radius
     *         The radius for this new bullet.
     * @throws IllegalNumberException
     * 		  The given number is not valid
     *         | ! isValidNumber(number)
     *@throw   IllegalNumberException
     *		  the given radius is not valid.
     *	      | ! canHaveAsRadius(radius)
     * @pre    The given orientation must be a valid orientation for any bullet.
     *         | isValidorientation(orientation)
     * @post   The position of this new bullet is equal to a double[] with coordinates the given x and y.
     *         |new.getPosition()=={x,y}.
     * @post   If the given x-coordinate and y-coordinate are valid coordinates for the velocity of any bullet 
     * 		  and the speed is valid for any bullet, the velocity of this new bullet is equal to 
     * 		  a double[] with coordinates the given x and y.
     *         Otherwise, the velocity of this new bullet is equal to {speedlimit/sqrt(2) , speedlimit/sqrt(2)}.
     *         | if (isValidNumber(velocityx) && IsValidNumber(velocityy) && isValidSpeed(new.speed) )
     *         |   	then new.getVelocity() == {velocityx , velocityy}
     *         | else new.getVelocity() == {speedlimit/Math.sqrt(2) , speedlimit/Math.sqrt(2)}
     * @post   The orientation of this new bullet is equal to the given orientation.
     *         | new.getOrientation() == orientation
     * @post   The radius of this new bullet is equal to the given radius.
     * 	       | new.getRadius()== radius
     */
	public Bullet(double x, double y, double velocityx, double velocityy, double radius)
			throws IllegalNumberException{
		super(x,y,velocityx,velocityy,radius);

	}  
 
	/**
	 * Check whether the given number is a valid number for any bullet
	 * @param number
	 * 		 The number to check.
	 * @return true if the number is not infinitive and not NaN.
	 *              |result == (number != infinity && number!= -infinity && number!=NaN)
	 */
	public static boolean isValidNumber(double number) {
		return number != Double.POSITIVE_INFINITY && number != Double.NEGATIVE_INFINITY && ! Double.isNaN(number);
	}
	
	//methods about the radius of the bullet



	/**
	 * Check whether this bullet can have the given radius as its radius.
	 *  
	 * @param  radius
	 *         The radius to check.
	 * @return true if the radius of the bullet is larger than or equal to 10 and the radius is a valid number.
	 *       | result == (radius>=radiusLowerbound && isValidNumber(radius)
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return (radius >=Bullet.radiusLowerBound && isValidNumber(radius));
	}
	

	
	/**
	 * @return the lower bound of the radius of this bullet
	 */
    public static double getLowerBound(){
    	return Bullet.radiusLowerBound;
    }
    
    /**
     * set the lower bound of the radius for this bullet to the given lowerbound if it is valid
     * @param lowerbound
     * 		  the new lowerbound
     */
    public static void setLowerBound(double lowerbound){
    	if (! Bullet.isValidLowerBound(lowerbound))
    		Bullet.radiusLowerBound=10;
    	Bullet.radiusLowerBound=lowerbound;
    }
    
    /**
     * checks whether the given lowerbound is valid
     * @param lowerBound
     * 		  the lowerbound to check
     * @return
     * @see implementation
     */
    private static boolean isValidLowerBound(double lowerBound) {
		return (lowerBound>0);
	}
    
    /**
     * Variable registering the lower bound of the radius for this bullet
     */
    private static double radiusLowerBound=1;
    

    public boolean isValidMass(double mass) {
    	return (isValidNumber(mass));
    }
    /**
     * variable registering the mass of this bullet
     */
    private double mass;
    public double getMass() {
    	return mass;
    }
    /** 
     * @return the density of this bullet
     */
    public double getDensity(){
    	return this.density;
    }
    
    /**
     * Method registering the density
     */
    private final double density=7.8*Math.pow(10, 12);
    
    
    //methods associating ship
    
    /**
	 * Return the ship to which this bullet belongs.
	 */
	@Basic @Raw
	public Ship getShip() {
		return this.ship;
	}

	/**
	 * Check whether this bullet can have the given ship as
	 * its ship.
	 * 
	 * @param  ship
	 * 		   The ship to check.
	 * @return If this bullet is terminated, true if and only if the
	 *         given ship is not effective.
	 *       | if (this.isTerminated())
	 *       |   then result == (ship == null)
	 * @return If this bullet is not terminated, true if and only if the given
	 *         ship is effective and not yet terminated.
	 *       | if (! this.isTerminated())
	 *       |   then result ==
	 *       |    ( (ship != null) &&
	 *       |     (! ship.isTerminated())) || (ship==null || this.getShip()==null)
	 */
	@Raw
	public boolean canHaveAsShip(Ship ship) {
		if (this.isTerminated())
			return ship == null;
		if (ship==null || this.getShip()==null)
			return true;
		if (this.getShip()!=ship && this.getShip()!=null)
			return false;
		return((!ship.isTerminated() && this.getWorld()==null));
	}

	/**
	 * Check whether this bullet has a proper ship.
	 * 
	 * @return True if and only if this bullet can have its ship as its
	 *         ship, and if the ship of this bullet is either not effective
	 *         or if it has this bullet as one of its bullets.
	 *       | result ==
	 *       |   canHaveAsShip(getShip()) &&
	 *       |   ( (getShip() == null) || getShip().hasAsBullet(this))
	 */
	@Raw
	public boolean hasProperShip() {
		return canHaveAsShip(getShip())
				&& ((getShip() == null) || (getShip().hasAsBullet(this)));
	}

	/**
	 * Set the ship of this bullet to the given ship.
	 * 
	 * @param  ship
	 *         The new ship for this bullet.
	 * @post   The world of this bullet is the same as the
	 *         given ship.
	 *       | new.getShip() == ship
	 * @throws IllegalArgumentException
	 *         This bullet cannot have the given ship as its ship.
	 *       | ! canHaveAsShip(ship)
	 */
	@Raw
	public void setShip(Ship ship) throws IllegalArgumentException {
		if (!this.canHaveAsShip(ship) || this.getWorld()!=null)
			throw new IllegalArgumentException();
		this.ship = ship;
	}

	/**
	 * Variable referencing the ship to which this bullet belongs.
	 */
	private Ship ship;
	
	//methods associating bullet with world//
	
	/**
	 * Check whether this bullet can have the given world as
	 * its world.
	 * 
	 * @param  world
	 * 		   The world to check.
	 * @return If this bullet is terminated, true if and only if the
	 *         given world is not effective.
	 *       | if (this.isTerminated())
	 *       |   then result == (world == null)
	 * @return If this bullet is not terminated, true if and only if the given
	 *         world is effective and not yet terminated.
	 *       | if (! this.isTerminated())
	 *       |   then result ==
	 *       |     (world != null) &&
	 *       |     (! world.isTerminated() && this.getShip()==null ) ||(world==null || this.getWorld()==null)
	 */
	@Raw @Override
	public boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return world == null;
		if (world==null || this.getWorld()==null)
			return true;
		if (this.getWorld()!=world && this.getWorld()!=null)
			return false;
		return(!world.isTerminated() &&this.getShip()==null);
	}

	
	/**
	 * Set the world of this bullet to the given world.
	 * 
	 * @param  world
	 *         The new world for this bullet.
	 * @post   The world of this bullet is the same as the
	 *         given world.
	 *       | new.getWorld() == world
	 * @throws IllegalArgumentException
	 *         This bullet cannot have the given world as its world.
	 *       | ! canHaveAsWorld(world)
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (!canHaveAsWorld(world) || this.getShip()!=null)
			throw new IllegalArgumentException("Inappropriate world!");
		this.world = world;	

	}
	
	// methods associating bullet with its source//
	
	/**
	 * Sets the source of this bullet
	 * @param ship
	 */
	public void setSource(Ship ship) {
		this.source=ship;
	}
	/**
	 * 
	 * @return the source of this bullet
	 */
	public Ship getSource(){
		return this.source;
	}
	
	/**
	 * Variable registering the source of this ship
	 */
	private Ship source;
	
   // methods resolving bullets collisions//
	
	/**
	 *  resolve the collision between bullet and its source. 
	 *  the given ship loads this bullet back on ship if the given ship is its source.
	 * @param ship
	 * 		  the ship to collide with
	 * @throws IllegalNumberException
	 * 			when the bullet can't be loaded on the ship
	 */
   public void bulletCollidesWithOwnShip(Ship ship) throws IllegalNumberException {
       if ( this.getSource()==ship && this.getShip() == null && this.getWorld()!=null){
    	   ship.loadBullet(this);
    	   this.setCounter(0);}
   }
   
   /**
    * resolve the collision between this bullet and another entity in its world 
    * when bullet has another source, not null, than the given entity. the bullet and entity both die.
    * @param entity
    * 		the entity to collide with
    * @throws IllegalNumberException
    * 		  when the bullet or entity can't die
    */
   public void bulletCollidesWithOtherEntity(Entity entity) throws IllegalNumberException {
       if (this.getSource()!=entity && this.getSource()!=null)
           this.die();
           try{((Ship)entity).die();}
           catch (ClassCastException e){((Bullet) entity).die();}
   }
   

  /**
   * resolve the collision between this bullet and a boundary. Checks with which boundary the bullet collides
   * and gives the bullet a new velocity based on the boundary. if the counter is 3, the bullet dies. 
   *
   * @throws IllegalNumberException
   * 		when the bullet won't die
   */
   public void collideWithBoundary() throws IllegalNumberException{
	   if (this.getTimeToCollisionBoundary()<=0 && this.getWorld().apparentlyWithinBoundaries(this)){
	   if ((this.collidesWithHeightLowerbound() && this.collidesWithWidthLowerbound()) || 
			   (this.collidesWithHeightUpperbound() && this.collidesWithWidthUpperbound()) ||
			   (this.collidesWithHeightLowerbound() && this.collidesWithWidthUpperbound()) || 
			   (this.collidesWithHeightUpperbound() && this.collidesWithWidthLowerbound())){
		   this.setVelocity(-this.getVelocity()[0], -this.getVelocity()[1]);}	   
	   	else if (this.collidesWithHeightUpperbound() || this.collidesWithHeightLowerbound()){
	   		this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);}	   
	   	else if (this.collidesWithWidthLowerbound() || this.collidesWithWidthUpperbound()){
	   		this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);}
	   if (this.getCounter()==3)
		   this.die();}
	}
    
   /**
    * resolve the collision between a boundary and this bullet given the collision. checks with which boundary
    * the bullet collides and changes the velocity based on this boundary.
    * @param collision
    * 		 the collision to take place
    */
   public void collideWithBoundary(Collision collision){
	  
	   if ((this.collidesWithHeightLowerbound(collision) && this.collidesWithWidthLowerbound(collision)) || 
			   (this.collidesWithHeightUpperbound(collision) && this.collidesWithWidthUpperbound(collision)) ||
			   (this.collidesWithHeightLowerbound(collision) && this.collidesWithWidthUpperbound(collision)) || 
			   (this.collidesWithHeightUpperbound(collision) && this.collidesWithWidthLowerbound(collision))){
		   this.setVelocity(-this.getVelocity()[0], -this.getVelocity()[1]);
		   this.setCounter(this.getCounter()+1);}	   
	   	else if (this.collidesWithHeightUpperbound(collision) || this.collidesWithHeightLowerbound(collision)){
	   		this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);
	   		this.setCounter(this.getCounter()+1);}	   
	   	else if (this.collidesWithWidthLowerbound(collision) || this.collidesWithWidthUpperbound(collision)){
	   		this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
	   		this.setCounter(this.getCounter()+1);}
	}
   
   /**
    * 
    * @return the counter of how many times this bullet has already collided with a boundary
    */
   public int getCounter(){
	   return this.counter;
   }
   
   /**
    * set the counter to the given counter
    * @param counter
    * 		the new counter
    */
   public void setCounter(int counter){
	   this.counter=counter;
   }
   
   /**
    * variable referencing the counter of how many boundarycollisions of this bullet
    */
   private int counter;
   
   /**
    * the bullet is terminated and removed from its world
    * @throws IllegalArgumentException
    * @throws IllegalNumberException
    */
   public void die() throws IllegalArgumentException, IllegalNumberException{
       if (this.getWorld()!=null)
    	   this.getWorld().removeBullet(this);
	   this.terminate();
       
   }
   

}
