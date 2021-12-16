package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.model.Game;

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
