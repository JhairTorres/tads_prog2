package com.tads_prog2.model;

import com.tads_prog2.exceptions.KidsException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListSE {
    private Node head;
    private int size;

    //Add End
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

    public void deletePos(int posicion) throws KidsException {
        if (posicion < 0 || posicion > size) {
            // Verifica si la posición está fuera de rango
            throw new KidsException("La posición está fuera de rango.");
        }
        if (posicion == 0) {
            // Si la posición es 0, actualiza la cabeza para eliminar el primer nodo
            head = head.getNext();
        } else {
            Node temp = head;
            int contador = 0;
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
}
