
import java.util.Scanner;
import java.io.*;

public class TextForm {
    public static int GetNumColumn()    //Get Text Width number
    {
        Scanner sc = new Scanner(System.in);
        int c = 0;
        do {
            System.out.println("Enter the number of columns");
            c = sc.nextInt();
            if (c > 100 || c < 30) {
                System.out.println("Invalid value. Try again.");
                        }
        } while (c > 100 || c < 30);
        return c;
    }
    public static String GetInputFile() //Get the string representing the text
    {                                   //file
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the input file");
        String i = sc.nextLine();
        i = i.toLowerCase();
        return i;
    }
    public static String GetOutputFile()    //Get string represnting output file
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the output file");
        String o = sc.nextLine();
        o = o.toLowerCase();
        return o;
    }
    public static boolean Existance(File fileName)
    {
        return fileName.exists();       //get value of existance
    }
    public static void main(String[] args) throws FileNotFoundException
    {
        int column = GetNumColumn();
        boolean exist;
        String inMain;

        do {
            inMain = GetInputFile();
            File inputFile = new File(inMain);
            exist = Existance(inputFile);
            if (exist == false) {   //throw exception if file does not exist
                throw new FileNotFoundException("File not found");
            }
        } while (exist == false);

        Scanner si = null;
        si = new Scanner(new File (inMain));
        while(si.hasNextLine()==true)
        {
            System.out.println(si.nextLine());
        }
        System.out.println();
        File outputFile = null;
        //Existance(outputFile);

        PrintWriter so = null;
        //so = new PrintWriter(outputFile);
        boolean done = false;

        do {
            Scanner sc = new Scanner(System.in);
            String outMain = GetOutputFile();
            outputFile = new File(outMain);
            exist = Existance(outputFile);
            if (exist == true) {        //overwrite checker
                System.out.println("File already exists... overwrite? (Enter Yes or No)");
                String c = sc.nextLine();
                c = c.toLowerCase();
                if (c.contains("y")) {
                    done = true;
                }
                else {
                    done = false;
                }
            }
            else {
                done = true;
                }
        } while (done = false);


        System.out.println("Formatted text below is:");
        System.out.println();
        so = new PrintWriter(outputFile);
        String h = "";
        for(int n = 1;n <=column;n++)       //header with * 's
        {
            so.print("*");
            h = h+"*";
        }
        System.out.println(h);
        so.println();
        System.out.println();
        so.println();
        System.out.println();
        //System.lineSeperator();

        si = new Scanner(new File (inMain));
        String line = "";
        String word;
        while(si.hasNext()) {       //write output file and print to console
            word = si.next();
            if((line.length() + word.length()+1) > column) {
        so.println(line);
                System.out.println(line);
                line = word + " ";
            }
            else
                line += word + " ";
        }
        so.println(line);
        System.out.println(line);

        //flush and close the output file
        so.close();

    }
}
