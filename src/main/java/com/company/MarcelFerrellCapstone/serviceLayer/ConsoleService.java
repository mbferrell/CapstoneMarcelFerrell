package com.company.MarcelFerrellCapstone.serviceLayer;

import com.company.MarcelFerrellCapstone.dao.ConsoleDao;
import com.company.MarcelFerrellCapstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsoleService {

    private final ConsoleDao consoleDao;



    @Autowired
    public ConsoleService(ConsoleDao consoleDao) {
        this.consoleDao = consoleDao;
    }

    public Console saveConsole(Console console){return consoleDao.addConsole(console);}

    public Console getConsole(int id){return consoleDao.getConsole(id);}

    public List<Console> getAllConsoles(){return consoleDao.getAllConsoles();}

    public void updateConsole(Console console){ consoleDao.updateConsole(console);}

    public void deleteConsole(int id){consoleDao.deleteConsole(id);}

    public List<Console> getConsolesByManufacturer(String manufacturer){return consoleDao.getConsolesByManufacturer(manufacturer);}
}
