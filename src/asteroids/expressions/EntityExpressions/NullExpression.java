package asteroids.expressions.EntityExpressions;

import asteroids.expressions.Expression;
import asteroids.model.Ship;

public class NullExpression<T> extends Expression<T> {

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
	public T getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		setEntity(null);
	}
	
	private T result;
	
	public void setEntity(T entity) {
		this.result = entity;
	}

}
