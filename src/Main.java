import java.util.Scanner;

public class Main {

    private static final String[] ROMAN_NUMERALS = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] VALUES = {100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static int calculation(String[] numbers) throws Exception{
        if (Integer.parseInt(numbers[0]) > 10 || Integer.parseInt(numbers[0]) <= 0 ||
        (Integer.parseInt(numbers[2]) > 10 || Integer.parseInt(numbers[2]) <= 0))
            throw new Exception("Первый и второй операнд должны быть больше нуля и не больше десяти");
        return switch (numbers[1]) {
            case ("+") -> Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[2]);
            case ("-") -> Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[2]);
            case ("*") -> Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[2]);
            case ("/") -> Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[2]);
            default -> throw new Exception("Оператор может быть только: +, -, *, /");
        };
    }

    public static int romanToInt(String roman){
        roman = roman.toUpperCase();
        int result = 0;
        int index = 0;

        while (index < roman.length()) {
            boolean found = false;
            for (int i = 0; i < ROMAN_NUMERALS.length; i++) {
                if (roman.startsWith(ROMAN_NUMERALS[i], index)) { //prefix – префикс, который должен быть сопоставлен;//toffset – начальный индекс поиска в строке.
                    result += VALUES[i];
                    index += ROMAN_NUMERALS[i].length();
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Неверная римская цифра");
            }
        }
        return result;
    }

    public static String intToRome(int num){
        int index = 0;
        StringBuilder resultRome = new StringBuilder();

        while (num > 0){
            if (num >= VALUES[index]){
                num -= VALUES[index];
                resultRome.append(ROMAN_NUMERALS[index]);
            }
            else {
                index++;
            }
        }
        return resultRome.toString();
    }
    public static String calcRoman(String[] romanNumber) throws Exception {
        int firstRomanNumber = romanToInt(romanNumber[0]);
        int secondRomanNumber = romanToInt(romanNumber[2]);
        String[] romanArray = {String.valueOf(firstRomanNumber), romanNumber[1], String.valueOf(secondRomanNumber)};
        int resultArabic = calculation(romanArray);
        if(resultArabic < 1)
            throw new Exception("Римское число не может быть меньше одного");
        return intToRome(resultArabic);
    }

    public static String calc(String input) throws Exception {
        String[] numbers = input.split(" ");
        if (numbers.length != 3)
            throw new Exception("Должно быть 2 операнда и один оператор:+, -, *, /");
        String result = null;
        char[] charArray;
        charArray = input.toCharArray();
        if (Character.isDigit(charArray[0]) || Character.isDigit(charArray[2]))
            return result.valueOf(calculation(numbers));
        else
            return calcRoman(numbers);
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        String result = calc(expression);
        System.out.println(result);
    }
}