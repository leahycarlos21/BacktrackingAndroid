package datos2.tec.com.backtrackingmaze;


import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends Activity {
    RelativeLayout myRelativeLayout;


    public void paintButton_Matrix(int[][] matriz, Button[] matrixButtons) {

        int pos = 0;
        for (int row[] : matriz) {
            for (int cell : row) {
                matrixButtons[pos] = paintButton_MatrixAux(cell, matrixButtons[pos]);
                pos++;
            }
        }
    }

    public int[][] getMatrixRandom() {
        final int matriz1[][] = {
                {1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1},
        };
        final int matriz2[][] = {
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 0, 1, 1, 1},

        };
        final int matriz3[][] = {
                {1, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 0, 1},
        };
        final int matriz4[][] = {
                {1, 1, 1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},

        };
        final int matriz5[][] = {
                {1, 1, 1, 1, 0, 1, 1, 1},
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1},

        };
        Random rand = new Random();

        int n = rand.nextInt(4) + 1;
        switch (n) {
            case 1:
                return matriz1;
            case 2:
                return matriz2;
            case 3:
                return matriz3;
            case 4:
                return matriz4;
            case 5:
                return matriz5;
            default:
                return matriz3;
        }
    }

    public Button paintButton_MatrixAux(int data, Button button) {
        Button finalButton = button;
        if (data == 1)
            finalButton.setBackgroundColor(Color.GREEN);
        else if (data == 2) {
            finalButton.setBackgroundColor(Color.YELLOW);

        } else
            finalButton.setBackgroundColor(Color.GRAY);
        return finalButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button genButton = (Button) findViewById(R.id.buttonGen);
        final Button startButton = (Button) findViewById(R.id.startButton);
        myRelativeLayout = (RelativeLayout) findViewById(R.id.myRelativeLayout);
        final int[][][] matriz = {getMatrixRandom()};


        final Button[] matrixButtons = new Button[40];
        for (int i = 0; i < myRelativeLayout.getChildCount() - 2; i++) {
            Button child = (Button) myRelativeLayout.getChildAt(i);
            matrixButtons[i] = child;

        }
        paintButton_Matrix(matriz[0], matrixButtons);


        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genButton.setBackgroundColor(Color.GREEN);
                int[][] auxMatrix = matriz[0];
                while (auxMatrix == matriz[0]) {
                    auxMatrix = getMatrixRandom();
                }
                matriz[0] = auxMatrix;
                startButton.setEnabled(true);
                startButton.setClickable(true);
                paintButton_Matrix(matriz[0], matrixButtons);

            }
        });


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                startButton.setBackgroundColor(Color.BLUE);
                genButton.setEnabled(false);
                genButton.setClickable(false);
                paintButton_Matrix(matriz[0], matrixButtons);

                Thread mThread;
                mThread = new Thread() {
                    public void run() {
                        int pos = 0;
                        paintButton_Matrix(matriz[0], matrixButtons);
                        Backtracking backtracking = new Backtracking();
                        backtracking.busquedaCamino(matriz[0]);
                        while (pos < backtracking.myList.size()) {


                            int posX = backtracking.myList.get(pos).getPosXY().x;
                            int posY = backtracking.myList.get(pos).getPosXY().y;
                            int posfinal = (posY + (8 * posX));

                            paintButton(matrixButtons[posfinal], backtracking.myList.get(pos).isState());
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            pos++;
                        }
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                genButton.setEnabled(true);
                                genButton.setClickable(true);
                                startButton.setEnabled(false);
                                startButton.setClickable(false);
                                Toast.makeText(MainActivity.this,"Backtracking Solution",Toast.LENGTH_LONG).show();
                            }
                        });
                        stopThread(this, genButton);


                    }
                };
                mThread.start();

            }
        });
    }

    private synchronized void stopThread(Thread theThread, Button button) {
        if (theThread != null) {
            theThread = null;
        }
    }


    void paintButton(Button button, boolean state) {
        if (state == true) {
            button.setBackgroundColor(Color.BLUE);
        } else {
            button.setBackgroundColor(Color.GREEN);
        }
    }
}



