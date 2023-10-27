package com.tads_prog2.service;

import com.tads_prog2.controller.dto.DataReportListaDEDTO;
import com.tads_prog2.controller.dto.DataStructureDTO;
import com.tads_prog2.exceptions.KidsException;
import com.tads_prog2.model.City;
import com.tads_prog2.model.Gender;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ListDEService {
    private ListDE kids;

    public ListDEService() {
        //Simular que leyo un archivo o una base de datos
        kids = new ListDE();
        kids.addKidToEndDE(new Kid("1006", "Valeria Osorio", (byte) 20,new Gender("female"), new City("17001", "Manizales")
                , "si"));
        kids.addKidToEndDE(new Kid("1007", "Jhair Torres", (byte) 18, new Gender("male"), new City("05001", "Medellin"),
                "no"));
        kids.addKidToEndDE(new Kid("1003", "John Jaime", (byte) 18, new Gender("male"), new City("05091", "Betania"),
                "si"));
        kids.addKidToEndDE(new Kid("1004", "Sergio Nuñez", (byte) 19, new Gender("male"), new City("11001", "Bogota"),
                "no"));
        kids.addKidToEndDE(new Kid("1008", "Sebastian Rugeles", (byte) 19, new Gender("male"), new City("17001", "Manizales"),
                "si"));

    }

    public List<Kid> getAll() throws KidsException {
        return kids.getAll();
    }

    public String invertDE() {
        kids.invertDE();
        return "Invertido";
    }

    public String changeExtDE() {
        kids.changeExtDE();
        return "Invertidos los Extremos";
    }

    public String sortbyGenderDE() {
        try {
            kids.sortbyGenderDE();
            return "Intercalados";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String addPosDE(int posicion, Kid kid) {
        kids.addPosDE(posicion, kid);
        return "Agregado";
    }


    public String deletePosDE(int pos) {
        try {
            kids.deletePosDE(pos);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String deleteIdDE(String id) {
        try {
            kids.deleteIdDE(id);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String deleteKamikaze(int pos) {
        try {
            kids.deleteKamikaze(pos);
            return "Kamikazeeeee";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public List<DataReportListaDEDTO> cityReportDE() throws KidsException {
        try {
            return kids.BrothersReport();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public List<String> getgender() {
        return kids.getgender();
    }
}

