package org.example.testTools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * SystemInOutTester
 * A class to help build test-cases that need to test algorithms using System streams (in, out, err)
 *
 * @version 2.0.3 , 03-jul-2020
 */
public class SystemInOutTester {
    protected static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected static final InputStreamTester inContent = new InputStreamTester();
    protected static final PrintStream originalOut = System.out;
    protected static final PrintStream originalErr = System.err;
    protected static final InputStream originalIn = System.in;

    public SystemInOutTester(){
        setTestingStreams();
    }

    /**
     * Set system-streams so they can be used for testing
     */
    public static void setTestingStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(inContent);
    }

    /**
     * Set Input of the user
     *
     * @param data The data to use as input.
     */
    public void setInput(String data) {
        inContent.setInput(data);
    }

    /**
     * Add Input of the user
     *
     * @param data The data to use as input.
     */
    public void addInputLine(String data) {
        inContent.addInputLine(data);
    }

    /**
     * Get Output of the algorithm
     *
     * @return returns the output stream content
     */
    public String getOutput() {
        return outContent.toString().replace("\r\n", "\n");
    }

    /**
     * Get Error of the algorithm
     *
     * @return returns the error stream content
     */
    public String getError() {
        return errContent.toString().replace("\r\n", "\n");
    }

    /**
     * Restore system-streams so they can be used as normal
     */
    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    /**
     * Reset testing Streams so no test-output remains
     */
    @AfterEach
    public void resetStreams() {
        inContent.reset();
        /* //# Reset remaining scanner input?
        //Scanner keyb = new Scanner(System.in);
        while(true){
            try{
                //keyb.nextLine();
                //ConsoleTool.askPressEnterToContinue();
            }catch (Exception e            ){
                break;
            }
        }// */
        outContent.reset();
        errContent.reset();
    }

    /**
     * Print status and content of Streams on the original OutputStream (of System.in)
     */
    public void printStatus() {
        originalOut.println("Input Finished: " + inContent.isEnded());
        originalOut.println("Output: " + outContent.toString());
        originalErr.println("Errors: " + errContent.toString());
    }
}
