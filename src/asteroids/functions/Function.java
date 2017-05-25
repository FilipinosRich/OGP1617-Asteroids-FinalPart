package asteroids.functions;
import asteroids.statements.*;
public class Function {
	public Function(String functionName, Statement body) {
		setBody(body);
		setName(functionName);
	}
	
	private Statement body;
	private String functionName;
	
	public Statement getBody() {
		return body;
	}
	
	public void setBody(Statement body) {
		this.body = body;
	}
	
	public void setName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getName() {
		return functionName;
	}
}
