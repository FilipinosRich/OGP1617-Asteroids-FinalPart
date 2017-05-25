package asteroids.statements;

import asteroids.expressions.Expression;
import asteroids.model.Ship;

public class TurnStatement extends ActionStatement {
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
		try {
			//super.execute(ship);
			getExpression().execute(ship);
			setAngle((double) getExpression().getResult());
			getShip().turn(angle); 
			Thread.sleep(200);
			ship.dt -= 0.2;
		} catch(InterruptedException exc) {
			
		}
	}
	
	public Expression<?> expression;
	public void setExpression(Expression<?> expression) {
		this.expression = expression;
 	}

	public double getAngle() {
		return angle;
	}
	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return getAngle();
	}


}
