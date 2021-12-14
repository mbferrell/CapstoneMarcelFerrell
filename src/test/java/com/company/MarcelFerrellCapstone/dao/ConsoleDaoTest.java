package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.domain.Console;
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
public class ConsoleDaoTest {

    @Autowired
    public ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception {

        List<Console> allConsoles = consoleDao.getAllConsoles();
        for(Console a : allConsoles){
            consoleDao.deleteConsole(a.getConsoleId());
        }
    }

    @Test
    public void addGetDeleteConsole(){

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("64Gb");
        console.setProcessor("RazorSharp");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(1);

        console = consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());

        assertEquals(console1, console);

        consoleDao.deleteConsole(console.getConsoleId());

        console1 = consoleDao.getConsole(console.getConsoleId());

        assertNull(console1);

    }

    @Test
    public void getAllConsoles(){

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("64Gb");
        console.setProcessor("RazorSharp");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(5);

        console = consoleDao.addConsole(console);

        Console console2 = new Console();
        console2.setModel("Xbox");
        console2.setManufacturer("1");
        console2.setMemoryAmount("512Gb");
        console2.setProcessor("Microsoft");
        console2.setPrice(new BigDecimal("499.99"));
        console2.setQuantity(7);

        console2 = consoleDao.addConsole(console2);

        List<Console> consoles = consoleDao.getAllConsoles();

        assertEquals(consoles.size(), 2);

    }

    @Test
    public void updateConsole(){

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("64Gb");
        console.setProcessor("RazorSharp");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(5);

        console = consoleDao.addConsole(console);

        console.setModel("Switch Oled");
        console.setPrice(new BigDecimal("399.99"));

        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());

        assertEquals(console1, console);
    }
    @Test
    public void getConsolesByManufacturer(){

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("64Gb");
        console.setProcessor("RazorSharp");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(5);

        console = consoleDao.addConsole(console);

        Console console2 = new Console();
        console2.setModel("Xbox");
        console2.setManufacturer("1");
        console2.setMemoryAmount("512Gb");
        console2.setProcessor("Microsoft");
        console2.setPrice(new BigDecimal("499.99"));
        console2.setQuantity(7);

        console2 = consoleDao.addConsole(console2);

        Console console3 = new Console();
        console3.setModel("Switch Oled");
        console3.setManufacturer("Nintendo");
        console3.setMemoryAmount("64Gb");
        console3.setProcessor("RazorSharp");
        console3.setPrice(new BigDecimal("399.99"));
        console3.setQuantity(5);

        console3 = consoleDao.addConsole(console3);

        List<Console> consoles = consoleDao.getConsolesByManufacturer(console.getManufacturer());

        assertEquals(consoles.size(), 2);

    }
}
