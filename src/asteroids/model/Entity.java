
package asteroids.model;
 
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
 
public abstract class Entity {
       
    /**
     * Initialize this new entity with given x- and y-coordinates for the position, x-and y-coordinate
     * for the velocity and the given radius.
     *
     * @param  x
     *         the x-coordinate of the position for this new entity.
     * @param  y
     *         the y-coordinate of the position for this new entity.
     * @param  velocityx
     *         The x-coordinate of the velocity for this new entity.
     * @param  velocityy
     *         The y-coordinate of the velocity for this new entity.
     * @param  radius
     *         The radius for this new entity.
     * @throws IllegalNumberException
     * 		  The given position is not valid
     *         | ! isValidPosition(x,y)
     *@throw   IllegalNumberException
     *		  the given radius is not valid.
     *	      | ! canHaveAsRadius(radius)
     * @post   The position of this new entity is equal to a double[] with coordinates the given x and y.
     *         |new.getPosition()=={x,y}.
     * @post   If the given x-coordinate and y-coordinate are valid coordinates for the velocity of any entity 
     * 		  and the speed is valid for any entity, the velocity of this new entity is equal to 
     * 		  a double[] with coordinates the given x and y.
     *         Otherwise, the velocity of this new entity is equal to {speedLimit/sqrt(2) , speedLimit/sqrt(2)}.
     *         | if (isValidNumber(velocityx) && IsValidNumber(velocityy) && isValidSpeed(new.speed) )
     *         |   	then new.getVelocity() == {velocityx , velocityy}
     *         | else new.getVelocity() == {speedLimit/Math.sqrt(2) , speedLimit/Math.sqrt(2)}
     * @post   The radius of this new entity is equal to the given radius.
     *         | new.getRadius()== radius
     */
	public Entity(double x, double y, double velocityx, double velocityy, double radius) 
			throws IllegalNumberException, IllegalArgumentException {
			if ( !isValidPosition(x,y))
			throw new IllegalArgumentException();
		setPosition(x,y);
		setVelocity(velocityx,velocityy);
		if (! canHaveAsRadius(radius))
			throw new IllegalNumberException(radius);
		this.radius = radius;
		if (! isValidMass(mass))
    		this.mass = ((double)4/3)*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
	}  
	
	protected double mass;
	public abstract boolean isValidMass(double mass);
	public abstract double getDensity();
	/**
	 * Terminate this entity.
	 *
	 * @post   This entity  is terminated.
	 *       | new.isTerminated()
	 */
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this entity
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this person is terminated.
	  */
	 private boolean isTerminated = false;
	 
	
	
	/**
	* Check whether the given number is a valid number for any entity
	* @param number
	* 		 The number to check.
	* @return true if the number is not infinitive and not NaN.
	*              |result == (number != infinity && number!= -infinity && number!=NaN)
	*/
	public static boolean isValidNumber(double number) {
		return number != Double.POSITIVE_INFINITY && number != Double.NEGATIVE_INFINITY && ! Double.isNaN(number);
	}
	
	
	//Methods about the position of the entity 
	
	/**
	 * @return the position of this entity.
	 */
	
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	* Check whether the given position is a valid position for any entity
	* @param x
	* 		 The x-coordinate to check.
	* @param y
	* 		 the y-coordinate to check
	* @return true if the given coordinates are not infinitive and not NaN.
	*              |result == ((x != infinity && x!= -infinity && x!=NaN) && (y != infinity && y!= -infinity && y!=NaN))
	*/
	public static boolean isValidPosition(double x,double y) {
		return (isValidNumber(x) &&isValidNumber(y));
	}
	
	
	/**
	 * set the position of the entity to {x,y} with x and y the given coordinates.
	 * @param  x
	 *         the new x-coordinate for this entity.
	 * @param  y
	 *         the new y-coordinate for this entity.
	 * @post   If the x- and y-coordinates are valid, the position is equal to the double[] {x,y}.
	 *         | new.position=Double[] {x,y}
	 * @throws IllegalNumberException
	 *         The given x- or y-coordinate is not valid for the position of this entity.
	 *         | ! isValidNumber(x) || ! isValidNumber(y)
	 */
	
