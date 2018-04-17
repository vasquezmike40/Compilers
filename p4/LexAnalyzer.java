// Mike Vasquez
// N00885181

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;


public class LexAnalyzer {

    // current index of the location in the array
    static int currentIndex = 0;

    // a boolean variable that determines that something is a number instead of a id
    static boolean isNum = false;

    // a variable to determine the scope of the identifier
    static int depthCount = 0;

    // a variable to track the current parse index in the tokens array
    static int parsecount = 0;

    static int indexCode = 0;

    static int codeVar = 0;

    /**
     * reads file
     * if not found gives a FileNotFoundException
     **/

    public static void main(String[] args) throws FileNotFoundException {

        // Read file from command line
        File file = new File(args[0]);

        /*
        The following array lists contain the relational
        operators, keywords, deliminators, and expected mistakes
        */

        // Relational Operators
        ArrayList<String> relational = new ArrayList < > ();
        relational.add("+");
        relational.add("-");
        relational.add("*");
        relational.add("/");
        relational.add("<");
        relational.add("<=");
        relational.add(">");
        relational.add(">=");
        relational.add("==");
        relational.add("!=");
        relational.add("=");

        // Keywords
        ArrayList<String> keywords = new ArrayList < > ();
        keywords.add("float");
        keywords.add("int");
        keywords.add("else");
        keywords.add("if");
        keywords.add("return");
        keywords.add("void");
        keywords.add("while");

        // Deliminators
        ArrayList<String> delims = new ArrayList < > ();
        delims.add(";");
        delims.add(",");
        delims.add("(");
        delims.add(")");
        delims.add("[");
        delims.add("]");
        delims.add("{");
        delims.add("}");
        delims.add(".");

        ArrayList<String> numDelims = new ArrayList<>();
        numDelims.add(";");
        numDelims.add(",");
        numDelims.add("(");
        numDelims.add(")");
        numDelims.add("[");
        numDelims.add("]");
        numDelims.add("{");
        numDelims.add("}");

        // Known Mistakes
        ArrayList< String > mistakes = new ArrayList < > ();
        mistakes.add("!");
        mistakes.add("@");
        mistakes.add("_");

        // ArrayList: tokens
        ArrayList<Token> tokens = new ArrayList<>();
        // Used in semantic analysis: tokens
        LinkedList<Token> tokenList = new LinkedList<Token>();
        // LinkedList: ids in semantic analysis
        LinkedList<Identifier> identifierList = new LinkedList<Identifier>();

        String[][]codeGen = new String[100][5];

        // Calls the lexical analyzer
        lexical(keywords, relational, delims, mistakes, numDelims, file, tokens, tokenList);
        /*
        // num is the used to determine the array length
        int num = 0;
          for(int i = 50; i < tokenList.size(); i++){
          // count is used to determine the number of parameters
          int count = 0;
          // increment moves the pointer along by two to record the number of parameters
          int increment = i;
          //The input provided by user is stored in num
             Scanner input = new Scanner(System.in);
             num = input.nextInt();
          // x is used to check the token before it
          int x = i*51;
          if((tokenList.get(i).getTokenType()).notequals("ID")){
            if((tokenList.get(i+1).getToken()).equals("(")){
              increment = i+2;
              while(!((tokenList.get(increment).getToken()).equals(")"))){
                if((tokenList.get(increment).getToken()).equals(",")){
                  count++;
                }
                increment--;
              }
              // have to increment count after because count is actually recording the number of commas
              count++;
              // checks if the ID is a function declaration

              int rows, number = 1, counter, j;
         //To get the user's input
         Scanner input = new Scanner(System.in);
         System.out.println("Enter the number of rows for floyd's triangle: \n");
         //Copying user input into an integer variable named rows
         rows = input.nextInt();
         System.out.println("Floyd's triangle");
         System.out.println("****************");
         for ( counter = 1 ; counter <= rows ; counter++ )
         {
             for ( j = 1 ; j <= counter ; j++ )
             {
                  System.out.print(number+" ");
                  //Incrementing the number value
                  number++;
             }
             //For new line
             System.out.println();  if(((tokenList.drop(x).getToken()).equals("int")) || ((tokenList.dropToken(x).getToken()).equals("void"))){
                Identifier newIdentifier = new Identifier("FUNCTION", tokenList.get(x).dropToken(), tokenList.get(i).getToken(), tokenList.get(i).getDepth(), count, "DEFINITION", "", 0, "", i);
                if((tokenList.get(x).getToken()).equals("int")){
                  tokenList.get(i).setNumType("int");
                  int rows, number = 1, counter, j;
       //To get the user's input
       Scanner input = new Scanner(System.in);
       int rows, number = 1, counter, j;
       //To get the user's input
       if ( num % 2 == 0 )
               System.out.println("Entered number is even");
            else
               System.out.println("Entered number is odd");
       Scanner input = new Scanner(System.in);
       System.out.println("Enter the number of rows for floyd's triangle: \n");
       //Copying user input into an integer variable named rows
       rows = input.nextInt();
       System.out.println("Floyd's triangle") counter <= rows ; counter++ )
       {
           for ( j = 1 ; j <= counter ; j++ )
           {
                System.out.print(number+" ");
                //Incrementing the number value
                number++;
           }
           //For new line
           System.out.println();
       System.out.println("Enter the number of rows for floyd's triangle: \n");
       //Copying user input into an integer variable named rows
       rows = input.nextInt();
       System.out.println("Floyd's triangle");
       System.out.println("****************");
       for ( counter = 1 ; counter <= rows ; counter++ )
       {
           for ( j = 1 ; j <= counter ; j++ )
           {
                System.out.print(number+" ");
                //Incrementing the number value
                number++;
           }
           //For new line
           System.out.println();
                }else if((tokenList.get(x).getToken()).equals("float")){
                  tokenList.get(i).setNumType("float");
                }else if((tokenList.get(x).getToken()).equals("void")){
                  tokenList.get(i).setNumType("void");
                }
                identifierList.addLast(newIdentifier);
              }else{
                // if its not a function declaration, then its a function call
                Identifier newIdentifier2 = new Identifier("FUNCTION", "", tokenList.get(i).getToken(), tokenList.get(i).getDepth(), count, "CALL", "", 0, "", i);
                identifierList.addLast(newIdentifier2);
              }
            }else{
              // if the ID is not a function, then it is a variable
              if(((tokenList.get(x).getToken()).equals("float")) || ((tokenList.get(x).getToken()).equals("int"))){
                if((tokenList.get(x).getToken()).equals("int")){
                  tokenList.get(i).setNumType("int");
                }else if((tokenList.get(x).getToken()).equals("float")){
                  tokenList.get(i).setNumType("float");
                }
                // checks if the variable is an array
                if((tokenList.get(i+1).getToken()).equals("[")){
                  if((tokenList.get(i+2).getNumType()).equals("int")){
                    num = Integer.parseInt(tokenList.get(i+2).getToken());
                  }else if((tokenList.get(i+2).getToken()).equals("]")){
                    num = 0;
                  }else{
                    // and array must have a value or have nothing in it, this may be handled by the parser, but just in case
                    System.out.println("REJECT");
                    System.exit(1);
                  }
                  // creates the identifier object with the array tag and length
                  Identifier newIdentifier3 = new Identifier("VARIABLE", tokenList.get(x).getToken().trim(), tokenList.get(i).getToken().trim(), tokenList.get(i).getDepth(), 0, "DEFINITION", "ARRAY", num, "", i);
                  identifierList.addLast(newIdentifier3);

                /*  codeGen[lexAnalyzer.indexCode][0] = String.valueOf(lexAnalyzer.indexcode);          this is
                  codeGen[lexAnalyzer.indexCode][1] = "alloc";                                          for allocating
                  codeGen[lexAnalyzer.indexCode][2] = String.valueOf(4*num);                            variables
                  codeGen[LexAnalyzer.indexCode][3] = "";                                               did not have
                  codeGen[LexAnalyzer.indexCode][4] = newIdentifier3.getName();                         time to implament
                  LexAnalyzer.indexCode++;/*                                                            everything


                }else{
                  // if its not an array, then the tag is blank and the length is 0
                  Identifier newIdentifier3 = new Identifier("VARIABLE", tokenList.get(x).getToken().trim(), tokenList.get(i).getToken().trim(), tokenList.get(i).getDepth(), 0, "DEFINITION", "", 0, "", i);
                  identifierList.addLast(newIdentifier3);
                }
              }else{
                // if the variable is define as being void, it is not posisble, so its rejected
                if((tokenList.get(i-1).getToken()).equals("void")){
                  //System.out.println("ERROR: Variables cannot be void");
                  System.out.println("REJECT");
                  System.exit(1);
                }else{
                  // this is for a variable that is an array instantiation
                  if((tokenList.get(i+1).getToken()).equals("[")){
                    if((tokenList.get(i+2).getNumType()).equals("int")){
                      num = Integer.parseInt(tokenList.get(i+2).getToken());
                    }else if((tokenList.get(i+2).getToken()).equals("]")){
                      num = 0;
                    }else{
                      System.out.println("REJECT");
                      System.exit(1);
                    }
                    // if it was an array it created as an instantiation array with a length
                    Identifier newIdentifier4 = new Identifier("VARIABLE", "", tokenList.get(i).getToken().trim(), tokenList.get(i).getDepth(), 0, "INSTANTIATION", "ARRAY" , num, "", i);
                    identifierList.addLast(newIdentifier4);
                  }else{
                    // if its not an array then it is an instantiation with a length of 0 and no array tag
                    Identifier newIdentifier5 = new Identifier("VARIABLE", "", tokenList.get(i).getToken().trim(), tokenList.get(i).getDepth(), 0, "INSTANTIATION", "", 0, "", i);
                    identifierList.addLast(newIdentifier5);
                  }
                }
              }
            }
          }
        }
        public static void prime(String args[])
 {
    int n;
    int status = 1;
    int num = 3;
    //For capturing the value of n
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the value of n:");
    //The entered value is stored in the var n
    n = scanner.nextInt();
    if (n >= 1)
    {
       System.out.println("First "+n+" prime numbers are:");
       //2 is a known prime number
       System.out.println(2);
    }

    for ( int i = 2 ; i <=n ;  )
    {
       for ( int j = 2 ; j <= Math.sqrt(num) ; j++ )
       {
          if ( num%j == 0 )
          {
             status = 0;
             break;
          }
       }
       if ( status != 0 )
       {
          System.out.println(num);
          i++;
       }
       status = 1;
       num++;
    }
 }
        // this section for both the variables and functions, it looks through the already created identifierList to
        // detemine the type for each one. If the identifier was created and instantiated later then there is no type
        // associated with it, thus this section looks it up ans assigns it accordingly
        for(int j = 0; j < identifierList.size(); j++){
          String varName = "", funcName = "";
          if((identifierList.get(j).getCategory()).equals("FUNCTION")){
            if((identifierList.get(j).getDefCall()).equals("DEFINITION")){
              funcName = identifierList.get(j).getName();
              for(int k = 0; k < identifierList.size(); k++){
                if((identifierList.get(k).getName()).equals(funcName)){
                  if((identifierList.get(k).getCategory()).equals("FUNCTION")){
                    if((identifierList.get(k).getDefCall()).equals("CALL")){
                      identifierList.get(k).setType(identifierList.get(j).getType());
                      if((identifierList.get(j).getType()).equals("int")){
                        tokenList.get(identifierList.get(k).getTokenIndex()).setNumType("int");
                      }else if((identifierList.get(j).getType()).equals("float")){
                        tokenList.get(identifierList.get(k).getTokenIndex()).setNumType("float");
                      }
                    }
                  }
                }
              }
            }
          }else if((identifierList.get(j).getCategory()).equals("VARIABLE")){
            if((identifierList.get(j).getDefCall()).equals("DEFINITION")){
              varName = identifierList.get(j).getName();
              String isArray = identifierList.get(j).getVarType();
              int arrayLength = identifierList.get(j).getArraySize();
              String type = "";
              for(int k = 0; k < identifierList.size(); k++){
                if((identifierList.get(k).getName()).equals(varName)){
                  if((identifierList.get(k).getCategory()).equals("VARIABLE")){
                    if((identifierList.get(k).getDefCall()).equals("INSTANTIATION")){
                      identifierList.get(k).setType(identifierList.get(j).getType());
                      identifierList.get(k).setVarType(isArray);
                      identifierList.get(k).setArraySize(arrayLength);
                      if((identifierList.get(j).getType()).equals("int")){
                        tokenList.get(identifierList.get(k).getTokenIndex()).setNumType("int");
                      }else if((identifierList.get(j).getType()).equals("float")){
                        tokenList.get(identifierList.get(k).getTokenIndex()).setNumType("float");
                      }
                    }
                  }
                }
              }
            }
          }
        }

        String funcNameIn = "";
        String isGlobal = "";
        int indexCheck = 0;
        for(int j = 0; j < identifierList.size(); j++){
          if((identifierList.get(j).getCategory()).equals("FUNCTION")){
            funcNameIn = identifierList.get(j).getName();
            identifierList.get(j).setFunctionNameCurrent(funcNameIn);
            if((identifierList.get(j).getDefCall()).equals("DEFINITION")){
              for(int k = identifierList.get(j).getTokenIndex(); k < tokenList.size(); k++){
                if((tokenList.get(k).getToken()).equals("}")){
                  indexCheck = k;
                  if(tokenList.get(k).getDepth() == identifierList.get(j).getScope()){
                    isGlobal = "GLOBAL";
                    break;
                  }else{
                    isGlobal = funcNameIn;
                  }
                }
              }
            }
          }else if(((identifierList.get(j).getCategory()).equals("VARIABLE")) && funcNameIn.equals("")){
            identifierList.get(j).setFunctionNameCurrent("GLOBAL");
          }else if(((identifierList.get(j).getCategory()).equals("VARIABLE")) && (identifierList.get(j).getTokenIndex() < indexCheck)){
            identifierList.get(j).setFunctionNameCurrent(funcNameIn);
          }else if(((identifierList.get(j).getCategory()).equals("VARIABLE")) && (identifierList.get(j).getTokenIndex() > indexCheck)){
            identifierList.get(j).setFunctionNameCurrent("GLOBAL");
          }
        }
        //*********************
        */
        // Calling the parser
        grammar(tokens, codeGen);
        semantic(identifierList, tokenList, delims, codeGen);

        for (int i = 0; i < codeGen.length; i++) {
          int j = 0;
          if(codeGen[i][j] != null){
            for (j = 0; j < codeGen[i].length; j++) {
              System.out.printf("%7s", codeGen[i][j]);
            }
            System.out.printf("\n");
          }
        }

      } //end main

/*******************************************************************/

