package asteroids.tests;

import static org.junit.Assert.*;

import asteroids.model.Bullet;


import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.World;

import org.junit.Before;

public class ShipTests {
	private Ship aShip;
	
	private Bullet someBullet;
	private World someWorld;
	private static final double EPSILON = 0.0001;


	@Before
	public void setUp() throws Exception {
		aShip = new Ship(1,20,10,2,12,0,1);
		someBullet= new Bullet(5,5,10,10,20);
		someWorld= new World(100,100);	
		
	}
	
	// test Ship
	@Test
	public final void constructor_SingleCase() throws Exception {
		Ship newShip = new Ship(10,20,30,40,50,Math.PI,1);
		assertEquals(10, newShip.getPosition()[0],EPSILON);
		assertEquals(20, newShip.getPosition()[1],EPSILON);
		assertEquals(30,newShip.getVelocity()[0],EPSILON);
		assertEquals(40,newShip.getVelocity()[1],EPSILON);
		assertEquals(50,newShip.getRadius(),EPSILON);
		assertEquals(Math.PI,newShip.getOrientation(),EPSILON);
		
	}
	

	
	// test methods about orientation
	@Test
	public final void isValidOrientation_TrueCase(){
		assertTrue(Ship.isValidOrientation(Math.PI));
	}
	
	@Test
	public final void isValidOrientation_FalseCase(){
		assertFalse(Ship.isValidOrientation(3*Math.PI));
	}
	
	@Test
	public final void isValidOrientation_FalseCase2(){
		assertFalse(Ship.isValidOrientation(-Math.PI));
	}
	
	@Test(expected=AssertionError.class)
	public final void setOrientation_notValid(){
		aShip.setOrientation(3*Math.PI);
	}
	
	@Test
	public final void setOrientation_Valid(){
		aShip.setOrientation(Math.PI);
		assertEquals(Math.PI, aShip.getOrientation(),EPSILON);
	}
	
	// test methodes about radius
	@Test
	public final void isValidRadius_TrueCase(){
		assertTrue(aShip.canHaveAsRadius(20));
	}
	
	@Test
	public final void isValidRadius_FalseCase(){
		assertFalse(aShip.canHaveAsRadius(1));
	}
	
	@Test
	public final void isValidRadius_FalseCase2(){
		assertFalse(aShip.canHaveAsRadius(Double.NEGATIVE_INFINITY));
	}
	
		
	
	// test turn
	@Test(expected=AssertionError.class)
	public final void turn_newOrientationNotValid(){
		aShip.turn(3*Math.PI);
	}
	
	@Test 
	public final void turn_newOrientationValid(){
		aShip.turn(Math.PI);
		assertEquals(Math.PI,aShip.getOrientation(),EPSILON);
	}
	
	
	// test thrust	
	
	@Test
	public void thrust_thrusterDisabled(){
		aShip.thrustOff();
		aShip.thrust(20);
		assertEquals(10,aShip.getVelocity()[0],EPSILON);
		assertEquals(2,aShip.getVelocity()[1],EPSILON);
	}
	
	
	// test loadBullet
	@Test(expected=IllegalArgumentException.class)
	public final void loadBullet_bulletIsAlreadyLoadedOnOtherShip() throws Exception{
		someBullet.setPosition(5, 5);
		someBullet.setShip(aShip);
		Ship ship = new Ship(5,5,5,5,30,6,8);
		ship.setWorld(null);
		assertFalse(someBullet.canHaveAsShip(ship));
		assertNull(someBullet.getWorld());
		assertFalse(someBullet.canHaveAsShip(ship));
		ship.loadBullet(someBullet);
	}
	
	@Test
	public final void loadBullet_bulletIsBiggerThanShip() throws Exception{
		Bullet bullet = new Bullet(5,5,10,10,30);
		Ship ship = new Ship(5,5,5,5,20,6,8);
		bullet.setShip(null);
		ship.loadBullet(bullet);
		assertFalse(ship.hasAsBullet(bullet));
		assertNull(bullet.getShip());		
	}
	
