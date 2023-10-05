package com.tads_prog2.service;

import com.tads_prog2.exceptions.KidsException;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        // simular que lei un archivo o una base de datos
        kids = new ListSE();
        kids.addEnd(new Kid("1005085752","Valeria Osorio"
                , (byte) 20,"Femenino") );
        kids.addEnd(new Kid("356373763","Jhair Torres"
                , (byte) 18,"Masculino"));
        kids.addEnd(new Kid("4554544554","Sergio Núñez"
                , (byte) 19,"Masculino"));

    }

    //Add Pos#4
    public String insertInPos(int pos,Kid kid){
        kids.insertInPos(pos, kid);
        return "Insertado";
    }


    //Invert#5
    public String invert(){
        kids.invert();
        return "Invertido";
    }

    //Change Ext #6
    public String changeExt(){
        kids.changeExt();
        return "Invertidos";
    }
    //delete pos #8
    public String deletePos(int posicion){
        kids.deletePos(posicion);
        return "Borrado";
    }

    //delete id #9
    public String deleteId(String identification){
        kids.deleteId(identification);
        return "borrado";
    }

    //Update in pos #10
    public String updateInPos(int pos, Kid kid){
        kids.updateInPos(pos,kid);
        return "Actualizado";
    }

}
