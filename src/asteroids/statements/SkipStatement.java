package asteroids.statements;

import asteroids.model.Ship;

public class SkipStatement extends Statement {


	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		super.execute(ship);
		getShip();
	}
}
