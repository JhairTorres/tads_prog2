package com.tads_prog2.controller;

import com.tads_prog2.controller.dto.ResponseDTO;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.Node;
import com.tads_prog2.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.getKids().getHead(),null), HttpStatus.OK);
    }
    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.changeExt(),null),HttpStatus.OK);
    }
    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.invert(),null),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addEnd(@RequestBody Kid kid){
        // irian las validaciones de la entrada
        listSEService.getKids().addEnd(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }
    @PostMapping(path = "/tostart")
    public ResponseEntity<String> addToStart(@RequestBody Kid kid){
        // irian las validaciones de la entrada
        listSEService.getKids().addToStart(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }
   @DeleteMapping(path="/deletepos/{posicion}")
   public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int pos){
       String output = listSEService.deletePos(pos);

       if (output.equals("Fuera de rango")){
           List<String> errors = new ArrayList<>();
           errors.add(output);
           return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                   null,errors),HttpStatus.OK);
       } else {
           return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                   output,null),HttpStatus.OK);
       }
   }
    @DeleteMapping(path="/deleteid/{identification}")
    public  ResponseEntity<String> deleteid(@PathVariable String identification) {
        // irian las validaciones de la entrada
        listSEService.getKids().deleteId(identification);
        return new ResponseEntity<String>(
                "borrado exitosamente", HttpStatus.OK);
    }
    @PutMapping(path="/updateinpos/{posicion}")
    public ResponseEntity<String> updateInPos(@PathVariable int posicion,@RequestBody Kid kid){
        listSEService.getKids().updateInPos(posicion,kid);
        return new ResponseEntity<String>(
                "actualizado exitosamente", HttpStatus.OK);
    }

}
