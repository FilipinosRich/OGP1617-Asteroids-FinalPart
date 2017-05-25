package asteroids.expressions;

import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.expressions.*;
import asteroids.expressions.EntityExpressions.*;

public class GetPositionExpression<T> extends Expression<T> {
	public GetPositionExpression(Expression<?> e) {
		setExpression(e);
	}
	
	public void setExpression(Expression<?> expression) {
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
	public T getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public void execute(Ship ship) {
		getExpression().execute(ship);
		Entity e = (Entity) getExpression().getResult();
		Double pos = e.getPosition()[0];
		setResult((T) pos);
	}
	
	private T result;
	public void setResult(T ds) {
		this.result = ds;
	}


}
