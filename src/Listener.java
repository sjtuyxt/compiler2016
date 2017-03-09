import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;



import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class Listener extends moBaseListener {
	 
	public Stack<AST> stack = new Stack<>();

	@Override public void exitProgram(moParser.ProgramContext ctx) { 
		Program Prog = new Program();
		Stack<AST> a = new Stack<>();
		for (int i = 1; i <= ctx.getChildCount(); i++)
			a.push(stack.pop());
		for (int i = 1; i <= ctx.getChildCount(); i++)
			Prog.list.add(a.pop());
	}

	@Override public void exitDeclaration(moParser.DeclarationContext ctx) { 
		Expr c = null;
		if (ctx.initializer() != null)
			c = (Expr)stack.pop();
		Symbol b = Symbol.get(ctx.Identifier().getText());
		Type a = (Type)stack.pop();
		VarDecl x = new VarDecl(a, b, c);
		stack.push(x);
	}

	@Override public void exitFunction_definition(moParser.Function_definitionContext ctx) { 
		CompoundStmt d = (CompoundStmt)stack.pop();
		List<VarDecl> c = new ArrayList<>();
		Stack<VarDecl> s = new Stack<>();
		for (int i = 1; i <= ctx.declaration().size(); i++)
			s.push((VarDecl)stack.pop());
		for (int i = 1; i <= ctx.declaration().size(); i++)
			 c.add(s.pop());
		Symbol b = Symbol.get(ctx.Identifier().getText());
		Type a = (Type)stack.pop();
		FunctionDecl x = new FunctionDecl(a,b,c,d);
		stack.push(x);
	}

	@Override public void exitClass_definition(moParser.Class_definitionContext ctx) { 
		Symbol a = Symbol.get(ctx.Identifier().getText());
		List<DeclStmt> b = new ArrayList<>();
		Stack<DeclStmt> s = new Stack<>();
		for (int i = 1; i <= ctx.declaration_statemant().size(); i++)
			s.push((DeclStmt)stack.pop());
		for (int i = 1; i <= ctx.declaration_statemant().size(); i++)
			b.add(s.pop());
		ClassDecl x = new ClassDecl(a,b);
		stack.push(x);
	}
	
	@Override public void exitDeclaration_statemant(moParser.Declaration_statemantContext ctx) {
		Expr c = null;
		if (ctx.initializer() != null)
			c = (Expr)stack.pop();
		Symbol b = Symbol.get(ctx.Identifier().getText());
		Type a = (Type)stack.pop();
		DeclStmt x = new DeclStmt(a, b, c);
		stack.push(x);
	}
	
	@Override public void exitIfStmt(moParser.IfStmtContext ctx) { 
		Stmt c = null;
		if (ctx.statement().size() == 2)
			c = (Stmt)stack.pop();
		Stmt b = (Stmt)stack.pop();
		Expr a = (Expr)stack.pop();
		IfStmt x = new IfStmt(a,b,c);
		stack.push(x);
	}
	
	@Override public void exitForLoop(moParser.ForLoopContext ctx) { 
		Expr a = null;
		Expr b = null;
		Expr c = null;
		Stmt d = (Stmt)stack.pop();
		if (ctx.step != null) 
			c = (Expr)stack.pop();
		if (ctx.cond != null) 
			b = (Expr)stack.pop();
		if (ctx.init != null) 
			a = (Expr)stack.pop();
		ForLoop x = new ForLoop(a,b,c,d);
		stack.push(x);
	}
	
	@Override public void exitWhileStmt(moParser.WhileStmtContext ctx) { 
		Stmt b = (Stmt)stack.pop();
		Expr a = (Expr)stack.pop();
		WhileLoop x = new WhileLoop(a,b);
		stack.push(x);
	}
	
	@Override public void exitReturnStmt(moParser.ReturnStmtContext ctx) { 
		Expr a = null;
		if (ctx.expression() != null)
			a = (Expr)stack.pop();
		ReturnStmt x = new ReturnStmt(a);
		stack.push(x);
	}
	
	@Override public void exitBreakStmt(moParser.BreakStmtContext ctx) { 
		BreakStmt x = new BreakStmt();
		stack.push(x);
	}
	
	@Override public void exitContinueStmt(moParser.ContinueStmtContext ctx) { 
		ContinueStmt x = new ContinueStmt();
		stack.push(x);
	}
	
	@Override public void exitCompound_statement(moParser.Compound_statementContext ctx) { 
		List<Stmt> a = new ArrayList<>();
		Stack<Stmt> b = new Stack<>();
		for (int i = 1; i <= ctx.statement().size(); i++)
			b.push((Stmt)stack.pop());
		for (int i = 1; i <= ctx.statement().size(); i++)
			a.add(b.pop());
		CompoundStmt x = new CompoundStmt(a);
		stack.push(x);
	}
	
	@Override public void exitExpression_statement(moParser.Expression_statementContext ctx) { 
		Expr a = null;
		if (ctx.expression() != null)
			a = (Expr)stack.pop();
		ExprStmt x = new ExprStmt(a);
		stack.push(x);
	}
	
	@Override public void exitVoid(moParser.VoidContext ctx) { 
		VoidType x = new VoidType();
		stack.push(x);
	}
	
	@Override public void exitInt(moParser.IntContext ctx) { 
		IntType x = new IntType();
		stack.push(x);
	}
	
	@Override public void exitString(moParser.StringContext ctx) { 
		StringType x = new StringType();
		stack.push(x);
	}
	
	@Override public void exitBool(moParser.BoolContext ctx) { 
		BoolType x = new BoolType();
		stack.push(x);
	}
	
	@Override public void exitClass(moParser.ClassContext ctx) { 
		ClassType x = new ClassType(Symbol.get(ctx.Identifier().getText()));
		stack.push(x);
	}
	
	@Override public void exitType_specifier(moParser.Type_specifierContext ctx) {
		Type x = (Type)stack.pop();
		for (int i=1;i<=ctx.Brackets().size();i++){
			x = new ArrayType(x, null);
		}	
		stack.push(x);
	}

	@Override public void exitAssign(moParser.AssignContext ctx) {
		Expr b = (Expr)stack.pop();
		Expr a = (Expr)stack.pop();
		BinaryExpr x = new BinaryExpr(a, BinaryOp.ASSIGN, b);
		stack.push(x);
	}

	@Override public void exitCreate(moParser.CreateContext ctx) {
		List<Expr> a = new ArrayList<>();
		for (int i=1;i<=ctx.expression().size();i++)
			a.add((Expr)stack.pop());
		Type b = (Type)stack.pop();
		for (int i=1;i<=ctx.Brackets().size();i++)
			b = new ArrayType(b, null);
		for (int i=1;i<=ctx.expression().size();i++)
			b = new ArrayType(b, a.get(i-1));
		CreateExpr x = new CreateExpr(b);
		stack.push(x);
	}

	@Override public void exitLogical_or_expression(moParser.Logical_or_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.logical_and_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.logical_and_expression().size() - 1; i++)
			x = new BinaryExpr(x, BinaryOp.LOGICAL_OR, s.pop());
		stack.push(x);
	}

	@Override public void exitLogical_and_expression(moParser.Logical_and_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		//for (int i = 1; i <= ctx.inclusive_or_expression().size() - 1; i++)
		//	s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.inclusive_or_expression().size() - 1; i++)
			x = new BinaryExpr((Expr)stack.pop(), BinaryOp.LOGICAL_AND, x);
		stack.push(x);
	}

	@Override public void exitInclusive_or_expression(moParser.Inclusive_or_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.exclusive_or_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.exclusive_or_expression().size() - 1; i++)
			x = new BinaryExpr(x, BinaryOp.OR, s.pop());
		stack.push(x);
	}

	@Override public void exitExclusive_or_expression(moParser.Exclusive_or_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.and_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.and_expression().size() - 1; i++)
			x = new BinaryExpr(x, BinaryOp.XOR, s.pop());
		stack.push(x);
	}

	@Override public void exitAnd_expression(moParser.And_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.equality_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.equality_expression().size() - 1; i++)
			x = new BinaryExpr(x, BinaryOp.AND, s.pop());
		stack.push(x);
	}

	@Override public void exitEquality_expression(moParser.Equality_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.relational_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.relational_expression().size() - 1; i++) {
			if (ctx.equality_operator(i - 1).getText().equals("=="))
				x = new BinaryExpr(x, BinaryOp.EQ, s.pop());
			else if (ctx.equality_operator(i - 1).getText().equals("!="))
				x = new BinaryExpr(x, BinaryOp.NE, s.pop());
		}
		stack.push(x);
	}

	@Override public void exitRelational_expression(moParser.Relational_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.shift_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.shift_expression().size() - 1; i++) {
			if (ctx.relational_operator(i - 1).getText().equals("<"))
				x = new BinaryExpr(x, BinaryOp.LT, s.pop());
			else if (ctx.relational_operator(i - 1).getText().equals("<="))
				x = new BinaryExpr(x, BinaryOp.LE, s.pop());
			else if (ctx.relational_operator(i - 1).getText().equals(">"))
				x = new BinaryExpr(x, BinaryOp.GT, s.pop());
			else if (ctx.relational_operator(i - 1).getText().equals(">="))
				x = new BinaryExpr(x, BinaryOp.GE, s.pop());
		}
		stack.push(x);
	}

	@Override public void exitShift_expression(moParser.Shift_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.additive_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.additive_expression().size() - 1; i++) {
			if (ctx.shift_operator(i - 1).getText().equals("<<"))
				x = new BinaryExpr(x, BinaryOp.SHL, s.pop());
			else if (ctx.shift_operator(i - 1).getText().equals(">>"))
				x = new BinaryExpr(x, BinaryOp.SHR, s.pop());
		}
		stack.push(x);
	}

	@Override public void exitAdditive_expression(moParser.Additive_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.multiplicative_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.multiplicative_expression().size() - 1; i++) {
			if (ctx.additive_operator(i - 1).getText().equals("+"))
				x = new BinaryExpr(x, BinaryOp.ADD, s.pop());
			else if (ctx.additive_operator(i - 1).getText().equals("-"))
				x = new BinaryExpr(x, BinaryOp.SUB, s.pop());
		}
		stack.push(x);
	}

	@Override public void exitMultiplicative_expression(moParser.Multiplicative_expressionContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.unary_expression().size() - 1; i++)
			s.push((Expr)stack.pop());
		Expr x = (Expr)stack.pop();
		for (int i = 1; i <= ctx.unary_expression().size() - 1; i++) {
			if (ctx.multiplicative_operator(i - 1).getText().equals("*"))
				x = new BinaryExpr(x, BinaryOp.MUL, s.pop());
			else if (ctx.multiplicative_operator(i - 1).getText().equals("/"))
				x = new BinaryExpr(x, BinaryOp.DIV, s.pop());
			else if (ctx.multiplicative_operator(i - 1).getText().equals("%"))
				x = new BinaryExpr(x, BinaryOp.MOD, s.pop());
		}
		stack.push(x);
	}

	@Override public void exitUnarySelf(moParser.UnarySelfContext ctx) {
		Expr x = null;
		if (ctx.unary_operator().getText().equals("+"))
			x = new UnaryExpr(UnaryOp.PLUS, (Expr)stack.pop());
		else if (ctx.unary_operator().getText().equals("-"))
			x = new UnaryExpr(UnaryOp.MINUS, (Expr)stack.pop());
		else if (ctx.unary_operator().getText().equals("++"))
			x = new UnaryExpr(UnaryOp.INC, (Expr)stack.pop());
		else if (ctx.unary_operator().getText().equals("--"))
			x = new UnaryExpr(UnaryOp.DEC, (Expr)stack.pop());
		else if (ctx.unary_operator().getText().equals("!"))
			x = new UnaryExpr(UnaryOp.NOT, (Expr)stack.pop());
		else if (ctx.unary_operator().getText().equals("~"))
			x = new UnaryExpr(UnaryOp.TILDE, (Expr)stack.pop());
		stack.push(x);
	}

	@Override public void exitPrimary_expression(moParser.Primary_expressionContext ctx) {
		Expr x = null;
		if (ctx.Identifier() != null)
			x = new Identifier(Symbol.get(ctx.Identifier().getText()));
		else if (ctx.String() != null)
			x = new StringConst(ctx.String().getText());
		else if (ctx.Integer_constant() != null)
			x = new IntConst(Integer.valueOf(ctx.Integer_constant().getText()));
		else if (ctx.expression() != null)
			x = (Expr)stack.pop();
		else if (ctx.Bool_constant() != null)
			x = new BoolConst(Symbol.get(ctx.Bool_constant().getText()));
		else if (ctx.Null() != null)
			x = new Null();
		stack.push(x);
	}

	@Override public void exitArrayAccess(moParser.ArrayAccessContext ctx) {
		Expr b = (Expr)stack.pop();
		Expr a = (Expr)stack.pop();
		Expr x = new ArrayAccess(a,b);
		stack.push(x);
	}

	@Override public void exitFunctionCall(moParser.FunctionCallContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.assignment_expression().size(); i++)
			s.push((Expr)stack.pop());
		List<Expr> args = new ArrayList<>();
		for (int i = 1; i <= ctx.assignment_expression().size(); i++)
			args.add(s.pop());
		Symbol body = ((Identifier)stack.pop()).symbol;
		FunctionCall x = new FunctionCall(body, args);
		stack.push(x);
	}

	@Override public void exitClassAccess(moParser.ClassAccessContext ctx) {
		Symbol b = Symbol.get(ctx.Identifier().getText());
		Expr a = (Expr)stack.pop();
		ClassAccess x = new ClassAccess(a, b);
		stack.push(x);
	}

	@Override public void exitUnaryInc(moParser.UnaryIncContext ctx) {
		SelfIncrement x = new SelfIncrement((Expr)stack.pop());
		stack.push(x);
	}

	@Override public void exitUnaryDec(moParser.UnaryDecContext ctx) {
		SelfDecrement x = new SelfDecrement((Expr)stack.pop());
		stack.push(x);
	}

	@Override public void exitClassFunc(moParser.ClassFuncContext ctx) {
		Stack<Expr> s = new Stack<>();
		for (int i = 1; i <= ctx.assignment_expression().size(); i++)
			s.push((Expr)stack.pop());
		List<Expr> args = new ArrayList<>();
		for (int i = 1; i <= ctx.assignment_expression().size(); i++)
			args.add(s.pop());
		Symbol body = Symbol.get(ctx.Identifier().getText());
		Expr pro = (Expr)stack.pop();
		ClassFunc x = new ClassFunc(pro, body, args);
		stack.push(x);
	}
}