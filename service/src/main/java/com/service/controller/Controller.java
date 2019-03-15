package com.service.controller;

import com.service.service.NetworkInterfaceService;
import com.service.exception.InterfaceNotFoundException;
import com.service.exception.VersionDoesNotMatchException;
import com.service.model.NetworkInterface;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/service")
public class Controller {

    @Autowired
    private NetworkInterfaceService service;

    @GetMapping(value = "/version")
    public ResponseEntity<Map<String, String>> getVersion() {
        return new ResponseEntity<>(service.getCurrentApiVersion(), HttpStatus.OK);
    }

    @GetMapping(value = "/{api_version}/interfaces")
    public ResponseEntity<Map<String, List<String>>> getAllInterfaces(@PathVariable("api_version") String version)
                                                                  throws VersionDoesNotMatchException, ParseException {

        if (!version.equals(service.getCurrentApiVersion().get("version")))
            throw new VersionDoesNotMatchException("Version " + version + " is not current");

        return new ResponseEntity<>(service.getAllInterfaceNames(), HttpStatus.OK);
    }

    @GetMapping(value = "/{api_version}/interfaces/{name}")
    public ResponseEntity<NetworkInterface> getInterface(@PathVariable("api_version") String version,
                                                         @PathVariable(value = "name") String name)
                                                        throws InterfaceNotFoundException, VersionDoesNotMatchException,
                                                                                                        ParseException {

        if (!version.equals(service.getCurrentApiVersion().get("version")))
            throw new VersionDoesNotMatchException("Version " + version + " is not current");

        return new ResponseEntity<>(service.getInterfaceByName(name), HttpStatus.OK);
    }
}
