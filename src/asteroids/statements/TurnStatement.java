package asteroids.statements;

import asteroids.expressions.Expression;
import asteroids.model.Ship;

public class TurnStatement extends Statement {
	public TurnStatement(Expression<?> angle) {
		setExpression(angle);
	}
	
	public Expression<?> getExpression() {
		return expression;
	}
	public double angle;
	public void setAngle(double angle) {
		this.angle = angle;
	}
	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		getExpression().ship = ship;
		getExpression().execute(ship);
		setAngle((double) getExpression().getResult());
		getShip().turn(angle);
	}
	
	public Expression<?> expression;
	public void setExpression(Expression<?> expression) {
		this.expression = expression;
 	}
}