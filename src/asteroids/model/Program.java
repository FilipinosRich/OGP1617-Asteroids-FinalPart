package asteroids.model;
import java.util.*;

import asteroids.functions.*;
import asteroids.statements.*;




public class Program {
	public Program(List<Function> function, Statement statement) {
		setStatement(statement);
		setAvailable(true);
		
	}
	
	public boolean getAvailability() {
		return isAvailable;
	}
	public void setAvailable(boolean availability) {
		this.isAvailable = availability;
	}
	
	private boolean isAvailable;
	
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	private Statement statement;
	
	public List<Statement> sequence = new ArrayList<Statement>();
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	private World world;
	
	public World getWorld() {
		return world;
	}
	
	private Ship ship;
	
	public void setShip(Ship ship) {
		this.ship = ship;
		statement.setShip(ship);
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public Map<String,Object> assignment = new HashMap<String,Object>();
}
