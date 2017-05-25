package asteroids.statements;

import java.util.Iterator;
import java.util.List;

import asteroids.model.Ship;
import asteroids.statements.Statement;


public class SequenceStatement extends Statement {
	public SequenceStatement(List<Statement> statements) {
		setSequence(statements);
	}


	@Override
	public void execute(Ship ship) { 
			for (Iterator<Statement> i = sequence.iterator(); i.hasNext();) {
				if (!getShip().isExecutingProgram()){
					Statement s = i.next();
					s.ship = ship;
//					if (!(s instanceof StatementSequence)) {
//						s.execute();
//					} else {
//						getShip().getProgram().sequence.add(s);
//					}
					if (!(s instanceof SequenceStatement)){
						s.execute(ship);
						i.remove();
					} else if (s instanceof SequenceStatement){
						getShip().getProgram().sequence.add(s);
						if (((SequenceStatement) s).getSequence().isEmpty()){
							i.remove();
						}
					}
				} else {
					break;
				}
			}
		}
	
	private List<Statement> sequence;
	
	public List<Statement> getSequence() {
		return sequence;
	}

	public void setSequence(List<Statement> sequence) {
		this.sequence = sequence;
	}

}
