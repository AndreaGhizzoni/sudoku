package it.disi.unitn.sudoku;

import java.util.HashSet;

public class Launcher {

    static boolean sudokuSolver( Integer[][] S ){
        return sudoku(S, 0);
    }

    public static boolean sudoku( Integer[][] S, Integer i ){
        HashSet<Integer> choices = new HashSet<>();
        Integer x = i % 9;
        Integer y = i/9;
        if( i <= 80 ){
            if( !S[x][y].equals(0) ){
                choices.add(S[x][y]);
            }else{
                for( int count=1; count<10; count++ ){
                    if( check(S, x, y, count) ){
                        choices.add(count);
                    }
                }
            }
        }

        for( Integer choice: choices) {
            S[x][y] = choice;
            if( i.equals(80) ) return true;
            if( sudoku(S, i+1) ) return true;
        }
        return false;
    }

    public static boolean check( Integer[][] S, Integer x, Integer y, Integer valueToCheck ){
        for( int i=0; i<9; i++ ){
            if( S[x][i].equals(valueToCheck) ) return false;
            if( S[i][y].equals(valueToCheck) ) return false;
        }

        // search in sub matrix
        Integer bx= x/3;
        Integer by= y/3;
        for( int i=0; i<3; i++ ){
            for( int j=0; j<3; j++ ){
                if( S[bx*3+i][by*3+j].equals(valueToCheck) ) return false;
            }
        }
        return true;
    }

    public static void main( String...args ){
        // incomplete sudoku
//        Integer[][] S = {
//            {2,5,3, 8,9,1, 4,7,6},
//            {8,9,7, 2,6,4, 3,1,5},
//            {6,4,1, 5,7,3, 9,2,8},
//            {7,8,9, 4,3,5, 2,6,1},
//            {1,3,6, 7,2,9, 8,5,4},
//            {4,2,5, 6,1,8, 7,3,9},
//            {9,6,8, 3,5,2, 1,4,7},
//            {5,1,2, 9,4,7, 6,8,3},
//            {3,7,4, 1,8,6, 5,9,2},
//        };
        Integer[][] S = {
            {2,5,0, 0,9,0, 0,7,6},
            {0,0,0, 2,0,4, 0,0,0},
            {0,0,1, 5,0,3, 9,0,0},
            {0,8,9, 4,0,5, 2,6,0},
            {1,0,0, 0,2,0, 0,0,4},
            {0,2,5, 6,0,0, 7,3,0},
            {0,0,8, 3,0,2, 1,0,0},
            {0,0,0, 9,0,7, 0,0,0},
            {3,7,0, 0,8,0, 0,9,2}
        };

        printSudoku(S);
        System.out.println(sudokuSolver(S));
        printSudoku(S);

//        printSudoku(S);
//        System.out.println(
//            check(S, 0, 5, 1)
//        );
    }

    public static void printSudoku( Integer[][] S ){
        for( Integer[] row : S) {
            for( Integer i : row ){
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    }
}
