package com.client.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.client.commands.enums.Errors;
import com.client.commands.interfaces.Commandable;
import com.client.commands.interfaces.Networker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URL;

@Parameters(commandNames = {"list"})
public class ListCommand implements Commandable, Networker, com.client.commands.interfaces.JSONParser {

    @Parameter(names = {"--server"})
    private String ip;

    @Parameter(names = {"--port"})
    private String port;

    @Override
    public void performCommand() {
        try {
            URL url = new URL("http://" + ip + ":" + port + "/service/v1/interfaces");
            String response = String.valueOf(performRequest(url));
            parse(response);
        } catch (Exception e) {
            System.out.println(Errors.SERVER_ERROR.getMessage());
        }
    }

    @Override
    public void parse(String string) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(string);
            JSONArray networkInterfaces = (JSONArray) jsonObject.get("interfaces");
            System.out.println(String.join(" ", networkInterfaces));
        } catch (Exception e) {
            System.out.println(Errors.SERVER_ERROR.getMessage());
        }
    }
}
