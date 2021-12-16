package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao{

    private static final String INSERT_CONSOLE_SQL = "insert into console ( model , manufacturer , memory_amount , processor , price , quantity) values (?,?,?,?,?,?) ";

    private static final String SELECT_CONSOLE_SQL = "select * from console where console_id = ?";

    private static final String SELECT_ALL_CONSOLES_SQL = "select * from console";

    private static final String DELETE_CONSOLE_SQL = "delete from console where console_id = ?";

    private static final String UPDATE_CONSOLE_SQL = "update console set model = ?, manufacturer = ? , memory_amount = ? , processor = ? , price = ?, quantity = ? where console_id = ?";

    private static final String SELECT_CONSOLE_BY_MANUFACTURER_SQL = "select * from console where manufacturer = ?";

    // this allows us to use the templates above
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Console getConsole(int id){
        try{
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapRowToConsole, id);
        } catch(EmptyResultDataAccessException e){

            return null;
        }
    }

    @Override
    @Transactional
    public Console addConsole(Console console){
        jdbcTemplate.update(INSERT_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        console.setConsoleId(id);

        return  console;
    }

    @Override
    public void deleteConsole(int id){
        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);
    }

    @Override
    public  void updateConsole(Console console){
        jdbcTemplate.update(UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity(),
                console.getConsoleId());
    }

    @Override
    public List<Console> getAllConsoles(){
        return jdbcTemplate.query(SELECT_ALL_CONSOLES_SQL, this::mapRowToConsole);
    }

    @Override
    public List<Console> getConsolesByManufacturer(String manufacturer){
        return jdbcTemplate.query(SELECT_CONSOLE_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
    }

    private Console mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Console console = new Console();

        console.setConsoleId(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemoryAmount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getInt("quantity"));

        return console;
    }
}
