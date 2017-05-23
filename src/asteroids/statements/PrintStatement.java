package asteroids.statements;

import asteroids.model.*;
import asteroids.statements.*;
import asteroids.expressions.*;

public class PrintStatement extends Statement {

	public PrintStatement(Expression<?> value) {
		setValue(value);
	}
	
	private Expression<?> value;

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}

	public void execute() {
		getValue().ship = ship;
		getValue().execute();
		System.out.println(value.getResult());
	}
	
	
}
