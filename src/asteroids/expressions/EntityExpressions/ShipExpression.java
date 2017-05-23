package asteroids.expressions.EntityExpressions;

import asteroids.expressions.*;

public class ShipExpression<Ship> extends Expression{
	@Override
	public Ship getResult() {
		return entity;
	}
	private Ship entity;
	
	public void setShip(Ship unit) {
		this.entity = unit;
	}
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean hasAsSubExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toPostfix() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
