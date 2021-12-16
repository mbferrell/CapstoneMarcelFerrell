package com.company.MarcelFerrellCapstone.controllers;

import com.company.MarcelFerrellCapstone.serviceLayer.ConsoleService;
import com.company.MarcelFerrellCapstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;


    //create console ->post
    @RequestMapping(value = "/console", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Console createConsole(@RequestBody @Valid Console console) {
        try{
            return consoleService.saveConsole(console);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid Body");
        }
    }

    //Read (get  console)
    @RequestMapping(value="/console/{id}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public Console getConsole(@PathVariable int id){
        Console console  = null;

        try{
            console = consoleService.getConsole(id);
            return console;
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ID not found");
        }

    }

    //get all console
    @RequestMapping(value = "/console", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Console> getAllConsoles( ){
        List<Console> allConsole = consoleService.getAllConsoles( );
        if(allConsole == null){
            throw new IllegalArgumentException("no console found");
        }
        return allConsole;

    }

    //update console
    @RequestMapping(value = "/console", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateConsole(@RequestBody Console console){
        consoleService.updateConsole(console);

    }
    //delete a console
    @RequestMapping(value = "/console/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id){
        try{
            consoleService.deleteConsole(id);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ID Not Found - Cannot Delete");
        }

    }

    //get by Manufacturer
    @RequestMapping(value="/console/manufacturer/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer){
        try{
            return consoleService.getConsolesByManufacturer(manufacturer);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Manufacturer not found");
        }
    }


}
