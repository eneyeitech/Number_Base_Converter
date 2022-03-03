package converter.presentation;

import java.util.Scanner;

public class ConvertFromBase10 {
    private final Scanner scanner;

    public ConvertFromBase10() {
        scanner = new Scanner(System.in);
    }

    public void view() {

            System.out.print("Enter number in decimal system: ");
            int line = scanner.nextInt();
            System.out.print("Enter target base: ");
            int line1 = scanner.nextInt();
            String result = fromDecimal(line1, line);
            System.out.printf("Conversion result: %s\n", result);

    }

    // To return char for a value. For
// example '2' is returned for 2.
// 'A' is returned for 10. 'B' for 11
    public char reVal(int num)
    {
        if (num >= 0 && num <= 9)
            return (char)(num + 48);
        else
            return (char)(num - 10 + 65);
    }

    // Function to convert a given decimal number
// to a base 'base' and
    public String fromDecimal(int base1, int inputNum)
    {
        String s = "";

        // Convert input number is given
        // base by repeatedly dividing it
        // by base and taking remainder
        while (inputNum > 0)
        {
            s += reVal(inputNum % base1);
            inputNum /= base1;
        }
        StringBuilder ix = new StringBuilder();

        // append a string into StringBuilder input1
        ix.append(s);

        // Reverse the result
        return new String(ix.reverse());
    }

}
