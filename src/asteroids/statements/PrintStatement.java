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

	public void execute(Ship ship) {
		getValue().ship = ship;
		getValue().execute(ship);
		System.out.println(value.getResult());
	}
	
	
}
