package com.company.KiaraChambersMarcelFerrellCapstone.dao;

import com.company.KiaraChambersMarcelFerrellCapstone.model.Game;

import java.util.List;

public interface GameDao {

    Game addGame(Game game);

    Game getGame(int gameId);

    List<Game> getAllGames();

    void updateGame(Game game);

    void deleteGame(int gameId);

    List<Game> getGamesByStudio(String studio);

    List<Game> getGamesByESRBRating(String ESRBRating);

    Game getGameByTitle(String Title);

}
