package com.service.parser;

import com.service.model.NetworkInterface;
import com.service.parser.helpers.ConsoleReader;
import com.service.parser.helpers.enums.Commands;
import org.apache.el.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkInterfaceParserTest {

    @Mock
    ConsoleReader reader;

    @InjectMocks
    NetworkInterfaceParser parser;

    @Test
    public void testParseNames() throws ParseException {
        // prepare
        Optional<List<String>> result = Optional.of(new ArrayList<>(Arrays.asList("Iface MTU Met RX-OK RX-ERR RX-DRP " +
                        "                                                        RX-OVR TX-OK TX-ERR TX-DRP TX-OVR Flg",
                "docker0    1500 0         0      0      0 0             0      0      0      0 BMU",
                "eno1       1500 0         0      0      0 0             0      0      0      0 BMU",
                "lo        65536 0      2507      0      0 0          2507      0      0      0 LRU",
                "wlxe8cc18bc1213  1500 0     11660      0      0 0          7008      0      0      0 BMRU")));

        when(reader.getInfo(Commands.RETURN_NAMES.getName())).thenReturn(result);
        List<String> expectedResult = Arrays.asList("docker0", "eno1", "lo", "wlxe8cc18bc1213");

        // test
        Assert.assertEquals(expectedResult, parser.parseNames());
    }

    @Test(expected = ParseException.class)
    public void testParseNamesWithParseException() throws ParseException {

        // prepare
        Optional<List<String>> result = Optional.empty();
        when(reader.getInfo(Commands.RETURN_NAMES.getName())).thenReturn(result);

        // test
        parser.parseNames();
    }

    @Test
    public void testParseByName1() throws ParseException {

        // prepare
        Optional<List<String>> result = Optional.of(new ArrayList<>(Arrays.asList(
                                        "docker0   Link encap:Ethernet  HWaddr 02:42:b4:c1:8e:4c  ",
                                        "           inet addr:172.17.0.1  Bcast:172.17.255.255  Mask:255.255.0.0",
                                        "           UP BROADCAST MULTICAST  MTU:1500  Metric:1",
                                        "           RX packets:0 errors:0 dropped:0 overruns:0 frame:0",
                                        "           TX packets:0 errors:0 dropped:0 overruns:0 carrier:0",
                                        "           collisions:0 txqueuelen:0 ",
                                        "           RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)")));

        when(reader.getInfo(Commands.RETURN_INFO.getName() + "docker0")).thenReturn(result);
        NetworkInterface expectedResult = new NetworkInterface();
        expectedResult.setName("docker0");
        expectedResult.setHwAddress("02:42:b4:c1:8e:4c");
        expectedResult.setInetAddress(new String[]{"172.17.0.1", null});
        expectedResult.setMTU("1500");

        // test
        Assert.assertEquals(expectedResult, parser.parseByName("docker0"));
    }

    @Test
    public void testParseByName2() throws ParseException {

        // prepare
        Optional<List<String>> result = Optional.of(new ArrayList<>(Arrays.asList(
                                        "eno1      Link encap:Ethernet  HWaddr f4:6d:04:af:21:33  ",
                                        "           UP BROADCAST MULTICAST  MTU:1500  Metric:1",
                                        "           RX packets:0 errors:0 dropped:0 overruns:0 frame:0",
                                        "           TX packets:0 errors:0 dropped:0 overruns:0 carrier:0",
                                        "           collisions:0 txqueuelen:1000 ",
                                        "           RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)",
                                        "           Interrupt:18 Память:fe700000-fe720000 ")));

        when(reader.getInfo(Commands.RETURN_INFO.getName() + "eno1")).thenReturn(result);
        NetworkInterface expectedResult = new NetworkInterface();
        expectedResult.setName("eno1");
        expectedResult.setHwAddress("f4:6d:04:af:21:33");
        expectedResult.setInetAddress(new String[]{null, null});
        expectedResult.setMTU("1500");

        // test
        Assert.assertEquals(expectedResult, parser.parseByName("eno1"));
    }

    @Test
    public void testParseByName3() throws ParseException {

        // prepare
        Optional<List<String>> result = Optional.of(new ArrayList<>(Arrays.asList(
                                        "lo        Link encap:Локальная петля (Loopback)  ",
                                        "           inet addr:127.0.0.1  Mask:255.0.0.0",
                                        "           inet6 addr: ::1/128 Scope:Host",
                                        "           UP LOOPBACK RUNNING  MTU:65536  Metric:1",
                                        "           TX packets:27831 errors:0 dropped:0 overruns:0 carrier:0",
                                        "           collisions:0 txqueuelen:1000 ",
                                        "           RX bytes:3407502 (3.4 MB)  TX bytes:3407502 (3.4 MB)")));

        when(reader.getInfo(Commands.RETURN_INFO.getName() + "lo")).thenReturn(result);
        NetworkInterface expectedResult = new NetworkInterface();
        expectedResult.setName("lo");
        expectedResult.setHwAddress(null);
        expectedResult.setInetAddress(new String[]{"127.0.0.1", "::1/128"});
        expectedResult.setMTU("65536");

        // test
        Assert.assertEquals(expectedResult, parser.parseByName("lo"));
    }

    @Test
    public void testParseByName4() throws ParseException {

        // prepare
        Optional<List<String>> result = Optional.of(new ArrayList<>(Arrays.asList(
                                        "wlxe8cc18bc1213 Link encap:Ethernet  HWaddr e8:cc:18:bc:12:13  ",
                                        "           inet addr:192.168.1.102  Bcast:192.168.1.255  Mask:255.255.255.0",
                                        "           inet6 addr: fe80::e628:15fd:c103:b143/64 Scope:Link",
                                        "           UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1",
                                        "           RX packets:32527 errors:0 dropped:0 overruns:0 frame:0",
                                        "           TX packets:23245 errors:0 dropped:0 overruns:0 carrier:0",
                                        "           collisions:0 txqueuelen:1000 ",
                                        "           RX bytes:22893278 (22.8 MB)  TX bytes:5533608 (5.5 MB)")));

        when(reader.getInfo(Commands.RETURN_INFO.getName() + "wlxe8cc18bc1213")).thenReturn(result);
        NetworkInterface expectedResult = new NetworkInterface();
        expectedResult.setName("wlxe8cc18bc1213");
        expectedResult.setHwAddress("e8:cc:18:bc:12:13");
        expectedResult.setInetAddress(new String[]{"192.168.1.102", "fe80::e628:15fd:c103:b143/64"});
        expectedResult.setMTU("1500");

        // test
        Assert.assertEquals(expectedResult, parser.parseByName("wlxe8cc18bc1213"));
    }

    @Test(expected = ParseException.class)
    public void testParseByNameWithParseException() throws ParseException {

        // prepare
        Optional<List<String>> result = Optional.empty();
        when(reader.getInfo(Commands.RETURN_INFO.getName() + "lo")).thenReturn(result);

        // test
        parser.parseByName("lo");
    }
}
