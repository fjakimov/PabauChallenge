import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static String direction(Character [][]matrix, int rows, int columns, int columnsLength, int rowsLength, String direction){
        for(int i = 0; i<rowsLength;i++){
            for (int j = 0;j<columnsLength;j++){
                if(rows==i && columns == j && matrix[i][j]=='>'){
                    return "Right";
                }
                if(direction.equals("Right")){
                    if(i == rows && j == columns && j+1 < columnsLength && (matrix[i][j+1] != null && matrix[i][j+1] != '0')){
                        return "Right";
                    }
                    else if(i == rows && j == columns && i+1 < rowsLength && (matrix[i+1][j] != null && matrix[i+1][j] != '0')){
                        return "Down";
                    }
                    else if(i == rows && j == columns && i-1 >=0 && (matrix[i-1][j] != null && matrix[i-1][j] != '0')) {
                        return "Up";
                    }
                }
                else if(direction.equals("Down")){
                    if(i == rows && j == columns && i+1 < rowsLength && (matrix[i+1][j] != null && matrix[i+1][j] != '0')) {
                        return "Down";
                    }
                    else if(i == rows && j == columns && j+1 < columnsLength && (matrix[i][j+1] != null && matrix[i][j+1] != '0')){
                        return "Right";
                    }
                    else if(i == rows && j == columns && j-1 >= 0 && (matrix[i][j-1] != null && matrix[i][j-1] != '0'))
                        return "Left";
                }
                else if(direction.equals("Left")){
                    if(i == rows && j == columns && i-1 >= 0 && (matrix[i-1][j] != null && matrix[i-1][j] != '0')){
                        return "Up";
                    }
                    else if(i == rows && j == columns && j-1 >= 0 && (matrix[i][j-1] != null && matrix[i][j-1] != '0')){
                        return "Left";
                    }
                    else if(i == rows && j == columns && i + 1 < rowsLength && (matrix[i+1][j] != null && matrix[i+1][j] != '0')){
                        return "Down";
                    }
                }
                else if(direction.equals("Up")){
                    if(i == rows && j == columns && i-1 >= 0 && (matrix[i-1][j] != null && matrix[i-1][j] != '0')){
                        return "Up";
                    }
                    else if(i == rows && j == columns && j+1 < columnsLength  && (matrix[i][j+1] != null && matrix[i][j+1] != '0')) {
                        return "Right";
                    }
                    else if(i == rows && j == columns && j-1 >= 0 && (matrix[i][j-1] != null && matrix[i][j-1] != '0')){
                        return "Left";
                    }
                }
            }
        }
        return "";
    }
    public static void main(String[] args) {
        final Character[][] matrix = {
                {'>', '-', '-', '-', 'A', '-', '@', '-', '+'},
                {'0', '0', '0', '0', '0', '0', '0', '0', '|'},
                {'+', '-', 'U', '-', '+', '0', '0', '0', 'C'},
                {'|', '0', '0', '0', '|', '0', '0', '0', '|'},
                {'s', '0', '0', '0', 'C', '-', '-', '-', '+'}
        };

        List<Character> path = new ArrayList<>();

        int columns = matrix[0].length;
        int rows = matrix.length;

        int startI = 0;
        int startJ = 0;
        String direction = "Right";
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(matrix[i][j] == '>'){
                    path.add(matrix[i][j]);
                    startI = i;
                    startJ = j;
                    break;
                }
            }
        }
        for(int i = startI; i < rows; i++){
            for (int j = startJ; j < columns; j++){
                if(direction.equals("Right") && (matrix[i][j] == '-' || matrix[i][j]=='@')){
                    path.add(matrix[i][j]);
                }
                if(direction.equals("Left") && (matrix[i][j] == '-' || matrix[i][j] =='@')){
                    path.add(matrix[i][j]);
                    j=j-2;
                }
                else if(matrix[i][j]=='+' || Character.isLetter(matrix[i][j]) && matrix[i][j]!='s'){
                    direction = direction(matrix, i, j, columns, rows, direction);
                    path.add(matrix[i][j]);
                    if(direction.equals("Down")){
                        i++;
                        j--;
                    }
                    if(direction.equals("Left")){
                        j-=2;
                    }
                    if(direction.equals("Up")){
                        i--;
                        j--;
                    }
                }
                else if(matrix[i][j] == '|' && direction.equals("Down")){
                    path.add(matrix[i][j]);
                    i++;
                    j--;
                }
                else if(matrix[i][j]=='|' && direction.equals("Up")){
                    path.add(matrix[i][j]);
                    i--;
                    j--;
                }
                else if(matrix[i][j] == 's'){
                    path.add(matrix[i][j]);
                    break;
                }
            }
        }
        System.out.print("Path: ");
        path.forEach(System.out::print);
        System.out.println();
        System.out.print("Letters: ");
        path.stream().filter(character -> Character.isLetter(character) && Character.isUpperCase(character)).forEach(System.out::print);
        // Didnt know what to do with the @ because its not mentioned in the text so i assumed it as a - (Also could have ignored it)
    }
}