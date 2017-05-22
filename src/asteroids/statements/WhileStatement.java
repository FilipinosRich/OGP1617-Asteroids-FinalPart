package asteroids.statements;

import asteroids.expressions.Expression;
import asteroids.statements.Statement;


public class WhileStatement extends Statement {
	public WhileStatement(Expression<?> condition, Statement body) {
		setBody(body);
		setCondition(condition);
	}
	
	private Expression<?> condition;
	private Statement body;
	

	public Expression<?> getCondition() {
		return condition;
	}
	public void setCondition(Expression<?> condition) {
		this.condition = condition;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}
	@Override
	public void execute() {
		Statement copy = (Statement) this.clone();
		getCondition().ship = ship;
		getCondition().execute();
		if (condition.getResult().equals(true)){
			getBody().ship = ship;
			getShip().getProgram().sequence.add(getBody());
			getShip().getProgram().sequence.add(copy);
		}
	}
}
