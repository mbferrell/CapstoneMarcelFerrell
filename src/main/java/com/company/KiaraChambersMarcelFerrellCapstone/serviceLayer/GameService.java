package com.company.KiaraChambersMarcelFerrellCapstone.serviceLayer;

import com.company.KiaraChambersMarcelFerrellCapstone.dao.GameDao;
import com.company.KiaraChambersMarcelFerrellCapstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameDao gameDao;

    @Autowired
    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public Game saveGame(Game game){
        return gameDao.addGame(game);
    }

    public Game getGame(int id){
        return gameDao.getGame(id);
    }

    public List<Game> getAllGames(){
        return gameDao.getAllGames();
    }

    public void updateGame(Game game){
        gameDao.updateGame(game);
    }

    public void deleteGame(int id){
        gameDao.deleteGame(id);
    }

    public List<Game> getGamesByStudio (String studio){
        return gameDao.getGamesByStudio(studio);}

    public Game getGameByTitle(String title){return gameDao.getGameByTitle(title);}

    public List<Game> getGamesByESRB(String esrb){return gameDao.getGamesByESRBRating(esrb);}
}