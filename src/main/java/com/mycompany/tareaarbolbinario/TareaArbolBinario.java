
package com.mycompany.tareaarbolbinario;

import java.util.Scanner;

// Clase nodo del árbol binario
class Nodo {
    int dato;
    Nodo izquierda, derecha;

    public Nodo(int dato) {
        this.dato = dato;
        izquierda = derecha = null;
    }
}

// Clase árbol binario
class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // Insertar un nodo en el árbol
    public void insertar(int dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private Nodo insertarRecursivo(Nodo raiz, int dato) {
        if (raiz == null) {
            raiz = new Nodo(dato);
            return raiz;
        }
        if (dato < raiz.dato) {
            raiz.izquierda = insertarRecursivo(raiz.izquierda, dato);
        } else if (dato > raiz.dato) {
            raiz.derecha = insertarRecursivo(raiz.derecha, dato);
        }
        return raiz;
    }

    // Recorridos
    public void inOrden(Nodo nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierda);
            System.out.print(nodo.dato + " ");
            inOrden(nodo.derecha);
        }
    }

    public void preOrden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preOrden(nodo.izquierda);
            preOrden(nodo.derecha);
        }
    }

    public void postOrden(Nodo nodo) {
        if (nodo != null) {
            postOrden(nodo.izquierda);
            postOrden(nodo.derecha);
            System.out.print(nodo.dato + " ");
        }
    }

    // Buscar un elemento en el árbol
    public boolean buscar(int dato) {
        return buscarRecursivo(raiz, dato);
    }

    private boolean buscarRecursivo(Nodo raiz, int dato) {
        if (raiz == null) {
            return false;
        }
        if (dato == raiz.dato) {
            return true;
        }
        return dato < raiz.dato
                ? buscarRecursivo(raiz.izquierda, dato)
                : buscarRecursivo(raiz.derecha, dato);
    }

    // Eliminar un nodo
    public void eliminar(int dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private Nodo eliminarRecursivo(Nodo raiz, int dato) {
        if (raiz == null) return null;

        if (dato < raiz.dato) {
            raiz.izquierda = eliminarRecursivo(raiz.izquierda, dato);
        } else if (dato > raiz.dato) {
            raiz.derecha = eliminarRecursivo(raiz.derecha, dato);
        } else {
            // Caso 1: sin hijos
            if (raiz.izquierda == null && raiz.derecha == null) {
                return null;
            }
            // Caso 2: un hijo
            else if (raiz.izquierda == null) {
                return raiz.derecha;
            } else if (raiz.derecha == null) {
                return raiz.izquierda;
            }
            // Caso 3: dos hijos
            raiz.dato = valorMinimo(raiz.derecha);
            raiz.derecha = eliminarRecursivo(raiz.derecha, raiz.dato);
        }
        return raiz;
    }

    private int valorMinimo(Nodo nodo) {
        int min = nodo.dato;
        while (nodo.izquierda != null) {
            min = nodo.izquierda.dato;
            nodo = nodo.izquierda;
        }
        return min;
    }
}

// Clase principal con menú
public class TareaArbolBinario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        int opcion, valor;

        do {
            System.out.println("\n========== MENÚ ==========");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Recorrido Inorden");
            System.out.println("3. Recorrido Preorden");
            System.out.println("4. Recorrido Postorden");
            System.out.println("5. Buscar elemento");
            System.out.println("6. Eliminar nodo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor a insertar: ");
                    valor = sc.nextInt();
                    arbol.insertar(valor);
                    break;
                case 2:
                    System.out.println("Recorrido Inorden:");
                    arbol.inOrden(arbol.raiz);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Recorrido Preorden:");
                    arbol.preOrden(arbol.raiz);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Recorrido Postorden:");
                    arbol.postOrden(arbol.raiz);
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Ingrese valor a buscar: ");
                    valor = sc.nextInt();
                    System.out.println(arbol.buscar(valor) ? "Encontrado" : "No encontrado");
                    break;
                case 6:
                    System.out.print("Ingrese valor a eliminar: ");
                    valor = sc.nextInt();
                    arbol.eliminar(valor);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
