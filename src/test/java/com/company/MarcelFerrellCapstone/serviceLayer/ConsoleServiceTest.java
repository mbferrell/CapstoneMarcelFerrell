package com.company.MarcelFerrellCapstone.serviceLayer;

import com.company.MarcelFerrellCapstone.dao.ConsoleDao;
import com.company.MarcelFerrellCapstone.dao.ConsoleDaoJdbcTemplateImpl;
import com.company.MarcelFerrellCapstone.model.Console;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ConsoleServiceTest {

    ConsoleService consoleService;
    ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception{

        setUpConsoleDaoMock();

        consoleService = new ConsoleService(consoleDao);
    }
    @Test
    public void saveFindConsole(){
        Console console = new Console();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Zen 2");
        console.setPrice(new BigDecimal("300.00"));
        console.setQuantity(1);

        console = consoleService.saveConsole(console);

        Console fromConsoleService = consoleService.getConsole(console.getConsoleId());

        assertEquals(console,fromConsoleService);

    }
    @Test
    public void findallConsole(){
        List<Console> fromConsoleService = consoleService.getAllConsoles();

        assertEquals(1,fromConsoleService.size());
    }

    @Test
    public void saveFindManufacturer(){
        Console console = new Console();
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Zen 2");
        console.setPrice(new BigDecimal("300.00"));
        console.setQuantity(1);

        console = consoleService.saveConsole(console);


        List<Console> cList = consoleService.getConsolesByManufacturer(console.getManufacturer());

        assertEquals(1,cList.size());
    }

    //helper methods
    private void setUpConsoleDaoMock(){

        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console =new Console();
        console.setConsoleId(1);
        console.setModel("Playstation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("500GB");
        console.setProcessor("AMD Zen 2");
        console.setPrice(new BigDecimal("300.00"));
        console.setQuantity(1);


        Console console1 = new Console();
        console1.setModel("Playstation 5");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("500GB");
        console1.setProcessor("AMD Zen 2");
        console1.setPrice(new BigDecimal("300.00"));
        console1.setQuantity(1);

        List<Console> cList = new ArrayList<>();
        cList.add(console);

        doReturn(console).when(consoleDao).addConsole(console1);
        doReturn(console).when(consoleDao).getConsole(1);
        doReturn(cList).when(consoleDao).getAllConsoles();
        doReturn(cList).when(consoleDao).getConsolesByManufacturer("Sony");
    }
}