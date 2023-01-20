import java.util.Scanner;
public class Calculator{
    public static void main(String[] args){
        System.out.println("Введите арифметическую операцию(+-*/) с двумя числами от 1 до 10 или от I до X:");
        System.out.println("В строку вводится первое число, пробел, знак операции, пробел и второе число");
        Scanner scanner = new Scanner(System.in);
        Main result = new Main();
        String expression = scanner.nextLine();
        String answer = result.calc(expression);
        System.out.println(answer);
    }
}
class Main{
    public static String calc(String input){
        boolean romanOrArab = false;
        String exception = "throws Exception";
        Main romanChek = new Main();
        Main arabToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            return exception;
        }
        Integer firstNumber = 0;
        Integer secondNumber = 0;
        try {
            firstNumber = Integer.valueOf(inputSplit[0]);
            secondNumber = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException ex) {
            try {
                firstNumber = romanChek.romanToArab(inputSplit[0]);
                secondNumber = romanChek.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex1) {
                return exception;
            }
        }
        if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
            return exception;
        }

        String opertor = inputSplit[1];
        switch (opertor) {
            case "+": result = firstNumber + secondNumber;
            break;
            case "-": result = firstNumber - secondNumber;
            break;
            case "*": result = firstNumber * secondNumber;
            break;
            case "/": result = firstNumber / secondNumber;
            break;
            default:{
                return exception;
            }
        }
        String output;

        if (romanOrArab){
            if(result < 1){
                return exception;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }
    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }
    String arabToRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}
