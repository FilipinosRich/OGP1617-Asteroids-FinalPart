package asteroids.statements;

import java.io.Serializable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import asteroids.model.*;


public abstract class Statement implements Serializable {
	protected Ship ship;
	
	protected Ship getShip() {
		return ship;
	}
	
	protected void setShip(Ship ship) {
		this.ship = ship;
	}
	
	protected abstract void execute();
	
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

}
