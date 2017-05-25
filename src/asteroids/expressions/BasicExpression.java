package asteroids.expressions;

import asteroids.expressions.*;;

public abstract class BasicExpression extends Expression {

	@Override
	public final boolean hasAsSubExpression(Expression expression) {
		return expression == this;
	}
	
	
	public String toPostfix() {
		return toString();
	}
}
