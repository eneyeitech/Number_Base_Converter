package converter;

import converter.presentation.ConvertFromBase10;
import converter.presentation.ConvertToBase10;

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
                    BigInteger r = convertToBase10.result(input, s);
                    String o = convertFromBase10.result(t, r);
                    System.out.println("Conversion result: " + o + "\n");
                    break;
            }
        }while(i != 0);

    }
}
