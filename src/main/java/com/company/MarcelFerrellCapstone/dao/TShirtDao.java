package com.company.MarcelFerrellCapstone.dao;

import com.company.MarcelFerrellCapstone.domain.TShirt;

import java.util.List;

public interface TShirtDao {

    TShirt addTShirt(TShirt tShirt);

    TShirt getTShirt(int tShirtId);

    List<TShirt> getAllTShirts();

    void updateTShirt(TShirt tShirt);

    void deleteTShirt(int tShirtId);

    List<TShirt> getTShirtsByColor(String color);

    List<TShirt> getTShirtsBySize(String size);

}
