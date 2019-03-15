package com.client.commands;

import com.client.commands.enums.Errors;
import org.junit.Assert;
import org.junit.Test;

public class ShowCommandTest {

    private ShowCommand command = new ShowCommand();

    @Test
    public void testParse1() {

        // prepare
        String expectedResult = "docker0:  Hw_addr: 02:42:b4:c1:8e:4c\n" +
                                "          Ipv4: 172.17.0.1\n" +
                                "          Ipv6: null\n" +
                                "          MTU: 1500\n";

        String toParse = "{\"name\":\"docker0\"," +
                         "\"hwAddress\":\"02:42:b4:c1:8e:4c\"" +
                         ",\"inetAddress\":[\"172.17.0.1\",null]," +
                         "\"mtu\":\"1500\"}";

        // test
        Assert.assertEquals(expectedResult, command.parse(toParse));
    }

    @Test
    public void testParse2() {

        // prepare
        String expectedResult = "lo:  Hw_addr: null\n" +
                                "     Ipv4: 127.0.0.1\n" +
                                "     Ipv6: ::1/128\n" +
                                "     MTU: 65536\n";

        String toParse = "{\"name\":\"lo\"," +
                         "\"hwAddress\":null,\"" +
                         "inetAddress\":[\"127.0.0.1\",\"::1/128\"]," +
                         "\"mtu\":\"65536\"}";

        // test
        Assert.assertEquals(expectedResult, command.parse(toParse));
    }

    @Test
    public void testParse3() {

        // prepare
        String expectedResult = "eno1:  Hw_addr: f4:6d:04:af:21:33\n" +
                                "       Ipv4: null\n" +
                                "       Ipv6: null\n" +
                                "       MTU: 1500\n";

        String toParse = "{\"name\":\"eno1\"" +
                         ",\"hwAddress\":\"f4:6d:04:af:21:33\"" +
                         ",\"inetAddress\":[null,null]," +
                         "\"mtu\":\"1500\"}";

        // test
        Assert.assertEquals(expectedResult, command.parse(toParse));
    }

    @Test
    public void testParse4() {

        // prepare
        String expectedResult = "wlxe8cc18bc1213:  Hw_addr: e8:cc:18:bc:12:13\n" +
                                "                  Ipv4: 192.168.1.102\n" +
                                "                  Ipv6: fe80::e628:15fd:c103:b143/64\n" +
                                "                  MTU: 1500\n";

        String toParse = "{\"name\":\"wlxe8cc18bc1213\"," +
                         "\"hwAddress\":\"e8:cc:18:bc:12:13\"," +
                         "\"inetAddress\":[\"192.168.1.102\",\"fe80::e628:15fd:c103:b143/64\"]," +
                         "\"mtu\":\"1500\"}";

        // test
        Assert.assertEquals(expectedResult, command.parse(toParse));
    }

    @Test
    public void testParseWithException() {

        // prepare
        String expectedResult = Errors.SERVER_ERROR.getMessage();

        // test
        Assert.assertEquals(expectedResult, command.parse(""));
    }
}
