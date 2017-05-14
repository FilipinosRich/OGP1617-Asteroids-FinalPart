package asteroids.model;

import be.kuleuven.cs.som.annotate.*;


import java.util.*;
import asteroids.model.Entity;


/**
 * @invar  Each world can have its width as width .
 *       | canHaveAsWidth(this.getWidth())
 * @invar  Each world can have its height as height .
 *       | canHaveAsHeight(this.getHeight())
 * @invar   Each world must have proper ships.
 *        | hasProperShips()
 * @invar Each world must have proper bullets.
 * 			|hasProperBullets()
 */
public class World {
	/**
	 * Initialize this new world with given width and height as a non-terminated world with no ships or bullets yet.
	 * 
	 * @param  width
	 *         The width for this new world.
	 * @param  height
	 * 		   The height for this new world
	 * @post   If the given height is a valid height for any world,
	 *         the height of this new world is equal to the given
	 *         height. Otherwise, the height of this new world is equal
	 *         to 0.
	 *       | if (isValidValue(height))
	 *       |   then new.getHeight == height
	 *       |   else new.getHeight == 0
	 * @post   If the given width is a valid width for any world,
	 *         the width of this new world is equal to the given
	 *         width. Otherwise, the width of this new world is equal
	 *         to 0.
	 *       | if (isValidValue(width))
	 *       |   then new.getWidth == width
	 *       |   else new.getWidth == 0
	 * @post   This new world has no ships yet.
	 *       | new.getNbShips() == 0
	 * @post   This new world has no bullets yet.
	 *       | new.getNbBullets() == 0
	 */
	@Raw
	public World(double width, double height) {
		if (! canHaveAsValue(width))
			width = 0;
		if (! canHaveAsValue(height))
			height = 0;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Terminate this world.
	 *
	 * @post   This world  is terminated.
	 *       | new.isTerminated()
	 */
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this world
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
	 
	 //methods about this world height and width//
	 
	 /**
	  * 
	  * @return the upperbound for the width and hight of this world
	  */
	 @Basic @Raw
	 public static double getUpperbound(){
		 return World.upperbound;
	 }
	 
	 /**
	  * 
	  * @param upperbound
	  * 		the upperbound to check
	  * @return
	  * @see implementation
	  */
	 @Raw
	 public static boolean canHaveAsUpperbound(double upperbound){
		return upperbound<=Double.MAX_VALUE; 
	 }
	 
	 /**
	  * set the upperbound to the given upperbound if this upperbound is valid. otherwise, set the upperbound
	  * to MAX_VALUE
	  * @param upperbound
	  * 		the new upperbound
	  * 
	  */
	 @Raw
	 public static void setUpperbound(double upperbound){
		 if ( ! canHaveAsUpperbound(upperbound))
			 World.upperbound=Double.MAX_VALUE;
		 else{
			 World.upperbound=upperbound;
		 }
	 }
	
	 /**
	  * variable referencing the upperbound of any worlds hight and width
	  */
	 private static double upperbound=Double.MAX_VALUE;
	 /**
	  * Return the width of this world.
	  */
	 @Basic @Raw @Immutable
	 public double getWidth() {
		 return this.width;
	 }
	
	/**
	 * 
	 * @return the height of this world.
	 */
	@Basic @Raw @Immutable
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Check whether this world can have the given value as its height/width.
	 *  
	 * @param  value
	 *         The value to check.
	 * @return 
	 *       | result == (value>=0 && value <= Double.MAX_VALUE && value !=Double.NaN)
	*/
	@Raw
	public boolean canHaveAsValue(double value) {
		return ((value >= 0) && (value <= World.getUpperbound()) && (value != Double.NaN));
	}
	
	/**
	 * Variable registering the width of this world.
	 */
	private final double width;

	/**
	 * Variable registering the height of this world.
	 */
	private final double height;
	
	/**
	 * checks whether the given entities significant overlap.
	 * @param entity1
	 * 		  entity to check
	 * @param entity2
	 * 		  entity to check
	 * @return 
	 * @see implementation
	 */
	public boolean significantOverlap(Entity entity1, Entity entity2)  {
		if (entity1==entity2)
			return false;			
		return Math.sqrt(Math.pow(entity2.getPosition()[0]-entity1.getPosition()[0], 2)+Math.pow(entity2.getPosition()[1]-entity1.getPosition()[1], 2))<=0.99*(entity1.getRadius()+entity2.getRadius());

		
	}
	
	/**
	 * checks whether the given entity is apparently within boundaries of this world
	 * @param entity
	 * 		  entity to check
	 * @return
	 * @see implementation
	 */
	public boolean apparentlyWithinBoundaries(Entity entity){
		return (Math.abs(this.getWidth()-entity.getPosition()[0])>=0.99*entity.getRadius() && Math.abs(entity.getPosition()[0])>=0.99*entity.getRadius()
				&&Math.abs(entity.getPosition()[1])>=0.99*entity.getRadius()&& Math.abs(this.getHeight()-entity.getPosition()[1])>=0.99*entity.getRadius());
	}
	
	/**
	 * checks whether the given entities apparently collide
	 * @param entity1
	 * 		  entity to check
	 * @param entity2
	 * 		 entity to check
	 * @return 
	 * @see implementation
	 */
	public boolean apparentlyCollide(Entity entity1 ,Entity entity2){
		if (entity1==entity2)
			return false;
		return (Math.sqrt(Math.pow(entity2.getPosition()[0]-entity1.getPosition()[0], 2)+Math.pow(entity2.getPosition()[1]-entity1.getPosition()[1], 2))>0.99*(entity1.getRadius()+entity2.getRadius()) &&
				Math.sqrt(Math.pow(entity2.getPosition()[0]-entity1.getPosition()[0], 2)+Math.pow(entity2.getPosition()[1]-entity1.getPosition()[1], 2))<1.01*(entity1.getRadius()+entity2.getRadius()));
	}
	
	/**
	 * checks whether the x-coordinate of the given entity is within the width boundary of this world
	 * @param entity
	 * 		  entity to check
	 * @return
	 * @see implementation
	 */
	public boolean canHaveAsWidthCoordinate(Entity entity){
		if (!((entity.getPosition()[0] + entity.getRadius()) <= this.getWidth()))
			return false;
		if ((!canHaveAsValue(entity.getPosition()[0] + entity.getRadius())) || (! canHaveAsValue(entity.getPosition()[0] - entity.getRadius())))
			return false;
		return true;
	}
	
	/**
	 * checks whether the y-coordinate of the given entity is within the height boundary of this world
	 * @param entity
	 * 		  the entity to check
	 * @return
	 * @see implementation
	 */
	public boolean canHaveAsHeightCoordinate(Entity entity){
		if (!((entity.getPosition()[1] + entity.getRadius()) <= this.getHeight()))
			return false;
		if ((!canHaveAsValue(entity.getPosition()[1] + entity.getRadius())) || (! canHaveAsValue(entity.getPosition()[1] - entity.getRadius())))
			return false;
		return true;
	}

	
	//methods associating world with ship
	/**
	 * Check whether this world has the given ship as one of its
	 * ships.
	 * 
	 * @param  ship
	 *         The ship to check.
	 */
	@Basic
	@Raw
	public boolean hasAsShip(@Raw Ship ship) {
		return ships.contains(ship);
	}

	/**
	 * Check whether this world can have the given ship
	 * as one of its ships.
	 * 
	 * @param  ship
	 *         The ship to check.
	 * @return True if and only if the given ship is effective
	 *         and that ship is a valid ship for a world and the ship doesn't overlap with other entities of this world 
	 *         and the ship is within boundaries of this world.
	 */
	@Raw
	public boolean canHaveAsShip(Ship ship) throws IllegalNumberException {
		if ((ship == null) || (!ship.canHaveAsWorld(this)) )
			return false;
		if ((!this.canHaveAsHeightCoordinate(ship) || !this.canHaveAsWidthCoordinate(ship)))
			return false;
		for (Ship ship2 :this.getShips()){
			if (ship.overlap(ship2) && ship!=ship2 )
					return false;				
		}
		for (Bullet bullet :this.getBullets()) {
			if (bullet.overlap(ship) )
					return false;			
		}
		return true;
	}
	
	
	/**
	 * Check whether this world has proper ships attached to it.
	 * 
	 * @return True if and only if this world can have each of the
	 *         ships attached to it as one of its ships,
	 *         and if each of these ships references this world as
	 *         the world to which they are attached.
	 *       | for each ship in Ship:
	 *       |   if (hasAsShip(ship))
	 *       |     then canHaveAsShip(ship) &&
	 *       |          (ship.getWorld() == this)
	 */
	public boolean hasProperShips() throws IllegalNumberException{
		for (Ship ship : ships) {
			if (!canHaveAsShip(ship))
				return false;
			if (ship.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of ships associated with this world.
	 *
	 * @return  The total number of ships collected in this world.
	 *        | result ==
	 *        |   card({ship:Ship | hasAsShip({ship)})
	 */
	public int getNbShips() {
		return ships.size();
	}

	/**
	 * Add the given ship to the set of ships of this world.
	 * 
	 * @param  ship
	 *         The ship to be added.
	 * @throws   IllegalArgumentException
	 * 			The world can't have the given ship as its ship or the world already has this ship as its ship
	 *         this world.
	 * @throws IllegalNumberException
	 * @post   This world has the given ship as one of its ships.
	 *       | new.hasAsShip(ship)
	 */
	public void addShip(@Raw Ship ship) throws IllegalArgumentException, IllegalNumberException{
		if (!canHaveAsShip(ship) || this.hasAsShip(ship))
			throw new IllegalArgumentException();
		ships.add(ship);
		entities.add(ship);
	}

	/**
	 * Remove the given ship from the set of ships of this world.
	 * 
	 * @param  ship
	 *         The ship to be removed.
	 * @throws IllegalArgumentException 
	 *         This world doesn't have the given ship as one of
	 *         its ships
	 * @post   This world no longer has the given ship as
	 *         one of its ships.
	 *       | ! new.hasAsShip(ship)
	 */
	@Raw
	public void removeShip(Ship ship) throws IllegalArgumentException{
		if( !this.hasAsShip(ship))
			throw new IllegalArgumentException();
		ships.remove(ship);
		entities.remove(ship);
	}

	/**
	 * Variable referencing a set collecting all the ships
	 * of this world.
	 * 
	 * @invar  The referenced set is effective.
	 *       | ships != null
	 * @invar  Each ship registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each ship in ships:
	 *       |   ( (ship != null) &&
	 *       |     (! ship.isTerminated()) )
	 */
	private Set<Ship> ships = new HashSet<Ship>();
	
	/**
	 * Variable referencing a set collecting all the entities
	 * of this world.
	 * 
	 * @invar  The referenced set is effective.
	 *       | entities != null
	 * @invar  Each entity registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each entity in entities:
	 *       |   ( (entity != null) &&
	 *       |     (! entity.isTerminated()) )
	 */
	private Set<Entity> entities = new HashSet<Entity>();
	
	/**
	 * Check whether this world has the given entity as one of its
	 * entity.
	 * 
	 * @param  entity
	 *         The entity to check.
	 */
	@Basic
	@Raw
	public boolean hasAsEntity(@Raw Entity entity) {
		return entities.contains(entity);
	}
	
	/**
	 * checks whether the ships and bullets in entities are proper ships and bullets for this ship
	 * @return
	 * 		 | result == this.hasProperBullets() && this.hasProperShips()
	 * @throws IllegalNumberException
	 */
	public boolean hasProperEntities() throws IllegalNumberException{
		return this.hasProperBullets() && this.hasProperShips();
	}
	
	/**
	 * adds the given entity to this ship
	 * @param entity
	 * 		 entity to add
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public void addEntity(@Raw Entity entity) {
		entities.add(entity);}
	

	
	/**
	 * Return the number of entities associated with this world.
	 *
	 * @return  The total number of entities collected in this world.
	 *        | result ==
	 *        |   card({entity:entity | hasAsEntity({entity)})
	 */
	public int getNbEntities() {
		return entities.size();
	}
	
	//methods associating bullets with this world
	
	/**
	 * Check whether this world has the given bullet as one of its
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
	 * Check whether this world can have the given bullet
	 * as one of its bullets.
	 * 
	 * @param  bullet
	 *         The bullet to check.
	 * @return True if and only if the given bullet is effective
	 *         and that bullet is a valid bullet for a world 
	 *         and if the bullet doesn't overlap with other entities and if the bullet is fully within the bounds of this world.
	 */
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) throws IllegalNumberException{
		if ((bullet == null) || (!bullet.canHaveAsWorld(this))  || (bullet.getShip()!=null))
			return false;
		if (!this.canHaveAsHeightCoordinate(bullet) || !this.canHaveAsWidthCoordinate(bullet) )
			return false;
		for (Ship ship : ships) {
			if (ship.overlap(bullet))
				if (bullet.getSource()!=ship)
					return false;			
		}
		for (Bullet bullet2:bullets){
			if (bullet.overlap(bullet2) && bullet!=bullet2)
				return false;
		}
		return true;
	}
	
	
	/**
	 * Check whether this world has proper bullets attached to it.
	 * 
	 * @return True if and only if this world can have each of the
	 *         bullets attached to it as one of its bullets,
	 *         and if each of these bullets references this world as
	 *         the world to which they are attached.
	 *       | for each bullet in bullet:
	 *       |   if (hasAsbullet(bullet))
	 *       |     then canHaveAsbullet(bullet) &&
	 *       |          (bullet.getWorld() == this)
	 */
	public boolean hasProperBullets() throws IllegalNumberException{
		for (Bullet bullet : bullets) {
			if (!canHaveAsBullet(bullet))
				return false;
			if (bullet.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of bullets associated with this world.
	 *
	 * @return  The total number of bullets collected in this world.
	 *        | result ==
	 *        |   card({bullet:bullet | hasAsbullet({bullet)})
	 */
	public int getNbBullets() {
		return bullets.size();
	}

	/**
	 * Add the given bullet to the set of bullets of this world.
	 * 
	 * @param  bullet
	 *         The bullet to be added.
	 * @throws IllegalArgumentException
	 * 		   The world can't have the given bullet as its bullet or already has the given bullet as its bullet
	 * @post   This world has the given bullet as one of its bullets.
	 *       | new.hasAsbullet(bullet)
	 */
	public void addBullet(@Raw Bullet bullet) throws IllegalArgumentException,IllegalNumberException{
		if (!canHaveAsBullet(bullet) || this.hasAsBullet(bullet))
			throw new IllegalArgumentException();
		bullets.add(bullet);
		entities.add(bullet);
	}

	/**
	 * Remove the given bullet from the set of bullets of this world.
	 * 
	 * @param  bullet
	 *         The bullet to be removed.
	 * @throws IllegalNumberException 
	 * @throws IllegalArgumentException 
	 *         This world doesn't have the given bullet as one of
	 *         its bullets
	 * @post   This world no longer has the given bullet as
	 *         one of its bullets.
	 *       | ! new.hasAsbullet(bullet)
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException, IllegalNumberException{
		if( this.hasAsBullet(bullet)){
			bullets.remove(bullet);
			entities.remove(bullet);}
		else{throw new IllegalArgumentException();}
	}
	


	/**
	 * Variable referencing a set collecting all the bullets
	 * of this world.
	 * 
	 * @invar  The referenced set is effective.
	 *       | bullets != null
	 * @invar  Each bullet registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each bullet in bullets:
	 *       |   ( (bullet != null) &&
	 *       |     (! bullet.isTerminated()) )
	 */
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * give the entity at this position of this world if there is one
	 * @param position
	 * 		  the position to check
	 * @return null if there is no entity at the given position and if there is an entity, return the entity at the position.
	 */
	public Entity entityAtPosition(double[] position){
		for (Entity entity : entities) {
			if (entity.getPosition()[0]==position[0] && entity.getPosition()[1]==position[1])
				return entity;
		}
		return null;
	}
	
	/**
	 * 
	 * @return the set of entities of this world
	 */
	public Set<Entity> getEntities(){
		return this.entities;
	}
	
	/**
	 * 
	 * @return the set of bullets of this world
	 */
	public Set<Bullet> getBullets(){
		return this.bullets;
	}
	
	/**
	 * 
	 * @return the set of ships of this world
	 */
	public Set<Ship> getShips(){
		return this.ships;
	}
		
	/**
	 * destroy this world. terminate and remove all entities
	 */
	public void destroy(){
		for (Ship ship: this.getShips())
			ship.setWorld(null);
		for (Bullet bullet: this.getBullets())
			bullet.setWorld(null);
		this.ships= new HashSet<Ship>();
		this.bullets=new HashSet<Bullet>();
		this.entities=new HashSet<Entity>();

		this.terminate();
	}
	
	// methods advancing the collisions of this world

	/**
	 * 
	 * @return the time of the next collision in this world. infinity if the entities never collide.
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public double getTimeNextCollision() throws IllegalArgumentException, IllegalNumberException{
		if (this.hasProperEntities()){
		double timeFirstCollision=Double.POSITIVE_INFINITY;
		for (Entity entity: this.getEntities()){
			for (Entity entity2 : this.getEntities()){
				if (entity!=entity2 )
					{double t2=entity.getTimeToCollision(entity2);
					if (t2<=timeFirstCollision && t2>=0)
						timeFirstCollision=t2;
					}
				}
			double t1=entity.getTimeToCollisionBoundary();
			if (t1<=timeFirstCollision && t1>=0)
				timeFirstCollision=t1;
		}
		return timeFirstCollision;}
		return Double.POSITIVE_INFINITY;

		
	}
	
	/**
	 * 
	 * @return the position of the next collision of this world. null if the entities never collide.
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public double[] getPositionNextCollision() throws IllegalArgumentException, IllegalNumberException{
		if (this.hasProperEntities()){
		double time = this.getTimeNextCollision();
		for (Entity entity:this.getEntities()){
			double time1=entity.getTimeToCollisionBoundary();
			if (time1==time)
				return entity.getCollisionPositionBoundary();
			for (Entity entity2:this.getEntities()){
				double time2=entity.getTimeToCollision(entity2);
				if (time2==time && entity2!=entity)
					return entity.getCollisionPosition(entity2);}
			}}
		return null;
		
	}
	
	
	// evolve and help methods for evolve (no specification required for these methods)
	
	/**
	 * evolve this world by the given amount of time
	 * @param dt
	 * @param previousCollision
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public void evolve(double dt,Collision previousCollision) throws IllegalArgumentException, IllegalNumberException{

		if (dt>=0 && this.hasProperShips() && this.hasProperBullets()) {
			if (previousCollision!=null)
				previousCollisions.add(previousCollision);			
			
			Collision firstCollision=this.getFirstCollision();
			
			double t=firstCollision.getTimeToCollision();
			
			if (t!=0) {
				setPreviousCollisions(new HashSet<Collision>());
			}
			if (t<dt && t>=0){
				if (firstCollision.getEntity1()!=null || firstCollision.getEntity2()!=null){
					this.advance(t);
					this.resolveCollision(firstCollision);
					this.evolve(dt - t,firstCollision);
					}
				}
			this.advance(dt);
			}
	}
	
	/**
	 * advance the world by the given amount of time
	 * @param dt
	 * @throws IllegalNumberException
	 */
	public void advance(double dt) throws IllegalNumberException{
		if(dt>=0){
			for (Entity entity: this.getEntities()){
				entity.move(dt);
				}
			for (Ship ship:this.getShips()){
				if (ship.getStateThruster()==true)
					ship.thrust(dt);}}
	}
	
	/**
	 * resolve the given collision
	 * @param collision
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	
	public void resolveCollision(Collision collision) throws IllegalArgumentException, IllegalNumberException{
		for (Ship ship: this.getShips()){
				if (hasSameEntities(ship,null,collision)) {
					ship.collideWithBoundary(collision);
				}
			for (Ship ship2: this.getShips()){
				if (ship2!=ship && hasSameEntities(ship,ship2,collision) && this.shipsAlreadyCollided==false){
					ship.collideWithShip(ship2);
					this.shipsAlreadyCollided=true;
				}
			}
			for (Asteroid asteroid: this.getAsteroids()){
				if (hasSameEntities(ship,asteroid,collision)){
					asteroid.collideWithShip(ship);
				}
			}
		}
		for(Asteroid asteroid:this.getAsteroids()) {
			if (hasSameEntities(asteroid,null,collision))
				asteroid.collideWithBoundary(collision);
		}
		for (Bullet bullet: this.getBullets()){
			if (hasSameEntities(bullet,null, collision)){
				bullet.collideWithBoundary(collision);
				if (bullet.getCounter()==3)
					setBulletReachedBounceLimit(bullet);
				else{setBulletReachedBounceLimit(null);}}
			for (Entity entity: this.getEntities()){
				if (bullet!=entity){
					if (bullet.getSource()==entity && hasSameEntities(bullet,entity,collision) ){
						setCollisionBetweenBulletAndSource(bullet);}
					else if ( hasSameEntities(bullet,entity,collision) && bullet.getSource()!=entity && bullet.getSource()!=null){
						setCollisionBetweenBulletAndOtherEntity(bullet,entity);}
					}							
				}
			for (Ship ship: this.getShips()){
				if ( hasSameEntities(bullet,ship,collision) &&  bullet.getSource()==null)
					setCollisionWithBulletWithoutSource(bullet,ship);
					}}
		this.shipsAlreadyCollided=false;
		if (! this.getBullets().isEmpty())
			resolveBulletCollision();
		}
	
	/**
	 * resolve the bullet collisions if there are any
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public void resolveBulletCollision() throws IllegalArgumentException, IllegalNumberException{		
		DieBulletAfterCollision();
		if (getCollisionBetweenBulletAndSource()!=null){
			getCollisionBetweenBulletAndSource().bulletCollidesWithOwnShip(getCollisionBetweenBulletAndSource().getSource());
			setCollisionBetweenBulletAndSource(null);}
		if (getCollisionBetweenBulletAndOtherEntity()!=null &&getCollisionBetweenBulletAndOtherEntity2()!=null){
			getCollisionBetweenBulletAndOtherEntity().bulletCollidesWithOtherEntity(getCollisionBetweenBulletAndOtherEntity2());
			setCollisionBetweenBulletAndOtherEntity(null,null);}
		if (getCollisionWithBulletWithoutSource()!=null &&getCollisionWithBulletWithoutSource2()!=null){
			getCollisionWithBulletWithoutSource2().loadBullet(getCollisionWithBulletWithoutSource());
			setCollisionWithBulletWithoutSource(null,null);}
	}

	

	/**
	 * checks whether the given entities and time would make a valid collision
	 * @param entity1
	 * @param entity2
	 * @param time
	 * @return
	 */
	public boolean canHaveAsCollision(Entity entity1,Entity entity2,double time){
		if (previousCollisions==null)
			return true;
		for (Collision previousCollision: previousCollisions){
			if (time==0  && hasSameEntities(entity1,entity2, previousCollision))
				return false;}
		return true;		
	}
	
	/**
	 * get the first collision to happen in this world
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public Collision getFirstCollision() throws IllegalArgumentException, IllegalNumberException{
		double timeFirstCollision=Double.POSITIVE_INFINITY;
		Entity entity1=null;
		Entity entity2=null;
		for (Entity entity3: this.getEntities()){
			double t1=entity3.getTimeToCollisionBoundary();
			double t3=entity3.getTimeToCollisionBoundaryNotZero();
			if (t1<=timeFirstCollision && t1>=0 &&(canHaveAsCollision(entity3,null,t1))){
				timeFirstCollision=t1;
				entity1=entity3;
				entity2=null;}
			if (t3<=timeFirstCollision && t3>=0 &&(!canHaveAsCollision(entity3,null,t1) && t3!=Double.POSITIVE_INFINITY)){
					timeFirstCollision=t3;
					entity1=entity3;
					entity2=null;}
			for (Entity entity4 : this.getEntities()){
				if (entity3!=entity4)
					{double t2=entity4.getTimeToCollision(entity3);
					if (t2<=timeFirstCollision && t2>=0 && t2!=Double.POSITIVE_INFINITY && (canHaveAsCollision(entity3,entity4,t2))){
						timeFirstCollision=t2;
						entity1=entity4;
						entity2=entity3;
						}					
					}}
				}
		Collision firstCollision= new Collision(entity1,entity2,timeFirstCollision);
		return firstCollision;	
	}
	
	/**
	 * checks whether the given collision has the same entities as the given entities
	 * @param entity1
	 * @param entity2
	 * @param collision
	 * @return
	 */
	public boolean hasSameEntities(Entity entity1, Entity entity2, Collision collision){
		return ((collision.getEntity1()!=null || collision.getEntity2()!=null) &&((entity1==collision.getEntity1() && entity2==collision.getEntity2())||(entity1==collision.getEntity2() && entity2==collision.getEntity1())));
	}
	
	/**
	 * the bullet dies after hitting a boundary for the third time
	 * @throws IllegalArgumentException
	 * @throws IllegalNumberException
	 */
	public void DieBulletAfterCollision() throws IllegalArgumentException, IllegalNumberException{
		if (getBulletReachedBounceLimit()!=null)
			getBulletReachedBounceLimit().die();
		setBulletReachedBounceLimit(null);
	}
	
	/**
	 * set the bullet that reached the limit to collide with a boundary to the given bullet
	 * @param bullet
	 */
	public void setBulletReachedBounceLimit(Bullet bullet){
		this.reachedBounceLimit=bullet;
	}
	
	/**
	 * get the bullet that reached the limit to collide with a boundary
	 * @return
	 */
	public Bullet getBulletReachedBounceLimit(){
		return this.reachedBounceLimit;
	}  
	
	/**
	 * variable referencing the bullet that reached the limit to collide with a boundary
	 */
	private Bullet reachedBounceLimit;
	
	/**
	 * variable referencing the bullet that collides with its own ship
	 */
	private Bullet collisionBetweenBulletAndSource;
	
	/**
	 * set the bullet that collides with its own ship to this bullet 
	 * @param bullet
	 */
	public void setCollisionBetweenBulletAndSource(Bullet bullet){
		this.collisionBetweenBulletAndSource=bullet;
	}
	
	/**
	 * get the bullet that collides with its own ship 
	 * @return
	 */
	public Bullet getCollisionBetweenBulletAndSource(){
		return this.collisionBetweenBulletAndSource;
	}  	
	
	/**
	 * variable referencing the bullet that collides with an entity
	 */
	private Bullet collisionBetweenBulletAndOtherEntity;
	
	/**
	 * variable referencing the entity that collides with a bullet
	 */
	private Entity collisionBetweenBulletAndOtherEntity2;
	
	/**
	 * set the bullet that collides with an entity to the given bullet and the entity that collides with a bullet
	 * to the given entity
	 * @param bullet
	 * @param entity
	 */
	public void setCollisionBetweenBulletAndOtherEntity(Bullet bullet, Entity entity){
		this.collisionBetweenBulletAndOtherEntity=bullet;
		this.collisionBetweenBulletAndOtherEntity2=entity;
	}
	
	/**
	 * get the bullet that collides with an entity
	 * @return
	 */
	public Bullet getCollisionBetweenBulletAndOtherEntity(){
		return this.collisionBetweenBulletAndOtherEntity;
	} 
	
	/**
	 * get the entity that collides with a bullet
	 * @return
	 */
	public Entity getCollisionBetweenBulletAndOtherEntity2(){
		return this.collisionBetweenBulletAndOtherEntity2;
	}	
	
	/**
	 * variable referencing the bullet that collides with its source
	 */
	private Bullet collisionWithBulletWithoutSource;
	
	/**
	 * variable referencing the ship that collides with its fired bullet
	 */
	private Ship collisionWithBulletWithoutSource2;
	
	/**
	 * set the bullet that collides with its source to this bullet and the ship that collides with its fired bullet
	 * to this ship
	 * @param bullet
	 * @param ship
	 */
	public void setCollisionWithBulletWithoutSource(Bullet bullet, Ship ship){
		this.collisionWithBulletWithoutSource=bullet;
		this.collisionWithBulletWithoutSource2=ship;
	}
	
	/**
	 * get the bullet that collides with its source
	 * @return
	 */
	public Bullet getCollisionWithBulletWithoutSource(){
		return this.collisionWithBulletWithoutSource;
	} 
	
	/**
	 * get the ship that collides with its fired bullet
	 * @return
	 */
	public Ship getCollisionWithBulletWithoutSource2(){
		return this.collisionWithBulletWithoutSource2;
	}
	
	/**
	 * Variable referencing if the ships have already collides in the collision to resolve
	 */
	public boolean shipsAlreadyCollided=false;
	

	/**
	 * set referencing the previous collision that have happened at the same time 
	 */
	private Set<Collision> previousCollisions=new HashSet<Collision>();
	
	
	/**
	 * get the previous collision that have happened at the same time
	 * @return
	 */
	public Set<Collision> getPreviousCollisions(){
		return this.previousCollisions;
	}
	
	/**
	 * set the previous collision that have happened at the same time to the given set
	 * @param collision
	 */
	public void setPreviousCollisions(Set<Collision> collision){
		this.previousCollisions=collision;
	}
	

	/**
	 * a second addBullet method not checking whether this world can have the given bullet as its bullet
	 * (needed for loadShip() in class Ship)
	 * @param bullet
	 */
	public void addBullet2(Bullet bullet) {
		bullets.add(bullet);
		entities.add(bullet);	
	}	
	
	public Set<Asteroid> asteroids = new HashSet<Asteroid>();
	
	/**
	 * Check whether this world has the given ship as one of its
	 * ships.
	 * 
	 * @param  ship
	 *         The ship to check.
	 */
	@Basic
	@Raw
	public boolean hasAsAsteroid(@Raw Asteroid asteroid) {
		return asteroids.contains(asteroid);
	}

	/**
	 * Check whether this world can have the given ship
	 * as one of its ships.
	 * 
	 * @param  ship
	 *         The ship to check.
	 * @return True if and only if the given ship is effective
	 *         and that ship is a valid ship for a world and the ship doesn't overlap with other entities of this world 
	 *         and the ship is within boundaries of this world.
	 */
	@Raw
	public boolean canHaveAsAsteroid(Asteroid asteroid) throws IllegalNumberException {
		if ((asteroid == null) || (!asteroid.canHaveAsWorld(this)) )
			return false;
		if ((!this.canHaveAsHeightCoordinate(asteroid) || !this.canHaveAsWidthCoordinate(asteroid)))
			return false;
		for (Asteroid asteroid2 :this.getAsteroids()){
			if (asteroid.overlap(asteroid2) && asteroid!=asteroid2 )
					return false;				
		}
		for (Bullet bullet :this.getBullets()) {
			if (bullet.overlap(asteroid) )
					return false;			
		}
		return true;
	}
	
	
	/**
	 * Check whether this world has proper ships attached to it.
	 * 
	 * @return True if and only if this world can have each of the
	 *         ships attached to it as one of its ships,
	 *         and if each of these ships references this world as
	 *         the world to which they are attached.
	 *       | for each ship in Ship:
	 *       |   if (hasAsShip(ship))
	 *       |     then canHaveAsShip(ship) &&
	 *       |          (ship.getWorld() == this)
	 */
	public boolean hasProperAsteroids() throws IllegalNumberException{
		for (Asteroid asteroid:asteroids) {
			if (!canHaveAsAsteroid(asteroid))
				return false;
			if (asteroid.getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Return the number of ships associated with this world.
	 *
	 * @return  The total number of ships collected in this world.
	 *        | result ==
	 *        |   card({ship:Ship | hasAsShip({ship)})
	 */
	public int getNbAsteroids() {
		return asteroids.size();
	}

	/**
	 * Add the given ship to the set of ships of this world.
	 * 
	 * @param  ship
	 *         The ship to be added.
	 * @throws   IllegalArgumentException
	 * 			The world can't have the given ship as its ship or the world already has this ship as its ship
	 *         this world.
	 * @throws IllegalNumberException
	 * @post   This world has the given ship as one of its ships.
	 *       | new.hasAsShip(ship)
	 */
	public void addAsteroid(@Raw Asteroid asteroid) throws IllegalArgumentException, IllegalNumberException{
		//if (!canHaveAsAsteroid(asteroid) || this.hasAsAsteroid(asteroid))
			//throw new IllegalArgumentException();
		asteroids.add(asteroid);
		entities.add(asteroid);
	}

	/**
	 * Remove the given ship from the set of ships of this world.
	 * 
	 * @param  ship
	 *         The ship to be removed.
	 * @throws IllegalArgumentException 
	 *         This world doesn't have the given ship as one of
	 *         its ships
	 * @post   This world no longer has the given ship as
	 *         one of its ships.
	 *       | ! new.hasAsShip(ship)
	 */
	@Raw
	public void removeAsteroid(Asteroid asteroid) throws IllegalArgumentException{
		if( !this.hasAsAsteroid(asteroid))
			throw new IllegalArgumentException();
		asteroids.remove(asteroid);
		entities.remove(asteroid);
	}
	public Set<Asteroid> getAsteroids() {
		return asteroids;
	}
} 