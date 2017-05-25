package asteroids.expressions.EntityExpressions;

import asteroids.expressions.*;
import asteroids.model.Ship;

public class ShipExpression<T> extends Expression<T>{
	@Override
	public T getResult() {
		return (T) ship;
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
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		setShip(ship);
	}
	
	private T ship;
	public void setShip(T ship) {
		this.ship = ship;
	}
}
