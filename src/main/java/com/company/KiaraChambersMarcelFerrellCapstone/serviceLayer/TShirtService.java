package com.company.KiaraChambersMarcelFerrellCapstone.serviceLayer;

import com.company.KiaraChambersMarcelFerrellCapstone.dao.TShirtDao;
import com.company.KiaraChambersMarcelFerrellCapstone.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TShirtService {
    private final TShirtDao tShirtDao;

    @Autowired
    public TShirtService(TShirtDao tShirtDao) {
        this.tShirtDao = tShirtDao;
    }

    public TShirt saveTshirt(TShirt tShirt){return tShirtDao.addTShirt(tShirt);}

    public TShirt getTshirt(int id){return tShirtDao.getTShirt(id);}

    public List<TShirt> getAllTshirts(){return tShirtDao.getAllTShirts();}

    public void deleteTshirt(int id){tShirtDao.deleteTShirt(id);}

    public void updateTshirt(TShirt tShirt){tShirtDao.updateTShirt(tShirt);}

    public List<TShirt> getbyColor(String color){return tShirtDao.getTShirtsByColor(color);}

    public List<TShirt> getbySize(String size){return tShirtDao.getTShirtsBySize(size);}

}