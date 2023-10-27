package com.tads_prog2.model;

import com.tads_prog2.controller.dto.*;
import com.tads_prog2.exceptions.KidsException;
import lombok.Data;
import com.tads_prog2.model.NodeDE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ListDE {
    private NodeDE head;
    private int size;
    private List<String> Cities;

    // Agregar al final
    public void addKidToEndDE(Kid newkid) {
        NodeDE newNode = new NodeDE(newkid);
        if (this.head == null) {
            this.head = newNode;
        } else {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }
        this.size++;


    }

    // Agregar al principio
    public void addToStartDE(Kid kid) {
        if (this.head == null) {
            this.head = new NodeDE(kid);
        } else {
            NodeDE newNode = new NodeDE(kid);
            newNode.setNext(this.head);
            newNode.setPrevious(newNode);
            this.head.setPrevious(newNode);
            this.head = newNode;
        }
        this.size++;
    }

    // Agregar en una posición
    public void addPosDE(int posicion, Kid kid) {
        if (posicion == 1) {
            this.addToStartDE(kid);

        } else if (posicion > this.size) {
            this.addKidToEndDE(kid);

        } else if (posicion <= this.size) {
            NodeDE temp = this.head;
            int posAct = 1;
            while (posAct < posicion - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeDE newNode = new NodeDE(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }

    }

    // Invertir la lista
    public void invertDE() {
        if (this.head != null) {
            ListDE listcopy = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listcopy.addToStartDE(temp.getData());
                temp = temp.getNext();
            }
            this.head = listcopy.getHead();
        }
    }

    // Cambiar los extremos
    public void changeExtDE() {
        if (this.head != null && this.size > 1) {
            NodeDE firstNode = this.head;
            NodeDE lastNode = this.head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            Kid tempKid = firstNode.getData();
            firstNode.setData(lastNode.getData());
            lastNode.setData(tempKid);
        }
    }

    // Ordenar por género
    public void sortbyGenderDE() throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacia");
        } else if (this.head.getNext() == null) {
            throw new KidsException("Insuficientes elementos");
        } else {
            ListDE sortedList = new ListDE();
            NodeDE temp = this.head;
            int posmale = 1;
            int posfemale = 2;

            while (temp != null) {
                if (temp.getData().getGender().equals("Male")) {
                    sortedList.addPosDE(posmale, temp.getData());
                } else if (temp.getData().getGender().equals("Female")) {
                    sortedList.addPosDE(posfemale, temp.getData());
                }
                temp = temp.getNext();
            }

            this.head = sortedList.getHead();
        }
    }

    // Eliminar en una posición
    public void deletePosDE(int posicion) throws KidsException {
        if (posicion <= 0 || posicion > this.size) {
            throw new KidsException("La posición está fuera de rango.");
        }

        if (posicion == 1) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else {
            NodeDE temp = this.head;
            int contador = 1;

            while (contador < posicion) {
                temp = temp.getNext();
                contador++;
            }

            NodeDE prevNode = temp.getPrevious();
            NodeDE nextNode = temp.getNext();
            prevNode.setNext(nextNode);

            if (nextNode != null) {
                nextNode.setPrevious(prevNode);
            }
        }

        this.size--;
    }

    // Eliminar por identificación
    public void deleteIdDE(String id) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        } else if (this.head.getData().getIdentification().equals(id)) {
            // El nodo a eliminar es el primer nodo
            this.head = this.head.getNext();
            if (this.head != null) {
                // Asegurarse de que la nueva cabeza tenga su puntero "previous" a null
                this.head.setPrevious(null);
            }
            this.size--;
        } else {
            NodeDE temp = this.head;
            while (temp != null) {
                if (temp.getData().getIdentification().equals(id)) {
                    NodeDE previous = temp.getPrevious();
                    NodeDE nextNode = temp.getNext();
                    previous.setNext(nextNode);
                    if (nextNode != null) {
                        // Asegurarse de que el nodo siguiente tenga su puntero "previous" actualizado
                        nextNode.setPrevious(previous);
                    }
                    this.size--;
                    break; // Importante para evitar eliminar múltiples nodos con el mismo ID
                }
                temp = temp.getNext();
            }
        }
    }

    public List<Kid> getAll() throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacia");
        } else {
            List<Kid> kids = new ArrayList<>();
            NodeDE temp = this.head;
            while (temp != null) {
                kids.add(temp.getData());
                temp = temp.getNext();
            }
            return kids;
        }
    }

    public void deleteKamikaze(int pos) throws KidsException {
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 1) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else {
            NodeDE temp = this.head;
            int cont = 1;

            while (cont != pos) {
                temp = temp.getNext();
                cont++;
            }
            NodeDE previous = temp.getPrevious();
            NodeDE nextNode = temp.getNext();
            previous.setNext(nextNode);
            if (nextNode != null) {
                nextNode.setPrevious(previous);
            }
        }

        this.size--;
    }
    public List<String> getgender() {
        NodeDE temp = this.head;
        List<String> genders = new ArrayList<>();

        while (temp != null) {
            String gender = String.valueOf(temp.getData().getGender());
            if (!genders.contains(genders)) {
                genders.add(gender);
            }
            temp = temp.getNext();
        }

        return genders;
    }


    public List<DataReportListaDEDTO> BrothersReport() throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        } else {
            List<String> genders = this.getgender();
            List<DataReportListaDEDTO> finalReport = new ArrayList<>();

            for (String gender : genders) {
                int totalCityCount = 0; // CUENTA EL TOTAL DE PERSONAS EN LA CIUDAD
                int maleCount = 0; // CUENTA LOS HOMBRES EN ESCUELAS
                int femaleCount = 0; // CUENTA LAS MUJERES EN ESCUELAS
                int siHermanosHombres = 0;
                int siHermanosMujeres = 0;
                NodeDE temp = this.head;

                while (temp != null) {
                    if (temp.getData().getGender().equals("male")) {
                        if (temp.getData().getCityname().getCity().equals("city")) {
                            if (temp.getData().getBrothers().equals("si")) {
                                siHermanosHombres++;
                            }
                            totalCityCount++;
                            maleCount++;
                        }
                    } else if (temp.getData().getGender().equals("female")) {
                        if (temp.getData().getCityname().getCity().equals("city")) {
                            if (temp.getData().getBrothers().equals("si")) {
                                siHermanosMujeres++;
                            }
                            totalCityCount++;
                            femaleCount++;
                        }
                    }
                    temp = temp.getNext();
                }

                // Lista de hermanos para hombres
                List<SiblingStructureListDEDTO> brothersMales = new ArrayList<>();
                brothersMales.add(new SiblingStructureListDEDTO("Hay hermanos", siHermanosHombres));

                // Lista de hermanos para mujeres
                List<SiblingStructureListDEDTO> brothersFemales = new ArrayList<>();
                brothersFemales.add(new SiblingStructureListDEDTO(femaleCount,siHermanosMujeres));

                // Divide por género
                List<CityStructureDTO> city = new ArrayList<>();
                city.add(new CityStructureDTO("Male", brothersMales, maleCount));
                city.add(new CityStructureDTO("Female", brothersFemales, femaleCount));

                // Objeto para el informe final
                finalReport.add(new DataReportListaDEDTO(gender, city,totalCityCount));
            }

            return finalReport;
        }
    }


}