	@Raw
	public void setPosition(double x,double y) throws IllegalArgumentException {
		double[] loc = {x,y};
		if (isValidPosition(x,y))
			this.position = loc;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * variable registering the position of this entity.
	 */
	private double[] position;
	
    //methods about the velocity of the ship
	
	/**
	 * 
	 * @return the speedlimit of this entity
	 */
	
	@Raw @Basic
    public double getSpeedLimit(){
    	return Entity.speedLimit;
    }
    
	/**
	 * set the speedlimit to a given amount.
	 * @param amount
	 * 		  the new speedlimit for this entity
	 * @post if the speedlimit is not valid, set the limit to 300000, else to the given limit
	 * 		| if (!isValidSpeedLimit(amount))
	 *     	|	Entity.speedLimit=c;
	 *      | else
	 *      | 	Entity.speedLimit=amount;
	 */
    @Raw
    public void setSpeedLimit(double amount){
    	if (!isValidSpeedLimit(amount))
    		Entity.speedLimit=c;
    	else{
    		Entity.speedLimit=amount;
    	}
    }
    
    /**
     * checks whether the given speedlimit is valid
     * @param amount
     * 		  the limit to check
     * @return 
     * 		  | result == (amount<=300000)
     */
    public static boolean isValidSpeedLimit(double amount){
    	return amount<=c;
    }
    
    
    /**
     * 
     * @return the speed of this entity
     */
	@Basic @Raw
	public double getSpeed(){
		return this.speed;
	}
	
	/**
     * Check whether the given speed is a valid speed for
     * any entity.
     *  
     * @param  speed
     *         The speed to check.
     * @return true if speed<= this.speedLimit
     *         |reslult == (velocity<= this.speedLimit)
     */
    public static boolean isValidSpeed(double speed) {
    	return speed<=Entity.speedLimit;
    }
    
    /**
     * calculate the speed of this entity.
     * @param velocityx
     * 		  the x-coordinate to calculate the speed
     * @param velocityy
     * 		  the y-coordinate to calculate the speed
     * @return the new speed
     * 			| result == Math.sqrt(Math.pow(velocityx, 2) + Math.pow(velocityy, 2))
     */
    public double calculateSpeed(double velocityx, double velocityy){
    	return Math.sqrt(Math.pow(velocityx, 2) + Math.pow(velocityy, 2));
    }
    
    /**
     * @Return the velocity of this entity.
     */
    @Basic @Raw
    public double[] getVelocity() {
    	return this.velocity;
    }
    
    
    /**
     * checks whether the given velocity is valid
     * @param velocityx
     * 		the x-coordinate of the velocity to check
     * @param velocityy
     * 		the y-coordinate of the velocity to check
     * @return
     * 		  result == (isValidNumber(velocityx) && isValidNumber(velocityy) && isValidSpeed(speed2) )
     */
    public boolean isValidVelocity(double velocityx,double velocityy){
    	double speed2 =  calculateSpeed(velocityx,velocityy);
    	return (isValidNumber(velocityx) && isValidNumber(velocityy) && isValidSpeed(speed2) );
    }
    
    /**
     * Set the velocity of this ship to a double[] {x,y} with x and y 
     * the given coordinates of the velocity.
     *
     * @param velocityx
     *        The new x-coordinate of the velocity for this entity.
     * @param velocityy
     * 		  The new y-coordinate of the velocity for this entity.
     * @post  If the calculated speed is a valid speed for any entity,
     *        the velocity of this new entity is equal to the the double[] {x,y}
     *        with x and y the given coordinates.
     *        Otherwise, the velocity is equal to {speedLimit/sqrt(2), speedLimit/sqrt(2)}
     *       | if (isValidSpeed(Speed))
     *       |   then new.getVelocity() == double[] {velocityx, velocityy}
     *       | else new.getVelocity() == double[] {speedLimit/Math.sqrt(2), speedLimit/Math.sqrt(2)}
     */
    @Raw
    public void setVelocity(double velocityx,double velocityy) {
    	if (!isValidVelocity(velocityx, velocityy)){
     		double[] velocityList= {this.getSpeedLimit()/Math.sqrt(2), this.getSpeedLimit()/Math.sqrt(2)};
 			this.speed=this.getSpeedLimit();
 			this.velocity = velocityList;}
    	else{ 
    		double[] velocityList= {velocityx,velocityy};
    		this.velocity = velocityList;
    		this.speed=calculateSpeed(velocityx,velocityy);}
	    }
    
    
    
    /**
     * variable registering the velocity of this entity.
     */
    private double[] velocity;
    
    /**
    * Variable registering the speed of this entity.
    */
    
    private static final double c=300000;
    
    /**
     * Variable registering the speedlimit of any entity
     */
    private static double speedLimit=c;
    
    /**
     * variable registering the speed of this entity
     */
    private double speed;
    
	
	//methods about the radius of the entity
	
	/**
	 * Return the radius of this entity.
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return this.radius;
	}
	
		
    /**
     * Check whether this ship can have the given radius as its radius.
     * @param radius 
     *  
     * @param  radius
     *         The radius to check.
     * @return true if the radius of the ship is larger than or equal to 10 and the radius is a valid number.
     *       | result == (radius>=10 && isValidNumber(radius)
     */
    @Raw 
   public abstract boolean canHaveAsRadius(double radius);
	
    /**
	 * Variable registering the radius of this entity.
	 */
	
	protected final double radius;
	
    
	//methods associating world    
    
	/**
	 * Return the world to which this entity belongs.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
		/**
	 * Check whether this entity can have the given world as
	 * its world.
	 * 
	 * @param  world
	 * 		   The world to check.
	 * @return If this entity is terminated, true if and only if the
	 *         given world is not effective.
	 *       | if (this.isTerminated())
	 *       |   then result == (world == null)
	 * @return If this entity is not terminated, true if and only if the given
	 *         world is effective and not yet terminated.
	 *       | if (! this.isTerminated())
	 *       |   then result ==
	 *       |    ( (world == null || (this.getWorld()==null && !world.isTerminated()) && (this.getWorld()==world || this.getWorld()==null) && (!world.isTerminated())) 
	 */
	@Raw
	public boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return world == null;
		if (world == null || (this.getWorld()==null && !world.isTerminated()))
			return true;
		if (this.getWorld()!=world && this.getWorld()!=null)
			return false;
		return(!world.isTerminated());
	}
		
