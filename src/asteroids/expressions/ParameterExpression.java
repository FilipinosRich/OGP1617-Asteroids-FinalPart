package asteroids.expressions;

public class ParameterExpression<T> extends Expression<T>{
	public ParameterExpression(String parameterName) {
		setParameter(parameterName);
	}

}
