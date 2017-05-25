package asteroids.model;
import java.util.*;

import asteroids.expressions.Expression;
import asteroids.functions.*;
import asteroids.statements.*;




public class Program {
	public Program(List<Function> functions, Statement statement) {
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
	
	private Expression<?> expression;
	
	public void setExpression(Expression<?> expression) {
		this.expression = expression;
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	/**
	 * keeping track of multiple statements
	 */
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
	
	public Object getResult(Expression<?> expression) {
		return expression.getResult();
	}
	public Map<String,Object> assignment = new HashMap<String,Object>();
	
	public Map<String,Object> getAssignment() {
		return assignment;
	}
}
