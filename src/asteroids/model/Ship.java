package asteroids.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import asteroids.statements.ReturnStatement;
import be.kuleuven.cs.som.annotate.*;


/**
 *
 * A class of ships with a position, a velocity, a radius, a mass and an orientation.
 *
 * @invar The x-and y-coordinates of the position of each ship must be valid 
 *        | isValidPosition(x,y)
 * @invar The x-and y-coordinates for the velocity of each ship must be valid for any
          ship. The calculated speed of the ship must be a valid speed for any ship. 
 *        | isValidVelocity(xVelocity,yVelocity)
 *        | isValidSpeed(new.speed)
 * @invar The orientation of each ship must be a valid orientation for any
 *        ship.
 *        | isValidorientation(getorientation())
 * @invar  Each ship can have its radius as radius.
 *        | canHaveAsRadius(this.getRadius())
 *
 * @invar  The mass of each ship must be a valid mass for any
 *         ship.
 *        | isValidMass(getMass())
 * @invar   Each ship must have proper bullets.
 *        | hasProperBullets()
 */

public class Ship extends Entity{
   
    /**
    * Initialize this new ship as a non-terminated ship with  no bullets yet, the thruster off and the given x- and y-coordinates for the position, x-and y-coordinate
    * for the velocity, given orientation, the given mass and the given radius.
    *
    * @param  x
    *         the x-coordinate of the position for this new ship.
    * @param  y
    *         the y-coordinate of the position for this new ship.
    * @param  velocityx
    *         The x-coordinate of the velocity for this new ship.
    * @param  velocityy
    *         The y-coordinate of the velocity for this new ship.
    * @param  mass
	*         The mass for this new ship.
	* @param  orientation
    *         The orientation for this new ship.
    * @param  radius
    *         The radius for this new ship.
    * @param  mass
    * 		  the mass for this new ship.
    * @throws IllegalNumberException
    * 		  One of the given numbers for the position is not valid
    *         | ! isValidPosition(x,y)
    *@throw   IllegalNumberException
    *		  the given radius is not valid.
    *	      | ! canHaveAsRadius(radius)
    * @pre    The given orientation must be a valid orientation for any ship.
    *         | isValidorientation(orientation)
    * @post   The position of this new ship is equal to a double[] with coordinates the given x and y.
    *         | new.getPosition()=={x,y}.
    * @post   If the given x-coordinate and y-coordinate are valid coordinates for the velocity of any ship 
    * 		  and the speed is valid for any ship, the velocity of this new ship is equal to 
    * 		  a double[] with coordinates the given x and y.
    *         Otherwise, the velocity of this new ship is equal to {speedLimit/sqrt(2) , speedLimit/sqrt(2)}.
    *         | if (isValidVelocity(velocityx,velocityy) && isValidSpeed(new.speed) )
    *         |   	then new.getVelocity() == {velocityx , velocityy}
    *         | else new.getVelocity() == {speedLimit/Math.sqrt(2) , speedLimit/Math.sqrt(2)}
    * @post   The orientation of this new ship is equal to the given orientation.
    *         | new.getOrientation() == orientation
    * @post   The radius of this new ship is equal to the given radius.
    *         | new.getRadius()== radius
    * @post   If the given mass is a valid mass for any ship,
	*         the mass of this new ship is equal to the given
	*         mass. Otherwise, the mass of this new ship is equal
	*         to 4/3*Math.PI*Math.pow(this.getRadius(),3)*1.42*10^12.
	*       | if (isValidMass(mass))
	*       |   then new.getMass() == mass
	*       |   else new.getMass() == (double)4/3*Math.PI*Math.pow(this.getRadius(),3)*
	* @post   This new ship has no bullets yet.
	*       | new.getNbBullets() == 0
	* @post the thruster of the ship isn't on.
	* 		| thrustoff()
	*/

