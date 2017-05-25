package asteroids.model;
import asteroids.functions.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.expressions.*;
import asteroids.statements.*;

import java.util.ArrayList;
import java.util.List;

import asteroids.expressions.*;
import asteroids.statements.*;
import asteroids.expressions.EntityExpressions.*;
public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		List<Function> program = new ArrayList<Function>();
		if (functions != null && functions.size() > 0) {
			for (Function pos : functions) {
				program.add(pos);
			} 
		}
		return new Program(program,main);
		
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Function(functionName,body);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AssignmentStatement(variableName,value);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WhileStatement(condition,body);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new BreakStatement();
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReturnStatement(value);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IfOptionalElseStatement(condition,ifBody,elseBody);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new PrintStatement(value);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SequenceStatement(statements);
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new VariableName<String>(variableName);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ParameterExpression(parameterName);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		// TODO Auto-generated method stub
		return new DoubleLiteralExpression(value);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new NullExpression();
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new SelfExpression<Ship>();
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ShipExpression<Ship>();
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new BulletExpression<Bullet>();
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new AnyExpression<Entity>();
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetPositionExpression<Double>(e);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetPositionExpression<Double>(e) {
			@Override
			public void execute(Ship ship) {
				getExpression().execute(ship);
				Entity e = (Entity) getExpression().getResult();
				Double pos = e.getPosition()[1];
				setResult(pos);
			}
		};
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetRadiusExpression<Double>(e);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new LessThanExpression(e1,e2);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new EqualityExpression(e1,e2);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new AdditionExpression(e1,e2);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new MultiplicationExpression(e1,e2);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new FireStatement();
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		// TODO Auto-generated method stub
		return new TurnStatement(angle);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new SkipStatement();
	}

	

}
