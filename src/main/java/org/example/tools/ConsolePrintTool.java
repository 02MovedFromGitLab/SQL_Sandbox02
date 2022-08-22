package org.example.tools;

/**
 * ConsolePrintTool
 * For special console printing needs
 *
 * @version 2.0.2 , 18-march-2021
 * */
public final class ConsolePrintTool {
   private ConsolePrintTool(){}

   public static void printTitle(String title, char type){
      if( title == null || title.length() == 0 ) return;

      StringBuilder sb = new StringBuilder();
      for(int i = 0; i< title.length(); i++){
         sb.append(type);
      }
      System.out.println(title);
      System.out.println(sb.toString());
   }

   public static void printTitle(String title){
      printTitle(title, '-');
   }
}
