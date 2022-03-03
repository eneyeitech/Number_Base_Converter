package converter;

import converter.presentation.ConvertFromBase10;
import converter.presentation.ConvertToBase10;

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
}
