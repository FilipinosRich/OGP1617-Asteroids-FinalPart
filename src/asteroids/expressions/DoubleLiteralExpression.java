package asteroids.expressions;

import java.util.List;

import asteroids.expressions.*;
import asteroids.model.Ship;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class DoubleLiteralExpression<T> extends BasicExpression {
	public DoubleLiteralExpression(double value) {
		this.value = value;
	}
	
	private double value;
	
	public DoubleLiteralExpression() {
		this(0);
	}
	
	@Override
	public T getResult() {
		Double e = (value);
		return (T)e;
	}
	public final static DoubleLiteralExpression ZERO = new DoubleLiteralExpression();
	
	@Override
	@Basic @Immutable
	public double getValue() {
		return value;
	}


	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		
	}
	public void updateList(List<Object> tracker) {
		tracker.add(getResult());
	}
	




}
