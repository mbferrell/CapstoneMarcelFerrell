package com.company.MarcelFerrellCapstone.controllers;

import com.company.MarcelFerrellCapstone.serviceLayer.TShirtService;
import com.company.MarcelFerrellCapstone.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TShirtController {

    @Autowired
    private TShirtService tShirtService;

    //create tshirt ->post
    @RequestMapping(value = "/TShirt", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public TShirt createTShirt(@RequestBody @Valid TShirt tShirt) {
        try{
            return tShirtService.saveTshirt(tShirt);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid Body");
        }

    }
    //Read (get  TShirt)

    @RequestMapping(value="/TShirt/{id}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public TShirt getTShirt(@PathVariable int id){
        try{
            return tShirtService.getTshirt(id);

        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ID not found");
        }

    }

    //get all TShirt
    @RequestMapping(value = "/TShirt", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<TShirt> getAllTShirt( ){

        List<TShirt> allTshirt = tShirtService.getAllTshirts( );
        if(allTshirt == null){
            throw new IllegalArgumentException("no t-shirt found");
        }
        return allTshirt;

    }

    //update tshirt
    @RequestMapping(value = "/TShirt", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateTShirt(@RequestBody TShirt tShirt){
        tShirtService.updateTshirt(tShirt);

    }
    //delete a TShirt
    @RequestMapping(value = "/TShirt/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id){
        try{
            tShirtService.deleteTshirt(id);

        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ID not found");
        }

    }

    //get by Color
    @RequestMapping(value="/TShirt/color/{color}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public List<TShirt> getConsoleByColor(@PathVariable String color){
        try{
            return tShirtService.getbyColor(color);

        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Color not found");
        }

    }

    //get by size
    @RequestMapping(value="/TShirt/size/{size}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public List<TShirt> getConsoleBySize(@PathVariable String size){
        try{
            return tShirtService.getbySize(size);

        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Size not found");
        }


    }
}