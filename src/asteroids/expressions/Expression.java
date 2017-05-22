package asteroids.expressions;

import asteroids.model.*;
import asteroids.statements.Statement;


public abstract class Expression<T> extends Statement {
	
	public abstract T getResult();
}
