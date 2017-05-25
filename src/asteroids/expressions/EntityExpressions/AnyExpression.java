package asteroids.expressions.EntityExpressions;

import java.util.*;

import asteroids.expressions.Expression;
import asteroids.model.Entity;
import asteroids.model.Ship;

public class AnyExpression<T> extends Expression {
	@Override
	public T getResult() {
		return this.entityAny;
	}
	
	private T entityAny;
	
	public void setAnyEntity(T entity) {
		this.entityAny= entity;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		Set<T> min = (Set<T>) ship.getWorld().getEntities();
		Iterator iter = min.iterator();
		Object first = (T)iter.next();
		setAnyEntity((T) first);
	}
}
