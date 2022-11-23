package com.rsakin.sportradar.casestudy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

class FCScoreBoardTest {

    // To catch the stdout and stderr messages and assert with expected ones
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream testIn;

    private final InputStream systemIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(systemIn);
    }

    // provides to put input for stdin
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void shouldScoreBoardStartStreaming() {
        ScoreBoard scoreBoard = ScoreBoard.getScoreBoard();
        scoreBoard.startStreaming();
        assertEquals("Live Score Board Up and Running\r\n", outContent.toString());
    }

    // We can start new match and add to live score board
    @Test
    void shouldAcceptStartCommandToAddNewMatchToBoard() {
        ScoreBoard scoreBoard = ScoreBoard.getScoreBoard();
        scoreBoard.startStreaming();
        provideInput("start Mexico Uruguay");
        assertEquals("Live Score Board Up and Running\r\nMatch started [ Mexico 0 - Uruguay 0 ]\"", outContent.toString());
    }

}