package asteroids.statements;

import asteroids.expressions.Expression;
import asteroids.model.Ship;

public class SkipStatement extends Statement {


	@Override
	public void execute(Ship ship)  {
		// TODO Auto-generated method stub
		try {
			//super.execute(ship);
			Thread.sleep(200);
			ship.dt -= (double) 0.2;
		} catch(InterruptedException exc) {
			
		}
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return ship.dt;
	}


}
