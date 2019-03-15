package com.client.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.client.commands.enums.Errors;
import com.client.commands.interfaces.Commandable;
import com.client.commands.interfaces.JSONParser;
import com.client.commands.interfaces.Networker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Parameters(commandNames = {"show"})
public class ShowCommand implements Commandable, Networker, JSONParser {

    @Parameter(names = { "--name" })
    private String interfaceName;

    @Parameter(names = { "--server" })
    private String ip;

    @Parameter(names = { "--port" })
    private String port;

    @Override
    public void performCommand() {
        try {
            URL url = new URL("http://" + ip + ":" + port + "/service/v1/interfaces/" + interfaceName);
            String response = String.valueOf(performRequest(url));
            System.out.println(parse(response));
        } catch (IOException e) {
            if (e.getClass() == java.io.FileNotFoundException.class) {
                System.out.println(Errors.NAME_ERROR.getMessage());
            } else {
                System.out.println(Errors.SERVER_ERROR.getMessage());
            }
        }
    }

    @Override
    public String parse(String string) {
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) new org.json.simple.parser.JSONParser().parse(string);
        } catch (ParseException e) {
            return Errors.SERVER_ERROR.getMessage();
        }
        if (jsonObject.get("name") != null) {
            return format(jsonObject);
        }
        return "";
    }

    private String format(JSONObject jsonObject) {
        String name = (String) jsonObject.get("name");
        String space = new String(new char[name.length() + 3]).replace('\0', ' ');
        List inetAddresses = (List) jsonObject.get("inetAddress");
        StringBuilder result = new StringBuilder();

        result.append(jsonObject.get("name")).append(":  ");
        result.append("Hw_addr: ").append(jsonObject.get("hwAddress")).append("\n");
        result.append(space).append("Ipv4: ").append(inetAddresses.remove(0)).append("\n");
        result.append(space).append("Ipv6: ").append(inetAddresses.remove(0)).append("\n");
        result.append(space).append("MTU: ").append(jsonObject.get("mtu")).append("\n");
        return result.toString();
    }
}
