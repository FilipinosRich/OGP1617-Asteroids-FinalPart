package asteroids.expressions;

import asteroids.model.Ship;

public class VariableName<T> extends Expression<T> {
	public VariableName(String var) {
		this.var = var;
	}
	
	private final String var;
	
	public String getName() {
		return var;
	}
	
	private T result;
	
	@Override
	public void execute(Ship ship) {
		if (getShip().getProgram().assignment.containsKey(getName())){
			setResult((T)getShip().getProgram().assignment.get(getName()));
		}
		
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public T getResult() {
		return this.result;
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

	

	



}
