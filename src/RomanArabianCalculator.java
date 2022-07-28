import java.util.Scanner;
import java.util.HashMap;
public class RomanArabianCalculator {
    static Scanner scanner = new Scanner(System.in);
    static int number1;
    static int number2;
    static char operation;
    static int result;

    public static void main(String[] args) throws CalcException {
        System.out.println("Input:");
        String userInput = scanner.nextLine();
        String[] userInputChars = userInput.split(" ");
        switch (userInputChars[1]) {
            case "+" -> operation = '+';
            case "-" -> operation = '-';
            case "*" -> operation = '*';
            case "/" -> operation = '/';
            default -> throw new CalcException("Введен неправильный знак операции");
        }
        String stableNumber1 = userInputChars[0];
        String stableNumber2 = userInputChars[2];

        if(isRoman(stableNumber1) ^ isRoman(stableNumber2)){
            throw new CalcException("используются одновременно разные системы счисления");
        }

        if(isRoman(stableNumber1)){
            number1 = romanToInt(stableNumber1);
            number2 = romanToInt(stableNumber2);
            result = calculated(number1, number2, operation);
            String romanResult = intToRoman(result);
            if (result < 1){
                throw new CalcException("результат не может быть меньше 1");
            }else
                System.out.println(romanResult);
        } else {
            number1 = Integer.parseInt(stableNumber1);
            number2 = Integer.parseInt(stableNumber2);
            result = calculated(number1, number2, operation);
            System.out.println(result);
        }
        if (((number1 < 1) || (number1 >10)) || (number2 < 1) || (number2 >10)){
            throw new CalcException("Числа не в пределах допустимого значения");
        }
        if (userInputChars.length > 3){
            throw new CalcException("Введено больше чисел, чем нужно");
        }

    }

    public static String intToRoman(int num) {
        String[] thousands = new String[]{"", "M", "MM", "MMM"};
        String[] hundreds =
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens =
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units =
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num / 1000] +
                hundreds[(num % 1000) / 100] +
                tens[(num % 100) / 10] +
                units[num % 10];
    }

    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
                result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }
    public static boolean isRoman (String str){
        return str.matches("^I$|^II$|^III$|^IV$|^V$|^VI$|^VII$|^VIII$|^IX$|^X$");
    }



    public static int calculated(int num1, int num2, char op) throws CalcException {
        if (operation == '+') {
            result = num1 + num2;
        } else if (operation == '-') {
            result = num1 - num2;
        } else if (operation == '*') {
            result = num1 * num2;
        } else if (operation == '/') {
            result = num1 / num2;
        } else {
            throw new CalcException("Неверная операция");
        }
        return result;
    }

}
