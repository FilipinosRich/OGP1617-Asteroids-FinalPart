package asteroids.expressions;

import asteroids.model.Ship;
import asteroids.expressions.*;
import asteroids.expressions.EntityExpressions.*;

public class GetXExpression<T> extends Expression<T> {
	public GetXExpression(Expression<Ship> ship) {
		setExpression(ship);
	}
	
	public void setExpression(Expression<Ship> expression) {
		this.expression = expression;
	}
	
	private Expression<?> expression;
	
	public Expression<?> getExpression() {
		return expression;
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

	private Expression<Ship> result;
	public void setResult(ShipExpression result) {
		this.result = result;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		setResult((T)getExpression().getResult());
	}

	@Override
	public T getResult() {
		// TODO Auto-generated method stub
		return result;
	}
}