    public static boolean isInteger(String stringNumber) {
          try {
              Integer.parseInt(stringNumber);
              return true;
          } catch (NumberFormatException e) {
              return false;
          }
      }

/*******************************************************************
  Reads in possible numbers and checks to see if they are valid
     ***************************************************************/

    public static void checkNum(String num, ArrayList < String > mistakes, ArrayList<Token> tokens, LinkedList<Token> tokenList){
        char [] numArray = num.toCharArray();
        String numToPrint = "";
        String errorNum = "";
        String error = "";

        for (int r = 0; r < numArray.length; r++) {
            if ((numArray[0] == '.') || (numArray[0] == 'E')) {
                errorNum = num;
                break;
            }

            if (isInteger(String.valueOf(numArray[r]))) {
                numToPrint = numToPrint + numArray[r];

                if (r + 1 < numArray.length) {
                    if (numArray[r + 1] == '.') {
                        numToPrint = numToPrint + numArray[r + 1];
                        r = r + 1;

                        if (r + 1 < numArray.length) {
                            if (isInteger(String.valueOf(numArray[r + 1]))) {
                                numToPrint = numToPrint + numArray[r + 1];
                                r = r + 1;
                            } else {
                                errorNum = num;
                                break;
                            }
                        }
                    } else if (numArray[r + 1] == 'E') {
                        numToPrint = numToPrint + numArray[r + 1];
                        r = r + 1;

                        if (r + 1 < numArray.length) {
                            if ((numArray[r + 1] == '+') || (numArray[r + 1] == '-')) {
                                numToPrint = numToPrint + numArray[r + 1];
                                r = r + 1;
                            } else if (isInteger(String.valueOf(numArray[r + 1]))) {
                                numToPrint = numToPrint + numArray[r + 1];
                                r = r + 1;
                            } else {
                                errorNum = num;
                                break;
                            }
                        } else {
                            errorNum = num;
                            break;
                        }
                    } else if (isInteger(String.valueOf(numArray[r + 1]))) {
                        numToPrint = numToPrint + numArray[r + 1];
                        r = r + 1;
                    }
                }
            } else if (numArray[r] == '.') {
                numToPrint = numToPrint + numArray[r];

                if (r + 1 < numArray.length) {
                    if (isInteger(String.valueOf(numArray[r + 1]))) {
                        numToPrint = numToPrint + numArray[r + 1];
                        r = r + 1;
                    } else {
                        errorNum = num;
                        break;
                    }
                } else {
                    errorNum = num;
                    break;
                }
            } else if (numArray[r] == 'E') {
                numToPrint = numToPrint + numArray[r];

                if (r + 1 < numArray.length) {
                    if ((numArray[r + 1] == '+') || (numArray[r + 1] == '-')) {
                        numToPrint = numToPrint + numArray[r + 1];
                        r = r + 1;
                    } else if (isInteger(String.valueOf(numArray[r + 1]))) {
                        numToPrint = numToPrint + numArray[r + 1];
                        r = r + 1;
                    }
                } else {
                    errorNum = num;
                    break;
                }
            }

            if (mistakes.contains(String.valueOf(numArray[r]))) {
                for (int f = r; f < numArray.length; f++) {
                    error = error + numArray[f];
                }
                //System.out.println("error: " + error);
                System.out.println("REJECT1");
                System.exit(1);
                break;
            }
        }

        if (errorNum.isEmpty() == true){
            if ((num.indexOf('.')) >= 0 || (num.indexOf('E')) >= 0) {

                Token collected = new Token("NUM", "float", numToPrint, LexAnalyzer.depthCount);
                tokens.add(collected);
                numToPrint = "";
            } else {

                Token collected = new Token("NUM", "int", numToPrint, LexAnalyzer.depthCount);
                tokens.add(collected);
                numToPrint = "";
              }
        } else {

            System.out.println("REJECT2");
            System.exit(1);
        }
    }

/****************************************************************************/

