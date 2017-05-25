package asteroids.expressions;

import asteroids.expressions.exceptions.IllegalOperandException;
import asteroids.model.Ship;

public class EqualityExpression extends BinaryExpression {
	public EqualityExpression(Expression left, Expression right) throws IllegalOperandException {
		super(left,right);
	}

	@Override
	public String getOperatorSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Boolean getResult() {
		// TODO Auto-generated method stub
		return (getLeftOperand().getValue() == getRightOperand().getValue());
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		
	}



}
