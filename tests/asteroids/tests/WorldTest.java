package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.*;


public class WorldTest {	
	private Bullet someBullet;
	private Ship someShip;
	private World someWorld;
	private static final double EPSILON = 0.0001;

	@Before
	public void setUp() throws Exception {
		someShip = new Ship(1,20,10,2,12,0,1);
		someBullet= new Bullet(5,5,10,10,20);
		someWorld= new World(100,100);	
		
	}
	
	//test addShip && addBullet//
	@Test (expected = IllegalArgumentException.class)
	public void addShip_shipAlreadyInWorld()throws Exception{
		someWorld.addShip(someShip);
		someWorld.addShip(someShip);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addBullet_bulletAlreadyInWorld()throws Exception{
		someWorld.addBullet(someBullet);
		someWorld.addBullet(someBullet);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addShip_shipNotFullyWithinBounds() throws Exception{
		Ship ship = new Ship(200,200,2,2,10,2,2);
		someWorld.addShip(ship);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addBullet_bulletNotFullyWithinBounds() throws Exception{
		Bullet bullet = new Bullet(200,200,2,2,2);
		someWorld.addBullet(bullet);	
	}
	@Test (expected = IllegalArgumentException.class)
	public void addShip_shipOverlaps() throws Exception{
		Ship ship = new Ship(50,50,2,2,10,5,5);
		Bullet bBullet = new Bullet(50,50,5,6,5);
		someWorld.addBullet(bBullet);		
		someWorld.addShip(ship);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addBullet_bulletOverlaps() throws Exception{
		Bullet bullet = new Bullet(50,50,2,2,2);
		Bullet bBullet = new Bullet(50,50,5,6,5);
		someWorld.addBullet(bBullet);		
		someWorld.addBullet(bullet);	
	}	
	
	
	
	@Test
	public void getTimeNextCollision_timeNotInfinite() throws Exception {
		Ship aShip= new Ship(50,50,10,10,20,0,5);
		Bullet aBullet= new Bullet(20,20,10,10,2);
		World aWorld= new World(500,500);
		aWorld.addBullet(aBullet);
		aWorld.addShip(aShip);
		aShip.setWorld(aWorld);
		aBullet.setWorld(aWorld);

		assertNotSame(Double.POSITIVE_INFINITY,aWorld.getTimeNextCollision());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getTimeNextCollision_entitiesOverlap() throws Exception{
		Ship aShip= new Ship(20,20,10,10,20,0,5);
		Bullet aBullet= new Bullet(20,20,10,10,2);
		World aWorld= new World(500,500);
		aWorld.addBullet(aBullet);
		aWorld.addShip(aShip);
		aShip.setWorld(aWorld);
		aBullet.setWorld(aWorld);
		aWorld.getTimeNextCollision();	
	}
	
	
	@Test
	public void advance_entityMovesProperly() throws Exception{
		World aWorld = new World(5000,5000);
		Ship aShip= new Ship(50,50,-10,-10,20,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.advance(10);
		assertNotSame(50,aShip.getPosition()[0]);
		assertNotSame(50,aShip.getPosition()[1]);
	}
	
	@Test
	public void evolve_dtSmallerThanTimeToCollision() throws Exception{
		World aWorld = new World(5000,5000);
		Ship aShip= new Ship(50,50,-10,-10,20,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.evolve(2,null);
		assertNotSame(50,aShip.getPosition()[0]);
		assertNotSame(50,aShip.getPosition()[1]);
	}
	
	@Test
	public void evolve_dtBiggerThanTimeToCollision() throws Exception{
		World aWorld = new World(5000,5000);
		Ship aShip= new Ship(30,30,-10,-10,20,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.evolve(10000,null);
		assertNotSame(30,aShip.getPosition()[0]);
		assertNotSame(30,aShip.getPosition()[1]);
	}
	
	@Test
	public void evolve_shipCollidesWithWorldRightbound() throws Exception{
		World aWorld = new World(50,50);
		Ship aShip= new Ship(29,29,10,0,20,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.evolve(0.3,null);
		assertEquals(-10,aShip.getVelocity()[0],EPSILON);
		
	}
	
	@Test
	public void evolve_shipCollidesWithWorldUpperbound() throws Exception{
		World aWorld = new World(50,50);
		Ship aShip= new Ship(35,35,0,10,10,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.evolve(1,null);
		assertEquals(-10,aShip.getVelocity()[1],EPSILON);	
	}
	
	@Test
	public void evolve_shipCollidesWithWorldLowerbound() throws Exception{
		World aWorld = new World(50,50);
		Ship aShip= new Ship(15,15,0,-10,10,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.evolve(1,null);
		assertEquals(10,aShip.getVelocity()[1],EPSILON);	
	}
	
	@Test
	public void evolve_twoCollisionsAtTheSameTime() throws Exception{
		World aWorld = new World(50,50);
		Ship bShip= new Ship(35,25,0,10,10,0,5);
		Ship aShip= new Ship(15,25,0,-10,10,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		bShip.setWorld(aWorld);
		aWorld.addShip(bShip);
		aWorld.evolve(3,null);
		assertEquals(10,aShip.getVelocity()[1],EPSILON);
		assertEquals(-10,bShip.getVelocity()[1],EPSILON);
	}
	
	@Test
	public void evolve_twoCollisionsAtTheSameTime2() throws Exception{
		World aWorld = new World(100,100);
		Ship bShip= new Ship(85,25,10,0,10,0,5);
		Ship aShip= new Ship(25,15,0,-10,10,0,5);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		bShip.setWorld(aWorld);
		aWorld.addShip(bShip);
		aWorld.evolve(6,null);
		assertEquals(10,aShip.getVelocity()[1],EPSILON);
		assertEquals(-10,bShip.getVelocity()[0],EPSILON);
	}
	
	@Test
	public void evolve_pfffttt() throws Exception{
		World aWorld = new World(100,100);
		Ship aShip= new Ship(15,15,2,-10,10,0,5);
		Ship bShip= new Ship(30,30,5,20,10,0,6);
		Ship cShip= new Ship(49,49,2,3,10,0, 6);
		Bullet aBullet= new Bullet(15,3,5,10,2);
		Bullet bBullet= new Bullet(89,89,6,10,2);
		aShip.setWorld(aWorld);
		bShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		aWorld.addShip(bShip);
		aWorld.addShip(cShip);
		aBullet.setWorld(aWorld);
		bBullet.setWorld(aWorld);
		bBullet.setSource(bShip);;
		cShip.setWorld(aWorld);
		aWorld.addBullet(aBullet);
		aWorld.addBullet(bBullet);
		aWorld.evolve(100,null);
	}	
	
	@Test
	public void evolve_shipCollidesWithBulletWithSource() throws Exception{
		World aWorld = new World(50,50);
		Ship aShip= new Ship(15,15,0,-10,10,0,5);
		Ship bShip= new Ship(30,30,5,20,10,0,6);
		Bullet bullet= new Bullet(15,3,0,10,2);
		aShip.setWorld(aWorld);
		aWorld.addShip(aShip);
		bullet.setWorld(aWorld);
		aWorld.addBullet(bullet);
		bullet.setSource(bShip);
		aWorld.evolve(2,null);
		assertFalse(aWorld.hasAsBullet(bullet));
		assertTrue(bullet.isTerminated());
		assertFalse(aWorld.hasAsShip(aShip));
		assertTrue(aShip.isTerminated());
	}
	
	@Test
	public void evolve_shipCollidesWithBulletWithoutSource() throws Exception{
		World aWorld = new World(50,50);
		Ship aShip= new Ship(15,15,0,-10,10,0,5);
		Bullet bullet= new Bullet(15,3,0,10,2);
		aWorld.addShip(aShip);
		aShip.setWorld(aWorld);
		aWorld.addBullet(bullet);
		bullet.setWorld(aWorld);
		aWorld.evolve(2,null);
		assertTrue(aShip.hasAsBullet(bullet));
		assertFalse(aWorld.hasAsBullet(bullet));
		assertTrue(aWorld.hasAsShip(aShip));
		assertTrue(bullet.getShip()==aShip);
		assertNull(bullet.getWorld());
	}
	
	@Test
	public void evolve_bulletCollidesWithBoundary() throws Exception{
		World aWorld = new World(50,50);
		Bullet bullet= new Bullet(3,3,0,-10,2);
		bullet.setWorld(aWorld);
		aWorld.addBullet(bullet);
		aWorld.evolve(2,null);
		assertEquals(bullet.getVelocity()[1],10,EPSILON);
		
	}
	
	@Test
	public void evolve_bulletCollidesWithBoundaryAndDies() throws Exception{
		World aWorld = new World(10,10);
		Bullet bullet= new Bullet(3,3,0,-10,2);
		bullet.setWorld(aWorld);
		aWorld.addBullet(bullet);
		aWorld.evolve(30,null);
		assertFalse(aWorld.hasAsBullet(bullet));
		assertTrue(bullet.isTerminated());
		
	}
	@Test
	public void evolve_shipCollidesWithOtherShip() throws Exception{
		World aWorld = new World(100,100);
		Asteroid bAsteroid= new Asteroid(15,15,0,10,10);
		Asteroid aAsteroid= new Asteroid(15,40,0,-10,10);
		bAsteroid.setWorld(aWorld);
		aWorld.addAsteroid(bAsteroid);
		aAsteroid.setWorld(aWorld);
		aWorld.addAsteroid(aAsteroid);
		aWorld.evolve(0.5,null);
		assertEquals(aAsteroid.getVelocity()[1],-10,EPSILON);
		assertEquals(bAsteroid.getVelocity()[1],10,EPSILON);
		
		
		
	}
	
	
	@Test
	public void evolve_shouldWorkNormally() throws Exception{
		double dt=10;
		Ship aShip= new Ship(50,50,-10,-10,20,0,5);
		Bullet aBullet= new Bullet(20,20,10,10,2);
		World aWorld= new World(200,200);
		aWorld.addBullet(aBullet);
		aWorld.addShip(aShip);
		aShip.setWorld(aWorld);
		aBullet.setWorld(aWorld);
		aWorld.evolve(dt,null);
		Asteroid aAsteroid= new Asteroid(20,20,10,10,7);
		aAsteroid.setWorld(aWorld);
		aWorld.addAsteroid(aAsteroid);
	
		
	}

	@Test
	public void evolve_bulletCollidesWithCounter2() throws Exception{
		Ship aShip= new Ship(170,170,-10,-10,20,0,5);
		Bullet aBullet= new Bullet(20,20,10,10,2);
		World aWorld= new World(200,200);
		aShip.addBullet(aBullet);
		aBullet.setShip(aShip);
		aWorld.addShip(aShip);
		aShip.setWorld(aWorld);	
		aShip.fireBullet();
		assertTrue(aWorld.hasAsBullet(aBullet));
		aBullet.setCounter(2);
		aWorld.evolve(10,null);
		assertTrue(aBullet.isTerminated());
		
	}
	
	@Test 
	public void evolveAsteroid() throws Exception {
		Ship aShip= new Ship(170,170,-10,-10,20,0,5);
		Asteroid aAsteroid= new Asteroid(20,20,10,10,7);
		World aWorld= new World(200,200);
		aWorld.addAsteroid(aAsteroid);
		aWorld.addShip(aShip);
		assertTrue(aWorld.hasAsAsteroid(aAsteroid));
		aWorld.evolve(10, null);
	}
	
	@Test
	public void evolve_AsteroidCollidesWithOtherAsteroid() throws Exception{
		World aWorld = new World(100,100);
		Asteroid aAsteroid= new Asteroid(15,15,0,10,10);
		Asteroid bAsteroid= new Asteroid(15,40,0,-10,10);
		aAsteroid.setWorld(aWorld);
		aWorld.addAsteroid(aAsteroid);
		bAsteroid.setWorld(aWorld);
		aWorld.addAsteroid(bAsteroid);
		aWorld.evolve(0.5,null);
		assertEquals(aAsteroid.getVelocity()[1],10,EPSILON);
		assertEquals(bAsteroid.getVelocity()[1],-10,EPSILON);
	}
}