    public static void lexical(ArrayList<String> keywords, ArrayList<String> relational, ArrayList<String> delims, ArrayList < String > mistakes, ArrayList<String> numDelims, File file, ArrayList < Token > tokens, LinkedList<Token> tokenList){
        try {
            Scanner input = new Scanner(file);
            int tracker = 0;

            int NEGATIVE_INFINITY = Integer.MIN_VALUE;
            int[][] m = new int[ 1][ 1];
            int[][] sol = new int[1][1];

            while (input.hasNextLine()) {
                String text = input.nextLine();
                String compare = "";
                //System.out.println("");

                if (text.isEmpty() == false) {
                    //System.out.println("INPUT: " + text);
                }

                char[] newArray = text.toCharArray();
                // Checks for comments
                for(LexAnalyzer.currentIndex = 0; LexAnalyzer.currentIndex < newArray.length; LexAnalyzer.currentIndex++) {
                    if ((LexAnalyzer.currentIndex + 1) < newArray.length) {
                        if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("/*")) {
                            tracker++;
                            LexAnalyzer.currentIndex++;
                        } else if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("*/")) {
                            if (tracker > 0) {
                                tracker--;
                            } else if (tracker == 0) {
                            }

                            LexAnalyzer.currentIndex++;
                        } else if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("//")) {

                          int W = 6;
                          int N = 1;

                            for (int i = 1; i <= N; i++)
                              {
                                for (int j = 0; j <= W; j++)
                                  {
                                    int m1 = N;
                                    int m2 = NEGATIVE_INFINITY;
                                    if (j >= W)
                                    m2 = N * W;
                                      /** select max of m1, m2 **/
                                      N = Math.max(m1, m2);
                                      W = m2 > m1 ? 1 : 0;

                                    }

                                  }
                                  break;
                        }
                    }


                    // If no commments
                    if (tracker == 0) {
                        if (!String.valueOf(newArray[LexAnalyzer.currentIndex]).equals("\t")) {
                            compare = compare + newArray[LexAnalyzer.currentIndex];
                        }

                        // If keyword then
                        if (keywords.contains(compare)) {
                          if(LexAnalyzer.currentIndex + 1 < newArray.length){
                              if((String.valueOf(newArray[LexAnalyzer.currentIndex + 1]).equals(" "))){
                            Token collected = new Token("KEYWORD", "", compare, LexAnalyzer.depthCount);
                            tokens.add(collected);
                            tokenList.addLast(collected);
                            compare = "";
                              }
                          }
                          if((LexAnalyzer.currentIndex == (newArray.length - 1))){
                            Token collected = new Token("KEYWORD", "", compare, LexAnalyzer.depthCount);
                            tokens.add(collected);
                            tokenList.addLast(collected);
                            compare = "";
                          }
                        }
                        // If there a relational operator that matches the string
                        else if (relational.contains(compare)) {
                            if ((LexAnalyzer.currentIndex + 1) < newArray.length) {
                                if (relational.contains(String.valueOf(newArray, LexAnalyzer.currentIndex, 2))) {
                                    compare = compare + newArray[LexAnalyzer.currentIndex+1];
                                    //System.out.println("RELATIONAL: " + compare);
                                    Token collected = new Token("RELATIONAL", "", compare, LexAnalyzer.depthCount);
                                    tokens.add(collected);
                                    tokenList.addLast(collected);
                                    compare = "";
                                } else {
                                    if (!(relational.contains(String.valueOf(newArray[LexAnalyzer.currentIndex - 1])))) {
                                        //System.out.println("RELATIONAL: " + compare);
                                        Token collected = new Token("RELATIONAL", "", compare, LexAnalyzer.depthCount);
                                        tokens.add(collected);
                                        tokenList.addLast(collected);
                                        compare = "";
                                    }
                                }

                                compare = "";
                            }
                          }

                        // if there is an error in the string
                        else if (mistakes.contains(compare)) {
                            boolean exception = false;
                            String bigNoNo = "";

                            if (compare.equals("!")) {
                                if (LexAnalyzer.currentIndex + 1 < newArray.length) {
                                    if ((String.valueOf(newArray[LexAnalyzer.currentIndex + 1])).equals("=")) {
                                        LexAnalyzer.currentIndex++;
                                        compare = compare + String.valueOf(newArray[LexAnalyzer.currentIndex]);
                                        Token collected = new Token("RELATIONAL", "", compare, LexAnalyzer.depthCount);
                                        tokens.add(collected);
                                        tokenList.addLast(collected);
                                        compare = "";
                                        LexAnalyzer.currentIndex++;
                                        exception = true;
                                    }
                                } else{
                                    bigNoNo = "!";
                                    exception = false;
                                    LexAnalyzer.currentIndex++;
                                }
                            }
                            else {
                                for (int d = LexAnalyzer.currentIndex; d < newArray.length; d++) {
                                    if (!(String.valueOf(newArray[d])).equals(" ")) {
                                        bigNoNo = bigNoNo + newArray[d];
                                    } else {
                                        LexAnalyzer.currentIndex = d;
                                        break;
                                    }
                                }

                            }
                            // ending the program if there is an error
                            if (exception == false){
                                //System.out.println("ERROR: " + BigNoNo);
                                System.out.println("REJECT3");
                                System.exit(1);
                                bigNoNo = "";
                                compare = "";
                              }
                          }
                          // if read integer

                        else if ((isInteger(compare) == true) || (compare.equals(".")) || (compare.equals("E"))) {
                            String newNum = "";
                            LexAnalyzer.isNum = true;

                            for (int x = LexAnalyzer.currentIndex; x < newArray.length; x++) {
                                if ((newArray[x] != ' ') && (!(numDelims.contains(String.valueOf(newArray[x]))))) {
                                    newNum = newNum + newArray[x];
                                } else {
                                    LexAnalyzer.currentIndex = x;
                                    checkNum(newNum, mistakes, tokens, tokenList);
                                    compare = "";
                                    LexAnalyzer.isNum = false;
                                    break;
                                }

                                if (x == (newArray.length - 1)) {
                                    checkNum(newNum, mistakes, tokens, tokenList);
                                }

                                LexAnalyzer.currentIndex = x;
                            }
                            LexAnalyzer.isNum = false;
                        }

                        // if the string is a delimitor
                        else if (delims.contains(compare)) {
                          if(compare.equals("{")){
                            LexAnalyzer.depthCount++;
                          }else if(compare.equals("}")){
                            LexAnalyzer.depthCount--;
                          }
                            //System.out.println("DELIMS: " + compare);
                            Token collected = new Token("DELIMS", "",compare, LexAnalyzer.depthCount);
                            tokens.add(collected);
                            tokenList.addLast(collected);
                            compare = "";

                          }
                        // at this point most likely an identifier
                        else if ((String.valueOf(newArray[LexAnalyzer.currentIndex]).equals(" ")) || (delims.contains(String.valueOf(newArray[LexAnalyzer.currentIndex]))) || (relational.contains(String.valueOf(newArray[LexAnalyzer.currentIndex])))) {
                            String newID = "";
                            String testString = "";
                            String beforeTest = "";
                            Token data = new Token("", "", "", 0);
                            Token data1 = new Token("", "", "", 0);
                            Token data2 = new Token("", "", "", 0);
                            boolean tester = false;
                            boolean tester1 = false;
                            boolean tester2 = false;
                            String type = "";
                            String type2 = "";
                            String type3 = "";
                            String val = "";
                            String val2 = "";
                            String val3 = "";
                            int depth = 0;
                            int depth2 = 0;
                            int depth3 = 0;

                            if ((!(keywords.contains(compare))) && (!(relational.contains(compare))) && (!(delims.contains(compare))) && (!(mistakes.contains(compare))) && (!(compare).equals(" ")) && (LexAnalyzer.isNum == false)) {
                                for (int k = 0; k < compare.length(); k++) {
                                    char f = compare.charAt(k);
                                    beforeTest = testString;
                                    testString = testString + f;

                                    if (delims.contains(String.valueOf(f))) {
                                        data = new Token("DELIMS", "", String.valueOf(f), LexAnalyzer.depthCount);
                                        type = "DELIMS";
                                        val = String.valueOf(f);
                                        depth = LexAnalyzer.depthCount;
                                        tester = true;
                                        compare = "";
                                        newID = beforeTest;
                                    }else if (mistakes.contains(String.valueOf(f))){
                                        String BigNoNo = compare.substring(k, compare.length());
                                        System.out.println("REJECT4");
                                        System.exit(1);
                                        compare = "";
                                        break;
                                    }else if (relational.contains(String.valueOf(f))){
                                      data1 = new Token("RELATIONAL", "", String.valueOf(f), LexAnalyzer.depthCount);
                                      type2 = "RELATIONAL";
                                      val2 = String.valueOf(f);
                                      depth2 = LexAnalyzer.depthCount;
                                      tester1 = true;
                                    }else{
                                      newID = newID + f;
                                    }
                                  }
                                if (newID.isEmpty() == false) {
                                    if (keywords.contains(newID)) {
                                        data2 = new Token("KEYWORD", "", newID.trim(), LexAnalyzer.depthCount);
                                        type3 = "KEYWORD";
                                        val3 = newID;
                                        depth3 = LexAnalyzer.depthCount;
                                        tester2 = true;
                                    } else {
                                      Token data3 = new Token("ID", "", newID.trim(), LexAnalyzer.depthCount);
                                      tokens.add(data3);
                                      tokenList.addLast(data3);
                                        /*Identifier data3 = new Identifier(newID, LexAnalyzer.depthCount, 0);
                                        lockerspace.add(data3);
                                        Token data4 = new Token("ID", newID);
                                        tokens.add(data4);*/
                                    }
                                }
                                if (tester2 == true) {
                                  tokenList.addLast(data2);
                                  tokens.add(data2);
                                }
                                if (tester == true) {
                                  tokenList.addLast(data);
                                  tokens.add(data);
                                }
                                if (tester1 == true) {
                                  tokenList.addLast(data1);
                                  tokens.add(data1);
                                }
                            } //good
                            compare = "";
                        } //good

                        // if the string only contains 1 char
                      else if (LexAnalyzer.currentIndex == (newArray.length - 1)) {
                            String newID = "";
                            Token data = new Token("", "", "", 0);
                            Token data1 = new Token("", "", "", 0);
                            Token data2 = new Token("", "", "", 0);
                            String type = "";
                            String type2 = "";
                            String type3 = "";
                            String val = "";
                            String val2 = "";
                            String val3 = "";
                            int depth = 0;
                            int depth2 = 0;
                            int depth3 = 0;
                            boolean tester = false;
                            boolean tester1 = false;
                            boolean tester2 = false;

                            for (int p = 0; p < compare.length(); p++) {
                                char f = compare.charAt(p);
                                if (delims.contains(String.valueOf(f))) {
                                    data = new Token("DELIMS", "", String.valueOf(f), LexAnalyzer.depthCount);
                                    type = "DELIMS";
                                    val = String.valueOf(f);
                                    depth = LexAnalyzer.depthCount;
                                    tester = true;
                                } else if (mistakes.contains(String.valueOf(f))) {
                                    String BigNoNo = "";
                                    for (int j = p + 1; j < newArray.length; j++) {
                                        if (!(String.valueOf(newArray[j])).equals(" ")) {
                                            BigNoNo = BigNoNo + newArray[j];
                                        } else {
                                            LexAnalyzer.currentIndex = j;
                                            break;
                                        }
                                    }
                                    //System.out.println("REJECT5");
                                    //System.exit(1);
                                    compare = "";
                                    break;
                                } else if (relational.contains(String.valueOf(f))) {
                                    data1 = new Token("RELATIONAL", "", String.valueOf(f), LexAnalyzer.depthCount);
                                    type2 = "RELATIONAL";
                                    val2 = String.valueOf(f);
                                    depth2 = LexAnalyzer.depthCount;
                                    tester1 = true;
                                } else {
                                    newID = newID + f;
                                }
                            }
                            if (newID.isEmpty() == false) {
                                if (keywords.contains(newID)) {
                                  data2 = new Token("KEYWORD", "", newID, LexAnalyzer.depthCount);
                                  type3 = "KEYWORD";
                                  val3 = newID;
                                  depth3 = LexAnalyzer.depthCount;
                                  tester2 = true;
                                } else {
                                  Token data3 = new Token("ID", "", newID.trim(), LexAnalyzer.depthCount);
                                  tokens.add(data3);
                                  tokenList.addLast(data3);
                                }
                              }
                                if(tester2 == true){
                                  tokenList.addLast(data2);
                                  tokens.add(data2);
                                }
                                if(tester == true){
                                  tokenList.addLast(data);
                                  tokens.add(data);
                                }
                                if(tester1 == true){
                                  tokenList.addLast(data1);
                                  tokens.add(data1);
                                }

                        } //good
                    }
                }
            }

            input.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

