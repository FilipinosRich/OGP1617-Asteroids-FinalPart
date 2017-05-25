package asteroids.statements;

import java.util.*;

import asteroids.model.Ship;


public abstract class ActionStatement extends Statement{

	
	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		try {
				ship.dt -= 0.2;
				Thread.sleep(200);
		} catch(InterruptedException e) {
			
		}
	}

}