	@Test
	public final void loadBullet_getBulletLoadedOnShip() throws Exception{
		Bullet bullet= new Bullet(65,65,0,15,5);
		bullet.setShip(null);
		bullet.setWorld(someWorld);
		someWorld.addBullet(bullet);
		Ship ship = new Ship(40,40,0,-15,10,6,Math.pow(10, 20));
		ship.setWorld(someWorld);
		someWorld.addShip(ship);
		ship.loadBullet(bullet);
		assertNull(bullet.getWorld());
		assertEquals(ship,bullet.getShip());
		assertFalse(someWorld.hasAsBullet(bullet));
	}
	
	//test fireBullet//
	
	@Test
	public final void fireBullet_bulletPlacedOutOfBoundaries() throws Exception{
		World world = new World(50,50);
		Bullet bullet= new Bullet(30,30,15,15,2);
		Ship ship = new Ship(40,40,5,5,10,2,Math.pow(10, 20));
		ship.setWorld(world);
		world.addShip(ship);
		ship.loadBullet(bullet);
		assertTrue(ship.hasAsBullet(bullet));
		ship.fireBullet();
		assertFalse(ship.hasAsBullet(bullet));
		assertTrue(bullet.isTerminated());
		
	}	
	
	@Test
	public final void fireBullet_bulletOverlapsWithOtherShip() throws Exception{
		World world = new World(80,80);
		Bullet bullet = new Bullet(30,30,15,15,5);
		Ship ship = new Ship(40,40,5,5,10,0,Math.pow(10, 20));
		Ship aShip = new Ship(61,40,5,5,10,0,6);
		ship.setWorld(world);
		world.addShip(ship);
		aShip.setWorld(world);
		world.addShip(aShip);		
		ship.loadBullet(bullet);
		assertTrue(ship.hasAsBullet(bullet));
		assertTrue(bullet.canHaveAsWorld(world));
		ship.fireBullet();
		assertFalse(ship.hasAsBullet(bullet));
		assertTrue(bullet.isTerminated());
		assertTrue(aShip.isTerminated());
	}
	
	@Test
	public void fireBullet_bulletIsPlacedRightInWorld() throws Exception{
		World world = new World(80,80);
		Bullet bullet = new Bullet(30,30,15,15,5);
		Ship ship = new Ship(40,40,5,5,10,0,Math.pow(10, 20));		
		ship.setWorld(world);
		world.addShip(ship);		
		ship.loadBullet(bullet);
		ship.fireBullet();
		assertEquals(bullet.getSpeed(),250,EPSILON);
		assertFalse(bullet.overlap(ship));
	}
	
	@Test
	public final void fireBullet_bulletGetsfiredNormally() throws Exception{
		Bullet bullet= new Bullet(30,30,15,15,2);
		Ship ship = new Ship(30,30,5,5,10,2,Math.pow(10, 20));
		ship.setWorld(someWorld);
		someWorld.addShip(ship);
		ship.loadBullet(bullet);
		assertTrue(ship.hasAsBullet(bullet));
		assertTrue(bullet.canHaveAsWorld(someWorld));
		assertTrue(someWorld.canHaveAsWidthCoordinate(bullet));
		assertTrue(someWorld.canHaveAsHeightCoordinate(bullet));
		ship.fireBullet();
		assertFalse(ship.hasAsBullet(bullet));
		assertTrue(someWorld.hasAsBullet(bullet));
		assertEquals(bullet.getSource(), ship);
		assertNull(bullet.getShip());
		assertEquals(bullet.getWorld(),ship.getWorld());
		assertTrue(someWorld.hasAsBullet(bullet));

	}


	
	//test ship boundary collision
	@Test
	public final void collideWithBoundary_shipCollidesWithUpperbound() throws Exception{
		aShip.setVelocity(0, 5);
		World world= new World(40,40);
		aShip.setPosition(16, 20);
		aShip.setWorld(world);
		world.addShip(aShip);
		aShip.move(aShip.getTimeToCollisionBoundary());
		
		assertEquals(0,aShip.getVelocity()[0],EPSILON);
		assertEquals(5,aShip.getVelocity()[1],EPSILON);	
	}	
	
	
	
		
}