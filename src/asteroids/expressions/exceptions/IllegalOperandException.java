package asteroids.expressions.exceptions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.expressions.Expression;

public class IllegalOperandException extends RuntimeException {


	
	public IllegalOperandException(Expression targetExpression, Expression operand) {
		this.targetExpression = targetExpression;
		this.operand = operand;
	}
	
	@Basic @Immutable @Raw
	public Expression getTargetExpression() {
		return this.targetExpression;
	}
	
	private final Expression targetExpression;
	
	@Basic @Immutable @Raw
	public Expression getOperand() {
		return this.operand;
	}

	private final Expression operand;
	
	private static final long serialVersionUID = 1L;

}
