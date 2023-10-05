package com.tads_prog2.controller;

import com.tads_prog2.controller.dto.ResponseDTO;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.ListSE;
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


    //getall #1
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    //Add end #2
    @PostMapping(path = "/toend")
    public ResponseEntity<String> addEnd(@RequestBody Kid kid){
        // irian las validaciones de la entrada
        listSEService.getKids().addEnd(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }

    //Add start #3
    @PostMapping(path = "/tostart")
    public ResponseEntity<String> addToStart(@RequestBody Kid kid){
        // irian las validaciones de la entrada
        listSEService.getKids().addToStart(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }

    //Add Pos #4
    @PostMapping(path="/topos/{pos}")
    public ResponseEntity<ResponseDTO> insertInPos(@PathVariable int pos ,@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.insertInPos(pos, kid),null),HttpStatus.OK);
    }

    //Invert #5
    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.invert(),null),HttpStatus.OK);
    }


    //Change ext #6
    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.changeExt(),null),HttpStatus.OK);
    }


    //Delete pos#8
   @DeleteMapping(path="/deletepos/{posicion}")
   public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int posicion){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.deletePos(posicion),null),HttpStatus.OK);
   }



   //Delete Id #9
    @DeleteMapping(path="/deleteid/{identification}")
    public  ResponseEntity<ResponseDTO> deleteid(@PathVariable String identification) {
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.deleteId(identification),null),HttpStatus.OK);
    }


    //Update in Pos #10
    @PutMapping(path="/updateinpos/{posicion}")
    public ResponseEntity<ResponseDTO> updateInPos(@PathVariable int posicion,@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.updateInPos(posicion, kid),null),HttpStatus.OK);
    }

}
