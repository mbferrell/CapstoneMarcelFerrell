package com.company.KiaraChambersMarcelFerrellCapstone.serviceLayer;

import com.company.KiaraChambersMarcelFerrellCapstone.dao.GameDao;
import com.company.KiaraChambersMarcelFerrellCapstone.dao.GameDaoJdbcTemplateImpl;
import com.company.KiaraChambersMarcelFerrellCapstone.model.Game;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


public class GameServiceTest {

    GameService gameService;
    GameDao gameDao;

    @Before
    public void setUp() throws Exception{
        setUpGameDaoMock();

        gameService = new GameService(gameDao);
    }

    @Test
    public void saveFindGame(){
        Game game = new Game();

        game.setTitle("Black Ops");
        game.setEsrbRating("Rated-R");
        game.setDescription("shooting game");
        game.setPrice(new BigDecimal("50.00"));
        game.setStudio("Activision");
        game.setQuantity(3);

        game = gameService.saveGame(game);

        Game fromGameService = gameService.getGame(game.getGameId());

        assertEquals(game,fromGameService);
    }

    @Test
    public void findAllGame(){
        List<Game> fromGameService = gameService.getAllGames();

        assertEquals(1,fromGameService.size());
    }

    @Test
    public void saveFindStudio(){
        Game game = new Game();
        game.setTitle("Black Ops");
        game.setEsrbRating("Rated-R");
        game.setDescription("shooting game");
        game.setPrice(new BigDecimal("50.00"));
        game.setStudio("Activision");
        game.setQuantity(3);

        game = gameService.saveGame(game);

        List<Game> gList = gameService.getGamesByStudio(game.getStudio());

        assertEquals(1, gList.size());
    }

    @Test
    public void saveFindTitle(){
        Game game = new Game();
        game.setTitle("Black Ops");
        game.setEsrbRating("Rated-R");
        game.setDescription("shooting game");
        game.setPrice(new BigDecimal("50.00"));
        game.setStudio("Activision");
        game.setQuantity(3);

        game = gameService.saveGame(game);

        Game game1 = gameService.getGameByTitle(game.getTitle());

        assertEquals(game, game1);
    }
    @Test
    public void saveFindESRB(){
        Game game = new Game();
        game.setTitle("Black Ops");
        game.setEsrbRating("Rated-R");
        game.setDescription("shooting game");
        game.setPrice(new BigDecimal("50.00"));
        game.setStudio("Activision");
        game.setQuantity(3);

        game = gameService.saveGame(game);

        List<Game> gList = gameService.getGamesByESRB(game.getEsrbRating());

        assertEquals(1, gList.size());
    }

    //helper methods
    private void setUpGameDaoMock(){
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Black Ops");
        game.setEsrbRating("Rated-R");
        game.setDescription("shooting game");
        game.setPrice(new BigDecimal("50.00"));
        game.setStudio("Activision");
        game.setQuantity(3);

        Game game1 = new Game();
        game1.setTitle("Black Ops");
        game1.setEsrbRating("Rated-R");
        game1.setDescription("shooting game");
        game1.setPrice(new BigDecimal("50.00"));
        game1.setStudio("Activision");
        game1.setQuantity(3);

        List<Game> gList = new ArrayList<>();
        gList.add(game);

        doReturn(game).when(gameDao).addGame(game1);
        doReturn(game).when(gameDao).getGame(1);
        doReturn(gList).when(gameDao).getAllGames();
        doReturn(gList).when(gameDao).getGamesByStudio("Activision");
        doReturn(gList).when(gameDao).getGamesByESRBRating("Rated-R");
        doReturn(game).when(gameDao).getGameByTitle("Black Ops");
    }

}