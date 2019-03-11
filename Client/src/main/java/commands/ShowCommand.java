package commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import commands.enums.Errors;
import commands.interfaces.Commandable;
import commands.interfaces.JSONParser;
import commands.interfaces.Networker;
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
            parse(response);
        } catch (IOException e) {
            if (e.getClass() == java.io.FileNotFoundException.class) {
                System.out.println(Errors.NAME_ERROR.getMessage());
            } else {
                System.out.println(Errors.SERVER_ERROR.getMessage());
            }
        }
    }

    @Override
    public void parse(String string) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) new org.json.simple.parser.JSONParser().parse(string);
        } catch (ParseException e) {
            System.out.println(Errors.SERVER_ERROR.getMessage());
        }
        if (jsonObject.get("name") != null) {
            format(jsonObject);
        }
    }

    private void format(JSONObject jsonObject) {
        String name = (String) jsonObject.get("name");
        String space = new String(new char[name.length() + 3]).replace('\0', ' ');
        List inetAddresses = (List) jsonObject.get("inetAddress");
        System.out.print(jsonObject.get("name") + ":  ");
        System.out.println("Hw_addr: " + jsonObject.get("hwAddress") + "\n");
        System.out.println(space + "Ipv4: " + inetAddresses.remove(0) + "\n");
        System.out.println(space + "Ipv6: " + inetAddresses.remove(0) + "\n");
        System.out.println(space + "MTU: "  + jsonObject.get("mtu") + "\n");
    }
}
