package com.company.MarcelFerrellCapstone.dao;

import  com.company.MarcelFerrellCapstone.domain.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoTest {

    @Autowired
    public GameDao gameDao;

    @Before
    public void setUp() throws Exception {

        List<Game> allGames = gameDao.getAllGames();
        for(Game a : allGames) {
            gameDao.deleteGame(a.getGameId());
        }
    }

    @Test
    public void  addGetDeleteGame(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrbRating("M+");
        game.setDescription("Military/War game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Primary Infinity Ward");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        Game game1 = gameDao.getGame(game.getGameId());

        assertEquals(game1, game);

        gameDao.deleteGame(game.getGameId());

        game1 = gameDao.getGame(game.getGameId());

        assertNull(game1);

    }

    @Test
    public void getAllGames(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrbRating("M+");
        game.setDescription("Military/War game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Primary Infinity Ward");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Grand Theft Auto");
        game.setEsrbRating("M+");
        game.setDescription("Crime/Gang game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        List<Game> games = gameDao.getAllGames();

        assertEquals(games.size(), 2);

    }

    @Test
    public void updateGame(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrbRating("M+");
        game.setDescription("Military/War game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Primary Infinity Ward");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        game.setEsrbRating("R");
        game.setPrice(new BigDecimal("49.99"));

        gameDao.updateGame(game);

        Game game1 = gameDao.getGame(game.getGameId());

        assertEquals(game1,game);
    }

    @Test
    public void getGamesByStudio(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrbRating("M+");
        game.setDescription("Military/War game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Primary Infinity Ward");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Grand Theft Auto");
        game.setEsrbRating("R");
        game.setDescription("Crime/Gang game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Red Dead Redemption");
        game.setEsrbRating("M+");
        game.setDescription("Zombie");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        List<Game> games = gameDao.getGamesByStudio(game.getStudio());

        assertEquals(games.size(), 2);

    }

    @Test
    public void getGameByESRBRating(){
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrbRating("M+");
        game.setDescription("Military/War game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Primary Infinity Ward");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Grand Theft Auto");
        game.setEsrbRating("R");
        game.setDescription("Crime/Gang game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("Red Dead Redemption");
        game.setEsrbRating("M+");
        game.setDescription("Zombie");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Rockstar Games");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        List<Game> games = gameDao.getGamesByESRBRating(game.getEsrbRating());

        assertEquals(games.size(), 2);
    }

    @Test
    public void getGameByTitle(){
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrbRating("M+");
        game.setDescription("Military/War game");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Primary Infinity Ward");
        game.setQuantity(3);

        game = gameDao.addGame(game);

        Game game1 = gameDao.getGameByTitle("Call of Duty");

        assertEquals(game1, game);

    }
}