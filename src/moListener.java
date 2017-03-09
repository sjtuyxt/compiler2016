// Generated from C:/dabian\mo.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link moParser}.
 */
public interface moListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link moParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(moParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(moParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(moParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(moParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(moParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(moParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 */
	void enterClassDecl(moParser.ClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 */
	void exitClassDecl(moParser.ClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(moParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(moParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(moParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(moParser.Function_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(moParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(moParser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Void}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterVoid(moParser.VoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Void}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitVoid(moParser.VoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterInt(moParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitInt(moParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterString(moParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitString(moParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterBool(moParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitBool(moParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Class}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterClass(moParser.ClassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Class}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitClass(moParser.ClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void enterType_specifier(moParser.Type_specifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void exitType_specifier(moParser.Type_specifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#class_definition}.
	 * @param ctx the parse tree
	 */
	void enterClass_definition(moParser.Class_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#class_definition}.
	 * @param ctx the parse tree
	 */
	void exitClass_definition(moParser.Class_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(moParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(moParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#expression_statement}.
	 * @param ctx the parse tree
	 */
	void enterExpression_statement(moParser.Expression_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#expression_statement}.
	 * @param ctx the parse tree
	 */
	void exitExpression_statement(moParser.Expression_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#declaration_statemant}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_statemant(moParser.Declaration_statemantContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#declaration_statemant}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_statemant(moParser.Declaration_statemantContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#compound_statement}.
	 * @param ctx the parse tree
	 */
	void enterCompound_statement(moParser.Compound_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#compound_statement}.
	 * @param ctx the parse tree
	 */
	void exitCompound_statement(moParser.Compound_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link moParser#selection_statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(moParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link moParser#selection_statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(moParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link moParser#iteration_statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(moParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link moParser#iteration_statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(moParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForLoop}
	 * labeled alternative in {@link moParser#iteration_statement}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(moParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForLoop}
	 * labeled alternative in {@link moParser#iteration_statement}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(moParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(moParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(moParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(moParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(moParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(moParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(moParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(moParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(moParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign1}
	 * labeled alternative in {@link moParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void enterAssign1(moParser.Assign1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign1}
	 * labeled alternative in {@link moParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void exitAssign1(moParser.Assign1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link moParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void enterAssign(moParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link moParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void exitAssign(moParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#assignment_operator}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_operator(moParser.Assignment_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#assignment_operator}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_operator(moParser.Assignment_operatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Create}
	 * labeled alternative in {@link moParser#creation_expression}.
	 * @param ctx the parse tree
	 */
	void enterCreate(moParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Create}
	 * labeled alternative in {@link moParser#creation_expression}.
	 * @param ctx the parse tree
	 */
	void exitCreate(moParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Create2}
	 * labeled alternative in {@link moParser#creation_expression}.
	 * @param ctx the parse tree
	 */
	void enterCreate2(moParser.Create2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code Create2}
	 * labeled alternative in {@link moParser#creation_expression}.
	 * @param ctx the parse tree
	 */
	void exitCreate2(moParser.Create2Context ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#logical_or_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_or_expression(moParser.Logical_or_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#logical_or_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_or_expression(moParser.Logical_or_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#logical_and_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_and_expression(moParser.Logical_and_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#logical_and_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_and_expression(moParser.Logical_and_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#inclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void enterInclusive_or_expression(moParser.Inclusive_or_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#inclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void exitInclusive_or_expression(moParser.Inclusive_or_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#exclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void enterExclusive_or_expression(moParser.Exclusive_or_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#exclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void exitExclusive_or_expression(moParser.Exclusive_or_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#and_expression}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expression(moParser.And_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#and_expression}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expression(moParser.And_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality_expression(moParser.Equality_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality_expression(moParser.Equality_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#equality_operator}.
	 * @param ctx the parse tree
	 */
	void enterEquality_operator(moParser.Equality_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#equality_operator}.
	 * @param ctx the parse tree
	 */
	void exitEquality_operator(moParser.Equality_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void enterRelational_expression(moParser.Relational_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void exitRelational_expression(moParser.Relational_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#relational_operator}.
	 * @param ctx the parse tree
	 */
	void enterRelational_operator(moParser.Relational_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#relational_operator}.
	 * @param ctx the parse tree
	 */
	void exitRelational_operator(moParser.Relational_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#shift_expression}.
	 * @param ctx the parse tree
	 */
	void enterShift_expression(moParser.Shift_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#shift_expression}.
	 * @param ctx the parse tree
	 */
	void exitShift_expression(moParser.Shift_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#shift_operator}.
	 * @param ctx the parse tree
	 */
	void enterShift_operator(moParser.Shift_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#shift_operator}.
	 * @param ctx the parse tree
	 */
	void exitShift_operator(moParser.Shift_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expression(moParser.Additive_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expression(moParser.Additive_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#additive_operator}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_operator(moParser.Additive_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#additive_operator}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_operator(moParser.Additive_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expression(moParser.Multiplicative_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expression(moParser.Multiplicative_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#multiplicative_operator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_operator(moParser.Multiplicative_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#multiplicative_operator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_operator(moParser.Multiplicative_operatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Self}
	 * labeled alternative in {@link moParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void enterSelf(moParser.SelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Self}
	 * labeled alternative in {@link moParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void exitSelf(moParser.SelfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnarySelf}
	 * labeled alternative in {@link moParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnarySelf(moParser.UnarySelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnarySelf}
	 * labeled alternative in {@link moParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnarySelf(moParser.UnarySelfContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void enterUnary_operator(moParser.Unary_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#unary_operator}.
	 * @param ctx the parse tree
	 */
	void exitUnary_operator(moParser.Unary_operatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccess}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(moParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccess}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(moParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryDec}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryDec(moParser.UnaryDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryDec}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryDec(moParser.UnaryDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Primary}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(moParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Primary}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(moParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassAccess}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterClassAccess(moParser.ClassAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassAccess}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitClassAccess(moParser.ClassAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryInc}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryInc(moParser.UnaryIncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryInc}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryInc(moParser.UnaryIncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassFunc}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterClassFunc(moParser.ClassFuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassFunc}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitClassFunc(moParser.ClassFuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(moParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(moParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link moParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_expression(moParser.Primary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link moParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_expression(moParser.Primary_expressionContext ctx);
}