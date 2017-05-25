package asteroids.statements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import asteroids.expressions.Expression;
import asteroids.model.*;


public abstract class Statement implements Serializable {
	public Ship ship;
	
	public Ship getShip() {
		return ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public void execute(Ship ship) {
		if (dt > 0.2) {
			setActionTime(dt);
		} else {
			List<Statement> keeper = new ArrayList<Statement>();
			keeper.add(this);
		}
	}
	


	
	
	
	/**
	 * 
	 * Makes a deepclone of an object. We assume ....
	 * 
	 * source: http://alvinalexander.com/java/java-deep-clone-example-source-code
	 * 
	 * @return
	 */
	public Object deepClone() {
		   try {
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ObjectOutputStream oos = new ObjectOutputStream(baos);
		     oos.writeObject(this);
		     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		     ObjectInputStream ois = new ObjectInputStream(bais);
		     return ois.readObject();
		   }
		   catch (Exception e) {
		     e.printStackTrace();
		     return null;
		   }
		 }

	public static double dt;
	
	public void setActionTime(double dt) {
		this.actionTime = dt;
	}
	
	public static double actionTime;
}
