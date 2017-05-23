package asteroids.model;
import java.util.*;

import asteroids.functions.*;
import asteroids.statements.*;


public class Program {
	public Program(List<Function> function, Statement statement) {
		setStatement(statement);
		setAvailable(true);
	}
	
	public boolean getAvailability() {
		return isAvailable;
	}
	public void setAvailable(boolean availability) {
		this.isAvailable = availability;
	}
	
	private boolean isAvailable;
	
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	private Statement statement;
}
