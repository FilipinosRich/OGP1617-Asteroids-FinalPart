package asteroids.statements;

import java.util.List;
import asteroids.model.*;

public class BreakStatement extends Statement {

	@Override
	public void execute(Ship ship) {
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
}
