package com.client.commands;

import com.client.commands.enums.Errors;
import org.junit.Assert;
import org.junit.Test;

public class ListCommandTest {

    private ListCommand command = new ListCommand();

    @Test
    public void testParse() {

        // prepare
        String expectedResult = "docker0 eno1 lo wlxe8cc18bc1213";

        // test
        Assert.assertEquals(expectedResult, command.parse(
                "{\"interfaces\":[\"docker0\",\"eno1\",\"lo\",\"wlxe8cc18bc1213\"]}"));
    }

    @Test
    public void testParseWithException() {

        // prepare
        String expectedResult = Errors.SERVER_ERROR.getMessage();

        // test
        Assert.assertEquals(expectedResult, command.parse(""));
    }
}
