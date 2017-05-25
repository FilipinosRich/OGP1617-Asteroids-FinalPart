package asteroids.expressions;

import asteroids.expressions.*;
import asteroids.expressions.exceptions.IllegalOperandException;
import be.kuleuven.cs.som.annotate.*;



public abstract class BinaryExpression extends ComposedExpression {
	
	@Model
	protected BinaryExpression(Expression left, Expression right)
			throws IllegalOperandException {
		//if (!canHaveAsOperand(left))
			//throw new IllegalOperandException(this, left);
		//if (!canHaveAsOperand(right))
			//throw new IllegalOperandException(this, right);
		setOperandAt(1, left);
		setOperandAt(2, right);
	}
	
	@Override
	public String toString() {
		String result;
		if (getLeftOperand() instanceof BasicExpression)
			result = getLeftOperand().toString();
		else if (getLeftOperand() instanceof ComposedExpression)
			result = "(" + getLeftOperand().toString() + ")";
		else
			throw new Error("Unknown expression type!");
		result += getOperatorSymbol();
		if (getRightOperand() instanceof BasicExpression)
			result += getRightOperand().toString();
		else if (getRightOperand() instanceof ComposedExpression)
			result += "(" + getRightOperand().toString() + ")";
		else
			throw new Error("Unknown expression type!");
		return result;
	}
	
	private Expression rightOperand;
	
	@Basic
	public Expression getRightOperand() {
		return rightOperand;
	}

	private Expression leftOperand;
	
	@Basic
	public Expression getLeftOperand() {
		return leftOperand;
	}
	
	
	@Raw
	protected void setOperandAt(int index, Expression operand) {
		if (index == 1)
			this.leftOperand = operand;
		else
			this.rightOperand = operand;
	}
	
	
	@Raw
	public final Expression getOperandAt(int index)
			throws IndexOutOfBoundsException {
		if ((index != 1) && (index != 2))
			throw new IndexOutOfBoundsException();
		if (index == 1)
			return getLeftOperand();
		else
			return getRightOperand();
	}

	@Raw
	public final boolean canHaveAsNbOperands(int number) {
		return number == 2;
	}
	@Override
	@Basic
	public final int getNbOperands() {
		return 2;
	}

}
