import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please use a space after each character in the line!!");
        System.out.println("Negative numbers must be written together with the sign");
        System.out.println("Enter the expression");


        Calculator calcMain = new Calculator();

        String str = "";

        try( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println();
        System.out.println(calcMain.calculate(str));


    }
}
