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
		Double e = getLeftOperand().getValue() + getRightOperand().getValue();
		return (T) e;
	}
	
	
	@Override
	public String getOperatorSymbol() {
		return "+";
	}


	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
	}

	public double getValue() {
		return getLeftOperand().getValue() + getRightOperand().getValue();
	}
	
	public void updateList(List<Object> tracker) {
		tracker.add(getResult());
	}





	



}
