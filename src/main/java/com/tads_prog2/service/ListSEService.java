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
                , (byte) 20));
        kids.addEnd(new Kid("356373763","Jhair Torres"
                , (byte) 18));
        kids.addEnd(new Kid("4554544554","Sergio Núñez"
                , (byte) 19));

    }

    public String invert(){
        kids.invert();
        return "Invertido";
    }
    public String changeExt(){
        kids.changeExt();
        return "Invertidos";
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

}
