package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.IllegalNumberException;
import asteroids.model.Ship;
import asteroids.model.World;

public class BulletTest {

	private Ship someShip;
	private Bullet aBullet;
	private World someWorld;
	
	@Before
	public void setUp() throws Exception {
		someShip = new Ship(50,60,10,2,10,0,1);
		aBullet= new Bullet(15,15,10,10,2);
		someWorld= new World(100,100);	
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setWorld_bulletAlreadyHasShip(){
		aBullet.setShip(null);
		aBullet.setWorld(null);		
		aBullet.setShip(someShip);
		aBullet.setWorld(someWorld);
	}

	
	
	@Test(expected=IllegalArgumentException.class)
	public void setShip_bulletAlreadyHasWorld(){
		aBullet.setShip(null);
		aBullet.setWorld(null);			
		aBullet.setWorld(someWorld);	
		aBullet.setShip(someShip);
	}
	
	@Test
	public void die_test() throws Exception {
		someWorld.addBullet(aBullet);
		aBullet.setWorld(someWorld);
		aBullet.die();
		assertTrue(aBullet.isTerminated());
		assertFalse(someWorld.hasAsBullet(aBullet));
		
		
	}

}