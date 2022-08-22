package org.example.tools;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ConsoleInputTool
 * A Utility class to get input from the user using the console
 *
 * @version 2.3.2 , 31-march-2021
 */
public final class ConsoleInputTool {
   Scanner keyboard;
   //Todo: AskUserDateTimeBetween()
   //Todo: AskUserDateBetween()

   public ConsoleInputTool() {
      keyboard = new Scanner(System.in);
   }

   public ConsoleInputTool(Scanner scanner) {
      if (scanner == null) throw new IllegalArgumentException("ConsoleInputTool Error: Scanner can not be null");
      this.keyboard = scanner;
   }

   /**
    * Requests the user to press the return key to continue.
    */
   public void askPressEnterToContinue() {
      System.out.print("Press enter to continue.");
      keyboard.nextLine();
   }

   /**
    * Ask the user for a boolean(may repeat until input is correct).
    * when the use leaves the input blank the default value will be used if allowed.
    *
    * @param question          The question to ask(print to) the user.
    * @param allowBlankDefault whether the user is allowed to input a blank line, use default value.
    * @param defaultValue      The default value to use if the user inputs a blank line.
    * @return the user input: string.
    */
   public boolean askUserYesNoQuestion(String question, boolean allowBlankDefault, boolean defaultValue) {
      do {
         if (question != null)
            System.out.print(question);
         String answer = keyboard.nextLine();
         answer = answer.toLowerCase();
         if (answer.equals("y") || answer.equals("yes") || answer.equals("j") || answer.equals("ja")) return true;
         else if (answer.equals("n") || answer.equals("no") || answer.equals("nee")) return false;
         else if (allowBlankDefault && answer.isBlank()) return defaultValue;
         System.err.println("Error: input must be y or n.");
      } while (true);
   }

   /**
    * Ask the user for a boolean(repeat until input is correct).
    *
    * @param question The question to ask(print to) the user.
    * @return the user input: string.
    */
   public boolean askUserYesNoQuestion(String question) {
      return askUserYesNoQuestion(question, false, false);
   }

   /**
    * Ask the user for a boolean(repeat until input is correct).
    * when the use leaves the input blank the default value will be used.
    *
    * @param question     The question to ask(print to) the user.
    * @param defaultValue The default value to use if the user inputs a blank line.
    * @return the user input: string.
    */
   public boolean askUserYesNoQuestion(String question, boolean defaultValue) {
      return askUserYesNoQuestion(question, true, defaultValue);
   }

   /**
    * Ask the user for a String(repeat until input is correct).
    *
    * @param question          the question to ask(print to) the user.
    * @param minimumCharacters the minimum length of String to return.
    * @return the user input: string.
    */
   public String askUserString(String question, int minimumCharacters) {
      if (minimumCharacters <= 0) {
         System.out.print(question);
         return keyboard.nextLine();
      } else {
         String input;
         do {
            if (question != null)
               System.out.print(question);
            input = keyboard.nextLine();
            if (input.length() < minimumCharacters)
               System.err.format("Error: Input must be at least %d character%s.\n", minimumCharacters, minimumCharacters > 1 ? "s" : "");
         } while (input.length() < minimumCharacters);
         return input;
      }
   }

   /**
    * Ask the user for a String(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: string.
    */
   public String askUserString(String question) {
      return askUserString(question, 0);
   }

   /**
    * Ask the user for a integer(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: integer.
    */
   public int askUserInteger(String question) {
      int input;
      while (true) {
         try {
            if (question != null)
               System.out.print(question);
            input = keyboard.nextInt();
            break;
         } catch (InputMismatchException ime) {
            System.err.println("Error: input is not a number");
         } finally {
            keyboard.nextLine();
         }
      }
      return input;
   }

   /**
    * Ask the user for a integer(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @param minimum  the minimum the integer is allowed to be.
    * @return the user input: integer.
    */
   public int askUserInteger(String question, int minimum) {
      int input;// = minimum - 1;
      do {
         input = askUserInteger(question);
         if (input < minimum) {
            System.err.println("Error: input must be equal or higher than " + minimum);
         }
      } while (input < minimum);
      return input;
   }

   /**
    * Ask the user for a integer(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @param minimum  the minimum the integer is allowed to be.
    * @param maximum  the maximum the integer is allowed to be.
    * @return the user input: integer.
    */
   public int askUserInteger(String question, int minimum, int maximum) {
      int input;// = 0;
      do {
         input = askUserInteger(question);
         if (input < minimum) {
            System.err.println("Error: input must be equal or higher than " + minimum);
         } else if (input > maximum) {
            System.err.println("Error: input must be equal or lower than " + maximum);
         }
      } while (input < minimum || input > maximum);
      return input;
   }

   /**
    * Ask the user for a floating point number(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: float.
    */
   public float askUserFloat(String question) {
      float input;
      while (true) {
         try {
            System.out.print(question);
            input = keyboard.nextFloat();
            break;
         } catch (InputMismatchException ime) {
            System.err.println("Error: input is not a number");
         } finally {
            keyboard.nextLine();
         }
      }
      return input;
   }

   /**
    * Ask the user for a floating point number(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @param minimum  the minimum the float is allowed to be.
    * @return the user input: integer.
    */
   public float askUserFloat(String question, float minimum) {
      float input;// = minimum - 1;
      do {
         input = askUserFloat(question);
         if (input < minimum) {
            System.err.println("Error: input must be equal or higher than " + minimum);
         }
      } while (input < minimum);
      return input;
   }

