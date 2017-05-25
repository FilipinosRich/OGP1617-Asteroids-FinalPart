package asteroids.expressions;

import asteroids.model.Entity;
import asteroids.model.Ship;

public class GetRadiusExpression<T> extends Expression<T> {
	
	public GetRadiusExpression(Expression<?> e) {
		setExpression(e);
	}
	
	private Expression<?> expression;
	public void setExpression(Expression<?> e) {
		this.expression = e;
	}
	
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
	public T getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		getExpression().execute(ship);
		Entity e = (Entity) getExpression().getResult();
		Double rad = e.getRadius();
		setResult((T) rad);
	}
	
	private T result;
	
	public void setResult(T ds) {
		this.result = ds;
	}

}