	@Raw
    public Ship(double x, double y, double velocityx, double velocityy, double radius, double orientation,double mass)
    		throws IllegalNumberException,IllegalArgumentException{
    	super(x,y,velocityx,velocityy,radius);
        setOrientation(orientation);
    	if (! isValidMass(mass))
    		this.mass = ((double)4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
    	this.setMass(mass);
    	this.thrustOff();
    }  
    

    
    //methods about the orientation of the ship
    
    /**
     * @Return the orientation of this ship.
     */
    @Basic @Raw
    public double getOrientation() {
        return this.orientation;
    }
 
    /**
     * Check whether the given orientation is a valid orientation for
     * any ship.
     *  
     * @param  orientation
     *          The orientation to check.
     * @return  true if the orientation of the ship is between 0 and 2*pi.
     *          | result == (orientation >= 0 && orientation <=2pi && isValidNumber(orientation))
     */
    public static boolean isValidOrientation(double orientation) {
        return ((orientation >= 0) && (orientation <= 2*Math.PI)) &&(isValidNumber(orientation));
    }
 
    /**
     * Set the orientation of this ship to the given orientation.
     *
     * @param  orientation
     *         The new orientation for this ship.
     * @pre    The given orientation must be a valid orientation for any
     *         ship.
     *         | isValidorientation(orientation)
     * @post   The orientation of this ship is equal to the given orientation.
     *         | new.getOrientation() == orientation
     */
    @Raw
    public void setOrientation(double orientation) {
        assert isValidOrientation(orientation);
        this.orientation = orientation;
    }
 
   /**
    * Variable registering the orientation of this ship.
    */
    private double orientation;
   
   
    //methods about the radius of the ship

    /**
     * Check whether this ship can have the given radius as its radius.
     *  
     * @param  radius
     *         The radius to check.
     * @return true if the radius of the ship is larger than or equal to 10 and the radius is a valid number.
     *       | result == (radius>=10 && isValidNumber(radius)
     */
    @Raw
    public  boolean canHaveAsRadius(double radius) {
    	return (radius >=Ship.radiusLowerBound && isValidNumber(radius));
    }
    
/*    *//**
     * 
     * @param lowerbound
     * 		  the lowerbound to check
     * @return true if the lowerbound of the radius for any ship is greater than zero.
     * 		   | result == (lowerbound >0) 
     *//*
    private static boolean isValidLowerBound(double lowerbound) {
		return (lowerbound>0);
	}*/


   
    /**
     * variable registering the lowerbound of the radius for each ship.
     */
    private static double radiusLowerBound=10;

    
    // methods about the mass of the ship.
    
    /**
     * @return the mass of this ship.
     */
    @Basic @Raw
    public double getMass() {
    	return this.mass;
    }
    
    /**
     * Check whether the given mass is a valid mass for
     * any ship.
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
     * Set the mass of this ship to the given mass.
     * 
     * @param  mass
     *         The new mass for this ship.
     * @post   If the given mass is a valid mass for any ship,
     *         the mass of this new ship is equal to the given
     *         mass.
     *       | if (isValidMass(mass))
     *       |   then new.getMass() == mass
     */
    @Raw
    private void setMass(double mass) {
    	if (!isValidMass(mass)){
    		this.mass=(double)(4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();}
    	else
    		{this.mass = mass;}     	
    }
    
    /**
     * 
     * @return the lowerbound for the density of any ship.
     */
    public double getDensity(){
    	return Ship.density;
    }
    /**
     * Variable registering the mass of this ship.
     */
    private double mass;
    
    /**
     * Variable registering the lowerbound of the density of each ship.
     */
    private final static double density=1.42*Math.pow(10, 12);
    
    
    

    
    //method turn
    
    /**
     * Turn the ship by adding a given angle to the current orientation of this ship.
     * @param angle
     *      the angle to be added to the current orientation.
     * @pre the sum of the current orientation and the given angle must be a valid orientation.
     *      | isValidOrientation(angle+orientation)
     * @post the new orientation is equal to the sum of the old orientation and the given angle.
     *      |new.orientation == getOrientation()+angle
     */
    public void turn(double angle) {
        double NewAngle = (angle + this.getOrientation());
        assert (isValidOrientation(NewAngle));
        this.orientation = NewAngle;
    }
 
    //method thrust
    /**
     * Turn the thruster of this ship on.
     * @post the thruster of the ship is enabled.
     * 		 |new.thrusterEnabled == true
     */
    public void thrustOn() {
        this.thrusterEnabled = true;
    }
    
    /**
     *  Turn the thruster of this ship off.
     *  @post the thruster of this shif is disabled.
     *        | new.thrusterEnabled == false
     */
    public void thrustOff() {
        this.thrusterEnabled = false;
    }
     
    /**
     *  variable registering the state of the thruster of this ship.
     */
    private boolean thrusterEnabled = false;
    
    
    /**
     * change the velocity of this ship based on the current velocity, current orientation, current acceleration and a given amount.
     *
     * @param amount
     *        amount which changes the velocity to a new velocity.
     * @post  If the given amount is negative, the new amount is zero.
     *        | if (amount < 0)
     *        |   then amount == 0
     * @post The new  x-coordinate of the velocity of this ship is set to this.getVelocity()[0]+getAcceleration()*amount*cos(this.orientation).
     * 		 The new y-coordinate of the velocity of this ship is set to this.getVelocity()[1]+getAcceleration()*amount*sin(this.orientation).
     *        | new.getVelocity()[0]=this.getVelocity()[0]+amount*cos(this.getOrientation())*this.getAcceleration().
     *        | new.getVelocity()[1]=this.getVelocity()[1]+amount*sin(this.getOrientation())*this.getAcceleration().
     */
   
    public void thrust(double amount) {
    	if (this.thrusterEnabled==true){
    		if (amount < 0)
    			amount = 0;
    		calculateAcceleration();
    		double a=this.getAcceleration();
        	this.setVelocity(this.getVelocity()[0] + a*Math.cos(getOrientation())*amount,this.getVelocity()[1] + a*Math.sin(getOrientation())*amount);}
    }
    
    /**
     * 
     * @return the acceleration of this ship.
     */
    @Basic @Raw
    public double getAcceleration(){
    	this.calculateAcceleration();
    	return this.acceleration;
    }
    
    /**
     * calculates the acceleration based on the force and mass of this ship.
     * @post if force/this.getMass()>=0 the new acceleration is equal to force/this.getMass().
     * 		 otherwise the new acceleration is equal to zero.
     *       | if ( force/this.getMass() <0)
     *       |	 then new.acceleration == 0
     *       | else 
     *       |     new.acceleration == force/this.getMass()
     */
    private void calculateAcceleration(){
    	double calculatedAcceleration= (double) force/this.getMass();
    	if (calculatedAcceleration<0)
    		this.acceleration=0;
    	else {this.acceleration= calculatedAcceleration;}
    }
    
    /**
     * 
     * @return the state of the thruster.
     */
    public boolean getStateThruster(){
    	return this.thrusterEnabled;
   }

    /**
     *  variable registering the acceleration of this ship.
     */
	private double acceleration;
	
	/**
	 * variable registering the force of any ship.
	 */
    private static final double force=1.1*Math.pow(10, 18);
    
    
    
    //methods associating bullet//
    
    
	/**
	 * Check whether this ship has the given bullet as one of its
	 * bullets.
	 * 
	 * @param  bullet
	 *         The bullet to check.
	 */
	@Basic
	@Raw
	public boolean hasAsBullet(@Raw Bullet bullet) {
		return bullets.contains(bullet);
	}

	/**
	 * Check whether this ship can have the given bullet
	 * as one of its bullets.
	 * 
	 * @param  bullet
	 *         The bullet to check.
	 * @return True if and only if the given bullet is effective
	 *         and that bullet is a valid bullet for a ship.
	 *       | result ==
	 *       |   (bullet != null) &&
	 *       |   Bullet.isValidShip(this)
	 */
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		return (bullet != null) && (bullet.canHaveAsShip(this)) &&  !bullets.contains(bullet) ;
	}

	/**
	 * Check whether this ship has proper bullets attached to it.
	 * 
	 * @return True if and only if this ship can have each of the
	 *         bullets attached to it as one of its bullets,
	 *         and if each of these bullets references this ship as
	 *         the ship to which they are attached.
	 *       | for each bullet in Bullet:
	 *       |   if (hasAsBullet(bullet))
	 *       |     then canHaveAsBullet(bullet) &&
	 *       |          (bullet.getShip() == this)
	 */
	public boolean hasProperBullets() {
		for (Bullet bullet : bullets) {
			if (!canHaveAsBullet(bullet))
				return false;
			if (bullet.getShip() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of bullets associated with this ship.
	 *
	 * @return  The total number of bullets collected in this ship.
	 *        | result ==
	 *        |   card({bullet:Bullet | hasAsBullet({bullet)})
	 */
	public int getNbBullets() {
		return bullets.size();
	}

	/**
	 * Add the given bullet to the set of bullets of this ship.
	 * 
	 * @param  bullet
	 *         The bullet to be added.
	 * @throws IllegalArgumentException 
	 * 		    The given bullet has world not null and this can have the given bullet as its bullet.
	 *       | (bullet != null) && (bullet.getShip() == this)
	 * @post   This ship has the given bullet as one of its bullets.
	 *       | new.hasAsBullet(bullet)
	 */
	public void addBullet(Bullet bullet){
		if (this.canHaveAsBullet(bullet) && bullet.getWorld()==null)
			bullets.add(bullet);
		else 
			throw new IllegalArgumentException();
		
	}
	
	//loadbullet//
	
	/**
	 * load the given collection of bullets on the ship.
	 * @param bullets
	 * 		  the bullets to be loaded on the ship
	 * @throws IllegalArgumentException
	 * 		   This ship can not have one of the given bullets in the collection as its bullet.
	 * 		  | (!canHaveAsBullet(bullet) )
	 * @throws IllegalNumberException
	 * 		   when for the bullets in the collection: if the bullet collides and the collision can not be resolved
	 * @post  if the radius of a bullet of the collection is bigger than the radius of this ship, this ship and the bullet collide.
	 * 		  | if (bullet.getRadius()>this.getRadius())
	 * 		  | 	bullet.bulletCollidesWithOtherEntity(this)
	 * @post  if the world of the bullet is not null, the bullet is removed from his world.
	 *		  | if (bullet.getWorld()!=null)
	 *		  |		bullet.getWorld.removeBullet(bullet)
	 * @post  for each bullet in the collection: 
	 * 		  the world of the bullet is set to null, the new ship of the bullet is this, the bullet is added to the list of bullets of this ship,
	 * 		  the mass of this ship is set to the sum of the mass of this ship and the bullets mass. 
	 * 		  the new position of the bullet is the position of this ship.
	 * 		  | bullet.setWorld(null);
     *		  | bullet.setShip(this);				
	 *		  | this.addBullet(bullet);
	 *		  | new.setMass(this.getMass()+bullet.getMass());
	 *		  | bullet.setPosition(this.getPosition()[0], this.getPosition()[1]) 
	 */
	public void loadBullets(@Raw Collection<Bullet> bullets) throws IllegalArgumentException, IllegalNumberException {
		for (Bullet bullet : bullets){
			if( ! this.canHaveAsBullet(bullet) )
				throw new IllegalArgumentException();}
		for (Bullet bullet:bullets){
				if (bullet.getRadius()>this.getRadius())
					this.getWorld().setCollisionBetweenBulletAndOtherEntity(bullet, this);
				if (bullet.getWorld()!=null)
					bulletsToRemove.add(bullet);}
		for (Bullet bullet : bulletsToRemove)
			bullets.remove(bullet);
		for (Bullet bullet:bullets){
			if (bullet.getRadius()<=this.getRadius()){
				bullet.setWorld(null);
				bullet.setShip(this);				
				this.addBullet(bullet);
				this.setMass(this.getMass()+bullet.getMass());
				bullet.setPosition(this.getPosition()[0], this.getPosition()[1]);}}
		this.getWorld().resolveBulletCollision();
		this.getWorld().setCollisionBetweenBulletAndOtherEntity(null, null);
		
	}
	
	/**
	 * Variable referencing the set of bullets to remove from the world of the bullet when loaded on this ship.
	 * 
	 * @invar  The referenced set is effective.
	 *       | bulletsToRemove != null
	 * @invar  Each bullet registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each bullet in bulletsToRemove:
	 *       |   ( (bullet != null) &&
	 *       |     (! bullet.isTerminated()) )
	 */     
	private Set<Bullet> bulletsToRemove =new HashSet<Bullet>();
	

	/**
	 * load the given bullet on the ship.
	 * @param bullet
	 * 		  the bullet to be loaded on the ship
	 * @throws IllegalArgumentException
	 * 		   This ship can not have the given bullet in the collection as its bullet .
	 * 		  | ( !canHaveAsBullet(bullet))
	 * @throws IllegalNumberException
	 * 		   when the bullet collides and the collision can not be resolved
	 * @post  if the radius of a bullet is bigger than the radius of this ship, this ship and the bullet collide.
	 * 		  | if (bullet.getRadius()>this.getRadius())
	 * 		  | 	bullet.bulletCollidesWithOtherEntity(this)
	 * @post  if the world of the bullet is not null, the bullet is removed from his world.
	 *		  | if (bullet.getWorld()!=null)
	 *		  |		bullet.getWorld.removeBullet(bullet)
	 * @post  the world of the bullet is set to null, the new ship of the bullet is this, the bullet is added to the set of bullets of this ship,
	 * 		  the mass of this ship is set to the sum of the mass of this ship and the bullets mass. 
	 * 		  the new position of the bullet is the position of this ship.
	 * 		  | bullet.setWorld(null);
     *		  | bullet.setShip(this);				
	 *		  | this.addBullet(bullet);
	 *		  | new.setMass(this.getMass()+bullet.getMass());
	 *		  | bullet.setPosition(this.getPosition()[0], this.getPosition()[1]) 
	 */
	public void loadBullet(Bullet bullet) throws IllegalArgumentException,IllegalNumberException{		
		if( ! this.canHaveAsBullet(bullet) )
			throw new IllegalArgumentException();
		else {
			if (bullet.getRadius()>this.getRadius())
				bullet.bulletCollidesWithOtherEntity(this);
			if (bullet.getRadius()<=this.getRadius()){
				if (bullet.getWorld()!=null)
					bullet.getWorld().removeBullet(bullet);
				bullet.setWorld(null);
				bullet.setShip(this);				
				this.addBullet(bullet);
				this.setMass(this.getMass()+bullet.getMass());}}
	}
	
	/**
	 * Remove the given bullet from the set of bullets of this ship.
	 * 
	 * @param  bullet
	 *         The bullet to be removed.
	 * @pre    This ship has the given bullet as one of
	 *         its bullets, and the given bullet does not
	 *         reference any ship.
	 *       | this.hasAsBullet(bullet) &&
	 *       | (bullet.getShip() == null)
	 * @post   This ship no longer has the given bullet as
	 *         one of its bullets.
	 *       | ! new.hasAsBullet(bullet)
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException{
		if (this.hasAsBullet(bullet)){
		bullets.remove(bullet);
		this.setMass(this.getMass()-bullet.getMass());}
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Variable referencing a set collecting all the bullets
	 * of this ship.
	 * 
	 * @invar  The referenced set is effective.
	 *       | bullets != null
	 * @invar  Each bullet registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each bullet in bullets:
	 *       |   ( (bullet != null) &&
	 *       |     (! bullet.isTerminated()) )
	 */
	private final Set<Bullet> bullets = new HashSet<Bullet>();   
	
	
	//fireBullet()//
	
	/**
	 * fire a bullet from the given ship in the world where this ship is set.distance between ship and bullet is 3 km
	 * @throws IllegalNumberException
	 * 		   when the bullet can't die properly
	 * @post  if the world of this ship is not null and the set of bullets is not empty: the bullet is removed from the ship
	 * 		  the source of the bullet is set to this. the ship of the bullet is set to null. 
	 * 		  the new position of the bullet is 
	 * 		  {this.getPosition()[0]+Math.cos(this.getOrientation())*(this.getRadius()+bullet.getRadius()+3),this.getPosition()[1]+Math.sin(this.getOrientation())*(this.getRadius()+bullet.getRadius()+3)}
	 * 		  the new velocity of the bullet is set to {250*Math.cos(this.getOrientation()), 250*Math.sin(this.getOrientation())}
	 * 		  | this.removeBullet(bullet);
	 *		  | bullet.setSource(this);
	 *		  | bullet.setShip(null);
	 *        | bullet.setPosition(this.getPosition()[0]+Math.cos(this.getOrientation())*(this.getRadius()+bullet.getRadius()+3),this.getPosition()[1]+Math.sin(this.getOrientation())*(this.getRadius()+bullet.getRadius()+3))
	 *        | bullet.setVelocity(250*Math.cos(this.getOrientation()), 250*Math.sin(this.getOrientation()))
	 * @post   if the new position of the bullet is outside of the worlds boundaries, the bullet dies. Else, the bullet is added to the world and the bullets world is set to this world.
	 *		  |	if (! this.getWorld().canHaveAsWidthCoordinate(bullet) || !this.getWorld().canHaveAsHeightCoordinate(bullet))
	 *	      |		bullet.die();
	 *		  | else
	 *		  | 	bullet.setWorld(this.getWorld());
	 *		  |		this.getWorld().addBullet2(bullet);
	 * @post if entities overlap with the bullet, they collide
	 * 		  | for (Entity entity ; this.getWorld.getEntities())
	 * 		  |		if (bullet.overlap(entity)
	 * 		  |			bullet.bulletCollidesWithOtherEntity(entity);
	 * @post if the bullet overlaps with this ship,the bullet is loaded on this ship
	 * 		|if bullet.overlap(this)
	 * 		|	bullet.bulletCollidesWithOwnShip(this)			
	 */
	public void fireBullet() throws IllegalNumberException {
		Object[] bulletsList= new Array[2];
		if (this.getWorld()!=null && !bullets.isEmpty())
			{bulletsList=(this.getBullets()).toArray();
			Bullet bullet= (Bullet) bulletsList[0];
			this.removeBullet(bullet);
			bullet.setSource(this);
			bullet.setShip(null);			
			double x= Math.cos(this.getOrientation());
			double y= Math.sin(this.getOrientation());
			double z= (this.getRadius()+bullet.getRadius()+3);			
			bullet.setPosition(this.getPosition()[0]+x*z,this.getPosition()[1]+y*z);
			bullet.setVelocity(250*Math.cos(this.getOrientation()), 250*Math.sin(this.getOrientation()));			
						
			if (! this.getWorld().canHaveAsWidthCoordinate(bullet) || !this.getWorld().canHaveAsHeightCoordinate(bullet))
				bullet.die();
			else{
				bullet.setWorld(this.getWorld());
				this.getWorld().addBullet2(bullet);
				for (Entity entity : this.getWorld().getEntities()) {
					if (bullet.overlap(entity) && entity!=bullet.getSource() && entity!=bullet)
						this.getWorld().setCollisionBetweenBulletAndOtherEntity(bullet, entity);
					else if (bullet.overlap(entity) && entity==bullet.getSource())
						this.getWorld().setCollisionBetweenBulletAndSource(bullet);
					}
				this.getWorld().resolveBulletCollision();
				this.getWorld().setCollisionBetweenBulletAndOtherEntity(null, null);
				this.getWorld().setCollisionBetweenBulletAndSource(null);}}

			
	}
	
	/**
	 * 
	 * @return the set of bullets.
	 */
	public Set<Bullet> getBullets(){
		return this.bullets;
	}
	
	//getDistanceBetween//
	
    /**
     * Get the distance between this entity and a given entity.
     * @param  entity
     *         the other entity of which we need to get the distance from this entity.
     * @return 0
     *         If the given entity is equal to this entity, the distance is zero.
     *         | distance == zero
     * @return distance
     *         returns the calculated distance between this entity and the given entity.
     *         | distance == Math.sqrt(Math.pow(this.getPosition().get(0)-entity.getPosition().get(0), 2)
     *           +Math.pow(this.getPosition().get(1)-entity.getPosition().get(1),2))-this.getRadius()-entity.getRadius();
     * @throws IllegalNumberException
     *         The given distance isn't a valid number for any entity.
     *         ( ! isValidNumber(distance))
     */
    public double getDistanceBetween(Entity entity1,Entity entity2) throws IllegalNumberException {
        double distance=Math.sqrt(Math.pow(entity1.getPosition()[0]-entity2.getPosition()[0], 2)
                +Math.pow(entity1.getPosition()[1]-entity2.getPosition()[1],2))-entity1.getRadius()-entity2.getRadius();
        if (! isValidNumber(distance))
            throw new IllegalNumberException(distance);
        if (entity1==entity2)
            return 0;
        return distance;
    }
 
   //method overlap
    /**
     * Determine if this entity overlaps with the given entity.
     * @param   entity
     *          The given entity which may or may not overlap with this entity.
     * @return  true if the distance between this entity and the given entity is less than 0
     *          or if this entity is equal to the given entity.
     *          | (this.getDistanceBetween(entity) <= 0 )
     * @throws  IllegalNumberException
     *          The distance between this entity and a given entity is not a valid number.
     *          | (! isValidNumber(getDistanceBetween(entity))
     */  
    public boolean overlap(Entity entity1,Entity entity2) throws IllegalNumberException {
        return (getDistanceBetween(entity1,entity2) < 0);
    }
   
    //methods concerning getTimeCollision
   
    /**
     * Return when, if ever, two space entitys collide.
     * @param entity
     * @return Positive infinity
     *         if the scalar product of the velocity vector and the position vector
     *         is greater than or equal to 0,
     *         meaning the entitys won't ever collide.
     * @return Positive infinity
     *         if the scalar product of the velocity vector and the position vector squared
     *         - the product of the length of the velocity vector squared and (the length of the position vector squared
     *         - the sum of the radii squared), is less than or equal to 0,
     *         meaning the entitys won't ever collide.
     * @return The time needed for the two entitys to collide,
     *         by collide meaning that the distance between this entity and a given entity is equal to the sum of the two radii.
     *         |timeToCollision == -(this.scalarProductVeloPos(entity)
     *         | + Math.sqrt(this.calculatedD(entity))) / this.lengthVelocitySquared(entity)
     * @throws IllegalArgumentException
     *         if the two entitys already overlap.
     *         if (overlap(entity))
     * @throws IllegalNumberException
 
     */
    public double getTimeToCollision(Entity entity1, Entity entity2) throws IllegalNumberException,IllegalArgumentException {
        if ( overlap(entity1,entity2))
            throw new IllegalArgumentException();
        if (scalarProductVeloPos(entity1,entity2) >= 0)
            return Double.POSITIVE_INFINITY;
        if (( calculatedD(entity1,entity2) <= 0))
            return Double.POSITIVE_INFINITY;
        return -( scalarProductVeloPos(entity1,entity2) + Math.sqrt(calculatedD(entity1,entity2))) / lengthVelocitySquared(entity1,entity2);
    }
   
    /**
     * calculate the position vector between the two positions
     * @param entity
     * @return  a double[] list {x,y} where x is the position vector between the x-coordinates of this entity and a given entity
     *          and y is the position vector between the y-coordinates of this entity and a given entity.
     *          | newposition == (entity.getPosition()[0] - this.getPosition()[0], entity.getPosition()[1] - this.getPosition[1])
     */
    public double[] positionVector(Entity entity1, Entity entity2) {
        double [] positionVector = {entity2.getPosition()[0] - entity1.getPosition()[0],entity2.getPosition()[1] - entity1.getPosition()[1]};
        return positionVector;
    }
   
    /**
     * calculate the velocity vector between the two velocities
     * @param  entity
     * @return a double[] (x,y) where x is the velocity vector between this entity and a given entity in x direction
     *         and y is the velocity vector between this entity and a given entity in the y direction.
     *         | newvelocity == (entity.getVelocityx() - this.getVelocityx(), entity.getVelocityy() - this.getVelocityy())
     */
    public double[] velocityVector(Entity entity1, Entity entity2) {
        double[] velocityVector=new double[2];
        velocityVector[0] = entity2.getVelocity()[0] - entity1.getVelocity()[0];
        velocityVector[1] = entity2.getVelocity()[1] - entity1.getVelocity()[1];
        return velocityVector;
    }
   
    /**
     * calculate the scalar product of the position vector with itself
     * @param entity
     * @return the length of the vector between the center of this entity and a given entity squared
     *         | length == Math.pow(differenceInPosition(entity)[0], 2) + Math.pow(differenceInPosition(entity)[1], 2);
     * @throws IllegalNumberException
     *         if the calculated length is not valid.
     *         | ( ! isValidNumber(length))
     */
    public double lengthPositionSquared(Entity entity1, Entity entity2) throws IllegalNumberException {
        double length=Math.pow(positionVector(entity1, entity2)[0], 2) + Math.pow(positionVector(entity1,entity2)[1], 2);
        if  ( ! isValidNumber(length))
            throw new IllegalNumberException(length);
        return  length;
    }
   
    /**
     * calculate the scalar product of the velocity vector with itself
     * @param entity
     * @return the length of the velocity vector between the center of this entity and a given entity squared
     *         | differenceVeSq == Math.pow(differenceInVelocity(entity)[0], 2) + Math.pow(differenceInVelocity(entity)[1], 2);
     * @throws IllegalNumberException
     *         if the calculated length is not valid.
     *         | ( ! isValidNumber(number))
     */
    public double lengthVelocitySquared(Entity entity1 ,Entity entity2) throws IllegalNumberException {
            lengthVeSq = Math.pow(velocityVector(entity1, entity2)[0], 2) + Math.pow(velocityVector(entity1,entity2)[1], 2);
        if (!isValidNumber(lengthVeSq))
                throw new IllegalNumberException(lengthVeSq);
        return lengthVeSq; 
    }
   
    /**
     * variable registering the the length of the velocity vector squared
     */
    private double lengthVeSq;
 
   
    /**
     * calculate the scalar product of the velocity vector and the position vector
     * @param  entity
     * @return the velocity vector in the x-direction times the position vector on the x-axis
     *         + the velocity vector in the y-direction times the position vector on the y-axis
     *         |differenceVeLo == ((differenceInVelocity(entity)[0] * differenceInPosition(entity)[0])
     *         |+ (differenceInVelocity(entity)[1] * differenceInPosition(entity)[1]))
     * @throws IllegalNumberException
     *         if the calculated number is not valid.
     *         | ( ! isValidNumber(number))
     */
    public double scalarProductVeloPos(Entity entity1 ,Entity entity2) throws IllegalNumberException{
        differenceVePos = ((velocityVector(entity1 ,entity2)[0] *positionVector(entity1 ,entity2)[0])
                + (velocityVector(entity1, entity2)[1] * positionVector(entity1,entity2)[1]));
        if (!isValidNumber(differenceVePos))
                throw new IllegalNumberException(differenceVePos);
        return differenceVePos;
    }
   
    /**
     * variable registering the product of the velocity vector and the position vector.
     */
    private double differenceVePos;
   
    /**
     * calculate the sum of the radii squared
    * @param entity
     * @return the sum of the radii squared.
     *         | sigmasquared == Math.pow(this.getRadius() + entity.getRadius(),2);
     * @throws IllegalNumberException
     *         if the calculated number is not valid.
     *         | ( ! isValidNumber(number))
     */
    public double sigmasquared(Entity entity1, Entity entity2)throws IllegalNumberException{
        sigmasquared = Math.pow(entity1.getRadius() + entity2.getRadius(),2);
        if (!isValidNumber(sigmasquared))
                throw new IllegalNumberException(sigmasquared);
        return sigmasquared;
    }
   
    /**
     * variable registering the sum of the radii squared.
     */
    private double sigmasquared;
   
    /**
     * calculate d
     * @param entity
     * @return ( the scalar product of the velocity vector and the position vector ) squared
     *         - the product of length of the velocity vector squared
     *         and (the length of the position vector squared - the sum of the radii squared)
     *         |calculatedD == Math.pow((differenceVeloLoc(entity)),2)
     *         |- ((differenceVelocitySquared(entity)) * ((differenceLocationSquared(entity) - sigmaSquared(entity))))
     * @throws IllegalNumberException
     *         if the calculated number is not valid.
     *         | ( ! isValidNumber(number))            
     */
    public double calculatedD(Entity entity1 , Entity entity2) throws IllegalNumberException {
        try{
            calculatedD = Math.pow((scalarProductVeloPos(entity1, entity2)),2) -
                    ((lengthVelocitySquared(entity1,  entity2)) * ((lengthPositionSquared(entity1, entity2) - sigmasquared(entity1,entity2))));
        }catch (IllegalNumberException e){
            throw new IllegalNumberException(calculatedD);
            }
        return calculatedD;
    }
   
    /**
     * variable registering the calculated D.
     */
    private double calculatedD;
   
    //methods concerning getCollisionPosition
   
    /**
     * variable registering the center of this entity while colliding
     */
    private double[] thisCoordCollision;
   
    /**
     * variable registering the center of the other entity while colliding
     */
    private double[] entityCoordCollision;
   
    /**
     * calculate the center of the circles at the time of collision
     * @param  entity
     * @post   set the double[] thisCoordCollision to this.getPosition() + (getTimeToCollision(entity) * this.getVelocity());
     *         |this.thisCoordCollision==this.getPosition() + (getTimeToCollision(entity) * this.getVelocity())
     * @post   set the double[] entityCoordCollision to entity.getPosition() + (getTimeToCollision(entity) * entity.getVelocity());
     *         |this.entityCoordCollision==entity.getPosition() + (getTimeToCollision(entity) * entity.getVelocity())
     * @throws IllegalNumberException
     */
    public void calculateCollision(Entity entity1 ,Entity entity2) throws IllegalNumberException {
        double[] coordcollthis=new double[2];
        double[] coordcollentity=new double[2];
        coordcollthis[0] = entity1.getPosition()[0] + (getTimeToCollision(entity1,entity2) * entity1.getVelocity()[0]);
        coordcollthis[1] = entity1.getPosition()[1] + (getTimeToCollision(entity1, entity2) * entity1.getVelocity()[1]);
        coordcollentity[0] = entity2.getPosition()[0] + (getTimeToCollision(entity1, entity2) * entity2.getVelocity()[0]);
        coordcollentity[1] = entity2.getPosition()[1] + (getTimeToCollision(entity1,entity2) * entity2.getVelocity()[1]);
        this.thisCoordCollision=coordcollthis;
        this.entityCoordCollision=coordcollentity;
    }
   
    /**
     * get the position of the collision
     * @param  entity
     * @return null
     *         if the time before the collision is infinity.
     *         |(getTimeToCollision(entity)==Double.POSITIVE_INFINITY)  
     * @return collisionPosition
     *         the position where the two entities collide
     *         | collisionPosition= thisCoordCollision+direction * (this.getRadius()/(this.getRadius()+entity.getRadius()));
     * @throws IllegalArgumentException
     *         if the two entities already overlap
     *         | (this.overlap(entity))
     * @throws IllegalNumberException
     *         if one of the elements of the position of the collision is negative or NaN
     *         | (collisionPosition[0]==Double.NEGATIVE_INFINITY || collisionPosition[0]==Double.NaN)
     *         | (collisionPosition[1]==Double.NEGATIVE_INFINITY || collisionPosition[1]==Double.NaN)
     */
    public double[] getCollisionPosition(Entity entity1, Entity entity2) throws IllegalArgumentException,IllegalNumberException{
    	if (overlap(entity1, entity2))
    		throw new IllegalArgumentException();
        if (getTimeToCollision(entity1, entity2)==Double.POSITIVE_INFINITY)
            return null;
        calculateCollision(entity1,entity2);
        double[] direction=new double[2];
        direction[0]=entityCoordCollision[0]-thisCoordCollision[0];
        direction[1]=entityCoordCollision[1]-thisCoordCollision[1];
        double[] colposition= new double[2];
        colposition[0]= thisCoordCollision[0]+direction[0] * (entity1.getRadius()/(entity1.getRadius()+entity2.getRadius()));
        colposition[1]= thisCoordCollision[1]+direction[1] * (entity1.getRadius()/(entity1.getRadius()+entity2.getRadius()));
        if (colposition[0]==Double.NEGATIVE_INFINITY || colposition[0]==Double.NaN)
            throw new IllegalNumberException(colposition[0]);
        if (colposition[0]==Double.NEGATIVE_INFINITY || colposition[0]==Double.NaN)
            throw new IllegalNumberException(colposition[1]);
        this.collisionPosition=colposition;
        return this.collisionPosition;
    }
   
 
   /**
    * variable registering the position of the collision.
    */
    private double[] collisionPosition;
	
    
    // methods about collisions of this ship//
    
    /**
     * collide with a given ship
     * @param ship
     * 		  the ship to collide with		
     * @throws IllegalNumberException
     * @post the new velocity of this ship is set to {this.getVelocity()[0] + this.calculateJx(ship)/this.getMass(),this.getVelocity()[1] + this.calculateJy(ship)/this.getMass()}
     * 		|this.setVelocity(this.getVelocity()[0] + this.calculateJx(ship)/this.getMass(),this.getVelocity()[1] + this.calculateJy(ship)/this.getMass())
     * @post the new velocity of the given ship is set to {this.getVelocity()[0] - this.calculateJx(ship)/this.getMass(),this.getVelocity()[1] - this.calculateJy(ship)/this.getMass()}
     * 		|ship.setVelocity(this.getVelocity()[0] - this.calculateJx(ship)/this.getMass(),this.getVelocity()[1] - this.calculateJy(ship)/this.getMass())
     */
	public void collideWithShip(Ship ship) throws  IllegalNumberException{	
		double newVelocityxThis=this.getVelocity()[0] + this.calculateJx(ship)/this.getMass();
		double newVelocityyThis=this.getVelocity()[1] + this.calculateJy(ship)/this.getMass();
		double newVelocityxShip=ship.getVelocity()[0] - this.calculateJx(ship)/ship.getMass();
		double newVelocityyShip=ship.getVelocity()[1] - this.calculateJy(ship)/ship.getMass();		
		this.setVelocity(newVelocityxThis  , newVelocityyThis );
		ship.setVelocity(newVelocityxShip, newVelocityyShip);
	}
	
	/**
	 * 
	 * @param ship
	 * 		 the ship to calculate J with
	 * @return J
	 * @see implementation
	 * @throws IllegalNumberException
	 */
    public double calculateJ(Ship ship) throws IllegalNumberException  {
        return (double)(2*this.getMass()* ship.getMass() * this.scalarProductVeloPos(ship)) / ((this.getRadius() + ship.getRadius()) *(this.getMass() + ship.getMass()));
    }
   
    /**
     * 
     * @param ship
     * 		  the ship to calculate Jx with
     * @return Jx
     * @see implementation
     * @throws IllegalNumberException
     */
    public double calculateJx(Ship ship) throws IllegalNumberException   {
        return (double)(this.calculateJ(ship) * this.positionVector(ship)[0])/(this.getRadius()+ship.getRadius());
    }
 
    /**
     * 
     * @param ship
     * 		 the ship to calculate Jy with 
     * @return Jy
     * @see implementation
     * @throws IllegalNumberException
     */
    public double calculateJy(Ship ship) throws IllegalNumberException {
        return (double)(this.calculateJ(ship) * this.positionVector(ship)[1])/(this.getRadius()+ship.getRadius());
    }
   
    /**
     * this ship collides with a boundary
     * @param collision
     * 		  the collision which collides the ship with a boundary
     * @post  if the ship collides with an upperbound or lowerbound, the velocity is set to {this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1])}
     * 		  |if (this.collidesWithHeightUpperbound(collision) || this.collidesWithHeightLowerbound(collision))
     *		  |		this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1])
     *@post if the ship collides with a rightbound or leftbound, the velocity is set to {this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]}
     *    	| if (this.collidesWithWidthLowerbound(collision) || this.collidesWithWidthUpperbound(collision))
     *				this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);		
     */
    public void collideWithBoundary(Collision collision){
    	if (this.collidesWithHeightUpperbound(collision) || this.collidesWithHeightLowerbound(collision))
    		this.setVelocity(this.getVelocity()[0], -this.getVelocity()[1]);
    	if (this.collidesWithWidthLowerbound(collision) || this.collidesWithWidthUpperbound(collision))
    		this.setVelocity(-this.getVelocity()[0], this.getVelocity()[1]);
	}



   /**
    * the ship dies
    * @post the ship is removed from its world and terminated and this world is set to null.
    * 		| if (this.getWorld()!=null)
    *  		| 		this.getWorld().removeShip(this);
    *       | this.setWorld(null);
    *    	| this.terminate();
    */
    public void die(){
        if (this.getWorld()!=null)
        	this.getWorld().removeShip(this);
        this.setWorld(null);
        this.terminate();
    }

    public void executeProgram(double dt) {
    	setExecutingProgram(true);
    	if (this.getProgram().getAvailability() == true) {
    		this.getProgram().getStatement().execute(this);
    	}
    	
    }
    private Program program;
    
    public void setExecutingProgram(boolean ds) {
    	this.isExecutingProgram = ds;
    }
    public void setProgram(Program program) {
    	this.program = program;
    	program.setShip(this);
    }
    
    public Program getProgram() {
    	return program;
    }
    private boolean isExecutingProgram;
    public boolean isExecutingProgram() {
    	return isExecutingProgram;
    }
    
  
    
} 