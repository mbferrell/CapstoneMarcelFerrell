package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoJdbcTemplateImpl implements GameDao {
    private static final String INSERT_GAME_SQL = "insert into game ( title , esrb_rating , description ,  price , studio , quantity) values (?,?,?,?,?,?) ";

    private static final String SELECT_GAME_SQL = "select * from game where game_id = ?";

    private static final String SELECT_ALL_GAMES_SQL = "select * from game";

    private static final String DELETE_GAME_SQL = "delete from game where game_id = ?";

    private static final String UPDATE_GAME_SQL = "update game set title = ?, esrb_rating = ? , description = ? , price = ? , studio = ?, quantity = ? where game_id = ?";

    private static final String SELECT_GAME_BY_STUDIO_SQL = "select * from game where studio = ?";

    private static final String SELECT_GAME_BY_ESRB_RATING_SQL = "select * from game where esrb_rating = ?";

    private static final String SELECT_GAME_BY_TITLE_SQL = "select * from game where title = ?";

    // this allows us to use the templates above
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game getGame(int id){
        try{
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapRowToGame, id);
        } catch(EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    @Transactional
    public Game addGame(Game game){
        jdbcTemplate.update(INSERT_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        game.setGameId(id);

        return  game;
    }

    @Override
    public void deleteGame(int id){
        jdbcTemplate.update(DELETE_GAME_SQL, id);
    }

    @Override
    public  void updateGame(Game game){
        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getGameId());
    }

    @Override
    public List<Game> getAllGames(){
        return jdbcTemplate.query(SELECT_ALL_GAMES_SQL, this::mapRowToGame);
    }

    @Override
    public List<Game> getGamesByStudio(String studio){
        return jdbcTemplate.query(SELECT_GAME_BY_STUDIO_SQL, this::mapRowToGame, studio);
    }
    @Override
    public List<Game> getGamesByESRBRating(String esrbRating){
        return jdbcTemplate.query(SELECT_GAME_BY_ESRB_RATING_SQL, this::mapRowToGame, esrbRating);
    }

    @Override
    public Game getGameByTitle(String title) {
        try{
            return jdbcTemplate.queryForObject(SELECT_GAME_BY_TITLE_SQL, this::mapRowToGame, title);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    private Game mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();

        game.setGameId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setEsrbRating(rs.getString("esrb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));

        return game;
    }
}
