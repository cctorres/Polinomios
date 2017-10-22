package Modelo;


public class LDLRC {
    private NodoDoble primero, ultimo, cabeza;
    
    /**
     * Constructor
     */
    public LDLRC(){       
        NodoDoble nCabeza = new NodoDoble(0);
        cabeza = nCabeza;
        primero = ultimo = null;
    }  
    
    /**
     * @return El primer nodo con dato de la lista
     */
    public NodoDoble primerNodo(){
        return(primero);
    }
    
    /**
     * @return El último nodo con dato de la lista
     */
    public NodoDoble ultimoNodo(){
      return(ultimo);  
    }
    
    /**
     * @return El nodo cabeza de la lista
     */
    public NodoDoble nodoCabeza(){
        return(cabeza);
    }
    
    /**
     * 
     * @param p Un NodoDoble con el que se recorrerá la lista
     * @return verdadero si el nodo p recorrió toda la lista
     */
    public boolean finRecorrido(NodoDoble p){
        return p == null;
    }
    
    /**
     * @return verdadero si la lista no tiene datos
     */
    public boolean esVacia(){
        return primero == null;
    }
    
    /**
     * 
     * @param p NodoDoble del cual se le quiere conocer el anterior
     * @return Retorna el NodoDoble anterior al NodoDoble p del parametro
     */
    public NodoDoble anterior(NodoDoble p){
        return(p.retornaLI());
    }
    
    /**
     * @return Retorna el tamaño de la lista
     */
    public int tamaño(){
        int tam;
        NodoDoble cab = this.nodoCabeza();
        tam = (Integer) cab.retornaDato();
        return (tam);        
    }
    
    /**
     * Inserta un NodoDoble con el String "texto" después del nodo q. 
     * @param o Objeto que será el dato del nodo el cuál se insertará 
     * @param q NodoDoble que será el anterior al nodo que se insertará
     */
    public void insertar(Object o, NodoDoble q){
        NodoDoble x, p;
        x = new NodoDoble(o);
        conectar(x,q);
    }
    
    /**
     * Se inserta el nodo x después del nodo y
     * @param x NodoDoble el cuál se insertará
     * @param y Nodo que será el anterior al nodo que se insertará
     */
    public void conectar(NodoDoble x, NodoDoble y){
        if(y == null){ //Al principio
            cabeza.asignaLD(x);
            x.asignaLI(cabeza);
            x.asignaLD(primero);
            if(primero!=null){
                primero.asignaLI(x);
            }
            else{
                ultimo = x;
            }
            primero = x;
        }
        else{
            if(y.retornaLD()==null){ //Se conecta al final
                y.asignaLD(x);
                x.asignaLI(y);
                ultimo = x;
            }
            else{
                x.asignaLD(y.retornaLD()); //Se conecta al medio
                x.asignaLI(y);
                x.retornaLD().asignaLI(x);
                y.asignaLD(x);
            }
        }
        //Se le suma el contador a la cabeza que llevará el tamaño de la lista
        int cont =  (Integer)cabeza.retornaDato()+1;
        cabeza.asignaDato(cont);
    }
    
    /**
     * Se borra el nodo p de la lista
     * @param p NodoDoble que se desea borrar
     */
    public void borrar (NodoDoble p){
        if(p == null){
            return;
        }
        desconectar(p);
        int cantidad = (Integer) this.nodoCabeza().retornaDato();
        this.nodoCabeza().asignaDato(cantidad-1);
    }
    
    /**
     * Desconecta el nodo p de la lista
     * @param p Nodo el cuál se desea desconectar
     */
    public void desconectar(NodoDoble p){
        if(p.retornaLI()==cabeza){ //Desconectar al principio
            primero = p.retornaLD();
            cabeza.asignaLD(primero);
            if(primero == null){
                ultimo = null;
            }
            else{
                primero.asignaLI(cabeza);
            }
        }
        else{
            if(p.retornaLD()==null){ //Desconectar al final
                ultimo = p.retornaLI();
                ultimo.asignaLD(null);
            }
            else{ //Desconectar en medio
                p.retornaLD().asignaLI(p.retornaLI());
                p.retornaLI().asignaLD(p.retornaLD());
            }
        }
    }

    /**
     * @return un String con los datos de la lista de Izquierda a Derecha 
     */
    public String recorrerID(){
        String recorrido = "";
        NodoDoble p = primerNodo();
        while(!this.finRecorrido(p)){
            recorrido = recorrido.concat((String)p.retornaDato());
            p = p.retornaLD();
        }
    return(recorrido); 
    }
    
    /**
     * @return un String con los datos de la lista de Derecha a Izquierda 
     */
    public String recorrerDI(){
         String recorrido = "";
        NodoDoble p = ultimoNodo();
        while(!this.finRecorrido(p) & p!=this.nodoCabeza()){
            recorrido = recorrido.concat((String)p.retornaDato());
            p = p.retornaLI();
        }
    return(recorrido); 
    }
    
    /**
     * Invierte el orden de los datos de la lista
     */
    public void invertir(){
        NodoDoble z = this.ultimoNodo();
        NodoDoble p = this.primerNodo();
        int i = (Integer)this.nodoCabeza().retornaDato();
        int j = 0;
        while(i<=j){
            NodoDoble q = p.retornaLD();
            q.asignaLI(this.nodoCabeza());
            this.cabeza.asignaLD(q);
            primero = q;
            p.asignaLI(this.ultimoNodo());
            p.asignaLD(null);
            this.ultimoNodo().asignaLD(p);
            ultimo = p;
            p = q;    
            j++;
        }
    }   
    
    /**
     * @return Una LDLRC con los mismo datos y orden que la lista 
     */
    public LDLRC duplicar(){
        LDLRC copia = new LDLRC();
        NodoDoble p = this.primerNodo();
        while(!this.finRecorrido(p)){
            //Recorre la lista que invoca el método nodo por nodo
            String dato = (String) p.retornaDato();
            copia.insertar(dato, copia.ultimoNodo()); //Agrega el nodo a la nueva lista
            p = p.retornaLD();
        }
        return(copia);
    }
}
