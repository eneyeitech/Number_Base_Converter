package converter.presentation;

import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public class ConvertToBase10 {

    private final Scanner scanner;

    public ConvertToBase10(Scanner scanner) {
        this.scanner = scanner;
    }

    public void view() {

        System.out.print("Enter source number: ");
        String line = scanner.next();
        System.out.print("Enter source base: ");
        int line1 = scanner.nextInt();
        //int result = toDecimal(line.toUpperCase(Locale.ROOT), line1);
        BigInteger /*int*/ result = toDecimal(line, line1);
        System.out.printf("Conversion to decimal result: %s\n", result.toString());

    }

    // To return value of a char.
// For example, 2 is returned
// for '2'. 10 is returned
// for 'A', 11 for 'B'
    private int val(char c)
    {
        if (c >= '0' && c <= '9')
            return (int)c - '0';
        else
            //return (int)c - 'A' + 10;
            return (int)c - 'a' + 10;
    }

    // Function to convert a
// number from given base
// 'b' to decimal
    private /*int*/ BigInteger toDecimal(String str,
                      int base)
    {
        int len = str.length();
        int power = 1; // Initialize
        BigInteger power1 = BigInteger.ONE;
        // power of base
        int num = 0; // Initialize result
        BigInteger num1 = BigInteger.ZERO;
        int i;

        // Decimal equivalent is
        // str[len-1]*1 + str[len-2] *
        // base + str[len-3]*(base^2) + ...
        for (i = len - 1; i >= 0; i--)
        {
            // A digit in input number
            // must be less than
            // number's base
            /**if (val(str.charAt(i)) >= base)
            {
                System.out.println("Invalid Number");
                return -1;
            }*/

            num += val(str.charAt(i)) * power;
            int val = val(str.charAt(i));
            BigInteger val1 = new BigInteger(String.valueOf(val));
            BigInteger val11 = val1.multiply(power1);
            num1 = num1.add(val11);
            power = power * base;
            power1 = power1.multiply(new BigInteger(String.valueOf(base)));
        }

        //return num;
        return num1;
    }

    public /*int*/ BigInteger result(String n, int b){
        return toDecimal(n, b);
    }
}
