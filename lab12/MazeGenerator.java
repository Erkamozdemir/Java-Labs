import java.util.Random;
import java.util.Scanner;

public class MazeGenerator {
    public static Scanner in = new Scanner(System.in);
    public static char[][] maze;
    public static Random random = new Random();

    public static int[] getCorrectInput() {
        System.out.print("Enter the width of the maze: ");
        if (!in.hasNextInt()) {
            System.out.println("Invalid input. Please try again.");
            in.nextLine();
            return getCorrectInput();
        }
        int width = in.nextInt();
        System.out.print("Enter the height of the maze: ");
        if (!in.hasNextInt()) {
            System.out.println("Invalid input. Please try again.");
            in.nextLine();
            return getCorrectInput();
        }
        int height = in.nextInt();
        if (width <= 0 || height <= 0 || width % 2 == 0 || height % 2 == 0) {
            System.out.println("Invalid input. Width and height must be positive and odd integers.");
            return getCorrectInput();
        }
        return new int[] { width, height };
    }

    public static void generateMazeCarving(int[] dimensions) {
        int width = dimensions[0];
        int height = dimensions[1];
        maze = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = '#';
            }
        }
        maze[1][1] = ' ';
        carveMaze(maze, 1, 1);
        maze[0][1] = ' ';
        maze[height - 1][width - 2] = ' ';
        printMaze(maze);
    }

    public static void generateRecursiveDivision(int[] dimensions) {
        int width = dimensions[0];
        int height = dimensions[1];
        maze = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = ' ';
            }
        }
        for (int i = 0; i < width; i++) {
            maze[0][i] = '#';
            maze[height - 1][i] = '#';
        }
        for (int i = 0; i < height; i++) {
            maze[i][0] = '#';
            maze[i][width - 1] = '#';
        }
        maze[0][1] = ' ';
        maze[height - 1][width - 2] = ' ';
        divideMaze(maze, 1, height - 2, 1, width - 2);
        printMaze(maze);
    }

    public static void generateHybridMaze(int[] dimensions) {
        int width = dimensions[0];
        int height = dimensions[1];
        maze = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = ' ';
            }
        }
        for (int i = 0; i < width; i++) {
            maze[0][i] = '#';
            maze[height - 1][i] = '#';
        }
        for (int i = 0; i < height; i++) {
            maze[i][0] = '#';
            maze[i][width - 1] = '#';
        }
        maze[0][1] = ' ';
        maze[height - 1][width - 2] = ' ';
        hybridMaze(maze, 1, height - 2, 1, width - 2);
        printMaze(maze);
    }

    public static void hybridMaze(char[][] maze, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowEnd - rowStart < 5 || colEnd - colStart < 5) {
            carveMaze(maze, rowEnd, colEnd);
        } else {
            int wallRow = (rowStart + rowEnd) / 2;
            if (wallRow % 2 != 0)
                wallRow++;
            int wallCol = (colStart + colEnd) / 2;
            if (wallCol % 2 != 0)
                wallCol++;
            for (int i = colStart; i <= colEnd; i++) {
                maze[wallRow][i] = '#';
            }
            for (int i = rowStart; i <= rowEnd; i++) {
                maze[i][wallCol] = '#';
            }

            int topOpen = (rowStart + wallRow - 1) / 2;
            if (topOpen % 2 == 0)
                topOpen++;
            int bottomOpen = (wallRow + 1 + rowEnd) / 2;
            if (bottomOpen % 2 == 0)
                bottomOpen++;
            int leftOpen = (colStart + wallCol - 1) / 2;
            if (leftOpen % 2 == 0)
                leftOpen++;
            int rightOpen = (wallCol + 1 + colEnd) / 2;
            if (rightOpen % 2 == 0)
                rightOpen++;

            int closedWall = random.nextInt(4);

            if (closedWall != 0)
                maze[topOpen][wallCol] = ' ';

            if (closedWall != 1)
                maze[bottomOpen][wallCol] = ' ';

            if (closedWall != 2)
                maze[wallRow][leftOpen] = ' ';

            if (closedWall != 3)
                maze[wallRow][rightOpen] = ' ';

            divideMaze(maze, rowStart, wallRow - 1, colStart, wallCol - 1);
            divideMaze(maze, rowStart, wallRow - 1, wallCol + 1, colEnd);
            divideMaze(maze, wallRow + 1, rowEnd, colStart, wallCol - 1);
            divideMaze(maze, wallRow + 1, rowEnd, wallCol + 1, colEnd);
        }

    }

    public static void divideMaze(char[][] maze, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowEnd - rowStart < 2 || colEnd - colStart < 2) {
            return;
        } else {
            int wallRow = (rowStart + rowEnd) / 2;
            if (wallRow % 2 != 0)
                wallRow++;
            int wallCol = (colStart + colEnd) / 2;
            if (wallCol % 2 != 0)
                wallCol++;
            for (int i = colStart; i <= colEnd; i++) {
                maze[wallRow][i] = '#';
            }
            for (int i = rowStart; i <= rowEnd; i++) {
                maze[i][wallCol] = '#';
            }

            int topOpen = (rowStart + wallRow - 1) / 2;
            if (topOpen % 2 == 0)
                topOpen++;
            int bottomOpen = (wallRow + 1 + rowEnd) / 2;
            if (bottomOpen % 2 == 0)
                bottomOpen++;
            int leftOpen = (colStart + wallCol - 1) / 2;
            if (leftOpen % 2 == 0)
                leftOpen++;
            int rightOpen = (wallCol + 1 + colEnd) / 2;
            if (rightOpen % 2 == 0)
                rightOpen++;

            int closedWall = random.nextInt(4);

            if (closedWall != 0)
                maze[topOpen][wallCol] = ' ';

            if (closedWall != 1)
                maze[bottomOpen][wallCol] = ' ';

            if (closedWall != 2)
                maze[wallRow][leftOpen] = ' ';

            if (closedWall != 3)
                maze[wallRow][rightOpen] = ' ';

            divideMaze(maze, rowStart, wallRow - 1, colStart, wallCol - 1);
            divideMaze(maze, rowStart, wallRow - 1, wallCol + 1, colEnd);
            divideMaze(maze, wallRow + 1, rowEnd, colStart, wallCol - 1);
            divideMaze(maze, wallRow + 1, rowEnd, wallCol + 1, colEnd);
        }

    }

    public static void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static void carveMaze(char[][] maze, int row, int col) {
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 3; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + 2 * directions[i][0];
            int newCol = col + 2 * directions[i][1];
            if (newRow > 0 && newRow < maze.length - 1 && newCol > 0 && newCol < maze[0].length - 1
                    && maze[newRow][newCol] == '#') {
                maze[row + directions[i][0]][col + directions[i][1]] = ' ';
                maze[newRow][newCol] = ' ';
                carveMaze(maze, newRow, newCol);
            }
        }
    }

    public static void main(String[] args) {

        boolean isExit = false;

        while (!isExit) {
            System.out.println("Enter 1 to generate a maze using Maze Carving.");
            System.out.println("Enter 2 to generate a maze using Recursive Division.");
            System.out.println("Enter 3 to generate a maze using Hybrid Generating.");
            System.out.println("Enter 4 to Exit.");
            System.out.print("Enter your choice: ");
            if (in.hasNextInt()) {
                int choice = in.nextInt();
                if (choice == 1) {
                    int[] dimensions = getCorrectInput();
                    generateMazeCarving(dimensions);
                } else if (choice == 2) {
                    int[] dimensions = getCorrectInput();
                    generateRecursiveDivision(dimensions);
                } else if (choice == 3) {
                    int[] dimensions = getCorrectInput();
                    generateHybridMaze(dimensions);
                } else if (choice == 4) {
                    isExit = true;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please try again.");
                in.nextLine();
            }

        }

    }
} 