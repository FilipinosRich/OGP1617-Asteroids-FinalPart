package asteroids.tests;

import static org.junit.Assert.*;

import asteroids.model.*;
import org.junit.*;

public class AsteroidTests {


	
	private Asteroid aAsteroid;
	
	private World someWorld;

	private Bullet someBullet;
	
	private static final double EPSILON = 0.0001;
	
	@Before
	public void setUp() throws Exception {
		aAsteroid = new Asteroid(1,20,10,2,12);
		someBullet= new Bullet(5,5,10,10,20);
		someWorld= new World(100,100);	
	}
	
	@Test
	public final void constructor_SingleCase() throws Exception {
		Asteroid newAsteroid = new Asteroid(10,20,30,40,50);
		assertEquals(10, newAsteroid.getPosition()[0],EPSILON);
		assertEquals(20, newAsteroid.getPosition()[1],EPSILON);
		assertEquals(30,newAsteroid.getVelocity()[0],EPSILON);
		assertEquals(40,newAsteroid.getVelocity()[1],EPSILON);
		
	}
	
	@Test
	public final void isValidRadius_TrueCase(){
		assertTrue(aAsteroid.canHaveAsRadius(20));
	}
	
	@Test
	public final void isValidRadius_FalseCase(){
		assertFalse(aAsteroid.canHaveAsRadius(1));
	}
	
	@Test
	public final void isValidRadius_FalseCase2(){
		assertFalse(aAsteroid.canHaveAsRadius(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public final void collideWithBoundary_shipCollidesWithUpperbound() throws Exception{
		aAsteroid.setVelocity(0, 5);
		World world= new World(40,40);
		aAsteroid.setPosition(16, 20);
		aAsteroid.setWorld(world);
		world.addAsteroid(aAsteroid);
		aAsteroid.move(aAsteroid.getTimeToCollisionBoundary());
		
		assertEquals(0,aAsteroid.getVelocity()[0],EPSILON);
		assertEquals(5,aAsteroid.getVelocity()[1],EPSILON);	
	}	
	
}
