package com.company.MarcelFerrellCapstone.controllers;

import com.company.MarcelFerrellCapstone.serviceLayer.GameService;
import com.company.MarcelFerrellCapstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameController {


    @Autowired
    private GameService gameService;


    //create game ->post
    @RequestMapping(value = "/game", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Game createGame(@RequestBody @Valid Game game) {
        Game game1 = new Game();
        try{
            game = gameService.saveGame(game);
            return game;
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid Body");
        }
    }

    //Read (get  game)
    @RequestMapping(value="/game/{id}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public Game getGame(@PathVariable int id){
        Game game1 = new Game();
        game1= gameService.getGame(id);
        return game1;
    }

    //get all game
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Game> getAllGames(){
        List<Game> allGame = gameService.getAllGames( );
        if(allGame == null){
            throw new IllegalArgumentException("no game found");
        }
        return allGame;

    }

    //update game
    @RequestMapping(value = "/game", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateGame(@RequestBody Game game){
        gameService.updateGame(game);

    }
    //delete a game
    @RequestMapping(value = "/game/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        try{
            gameService.deleteGame(id);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ID Not Found - Cannot Delete");
        }

    }

    //get by studio
    @RequestMapping(value="/game/studio/{studio}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio){
        try{
            return gameService.getGamesByStudio(studio);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Studio not Found");
        }

    }

    //get by ESRB
    @RequestMapping(value="/game/esrb/{esrb}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public List<Game> getGameByESRB(@PathVariable String esrb){
        try{
            return gameService.getGamesByESRB(esrb);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("ESRB rating not Found");
        }
    }

    //get by Title
    @RequestMapping(value="/game/title/{title}", method = RequestMethod.GET)
    @ResponseStatus(value =HttpStatus.OK)
    public Game getGameByTitle(@PathVariable String title){
        try{
            return gameService.getGameByTitle(title);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Title not Found");
        }

    }
}
