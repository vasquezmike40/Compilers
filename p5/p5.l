%{
#include <stdio.h>
#include "p5.tab.h"
%}
BiOperation UNION|INTERSECT|MINUS|TIMES|JOIN|DIVIDEBY
Comparison <|<=|>|>=|=|<>
Attributes CNO|CITY|CNAME|SNO|PNO|TQTY|SNAME|QUOTA|PNAME|COST|AVQTY|S#|STATUS|P#|COLOR|WEIGHT|QTY
Relations S|P|SP|PRDCT|CUST|ORDERS
%%
[ \t]+  	       ;
\n	             if        (yyin==stdin) return 0;
{BiOperation}	   return    BiOperation;
{Comparison}	   return    Comparison;
[0-9]+	         return    Value;
{Attributes}	   return    Attributes;
{Relations}	     return    Relations;
"RENAME"         return    RENAME;
"AS"	           return    AS;
"WHERE"	         return    WHERE;
"("	             return    '(';
")"	             return    ')';
"["              return    '[';
"]"	             return    ']';
","	             return    ',';
. {printf("REJECT\n"); exit(0);}
%%
