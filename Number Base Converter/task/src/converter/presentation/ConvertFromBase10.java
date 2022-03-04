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

    static String decimalToBinary(double num, int k_prec)
    {
        String binary = "";

        // Fetch the integral part of decimal number
        int Integral = (int) num;

        // Fetch the fractional part decimal number
        double fractional = num - Integral;

        // Conversion of integral part to
        // binary equivalent
        while (Integral > 0)
        {
            int rem = Integral % 2;

            // Append 0 in binary
            binary += ((char)(rem + '0'));

            Integral /= 2;
        }

        // Reverse string to get original binary
        // equivalent
        binary = reverse(binary);

        // Append point before conversion of
        // fractional part
        binary += ('.');

        // Conversion of fractional part to
        // binary equivalent
        while (k_prec-- > 0)
        {
            // Find next bit in fraction
            fractional *= 2;
            int fract_bit = (int) fractional;

            if (fract_bit == 1)
            {
                fractional -= fract_bit;
                binary += (char)(1 + '0');
            }
            else
            {
                binary += (char)(0 + '0');
            }
        }

        return binary;
    }


    static String reverse(String input)
    {
        char[] temparray = input.toCharArray();
        int left, right = 0;
        right = temparray.length - 1;

        for (left = 0; left < right; left++, right--)
        {
            // Swap values of left and right
            char temp = temparray[left];
            temparray[left] = temparray[right];
            temparray[right] = temp;
        }
        return String.valueOf(temparray);
    }


}
