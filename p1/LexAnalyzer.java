// Mike Vasquez
// N00885181

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;



public class LexAnalyzer
{      

      // current index of the location in the array
   static int currentIndex = 0;
      
	  // a boolean variable that determines that something is a number instead of a id
   static boolean isNum = false;

    /**
     * reads file
     * if not found gives a FileNotFoundException
    **/
	
   public static void main(String[] args) throws FileNotFoundException{
       
        // Read file from command line
      File file = new File(args[0]);
   
   	  /*
   	   The following array lists contain the relational 
   	   operators, keywords, deliminators, and expected errors
   	  */
   	
   	
        // Relational Operators
      ArrayList<String> relational = new ArrayList<>();
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
      ArrayList<String> keywords = new ArrayList<>();
      keywords.add("float");
      keywords.add("int");
      keywords.add("else");
      keywords.add("if");
      keywords.add("return");
      keywords.add("void");
      keywords.add("while");
   	
   	  // Deliminators
      ArrayList<String> delims = new ArrayList<>();
      delims.add(";");
      delims.add(",");
      delims.add("(");
      delims.add(")");
      delims.add("[");
      delims.add("]");
      delims.add("{");
      delims.add("}");
      delims.add(".");
   
        // Known Errors
      ArrayList<String> errors = new ArrayList<>();
      errors.add("!");
      errors.add("@");
      errors.add("_");
   
        // ArrayList for Generated Identifiers
      ArrayList<String> symbolTable = new ArrayList<>();
   
        // Calls the lexical analyzer
      lexical(keywords, relational, delims, errors, symbolTable, file);
   
   }   // end main

     /*******************************************************
     * Method checks is the String passed to it is a number *
     * @param stringNumber                                  *
     * @return                                              *
     *******************************************************/
    
   public static boolean isInteger(String stringNumber)
      {
         try
            {
               Integer.parseInt(stringNumber);
               return true;
            }
         
         catch(NumberFormatException e)
            {
               return false;
            }
      }


     /**********************************************************************************
     * Reads in possible numbers and checks to see if they are valid                   *
     * if they are it prints them to the console as either a num or a float            *
     * otherwise they are marked as an error                                           *
     **********************************************************************************/
   
   public static void checkNum(String num, ArrayList<String> errors)
      {
         char [] numArray = num.toCharArray();
         String numToPrint = "";
         String errorNum = "";
         String error = "";
         
         for(int r = 0; r < numArray.length; r++)
            {
               if((numArray[0] == '.')||(numArray[0] == 'E'))
                  {
                     errorNum = num;
                     break;
                  }
               
               if(isInteger(String.valueOf(numArray[r])))
                  {
                     numToPrint = numToPrint + numArray[r];
                     
                     if(r+1 < numArray.length)
                        {
                           if(numArray[r+1] == '.')
                              {
                                 numToPrint = numToPrint + numArray[r+1];
                                 r = r + 1;
                                 
                                 if(r+1 < numArray.length)
                                    {
                                       if(isInteger(String.valueOf(numArray[r+1])))
                                          {
                                             numToPrint = numToPrint + numArray[r+1];
                                             r = r + 1;
                                          }
                                       
                                       else
                                          {
                                             errorNum = num;
                                             break;
                                          }
                                    }
                              }
                           
                           else if(numArray[r+1] == 'E')
                              {
                                 numToPrint = numToPrint + numArray[r+1];
                                 r = r + 1;
                                 
                                 if(r+1 < numArray.length)
                                    {
                                       if((numArray[r+1] == '+') || (numArray[r+1] == '-'))
                                          {
                                             numToPrint = numToPrint + numArray[r+1];
                                             r = r + 1;
                                          }
                                                  
                                       else if(isInteger(String.valueOf(numArray[r+1])))
                                          {
                                             numToPrint = numToPrint + numArray[r+1];
                                             r = r + 1;
                                          }
                                       
                                       else
                                          {
                                             errorNum = num;
                                             break;
                                          }
                                    }
                                    
                                 else
                                    {
                                       errorNum = num;
                                       break;
                                    }
                              }
                                          
                           else if(isInteger(String.valueOf(numArray[r+1])))
                              {
                                 numToPrint = numToPrint + numArray[r+1];
                                 r = r + 1;
                              }
                        }
                  }
               
               else if(numArray[r] == '.')
                  {
                     numToPrint = numToPrint + numArray[r];
                     
                     if(r+1 < numArray.length)
                        {
                           if(isInteger(String.valueOf(numArray[r+1])))
                              {
                                 numToPrint = numToPrint + numArray[r+1];
                                 r = r + 1;
                              }
                           
                           else
                              {
                                 errorNum = num;
                                 break;
                              }
                        }
                     
                     else
                        {
                           errorNum = num;
                           break;
                        }
                  }
                     
               else if(numArray[r] == 'E')
                  {
                     numToPrint = numToPrint + numArray[r];
                     
                     if(r+1 < numArray.length)
                        {
                           if((numArray[r+1] == '+') || (numArray[r+1] == '-'))
                              {
                                 numToPrint = numToPrint + numArray[r+1];
                                 r = r + 1;
                              }
                           
                           else if(isInteger(String.valueOf(numArray[r+1])))
                              {
                                 numToPrint = numToPrint + numArray[r+1];
                                 r = r + 1;
                              }
                        }
                        
                     else
                        {
                           errorNum = num;
                           break;
                        }
                  }
                        
               if(errors.contains(String.valueOf(numArray[r])))
                  {
                     for(int c = r; c < numArray.length; c++)
                        {
                           error = error + numArray[c];
                        }
                     
                     System.out.println("error: " + error);
                     break;
                  }
            }
            
         if(errorNum.isEmpty() == true)
            {
               if((num.indexOf('.')) >= 0 || (num.indexOf('E')) >= 0)
                  {
                     System.out.println("FLOAT: " + numToPrint);
                     numToPrint = "";
                  }  
               
               else
                  {
                     System.out.println("NUM: " + numToPrint);
                     numToPrint = "";
                  }
            }
            
         else
            {
               System.out.println("ERROR: " + errorNum);
            }
      }