/***************************************************************************/

/*--------------------------------how--------------------------------------*/
/*--------------------------------far--------------------------------------*/
/*--------------------------------ive--------------------------------------*/
/*-------------------------------gotten------------------------------------*/
/*--------------------------------so---------------------------------------*/
/*--------------------------------far--------------------------------------*/

/***************************************************************************/

    // The parser
    public static void grammar(ArrayList < Token > tokens, String[][] codeGen) {

        Token EOF = new Token("EOF", "", "EOF", 0);
        tokens.add(EOF);

        A(tokens, codeGen);

    }

    // A -> B
    public static void A(ArrayList < Token > tokens, String[][] codeGen) {
      if(((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
      ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float")) ||
      ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void"))){
        B(tokens, codeGen);
      } else{
        return;
        }
    }

    // B -> CB2
    public static void B(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else {
            C(tokens, codeGen);
            B2(tokens, codeGen);
            return;
        }
    }

    // B2 -> CB2 | @			@ = empty
    public static void B2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void"))) {
            C(tokens, codeGen);
            B2(tokens, codeGen);
            return;
        } else {
            return;
        }
    }

    // C -> F id C2
    public static void C(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else {
            if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void"))); {
                F(tokens, codeGen);
                if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
                    LexAnalyzer.parsecount++;
                    C2(tokens, codeGen);
                    return;
                }
            }
        }
    }

    // C2 -> ; | [num]; |(G)H
    public static void C2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else {
            if ((tokens.get(LexAnalyzer.parsecount).getToken().equals("["))) {
                if ((tokens.get(LexAnalyzer.parsecount + 1).getTokenType().equals("number"))) {
                    LexAnalyzer.parsecount++;
                    if ((tokens.get(LexAnalyzer.parsecount + 1).getToken().equals("]"))) {
                        LexAnalyzer.parsecount++;
                        if ((tokens.get(LexAnalyzer.parsecount + 1).getToken().equals(";"))) {
                            LexAnalyzer.parsecount++;
                            LexAnalyzer.parsecount++;
                            return;
                        } else {
                            //System.out.println("REJECT");
                            //System.exit(1);
                        }
                    } else {
                        //System.out.println("REJECT");
                        //System.exit(1);
                    }
                } else {
                    //System.out.println("REJECT");
                    //System.exit(1);
                }
            } else if ((tokens.get(LexAnalyzer.parsecount).getToken().equals(";"))) {
                LexAnalyzer.parsecount++;
                return;
            } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                G(tokens, codeGen);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    H(tokens, codeGen);
                    return;
                } else

                {
                    //System.out.println("REJECT");
                    //System.exit(1);
                }
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        }
    }

    // F -> int | void | float
    public static void F(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float"))) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }

    }

    // G -> int id J2 I2 | void G2 | float id J2 I2
    public static void G(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else {
            if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float"))) {
                LexAnalyzer.parsecount++;
                if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
                    LexAnalyzer.parsecount++;
                    J2(tokens, codeGen);
                    I2(tokens, codeGen);
                    return;
                } else {
                    //System.out.println("REJECT");
                    //System.exit(1);
                }
            } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) {
                LexAnalyzer.parsecount++;
                G2(tokens, codeGen);
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        }
    }

    //G2 ->	id J2 I2 | @
    public static void G2(ArrayList < Token > tokens, String[][] codeGen) {

        if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            J2(tokens, codeGen);
            I2(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals(")")) {
            return;
        }
    }

    //H  -> { K L }
    public static void H(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) {
            LexAnalyzer.parsecount++;
            K(tokens, codeGen);
            L(tokens, codeGen);
            LexAnalyzer.parsecount--;
            if ((tokens.get(LexAnalyzer.parsecount + 1).getToken()).equals("}")) {
                LexAnalyzer.parsecount++;
                if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
                    //System.out.println("ACCEPT");
                    //System.exit(1);
                }
                return;
            } else {
                ///System.out.println("REJECT6");
                //System.exit(1);
            }
        }
    }

    //I' -> , J I2 | @
    public static void I2(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) {
            LexAnalyzer.parsecount++;
            J(tokens, codeGen);
            I2(tokens, codeGen);
            LexAnalyzer.parsecount--;
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("else")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("$"))) {
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //J -> F id J
    public static void J(ArrayList < Token > tokens, String[][] codeGen) {
        F(tokens, codeGen);
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) {
            LexAnalyzer.parsecount++;
            J(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //J' ->	[ ] |	@
    public static void J2(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("[")) {
            LexAnalyzer.parsecount++;
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) {
                //System.out.println("ACCEPT");
                //System.exit(1);
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
            if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(","))) {
                LexAnalyzer.parsecount++;
                return;
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        }
    }

    //K  ->	K'
    public static void K(ArrayList < Token > tokens, String[][] codeGen) {
        K2(tokens, codeGen);
        return;
    }

    //K2->	F id K3	|	@
    public static void K2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float"))) {
            F(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) {
                LexAnalyzer.parsecount++;
                K3(tokens, codeGen);
                return;
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if"))) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //	K3	->	; K2	|	[ number ] ; K2
    public static void K3(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
            LexAnalyzer.parsecount++;
            K2(tokens, codeGen);
            return;
        } else {
            if ((tokens.get(LexAnalyzer.parsecount).getToken().equals("["))) {
                if ((tokens.get(LexAnalyzer.parsecount + 1).getTokenType().equals("number"))) {
                    LexAnalyzer.parsecount++;
                    if ((tokens.get(LexAnalyzer.parsecount + 1).getToken().equals("]"))) {
                        LexAnalyzer.parsecount++;
                        if ((tokens.get(LexAnalyzer.parsecount + 1).getToken().equals(";"))) {
                            LexAnalyzer.parsecount++;
                            K2(tokens, codeGen);
                            return;
                        } else {
                            //System.out.println("REJECT");
                            //System.exit(1);
                        }
                    } else {
                        //System.out.println("REJECT");
                        //System.exit(1);
                    }
                } else {
                    //System.out.println("REJECT");
                    //System.exit(1);
                }
            } else {
                //System.out.println("REJECT");
              //  System.exit(1);
            }
        }
    }

    //L ->	L2
    public static void L(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if"))) {
            L2(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //L2 ->	M L2	|	@
    public static void L2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if"))) {
            M(tokens, codeGen);
            L2(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) {
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //M 	->	N	|	H	|	O	|	P	|	Q
    public static void M(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";"))) {
            N(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) {
            H(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) {
            Q(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) {
            P(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if")) {
            O(tokens, codeGen);
            return;
        }
    }

    //N 	->	R ;	|	;
    public static void N(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
                //System.out.println("ACCEPT");
                //System.exit(1);
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //O -> if ( R ) M O2
    public static void O(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if")) {
            LexAnalyzer.parsecount++;
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                R(tokens, codeGen);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    M(tokens, codeGen);
                    O2(tokens, codeGen);
                    return;
                } else {
                    //System.out.println("REJECT");
                    //System.exit(1);
                }

            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //O2	->	else M	|	@
    public static void O2(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("else")) {
            LexAnalyzer.parsecount++;
            M(tokens, codeGen);
            return;
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if"))) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //P ->	WHILE ( R ) M
    public static void P(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) {
            LexAnalyzer.parsecount++;
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                R(tokens, codeGen);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    M(tokens, codeGen);
                    return;
                } else {
                    //System.out.println("REJECT");
                    //System.exit(1);
                }

            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //Q 	->	RETURN Q'
    public static void Q(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) {
            LexAnalyzer.parsecount++;
            Q2(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //Q'	->	;	|	R;
    public static void Q2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
                //System.out.println("ACCEPT");
                //System.exit(1);
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //R 	-> 	id R3	|	U S2
    public static void R(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            R3(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("("))
            if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("number")) {
                U(tokens, codeGen);
                S2(tokens, codeGen);
                return;
            }
        else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //R2 -> = R	|	W' U' S'
    public static void R2(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("=")) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            return;
        } else if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) || ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) || ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) || ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) || ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) || ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            LexAnalyzer.parsecount++;
            W2(tokens, codeGen);
            U2(tokens, codeGen);
            S2(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //R3	-> 	T2 R2	|	( ABAR ) W' U' S'
    public static void R3(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            ABAR(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                LexAnalyzer.parsecount++;
                W2(tokens, codeGen);
                U2(tokens, codeGen);
                S2(tokens, codeGen);
                return;
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }

        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("[")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            LexAnalyzer.parsecount++;
            T2(tokens, codeGen);
            R2(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //S2	-> 	V S3	|	@
    public static void S2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            LexAnalyzer.parsecount++;
            V(tokens, codeGen);
            S3(tokens, codeGen);
            return;
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(","))
        ) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //S3	-> 	U	| 	id S4
    public static void S3(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            U(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            S4(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //S4-> 	( ABAR )W'U'	|	T' W' U'
    public static void S4(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            ABAR(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                LexAnalyzer.parsecount++;
                W2(tokens, codeGen);
                U2(tokens, codeGen);
                return;
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("[")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/"))
        ) {
            LexAnalyzer.parsecount++;
            T2(tokens, codeGen);
            W2(tokens, codeGen);
            U2(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //T	-> 	id T2
    public static void T(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            //System.out.println("ACCEPT");
            //System.exit(1);
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            T2(tokens, codeGen);
            return;
        } else {
            //System.out.println("REJECT");
            //System.exit(1);
        }
    }

    //T2	-> 	[ R ]	|	@
    public static void T2(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                LexAnalyzer.parsecount++;
                return;
            } else {
                //System.out.println("REJECT");
                //System.exit(1);
            }
        } else if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            LexAnalyzer.parsecount++;
            return;
        }
    }

    //V	-> 	<=	|	<	|	>	|	>=	|	==	|	!=
    public static void V(ArrayList < Token > tokens, String[][] codeGen) {
        if (!((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))) {
            //System.out.println("REJECT");
            //System.exit(1);
        }
        LexAnalyzer.parsecount++;
        return;
    }

    //U 	-> 	W U'
    public static void U(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            W(tokens, codeGen);
            U2(tokens, codeGen);
            return;
        }
    }

    //U'	-> 	CBAR U3	|	@
    public static void U2(ArrayList < Token > tokens, String[][] codeGen) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-"))) {
            LexAnalyzer.parsecount++;
            CBAR(tokens, codeGen);
            U3(tokens, codeGen);
            return;
        } else if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            LexAnalyzer.parsecount++;
            return;
        }
    }

    //U3	-> 	T W U'	|	W U'
    public static void U3(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("ID")) {
            LexAnalyzer.parsecount++;
            T(tokens, codeGen);
            W(tokens, codeGen);
            U2(tokens, codeGen);
            return;
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))) {
            LexAnalyzer.parsecount++;
            W(tokens, codeGen);
            U2(tokens, codeGen);
            return;
        }
    }

    //W 	-> 	Y W'
    public static void W(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            Y(tokens, codeGen);
            W2(tokens, codeGen);
            return;
        }
    }

    //W' 	-> 	X W''	|	@
    public static void W2(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/"))
        ) {
            LexAnalyzer.parsecount++;
            X(tokens, codeGen);
            W3(tokens, codeGen);
            return;
        } else if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            return;
        }
    }

    //W''	-> 	Y W'	|	id ( ABAR ) W'
    public static void W3(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            Y(tokens, codeGen);
            W2(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                ABAR(tokens, codeGen);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    W2(tokens, codeGen);
                    return;
                }
            }
        }
    }

    //CBAR 	-> 	+ 	| 	-
    public static void CBAR(ArrayList < Token > tokens, String[][] codeGen) {
        if (!((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-"))) {
            //System.out.println("REJECT");
            //System.exit(1);
        }
        LexAnalyzer.parsecount++;
        return;
    }

    //X 	-> 	* 	| 	/
    public static void X(ArrayList < Token > tokens, String[][] codeGen) {
        if (!((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/"))) {
            //System.out.println("REJECT");
            //System.exit(1);
        }
        LexAnalyzer.parsecount++;
        return;
    }

    //Y 	-> 	( R )	|	num
    public static void Y(ArrayList < Token > tokens, String[][] codeGen) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                //System.out.println("ACCEPT");
                //System.exit(1);
            }
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) {
            LexAnalyzer.parsecount++;
            return;
        }
    }

    //ABAR 	-> 	BBAR	|	@
    public static void ABAR(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("ID"))
        ) {
            LexAnalyzer.parsecount++;
            BBAR(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
            LexAnalyzer.parsecount++;
            return;
        }
    }

    //BBAR	->	R BBAR'
    public static void BBAR(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("ID"))
        ) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            BBAR2(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
            LexAnalyzer.parsecount++;
            return;
        }
    }

    //BBAR2	-> 	, R BBAR'	|	@
    public static void BBAR2(ArrayList < Token > tokens, String[][] codeGen) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(","))
        ) {
            LexAnalyzer.parsecount++;
            R(tokens, codeGen);
            BBAR2(tokens, codeGen);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
            LexAnalyzer.parsecount++;
            return;
        }
    }

  // semantic rules
  public static void semantic(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList, ArrayList<String> delims, String[][] codeGen){






          boolean checkMain = mainCheck(identifierList);
          boolean checkParamSize = paramSizeCheck(identifierList);
          boolean checkDefCall = defCallCheck(identifierList);
          boolean checkParamType = paramTypeCheck(identifierList, tokenList);
          returnSimpleCheck(identifierList, tokenList, delims);
          voidFuncCheck(identifierList, tokenList, codeGen);
          intFloatFuncCheck(identifierList, tokenList, codeGen);
          operatorOperandCheck(identifierList, tokenList, codeGen);
          operatorOperandAssignmentCheck(identifierList, tokenList, codeGen);
          varDeclaredOnceCheck(identifierList);
          if(checkMain == true && checkParamSize == true && checkDefCall == true && checkParamType == true){ // Add more boolean checks as you write the methods
            //System.out.println("ACCEPT");
            //System.exit(1);
          }else{
            //System.out.println("REJECT");
            //System.exit(1);
          }
        }

        // Checks to make sure that there is one main. If there are more than one, it rejects. If
        // there are none it rejects. If main is not the last function in the file, it rejects.
        public static boolean mainCheck(LinkedList<Identifier> identifierList){
          int mainCount = 0;
          boolean lastFunction = false;

          for(int i = 0; i < identifierList.size(); i++){
            if((identifierList.get(i).getCategory()).equals("FUNCTION")){
              if((identifierList.get(i).getName()).equals("main")){
                mainCount++;
                if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                  lastFunction = true;
                }
              }else{
                if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                  lastFunction = false;
                }
              }
            }
          }
          if(mainCount > 1){
            //System.out.println("REJECT9");
            //System.exit(1);
          }else if(mainCount == 0){
            //System.out.println("REJECT10");
            //System.exit(1);
          }
          if(lastFunction == false){
            //System.out.println("REJECT11");
            //System.exit(1);
          }
          return true;
        }

        // Checks to make sure that the parameters of a function call match the length
        // of the parameters of the function definition
        public static boolean paramSizeCheck(LinkedList<Identifier> identifierList){
          for(int i = 0; i < identifierList.size(); i++){
            int paramCheck = 0;
            String funcName = "";
            if((identifierList.get(i).getCategory()).equals("FUNCTION")){
              if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                paramCheck = identifierList.get(i).getNumOfParams();
                funcName = identifierList.get(i).getName();
                for(int j = 0; j < identifierList.size(); j++){
                  if((identifierList.get(j).getCategory()).equals("FUNCTION")){
                    if((identifierList.get(j).getName()).equals(funcName)){
                      if((identifierList.get(j).getDefCall()).equals("CALL")){
                        if(paramCheck == identifierList.get(j).getNumOfParams()){
                          return true;
                        }else{
                          //System.out.println("REJECT12");
                          //System.exit(1);
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          return true;
        }

        // Checks to make sure that for every function call there is an associated defined function.
        // Same thing with the variable instantiation. It checks that there is a variable defined first
        // before it is used.
        public static boolean defCallCheck(LinkedList<Identifier> identifierList){

          boolean definedFUNC = false, definedVAR = false, find = true;
          int count = 0;
          int counterVar = 0;
          for(int r = 0; r < identifierList.size(); r++){
            if((identifierList.get(r).getCategory()).equals("FUNCTION")){
              count++;
            }
          }
          if(count == identifierList.size()){
            definedVAR = true;
          }
          for(int i = 0; i < identifierList.size(); i++){
            if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
              if((identifierList.get(i).getCategory()).equals("VARIABLE")){
                definedVAR = true;
              }else if((identifierList.get(i).getCategory()).equals("FUNCTION")){
                definedFUNC = true;
              }
            }else{
              if((identifierList.get(i).getCategory()).equals("VARIABLE")){
                definedVAR = false;
              }else if((identifierList.get(i).getCategory()).equals("FUNCTION")){
                definedFUNC = false;
              }
              for(int j = i; j < identifierList.size(); j++){
                String varName = "", funcName = "";
                if((identifierList.get(j).getCategory()).equals("FUNCTION")){
                  if((identifierList.get(j).getDefCall()).equals("CALL")){
                    funcName = identifierList.get(j).getName();
                    for(int k = 0; k < i; k++){
                      if((identifierList.get(k).getName()).equals(funcName)){
                        if((identifierList.get(k).getCategory()).equals("FUNCTION")){
                          if((identifierList.get(k).getDefCall()).equals("DEFINITION")){
                            definedFUNC = true;
                          }
                        }
                      }
                    }
                  }
                }else if((identifierList.get(j).getCategory()).equals("VARIABLE")){
                  if((identifierList.get(j).getDefCall()).equals("INSTANTIATION")){
                    varName = identifierList.get(j).getName();
                    for(int k = 0; k < i; k++){
                      if((identifierList.get(k).getName()).equals(varName)){
                        if((identifierList.get(k).getCategory()).equals("VARIABLE")){
                          counterVar++;
                          if((identifierList.get(k).getDefCall()).equals("DEFINITION")){
                            definedVAR = true;
                          }
                        }
                      }
                    }
                    if(counterVar == 0){
                      find = false;
                    }
                  }
                }
              }
            }
          }
          if(find == false){
            //System.out.println("REJECT13");
            //System.exit(1);
            return false;
          }
          if(definedVAR == true && definedFUNC == true){
            return true;
          }else{
            //System.out.println("REJECT14");
            //System.exit(1);
            return false;
          }
        }

        // Checks to make sure that void functions return nothing, if they have a return statement.
        // If there is no return statement, there is no problem.
        public static void voidFuncCheck(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList, String[][] codeGen){
          boolean voidCorrect = false;
          for(int i = 0; i < identifierList.size(); i++){
            String funcName = "";
            int returnCount = 0;
            if((identifierList.get(i).getCategory()).equals("FUNCTION")){
              if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                if((identifierList.get(i).getType()).equals("void")){
                  funcName = identifierList.get(i).getName();
                  for(int k = 0; k < tokenList.size(); k++){
                    if((tokenList.get(k).getToken()).equals(funcName)){
                      if((tokenList.get(k-1).getToken()).equals("void")){
                        for(int r = k+1; r < tokenList.size(); r++){
                          if((tokenList.get(r).getToken()).equals("{")){
                            for(int p = r+1; p < tokenList.size(); p++){
                              if((tokenList.get(p).getToken()).equals("return")){
                                returnCount++;
                                if((tokenList.get(p+1).getToken()).equals(";")){
                                  voidCorrect = true;
                                  break;
                                }else{
                                  voidCorrect = false;
                                  //System.out.println("REJECT15");
                                  //System.exit(1);
                                  codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                                  codeGen[LexAnalyzer.indexCode][1] = "return";
                                  codeGen[LexAnalyzer.indexCode][2] = "";
                                  codeGen[LexAnalyzer.indexCode][3] = "";
                                  codeGen[LexAnalyzer.indexCode][4] = "";
                                  LexAnalyzer.indexCode++;
                                  break;
                                }
                              }else if(((tokenList.get(p).getToken()).equals("}")) && returnCount == 0){
                                voidCorrect = true;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }

        // Checks to make sure that the return statement does not have something complicated in it
        // Like a comma, which indicates returning two things, or an array
        public static void returnSimpleCheck(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList, ArrayList<String> delims){
          boolean simple = true;
          for(int i = 0; i < identifierList.size(); i++){
            String funcName = "";
            int returnCount = 0;
            String functionType = "";
            if((identifierList.get(i).getCategory()).equals("FUNCTION")){
              if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                funcName = identifierList.get(i).getName();
                functionType = identifierList.get(i).getType();
                for(int k = 0; k < tokenList.size(); k++){
                  if((tokenList.get(k).getToken()).equals(funcName)){
                    for(int r = k+1; r < tokenList.size(); r++){
                      if((tokenList.get(r).getToken()).equals("{")){
                        for(int p = r+1; p < tokenList.size(); p++){
                          if((tokenList.get(p).getToken()).equals("return")){
                            returnCount++;
                            if((tokenList.get(p+2).getToken()).equals("[")){
                              if(!((tokenList.get(p+3).getNumType()).equals(functionType))){
                                //System.out.println("REJECT16");
                                //System.exit(1);
                                break;
                              }else{
                                simple = true;
                                break;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }

        // Checks to make sure that the parameters of the function call match those of
        // the function definition
        public static boolean paramTypeCheck(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList){
          boolean typesMatch = false;
          int count = 0;
          for(int b = 0; b < identifierList.size(); b++){
            if((identifierList.get(b).getCategory()).equals("FUNCTION")){
              if((identifierList.get(b).getDefCall()).equals("CALL")){
                count++;
              }
            }
          }

          if(count > 0){
            for(int i = 0; i < identifierList.size(); i++){
              String funcName = "";
              ArrayList<Param> paramList = new ArrayList<Param>();
              paramList.clear();
              if((identifierList.get(i).getCategory()).equals("FUNCTION")){
                if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                  funcName = identifierList.get(i).getName();
                  int indexCheck = i;
                  for(int k = 0; k < identifierList.get(i).getNumOfParams(); k++){
                    paramList.add(new Param(funcName, identifierList.get(indexCheck+1).getType(), identifierList.get(indexCheck+1).getVarType()));
                    indexCheck++;
                  }
                  for(int r = 0; r < identifierList.size(); r++){
                    if((identifierList.get(r).getCategory()).equals("FUNCTION")){
                      if((identifierList.get(r).getDefCall()).equals("CALL")){
                        if((identifierList.get(r).getName()).equals(funcName)){
                          int numParams = identifierList.get(r).getNumOfParams();
                          int f = r+1;
                          if(((identifierList.get(r).getType()).equals("void")) && ((paramList.get(0).getParamType()).equals("void"))){
                            typesMatch = true;
                          }else{
                            for(int a = 0; a < numParams; a++){
                              if((tokenList.get(identifierList.get(r).getTokenIndex() + 1).getNumType()).equals(paramList.get(a).getParamType())){
                                typesMatch = true;
                              }else{
                                typesMatch = false;
                              }if(f < identifierList.size()){
                                if((identifierList.get(f).getType()).equals(paramList.get(a).getParamType())){
                                  if((identifierList.get(f).getVarType()).equals(paramList.get(a).getParamVarType())){
                                    typesMatch = true;
                                    f++;
                                  }else{
                                    int index = identifierList.get(f).getTokenIndex() + 1;
                                    if((tokenList.get(index).getToken()).equals("[")){
                                      if((tokenList.get(index + 1).getNumType()).equals("int")){
                                        typesMatch = true;
                                        f++;
                                      }else{
                                        typesMatch = false;
                                      }
                                    }else{
                                      typesMatch = false;
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }else{
            typesMatch = true;
          }
          if(typesMatch == true){
            return true;
          }else{
              //      System.out.println("REJECT");
              return false;
            }
          }

        // Checks to make sure that an int or float function has a return and that it returns
        // the appropriate type. It even checks if it returns an ID of the appropriate type.
        // If there is no return, then the file is rejected.
        public static void intFloatFuncCheck(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList, String[][] codeGen){
          boolean intCorrect = false;
          boolean floatCorrect = false;
          int countIntFunc = 0;
          int countFloatFunc = 0;
          for(int r = 0; r < identifierList.size(); r++){
            if((identifierList.get(r).getCategory()).equals("FUNCTION")){
              if((identifierList.get(r).getDefCall()).equals("DEFINITION")){
                if(((identifierList.get(r).getType()).equals("int")) || ((identifierList.get(r).getType()).equals("void"))){
                  countIntFunc++;
                }else if(((identifierList.get(r).getType()).equals("float")) || ((identifierList.get(r).getType()).equals("void"))){
                  countFloatFunc++;
                }
              }
            }
          }
          if(countIntFunc == identifierList.size()){
            floatCorrect = true;
          }else if(countFloatFunc == identifierList.size()){
            intCorrect = true;
          }
          for(int i = 0; i < identifierList.size(); i++){
            String funcName = "";
            String checkToken = "";
            int returnCount = 0;
            if((identifierList.get(i).getCategory()).equals("FUNCTION")){
              if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                if((identifierList.get(i).getType()).equals("int")){
                  funcName = identifierList.get(i).getName();
                  for(int k = 0; k < tokenList.size(); k++){
                    if((tokenList.get(k).getToken()).equals(funcName)){
                      if((tokenList.get(k-1).getToken()).equals("int")){
                        for(int r = k+1; r < tokenList.size(); r++){
                          if((tokenList.get(r).getToken()).equals("{")){
                            for(int p = r+1; p < tokenList.size(); p++){
                              if((tokenList.get(p).getToken()).equals("return")){
                                returnCount++;
                                if((tokenList.get(p+1).getNumType()).equals("int")){
                                  intCorrect = true;
                                  codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                                  codeGen[LexAnalyzer.indexCode][1] = "return";
                                  codeGen[LexAnalyzer.indexCode][2] = "";
                                  codeGen[LexAnalyzer.indexCode][3] = "";
                                  codeGen[LexAnalyzer.indexCode][4] = "t"+String.valueOf(LexAnalyzer.codeVar);
                                  LexAnalyzer.indexCode++;
                                  LexAnalyzer.codeVar++;
                                  break;
                                }else if((tokenList.get(p+1).getTokenType()).equals("ID")){
                                  checkToken = tokenList.get(p+1).getToken();
                                  for(int a = 0; a < identifierList.size(); a++){
                                    if((identifierList.get(a).getCategory()).equals("VARIABLE")){
                                      if((identifierList.get(a).getDefCall()).equals("INSTANTIATION")){
                                        if((identifierList.get(a).getName()).equals(checkToken)){
                                          if((identifierList.get(a).getType()).equals("int")){
                                            intCorrect = true;
                                            codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                                            codeGen[LexAnalyzer.indexCode][1] = "return";
                                            codeGen[LexAnalyzer.indexCode][2] = "";
                                            codeGen[LexAnalyzer.indexCode][3] = "";
                                            codeGen[LexAnalyzer.indexCode][4] = identifierList.get(a).getName();
                                            LexAnalyzer.indexCode++;
                                            break;
                                          }
                                        }
                                      }
                                    }
                                  }
                                }else{
                                  intCorrect = false;
                                  //System.out.println("REJECT17");
                                  //System.exit(1);
                                  break;
                                }
                              }else if(((tokenList.get(p).getToken()).equals("}")) && returnCount == 0){
                                intCorrect = false;
                                //System.out.println("REJECT18");
                                //System.exit(1);
                                break;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }else if((identifierList.get(i).getType()).equals("float")){
                  funcName = identifierList.get(i).getName();
                  for(int k = 0; k < tokenList.size(); k++){
                    if((tokenList.get(k).getToken()).equals(funcName)){
                      if((tokenList.get(k-1).getToken()).equals("float")){
                        for(int r = k+1; r < tokenList.size(); r++){
                          if((tokenList.get(r).getToken()).equals("{")){
                            for(int p = r+1; p < tokenList.size(); p++){
                              if((tokenList.get(p).getToken()).equals("return")){
                                returnCount++;
                                if((tokenList.get(p+1).getNumType()).equals("float")){
                                  floatCorrect = true;
                                  codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                                  codeGen[LexAnalyzer.indexCode][1] = "return";
                                  codeGen[LexAnalyzer.indexCode][2] = "";
                                  codeGen[LexAnalyzer.indexCode][3] = "";
                                  codeGen[LexAnalyzer.indexCode][4] = "t"+String.valueOf(LexAnalyzer.codeVar);
                                  LexAnalyzer.indexCode++;
                                  LexAnalyzer.codeVar++;
                                  break;
                                }else if((tokenList.get(p+1).getTokenType()).equals("ID")){
                                  checkToken = tokenList.get(p+1).getToken();
                                  for(int a = 0; a < identifierList.size(); a++){
                                    if((identifierList.get(a).getCategory()).equals("VARIABLE")){
                                      if((identifierList.get(a).getDefCall()).equals("INSTANTIATION")){
                                        if((identifierList.get(a).getName()).equals(checkToken)){
                                          if((identifierList.get(a).getType()).equals("float")){
                                            intCorrect = true;
                                            codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                                            codeGen[LexAnalyzer.indexCode][1] = "return";
                                            codeGen[LexAnalyzer.indexCode][2] = "";
                                            codeGen[LexAnalyzer.indexCode][3] = "";
                                            codeGen[LexAnalyzer.indexCode][4] = identifierList.get(a).getName();
                                            LexAnalyzer.indexCode++;
                                            break;
                                          }
                                        }
                                      }
                                    }
                                  }
                                }else{
                                  floatCorrect = false;
                                  //System.out.println("REJECT19");
                                  //System.exit(1);
                                  break;
                                }
                              }else if(((tokenList.get(p).getToken()).equals("}")) && returnCount == 0){
                                floatCorrect = false;

                                break;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }

        // Checks that the operators on both sides of an operand match in type
        public static void operatorOperandCheck(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList, String[][] codeGen){
          boolean tester = false;
          int count = 0;

          for(int i = 0; i < tokenList.size(); i++){
            if(((tokenList.get(i).getToken()).equals("+")) || ((tokenList.get(i).getToken()).equals("-")) || ((tokenList.get(i).getToken()).equals("/")) || ((tokenList.get(i).getToken()).equals("*"))){
              count++;
            }
          }

          if(count == 0){
            tester = true;
          }

          for(int i = 0; i < tokenList.size(); i++){
            if(((tokenList.get(i).getToken()).equals("+")) || ((tokenList.get(i).getToken()).equals("-")) || ((tokenList.get(i).getToken()).equals("/")) || ((tokenList.get(i).getToken()).equals("*"))){
              if(((tokenList.get(i-1).getNumType()).equals((tokenList.get(i+1).getNumType())))){
                tester = true;
                String token1 = tokenList.get(i-1).getToken().trim();
                String token2 = tokenList.get(i+1).getToken().trim();
                String token1Search = "", token1Var = "";
                String token2Search = "", token2Var = "";;
                for(int j = 0; j < identifierList.size(); j++){
                  if((identifierList.get(j).getName()).equals(token1)){
                    if((identifierList.get(j).getCategory()).equals("VARIABLE")){
                      if((identifierList.get(j).getDefCall()).equals("INSTANTIATION")){
                        // token1Var is not being assigned
                        token1Var = identifierList.get(j).getVarType();
                      }
                    }
                  }else if((identifierList.get(j).getName()).equals(token2)){
                    if((identifierList.get(j).getCategory()).equals("VARIABLE")){
                      if((identifierList.get(j).getDefCall()).equals("INSTANTIATION")){
                        token2Var = identifierList.get(j).getVarType();
                      }
                    }
                  }
                }
                if((tokenList.get(i).getToken()).equals("-")){
                codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                codeGen[LexAnalyzer.indexCode][1] = "sub";
                codeGen[LexAnalyzer.indexCode][2] = token1;
                codeGen[LexAnalyzer.indexCode][3] = token2;
                codeGen[LexAnalyzer.indexCode][4] = "t"+String.valueOf(LexAnalyzer.indexCode);
                LexAnalyzer.indexCode++;
                LexAnalyzer.codeVar++;
              }else if((tokenList.get(i).getToken()).equals("*")){
                codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                codeGen[LexAnalyzer.indexCode][1] = "mult";
                codeGen[LexAnalyzer.indexCode][2] = token1;
                codeGen[LexAnalyzer.indexCode][3] = token2;
                codeGen[LexAnalyzer.indexCode][4] = "t"+String.valueOf(LexAnalyzer.indexCode);
                LexAnalyzer.indexCode++;
                LexAnalyzer.codeVar++;
              }else if((tokenList.get(i).getToken()).equals("/")){
                codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                codeGen[LexAnalyzer.indexCode][1] = "div";
                codeGen[LexAnalyzer.indexCode][2] = token1;
                codeGen[LexAnalyzer.indexCode][3] = token2;
                codeGen[LexAnalyzer.indexCode][4] = "t"+String.valueOf(LexAnalyzer.indexCode);
                LexAnalyzer.indexCode++;
                LexAnalyzer.codeVar++;
              } else if((tokenList.get(i).getToken()).equals("+")){
                codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
                codeGen[LexAnalyzer.indexCode][1] = "add";
                codeGen[LexAnalyzer.indexCode][2] = token1;
                codeGen[LexAnalyzer.indexCode][3] = token2;
                codeGen[LexAnalyzer.indexCode][4] = "t"+String.valueOf(LexAnalyzer.codeVar);
                LexAnalyzer.indexCode++;
                LexAnalyzer.codeVar++;
              }
                if(token1Var.equals(token2Var)){
                  tester = true;
                }else{
                  tester = false;
                  //System.out.println("REJECT21");
                  //System.exit(1);
                }
              }else{
                tester = false;
                //System.out.println("REJECT22");
                //System.exit(1);
              }
      }else if(((tokenList.get(i).getToken()).equals("<=")) || ((tokenList.get(i).getToken()).equals("<")) || ((tokenList.get(i).getToken()).equals(">"))
          || ((tokenList.get(i).getToken()).equals(">="))|| ((tokenList.get(i).getToken()).equals("=="))|| ((tokenList.get(i).getToken()).equals("!="))){
            if((tokenList.get(i).getToken()).equals("<=")){
              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "BRLEQ";
              codeGen[LexAnalyzer.indexCode][2] = "" /*val here */;
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = String.valueOf(LexAnalyzer.indexCode + 2);
              LexAnalyzer.indexCode++;

              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "block";
              codeGen[LexAnalyzer.indexCode][2] = "";
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = "";
              LexAnalyzer.indexCode++;
            }else if((tokenList.get(i).getToken()).equals(">")){
              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "BRG";
              codeGen[LexAnalyzer.indexCode][2] = "" /*val here */;
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = String.valueOf(LexAnalyzer.indexCode + 2);
              LexAnalyzer.indexCode++;

              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "block";
              codeGen[LexAnalyzer.indexCode][2] = "";
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = "";
              LexAnalyzer.indexCode++;
            }else if((tokenList.get(i).getToken()).equals("!=")){
              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "BRNEQ";
              codeGen[LexAnalyzer.indexCode][2] = "" /*val here */;
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = String.valueOf(LexAnalyzer.indexCode + 2);
              LexAnalyzer.indexCode++;

              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "block";
              codeGen[LexAnalyzer.indexCode][2] = "";
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = "";
              LexAnalyzer.indexCode++;
              }else if((tokenList.get(i).getToken()).equals(">=")){
              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "BRGEQ";
              codeGen[LexAnalyzer.indexCode][2] = "" /*val here */;
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = String.valueOf(LexAnalyzer.indexCode + 2);
              LexAnalyzer.indexCode++;

              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "block";
              codeGen[LexAnalyzer.indexCode][2] = "";
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = "";
              LexAnalyzer.indexCode++;
            }else if((tokenList.get(i).getToken()).equals("<")){
              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "BRL";
              codeGen[LexAnalyzer.indexCode][2] = "" /*val here */;
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = String.valueOf(LexAnalyzer.indexCode + 2);
              LexAnalyzer.indexCode++;

              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "block";
              codeGen[LexAnalyzer.indexCode][2] = "";
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = "";
              LexAnalyzer.indexCode++;
            }else if((tokenList.get(i).getToken()).equals("==")){
              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "BREQ";
              codeGen[LexAnalyzer.indexCode][2] = "" /*val here */;
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = String.valueOf(LexAnalyzer.indexCode + 2);
              LexAnalyzer.indexCode++;

              codeGen[LexAnalyzer.indexCode][0] = String.valueOf(LexAnalyzer.indexCode);
              codeGen[LexAnalyzer.indexCode][1] = "block";
              codeGen[LexAnalyzer.indexCode][2] = "";
              codeGen[LexAnalyzer.indexCode][3] = "";
              codeGen[LexAnalyzer.indexCode][4] = "";
              LexAnalyzer.indexCode++;
            }
          }
        }
      }

        // Checks that the value on the right matches the type on the left
        public static void operatorOperandAssignmentCheck(LinkedList<Identifier> identifierList, LinkedList<Token> tokenList, String[][] codeGen){
          boolean typeValMatch = true;
          for(int i = 0; i < identifierList.size(); i++){
            String varName = "";
            int scope = 0;
            if((identifierList.get(i).getCategory()).equals("VARIABLE")){
              varName = identifierList.get(i).getName();
              scope = identifierList.get(i).getScope();
              for(int a = 0; a < tokenList.size(); a++){
                if((tokenList.get(a).getToken()).equals(varName)){
                  if(tokenList.get(a).getDepth() == scope){
                    if((tokenList.get(a+1).getToken()).equals("=")){
                      if((tokenList.get(a+2).getNumType()).equals(identifierList.get(i).getType())){
                        typeValMatch = true;
                        a = a+3;
                      }else{
                        typeValMatch = false;
                        System.out.println("REJECT23");
                        System.exit(1);
                      }
                    }
                  }
                }
              }
            }
          }
        }

        // Checks to make sure that a variable is only declared once
        public static void varDeclaredOnceCheck(LinkedList<Identifier> identifierList){
          boolean tester = true;
          for(int i = 0; i < identifierList.size(); i++){
            String varName = "";
            int scope = 0;
            String funcName = "";
            int index = 0;
            String typeVar = "";
            if((identifierList.get(i).getCategory()).equals("VARIABLE")){
              if((identifierList.get(i).getDefCall()).equals("DEFINITION")){
                varName = identifierList.get(i).getName();
                scope = identifierList.get(i).getScope();
                funcName = identifierList.get(i).getFunctionNameCurrent();
                index = identifierList.get(i).getTokenIndex();
                typeVar = identifierList.get(i).getVarType();
                for(int a = 0; a < identifierList.size(); a++){
                  if((identifierList.get(a).getCategory()).equals("VARIABLE")){
                    if((identifierList.get(a).getDefCall()).equals("DEFINITION")){
                      if((identifierList.get(a).getName()).equals(varName)){
                        if(identifierList.get(a).getScope() == scope){
                          if(!(identifierList.get(a).getTokenIndex() == index)){
                            if((identifierList.get(a).getFunctionNameCurrent()).equals("GLOBAL")){
                              tester = false;
                              System.out.println("REJECT24");
                              System.exit(1);
                            }else if(((identifierList.get(a).getFunctionNameCurrent()).equals(funcName)) && (!(funcName.equals("GLOBAL")))){
                              tester = false;
                              System.out.println("REJECT25");
                              System.exit(1);
                            }else{
                              tester = true;
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }

        public static void createExpression(LinkedList<Token> tokenList){
           for(int i = 0; i < tokenList.size(); i++){
             String expression = "";
             if((tokenList.get(i).getToken()).equals("=")){
               for(int h = 0; h < tokenList.size(); h++){
                 if((tokenList.get(h).getToken()).equals(";")){
                   System.out.println("hi: " + expression);
                //   createPostfix(expression);
                //   break;
                 }else{
                   expression = expression + tokenList.get(h).getToken();
                 }
               }
             }
           }
         }



    static class Token{
      // The values on each line
      private String tokenType;     // ID, DELIM,...
      private String numType;       // int, float
      private String token;         // the actual token
      private int depth;            // the scope of the token
      private Token next;
      private Token previous;

      // Token constructors
      public Token(){
      }

      public Token(String tokenType, String numType, String token, int depth){
        this.tokenType = tokenType;
        this.numType = numType;
        this.token = token;
        this.depth = depth;
      }

      //Methods for the Token Class

      // Get Methods for Token Class
      public String getTokenType(){
        return tokenType;
      }

      public String getNumType(){
        return numType;
      }

      public String getToken(){
        return token;
      }

      public int getDepth(){
        return depth;
      }

      // Set Methods for Token Class
      public void setTokenType(String newTokenType){
        this.tokenType = newTokenType;
      }

      public void setNumType(String newNumType){
        this.numType = newNumType;
      }

      public void setToken(String newToken){
        this.token = newToken;
      }

      public void setDepth(int newDepth){
        this.depth = newDepth;
      }

      public boolean equals(Object obj){
         return true;
      }

      public void displayTokenAll(){
         System.out.println("Type: " + tokenType + "      Num Type: " + numType + "    Token: " +  token + "    Scope: " + depth);
      }

      public String toString(){
        return "\n" + getTokenType() + " " + getNumType() + "  "+ getToken() + "  " + getDepth() + "\n";
      }
    }











    static class Identifier{
       // The values on each line
       private String category;             // Variable or Function
       private String type;                 // int, float, void
       private String name;                 // The name of the Identifier
       private int scope;                   // The scope of the Identifier
       private int numOfParams;             // Number of params the function has
       private String defCall;              // definition, instantiation, or call
       private String varType;              // if its an array
       private int arraySize;               // length of the array
       private String functionNameCurrent;  // name of function its in
       private int tokenIndex;              // index of id in tokenList
       private Identifier next;
       private Identifier previous;

       // Identifier constructors
       public Identifier(){
       }

       public Identifier(String category, String type, String name, int scope,
        int numOfParams, String defCall, String varType, int arraySize, String functionNameCurrent,
        int tokenIndex){
         this.category = category;
         this.type = type;
         this.name = name;
         this.scope = scope;
         this.numOfParams = numOfParams;
         this.defCall = defCall;
         this.varType = varType;
         this.arraySize = arraySize;
         this.functionNameCurrent = functionNameCurrent;
         this.tokenIndex = tokenIndex;
       }

       //Methods for the Identifier Class

       // Get Methods for Identifier Class
       public String getCategory(){
         return category;
       }

       public String getType(){
         return type;
       }

       public String getName(){
         return name;
       }

       public int getScope(){
         return scope;
       }

       public int getNumOfParams(){
         return numOfParams;
       }

       public String getDefCall(){
         return defCall;
       }

       public String getVarType(){
         return varType;
       }

       public int getArraySize(){
         return arraySize;
       }

       public String getFunctionNameCurrent(){
         return functionNameCurrent;
       }

       public int getTokenIndex(){
         return tokenIndex;
       }

       // Set Methods for Identifier Class
       public void setCategory(String newCategory){
         this.category = newCategory;
       }

       public void setType(String newType){
         this.type = newType;
       }

       public void setName(String newName){
         this.name = newName;
       }

       public void setScope(int newScope){
         this.scope = newScope;
       }

       public void setNumOfParams(int newNumOfParams){
         this.numOfParams = newNumOfParams;
       }

       public void setDefCall(String newDefCall){
         this.defCall = newDefCall;
       }

       public void setVarType(String newVarType){
         this.varType = newVarType;
       }

       public void setArraySize(int newArraySize){
         this.arraySize = newArraySize;
       }

       public void setFunctionNameCurrent(String newFunctionNameCurrent){
         this.functionNameCurrent = newFunctionNameCurrent;
       }

       public void setTokenIndex(int newTokenIndex){
         this.tokenIndex = newTokenIndex;
       }

       public boolean equals(Object obj){
          return true;
       }

       public void displayIdentifierAll(){
          System.out.println("Category: " + category + "    Type: " +  type + "    Name: " + name + "        Scope: " + scope + "     Number of Params: " + numOfParams +
          "       DefCall: " + defCall + "       VarType: " + varType + "       ArraySize: " + arraySize + "       Function Name: " + functionNameCurrent +
          "       Token Index: " + tokenIndex);
       }

       public String toString(){
         return "\n" + getCategory() + " " + getType() + " " + getName() + "  " + getScope() + "   " + getNumOfParams() + "      " + getDefCall() + "         " + getVarType() +
          "   " + getArraySize() + "  " + getFunctionNameCurrent() + "  " + getTokenIndex() + "\n";
       }
    }

    static class Param{
        // The values on each line
        private String functionName;          // name of the function
        private String paramType;             // the type of the param, int, float, void
        private String paramVarType;          // if the param is an array
        private Token next;
        private Token previous;

        // Param constructors
        public Param(){
        }

        public Param(String functionName, String paramType, String paramVarType){
          this.functionName = functionName;
          this.paramType = paramType;
          this.paramVarType = paramVarType;
        }

        //Methods for the Param Class

        // Get Methods for Param Class
        public String getFunctionName(){
          return functionName;
        }

        public String getParamType(){
          return paramType;
        }

        public String getParamVarType(){
          return paramVarType;
        }

        // Set Methods for Param Class
        public void setFunctionName(String newFunctionName){
          this.functionName = newFunctionName;
        }

        public void setParamType(String newParamType){
          this.paramType = newParamType;
        }

        public void setParamVarType(String newParamVarType){
          this.paramVarType = newParamVarType;
        }

        public boolean equals(Object obj){
           return true;
        }

        public void displayTokenAll(){
           System.out.println("Function Name: " + functionName + "      Param Type: " + paramType + "    Param Var Type: " +  paramVarType);
        }

        public String toString(){
          return "\n" + getFunctionName() + " " + getParamType() + "  "+ getParamVarType() + "\n";
        }
      }


}
