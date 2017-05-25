package asteroids.statements;

import asteroids.expressions.Expression;
import asteroids.model.Ship;

public class ReturnStatement extends Statement {
	public ReturnStatement(Expression<?> value) {
		setValue(value);
	}
	
	public void setValue(Expression<?> value) {
		this.value = value;
	}
	
	private Expression<?> value;
	
	public Expression getValue() {
		return value;
	}

	@Override
	public void execute(Ship ship) {
		//super.execute(ship);
		setResult(value);
	}
	
	public void setResult(Expression<?> expression) {
		this.result = (Expression<?>) expression.getResult();
	}
	private Expression<?> result;
	
	public Expression<?> getResult() {
		return result;
	}
}
