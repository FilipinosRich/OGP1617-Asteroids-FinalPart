package asteroids.expressions.EntityExpressions;

import asteroids.expressions.Expression;
import asteroids.model.Ship;

public class SelfExpression<T> extends Expression {
	@Override 
	public T getResult() {
		return (T) ship;
	}
	
	
	public void setShip(T ship) {
		this.ship = ship;
	}
	private T ship;
	
	@Override
	public void execute(Ship ship) {
		this.setShip((T)ship);
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
	
	
}
