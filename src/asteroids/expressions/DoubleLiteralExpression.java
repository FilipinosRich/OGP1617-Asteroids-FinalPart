package asteroids.expressions;

import asteroids.expressions.*;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class DoubleLiteralExpression extends BasicExpression {
	public DoubleLiteralExpression(double value) {
		this.value = value;
	}
	
	private double value;
	
	public DoubleLiteralExpression() {
		this(0);
	}
	
	public final static DoubleLiteralExpression ZERO = new DoubleLiteralExpression();
	
	@Override
	@Basic @Immutable
	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return Double.toString(getValue());
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
