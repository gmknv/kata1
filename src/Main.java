import java.util.Scanner;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println(calc(console.nextLine()));
    }

    private static int romToArab(String romNum) {
        String[] romNums = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return Arrays.asList(romNums).indexOf(romNum) + 1;
    }

    private static String arabToRom(int arabNum) {
        String[] romDigs = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] romTens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        return romTens[arabNum / 10] + romDigs[arabNum % 10];
    }

    private static boolean isOK(String val) {
        boolean is = true;
        String[] valSplit = val.split(" ");
        if (valSplit.length == 3) {
            if (!(valSplit[1].equals("+") || valSplit[1].equals("-") || valSplit[1].equals("*") || valSplit[1].equals("/")))
                is = false;
            if (!(isRom(valSplit[0]) & isRom(valSplit[2]) | isArab(valSplit[0]) & isArab(valSplit[2])))
                is = false;
        } else
            is = false;
        return is;

    }

    private static boolean isRom(String val) {
        String[] romDigs = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return Arrays.asList(romDigs).contains(val);
    }

    private static boolean isArab(String val) {
        String[] arabDigs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        return Arrays.asList(arabDigs).contains(val);
    }

    public static String calc(String input) throws Exception {
        String ret;
        if (!isOK(input))
            throw new Exception();

        int res = 0, num1 = 0, num2 = 0;
        String[] inputSplit = input.split(" ");
        if (isArab(inputSplit[0])) {
            num1 = Integer.parseInt(inputSplit[0]);
            num2 = Integer.parseInt(inputSplit[2]);
        }
        if (isRom(inputSplit[0])) {
            num1 = romToArab(inputSplit[0]);
            num2 = romToArab(inputSplit[2]);
        }
        switch (inputSplit[1]) {
            case "+" -> res = num1 + num2;
            case "-" -> res = num1 - num2;
            case "*" -> res = num1 * num2;
            case "/" -> res = num1 / num2;
        }


        if (isRom(inputSplit[0])) {
            if (res > 0)
                ret = arabToRom(res);
            else
                throw new Exception();
        } else
            ret = Integer.toString(res);


        return ret;
    }

}

