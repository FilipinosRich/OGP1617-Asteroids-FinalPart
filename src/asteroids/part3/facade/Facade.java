package asteroids.part3.facade;
 
import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.IllegalNumberException;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.model.Asteroid;
//import asteroids.part3.facade.Planetoid;
import asteroids.model.Program;
import asteroids.model.ProgramFactory;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;
public class Facade implements asteroids.part3.facade.IFacade {
 
    @Override
    public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
            throws ModelException {
        try {
            return new Ship(x,y,xVelocity,yVelocity,radius,orientation,mass);
           
        } catch(IllegalNumberException Ship) {
            throw new ModelException(Ship);
        }
    	catch(AssertionError Ship){
    		throw new ModelException(Ship);
    	} 
        catch (IllegalArgumentException Ship){
        	throw new ModelException(Ship);
        }
    }
 
	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

    @Override
    public double[] getShipPosition(Ship ship) throws ModelException {
        return ship.getPosition();
    }
 
    @Override
    public double[] getShipVelocity(Ship ship) throws ModelException {
        return ship.getVelocity();
    }
 
    @Override
    public double getShipRadius(Ship ship) throws ModelException {
        return ship.getRadius();
    }
 
    @Override
    public double getShipOrientation(Ship ship) throws ModelException {
        return ship.getOrientation();
    }
 
    @Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
    }
    
	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

    @Override
    public void turn(Ship ship, double angle) throws ModelException {
        ship.turn(angle);
    }
    
	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.getStateThruster();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active == true)
			ship.thrustOn();
		if (active == false)
			ship.thrustOff();
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}
    
	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
        try {
            return new Bullet(x, y, xVelocity, yVelocity, radius);
           
        } catch(IllegalNumberException Bullet) {
            throw new ModelException(Bullet);
        }
    	catch(AssertionError Bullet){
    		throw new ModelException(Bullet);
    	} 
        catch (IllegalArgumentException Bullet){
        	throw new ModelException(Bullet);
        }
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocity();
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSource();
	}
	
	@Override
	public World createWorld(double width, double height) throws ModelException {
        try {
            return new World(width , height);
        }
    	catch(AssertionError World){
    		throw new ModelException(World);
    	} 
        catch (IllegalArgumentException World){
        	throw new ModelException(World);
        }
    }

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		double[] size= {world.getWidth(),world.getHeight()};
		return size;
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addShip(ship);
			ship.setWorld(world);
		} catch (IllegalArgumentException | IllegalNumberException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeShip(ship);
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addBullet(bullet);
			bullet.setWorld(world);
		} catch (IllegalArgumentException | IllegalNumberException e) {
			throw new ModelException(e);
		}		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeBullet(bullet);
		} catch (IllegalArgumentException | IllegalNumberException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try{
			try {
				ship.loadBullet(bullet);
			} catch (IllegalNumberException e) {
				throw new ModelException(e);
			}
		} catch(IllegalArgumentException Bullet){
			throw new ModelException(Bullet);
		}
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.loadBullets(bullets);
		} catch (IllegalArgumentException | IllegalNumberException e) {
			throw new ModelException(e);
		}		
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);

	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		try {
			ship.fireBullet();
		} catch (IllegalNumberException  | IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	
    @Override
    public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
        try {
			return ship1.getDistanceBetween(ship2);
		} catch (IllegalNumberException e) {
			throw new ModelException(e);
		}
    }
 
    @Override
    public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
        try {
			return ship1.overlap(ship2);
		} catch (IllegalNumberException e) {
			throw new ModelException(e);
		}
    }
 
    @Override
    public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
        try {
            return ship1.getTimeToCollision(ship2);
        } catch(IllegalArgumentException | IllegalNumberException a){
        	throw new ModelException(a);
        }
    }
 
    @Override
    public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
        try {
            return ship1.getCollisionPosition(ship2);  
        } catch(IllegalNumberException e) {
            throw new ModelException(e);
        }
        catch(IllegalArgumentException a){
        	throw new ModelException(a);
        }
 
    }



	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getTimeToCollisionBoundary();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity)object).getCollisionPositionBoundary();
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {

			try {
				return ((Entity) entity1).getTimeToCollision((Entity) entity2);
			} catch (IllegalArgumentException | IllegalNumberException  e) {
				throw new ModelException(e);
			}

	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {

			try {
				return ((Entity) entity1).getCollisionPosition(((Entity) entity2));
			} catch (IllegalArgumentException | IllegalNumberException e) {
				throw new ModelException(e);
			}
	}


	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		try {
			return world.getTimeNextCollision();
		} catch (IllegalArgumentException | IllegalNumberException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		try {
			return world.getPositionNextCollision();
		} catch (IllegalArgumentException | IllegalNumberException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
			try {
				world.evolve(dt,null);
			} catch (IllegalNumberException e) {
				throw new ModelException(e);
			} catch (IllegalArgumentException e) {
				throw new ModelException(e);
			}

	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		double[] position = {x,y};
		return world.entityAtPosition(position);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}

	@Override
	public int getNbStudentsInTeam() {
		return 1;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return world.getAsteroids();
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
		world.addAsteroid(asteroid);
		asteroid.setWorld(world);
		} catch(IllegalNumberException e) {
			throw new ModelException(e);
		} catch(IllegalArgumentException e) {
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeAsteroid(asteroid);
	}

	/*@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Asteroid(x,y,xVelocity,yVelocity,radius);
		} catch(IllegalNumberException | IllegalArgumentException e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return asteroid.getPosition();
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return asteroid.getVelocity();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	/*@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

*/	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		// TODO Auto-generated method stub
		ship.setProgram(program);
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		ship.executeProgram(dt);
		return ship.getProgram().getStatement().getKeeper();
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		// TODO Auto-generated method stub
		return new ProgramFactory();
	}
 
}