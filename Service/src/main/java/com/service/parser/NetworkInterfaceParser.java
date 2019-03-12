package com.service.parser;

import com.service.model.NetworkInterface;
import com.service.parser.helpers.enums.Commands;
import com.service.parser.helpers.ConsoleReader;
import com.service.parser.helpers.enums.InfoParameters;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkInterfaceParser {

    private List<String> info;

    @Autowired
    private ConsoleReader consoleReader;

    /**
     * Parsing names of all available NetworkInterfaces
     * @return list with names of all available NetworkInterfaces
     * @throws ParseException if nothing was read from console
     */
    public List<String> parseNames() throws ParseException {
        List<String> interfaceNames = new ArrayList<>();
        info = consoleReader.getInfo(Commands.RETURN_NAMES.getName()).orElseThrow(ParseException::new);
        info.remove(0);
        for (String line : info) {
            interfaceNames.add(line.split(" ")[0]);
        }
        return interfaceNames;
    }

    /**
     * Parsing NetworkInterface and its parameters
     * @param name name of NetworkInterface
     * @return NetworkInterface object
     * @throws ParseException if nothing was read from console
     */
    public NetworkInterface parseByName(String name) throws ParseException {
        NetworkInterface networkInterface = new NetworkInterface();
        String[] networkAddresses = new String[2];
        info = consoleReader.getInfo(Commands.RETURN_INFO.getName() + name).orElseThrow(ParseException::new);
        for (String line : info) {
           String[] lineToArray = line.split(" ");
            if (line.contains(InfoParameters.MAC.getName())) {
                networkInterface.setHwAddress(lineToArray[lineToArray.length - 1]);
            }
            if (line.contains(InfoParameters.MTU.getName())) {
                networkInterface.setMTU(lineToArray[lineToArray.length - 3].split(":")[1]);
            }
            if (line.contains(InfoParameters.IPV4.getName()) && !line.contains(InfoParameters.IPV6.getName())) {
                int ipv4Index = line.indexOf(InfoParameters.IPV4.getName());
                networkAddresses[0] = lineToArray[ipv4Index + 1].split(":")[1];
            }
            if (line.contains(InfoParameters.IPV6.getName())) {
                int ipv6Index = line.indexOf(InfoParameters.IPV6.getName());
                networkAddresses[1] = lineToArray[ipv6Index + 2];
            }
            networkInterface.setName(name);
            networkInterface.setInetAddress(networkAddresses);
        }
        return networkInterface;
    }
}
