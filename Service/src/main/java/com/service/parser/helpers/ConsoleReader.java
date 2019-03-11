package com.service.parser.helpers;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsoleReader {

    /**
     * @param command to execute
     * @return list with lines of command's output
     * @throws IOException if bufferedReader went wrong
     * @throws InterruptedException if process went wrong
     */
    public List<String> getInfo(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String resultSet;
        List<String> stringList = new ArrayList<>();
        while ((resultSet = bufferedReader.readLine()) != null)
            stringList.add(resultSet);
        process.waitFor();
        process.destroy();
        return stringList;
    }
}
