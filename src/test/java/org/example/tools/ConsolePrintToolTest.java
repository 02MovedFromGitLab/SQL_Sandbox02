package org.example.tools;

import org.example.testTools.SystemInOutTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


/**
 * ConsoleInputToolTest
 *
 * @version 2.0.1 , 10-may-2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsolePrintToolTest extends SystemInOutTester {

   @Test
   void printTitle() {
      //# using the default underlining character
      ConsolePrintTool.printTitle("abc");
      assertEquals("abc\n---\n", getOutput());
      assertTrue(getError().isBlank());

      resetStreams();

      //# using minus as the underlining character
      ConsolePrintTool.printTitle("abc", '=');
      assertEquals("abc\n===\n", getOutput());
      assertTrue(getError().isBlank());
   }
}