package com.tads_prog2.controller;
import com.tads_prog2.controller.dto.ResponseDTO;
import com.tads_prog2.exceptions.KidsException;
import com.tads_prog2.model.Kid;
import com.tads_prog2.service.ListDEService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDEService.getAll(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @PostMapping(path="/addend")
    public ResponseEntity<String> addEnd(@Valid @RequestBody Kid kid){
        // Aquí irían las validaciones de la entrada
        listDEService.getKids().addKidToEndDE(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }

    @PostMapping(path = "/tostart")
    public ResponseEntity<String> addToStart(@Valid @RequestBody Kid kid){
        // Aquí irían las validaciones de la entrada
        listDEService.getKids().addToStartDE(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }

    @PostMapping(path="/addposition/{posicion}")
    public ResponseEntity<String> addPos(@Valid @RequestBody Kid kid, @PathVariable int posicion){
        if (posicion < 1) {
            return new ResponseEntity<>("Posicion no válida", HttpStatus.BAD_REQUEST);
        }
        listDEService.getKids().addPosDE(posicion, kid);
        return new ResponseEntity<>(
                "Adicionado exitosamente", HttpStatus.OK
        );
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.invertDE(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExt(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.changeExtDE(), null), HttpStatus.OK);
    }

    @GetMapping(path="/sortbygender")
    public ResponseEntity<ResponseDTO> sortbyGender(){
        String output = listDEService.sortbyGenderDE();
        if (output.equals("Lista vacía") || output.equals("Insuficientes elementos")) {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output, null), HttpStatus.OK);
        }
    }

    @DeleteMapping(path="/deletepos/{pos}")
    public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int pos){
        String output = listDEService.deletePosDE(pos);
        if (output.equals("La posición está fuera de rango.")) {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output, null), HttpStatus.OK);
        }
    }

    @DeleteMapping(path="/deleteid/{id}")
    public ResponseEntity<ResponseDTO> deleteId(@PathVariable String id) {
        String output = listDEService.deleteIdDE(id);
        if (output.equals("Insertado")) {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output, null), HttpStatus.OK);
        } else {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null, errors), HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/kamikaze/{pos}")
    public ResponseEntity<ResponseDTO> deleteKamikaze(@PathVariable int pos){
        String output = listDEService.deleteKamikaze(pos);
        if(output.equals("Kamikazeeeee")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @GetMapping(path="/jhairreport")
    public ResponseEntity<ResponseDTO> jhairReport(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDEService.jhair(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());

            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @GetMapping(path="/getcities")
    public ResponseEntity<ResponseDTO> getCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.getCities(),null),HttpStatus.OK);
    }


}

