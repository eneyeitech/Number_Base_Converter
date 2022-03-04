package converter.presentation;

import java.math.BigInteger;
import java.util.Scanner;

public class ConvertFromBase10 {
    private final Scanner scanner;

    public ConvertFromBase10(Scanner scanner) {
        this.scanner = scanner;
    }

    public void view() {

            System.out.print("Enter number in decimal system: ");
            int line = scanner.nextInt();
            System.out.print("Enter target base: ");
            int line1 = scanner.nextInt();
            //String result = fromDecimal(line1, line);
        String result = fromDecimal(line1, new BigInteger(String.valueOf(line1)));
            System.out.printf("Conversion result: %s\n", result);

    }

    // To return char for a value. For
// example '2' is returned for 2.
// 'A' is returned for 10. 'B' for 11
    private char reVal(BigInteger num)
    {
        if (num.intValue() >= 0 && num.intValue() <= 9)
            return (char)(num.intValue() + 48);
        else
            //return (char)(num - 10 + 65);
            return (char)(num.intValue() - 10 + 97);
    }

    // Function to convert a given decimal number
// to a base 'base' and
    private String fromDecimal(int base1, BigInteger inputNum)
    {
        String s = "";

        // Convert input number is given
        // base by repeatedly dividing it
        // by base and taking remainder
        //while (inputNum > 0)
        //while (inputNum.signum() == 1)
        do{
            BigInteger baseBI = new BigInteger(String.valueOf(base1));
            //s += reVal(inputNum % base1);
            s += reVal(inputNum.remainder(baseBI) );
            //inputNum /= base1;
            inputNum = inputNum.divide(baseBI);
        }while (inputNum.signum() == 1);
        StringBuilder ix = new StringBuilder();

        // append a string into StringBuilder input1
        ix.append(s);

        // Reverse the result
        return new String(ix.reverse());
    }

    public String result(int b, BigInteger n){
        return fromDecimal(b, n);
    }

}
