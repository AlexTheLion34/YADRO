package com.service.parser.helpers;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsoleReader {

    /**
     * Executing given command and putting data in list
     * @param command to execute
     * @return list with lines of command's output
     * or empty object if errors occurred
     */
    public Optional<List<String>> getInfo(String command) {
        Optional<List<String>> stringList = Optional.of(new ArrayList<>());
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String resultSet;
            while ((resultSet = bufferedReader.readLine()) != null)
                stringList.get().add(resultSet);
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            return Optional.empty();
        }
        return stringList;
    }
}
