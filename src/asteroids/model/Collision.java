package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
/**
 * @invar  Each collision can have its entity1 as entity1.
 *       | canHaveAsEntity1(this.getEntity1())
 * @invar  Each collision can have its entity2 as entity2 .
 *       | canHaveAsEntity2(this.getEntity2())       
 * @invar  Each collision can have its timeToCollision as timeToCollision .
 *       | canHaveAsTimeToCollision(this.getTimeToCollision())
 */

public class Collision {
	/**
	 * Initialize this new collision with given entity1, entity2 and timeToCollision.

	 * @param  entity1
	 *         The entity1 for this new collision.
	 * @param entity2
	 * 		  the entity2 for this new collision
	 * @param timeToCollision	 
	 * 		  The timeToCollision for this new collision.
	 * @post   If the given entity1 is a valid entity1 for any collision,
	 *         the entity1 of this new collision is equal to the given
	 *         entity1. Otherwise, the entity1 of this new collision is equal
	 *         to null.
	 *       | if (canHaveAsEntity1(entity1))
	 *       |   then new.getEntity1() == entity1
 	 *       |   else new.getEntity1() == null
	 * @post   If the given entity2 is a valid entity2 for any collision,
	 *         the entity2 of this new collision is equal to the given
	 *         entity2. Otherwise, the entity2 of this new collision is equal
	 *         to null.
	 *       | if (canHaveAsEntity2(entity2))
	 *       |   then new.getEntity2() == entity2
 	 *       |   else new.getEntity2() == null       
 	 * @post   If the given timeToCollision is a valid timeToCollision for any collision,
 	 *         the timeToCollision of this new collision is equal to the given
 	 *         timeToCollision. Otherwise, the timeToCollision of this new collision is equal
 	 *         to 0.
 	 *       | if (isValidTimeToCollision(timeToCollision))
 	 *       |   then new.getTimeToCollision() == timeToCollision
 	 *       |   else new.getTimeToCollision() == 0
 */
	public Collision(Entity entity1,Entity entity2, double timeToCollision){
		if (! canHaveAsEntity1(entity1))
			entity1=null;
		this.entity1=entity1;
		if (! canHaveAsEntity2(entity2))
			entity2=null;
		this.entity2=entity2;
		if (!canHaveAsTimeToCollision(timeToCollision))
			timeToCollision=0;
		this.timeToCollision=timeToCollision;
		
	}
	

	/**
	 * Terminate this collision.
	 *
	 * @post   This collision  is terminated.
	 *       | new.isTerminated()
	 */
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this collision
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
	 * Return the entity1 of this collision.
	 */
	@Basic @Raw @Immutable
	public Entity getEntity1() {
		return this.entity1;
	}

	/**
	 * Check whether this collision can have the given entity1 as its entity1.
	 *  
	 * @param  entity1
	 *         The entity1 to check.
	 * @return 
	 *       | result == true
	 */
	@Raw
	public boolean canHaveAsEntity1(Entity entity1) {
		return true;
	}

	/**
	 * Variable registering the entity1 of this collision.
	 */
	private final Entity entity1;
	




	/**
	 * Return the entity2 of this collision.
	 */
	@Basic @Raw @Immutable
	public Entity getEntity2() {
		return this.entity2;
	}

	/**
	 * Check whether this collision can have the given entity2 as its entity2.
	 *  
	 * @param  entity2
	 *         The entity2 to check.
	 * @return 
	 *       | result == true;
	 */
	@Raw
	public boolean canHaveAsEntity2(Entity entity2) {
		return true;
	}

	/**
	 * Variable registering the entity2 of this collision.
	 */
	private final Entity entity2;

	/**
	 * Return the timeToCollision of this collision.
	 */
	@Basic @Raw @Immutable
	public double getTimeToCollision() {
		return this.timeToCollision;
	}
	
	/**
	 * Check whether this collision can have the given timeToCollision as its timeToCollision.
	 *  
	 * @param  timeToCollision
	 *         The timeToCollision to check.
	 * @return 
	 *       | result == ((Double.isFinite(timeToCollision) ||timeToCollision==Double.POSITIVE_INFINITY) && timeToCollision>=0)
	 */
	@Raw
	public boolean canHaveAsTimeToCollision(double timeToCollision) {
		return ((Double.isFinite(timeToCollision) ||timeToCollision==Double.POSITIVE_INFINITY) && timeToCollision>=0);
	}
	
	/**
	 * Variable registering the timeToCollision of this collision.
	 */
	private final double timeToCollision;
	
	/**
	 * Return the world of this collision.
	 */
	@Basic @Raw @Immutable
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Check whether this collision can have the given world as its world.
	 *  
	 * @param  world
	 *         The world to check.
	 * @return 
	 *       | result == ((world!=null && !world.isTerminated()) || (isTerminated() && world==null) 
	 */	
	@Raw
	public boolean canHaveAsWorld(World world){
		if (isTerminated())
			return world==null;
		else
			return(world!=null && !world.isTerminated());
		
	}
	
	/**
	 * 
	 * @return 
	 * 		  | result == (canHaveAsWorld(this.getWorld()))
	 */
	public boolean hasProperWorld(){
			if (!canHaveAsWorld(this.getWorld()))
				return false;
		return true;
	}
	
	
	/**
	 * Variable registering the world of this collision.
	 */
	private World world;
	
	/**
	 * checks whether 2 collisions have the same ships
	 * @param collision
	 * 		  the collision to check
	 * @return 
	 * 		  | result == (this.getEntity1()==collision.getEntity1() && this.getEntity2()==collision.getEntity2()) ||
				( this.getEntity1()==collision.getEntity2() && this.getEntity2()==collision.getEntity2())
	 */
	public boolean involvesSameShips(Collision collision){
		return (this.getEntity1()==collision.getEntity1() && this.getEntity2()==collision.getEntity2()) ||
				( this.getEntity1()==collision.getEntity2() && this.getEntity2()==collision.getEntity2());			
	}
	
	
	
	
}
