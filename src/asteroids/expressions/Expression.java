package asteroids.expressions;

import asteroids.model.*;
import asteroids.statements.Statement;
import asteroids.expressions.*;





public abstract class Expression<T> extends Statement implements Cloneable {
	
	




	public abstract boolean hasAsSubExpression(Expression expression);


	
	public boolean isIdenticalTo(Expression other) {
		if (other == null)
			 return false;
		if (this.getClass() != other.getClass())
			return false;
		if (this.equals(other))
			return true;
		return false;
	}


	
	private static final long serialVersionUID = 1L;
	


	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}
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



	public abstract double getValue();


	
	
}
