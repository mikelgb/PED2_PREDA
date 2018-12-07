package ped2_preda;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Miquel Ginés Borràs
 */
public class IOHelper {

    public IOHelper() {
    }

    public EditionDistance readFromFile(File file) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String sourceString = "", targetString = "";
            String currentLine;
            if (((currentLine = br.readLine()) != null)) {
                sourceString = currentLine;
            }
            if (((currentLine = br.readLine()) != null)) {
                targetString = currentLine;
            }
            br.close();
            fr.close();
            return new EditionDistance(sourceString, targetString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("ERROR: Se ha introducido un valor duplicado en el conjunto de números de entrada.");
            return null;
        }
    }

    public EditionDistance readFromKeyboard() {
        String sourceString = readLineFromConsole("Introduce la palabra origen:");
        String targetString = readLineFromConsole("Introduce la palabra objetivo:");
        return new EditionDistance(sourceString, targetString);
    }

    public void printResultsToFile(EditionDistance editionDistance, File file) {
//        try {
//            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
//            String printString = "";
//            for (int[] result : subsetSum.getResults()) {
//                for (int num : result) {
//                    printString += num + " ";
//                }
//                printString += "\r\n";
//            }
//            writer.write(printString);
//            writer.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    public void printResultsToConsole(EditionDistance editionDistance) {
        System.out.println("\r\n---------------------------------------------------------------------------");
        System.out.println("\r\nPalabra original: " + editionDistance.getSourceString());
        System.out.println("Palabra objetivo: " + editionDistance.getTargetString());
        System.out.println("\n\rNúmeros de cambios realizados: " + editionDistance.getChanges());
        System.out.println("\n\rTransformaciones realizadas:");

        for (Transformation t : editionDistance.getTransformations()) {
            if (t != null) {
                System.out.println(t.toString());
            }
        }
    }

    private String readLineFromConsole(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\r" + msg);
        return sc.nextLine().trim().replaceAll(" +", " ");
    }

}
