package asteroids.statements;

import javax.sound.midi.Sequence;

import asteroids.expressions.*;
import asteroids.model.Ship;

public class IfOptionalElseStatement extends Statement {
	
	public IfOptionalElseStatement(Expression<?> condition, Statement ifBody, Statement elseBody) {
		setCondition(condition);
		setIfbody(ifBody);
		setElsebody(elseBody);
	}
	
	private Expression<?> condition;
	private Statement ifbody;
	private Statement elsebody;
	
	public Expression<?> getCondition() {
		return condition;
	}

	public void setCondition(Expression<?> condition) {
		this.condition = condition;
	}

	public Statement getIfbody() {
		return ifbody;
	}

	public void setIfbody(Statement ifbody) {
		this.ifbody = ifbody;
	}

	public Statement getElsebody() {
		return elsebody;
	}

	public void setElsebody(Statement elsebody) {
		this.elsebody = elsebody;
	}
	
	@Override
	public void execute(Ship ship) {
		getCondition().ship = ship;
		getCondition().execute(ship);
		if (condition.getResult().equals(true)){
			getIfbody().ship = ship;
			getShip().getProgram().sequence.add(getIfbody());
		} else {
			if (getElsebody() != null){
				getElsebody().ship = ship;
				getShip().getProgram().sequence.add(getElsebody());
			}
		}
		
	}
	
	
}
