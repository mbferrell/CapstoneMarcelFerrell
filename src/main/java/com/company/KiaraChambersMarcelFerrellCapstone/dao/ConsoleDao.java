package com.company.KiaraChambersMarcelFerrellCapstone.dao;

import com.company.KiaraChambersMarcelFerrellCapstone.model.Console;

import java.util.List;

public interface ConsoleDao {

    Console addConsole(Console console);

    Console getConsole(int consoleId);

    List<Console> getAllConsoles();

    void updateConsole(Console console);

    void deleteConsole(int consoleId);

    List<Console> getConsolesByManufacturer(String manufacturer);

}
