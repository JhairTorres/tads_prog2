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
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
            }
            NodeDE newNode = new NodeDE(kid);
            tempNode.setNext(newNode);
            newNode.setPrevious(tempNode);
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
            this.head=newNode;
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
            int posmale=1;
            int posfemale=2;

            while (temp != null) {
                if (temp.getData().getGender().equals("Male")) {
                    sortedList.addPosDE(posmale,temp.getData());
                } else if (temp.getData().getGender().equals("Female")) {
                    sortedList.addPosDE(posfemale,temp.getData());
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
    public void deleteIdDE(String id) throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacia");
        } else if (this.head.getData().getIdentification().equals(id)) {
            //Nueva cabeza
            this.head = this.head.getNext();
            this.head.setPrevious(null);
            this.size--;
        }
        else{
            NodeDE temp = this.head;
            while(temp!=null) {
                if (temp.getData().getIdentification().equals(id)) {
                    NodeDE previous = temp.getPrevious();
                    previous.setNext(temp.getNext());
                    temp.getNext().setPrevious(previous);
                }
                temp = temp.getNext();
            }
            this.size--;
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

            while (cont!=pos) {
                temp = temp.getNext();
                cont++;
            }
            NodeDE previous = temp.getPrevious();
            temp.setPrevious(null);
            previous.setNext(temp.getNext());
        }

        this.size--;
    }
}


