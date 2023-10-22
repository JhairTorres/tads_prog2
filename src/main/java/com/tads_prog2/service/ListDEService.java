package com.tads_prog2.service;

import com.tads_prog2.model.City;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ListDEService {
    private ListDE kids;
    public ListDEService() {
        //Simular que leyo un archivo o una base de datos
        kids = new ListDE();
        kids.addKidToEnd(new Kid("1006", "Valeria Osorio", (byte) 20,"Female",new City("17001","Manizales")));
        kids.addKidToEnd(new Kid("1007", "Jhair Torres", (byte)18 ,"Male",new City("05001","Medellin")));
        kids.addKidToEnd(new Kid("1003", "John Jaime", (byte) 18,"Male",new City("05091","Betania")));
        kids.addKidToEnd(new Kid("1004", "Sergio Nuñez", (byte) 19,"Male",new City("11001","Bogota")));
        kids.addKidToEnd(new Kid("1008", "Sebastian Rugeles", (byte) 19,"Male",new City("17001","Manizales")));

    }
}