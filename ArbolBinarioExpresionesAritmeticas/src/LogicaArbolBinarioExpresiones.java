/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edrei
 */
public class LogicaArbolBinarioExpresiones {

    NodoArbol raizArbol;

    public LogicaArbolBinarioExpresiones() {
        raizArbol = null;
    }

    /**
     * Constructor que crea el árbol a partir de una cadena de expresión
     * matemática.
     *
     * @param cadena La cadena que representa la expresión matemática.
     */
    public LogicaArbolBinarioExpresiones(String cadena) {
        raizArbol = creaArbolBE(cadena);
    }

    /**
     * Reinicia el árbol, asignando la raíz como nula.
     */
    public void reiniciaArbol() {
        raizArbol = null;
    }

    /**
     * Crea un nuevo nodo en el árbol con el dato especificado.
     *
     * @param dato El dato del nuevo nodo.
     */
    public void creaNodo(Object dato) {
        raizArbol = new NodoArbol(dato);
    }

    /**
     * Crea un subárbol con un operador y dos operandos dados.
     *
     * @param dato2 El operando derecho.
     * @param dato1 El operando izquierdo.
     * @param operador El nodo que representa al operador.
     * @return El nodo raíz del subárbol creado.
     */
    public NodoArbol creaSubArbol(NodoArbol dato2, NodoArbol dato1, NodoArbol operador) {
        operador.izquierdo = dato1;
        operador.derecho = dato2;
        return operador;
    }

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si el árbol está vacío, false en caso contrario.
     */
    public boolean arbolVacio() {
        return raizArbol == null;
    }

    /**
     * Calcula el número de nodos completos en un subárbol.
     *
     * @param subArbol El subárbol a analizar.
     * @return El número de nodos completos en el subárbol.
     */
    public int nodosCompletos(NodoArbol subArbol) {
        if (subArbol == null) {
            return 0;
        } else {
            if (subArbol.izquierdo != null && subArbol.derecho != null) {
                return nodosCompletos(subArbol.izquierdo) + nodosCompletos(subArbol.derecho) + 1;

            }
            return nodosCompletos(subArbol.izquierdo) + nodosCompletos(subArbol.derecho);
        }
    }

    /**
     * Devuleve la cadena en preorden.
     *
     * @param subArbol El subárbol a que ordenara.
     * @param c Cadena vacia.
     * @return La cadena en preorden.
     */
    private String preorden(NodoArbol subArbol, String c) {
        String cadena;
        cadena = "";
        if (subArbol != null) {
            cadena = c + subArbol.dato.toString() + "\n" + preorden(subArbol.izquierdo, c) + preorden(subArbol.derecho, c);
        }
        return cadena;
    }

    /**
     * Devuleve la cadena en orden.
     *
     * @param subArbol El subárbol a que ordenara.
     * @param c Cadena vacia.
     * @return La cadena en orden.
     */
    private String inorden(NodoArbol subArbol, String c) {
        String cadena;
        cadena = "";
        if (subArbol != null) {
            cadena = c + inorden(subArbol.izquierdo, c) + subArbol.dato.toString() + "\n" + inorden(subArbol.derecho, c);
        }
        return cadena;
    }

    /**
     * Devuleve la cadena en posorden.
     *
     * @param subArbol El subárbol a que ordenara.
     * @param c Cadena vacia.
     * @return La cadena en posorden.
     */
    private String posorden(NodoArbol subArbol, String c) {
        String cadena;
        cadena = "";
        if (subArbol != null) {
            cadena = c + posorden(subArbol.izquierdo, c) + posorden(subArbol.derecho, c) + subArbol.dato.toString() + "\n";
        }
        return cadena;
    }

    /**
     * Genera un recorrido del árbol en el orden especificado.
     *
     * @param opc La opción del recorrido (0: preorden, 1: inorden, 2:
     * posorden).
     * @return La cadena resultante del recorrido.
     */
    public String cadenaOrdenada(int opc) {
        String cadena = "";
        switch (opc) {
            case 0:
                cadena = preorden(raizArbol, cadena);
                break;

            case 1:
                cadena = inorden(raizArbol, cadena);
                break;

            case 2:
                cadena = posorden(raizArbol, cadena);
                break;
        }
        return cadena;
    }

    /**
     * Selecciona una prioridad al caracter aritmetico que ingresa
     *
     * @param opc La prioridad del caracter (^: 30, * o /: 20, + o -: 10).
     * @return La cadena resultante del recorrido.
     */
    private int prioridad(char opc) {
        int p = 100;
        switch (opc) {
            case '^':
                p = 30;
                break;
            case '*':
            case '/':
                p = 20;
                break;
            case '+':
            case '-':
                p = 10;
                break;
            default:
                p = 0;
        }
        return p;
    }

