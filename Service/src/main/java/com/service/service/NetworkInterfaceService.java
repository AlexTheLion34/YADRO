package com.service.service;

import com.service.exception.InterfaceNotFoundException;
import com.service.parser.NetworkInterfaceParser;
import com.service.model.NetworkInterface;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NetworkInterfaceService {

    @Autowired
    private NetworkInterfaceParser parser;

    public Map<String, String> getCurrentApiVersion() {
        Map<String, String> version = new HashMap<>();
        version.put("version", "v1");
        return version;
    }

    public Map<String, List<String>> getAllInterfaceNames() throws ParseException {
        Map<String, List<String>> interfaces = new HashMap<>();
        interfaces.put("interfaces", parser.parseNames());
        return interfaces;
    }

    public NetworkInterface getInterfaceByName(String name) throws InterfaceNotFoundException, ParseException {

        if (!getAllInterfaceNames().get("interfaces").contains(name))
            throw new InterfaceNotFoundException("interface " + name + " was not found");
        return parser.parseByName(name);
    }
}
