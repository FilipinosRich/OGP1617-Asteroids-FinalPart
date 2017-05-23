package asteroids.expressions;

import asteroids.model.*;
import asteroids.statements.Statement;





public abstract class Expression<T> extends Statement implements Cloneable {
	
	



	public abstract double getValue();
	
	public abstract boolean hasAsSubExpression(Expression expression);

	
	public abstract boolean isMutable();

	@Override
	public Expression clone() {
		try {
			if (isMutable())
				return (Expression) super.clone();
			else
				return this;
		} catch (CloneNotSupportedException exc) {
			throw new AssertionError(exc);
		}
	}
	
	
	public boolean isIdenticalTo(Expression other) {
		if (other == null)
			 return false;
		if (this.getClass() != other.getClass())
			return false;
		if (this.equals(other))
			return true;
		return false;
	}

	
	@Override
	public int hashCode() {
		if (! this.isMutable())
			return (int) getValue();
		else
			return super.hashCode();
	}


	@Override
	public abstract String toString();


	public abstract String toPostfix();

	
	private static final long serialVersionUID = 1L;
	
	public abstract T getResult();
	
	public abstract void execute(Ship ship);
}
