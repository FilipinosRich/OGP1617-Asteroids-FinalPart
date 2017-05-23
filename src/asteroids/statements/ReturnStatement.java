package asteroids.statements;

import asteroids.expressions.Expression;

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
	protected void execute() {
		getValue().ship = ship;
		return getValue().execute();
		
	}

}
