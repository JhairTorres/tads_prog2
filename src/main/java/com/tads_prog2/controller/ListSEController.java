package com.tads_prog2.controller;

import com.tads_prog2.controller.dto.ResponseDTO;
import com.tads_prog2.exceptions.KidsException;
import com.tads_prog2.model.Kid;
import com.tads_prog2.model.ListSE;
import com.tads_prog2.model.Node;
import com.tads_prog2.service.ListSEService;
import com.tads_prog2.model.Node;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping
    public ResponseEntity<Node> getAll(){
        return new ResponseEntity<Node>(
                listSEService.getKids().getHead(), HttpStatus.OK
        );
    }
    @PostMapping
    public ResponseEntity<String> addEnd(@Valid @RequestBody Kid kid){
        // irian las validaciones de la entrada
        listSEService.getKids().addKidToEnd(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }

    @PostMapping(path = "/tostart")
    public ResponseEntity<String> addToStart(@Valid @RequestBody Kid kid){
        // irian las validaciones de la entrada
        listSEService.getKids().addToStart(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }
    @PostMapping(path="/addposition/{posicion}")
    public ResponseEntity<String> addPos( @Valid @RequestBody Kid kid, @PathVariable int posicion ){
        if (posicion<1){
            return new ResponseEntity<>("Posicion no valida",HttpStatus.BAD_REQUEST);
        }
        listSEService.getKids().addPos(posicion, kid);
        return new ResponseEntity<>(
                "Adicionado exitosamente",HttpStatus.OK
        );
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.invert(),null),HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExt(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.changeExt(),null   ),HttpStatus.OK);

    }
    @GetMapping(path="/sortbygender")
    public ResponseEntity<ResponseDTO> sortbyGender(){
        String output= listSEService.sortbyGender();
        if (output.equals("Lista vacia")||output.equals("Insuficiente elementos")){
            List<String> errors =new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors ),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deletepos/{pos}")
    public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int pos){
        String output = listSEService.deletePos(pos);

        if (output.equals("La posición está fuera de rango.")){
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deleteid/{id}")
    public  ResponseEntity<ResponseDTO> deleteId(@PathVariable String id) {
        String output=listSEService.deleteId(id);
        if (output.equals("Insertado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }else {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null,errors),HttpStatus.OK);
        }

    }
    @PutMapping(path="/updateinpos/{posicion}")
    public ResponseEntity<String> updateInPos(@PathVariable int posicion,@Valid @RequestBody Kid kid){
        listSEService.getKids().updateInPos(posicion,kid);
        return new ResponseEntity<String>(
                "actualizado exitosamente", HttpStatus.OK);
    }

    @GetMapping(path = "/report")
    public ResponseEntity<ResponseDTO> cityReport(){
        Object output = null;
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listSEService.getKids().cityReport(), null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null,errors),HttpStatus.OK);

        }
    }

    @GetMapping(path="/getcities")
    public ResponseEntity<ResponseDTO> getCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.getKids().getCities(), null),HttpStatus.OK);
    }


}