package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.domain.Game;
import com.company.MarcelFerrellCapstone.domain.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao {
    private static final String INSERT_T_SHIRT_SQL = "insert into t_shirt ( size , color , description ,  price , quantity) values (?,?,?,?,?) ";

    private static final String SELECT_T_SHIRT_SQL = "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_ALL_T_SHIRTS_SQL = "select * from t_shirt";

    private static final String DELETE_T_SHIRT_SQL = "delete from t_shirt where t_shirt_id = ?";

    private static final String UPDATE_T_SHIRT_SQL = "update t_shirt set size = ?, color = ? , description = ? , price = ? , quantity = ? where t_shirt_id = ?";

    private static final String SELECT_T_SHIRT_BY_SIZE_SQL = "select * from t_shirt where size = ?";

    private static final String SELECT_T_SHIRT_BY_COLOR_SQL = "select * from t_shirt where color = ?";
    // this allows us to use the templates above
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TShirt getTShirt(int id){
        try{
            return jdbcTemplate.queryForObject(SELECT_T_SHIRT_SQL, this::mapRowToTShirt, id);
        } catch(EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    @Transactional
    public TShirt addTShirt (TShirt tShirt){
        jdbcTemplate.update(INSERT_T_SHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        tShirt.settShirtId(id);

        return  tShirt;
    }

    @Override
    public void deleteTShirt(int id){
        jdbcTemplate.update(DELETE_T_SHIRT_SQL, id);
    }

    @Override
    public  void updateTShirt(TShirt tShirt){
        jdbcTemplate.update(UPDATE_T_SHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getShirtId());
    }

    @Override
    public List<TShirt> getAllTShirts(){
        return jdbcTemplate.query(SELECT_ALL_T_SHIRTS_SQL, this::mapRowToTShirt);
    }

    @Override
    public List<TShirt> getTShirtsBySize(String size){
        return jdbcTemplate.query(SELECT_T_SHIRT_BY_SIZE_SQL, this::mapRowToTShirt, size);
    }
    @Override
    public List<TShirt> getTShirtsByColor(String color){
        return jdbcTemplate.query(SELECT_T_SHIRT_BY_COLOR_SQL, this::mapRowToTShirt, color);
    }


    private TShirt mapRowToTShirt(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();

        tShirt.settShirtId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));

        return tShirt;
    }
}
