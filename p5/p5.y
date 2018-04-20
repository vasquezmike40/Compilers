%{
#include <stdio.h>
#include <stdlib.h>
%}
%token RENAME Attributes AS Relations WHERE BiOperation Comparison Value

%%

Start										: Expressions	;

Expressions							:	OneRelationExpression	| TwoRelationExpressions ;

OneRelationExpression		:	Renaming	|	Restrictions	| Projections	;

Renaming								:	Term RENAME Attributes AS Attributes	;

Term										:	Relations	|	'(' Expressions ')'	;

Restrictions						:	Term WHERE CompareMe	;

Projections							:	Term	| Term '[' AttributeCommaList ']'	;

AttributeCommaList			:	Attributes	|	Attributes ',' AttributeCommaList	;

TwoRelationExpressions	:	Projections BiOperation Expressions	;

CompareMe							:	Attributes Comparison Number	;

Number									:	Value	|	Number Value	;

%%

yyerror(){
	printf("REJECT\n");
	exit(0);
}
main(){
	yyparse();
	printf("ACCEPT\n");
}
yywrap(){
}
