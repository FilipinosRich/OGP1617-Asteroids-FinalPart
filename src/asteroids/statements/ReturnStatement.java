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
		execute(ship);
	}

}