	/**
	 * Check whether this entity has a proper world.
	 * 
	 * @return True if and only if this entity can have its world as its
	 *         world, and if the world of this entity is either not effective
	 *         or if it has this entity as one of its entitys.
	 *       | result ==
	 *       |   canHaveAsworld(getworld()) &&
	 *       |   ( (getworld() == null) || getworld().hasAsentity(this))
	 */
	@Raw
	public boolean hasProperWorld()  {
		return canHaveAsWorld(getWorld())
				&& ((getWorld() == null) || (getWorld().hasAsEntity(this)));
	}
	
	/**
	 * Set the world of this entity to the given world.
	 * 
	 * @param  world
	 *         The new world for this entity.
	 * @post   The world of this entity is the same as the
	 *         given world.
	 *       | new.getworld() == world
	 * @throws IllegalArgumentException
	 *         This entity cannot have the given world as its world.
	 *       | ! canHaveAsworld(world)
	 */
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (!canHaveAsWorld(world))
			throw new IllegalArgumentException("Inappropriate world!");
		this.world = world;	

	}
	
	/**
	 * Variable referencing the world to which this entity belongs.
	 */
	protected World world;
	
   
	//method move
    
   /**
    * Change the position of the entity based on the current position, current velocity and on a given time duration.
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
            setPosition(getPosition()[0],getPosition()[1]);
        else{
        setPosition(getPosition()[0] + (duration*getVelocity()[0]), getPosition()[1] + (duration*getVelocity()[1]));}
    }
	    
    //method getDistanceBetween
    
    /**
     * Get the distance between this ship and a given entity.
     * @param  ship
     *         the other ship of which we need to get the distance from this entity.
     * @return 0
     * 		   If the given entity is equal to this ship, the distance is zero.
     * 		   | distance == zero
     * @return distance
     *         returns the calculated distance between this entity and the given entity.
     *         | distance == Math.sqrt(Math.pow(this.getPosition().get(0)-ship.getPosition().get(0), 2)
     *           +Math.pow(this.getPosition().get(1)-entity.getPosition().get(1),2))-this.getRadius()-entity.getRadius();
     * @throws IllegalNumberException
     * 		   The given distance isn't a valid number for any entity.
     * 		   ( ! isValidNumber(distance))
     */
    public double getDistanceBetween(Entity entity) throws IllegalNumberException {
    	if (this==entity)
            return 0;
    	double[] thisPosition=this.getPosition();
    	double[] entityPosition=entity.getPosition();
    	double thisRadius=this.getRadius();
    	double entityRadius=entity.getRadius();
    	double distance=Math.sqrt(Math.pow(thisPosition[0]-entityPosition[0], 2)+Math.pow(thisPosition[1]-entityPosition[1],2))-thisRadius-entityRadius;
        if (! isValidNumber(distance))
            throw new IllegalNumberException(distance);

        return distance;
    }
 
   //method overlap
    /**
     * Determine if this entity overlaps with the given entity.
     * @param 	entity
     * 			The given entity which may or may not overlap with this entity.
     * @return 	true if the distance between this entity and the given entity is less than 0 
	 *			or if this entity is equal to the given entity.
	 *			| (this.getDistanceBetween(entity) <= 0 )
     * @throws  IllegalNumberException
     * 		 	The distance between this entity and a given entity is not a valid number.
     * 			| (! isValidNumber(getDistanceBetween(entity))
     */   
    public boolean overlap(Entity entity) throws IllegalNumberException {
        if (! isValidNumber(getDistanceBetween(entity)))
            throw new IllegalNumberException(getDistanceBetween(entity));
        return (getDistanceBetween(entity) < 0);
    }
    
    //methods concerning getTimeCollision
    
    /**
     * Return when, if ever, two space entities collide.
     * @param entity
     * @return Positive infinity 
     * 		   if the scalar product of the velocity vector and the position vector 
     *         is greater than or equal to 0,
     *         meaning the entities won't ever collide.
     * @return Positive infinity 
     * 		   if the scalar product of the velocity vector and the position vector squared 
     *         - the product of the length of the velocity vector squared and (the length of the position vector squared
     *         - the sum of the radii squared), is less than or equal to 0, 
     *         meaning the ships won't ever collide.
     * @return The time needed for the two entities to collide, 
     * 		   by collide meaning that the distance between this ship and a given entity is equal to the sum of the two radii. 
     * 		   |timeToCollision == -(this.scalarProductVeloPos(ship) 
     * 		   | + Math.sqrt(this.calculatedD(entity))) / this.lengthVelocitySquared(entity)
     * @throws IllegalArgumentException
     * 		   if the two entities already overlap.
     * 		   if (overlap(entity))
     * @throws IllegalNumberException
     */
    public double getTimeToCollision(Entity entity) throws IllegalNumberException,IllegalArgumentException {
        //if (this.overlap(entity))
        	//throw new IllegalArgumentException();
        if (this.scalarProductVeloPos(entity) >= 0)
            return Double.POSITIVE_INFINITY;
        if (( this.calculatedD(entity) <= 0))
            return Double.POSITIVE_INFINITY;
        return -( this.scalarProductVeloPos(entity) + Math.sqrt(this.calculatedD(entity))) / this.lengthVelocitySquared(entity);
    }
    
    /**
     * calculate the position vector between the two positions
     * @param entity
     * @return  a double[] list {x,y} where x is the position vector between the x-coordinates of this entity and a given entity
     * 		    and y is the position vector between the y-coordinates of this entity and a given entity.
     * 		    | newposition == (entity.getPosition()[0] - this.getPosition()[0], entity.getPosition()[1] - this.getPosition[1])
     */
    public double[] positionVector(Entity entity) {
    	double [] positionVector = {entity.getPosition()[0] - this.getPosition()[0],entity.getPosition()[1] - this.getPosition()[1]};
    	return positionVector;
    }
    
    /**
     * calculate the velocity vector between the two velocities
     * @param  entity
     * @return a double[] (x,y) where x is the velocity vector between this ship and a given entity in x direction 
     * 		   and y is the velocity vector between this entity and a given entity in the y direction.
     *		   | newvelocity == (entity.getVelocityx() - this.getVelocityx(), entity.getVelocityy() - this.getVelocityy())
     */
    public double[] velocityVector(Entity entity) {
    	double[] velocityVector=new double[2];
        velocityVector[0] = entity.getVelocity()[0] - this.getVelocity()[0];
        velocityVector[1] = entity.getVelocity()[1] - this.getVelocity()[1];
        return velocityVector;
    }
    
    /**
     * calculate the scalar product of the position vector with itself
     * @param entity
     * @return the length of the vector between the center of this entity and a given ship squared
     * 		   | length == Math.pow(differenceInPosition(entity)[0], 2) + Math.pow(differenceInPosition(entity)[1], 2);
     * @throws IllegalNumberException
     * 		   if the calculated length is not valid.
     * 		   | ( ! isValidNumber(length))
     */
    public double lengthPositionSquared(Entity entity) throws IllegalNumberException {
    	double length=Math.pow(positionVector(entity)[0], 2) + Math.pow(positionVector(entity)[1], 2);
    	if  ( ! isValidNumber(length))
    		throw new IllegalNumberException(length);
    	return  length;
    }
    
    /**
     * calculate the scalar product of the velocity vector with itself
     * @param entity
     * @return the length of the velocity vector between the center of this entity and a given entity squared
     * 		   | differenceVeSq == Math.pow(differenceInVelocity(ship)[0], 2) + Math.pow(differenceInVelocity(ship)[1], 2);
     * @throws IllegalNumberException
     * 		   if the calculated length is not valid.
     * 		   | ( ! isValidNumber(number))
     */
    public double lengthVelocitySquared(Entity entity) throws IllegalNumberException {
            lengthVeSq = Math.pow(this.velocityVector(entity)[0], 2) + Math.pow(this.velocityVector(entity)[1], 2);
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
     * 		   + the velocity vector in the y-direction times the position vector on the y-axis
     * 		   |differenceVeLo == ((differenceInVelocity(entity)[0] * differenceInPosition(entity)[0]) 
     * 		   |+ (differenceInVelocity(entity)[1] * differenceInPosition(entity)[1]))
     * @throws IllegalNumberException
     * 		   if the calculated number is not valid.
     * 		   | ( ! isValidNumber(number))
     */
    public double scalarProductVeloPos(Entity entity) throws IllegalNumberException{
        differenceVePos = ((this.velocityVector(entity)[0] * this.positionVector(entity)[0])+ (this.velocityVector(entity)[1] * this.positionVector(entity)[1]));
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
     * 		   | sigmasquared == Math.pow(this.getRadius() + entity.getRadius(),2);
     * @throws IllegalNumberException
     * 		   if the calculated number is not valid.
     * 		   | ( ! isValidNumber(number)) 
     */
    public double sigmasquared(Entity entity)throws IllegalNumberException{
        sigmasquared = Math.pow(this.getRadius() + entity.getRadius(),2);
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
     *         |calculatedD == Math.pow((differenceVeloLoc(ship)),2) 
     *         |- ((differenceVelocitySquared(entity)) * ((differenceLocationSquared(entity) - sigmaSquared(entity))))
     * @throws IllegalNumberException
     *		   if the calculated number is not valid.
     * 		   | ( ! isValidNumber(number)) 			
     */
    public double calculatedD(Entity entity) throws IllegalNumberException {
        
    	try{
    		calculatedD = Math.pow((this.scalarProductVeloPos(entity)),2) - 
    				((this.lengthVelocitySquared(entity)) * ((this.lengthPositionSquared(entity) - this.sigmasquared(entity))));
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
    private double[] shipCoordCollision;
    
    /**
     * calculate the center of the circles at the time of collision
     * @param  entity
     * @post   set the double[] thisCoordCollision to this.getPosition() + (getTimeToCollision(entity) * this.getVelocity());
     * 		   |this.thisCoordCollision==this.getPosition() + (getTimeToCollision(entity) * this.getVelocity())
     * @post   set the double[] entityCoordCollision to entity.getPosition() + (getTimeToCollision(entity) * entity.getVelocity());
     * 		   |this.entityCoordCollision==entity.getPosition() + (getTimeToCollision(entity) * entity.getVelocity())
     * @throws IllegalNumberException
     */
    public void calculateCollision(Entity entity) throws IllegalNumberException {
        double[] coordcollthis=new double[2];
        double[] coordcollship=new double[2];
    	coordcollthis[0] = this.getPosition()[0] + (getTimeToCollision(entity) * this.getVelocity()[0]);
        coordcollthis[1] = this.getPosition()[1] + (getTimeToCollision(entity) * this.getVelocity()[1]);
        coordcollship[0] = entity.getPosition()[0] + (getTimeToCollision(entity) * entity.getVelocity()[0]);
        coordcollship[1] = entity.getPosition()[1] + (getTimeToCollision(entity) * entity.getVelocity()[1]);
        this.thisCoordCollision=coordcollthis;
        this.shipCoordCollision=coordcollship;
    } 
   
    /**
     * get the position of the collision
     * @param  entity
     * @return null
     * 		   if the time before the collision is infinity.
     * 		   |(getTimeToCollision(entity)==Double.POSITIVE_INFINITY)  
     * @return collisionPosition
     * 		   the position where the two entities collide
     * 		   | collisionPosition= thisCoordCollision+direction * (this.getRadius()/(this.getRadius()+entity.getRadius()));
     * @throws IllegalArgumentException
     * 		   if the two entities already overlap
     * 		   | (this.overlap(entity))
     * @throws IllegalNumberException
     * 		   if one of the elements of the position of the collision is negative or NaN
     * 		   | (collisionPosition[0]==Double.NEGATIVE_INFINITY || collisionPosition[0]==Double.NaN)
     * 		   | (collisionPosition[1]==Double.NEGATIVE_INFINITY || collisionPosition[1]==Double.NaN)
     */
    public double[] getCollisionPosition(Entity entity) throws IllegalArgumentException,IllegalNumberException{
        if (overlap(entity))
            throw new IllegalArgumentException();
        if (this.getTimeToCollision(entity)==Double.POSITIVE_INFINITY)
            return null;
        calculateCollision(entity);
        double[] direction=new double[2];
        direction[0]=shipCoordCollision[0]-thisCoordCollision[0];
        direction[1]=shipCoordCollision[1]-thisCoordCollision[1];
        double[] colposition= new double[2];
        colposition[0]= thisCoordCollision[0]+direction[0] * (this.getRadius()/(this.getRadius()+entity.getRadius()));
        colposition[1]= thisCoordCollision[1]+direction[1] * (this.getRadius()/(this.getRadius()+entity.getRadius()));
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
    
    /**
     * calculate the time to collide with the upperbound of the world
     * @return the time to collide with the upperbound
     * 			|if time=((this.getWorld()).getHeight()-this.getRadius() - this.getPosition()[1])/this.getVelocity()[1>=0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */
    public double calculateTimeToCollisionHeightUpperbound() {
        if (this.getVelocity()[1]!=0 && this.getWorld()!=null ){
        	double time =((this.getWorld()).getHeight()-this.getRadius() - this.getPosition()[1])/this.getVelocity()[1];
        	if (time>=0)
        		return time;}
        
        return Double.POSITIVE_INFINITY;
    }
    
    
     /**
     * calculate the time to collide with the rightbound of the world
     * @return the time to collide with the rightbound
     * 			|if time=((this.getWorld()).getWidth()-this.getRadius() - this.getPosition()[0])/this.getVelocity()[0]>=0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */   
    public double calculateTimeToCollisionWidthUpperbound() {
       if (this.getVelocity()[0]!=0 && this.getWorld()!=null){
           double time=  ((double)(this.getWorld()).getWidth()-this.getRadius() - this.getPosition()[0])/(this.getVelocity()[0]);
           if (time>=0)
        	   return time;}
       return Double.POSITIVE_INFINITY;
    }
    
    
      /**
     * calculate the time to collide with the lowerbound of the world
     * @return the time to collide with the lowerbound
     * 			|if time=( (double) (this.getRadius()-this.getPosition()[1])/this.getVelocity()[1])>=0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */  
    public double calculateTimeToCollisionHeightLowerbound(){
    	if (this.getVelocity()[1]!=0 && this.getWorld()!=null  ){
    		double time =( (double) (this.getRadius()-this.getPosition()[1])/this.getVelocity()[1]);
    		if (time >=0)
    			return time;}
    	return Double.POSITIVE_INFINITY;
    }
   
    /**
   * calculate the time to collide with the leftbound of the world
   * @return the time to collide with the leftbound
   * 			|if time=( (double) (this.getRadius()-this.getPosition()[0])/this.getVelocity()[0])>=0
   * 			|	return time
   * 			|else
   * 			|  return Double.POSITIVE_INFINITY
   */  
    public double calculateTimeToCollisionWidthLowerbound(){
    	if (this.getVelocity()[0]!=0 && this.getWorld()!=null ){
    		double time =( (double) (this.getRadius()-this.getPosition()[0])/this.getVelocity()[0]);
    		if (time >=0)
    			return time;}
    	return Double.POSITIVE_INFINITY;
    } 
    
    /**
     * 
     * @return the time to collide with a boundary
     * @see implementation
     */
    public double getTimeToCollisionBoundary(){
    	double t1 = this.calculateTimeToCollisionHeightUpperbound();
    	double t2 = this.calculateTimeToCollisionWidthUpperbound();
    	double t3 = this.calculateTimeToCollisionHeightLowerbound();
    	double t4 = this.calculateTimeToCollisionWidthLowerbound();
    	if (t1!=Double.POSITIVE_INFINITY || t2!=Double.POSITIVE_INFINITY || t3 != Double.POSITIVE_INFINITY || t4!=Double.POSITIVE_INFINITY){	
    		if (t1<=t2 && t1<=t3 && t1<=t4){
    			return t1;}
    		if (t2 <=t1 && t2<=t3 && t2<=t4){ 			
    			return t2;}    		
    		if (t3 <=t1 && t3<=t2 && t3<=t4){   			
    			return t3;}
    		if (t4 <=t1 && t4<=t2 && t4<=t1){       			
    			return t4;}}
			return Double.POSITIVE_INFINITY;    		

    }
    
    /**
     * 
     * @return time to collide with the upperbound not accepting zero
     * 			|if time=((this.getWorld()).getHeight()-this.getRadius() - this.getPosition()[1])/this.getVelocity()[1>0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */
    public double calculateTimeToCollisionHeightUpperboundNotZero() {
        if (this.getVelocity()[1]!=0 && this.getWorld()!=null ){
        	double time =((this.getWorld()).getHeight()-this.getRadius() - this.getPosition()[1])/this.getVelocity()[1];
        	if (time>0)
        		return time;}
        
        return Double.POSITIVE_INFINITY;
    }
    
    /**
     * 
     * @return time to collide with the rightbound not accepting zero
     * 			|if time=((this.getWorld()).getWidth()-this.getRadius() - this.getPosition()[0])/this.getVelocity()[0]>0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */
    public double calculateTimeToCollisionWidthUpperboundNotZero() {
       if (this.getVelocity()[0]!=0 && this.getWorld()!=null){
           double time=  ((double)(this.getWorld()).getWidth()-this.getRadius() - this.getPosition()[0])/(this.getVelocity()[0]);
           if (time>0)
        	   return time;}
       return Double.POSITIVE_INFINITY;
    }
    
    /**
     * 
     * @return time to collide with the lowerbound not accepting zero
     * 		|if time=( (double) (this.getRadius()-this.getPosition()[1])/this.getVelocity()[1])>0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */
    public double calculateTimeToCollisionHeightLowerboundNotZero(){
    	if (this.getVelocity()[1]!=0 && this.getWorld()!=null  ){
    		double time =( (double) (this.getRadius()-this.getPosition()[1])/this.getVelocity()[1]);
    		if (time >0)
    			return time;}
    	return Double.POSITIVE_INFINITY;
    }
   
    /**
     * 
     * @return time to collide with the leftbound not accepting zero
     * 			|if time=( (double) (this.getRadius()-this.getPosition()[0])/this.getVelocity()[0])>=0
     * 			|	return time
     * 			|else
     * 			|  return Double.POSITIVE_INFINITY
     */
    public double calculateTimeToCollisionWidthLowerboundNotZero(){
    	if (this.getVelocity()[0]!=0 && this.getWorld()!=null ){
    		double time =( (double) (this.getRadius()-this.getPosition()[0])/this.getVelocity()[0]);
    		if (time >0)
    			return time;}
    	return Double.POSITIVE_INFINITY;
    } 
    
    /**
     * 
     * @return time to collision not accepting zero
     * @see implementation
     */
    public double getTimeToCollisionBoundaryNotZero(){
    	double t1 = this.calculateTimeToCollisionHeightUpperboundNotZero();
    	double t2 = this.calculateTimeToCollisionWidthUpperboundNotZero();
    	double t3 = this.calculateTimeToCollisionHeightLowerboundNotZero();
    	double t4 = this.calculateTimeToCollisionWidthLowerboundNotZero();
    	if (t1!=Double.POSITIVE_INFINITY || t2!=Double.POSITIVE_INFINITY || t3 != Double.POSITIVE_INFINITY || t4!=Double.POSITIVE_INFINITY){	
    		if (t1<=t2 && t1<=t3 && t1<=t4){
    			return t1;}
    		if (t2 <=t1 && t2<=t3 && t2<=t4){ 			
    			return t2;}    		
    		if (t3 <=t1 && t3<=t2 && t3<=t4){   			
    			return t3;}
    		if (t4 <=t1 && t4<=t2 && t4<=t1){       			
    			return t4;}}
			return Double.POSITIVE_INFINITY;    		

    }
    
    /**
     *  @return null if the entity never collides with a boundary
     * @return the position of a collision with a boundary
     * @see implementation
     *
     * 
     * 			
     */
    public double[] getCollisionPositionBoundary(){
    	double t=getTimeToCollisionBoundary();
    	if (t!=Double.POSITIVE_INFINITY)
    		{double newPosxCoordinate=this.getPosition()[0]+t*this.getVelocity()[0];
    		double newPosyCoordinate=this.getPosition()[1]+t*this.getVelocity()[1];
    		if ( newPosyCoordinate==(this.getWorld()).getHeight()-this.getRadius() ){
    			double[] collisionPosition={newPosxCoordinate,this.getWorld().getHeight()};
    			return collisionPosition;}
    		else if ( newPosxCoordinate==(this.getWorld()).getWidth()-this.getRadius() ){
    			double[] collisionPosition={this.getWorld().getWidth(),newPosyCoordinate};
    			return collisionPosition;}
    		else if (newPosyCoordinate==this.getRadius()){
    			double[] collisionPosition={newPosxCoordinate,0};
    			return collisionPosition;
    		}
    		else if (newPosxCoordinate==this.getRadius()){
    			double[] collisionPosition={0,newPosyCoordinate};
    			return collisionPosition;
    		}}
    	return null;    	
    		
    }
    
    /**
     * get the position of the collision when the entity is already at the place of collision.
     * @param collision
     * 		  the collision to get the position from
     * @return the position of this collision. returns null if the entity never collides
     * @see implementation
     */
    public double[] getCollisionPositionBoundary(Collision collision){
    	double t=collision.getTimeToCollision();
    	if ((t!=Double.POSITIVE_INFINITY) && (collision.getEntity1()!=null || collision.getEntity2()!=null))
    		{double newPosxCoordinate=this.getPosition()[0];
    		double newPosyCoordinate=this.getPosition()[1];
    		if ( newPosyCoordinate==(this.getWorld()).getHeight()-this.getRadius() ){
    			double[] collisionPosition={newPosxCoordinate,this.getWorld().getHeight()};
    			return collisionPosition;}
    		else if ( newPosxCoordinate==(this.getWorld()).getWidth()-this.getRadius() ){
    			double[] collisionPosition={this.getWorld().getWidth(),newPosyCoordinate};
    			return collisionPosition;}
    		else if (newPosyCoordinate==this.getRadius()){
    			double[] collisionPosition={newPosxCoordinate,0};
    			return collisionPosition;
    		}
    		else if (newPosxCoordinate==this.getRadius()){
    			double[] collisionPosition={0,newPosyCoordinate};
    			return collisionPosition;
    		}}
    	return null;    	
    		
    }
    
    /**
     * checks whether the entity collides with the lowerbound
     * @return
     * 		  |result == ( collisionPositionBoundary()[1]==0)
     */
    public boolean collidesWithHeightLowerbound(){
    	   double[] collisionPositionBoundary= this.getCollisionPositionBoundary();
    	   if(collisionPositionBoundary!=null)
    		   return (collisionPositionBoundary[1]==0);
    	   return false;
    	}
    
    /**
     * checks whether the entity collides with the upperbound
     * @return
     * 		  |result == ( collisionPositionBoundary()[1]==(this.getWorld()).getHeight())
     */
    public boolean collidesWithHeightUpperbound(){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary();
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[1]==(this.getWorld()).getHeight());
       return false;
    }

    
    /**
     * checks whether the entity collides with the leftbound
     * @return
     * 		  |result == ( collisionPositionBoundary()[0]==0)
     */	
    public boolean collidesWithWidthLowerbound(){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary();
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[0]==(double)0);
       return false;
    }

    /**
     * checks whether the entity collides with the rightbound
     * @return
     * 		  |result == ( collisionPositionBoundary()[0]==(this.getWorld()).getWidth())
     */
    public boolean collidesWithWidthUpperbound(){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary();
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[0]==(this.getWorld()).getWidth());
       return false;
    } 
 
    /**
     * checks whether the entity collides with the upperbound in the given collision
     * @param collision
     * 		  the collision to check
     * @return
     * 		  |result == ( collisionPositionBoundary()[1]==0)
     */
    public boolean collidesWithHeightLowerbound(Collision collision){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary(collision);
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[1]==0);
       return false;
    }
    
    /**
     * checks whether the entity collides with the lowerbound in the given collision
     * @param collision
     * 		  the collision to check
     * @return
     * 		  |result == ( collisionPositionBoundary()[1]==(this.getWorld()).getHeight())
     */    
    public boolean collidesWithHeightUpperbound(Collision collision){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary(collision);
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[1]==(this.getWorld()).getHeight());
       return false;
    }

       /**
     * checks whether the entity collides with the leftbound in the given collision
     * @param collision
     * 		  the collision to check
     * @return
     * 		  |result == ( collisionPositionBoundary()[0]==0)
     */ 
    public boolean collidesWithWidthLowerbound(Collision collision){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary(collision);
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[0]==(double)0);
       return false;
    }

     /**
     * checks whether the entity collides with the rightbound in the given collision
     * @param collision
     * 		  the collision to check
     * @return
     * 		  |result == ( collisionPositionBoundary()[0]==(this.getWorld()).getWidth())
     */   
    public boolean collidesWithWidthUpperbound(Collision collision){
       double[] collisionPositionBoundary= this.getCollisionPositionBoundary(collision);
       if(collisionPositionBoundary!=null)
    	   return (collisionPositionBoundary[0]==(this.getWorld()).getWidth());
       return false;
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

    public abstract void die() throws IllegalNumberException, IllegalArgumentException;

}