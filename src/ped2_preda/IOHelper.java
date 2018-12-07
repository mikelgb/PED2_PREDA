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
        return null;
//        Set<Integer> numSet = null;
//        int sumGoal = 0, maxSubset = 0;
//        BufferedReader br = null;
//        FileReader fr = null;
//        try {
//            fr = new FileReader(file);
//            br = new BufferedReader(fr);
//            String currentLine;
//            if (((currentLine = br.readLine()) != null)) {
//                numSet = readNumSet(currentLine);
//            }
//            if (((currentLine = br.readLine()) != null)) {
//                sumGoal = Integer.parseInt(currentLine);
//            }
//            if (((currentLine = br.readLine()) != null)) {
//                maxSubset = Integer.parseInt(currentLine);
//            }
//
//            br.close();
//            fr.close();
//            return  new SubsetSum(new ArrayList<Integer>(numSet), sumGoal, maxSubset);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } catch (Exception e) {
//            System.err.println("ERROR: Se ha introducido un valor duplicado en el conjunto de números de entrada.");
//            return null;
//        }
    }

    public EditionDistance readFromKeyboard() {
        String sourceString = "", targetString = "";
        try {
            sourceString = readLineFromConsole("Introduce la palabra origen:");
            targetString = readLineFromConsole("Introduce la palabra objetivo:");
        } catch (NumberFormatException e) {
            // Se ha introducido algo que no era un número, se parará la ejecución
            System.err.println("ERROR: Se ha introducido algún carácter no válido.");
            return null;
        } catch (Exception e) {
            System.err.println("ERROR: Se ha introducido un valor duplicado en el conjunto de números de entrada.");
            return null;
        }
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
