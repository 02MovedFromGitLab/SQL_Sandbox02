package org.example.testTools;

import java.io.InputStream;

/**
 * InputStream tester
 *
 * @version 2.0.2 , 11-may-2020
 */
public class InputStreamTester extends InputStream {
   byte[] bytes = null; // buffer
   int index = 0; // index, current(reading) position in buffer
   //String thisLine = null; // test/debug
   String nextLine = null; // next/split of input

   /**
    * Setter for the current input/data to process
    *
    * @param text the text to use as input
    */
   public void setInput(String text) {
      if (text != null) {
         //# split input after newline \n
         int index = text.indexOf("\n");
         if (index >= 0) {
            nextLine = text.substring(index + 1);
            text = text.substring(0, index + 1);
         } else {
            nextLine = null;
         }
         //# add newline if none exists
         if (!text.contains("\n")) text += "\n";
         //# set buffer
         //thisLine = text; // test/debug
         bytes = text.getBytes();
      } else {
         nextLine = null;
         bytes = null;
      }
      //# set index
      index = 0;
   }

   /**
    * Setter for the current input/data to process
    *
    * @param text the text to use as the next input
    */
   public void addInputLine(String text) {
      if (text != null) {
         if (bytes == null) {
            setInput(text);
         } else {
            //# add newline if none exists on the nextLine if a nextLine exists
            if (nextLine != null && !nextLine.endsWith("\n"))
               nextLine += "\n";
            //# add text to nextLine
            if (nextLine == null) nextLine = text;
            else nextLine = nextLine + text;
         }
      }
   }

   /**
    * Reset input
    */
   public void reset() {
      setInput(null);
   }

   /**
    * Has every byte been read?
    *
    * @return if everything has been read this returns true, otherwise false
    */
   public boolean isEnded() {
      if (bytes == null) return true;
      return index >= bytes.length && nextLine == null;
   }

   /**
    * Read next byte
    * @return next byte in stream or -1(when the stream ended)
    */
   @Override
   public int read() {
      if (bytes != null && index < bytes.length && index >= 0) // are we in the buffer?
         return bytes[index++]; // return buffer at index and continue reading(++)
      setInput(nextLine); // prepare next line
      return -1; // return end of buffer
   }
}
