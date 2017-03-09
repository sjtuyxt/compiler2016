grammar mo;


//Top level

program: block+
;
 
block: declaration 	';'		#VarDecl
	| function_definition  	#FuncDecl
	| class_definition		#ClassDecl
	;
 
declaration: type_specifier Identifier ('=' initializer)?;
 
function_definition: type_specifier Identifier '(' (declaration (',' declaration)*)? ')' compound_statement ;

initializer: assignment_expression;		

type_name: 'void' 	  	#Void
		| 'int' 		#Int
		| 'string' 		#String
		| 'bool' 		#Bool
		| Identifier 	#Class
		;
			
type_specifier: type_name Brackets*;

Brackets : '[]';

class_definition: 'class' Identifier '{' declaration_statemant* '}';

//Statement 

statement: expression_statement
          | compound_statement
          | selection_statement
          | iteration_statement
          | jump_statement 
		  | declaration_statemant ;
 
expression_statement: expression? ';' ;

declaration_statemant: type_specifier Identifier ('=' initializer)? ';' ;
 
compound_statement: '{' statement* '}' ;
 
selection_statement: 'if' '(' expression ')' statement ('else' statement)? 					#IfStmt
					;
 
iteration_statement: 'while' '(' expression ')' statement									#WhileStmt
                    | 'for' '(' init = expression? ';' cond = expression? ';' step = expression? ')' statement 	#ForLoop
					;
 
jump_statement: 'continue' ';'					#ContinueStmt
               | 'break' ';'					#BreakStmt
               | 'return' expression? ';' 		#ReturnStmt
			   ;
			   
			   
//Expression

expression: assignment_expression;
 
assignment_expression: creation_expression                                          #Assign1
                      | unary_expression assignment_operator assignment_expression  #Assign
                      ;
 
assignment_operator: '=' ;
 
creation_expression: 'new' type_name  ('[' expression ']')* Brackets*     #Create
					| logical_or_expression                               #Create2
					;
 
logical_or_expression: logical_and_expression ('||' logical_and_expression)* ;
 
logical_and_expression: inclusive_or_expression ('&&' inclusive_or_expression)* ;
 
inclusive_or_expression: exclusive_or_expression ('|' exclusive_or_expression)* ;
  
exclusive_or_expression: and_expression ('^' and_expression)* ;
 
and_expression: equality_expression ('&' equality_expression)* ;
 
equality_expression: relational_expression (equality_operator relational_expression)* ;
 
equality_operator: '==' | '!=' ;
 
relational_expression: shift_expression (relational_operator shift_expression)* ;
 
relational_operator: '<' | '>' | '<=' | '>=' ;
 
shift_expression: additive_expression (shift_operator additive_expression)* ;
 
shift_operator: '<<' | '>>' ;
 
additive_expression: multiplicative_expression (additive_operator multiplicative_expression)* ;
 
additive_operator: '+' | '-' ;
 
multiplicative_expression: unary_expression (multiplicative_operator unary_expression)* ;
 
multiplicative_operator: '*' | '/' | '%' ;

unary_expression: postfix_expression        #Self
                 | unary_operator unary_expression #UnarySelf
                 ;

unary_operator:  '+' | '-' | '~' | '!' | '++' | '--';

postfix_expression: primary_expression                                                                            #Primary
                |postfix_expression '.' Identifier '(' (assignment_expression (',' assignment_expression)*)? ')'  #ClassFunc
                |postfix_expression '[' expression ']'                                                            #ArrayAccess
                |postfix_expression '(' (assignment_expression (',' assignment_expression)*)? ')'                 #FunctionCall
                |postfix_expression '.' Identifier                                                                #ClassAccess
                |postfix_expression '++'                                                                          #UnaryInc
                |postfix_expression '--'                                                                          #UnaryDec
                ;

 
 primary_expression: Integer_constant
                   | String
                   | '(' expression ')'
                   | Bool_constant
                   | Null
                   | Identifier
                   ;

WS : ([ \t\r\n]+ | '//' (~[\n\r])* [\n\r]+ )-> skip ;

Integer_constant: [0-9]+ ;

String: '"' SCharSequence? '"' ;

Bool_constant: 'true' | 'false';

Null: 'null';

Identifier: ([a-zA-Z]) ([a-zA-Z0-9]|'_')* ;

fragment SCharSequence: SChar+ ;

fragment SChar: ~["\r\n]
				| EscapeSequence ;
				
fragment EscapeSequence: '\\'["n\\] ;