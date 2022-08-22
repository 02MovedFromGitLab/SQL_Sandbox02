package org.example.tools;

import org.example.testTools.SystemInOutTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ConsoleInputToolTest
 *
 * @version 2.1.0 , 10-may-2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsoleInputToolTest extends SystemInOutTester {
   static final String DEFAULT_QUESTION = "q:";
   ConsoleInputTool input = new ConsoleInputTool();
   //Todo: test AskUserDateTime()
   //Todo: test AskUserDateTimeBefore()
   //Todo: test AskUserDateTimeAfter()
   //Todo: test AskUserFloat()

   @Test
   void testAskPressEnterToContinue() {
      setInput("");
      input.askPressEnterToContinue();
      assertEquals("Press enter to continue.", getOutput());
      assertTrue(getError().isBlank());
   }

   @Test
   void testAskUserYesNoQuestion() {
      //# test true/yes
      setInput("y");
      assertTrue(input.askUserYesNoQuestion(DEFAULT_QUESTION));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());

      resetStreams();
      //# test false/no
      setInput("n");
      assertFalse(input.askUserYesNoQuestion(DEFAULT_QUESTION));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());

      resetStreams();
      //# test blankDefault true/yes
      setInput("");
      assertTrue(input.askUserYesNoQuestion(DEFAULT_QUESTION, true));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());

      resetStreams();
      //# test blankDefault false/no
      setInput("");
      assertFalse(input.askUserYesNoQuestion(DEFAULT_QUESTION, false));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());
   }

   @Test
   void testAskUserString() {
      setInput("abc");
      assertEquals("abc", input.askUserString(DEFAULT_QUESTION));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());
   }

   @Test
   void testAskUserInteger() {
      setInput("123");
      assertEquals(123, input.askUserInteger(DEFAULT_QUESTION));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());
   }

   @Test
   void testAskUserDate() {
      setInput("2000-01-01");
      assertEquals(LocalDate.of(2000, 1, 1),
              input.askUserDate(DEFAULT_QUESTION));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());

      resetStreams();
      //# test blankDefault
      setInput("");
      assertEquals(LocalDate.of(2020, 2, 2),
              input.askUserDate(DEFAULT_QUESTION, LocalDate.of(2020, 2, 2)));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());
   }

   @Test
   void testAskUserDateBefore() {
      setInput("2000-01-01");
      assertEquals(LocalDate.of(2000, 1, 1),
              input.askUserDateBefore(DEFAULT_QUESTION, LocalDate.of(2020, 1, 1)));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());
   }

   @Test
   void testAskUserDateAfter() {
      setInput("2100-01-01");
      assertEquals(LocalDate.of(2100, 1, 1),
              input.askUserDateAfter(DEFAULT_QUESTION, LocalDate.of(2020, 1, 1)));
      assertEquals(DEFAULT_QUESTION, getOutput());
      assertTrue(getError().isBlank());
   }

}