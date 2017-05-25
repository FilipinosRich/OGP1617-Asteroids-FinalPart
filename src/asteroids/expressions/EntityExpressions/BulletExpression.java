package asteroids.expressions.EntityExpressions;

import asteroids.expressions.Expression;
import asteroids.model.Bullet;
import asteroids.model.Ship;

public class BulletExpression<T> extends Expression{

	@Override
	public T getResult() {
		return (T) bullet;
	}
	
	
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	private T bullet;

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
		setBullet(bullet);
	}
	
	public void setBullet(T bullet) {
		this.bullet = bullet;
	}


	@Override
	public boolean hasAsSubExpression(Expression expression) {
		// TODO Auto-generated method stub
		return false;
	}

}
