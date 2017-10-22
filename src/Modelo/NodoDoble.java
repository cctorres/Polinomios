package Modelo;

public class NodoDoble {
    
    private Object Dato;
    private NodoDoble Derecha;
    private NodoDoble Izquierda;
    
    
    /**
     * Constructor vacio
     */
    public NodoDoble (){
        
    }
    
    /**
     * Constructor
     * @param d Objecto el cuál se insertará en el campo dato del nodo
     */
    public  NodoDoble(Object d) {
            Dato=d;
            Derecha=null;
            Izquierda=null;        
    }   
    
    /**
     * @return El NodoDoble de la liga derecha del Nodo
     */
    public NodoDoble retornaLD() {
             return Derecha;
    }

    /**
     * Modifica la liga derecha del nodo
     * @param Derecha NodoDoble el cual será la nueva liga derecha del nodo
     */
    public void asignaLD(NodoDoble Derecha) {
        this.Derecha = Derecha;
    }

    /**
     * @return El NodoDoble de la liga izquierda del Nodo
     */
    public NodoDoble retornaLI() {
        return Izquierda;
    }

     /**
     * Modifica la liga izquierda del nodo
     * @param Izquierda NodoDoble el cual será la nueva liga izquierda del nodo
     */
    public void asignaLI(NodoDoble Izquierda) {
        this.Izquierda = Izquierda;
    }

    /**
     * @return El dato que posee el NodoDoble
     */
    public Object retornaDato() {
        return Dato;
    }

     /**
     * Modifica el dato que posee el nodo
     * @param Dato Objeto el cual será el nuevo dato del nodo
     */
    public void asignaDato(Object Dato) {
        this.Dato = Dato;
    }  
}