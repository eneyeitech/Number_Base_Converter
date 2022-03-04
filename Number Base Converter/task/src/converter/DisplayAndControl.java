package converter;

import converter.presentation.ConvertFromBase10;
import converter.presentation.ConvertToBase10;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public class DisplayAndControl {

    private final Scanner scanner;
    private final ConvertToBase10 convertToBase10;
    private final ConvertFromBase10 convertFromBase10;

    public DisplayAndControl() {
        this.scanner = new Scanner(System.in);
        convertFromBase10 = new ConvertFromBase10(scanner);
        convertToBase10 = new ConvertToBase10(scanner);
    }

    public void display(){
        int i = 1;
        do {
            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            String input = scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (input) {
                case "/from":
                    convertFromBase10.view();
                    break;
                case "/to":
                    convertToBase10.view();
                    break;
                case "/exit":
                    i = 0;
                    break;
                default:
                    break;
            }
        }while(i != 0);
        System.exit(0);
    }

    public void display1(){
        int i = 1;
        do {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String input = scanner.nextLine().toLowerCase(Locale.ROOT);
            String[] arr = input.split(" ");
            int sourceBase = 1;
            int targetBase = 1;
            if (arr.length == 2) {
                 sourceBase = Integer.parseInt(arr[0]);
                 targetBase = Integer.parseInt(arr[1]);
            }
            if ((sourceBase >= 2 || sourceBase <= 36) && (targetBase >= 2 && targetBase <= 36)) {
                input = "go";
            }
            //System.out.println(sourceBase);
            //System.out.println(targetBase);
            switch (input) {
                case "/exit":
                    i = 0;
                    break;
                case "go":
                    display2(sourceBase, targetBase);
                default:
                    break;
            }
        }while(i != 0);
        System.exit(0);
    }

    private void display2(int s, int t){
        int i = 1;
        do {
            System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) ", s, t);
            String input = scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (input) {
                case "/back":
                    i = 0;
                    break;
                default:
                    String[] numberSplit = input.split("\\.");

                    if (numberSplit.length == 1 || s == 1){
                        String integerPart = convertIntegerPart(numberSplit[0], s, t);
                        System.out.printf("Conversion result: %s\n", integerPart);
                    } else {
                        String integerPart = convertIntegerPart(numberSplit[0], s, t);
                        String fractionalPart = convertFractionalPart(numberSplit[1], s, t);
                        System.out.printf("Conversion result: %s.%s\n", integerPart, fractionalPart);
                    }

                    //BigInteger r = convertToBase10.result(input, s);
                    //String o = convertFromBase10.result(t, r);
                    //System.out.println("Conversion result: " + o + "\n");
                    break;
            }
        }while(i != 0);

    }


    private String convertIntegerPart(String sourceInteger, int sourceBase, int targetBase) {
        int number = 0;
        BigInteger number1;
        if (sourceBase != 10) {
            if (sourceBase == 1) {
                number = sourceInteger.length();
                number1 = new BigInteger(String.valueOf(number));
            } else {
                //number = Integer.parseInt(sourceInteger, sourceBase);
                number1 = new BigInteger(sourceInteger, sourceBase);
            }
        } else {
            //number = Integer.parseInt(sourceInteger);
            number1 = new BigInteger(sourceInteger);
        }

        if (targetBase == 1) {
            return "1".repeat(Math.max(0, number));
        }
        //return Integer.toString(number, targetBase);
        return number1.toString(targetBase);
    }

    private String convertFractionalPart(String sourceFractional, int sourceBase, int targetBase) {
        double decimalValue = 0.0;
        BigDecimal decimalValue1 = BigDecimal.ZERO;
        if (sourceBase == 10) {
            decimalValue = Double.parseDouble("0." + sourceFractional);
            decimalValue1 = new BigDecimal(decimalValue);
        } else {
            char c;
            for (int i = 0; i < sourceFractional.length(); i++) {
                c = sourceFractional.charAt(i);
                decimalValue += Character.digit(c, sourceBase) / Math.pow(sourceBase, i + 1);
                //double d = Character.digit(c, sourceBase) / (double) Math.pow(sourceBase, i + 1);
                //BigDecimal b = new BigDecimal(Character.digit(c, sourceBase));
                //BigDecimal p = new BigDecimal(Math.pow(sourceBase, i + 1));
                //decimalValue1.add(b.divide(p));
                //decimalValue1.add(new BigDecimal(Double.toString(d)));
                decimalValue1.add(new BigDecimal(decimalValue));
            }
        }

        StringBuilder result = new StringBuilder();
        int decimal;
        BigInteger decimal1;
        for (int i = 0; i < 5; i++) {
            double aux = decimalValue * targetBase;
            BigDecimal aux1 = decimalValue1.multiply(new BigDecimal(String.valueOf(targetBase)));
            decimal = (int) aux;
            //decimal1 = aux1.toBigInteger();
            decimal1 = new BigInteger(String.valueOf(decimal));
            //result.append(Long.toString(decimal, targetBase));
            result.append(decimal1.toString(targetBase));
            decimalValue = aux - decimal;
            //decimalValue1 = aux1.subtract(new BigDecimal(decimal1.toString()));
            decimalValue1 = new BigDecimal(String.valueOf(decimalValue));
        }
        return result.toString();
    }
}
