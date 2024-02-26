package sudoku.computationLogic;

import sudoku.problemdomain.Coordinates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {


    public static int[][]getNewGameGrid(){
        return unsolvegame(getSolvedGame());
    }

    private static int[][] unsolvegame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvale = false;
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvale == false){
            SudokuUtilities.copySudokuArrayValue(solvedGame,solvableArray);

            int index = 0;

            while (index < 40){
                int xCoordinates = random.nextInt(GRID_BOUNDARY);
                int yCoordinates = random.nextInt(GRID_BOUNDARY);

                if(solvableArray[xCoordinates][yCoordinates] != 0){
                    solvableArray[xCoordinates][yCoordinates] = 0;
                    index++;
                }
            }
        int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
        SudokuUtilities.copySudokuArrayValue(solvableArray,toBeSolved);

        solvale = SudokuSolver.puzzleIsSolvable(toBeSolved);

        return solvableArray;
        }

        return new int[0][];
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        for(int value = 1; value <= GRID_BOUNDARY; value++){
            int allocations = 0;
            int interrupt = 0;

            List<Coordinates> allocTracker = new ArrayList<>();

            int attempts = 0;

            while(allocations < GRID_BOUNDARY){
                if (interrupt > 200){
                    allocTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.gety()] = 0;

                    });

                    interrupt =0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    if (attempts > 500){
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                int xCoordinates = random.nextInt(GRID_BOUNDARY);
                int yCoordinates = random.nextInt(GRID_BOUNDARY);

                if(newGrid[xCoordinates][yCoordinates] == 0){
                    newGrid[xCoordinates][yCoordinates] = value;

                    if(GameLogic.sudokuIsInvalid(newGrid)){
                        newGrid[xCoordinates][yCoordinates] = 0;
                        interrupt++;

                    }
                    else{
                        allocTracker.add(new Coordinates(xCoordinates,yCoordinates));
                        allocations++;
                    }
                }
            }

        }
        return newGrid;
    }

    private  static void clearArray(int[][] newGrid){
        for(int xIndex =0; xIndex <GRID_BOUNDARY; xIndex++){
            for(int yIndex =0; yIndex <GRID_BOUNDARY; yIndex++){
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
