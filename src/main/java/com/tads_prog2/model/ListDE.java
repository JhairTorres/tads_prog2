package com.tads_prog2.model;

import com.tads_prog2.controller.dto.DataStructureDTO;
import com.tads_prog2.controller.dto.GenderStructureDTO;
import com.tads_prog2.exceptions.KidsException;
import lombok.Data;
import com.tads_prog2.model.NodeDE;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    // Agregar al final
    public void addKidToEndDE(Kid kid) {
        if (this.head == null) {
            this.head = new NodeDE(kid);
        } else {
            NodeDE tempNode = this.head;
            while (tempNode.getPrevious() != null) {
                tempNode = tempNode.getPrevious();
            }
            NodeDE newNode = new NodeDE(kid);
            newNode.setNext(tempNode);
            tempNode.setPrevious(newNode);
        }
        this.size++;
    }
    // Agregar al principio
    public void addToStartDE(Kid kid) {
        if (this.head == null) {
            this.head = new NodeDE(kid);
        } else {
            NodeDE newNode = new NodeDE(kid);
            newNode.setPrevious(this.head);
            this.head.setNext(newNode);
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
                temp = temp.getPrevious();
                posAct++;
            }
            NodeDE newNode = new NodeDE(kid);
            newNode.setPrevious(temp);
            newNode.setNext(temp.getPrevious());
            temp.getPrevious().setNext(newNode);
            temp.setPrevious(newNode);
            this.size++;
        }
    }

    // Invertir la lista
    public void invertDE() {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                NodeDE nextNode = temp.getNext();
                temp.setNext(temp.getPrevious());
                temp.setPrevious(nextNode);
                temp = nextNode;
            }
            this.head = temp.getPrevious();
        }
    }

    // Cambiar los extremos
    public void changeExtDE() {
        if (this.head != null && this.size > 1) {
            NodeDE firstNode = this.head;
            NodeDE lastNode = this.head;
            while (lastNode.getPrevious() != null) {
                lastNode = lastNode.getPrevious();
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
        } else if (this.head.getPrevious() == null) {
            throw new KidsException("Insuficientes elementos");
        } else {
            ListDE sortedList = new ListDE();
            NodeDE temp = this.head;

            while (temp != null) {
                if (temp.getData().getGender().equals("Male")) {
                    sortedList.addKidToEndDE(temp.getData());
                } else if (temp.getData().getGender().equals("Female")) {
                    sortedList.addToStartDE(temp.getData());
                }
                temp = temp.getPrevious();
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
            this.head = this.head.getPrevious();
            this.head.setNext(null);
        } else {
            NodeDE temp = this.head;
            int contador = 1;

            while (contador < posicion) {
                temp = temp.getPrevious();
                contador++;
            }

            NodeDE prevNode = temp.getPrevious();
            NodeDE nextNode = temp.getNext();
            prevNode.setPrevious(nextNode);

            if (nextNode != null) {
                nextNode.setNext(prevNode);
            }
        }

        this.size--;
    }

    // Eliminar por identificación
    public void deleteIdDE(String id) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista Vacia");
        } else {
            NodeDE temp = this.head;

            while (temp != null) {
                if (temp.getData().getIdentification().equals(id)) {
                    NodeDE prevNode = temp.getPrevious();
                    NodeDE nextNode = temp.getNext();

                    if (prevNode != null) {
                        prevNode.setPrevious(nextNode);
                    } else {
                        this.head = nextNode;
                    }

                    if (nextNode != null) {
                        nextNode.setNext(prevNode);
                    }

                    this.size--;
                }
                temp = temp.getPrevious();
            }
        }
    }

    // Actualizar en una posición
    public void updateInPosDE(int posicion, Kid kid) {
        if (this.head != null && posicion > 0 && posicion <= this.size) {
            NodeDE temp = this.head;
            int currentPos = 1;

            while (currentPos < posicion) {
                temp = temp.getPrevious();
                currentPos++;
            }

            temp.setData(kid);
        }
    }

    // Obtener las ciudades
    public List<String> getCitiesDE() {
        NodeDE temp = this.head;
        List<String> cities = new ArrayList<>();

        while (temp != null) {
            String city = temp.getData().getCityname().getCity();
            if (!cities.contains(city)) {
                cities.add(city);
            }
            temp = temp.getPrevious();
        }

        return cities;
    }

    // Generar un informe de ciudades
    public List<DataStructureDTO> cityReportDE() throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        } else {
            List<String> cities = this.getCitiesDE();
            List<DataStructureDTO> citiesReport = new ArrayList<>();

            for (String city : cities) {
                int totalCityCount = 0;
                int maleCount = 0;
                int femaleCount = 0;
                NodeDE temp = this.head;

                while (temp != null) {
                    if (temp.getData().getCityname().getCity().equals(city)) {
                        if (temp.getData().getGender().equals("Male")) {
                            maleCount++;
                        } else if (temp.getData().getGender().equals("Female")) {
                            femaleCount++;
                        }
                        totalCityCount++;
                    }
                    temp = temp.getPrevious();
                }

                GenderStructureDTO cityFemales = new GenderStructureDTO("Female", femaleCount);
                GenderStructureDTO cityMales = new GenderStructureDTO("Males", maleCount);

                List<GenderStructureDTO> genders = new ArrayList<>();
                genders.add(cityFemales);
                genders.add(cityMales);

                DataStructureDTO finalCityReport = new DataStructureDTO(city, totalCityCount, genders);
                citiesReport.add(finalCityReport);
            }

            return citiesReport;
        }
    }
    public List<Kid> getAll() throws KidsException{
        if (this.head==null){
            throw new KidsException("Lista vacia");
        }else {
            List<Kid>kids = new ArrayList<>();
            NodeDE temp= this.head;
            while (temp!=null){
                kids.add(temp.getData());
                temp= temp.getNext();
            }
            return kids;
        }
    }
}