   /**
    * Ask the user for a LocalDate(repeat until input is correct).
    * when the use leaves the input blank the default value will be used when allowed.
    *
    * @param question          the question to ask(print to) the user.
    * @param allowBlankDefault whether the user is allowed to input a blank line, use default value.
    * @param defaultValue      The default value to use if the user inputs a blank line.
    * @return the user input: LocalDate.
    */
   public LocalDate askUserDate(String question, boolean allowBlankDefault, LocalDate defaultValue) {
      LocalDate localDate = null;
      do {
         if (question != null)
            System.out.print(question);
         String userInput = keyboard.nextLine();
         if (allowBlankDefault && userInput.isBlank()) return defaultValue;
         try {
            localDate = LocalDate.parse(userInput);
         } catch (DateTimeParseException dtpe) {
            System.out.println("Error: " + dtpe.getMessage());
            System.out.println("Expected format: YYYY-MM-DD for example 2000-01-01");
         }
      } while (localDate == null);
      return localDate;
   }

   /**
    * Ask the user for a LocalDate(repeat until input is correct).
    * when the use leaves the input blank the default value will be used.
    *
    * @param question     the question to ask(print to) the user.
    * @param defaultValue The default value to use if the user inputs a blank line.
    * @return the user input: LocalDate.
    */
   public LocalDate askUserDate(String question, LocalDate defaultValue) {
      return askUserDate(question, true, defaultValue);
   }

   /**
    * Ask the user for a LocalDate(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: LocalDate.
    */
   public LocalDate askUserDate(String question) {
      return askUserDate(question, false, null);
   }

   /**
    * Ask the user for a LocalDate(repeat until input is correct).
    *
    * @param question    the question to ask(print to) the user.
    * @param maximumDate the maximum date that is allowed
    * @return the user input: LocalDate.
    */
   public LocalDate askUserDateBefore(String question, LocalDate maximumDate) {
      LocalDate ld = askUserDate(question);
      while (ld.isAfter(maximumDate) || ld.isEqual(maximumDate)) {
         System.out.println("Error: Date must be before " + maximumDate);
         ld = askUserDate(question);
      }
      return ld;
   }

   /**
    * Ask the user for a LocalDate(repeat until input is correct).
    *
    * @param question    the question to ask(print to) the user.
    * @param minimumDate the minimum date that is allowed
    * @return the user input: LocalDate.
    */
   public LocalDate askUserDateAfter(String question, LocalDate minimumDate) {
      LocalDate ld = askUserDate(question);
      while (ld.isBefore(minimumDate) || ld.isEqual(minimumDate)) {
         System.out.println("Error: Date must be before " + minimumDate);
         ld = askUserDate(question);
      }
      return ld;
   }

   /**
    * Ask the user for a LocalDateTime(repeat until input is correct).
    * when the use leaves the input blank the default value will be used when allowed.
    *
    * @param question          the question to ask(print to) the user.
    * @param allowBlankDefault whether the user is allowed to input a blank line, use default value.
    * @param defaultValue      The default value to use if the user inputs a blank line.
    * @return the user input: LocalDateTime.
    */
   public LocalDateTime askUserDateTime(String question, String pattern, boolean allowBlankDefault, LocalDateTime defaultValue) {
      if (pattern == null || pattern.isBlank()) pattern = StringTool.dtfPattern_MetricMediumDashed;
      System.out.println("pattern: " + pattern);
      LocalDateTime localDateTime = null;
      do {
         if (question != null)
            System.out.print(question);
         String userInput = keyboard.nextLine();
         if (allowBlankDefault && userInput.isBlank()) return defaultValue;
         try {
            localDateTime = LocalDateTime.parse(userInput, DateTimeFormatter.ofPattern(pattern));
         } catch (DateTimeParseException dtpe) {
            System.out.println("Error: " + dtpe.getMessage());
            System.out.printf("Expected format: %s for example %s", pattern, StringTool.dateTimeToString(LocalDateTime.now(), pattern));
         }
      } while (localDateTime == null);
      return localDateTime;
   }

   /**
    * Ask the user for a LocalDateTime(repeat until input is correct).
    * when the use leaves the input blank the default value will be used.
    *
    * @param question     the question to ask(print to) the user.
    * @param defaultValue The default value to use if the user inputs a blank line.
    * @return the user input: LocalDateTime.
    */
   public LocalDateTime askUserDateTime(String question, LocalDateTime defaultValue) {
      return askUserDateTime(question, null, true, defaultValue);
   }

   /**
    * Ask the user for a LocalDateTime(repeat until input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: LocalDateTime.
    */
   public LocalDateTime askUserDateTime(String question) {
      return askUserDateTime(question, null, false, null);
   }

   /**
    * Ask the user for a LocalDateTime(repeat until input is correct).
    *
    * @param question        the question to ask(print to) the user.
    * @param maximumDateTime the maximum dateTime that is allowed
    * @return the user input: LocalDateTime.
    */
   public LocalDateTime askUserDateTimeBefore(String question, LocalDateTime maximumDateTime) {
      LocalDateTime ld = askUserDateTime(question);
      while (ld.isAfter(maximumDateTime) || ld.isEqual(maximumDateTime)) {
         System.out.println("Error: Date & time must be before " + maximumDateTime);
         ld = askUserDateTime(question);
      }
      return ld;
   }

   /**
    * Ask the user for a LocalDateTime(repeat until input is correct).
    *
    * @param question        the question to ask(print to) the user.
    * @param minimumDateTime the minimum dateTime that is allowed
    * @return the user input: LocalDateTime.
    */
   public LocalDateTime askUserDateAfter(String question, LocalDateTime minimumDateTime) {
      LocalDateTime ld = askUserDateTime(question);
      while (ld.isBefore(minimumDateTime) || ld.isEqual(minimumDateTime)) {
         System.out.println("Error: Date must be before " + minimumDateTime);
         ld = askUserDateTime(question);
      }
      return ld;
   }
}
