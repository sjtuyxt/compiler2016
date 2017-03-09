// Generated from /home/youxiaotian/compiler/dabian/mo.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link moParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface moVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link moParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(moParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(moParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(moParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassDecl}
	 * labeled alternative in {@link moParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(moParser.ClassDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(moParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#function_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_definition(moParser.Function_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(moParser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Void}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoid(moParser.VoidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(moParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(moParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(moParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Class}
	 * labeled alternative in {@link moParser#type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass(moParser.ClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#type_specifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_specifier(moParser.Type_specifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#class_definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_definition(moParser.Class_definitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(moParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#expression_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_statement(moParser.Expression_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#declaration_statemant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_statemant(moParser.Declaration_statemantContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#compound_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_statement(moParser.Compound_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link moParser#selection_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(moParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link moParser#iteration_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(moParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForLoop}
	 * labeled alternative in {@link moParser#iteration_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(moParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(moParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(moParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link moParser#jump_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(moParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(moParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign1}
	 * labeled alternative in {@link moParser#assignment_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign1(moParser.Assign1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link moParser#assignment_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(moParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#assignment_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_operator(moParser.Assignment_operatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Create}
	 * labeled alternative in {@link moParser#creation_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate(moParser.CreateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Create2}
	 * labeled alternative in {@link moParser#creation_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate2(moParser.Create2Context ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#logical_or_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_or_expression(moParser.Logical_or_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#logical_and_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_and_expression(moParser.Logical_and_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#inclusive_or_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusive_or_expression(moParser.Inclusive_or_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#exclusive_or_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusive_or_expression(moParser.Exclusive_or_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#and_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expression(moParser.And_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#equality_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality_expression(moParser.Equality_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#equality_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality_operator(moParser.Equality_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#relational_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_expression(moParser.Relational_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#relational_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_operator(moParser.Relational_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#shift_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift_expression(moParser.Shift_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#shift_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift_operator(moParser.Shift_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#additive_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_expression(moParser.Additive_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#additive_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive_operator(moParser.Additive_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_expression(moParser.Multiplicative_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#multiplicative_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative_operator(moParser.Multiplicative_operatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Self}
	 * labeled alternative in {@link moParser#unary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelf(moParser.SelfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnarySelf}
	 * labeled alternative in {@link moParser#unary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnarySelf(moParser.UnarySelfContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#unary_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_operator(moParser.Unary_operatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccess}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccess(moParser.ArrayAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryDec}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryDec(moParser.UnaryDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Primary}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(moParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassAccess}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassAccess(moParser.ClassAccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryInc}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryInc(moParser.UnaryIncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassFunc}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassFunc(moParser.ClassFuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link moParser#postfix_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(moParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link moParser#primary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_expression(moParser.Primary_expressionContext ctx);
}