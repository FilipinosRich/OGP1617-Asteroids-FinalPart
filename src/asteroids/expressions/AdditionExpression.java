package asteroids.expressions;
import java.util.List;

import asteroids.expressions.*;
import asteroids.expressions.exceptions.*;
import asteroids.model.Ship;


public class AdditionExpression<T> extends BinaryExpression  {
	public AdditionExpression(Expression left, Expression right) throws IllegalOperandException {
		super(left,right);
	}
	
	@Override
	public T getResult() {
		return result;
	}
	
	
	@Override
	public String getOperatorSymbol() {
		return "+";
	}


	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		Double e = getLeftOperand().getValue() + getRightOperand().getValue();
		setResult((T) e);
	}

	public double getValue() {
		return getLeftOperand().getValue() + getRightOperand().getValue();
	}
	private T result;
	public void setResult(T e) {
		this.result = (T)e;
	}
	






	



}
