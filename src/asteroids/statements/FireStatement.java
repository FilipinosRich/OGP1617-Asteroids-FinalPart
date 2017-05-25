package asteroids.statements;

import asteroids.model.IllegalNumberException;
import asteroids.expressions.Expression;
import asteroids.model.*;

public class FireStatement extends Statement {
	

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		try {
		//	super.execute(ship);
			ship.dt -= 0.2;
			Thread.sleep(200);
			getShip().fireBullet();
		} catch (IllegalNumberException | InterruptedException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return null;
	}


}
