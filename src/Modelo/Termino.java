package Modelo;


public class Termino {
    
    private Double base;
    private int exponente;

    /**
     * Constructor
     * @param base Double que será la base del termino
     * @param exponente Int que será el exponente del termino
     */
    public Termino(Double base, int exponente) {
        this.base = base;
        this.exponente = exponente;
    }
    
    /**
     * Constructor vacio
     */
    public Termino(){
        
    }

    /**
    * @return Retorna un Double con la base del termino 
     */
    public Double retornatBase() {
        return base;
    }

    /**
     * Modifica la base del Termino
     * @param base Double con el valor con el que se modificará la base
     */
    public void asignaBase(Double base) {
        this.base = base;
    }

    /**
     * @return retorna un entero con el exponente del Termino
     */
    public int retornaExponente() {
        return exponente;
    }

    /**
     * Modifica el exponente del Termino
     * @param exponente Entero con el valor con el que se modificará la base
     */
    public void asignaExponente(int exponente) {
        this.exponente = exponente;
    }
    
    /**
     * 
     * @param x Double con el Valor con el que se evaluará el Termino(monomio)
     * @return Retorna un double con la evaluación del término con el Double x
     */
    public Double evaluarTermino(Double x){
        Double resultado = 1.0;
        for(int i=1;i<=this.retornaExponente();i++){//Hace la potencia de x elevada al Exponente del termino
            resultado = resultado*x;
        }
        resultado= resultado*this.retornatBase();//Multiplica la potencia anterior con la base del Termino
        return(resultado);
    }
}
