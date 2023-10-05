package com.tads_prog2.model;

import com.tads_prog2.exceptions.KidsException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
public class ListSE {
    private Node head;
    private int size;

    //Add End #2
    public void addEnd(Kid kid){
        // Verificamos si hay datos
        if(this.head ==null){
            //No hay datos
            this.head = new Node(kid);
        }
        else {
            Node temp = this.head;
            while(temp.getNext()!= null){
                temp = temp.getNext();
            }
            temp.setNext(new Node(kid));
        }
        this.size ++;

    }


    //Add start #3
    public void addToStart(Kid kid){
        if(this.head ==null){
            this.head = new Node(kid);
        }
        else{
            Node newNode = new Node(kid);
            newNode.setNext(this.head);
            this.head = newNode;
        }
        this.size ++;
    }


    //Add Pos #4
    public void insertInPos(int posicion, Kid kid){
        if (posicion == 1){
            this.addToStart(kid);
        } else if (posicion>this.size) {
            this.addEnd(kid);
        } else if (posicion<=this.size) {
            Node temp = this.head;
            int posAct = 1;
            while (posAct<posicion-1){
                temp = temp.getNext();
                posAct++;
            }
            Node newNode = new Node(kid);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            size++;
        }

    }


    //invert #5
    public void invert() {
        //Hay datos?
        if (this.head != null) {
            ///Creo una lista copia
            ListSE listCopy = new ListSE();
            //Lamar a mi ayudante
            Node temp = this.head;
            while (temp != null) {
                listCopy.addToStart(temp.getData());
                temp = temp.getNext();
            }
            // en la lista copia estan invertidos
            // cambio la cabeza
            this.head = listCopy.getHead();
        }
    }


    //Change Ext #6
    public void changeExt(){
        if(size>=2){
            Kid kidtep = this.head.getData();
            Node temp = this.head;
            while (temp.getNext()!=null){
                temp = temp.getNext();
            }
            this.head.setData(temp.getData());
            temp.setData(kidtep);
        }
    }


    //Delete Pos #8
    public void deletePos(int posicion) {
        if (posicion < 0 || posicion >= size) {
            // Verifica si la posición está fuera de rango
            throw new IndexOutOfBoundsException("La posición está fuera de rango.");
        }

        if (posicion == 0) {
            // Si la posición es 0, actualiza la cabeza para eliminar el primer nodo
            head = head.getNext();
        } else {
            Node temp = head;
            int contador = 1;

            // Encuentra el nodo anterior al que se va a eliminar
            while (contador < posicion - 1) {
                temp = temp.getNext();
                contador++;
            }

            // Actualiza las referencias para eliminar el nodo en la posición especificada
            temp.setNext(temp.getNext().getNext());
        }

        size--;
    }


    //Delete ID #9
    public void deleteId(String identification) {
        if (this.head == null) {
            // La lista está vacía, no se puede borrar nada
            return;
        } else if (this.head.getData().getIdentification().equals(identification)) {
            // Si la identificación coincide con la de la cabeza, elimina el primer nodo
            this.head = this.head.getNext();
            size--;
            return;
        }
        Node temp = this.head;
        while (temp.getNext() != null) {
            if (temp.getNext().getData().getIdentification().equals(identification)) {
                // Si encuentra un nodo cuya identificación coincide, actualiza las referencias
                temp.setNext(temp.getNext().getNext());
                this.size--;
                return;
            }
            temp = temp.getNext();
        }
    }


    //Update in Pos #10
    public void updateInPos(int posicion, Kid kid) {
        if(this.head!= null) {
            Node temp = this.head;
            byte currentPos = 0;
            if (posicion > this.size) {
                this.addEnd(kid);
            }
            while(temp.getNext()!=null){
                if(currentPos==posicion){
                    temp.setData(kid);
                }
                temp = temp.getNext();
                currentPos++;
            }
        }
    }



}
