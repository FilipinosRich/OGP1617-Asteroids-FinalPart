package asteroids.statements;

import java.util.List;

import asteroids.expressions.Expression;
import asteroids.model.*;

public class BreakStatement extends Statement {

	@Override
	public void execute(Ship ship) {
		//super.execute(ship);
		List<Statement> sequence = getShip().getProgram().sequence;
		for (Statement subsequence: sequence) {
			if (subsequence instanceof BreakStatement) {
				for (Statement statement: sequence) {
					if (statement instanceof WhileStatement) {
						int index = sequence.indexOf(statement);
						sequence.remove(index);
					}
				}
			}
		}
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return null;
	}


}
