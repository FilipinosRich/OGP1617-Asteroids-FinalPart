package asteroids.statements;

import asteroids.model.*;
import asteroids.expressions.*;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(String variableName, Expression<?> value) {
		setValue(value);
		setVariableName(variableName);
	}
	
	private String variableName;
	private Expression<?> value;
	
	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}
	
	public void execute(Ship ship){
		getValue().ship = ship;
		getValue().execute(ship);
		getShip().getProgram().assignment.put(getVariableName(), getValue().getResult());
		
	}
}