    /**
     * Verifica si el caracter es un operador aritmetico.
     *
     * @param opc El caracter a verificar ('(', ')', '^', '*', '/', '*', '(+' o
     * '-': true).
     * @return true si el caracter es un operador, false en caso contrario.
     */
    private boolean esOperador(char opc) {
        boolean resultado;
        switch (opc) {
            case ')':
            case '(':
            case '^':
            case '*':
            case '/':
            case '+':
            case '-':
                resultado = true;
                break;
            default:
                resultado = false;
        }
        return resultado;
    }

    /**
     * Método privado que crea un árbol binario de expresiones a partir de una
     * cadena de caracteres.
     *
     * @param cadena La cadena que representa la expresión matemática.
     * @return El nodo raíz del árbol binario de expresiones creado.
     */
    private NodoArbol creaArbolBE(String cadena) {
        PilaArbolBinarioExpresiones PilaOperadores;
        PilaArbolBinarioExpresiones PilaExpresiones;
        NodoArbol token;
        NodoArbol op1;
        NodoArbol op2;
        NodoArbol op;
        boolean banderaOperando = false;
        PilaOperadores = new PilaArbolBinarioExpresiones();
        PilaExpresiones = new PilaArbolBinarioExpresiones();
        char caracterEvaluado;
        for (int i = 0; i < cadena.length(); i++) {
            caracterEvaluado = cadena.charAt(i);
            token = new NodoArbol(caracterEvaluado);
            if (!esOperador(caracterEvaluado)) {
                if (!banderaOperando) {
                    banderaOperando = true;
                    PilaExpresiones.insertar(token);
                } else {
                    String aux = PilaExpresiones.quitar().dato.toString();
                    aux = aux + caracterEvaluado;
                    token = new NodoArbol(aux);
                    PilaExpresiones.insertar(token);
                }
            } else {
                banderaOperando = false;
                switch (caracterEvaluado) {
                    case '(':
                        PilaOperadores.insertar(token);
                        break;
                    case ')':
                        while (!PilaOperadores.pilaVacia() && !PilaOperadores.topePila().dato.equals('(')) {
                            op2 = PilaExpresiones.quitar();
                            op1 = PilaExpresiones.quitar();
                            op = PilaOperadores.quitar();
                            op = creaSubArbol(op2, op1, op);
                            PilaExpresiones.insertar(op);
                        }
                        PilaOperadores.quitar();
                        break;
                    default:
                        while (!PilaOperadores.pilaVacia() && prioridad(caracterEvaluado) <= prioridad(PilaOperadores.topePila().dato.toString().charAt(0))) {
                            op2 = PilaExpresiones.quitar();
                            op1 = PilaExpresiones.quitar();
                            op = PilaOperadores.quitar();
                            op = creaSubArbol(op2, op1, op);
                            PilaExpresiones.insertar(op);
                        }
                        PilaOperadores.insertar(token);
                }
            }
        }
        while (!PilaOperadores.pilaVacia()) {
            op2 = PilaExpresiones.quitar();
            op1 = PilaExpresiones.quitar();
            op = PilaOperadores.quitar();
            op = creaSubArbol(op2, op1, op);
            PilaExpresiones.insertar(op);
        }
        op = PilaExpresiones.quitar();
        return op;
    }

    /**
     * Evalúa la expresión matemática representada por el árbol y devuelve su
     * resultado.
     *
     * @return El resultado de la evaluación de la expresión.
     */
    public double EvaluaExpresion() {
        return evalua(raizArbol);
    }

    /**
     * Evalúa la expresión matemática representada por el árbol y devuelve su
     * resultado.
     *
     * @param subArbol El sub arbol a evaluar
     * @return El resultado de la evaluación del sub arbol.
     */
    private double evalua(NodoArbol subArbol) {
        double acumulado = 0;
        if (!esOperador(subArbol.dato.toString().charAt(0))) {
            return Double.parseDouble(subArbol.dato.toString());
        } else {
            switch (subArbol.dato.toString().charAt(0)) {
                case '^':
                    acumulado = acumulado + Math.pow(evalua(subArbol.izquierdo), evalua(subArbol.derecho));
                    break;
                case '*':
                    acumulado = acumulado + evalua(subArbol.izquierdo) * evalua(subArbol.derecho);
                    break;
                case '/':
                    acumulado = acumulado + evalua(subArbol.izquierdo) / evalua(subArbol.derecho);
                    break;
                case '+':
                    acumulado = acumulado + evalua(subArbol.izquierdo) + evalua(subArbol.derecho);
                    break;
                case '-':
                    acumulado = acumulado + evalua(subArbol.izquierdo) - evalua(subArbol.derecho);
                    break;
            }
        }
        return acumulado;
    }
}