     /**********************************************************************************************************
     * Generates tokens based on the input file and also prints out its result to the console		            *
     * keywords : the ArrayList keywords to check if the string contains a keyword							         *
     * relational : the ArrayList relational to check if the string contains a relational operator			      *
     * delims : the ArrayList delims to check if the string contains an delimitor							         *
     * errors : the ArrayList errors to check if the string contains an error								            *
     * symbolTable : the ArrayList symbolTable that stores the identifiers, this isn't fully implemented yet	*
     * file : the file that is read in from the command line													            *
     **********************************************************************************************************/
    
   public static void lexical(ArrayList<String> keywords, ArrayList<String> relational, ArrayList<String> delims,
                               ArrayList<String> errors, ArrayList<String> symbolTable, File file)
      {
         try
            {
               Scanner input = new Scanner(file);
               int commentCounter = 0;
               
               while(input.hasNextLine())
                  {
                     String text = input.nextLine();
                     String compare = "";
                     System.out.println("");
                     
                     if(text.isEmpty() == false)
                        {
                           System.out.println("INPUT: " + text);
                        }
                     
                     char [] newArray = text.toCharArray();
          // Checks for comments
                     for(LexAnalyzer.currentIndex = 0; LexAnalyzer.currentIndex < newArray.length; LexAnalyzer.currentIndex++)
                        {
                           if ((LexAnalyzer.currentIndex + 1)< newArray.length)
                              {
                                 if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("/*"))
                                    {
                                       commentCounter++;
                                       LexAnalyzer.currentIndex++;
                                    }
                                 
                                 else if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("*/"))
                                    {
                                       if(commentCounter > 0)
                                          {
                                             commentCounter--;
                                          }
                                       
                                       else if(commentCounter == 0)
                                          {
                                             compare = compare + newArray[LexAnalyzer.currentIndex];
                                          }
                                       
                                       LexAnalyzer.currentIndex++;
                                    }
                                 
                                 else if (String.valueOf(newArray, LexAnalyzer.currentIndex, 2).equals("//"))
                                    {
                                       break;
                                    }
                              }
                              
                     // If there are no commments, continue reading the line
                           if(commentCounter == 0)
                              {
                                 if(!String.valueOf(newArray[LexAnalyzer.currentIndex]).equals("\t")) 
                                    {
                                       compare = compare + newArray[LexAnalyzer.currentIndex];
                                    }
                                          
                     // If keyword then
                                 if(keywords.contains(compare))
                                    {
                                       System.out.println("KEYWORD: " + compare);
                                       compare = "";
                                    }
                        
                     // If there a relational operator that matches the string
                                 else if(relational.contains(compare))
                                    {
                                       if((LexAnalyzer.currentIndex+1) < newArray.length)
                                          {
                                             if(relational.contains(String.valueOf(newArray, LexAnalyzer.currentIndex, 2)))
                                                {
                                                   compare = compare + newArray[LexAnalyzer.currentIndex+1];
                                                   System.out.println("RELATIONAL: " + compare);
                                                   compare = "";
                                                }
                                             
                                             else
                                                {
                                                   if(!(relational.contains(String.valueOf(newArray[LexAnalyzer.currentIndex-1]))))
                                                      {
                                                         System.out.println("RELATIONAL: " + compare);
                                                         compare = "";
                                                      }
                                                }
                                             
                                             compare = "";
                                          }
                                    }
                     
                     // if there is an error in the string
                                 else if(errors.contains(compare))
                                    {
                                       String BigNoNo = "";
                                       
                                       for(int z = LexAnalyzer.currentIndex; z < newArray.length; z++)
                                          {
                                             if(!(String.valueOf(newArray[z])).equals(" "))
                                                {
                                                   BigNoNo = BigNoNo + newArray[z];
                                                }
                                             
                                             else
                                                {
                                                   LexAnalyzer.currentIndex = z;
                                                   break;
                                                }
                                          }
                                       
                                       System.out.println("ERROR: " + BigNoNo);
                                       BigNoNo= "";
                                       compare = "";
                                    }
                     
                     // if there is an integer that was read
                                 else if((isInteger(compare) == true) || (compare.equals(".")) || (compare.equals("E")))
                                    {
                                       String newNum = "";
                                       LexAnalyzer.isNum = true;
                                       
                                       for(int x = LexAnalyzer.currentIndex; x < newArray.length; x++)
                                          {
                                             if((newArray[x] != ' ') && (!(delims.contains(String.valueOf(newArray[x])))))
                                                {
                                                   newNum = newNum + newArray[x];
                                                }
                                             
                                             else
                                                {
                                                   LexAnalyzer.currentIndex = x;
                                                   checkNum(newNum, errors);
                                                   compare = "";
                                                   LexAnalyzer.isNum = false;
                                                   break;
                                                }
                                                   
                                             if(x == (newArray.length - 1))
                                                {
                                                   checkNum(newNum, errors);
                                                }
                                                                  
                                             LexAnalyzer.currentIndex = x;
                                          }
                                    }
                     
                     // if the string is a delimitor
                                 else if(delims.contains(compare))
                                    {
                                       System.out.println("DELIMS: " + compare);
                                       compare = "";
                                    }
                        
                     // if the string compare that is read is none of the above
                     // then it is most likely an identifier
                                 else if((String.valueOf(newArray[LexAnalyzer.currentIndex]).equals(" ")) || (delims.contains(String.valueOf(newArray[LexAnalyzer.currentIndex]))) ||
                                        (relational.contains(String.valueOf(newArray[LexAnalyzer.currentIndex]))))
                                    {
                                       String newID = "";
                                       String testString = "";
                                       String beforeTest = "";
                                                         
                                       if((!(keywords.contains(compare)))&&(!(relational.contains(compare)))&&(!(delims.contains(compare)))
                                                           &&(!(errors.contains(compare)))&&(!(compare).equals(" "))&&(isNum == false))
                                          {
                                             for(int k = 0; k < compare.length(); k++) 
                                                {
                                                   char c = compare.charAt(k);
                                                   beforeTest = testString;
                                                   testString = testString + c;
                                                                     
                                                   if(delims.contains(String.valueOf(c)))
                                                      {
                                                         if(newID.isEmpty() == false)
                                                            {
                                                               symbolTable.add(newID);
                                                               System.out.println("ID: " + newID);
                                                            }
                                                   
                                                         System.out.println("DELIMS: " + String.valueOf(c));
                                                         newID = beforeTest;
                                                      }
                                                                     
                                                   
                                                   else if(errors.contains(String.valueOf(c)))
                                                      {
                                                         if(newID.isEmpty() == false)
                                                            {
                                                               symbolTable.add(newID);
                                                               System.out.println("ID: " + newID);
                                                            }
                                                         
                                                            
                                                         String BigNoNo = compare.substring(k, compare.length());
                                                         System.out.println("ERROR: " + BigNoNo);
                                                         compare = "";
                                                         break;
                                                            
                                                      }
                                                                        
                                                   else if(relational.contains(String.valueOf(c)))
                                                      {
                                                         if(newID.isEmpty() == false)
                                                            {
                                                               symbolTable.add(newID);
                                                               System.out.println("ID: " + newID);
                                                            }
                                                         
                                                         System.out.println("RELATIONAL: " + String.valueOf(c));
                                                      }     
                                                                           
                                                   else  
                                                      {     
                                                         newID = newID + c;
                                                      }
                                                      
                                                }
                                                   
                                          }
                                       compare = "";
                                    }
                                                                        
                     // if the string only contains 1 char
                                 else if(LexAnalyzer.currentIndex == (newArray.length - 1))
                                    {
                                       for(int p = 0; p < compare.length(); p++)
                                          {
                                             char c = compare.charAt(p);
                                             
                                             if(delims.contains(String.valueOf(c)))
                                                {
                                                   System.out.println("DELIMS: " + String.valueOf(c));
                                                }
                                             
                                             else if(errors.contains(String.valueOf(c)))
                                                {     
                                                   String BigNoNo = "";
                                                                           
                                                   for(int j = p+1; j < newArray.length; j++)
                                                      {
                                                         if(!(String.valueOf(newArray[j])).equals(" "))
                                                            {
                                                               BigNoNo = BigNoNo + newArray[j];
                                                            }
                                                                                 
                                                         else
                                                            {
                                                               LexAnalyzer.currentIndex = j;
                                                               break;
                                                            }
                                                      }
                                                                              
                                                   System.out.println("ERROR: " + BigNoNo);
                                                   compare = "";
                                                   break;
                                                }
                                                 
                                             else if(relational.contains(String.valueOf(c)))
                                                {
                                                   System.out.println("RELATIONAL: " + String.valueOf(c));
                                                }
                                          }
                                    }
                              }
                        }
                  }
               
               input.close();
            }
         
         catch (FileNotFoundException e)
            {
               e.printStackTrace();
            }
      }
}