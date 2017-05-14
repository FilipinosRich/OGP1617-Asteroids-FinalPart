package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

public class IllegalNumberException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IllegalNumberException(double value) {
		this.value = value;
	}
	@Basic @Immutable
	public double getValue() {
		return this.value;
	}
	private double value;
}
