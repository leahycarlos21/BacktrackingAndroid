package datos2.tec.com.backtrackingmaze;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    final int N = 7;
    final int nY=8;
    final int nX=5;
    List<nodoCoordenada> myList = new ArrayList<>();

    void mostrarSolucion(int matriz[][]) {
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++)
                System.out.print(" " + matriz[i][j] +
                        " ");
            System.out.println();
        }
        System.out.println("\n");



    }

    private boolean isValido(int matriz[][], int posX, int posY) {
        return (posX >= 0 && posX < nX && posY >= 0 &&
                posY < nY && matriz[posX][posY] == 1);
    }

    boolean busquedaCamino(int matriz[][]) {

        int sol[][] = {
                {0, 0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 0, 0, 0,0},

        };

        if (busquedaCaminoAux(matriz, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        mostrarSolucion(sol);
        return true;
    }

    private boolean busquedaCaminoAux(int matriz[][], int x, int y,
                                      int matrixFinal[][]) {

        /**Se llegó al objetivo*/
        if (x == nX - 1 && y == nY - 1) {
            matrixFinal[x][y] = 2;
            Point posAux = new Point(x,y);
            boolean state = true;
            nodoCoordenada nodoCoordenada2 = new nodoCoordenada(state,posAux);
            myList.add(nodoCoordenada2);

            mostrarSolucion(matrixFinal);
            return true;
        }


        if (isValido(matriz, x, y) == true) {

            matrixFinal[x][y] = 2;
            Point posAux = new Point(x,y);
            boolean state = true;
            nodoCoordenada nodoTemp = new nodoCoordenada(state,posAux);
            myList.add(nodoTemp);
            mostrarSolucion(matrixFinal);


            /** Mueve al frente en x */
            if (busquedaCaminoAux(matriz, x + 1, y, matrixFinal)) {
                mostrarSolucion(matrixFinal);

                return true;
            }

            /** Mueve en el eje y */
            if (busquedaCaminoAux(matriz, x, y + 1, matrixFinal)) {
                mostrarSolucion(matrixFinal);

                return true;
            }

            /** Si no funka quita la posicion de la solucion */
            matrixFinal[x][y] = 0;
            Point posAux2 = new Point(x,y);
            boolean state2 = false;
            nodoCoordenada nodoAux = new nodoCoordenada(state2,posAux2);
            myList.add(nodoAux);
            mostrarSolucion(matrixFinal);

            return false;
        }

        return false;
    }

    public void printMyList(){
        myList.size();
        for(int i=0;i<myList.size();i++){
           System.out.print( "POSSSSSSS"+myList.get(i).getPosXY().x+","+myList.get(i).getPosXY().y);

        }
    }

    public List<nodoCoordenada> getMyList() {
        return myList;
    }
}


