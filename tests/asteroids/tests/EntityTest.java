package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.IllegalNumberException;
import asteroids.model.Ship;
import asteroids.model.World;

public class EntityTest {
	private Entity aEntity;
	private Ship someShip;
	private Bullet someBullet;
	private World someWorld;
	private static final double EPSILON = 0.0001;

	@Before
	public void setUp() throws Exception {
		aEntity = new Entity(1,20,10,2,12);
		someShip= new Ship(5,5,10,10,20,0,5);
		someBullet= new Bullet(5,5,10,10,20);
		someWorld= new World(20,20);		
	}
	
	// test isValidNumber
	@Test
	public final void isValidNumber_TrueCase() {
		assertTrue(Entity.isValidNumber(1));
	}
	
	@Test
	public final void isValidNumber_FalseCase(){
		assertFalse(Entity.isValidNumber(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public final void isValidNumber_FalseCase2(){
		assertFalse(Entity.isValidNumber(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public final void isValidNumber_FalseCase3(){
		assertFalse(Entity.isValidNumber(Double.NaN));
	}
	
	
	// test methods about position
	@Test(expected = IllegalArgumentException.class)
	public final void setPosition_xNotValidPositioncoordinate() throws Exception {
		aEntity.setPosition(Double.NaN,5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void setPosition_yNotValidPositioncoordinate() throws Exception {
		aEntity.setPosition(4,Double.NEGATIVE_INFINITY);
	}
	
	@Test
	public final void setPosition_xAndyValidCoordinates() throws Exception {
		aEntity.setPosition(4,5);
		assertEquals(4,aEntity.getPosition()[0],EPSILON);
		assertEquals(5,aEntity.getPosition()[1],EPSILON);
	}
	
	
	// test methods about velocity
	
	@Test
	public final void isValidSpeedLimit_FalseCase(){
		assertFalse(Entity.isValidSpeedLimit(4000000));
	}
	
	@Test
	public final void isValidSpeedLimit_TrueCase(){
		assertTrue(Entity.isValidSpeedLimit(20000));
	}
	
	
	@Test
	public final void setSpeedLimit_limitNormal(){
		aEntity.setSpeedLimit(10);
		assertEquals(10,aEntity.getSpeedLimit(),EPSILON);
	}
	
	@Test
	public final void setSpeedLimit_limitNotValid(){
		aEntity.setSpeedLimit(400000);
		assertEquals(300000,aEntity.getSpeedLimit(),EPSILON);
	}
	
	@Test
	public final void isValidSpeed_TrueCase(){
		assertTrue(Entity.isValidSpeed(20000));
	}
	
	@Test
	public final void isValidSpeed_FalseCase(){
		assertFalse(Entity.isValidSpeed(400000));
	}
	
	
	@Test
	public final void setVelocity_xNotValidNumber() {
		aEntity.setVelocity(Double.NaN, 5);
		assertEquals(aEntity.getSpeedLimit()/Math.sqrt(2),aEntity.getVelocity()[0],EPSILON);
	}
	
	@Test
	public final void setVelocity_yNotValidNumber(){
		aEntity.setVelocity(5,Double.POSITIVE_INFINITY);
		assertEquals(aEntity.getSpeedLimit()/Math.sqrt(2),aEntity.getVelocity()[1],EPSILON);
	}
	
	@Test
	public final void setVelocity_xAndyValidNumbers(){
		aEntity.setVelocity(5,6);
		assertEquals(5,aEntity.getVelocity()[0],EPSILON);
		assertEquals(6,aEntity.getVelocity()[1],EPSILON);
	}
	
	@Test
	public final void setVelocity_speedTooFast(){
		aEntity.setSpeedLimit(100000);
		aEntity.setVelocity(200000, 200000);
		assertEquals(aEntity.getSpeedLimit()/Math.sqrt(2),aEntity.getVelocity()[0],EPSILON);
		assertEquals(aEntity.getSpeedLimit()/Math.sqrt(2),aEntity.getVelocity()[1],EPSILON);
	}
	
	@Test
	public final void setVelocity_speedNormal(){
		aEntity.setSpeedLimit(10);
		aEntity.setVelocity(1, 1);
		assertEquals(1,aEntity.getVelocity()[0],EPSILON);
		assertEquals(1,aEntity.getVelocity()[1],EPSILON);
	}
	
	//tests methods associating World with Entity
	
	@Test
	public final void canHaveAsWorld_entityIsDestroyedAndWorldIsNotNull() throws Exception{
		Entity terminatedEntity= new Entity(50,500,50,500,5);
		terminatedEntity.terminate();
		assertFalse(terminatedEntity.canHaveAsWorld(someWorld));
	}

	
	@Test
	public final void canHaveAsWorld_entityIsDestroyedAndWorldIsNull() throws Exception{
		Entity terminatedEntity= new Entity(50,500,50,500,5);
		terminatedEntity.terminate();
		assertTrue(terminatedEntity.canHaveAsWorld(null));
	}
	
	@Test
	public final void canHaveAsWorld_entityIsAlreadyAssociatedWithAnotherWorld() throws Exception{
		World aWorld= new World(5000,50000);
		aEntity.setWorld(aWorld);
		assertFalse(aEntity.canHaveAsWorld(someWorld));
	}
	
	@Test
	public final void canHaveAsWorld_entityIsNotAssociatedWithAnotherWorld() throws Exception{
		aEntity.setWorld(null);
		assertTrue(aEntity.canHaveAsWorld(someWorld));
	}
	
	@Test
	public final void canHaveAsWorld_worldIsTerminated() throws Exception {
		World aWorld= new World(5000,50000);
		aWorld.terminate();
		Entity entity=new Entity(5,5,5,5,5);
		assertFalse(entity.canHaveAsWorld(aWorld));
	}
	
	@Test
	public void hasProperWorld_worldIsImproper() throws Exception{
		Bullet bullet = new Bullet(5,5,5,5, 5);
		World aWorld= new World(5000,50000);
		bullet.setWorld(aWorld);
		aWorld.addBullet(bullet);
		aWorld.terminate();
		assertFalse(bullet.hasProperWorld());	
	}
	
	@Test
	public void hasProperWorld_nullIsProperWorld() throws Exception{
		aEntity.setWorld(null);
		assertTrue(aEntity.hasProperWorld());
	}
	
	@Test (expected= IllegalArgumentException.class)
	public void setWorld_entityAlreadyHasAWorld() throws Exception{
		Entity entity = new Entity(5,5,5,5, 5);
		entity.setWorld(someWorld);
		World aWorld= new World(5000,50000);		
		entity.setWorld(aWorld);
	}
	
	@Test 
	public void setWorld_entityDoesNotAlreadyHasAWorld() throws Exception{
		Entity entity = new Entity(5,5,5,5, 5);
		entity.setWorld(null);
		World aWorld= new World(5000,50000);		
		entity.setWorld(aWorld);
		assertEquals(entity.getWorld(),aWorld);
	}
	
	@Test 
	public void setWorld_entitySetWorldNull() throws Exception{
		Entity entity = new Entity(5,5,5,5, 5);
		entity.setWorld(someWorld);		
		entity.setWorld(null);
		assertNull(entity.getWorld());
	}	
	
	
	// tests predicting collisions
	
	// test move
	@Test (expected=IllegalNumberException.class)
	public final void move_durationNegative() throws Exception{
		aEntity.move(-10);
	}
	
	@Test 
	public final void move_speedZero() throws Exception{
		aEntity.setVelocity(0, 0);
		aEntity.move(10);
		assertEquals(1,aEntity.getPosition()[0],EPSILON);
		assertEquals(20,aEntity.getPosition()[1],EPSILON);
	}
	
	@Test 
	public final void move_durationZero() throws Exception{
		
		aEntity.move(0);
		assertEquals(1,aEntity.getPosition()[0],EPSILON);
		assertEquals(20,aEntity.getPosition()[1],EPSILON);
	}
	
	@Test 
	public final void move_shipMoves() throws Exception{
		aEntity.move(10);
		assertEquals(101,aEntity.getPosition()[0],EPSILON);
		assertEquals(40,aEntity.getPosition()[1],EPSILON);
	}
	
	// test getDistanceBetween
	@Test
	public final void getDistanceBetween_distanceWithItself() throws Exception{
		
		assertEquals(0,aEntity.getDistanceBetween(aEntity),EPSILON);
	}
	
	// test overlap

	@Test
	public final void overlap_falseCase() throws Exception{
		Entity bEntity = new Entity(20,0,2,4,10);
		assertFalse(aEntity.overlap(bEntity));
	}
	
	@Test
	public final void overlap_trueCase() throws Exception{
		Entity bEntity = new Entity(0,0,2,4,10);
		assertTrue(aEntity.overlap(bEntity));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void getTimeToCollision_overlappingShips() throws Exception{
		Entity bEntity = new Entity(0,0,2,4,10);
		aEntity.getTimeToCollision(bEntity);		
	}
	
	// test getTimeToCollision
	@Test
	public final void getTimeToCollision_scalarProductGreaterThanOrEqualToZero() throws Exception{
		Entity bEntity = new Entity (5,200,10,2,10);
		assertEquals(Double.POSITIVE_INFINITY,aEntity.getTimeToCollision(bEntity),EPSILON);
	}
	
	@Test
	public final void getTimeToCollision_calculatedDlessThanOrEqualToZero() throws Exception{
		Entity bEntity = new Entity (-100,-100,5,5,100);
		assertEquals(Double.POSITIVE_INFINITY,aEntity.getTimeToCollision(bEntity),EPSILON);
	}
	
	@Test
	public final void getTimeToCollision_finiteCase() throws Exception{
		Entity entity1 = new Entity(100,200,10,0,5);
		Entity entity2 = new Entity(150,200,-10,0,5);
		double time = entity1.getTimeToCollision(entity2);
		assertNotNull(time);
		assertEquals(2,time,EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void getCollisionPosition_overlappingShips() throws Exception{
		Entity bEntity = new Entity(0,0,2,4,10);
		aEntity.getTimeToCollision(bEntity);		
	}
	
	//test getCollisionPosition
	@Test
	public final void getCollisionPosition_getTimeToCollisionInfinite() throws Exception{
		Entity bEntity = new Entity (-100,-100,5,5,100);
		assertNull(aEntity.getCollisionPosition(bEntity));
	}
	
	//test getTimeBoundaryCollision
	
	@Test
	public final void getTimeToCollisionBoundary_collidesWithUpperBound() throws Exception{
		aEntity.setVelocity(4, 5);
		World world= new World(30,40);
		aEntity.setWorld(world);
		aEntity.setPosition(18, 20);
		assertEquals(0,aEntity.getTimeToCollisionBoundary(),EPSILON);	
	}
	
	@Test 
	public final void getTimeToCollisionBoundary_entityNeverCollides() throws Exception{
		World world = new World(Double.MAX_VALUE,Double.MAX_VALUE);
		aEntity.setWorld(world);
		aEntity.setVelocity(0, 0);
		assertEquals(Double.POSITIVE_INFINITY,aEntity.getTimeToCollisionBoundary(),EPSILON);
	}
	
	@Test
	public final void getCollisionPositionBoundary_collidesWithUpperBound() throws Exception{
		aEntity.setVelocity(4, 5);
		World world= new World(30,40);
		aEntity.setWorld(world);
		aEntity.setPosition(18, 20);
		assertEquals(30,world.getWidth(),EPSILON);
		assertEquals(40,world.getHeight(),EPSILON);
		assertEquals(30,aEntity.getCollisionPositionBoundary()[0],EPSILON);
		assertEquals(20,aEntity.getCollisionPositionBoundary()[1],EPSILON);	
	}
		
}
