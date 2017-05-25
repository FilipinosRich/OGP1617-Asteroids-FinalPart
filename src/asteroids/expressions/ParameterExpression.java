package asteroids.expressions;

import asteroids.model.Ship;

public class ParameterExpression<T> extends Expression<T>{
	public ParameterExpression(String parameterName) {
		setParameter(parameterName);
	}
	
	public void setParameter(String parameterName) {
		this.parameterName = parameterName;
	}
	
	private String parameterName;

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
		return (T) parameterName;
	}
	
	@Override
	public void execute(Ship ship) {
		
	}

}
