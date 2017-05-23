/**
 * Wijziging
 * 
 * Introduceer een method canHaveAsOperandAt(Expression, int),
 * die delegeert naar de methode canHaveAsOperand en naar een
 * nieuwe method canHaveAsOperandNumber(int), die in de plaats
 * komt van canHaveAsNbOperands
 */

package asteroids.expressions;

import be.kuleuven.cs.som.annotate.*;



public abstract class ComposedExpression extends Expression {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object other) {
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		ComposedExpression otherExpr = (ComposedExpression) other;
		if (this.isMutable() || otherExpr.isMutable())
			return this == other;
		if (getNbOperands() != otherExpr.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).equals(otherExpr.getOperandAt(pos)))
				return false;
		return true;
	}


	@Override	
	public boolean isIdenticalTo(Expression other) {
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		ComposedExpression otherExpr = (ComposedExpression) other;
		if (getNbOperands() != otherExpr.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).isIdenticalTo(otherExpr.getOperandAt(pos)))
				return false;
		return true;
	}

	@Override
	public boolean isMutable() {
		for (int i = 1; i <= getNbOperands(); i++)
			if (getOperandAt(i).isMutable())
				return true;
		return false;
	}

	@Override
	public ComposedExpression clone() {
		ComposedExpression result = (ComposedExpression) super.clone();
		if (isMutable())
			for (int i = 1; i <= getNbOperands(); i++)
				if (getOperandAt(i).isMutable())
					setOperandAt(i, getOperandAt(i).clone());
		return result;
	}


	@Basic
	public abstract int getNbOperands();

	@Raw
	public boolean canHaveAsNbOperands(int nbOperands) {
		return nbOperands > 0;
	}


	@Basic
	public abstract Expression getOperandAt(int index)
			throws IndexOutOfBoundsException;

	public boolean canHaveAsOperand(Expression expression) {
		return (expression != null) && (!expression.hasAsSubExpression(this));
	}


	protected abstract void setOperandAt(int index, Expression operand);

	
	@Override
	public boolean hasAsSubExpression(Expression expression) {
		if (expression == this)
			return true;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (getOperandAt(pos).hasAsSubExpression(expression))
				return true;
		return false;
	}

	
	public abstract String getOperatorSymbol();

	@Override
	public String toPostfix() {
		String result = "";
		for (int i = 1; i <= getNbOperands(); i++)
			result += getOperandAt(i).toPostfix() + " ";
		return result + getOperatorSymbol();
	}

}