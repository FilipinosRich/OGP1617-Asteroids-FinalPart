package asteroids.statements;

import asteroids.model.IllegalNumberException;
import asteroids.model.*;

public class FireStatement extends Statement {
	

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		try {
			getShip().fireBullet();
		} catch (IllegalNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
