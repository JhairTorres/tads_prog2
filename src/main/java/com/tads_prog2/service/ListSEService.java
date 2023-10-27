package com.tads_prog2.service;

import com.tads_prog2.controller.dto.DataStructureDTO;
import com.tads_prog2.exceptions.KidsException;
import com.tads_prog2.model.City;
import com.tads_prog2.model.Gender;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        //Simular que leyo un archivo o una base de datos
        kids = new ListSE();
        kids.addKidToEnd(new Kid("1006", "Valeria Osorio", (byte) 20,"female",new City("17001","Manizales")
                ,1));
        kids.addKidToEnd(new Kid("1007", "Jhair Torres", (byte)18 ,"male",new City("05001","Medellin"),
                2));
        kids.addKidToEnd(new Kid("1003", "John Jaime", (byte) 18,"male",new City("05091","Betania"),
                3));
        kids.addKidToEnd(new Kid("1004", "Sergio Nuñez", (byte) 19,"male",new City("11001","Bogota"),
                0));
        kids.addKidToEnd(new Kid("1008", "Sebastian Rugeles", (byte) 19,"male",new City("17001","Manizales"),
                2));
    }

    public String invert(){
        kids.invert();
        return "Invertido";
    }
    public String changeExt(){
        kids.changeExt();
        return "Invertidos los Extremos";
    }
    public String sortbyGender(){
        try {
            kids.sortbyGender();
            return "Intercalados";
        }catch (KidsException e){
            return e.getMessage();
        }
    }
    public String addPos(int posicion, Kid kid){
        kids.addPos(posicion,kid);
        return "Agregado";
    }
    public String updateInPos(byte pos, Kid kid){
        kids.updateInPos(pos,kid);
        return "Actualizado";
    }
    public String deletePos(int pos){
        try {
            kids.deletePos(pos);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String deleteId(String id){
        try {
            kids.deleteId(id);
            return "Eliminado";
        }catch (KidsException e){
            return e.getMessage();
        }
    }

    public List<DataStructureDTO> cityReport() throws KidsException {
        try {
            return kids.cityReport();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public List<String> getCities(){
        return kids.getCities();
    }



}