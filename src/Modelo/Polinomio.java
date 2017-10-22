package Modelo;

public class Polinomio extends LDLRC {

    /**
     * Constructor vacío.
     */
    public Polinomio() {
    }

    /**
     * Constructor de una constante
     * @param base Double que será la base del Polinomio
     */
    public Polinomio(Double base) {
        Termino t = new Termino(base, 0);
        this.insertar(t, this.primerNodo());
    }   

    /**
     * Constructor que inicializa el Polinomio con el String correspondiente al del parametro s
     * @param s String con el Polinomio que se quiere representar de la forma axn+bxn+c
     */
    public Polinomio(String s) {//Se supone que el texto s está bien ingresado.
        if (s.charAt(0) == '+') {//Validación del signo al principio
            s = s.substring(1);
        }
        s = s + "+";//Agregarle un criterio de parada al final
        int n = s.length();
        String numero = "";
        String baseTexto = "";
        String exponenteTexto = "";
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'x' | 'X':
                    //Validación por si la X no está precedida por un número por defecto se le pone base 1.
                    if (numero.equalsIgnoreCase("") | numero.equalsIgnoreCase("+") | numero.equalsIgnoreCase("-")) {
                        baseTexto = numero + "1";
                    } else {
                        baseTexto = numero;
                    }
                    numero = "";
                    //Validación si es solamente la x no lleva exponente, entonces por defecto lleva exponente 1
                    if (s.charAt(i + 1) == '+' | s.charAt(i + 1) == '-' | s.charAt(i + 1) == ' ') {
                        numero = "1";
                    }
                    break;                
                case  '+' : // '+' | '-'  No entra al ciclo uniendo el + y el - 
                    if (i != 0) {//Validar cuando está empezando el recorrido del string con un signo
                        Double base;
                        Integer exponente;
                        if (baseTexto.equals("")) {//Validar si es una constante
                            base = Double.parseDouble(numero);
                            exponente = 0;
                        } else {
                            exponenteTexto = numero;
                            base = Double.parseDouble(baseTexto);
                            exponente = Integer.parseInt(exponenteTexto);
                        }

                        Termino termino = new Termino(base, exponente);
                        this.insertarTermino(termino);
                    }
                    numero = "" + c;
                    baseTexto = "";
                    exponenteTexto = "";
                    break;
                case '-':
                    if (i != 0) {//Validar cuando está empezando el recorrido del string con un signo
                        Double base;
                        Integer exponente;
                        if (baseTexto.equals("")) {//Validar si es una constante
                            base = Double.parseDouble(numero);
                            exponente = 0;
                        } else {
                            exponenteTexto = numero;
                            base = Double.parseDouble(baseTexto);
                            exponente = Integer.parseInt(exponenteTexto);
                        }

                        Termino termino = new Termino(base, exponente);
                        this.insertarTermino(termino);
                    }
                    numero = "" + c;
                    baseTexto = "";
                    exponenteTexto = "";
                    break;
                    case ' ':
                    break;
                default:
                    numero = numero + c;
                    break;
            }
        }
    }
    
    /**
     * Inserta un Nodo con el Termino t en el lugar correspondiente de forma descendente
     * @param t Termino que tendrá el nodo en el campo de dato.
     */
    public void insertarTermino(Termino t){
        NodoDoble x = null;
        Termino tx = new Termino();
        NodoDoble s = new NodoDoble(t);
        //Ingresar el termino si su grado es mayor al del polinomio(Al principio)
        if(t.retornaExponente()>this.retornaGrado()){
            this.conectar(s, x);
            return;
        }
        else {
            x = this.primerNodo();
            while (!this.finRecorrido(x)) {
                tx = (Termino) x.retornaDato();
                /*Valida si ya hay un termino en la lista con el mismo grado que
                El termino que se quiere ingresar, entonces suma ambas bases
                del termino*/
                if (tx.retornaExponente() == t.retornaExponente()) {
                    tx.asignaBase(tx.retornatBase() + t.retornatBase());
                    x.asignaDato(tx);
                    return;
                }
                // Inserción del termino en el medio
                else{
                    if(tx.retornaExponente()<t.retornaExponente()){
                        this.conectar(s, x.retornaLI());
                        return;
                    }
                }
                x = x.retornaLD();
            }            
        }
        //Inserción al final del termino
        this.conectar(s, this.ultimoNodo());
    }

    /**
     * @return un String que representa el polinomio de la lista 
     */
    public String imprimirPolinomio() {
        String polinomio = "";
        NodoDoble p = this.primerNodo();
        while (!this.finRecorrido(p)) {//Se recorre la lista del Polinomio
            Termino t = (Termino) p.retornaDato();
            if (t.retornatBase() == 0) {//Si la base es cero no anexa nada al String                
            } else {
                if (t.retornatBase() >= 0) {//Se valida si la base es positiva agregar un signo '+'
                    polinomio = polinomio + "+";
                }
                polinomio = polinomio + t.retornatBase().toString();//Se anexa la base al String
                if (t.retornaExponente() != 0) {//Se valida si la base es una constante (Si su exponente es cero)
                    polinomio = polinomio + "x";
                    if (t.retornaExponente() != 1) {//Se valida si la x va acompañada de un exponente  (Si su exponente es 1 solo se le pone la x)
                        polinomio = polinomio + Integer.toString(t.retornaExponente());//Se anexa el exp al String
                    }
                    polinomio = polinomio + " ";
                }
            }

            p = p.retornaLD();
        }
        return (polinomio);
    }

    /**
     * 
     * @param x valor que tomará la x para evaluar el polinomio
     * @return Resultado de evaluar el polinomio con el valor x
     */
    public  Double evaluarPolinomio(Double x) {
        double resultado = 0;
        NodoDoble p = this.primerNodo();
        Termino t = new Termino();
        while (!this.finRecorrido(p)) {//Evalua cada termino de la lista y lo va sumando
            t = (Termino) p.retornaDato();
            resultado = resultado + t.evaluarTermino(x);
            p = p.retornaLD();
        }
        return (resultado);
    }

    /**
     * 
     * @param p Polinomio con el cual se quiere sumar
     * @return Una lista que representa la suma de los dos polinomios
     */
    public Polinomio sumarPolinomios(Polinomio p) {
        Polinomio resultado = new Polinomio();
        Termino t1, t2;
        NodoDoble p1 = this.primerNodo();
        NodoDoble p2 = p.primerNodo();
        while (!this.finRecorrido(p1) & !p.finRecorrido(p2)) {
            t1 = (Termino) p1.retornaDato();
            t2 = (Termino) p2.retornaDato();
            if (t1.retornaExponente() == t2.retornaExponente()) {
                Double base = t1.retornatBase() + t2.retornatBase();
                Integer exponente = t1.retornaExponente();
                Termino t = new Termino(base, exponente);
                NodoDoble termino = new NodoDoble(t);
                resultado.insertar(t, resultado.ultimoNodo());
                p1 = p1.retornaLD();
                p2 = p2.retornaLD();
            } //Por si alguna lista tiene un termino con exponente que el otro no tiene
            else {
                if (t1.retornaExponente() > t2.retornaExponente()) {//Primera lista tiene más terminos
                    Double base = t1.retornatBase();
                    Integer exponente = t1.retornaExponente();
                    Termino t = new Termino(base, exponente);
                    resultado.insertar(t, resultado.ultimoNodo());
                    p1 = p1.retornaLD();
                } else {//segunda lista tiene más terminos
                    Double base = t2.retornatBase();
                    Integer exponente = t2.retornaExponente();
                    Termino t = new Termino(base, exponente);
                    resultado.insertar(t, resultado.ultimoNodo());
                    p2 = p2.retornaLD();
                }
            }
        }
        //Por si algún polinomio tiene más terminos que el otro
        if (p1 != this.primerNodo()) {//Primer polinomio
            while (!this.finRecorrido(p1)) {
                t1 = (Termino) p1.retornaDato();
                resultado.insertar(t1, resultado.ultimoNodo());
                p1 = p1.retornaLD();
            }
        }
        if (p2 != p.primerNodo()) {//Segundo polinomio
            while (!p.finRecorrido(p2)) {
                t2 = (Termino) p2.retornaDato();
                resultado.insertar(t2, resultado.ultimoNodo());
                p2 = p2.retornaLD();
            }
        }
        return (resultado);
    }

    /**
     * @param poli2 Segundo Polinomio con el que se va a multiplicar
     * @return Una lista Polinomio con el resultado de la multiplicación de los polinomios
     */   
    public Polinomio MultiplicarPolinomios(Polinomio poli2){
        Polinomio resultado = new Polinomio();
        if(this.tamaño()<poli2.tamaño()){//Valida si una lista tiene más datos que la otra
            return(poli2.MultiplicarPolinomios(this));
        }
        NodoDoble p = this.primerNodo();        
        while(!this.finRecorrido(p)){//Recorre el primer polinomio
            NodoDoble q = poli2.primerNodo();
            while(!poli2.finRecorrido(q)){//Recorre el segundo polinomio
                //Multiplica cada monomio de la segunda lista por cada monomio
                //De la primera lista y agrega el resultado a una tercera lista
                Termino tp = (Termino) p.retornaDato();
                Termino tq = (Termino) q.retornaDato();
                Double base = tp.retornatBase()*tq.retornatBase();
                int exponente = tp.retornaExponente()+tq.retornaExponente();
                Termino tResultado = new Termino(base,exponente);
                resultado.insertarTermino(tResultado);
                q = q.retornaLD();
            }
            p = p.retornaLD();
        }
        return(resultado);
    }

    /**
     * @return retonar un entero con el máximo exponente del polinomio 
     */
    public Integer retornaGrado() {//Como la lista siempre está ordenada es válido este algoritmo
        if(this.esVacia()){
            return(0);
        }
        NodoDoble p = this.primerNodo();
        Termino t = (Termino) p.retornaDato();
        return (t.retornaExponente());
    }

    /**
     * @return Una lista(polinomio) que representa la derivada del polinomio 
     */
    public Polinomio primeraDerivada() {
        Polinomio resultado = new Polinomio();
        NodoDoble p = this.primerNodo();
        while (!this.finRecorrido(p)) {//Deriva cada monomio del Polinomio
            Termino t = (Termino) p.retornaDato();
            Double base = t.retornatBase() * t.retornaExponente();
            Integer exponente = t.retornaExponente() - 1;
            if (base != 0) {
                Termino tResultado = new Termino(base, exponente);
                resultado.insertarTermino(tResultado);
            }            
            p = p.retornaLD();
        }
        return (resultado);
    }

    /**
     * @param n un entero que es la cantidad de veces que se quiere derivar 
     * el polinomio
     * @return Una lista(polinomio) que representa la derivada enesima
     * del polinomio
     */
    public Polinomio enesimaDerivada(int n) {
        Polinomio resultado = new Polinomio();
        if (n >= this.retornaGrado()) {//Valida si la derivada final da cero
            Termino t = new Termino(0.0, 0);
            resultado.insertarTermino(t);
            return (resultado);
        }
        for (int i = 0; i < n; i++) {//Hace la derivada n veces
            if (i == 0) {
                resultado = this.primeraDerivada();
            } else {
                resultado = resultado.primeraDerivada();
            }
        }
        return (resultado);
    }

    /**
     * @return Una lista(polinomio) que representa la integral definida
     * del polinomio
     */
    public Polinomio integralIndefinida() {
        Polinomio resultado = new Polinomio();
        NodoDoble p = this.primerNodo();
        while (!this.finRecorrido(p)) {//Integra cada monomio del polinomio
            Termino t = (Termino) p.retornaDato();
            Integer exponente = t.retornaExponente() + 1;
            if (exponente == 0) {
                return (null);//returna null para generar la excepción de división entre cero
            }
            Double base = t.retornatBase() / (t.retornaExponente() + 1);
            Termino tResultado = new Termino(base, exponente);
            resultado.insertarTermino(tResultado);
            p = p.retornaLD();
        }
        return (resultado);
    }
    
    /**
     * @param n1 Double con el valor del intervalo superior
     * @param n2 Double con el valor del intervalo inferior
     * @return Double con el resultado de la Integral Definida en el intervalo[n1,n2]
     */
    public Double integralDefinifa(Double n1,Double n2){
        Double resultado;
        Polinomio p = this.integralIndefinida();       
        resultado = p.evaluarPolinomio(n2)-p.evaluarPolinomio(n1);
        return(resultado);
    }
    
    /**
     * 
     * @param factor Polinomio con el cual se va a buscar el favtor
     * @return Retorna verdadero si el polinomio del parametro es un factor del this
     */
    public boolean factorPolinomio(Polinomio factor){
        boolean b = false;
        Termino t1 = (Termino) factor.primerNodo().retornaDato();
        Termino t2 = (Termino) factor.ultimoNodo().retornaDato();
        Double evaluado = (t2.retornatBase()*-1)/t1.retornatBase();//Evaluación
        Double evaluar = this.evaluarPolinomio(evaluado);
        if(evaluar==0){
            b = true;
        }        
        return(b);
    }
    
    /**
     * Elimina del this el monomio representado en el parametro
     * @param monomio Polinomio que representa el monomio que se quiere eliminar
     */
    public void eliminarMonomio(Polinomio monomio ){
        if(monomio.retornaGrado()>this.retornaGrado()){
            return;
        }
        NodoDoble p = this.primerNodo();
        NodoDoble q = monomio.primerNodo();
        Termino tq = (Termino)q.retornaDato();
        while(!this.finRecorrido(p)){
            Termino tp = (Termino) p.retornaDato();
            Double base1 = tp.retornatBase();
            Double base2 = tq.retornatBase();
            int int1 = tp.retornaExponente();
            int int2 = tq.retornaExponente();
            if(base1.equals(base2) & int1==int2){//Evaluación de la igualdad de los monomios
                this.desconectar(p);
                return;
            }
            p = p.retornaLD();
        }
        
    }
}
