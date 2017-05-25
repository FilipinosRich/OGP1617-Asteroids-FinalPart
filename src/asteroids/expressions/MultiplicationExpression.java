package asteroids.expressions;

import asteroids.expressions.exceptions.IllegalOperandException;
import asteroids.model.Ship;

public class MultiplicationExpression extends BinaryExpression {
	public MultiplicationExpression(Expression left, Expression right) throws IllegalOperandException{
		super(left,right);
	}
	
	@Override
	public double getValue() {
		return getLeftOperand().getValue() * getRightOperand().getValue();
	}
	
	@Override
	public String getOperatorSymbol() {
		return "*";
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		
	}




}
