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
    static int depth = 0;

    // a variable to track the current parse index in the tokens array
    static int parsecount = 0;

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
        ArrayList < String > relational = new ArrayList < > ();
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
        ArrayList < String > keywords = new ArrayList < > ();
        keywords.add("float");
        keywords.add("int");
        keywords.add("else");
        keywords.add("if");
        keywords.add("return");
        keywords.add("void");
        keywords.add("while");

        // Deliminators
        ArrayList < String > delims = new ArrayList < > ();
        delims.add(";");
        delims.add(",");
        delims.add("(");
        delims.add(")");
        delims.add("[");
        delims.add("]");
        delims.add("{");
        delims.add("}");
        delims.add(".");

        // Known Mistakes
        ArrayList < String > mistakes = new ArrayList < > ();
        mistakes.add("!");
        mistakes.add("@");
        mistakes.add("_");

        // ArrayList for Generated Identifiers
        ArrayList < Identifier > lockerspace = new ArrayList < > ();
        ArrayList < Token > tokens = new ArrayList < > ();

        // Calls the lexical analyzer
        lexical(keywords, relational, delims, mistakes, lockerspace, file, tokens);

        // Calling the parser
        grammar(tokens);

        // The number of labels
        int n = lockerspace.size();

        // The first prime number greater than 2n
        int p = 0;

        for (int i = (2 * n + 1); true; i++) {
            if (isPrime(i)) {
                p = i;
                break;
            }
        }

        // Construct an array with all the symbols
        String[] symbolTable = new String[p];

        for (int i = 0; i < symbolTable.length; i++) {
            symbolTable[i] = null;
        }

        for (int i = 0; i <= lockerspace.size() - 1; i++) {
            String temp1 = lockerspace.get(i).getLabel();
            int num = insert(symbolTable, temp1, p);

            // Sets the hashVal
            lockerspace.get(i).setHashLocation(num);
        }
    } // end main

    /*******************************************************
     * Between p1 and p2 nothing has changed here. This     *
     * Method checks is the String passed to it is a number *
     *******************************************************/


    // Checks to see if the number is prime
    public static boolean isPrime(int n){
      for(int j = 2; (j*j <= n); j++){
        if(n % j == 0){
          return false;
        }
      }
      return true;
    }

    public static boolean isInteger(String stringNumber) {
        try {
            Integer.parseInt(stringNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Hashes
    public static int hash(String key, int arraySize) {
        int hashVal = 0;
        int alpha = 0;

        for (int j = 0; j < key.length(); j++) {
            if (key.charAt(j) > 64 && key.charAt(j) < 91) {
                alpha = key.charAt(j) - 64;
            } else if (key.charAt(j) > 96 && key.charAt(j) < 123) {
                alpha = key.charAt(j) - 96;
            }
            hashVal = (hashVal * 26 + alpha) % arraySize;
        }
        return hashVal;
    }

    // Inputs into
    static public int insert(String symbolTable[], String label, int arraySize) {
        int hashVal = hash(label, arraySize);

        while (symbolTable[hashVal] != null) {
            if (symbolTable[hashVal] == label) {}
            ++hashVal;
            hashVal %= arraySize;
        }
        symbolTable[hashVal] = label;
        return hashVal;
    }

    /**********************************************************************************
     * Reads in possible numbers and checks to see if they are valid                   *
     **********************************************************************************/

    public static void checkNum(String num, ArrayList < String > mistakes, ArrayList<Token> tokens) {
        char[] array = num.toCharArray();
        String numToPrint = "";
        String errorNum = "";
        String error = "";

        for (int r = 0; r < array.length; r++) {
            if ((array[0] == '.') || (array[0] == 'E')) {
                errorNum = num;
                break;
            }

            if (isInteger(String.valueOf(array[r]))) {
                numToPrint = numToPrint + array[r];

                if (r + 1 < array.length) {
                    if (array[r + 1] == '.') {
                        numToPrint = numToPrint + array[r + 1];
                        r = r + 1;

                        if (r + 1 < array.length) {
                            if (isInteger(String.valueOf(array[r + 1]))) {
                                numToPrint = numToPrint + array[r + 1];
                                r = r + 1;
                            } else {
                                errorNum = num;
                                break;
                            }
                        }
                    } else if (array[r + 1] == 'E') {
                        numToPrint = numToPrint + array[r + 1];
                        r = r + 1;

                        if (r + 1 < array.length) {
                            if ((array[r + 1] == '+') || (array[r + 1] == '-')) {
                                numToPrint = numToPrint + array[r + 1];
                                r = r + 1;
                            } else if (isInteger(String.valueOf(array[r + 1]))) {
                                numToPrint = numToPrint + array[r + 1];
                                r = r + 1;
                            } else {
                                errorNum = num;
                                break;
                            }
                        } else {
                            errorNum = num;
                            break;
                        }
                    } else if (isInteger(String.valueOf(array[r + 1]))) {
                        numToPrint = numToPrint + array[r + 1];
                        r = r + 1;
                    }
                }
            } else if (array[r] == '.') {
                numToPrint = numToPrint + array[r];

                if (r + 1 < array.length) {
                    if (isInteger(String.valueOf(array[r + 1]))) {
                        numToPrint = numToPrint + array[r + 1];
                        r = r + 1;
                    } else {
                        errorNum = num;
                        break;
                    }
                } else {
                    errorNum = num;
                    break;
                }
            } else if (array[r] == 'E') {
                numToPrint = numToPrint + array[r];

                if (r + 1 < array.length) {
                    if ((array[r + 1] == '+') || (array[r + 1] == '-')) {
                        numToPrint = numToPrint + array[r + 1];
                        r = r + 1;
                    } else if (isInteger(String.valueOf(array[r + 1]))) {
                        numToPrint = numToPrint + array[r + 1];
                        r = r + 1;
                    }
                } else {
                    errorNum = num;
                    break;
                }
            }

            if (mistakes.contains(String.valueOf(array[r]))) {
                for (int f = r; f < array.length; f++) {
                    error = error + array[f];
                }
                //System.out.println("error: " + error);
                System.out.println("REJECT");
                System.exit(1);
                break;
            }
        }

        if (errorNum.equals("") == true) {
            if ((num.indexOf('.')) >= 0 || (num.indexOf('E')) >= 0) {
                //System.out.println("FLOAT: " + numToPrint);
                Token collected = new Token("number", numToPrint);
                tokens.add(collected);
                numToPrint = "";
            } else {
                //System.out.println("NUM: " + numToPrint);
                Token collected = new Token("number", numToPrint);
                tokens.add(collected);
                numToPrint = "";
            }
        } else {
            //System.out.println("ERROR: " + errorNum);
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    /**********************************************************************************************************
     * Generates tokens based on the input file and also prints out its result to the console		            *
     * keywords : the ArrayList keywords to check if the string contains a keyword							         *
     * relational : the ArrayList relational to check if the string contains a relational operator			      *
     * delims : the ArrayList delims to check if the string contains an delimitor							         *
     * mistakes : the ArrayList mistakes to check if the string contains an error								            *
     * symbolTable : the ArrayList symbolTable that stores the Identifierifiers, this isn't fully implemented yet	*
     * file : the file that is read in from the command line													            *
     **********************************************************************************************************/

    public static void lexical(ArrayList < String > keywords, ArrayList < String > relational, ArrayList < String > delims, ArrayList < String > mistakes, ArrayList < Identifier > lockerspace, File file, ArrayList < Token > tokens) {
        try {
            Scanner input = new Scanner(file);
            int tracker = 0;

            while (input.hasNextLine()) {
                String text = input.nextLine();
                String compare = "";
                //System.out.println("");

                if (text.isEmpty() == false) {
                    //System.out.println("INPUT: " + text);
                }

                char[] newArray = text.toCharArray();
                // Checks for comments
                for (LexAnalyzer.currentIndex = 0; LexAnalyzer.currentIndex < newArray.length; LexAnalyzer.currentIndex++) {
                    if ((LexAnalyzer.currentIndex + 1) < newArray.length) {
                        if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("/*")) {
                            tracker++;
                            LexAnalyzer.currentIndex++;
                        } else if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("*/")) {
                            if (tracker > 0) {
                                tracker--;
                            } else if (tracker == 0) {
                                compare = compare + newArray[LexAnalyzer.currentIndex];
                            }

                            LexAnalyzer.currentIndex++;
                        } else if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("//")) {
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
                            //System.out.println("KEYWORD: " + compare);
                            Token collected = new Token("keywords", compare);
                            tokens.add(collected);
                            compare = "";

                        }

                        // If there a relational operator that matches the string
                        else if (relational.contains(compare)) {
                            if ((LexAnalyzer.currentIndex + 1) < newArray.length) {
                                if (relational.contains(String.valueOf(newArray, LexAnalyzer.currentIndex, 2))) {
                                    compare = compare + newArray[LexAnalyzer.currentIndex + 1];
                                    //System.out.println("RELATIONAL: " + compare);
                                    Token collected = new Token("relational", compare);
                                    tokens.add(collected);
                                    compare = "";
                                    compare = "";
                                } else {
                                    if (!(relational.contains(String.valueOf(newArray[LexAnalyzer.currentIndex - 1])))) {
                                        //System.out.println("RELATIONAL: " + compare);
                                        Token collected = new Token("relational", compare);
                                        tokens.add(collected);
                                        compare = "";
                                    }
                                }

                                compare = "";
                            }
                        }

                        // if there is an error in the string
                        else if (mistakes.contains(compare)) {
                            boolean exception = false;
                            String BigNoNo = "";

                            if (compare.equals("!")) {
                                if (LexAnalyzer.currentIndex + 1 < newArray.length) {
                                    if ((String.valueOf(newArray[LexAnalyzer.currentIndex + 1])).equals("=")) {
                                        LexAnalyzer.currentIndex++;
                                        compare = compare + String.valueOf(newArray[LexAnalyzer.currentIndex]);
                                        Token collected = new Token("relational", compare);
                                        tokens.add(collected);
                                        compare = "";
                                        LexAnalyzer.currentIndex++;
                                        exception = true;
                                    }
                                } else {
                                    BigNoNo = "!";
                                    exception = false;
                                    LexAnalyzer.currentIndex++;
                                }
                            } else {
                                for (int d = LexAnalyzer.currentIndex; d < newArray.length; d++) {
                                    if (!(String.valueOf(newArray[d])).equals(" ")) {
                                        BigNoNo = BigNoNo + newArray[d];
                                    } else {
                                        LexAnalyzer.currentIndex = d;
                                        break;
                                    }
                                }

                            }
                            if (

                                exception == false)
                                //System.out.println("ERROR: " + BigNoNo);
                                System.out.println("REJECT");
                            System.exit(1);
                            BigNoNo = "";
                            compare = "";

                        }
                        // if read integer
                        else if ((isInteger(compare) == true) || (compare.equals(".")) || (compare.equals("E"))) {
                            String newNum = "";
                            LexAnalyzer.isNum = true;

                            for (int x = LexAnalyzer.currentIndex; x < newArray.length; x++) {
                                if ((newArray[x] != ' ') && (!(delims.contains(String.valueOf(newArray[x]))))) {
                                    newNum = newNum + newArray[x];
                                } else {
                                    LexAnalyzer.currentIndex = x;
                                    checkNum(newNum, mistakes, tokens);
                                    compare = "";
                                    LexAnalyzer.isNum = false;
                                    break;
                                }

                                if (x == (newArray.length - 1)) {
                                    checkNum(newNum, mistakes, tokens);
                                }

                                LexAnalyzer.currentIndex = x;
                            }
                        }

                        // if the string is a delimitor
                        else if (delims.contains(compare)) {
                            //System.out.println("DELIMS: " + compare);
                            Token collected = new Token("delims", compare);
                            tokens.add(collected);
                            compare = "";
                        }

                        // at this point most likely an identifier
                        else if ((String.valueOf(newArray[LexAnalyzer.currentIndex]).equals(" ")) || (delims.contains(String.valueOf(newArray[LexAnalyzer.currentIndex]))) ||
                            (relational.contains(String.valueOf(newArray[LexAnalyzer.currentIndex])))) {
                            String newID = "";
                            String testString = "";
                            String beforeTest = "";
                            Token data = new Token("", "");
                            Token data1 = new Token("", "");
                            Token data2 = new Token("", "");
                            boolean tester = false;
                            boolean tester1 = false;
                            boolean tester2 = false;

                            if ((!(keywords.contains(compare))) && (!(relational.contains(compare))) && (!(delims.contains(compare))) &&
                                (!(mistakes.contains(compare))) && (!(compare).equals(" ")) && (isNum == false)) {
                                for (int k = 0; k < compare.length(); k++) {
                                    char f = compare.charAt(k);
                                    beforeTest = testString;
                                    testString = testString + f;

                                    if (delims.contains(String.valueOf(f))) {
                                        data = new Token("delims", String.valueOf(f));
                                        compare = "";
                                        newID = beforeTest;
                                    } else if (mistakes.contains(String.valueOf(f))) {
                                        String BigNoNo = compare.substring(k, compare.length());
                                        //System.out.println("ERROR: " + BigNoNo);
                                        System.out.println("REJECT");
                                        System.exit(1);
                                        compare = "";
                                        break;
                                    } else if (relational.contains(String.valueOf(f))) {
                                        data = new Token("delims", String.valueOf(f));
                                        tester = true;
                                    } else {
                                        newID = newID + f;
                                    }

                                }

                                if (newID.isEmpty() == false) {
                                    if (keywords.contains(newID)) {
                                        Token data5 = new Token("KEYWORD", newID);
                                        tester2 = true;
                                    } else {
                                        Identifier data3 = new Identifier(newID, LexAnalyzer.depth, 0);
                                        lockerspace.add(data3);
                                        Token data4 = new Token("ID", newID);
                                        tokens.add(data4);
                                    }
                                }
                                if (tester2 == true) {
                                    tokens.add(data2);
                                }
                                if (tester == true) {
                                    tokens.add(data);
                                }
                                if (tester1 == true) {
                                    tokens.add(data1);
                                }
                            } //good
                            compare = "";
                        } //good


                        // if the string only contains 1 char
                        else if (LexAnalyzer.currentIndex == (newArray.length - 1)) {
                            String newID = "";
                            Token data = new Token("", "");
                            Token data1 = new Token("", "");
                            Token data2 = new Token("", "");
                            boolean tester = false;
                            boolean tester1 = false;
                            boolean tester2 = false;

                            for (int p = 0; p < compare.length(); p++) {
                                char f = compare.charAt(p);
                                if (delims.contains(String.valueOf(f))) {
                                    data = new Token("delims", String.valueOf(f));
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
                                    //System.out.println("ERROR: " + BigNoNo);
                                    System.out.println("REJECT");
                                    System.exit(1);
                                    compare = "";
                                    break;
                                } else if (relational.contains(String.valueOf(f))) {
                                    data1 = new Token("RELATIONAL", String.valueOf(f));
                                    tester1 = true;
                                } else {
                                    newID = newID + f;
                                }
                            }
                            if (newID.isEmpty() == false) {
                                if (keywords.contains(newID)) {
                                    Token data3 = new Token("KEYWORD", newID);
                                    tester2 = true;
                                } else {
                                    Identifier data3 = new Identifier(newID, LexAnalyzer.depth, 0);
                                    lockerspace.add(data3);
                                    Token data4 = new Token("ID", newID);
                                    tokens.add(data4);
                                }
                            }
                            if (tester2 == true) {
                                tokens.add(data2);
                            }
                            if (tester == true) {
                                tokens.add(data);
                            }
                            if (tester1 == true) {
                                tokens.add(data1);
                            }

                        } //good
                    }
                }
            }




            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // The parser
    public static void grammar(ArrayList < Token > tokens) {

        Token EOF = new Token("EOF", "EOF");
        tokens.add(EOF);

        A(tokens);
        System.out.println("ACCEPT");
        System.exit(1);
    }

    // A -> B
    public static void A(ArrayList < Token > tokens) {
        B(tokens);
        return;
    }

    // B -> CB2
    public static void B(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else {
            C(tokens);
            B2(tokens);
            return;
        }
    }

    // B2 -> CB2 | @			@ = empty
    public static void B2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void"))) {
            C(tokens);
            B2(tokens);
            return;
        } else {
            return;
        }
    }

    // C -> F id C2
    public static void C(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else {
            if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void"))); {
                F(tokens);
                if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
                    LexAnalyzer.parsecount++;
                    C2(tokens);
                    return;
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }
                return;
            }
        }

    }

    // C2 -> ; | [num]; |(G)H
    public static void C2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
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
                            System.out.println("REJECT");
                            System.exit(1);
                        }
                    } else {
                        System.out.println("REJECT");
                        System.exit(1);
                    }
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }
            } else if ((tokens.get(LexAnalyzer.parsecount).getToken().equals(";"))) {
                LexAnalyzer.parsecount++;
                return;
            } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                G(tokens);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    H(tokens);
                    return;
                } else

                {
                    System.out.println("REJECT");
                    System.exit(1);
                }
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        }
    }

    // F -> int | void | float
    public static void F(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float"))) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }

    }

    // G -> int id J2 I2 | void G2 | float id J2 I2
    public static void G(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else {
            if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float"))) {
                LexAnalyzer.parsecount++;
                if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
                    LexAnalyzer.parsecount++;
                    J2(tokens);
                    I2(tokens);
                    return;
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }
            } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) {
                LexAnalyzer.parsecount++;
                G2(tokens);
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        }
    }

    //G2 ->	id J2 I2 | @
    public static void G2(ArrayList < Token > tokens) {

        if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            J2(tokens);
            I2(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals(")")) {
            return;
        }
    }

    //H  -> { K L }
    public static void H(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) {
            LexAnalyzer.parsecount++;
            K(tokens);
            L(tokens);
            LexAnalyzer.parsecount--;
            if ((tokens.get(LexAnalyzer.parsecount + 1).getToken()).equals("}")) {
                LexAnalyzer.parsecount++;
                if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
                    System.out.println("ACCEPT");
                    System.exit(1);
                }
                return;
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else {
            System.out.println("ERROR: Expecting { got: " + (tokens.get(LexAnalyzer.parsecount).getToken()));
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //I' -> , J I2 | @
    public static void I2(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(",")) {
            LexAnalyzer.parsecount++;
            J(tokens);
            I2(tokens);
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
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //J -> F id J
    public static void J(ArrayList < Token > tokens) {
        F(tokens);
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) {
            LexAnalyzer.parsecount++;
            J(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //J' ->	[ ] |	@
    public static void J2(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("[")) {
            LexAnalyzer.parsecount++;
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) {
                System.out.println("ACCEPT");
                System.exit(1);
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
            if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
                ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(","))) {
                LexAnalyzer.parsecount++;
                return;
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        }
    }

    //K  ->	K'
    public static void K(ArrayList < Token > tokens) {
        K2(tokens);
        return;
    }

    //K2->	F id K3	|	@
    public static void K2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("int")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("void")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("float"))) {
            F(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) {
                LexAnalyzer.parsecount++;
                K3(tokens);
                return;
            } else {
                System.out.println("REJECT");
                System.exit(1);
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
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //	K3	->	; K2	|	[ number ] ; K2
    public static void K3(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
            LexAnalyzer.parsecount++;
            K2(tokens);
            return;
        } else {
            if ((tokens.get(LexAnalyzer.parsecount).getToken().equals("["))) {
                if ((tokens.get(LexAnalyzer.parsecount + 1).getTokenType().equals("number"))) {
                    LexAnalyzer.parsecount++;
                    if ((tokens.get(LexAnalyzer.parsecount + 1).getToken().equals("]"))) {
                        LexAnalyzer.parsecount++;
                        if ((tokens.get(LexAnalyzer.parsecount + 1).getToken().equals(";"))) {
                            LexAnalyzer.parsecount++;
                            K2(tokens);
                            return;
                        } else {
                            System.out.println("REJECT");
                            System.exit(1);
                        }
                    } else {
                        System.out.println("REJECT");
                        System.exit(1);
                    }
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        }
    }

    //L ->	L2
    public static void L(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if"))) {
            L2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //L2 ->	M L2	|	@
    public static void L2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if"))) {
            M(tokens);
            L2(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("}")) {
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //M 	->	N	|	H	|	O	|	P	|	Q
    public static void M(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";"))) {
            N(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("{")) {
            H(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) {
            Q(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) {
            P(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if")) {
            O(tokens);
            return;
        } else {
            System.exit(1);
        }
    }

    //N 	->	R ;	|	;
    public static void N(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))) {
            LexAnalyzer.parsecount++;
            R(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
                System.out.println("ACCEPT");
                System.exit(1);
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //O -> if ( R ) M O2
    public static void O(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("if")) {
            LexAnalyzer.parsecount++;
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                R(tokens);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    M(tokens);
                    O2(tokens);
                    return;
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }

            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //O2	->	else M	|	@
    public static void O2(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("else")) {
            LexAnalyzer.parsecount++;
            M(tokens);
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
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //P ->	WHILE ( R ) M
    public static void P(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("while")) {
            LexAnalyzer.parsecount++;
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                R(tokens);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    M(tokens);
                    return;
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }

            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //Q 	->	RETURN Q'
    public static void Q(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("return")) {
            LexAnalyzer.parsecount++;
            Q2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //Q'	->	;	|	R;
    public static void Q2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("id")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))) {
            LexAnalyzer.parsecount++;
            R(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
                System.out.println("ACCEPT");
                System.exit(1);
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //R 	-> 	id R3	|	U S2
    public static void R(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            R3(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("("))
            if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("number")) {
                U(tokens);
                S2(tokens);
                return;
            }
        else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //R2 -> = R	|	W' U' S'
    public static void R2(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("=")) {
            LexAnalyzer.parsecount++;
            R(tokens);
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
            W2(tokens);
            U2(tokens);
            S2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //R3	-> 	T2 R2	|	( ABAR ) W' U' S'
    public static void R3(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            ABAR(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                LexAnalyzer.parsecount++;
                W2(tokens);
                U2(tokens);
                S2(tokens);
                return;
            } else {
                System.out.println("REJECT");
                System.exit(1);
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
            T2(tokens);
            R2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //S2	-> 	V S3	|	@
    public static void S2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))
        ) {
            LexAnalyzer.parsecount++;
            V(tokens);
            S3(tokens);
            return;
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(";")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("]")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(","))
        ) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //S3	-> 	U	| 	id S4
    public static void S3(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            U(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            S4(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //S4-> 	( ABAR )W'U'	|	T' W' U'
    public static void S4(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            ABAR(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                LexAnalyzer.parsecount++;
                W2(tokens);
                U2(tokens);
                return;
            } else {
                System.out.println("REJECT");
                System.exit(1);
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
            T2(tokens);
            W2(tokens);
            U2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //T	-> 	id T2
    public static void T(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("EOF"))) {
            System.out.println("ACCEPT");
            System.exit(1);
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            LexAnalyzer.parsecount++;
            T2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //T2	-> 	[ R ]	|	@
    public static void T2(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            R(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                LexAnalyzer.parsecount++;
                return;
            } else {
                System.out.println("REJECT");
                System.exit(1);
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
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //V	-> 	<=	|	<	|	>	|	>=	|	==	|	!=
    public static void V(ArrayList < Token > tokens) {
        if (!((tokens.get(LexAnalyzer.parsecount).getToken()).equals("==")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">=")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(">")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("<")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("!="))) {
            System.out.println("REJECT");
            System.exit(1);
        }
        LexAnalyzer.parsecount++;
        return;
    }

    //U 	-> 	W U'
    public static void U(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            W(tokens);
            U2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //U'	-> 	CBAR U3	|	@
    public static void U2(ArrayList < Token > tokens) {
        if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-"))) {
            LexAnalyzer.parsecount++;
            CBAR(tokens);
            U3(tokens);
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
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //U3	-> 	T W U'	|	W U'
    public static void U3(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("ID")) {
            LexAnalyzer.parsecount++;
            T(tokens);
            W(tokens);
            U2(tokens);
            return;
        } else if (((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))) {
            LexAnalyzer.parsecount++;
            W(tokens);
            U2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //W 	-> 	Y W'
    public static void W(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            Y(tokens);
            W2(tokens);
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //W' 	-> 	X W''	|	@
    public static void W2(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/"))
        ) {
            LexAnalyzer.parsecount++;
            X(tokens);
            W3(tokens);
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
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //W''	-> 	Y W'	|	id ( ABAR ) W'
    public static void W3(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number"))
        ) {
            LexAnalyzer.parsecount++;
            Y(tokens);
            W2(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getTokenType()).equals("ID")) {
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
                LexAnalyzer.parsecount++;
                ABAR(tokens);
                if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                    LexAnalyzer.parsecount++;
                    W2(tokens);
                    return;
                } else {
                    System.out.println("REJECT");
                    System.exit(1);
                }
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //CBAR 	-> 	+ 	| 	-
    public static void CBAR(ArrayList < Token > tokens) {
        if (!((tokens.get(LexAnalyzer.parsecount).getToken()).equals("+")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("-"))) {
            System.out.println("REJECT");
            System.exit(1);
        }
        LexAnalyzer.parsecount++;
        return;
    }

    //X 	-> 	* 	| 	/
    public static void X(ArrayList < Token > tokens) {
        if (!((tokens.get(LexAnalyzer.parsecount).getToken()).equals("*")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("/"))) {
            System.out.println("REJECT");
            System.exit(1);
        }
        LexAnalyzer.parsecount++;
        return;
    }

    //Y 	-> 	( R )	|	num
    public static void Y(ArrayList < Token > tokens) {
        if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) {
            LexAnalyzer.parsecount++;
            R(tokens);
            if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
                System.out.println("ACCEPT");
                System.exit(1);
            } else {
                System.out.println("REJECT");
                System.exit(1);
            }
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //ABAR 	-> 	BBAR	|	@
    public static void ABAR(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("ID"))
        ) {
            LexAnalyzer.parsecount++;
            BBAR(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //BBAR	->	R BBAR'
    public static void BBAR(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("(")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("number")) ||
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals("ID"))
        ) {
            LexAnalyzer.parsecount++;
            R(tokens);
            BBAR(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }

    //BBAR2	-> 	, R BBAR'	|	@
    public static void BBAR2(ArrayList < Token > tokens) {
        if (
            ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(","))
        ) {
            LexAnalyzer.parsecount++;
            R(tokens);
            BBAR(tokens);
            return;
        } else if ((tokens.get(LexAnalyzer.parsecount).getToken()).equals(")")) {
            LexAnalyzer.parsecount++;
            return;
        } else {
            System.out.println("REJECT");
            System.exit(1);
        }
    }


static class Identifier{
       // The values on each line
       private String label;
       private int scope;
       private int hashLocation;

       // Identifier constructors
       public Identifier(){
       }

       public Identifier(String label, int scope, int hashLocation){
         this.label = label;
         this.scope = scope;
         this.hashLocation = hashLocation;
       }

       //Methods for the Identifier Class

       // Get Methods for Identifier Class
       public String getLabel(){
         return label;
       }

       public int getScope(){
         return scope;
       }

       public int getHashLocation(){
         return hashLocation;
       }

       // Set Methods for Identifier Class
       public void setLabel(String newLabel){
         this.label = newLabel;
       }

       public void setScope(int newScope){
         this.scope = newScope;
       }

       public void setHashLocation(int newHashLocation){
         this.hashLocation = newHashLocation;
       }

       public boolean equals(Object obj){
          return true;
       }

       public String toString(){
         return getHashLocation() + " " + getLabel() + " " + getScope();
       }
    }

static class Token {
    // The values on each line
    private String tokenType;
    private String token;

    // Token constructors
    public Token() {}

    public Token(String tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    //Methods for the Token Class

    // Get Methods for Token Class
    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }

    // Set Methods for Token Class
    public void setTokenType(String newTokenType) {
        this.tokenType = newTokenType;
    }

    public void setToken(String newToken) {
        this.token = newToken;
    }

    public boolean equals(Object obj) {
        return true;
    }

    public String toString() {
        return getTokenType() + ": " + getToken();
    }

}
}
