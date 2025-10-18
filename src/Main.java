import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // init first input
        String[] boardSizeStr = br.readLine().split(" ");
        int[] boardSize = new int[2];
        boardSize[0] = Integer.parseInt(boardSizeStr[0]); // num of row
        boardSize[1] = Integer.parseInt(boardSizeStr[1]); // num of column

        // init board data
        // 'W' = true , 'B' = false
        boolean[][] board = new boolean[boardSize[0]][boardSize[1]];
        for (int i = 0; i < boardSize[0]; i++) {
            char[] rowCell = br.readLine().toCharArray();
            for (int j = 0; j < boardSize[1]; j++) {
                boolean cell = (rowCell[j] == 'W' ? true : false);
                board[i][j] = cell;
            }
        }

        //




        bw.flush();
        bw.close();
    }

    // culculate score of cutten board
    // firstColor : color of first cell of cutten board
    public static int culculateScore(boolean[][] cuttenBoard, boolean firstColor) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // get current cell color
                boolean currentColor;
                if (i + j % 2 == 0) { currentColor = firstColor; }
                else { currentColor = !firstColor;}

                if (cuttenBoard[i][j] == currentColor) {
                    score++;
                }
            }
        }

        if (score > 32) {
            return score;
        } else { // when board is entirely opposite color
            return 64 - score;
        }
    }

}
