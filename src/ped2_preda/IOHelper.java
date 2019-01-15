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

    /**
     * Lee los datos desde un fichero
     *
     * @param file Fichero de entrada
     * @return EditionDistance con los datos leídos
     */
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

    /**
     * Lee los datos desde la consola
     *
     * @return EditionDistance con los datos leídos
     */
    public EditionDistance readFromKeyboard() {
        String sourceString = readLineFromConsole("Introduce la palabra origen:");
        String targetString = readLineFromConsole("Introduce la palabra objetivo:");
        return new EditionDistance(sourceString, targetString);
    }

    /**
     * Imprime los resultados en un fichero
     *
     * @param editionDistance EditionDistance con los resultados
     * @param file Fichero donde escribir los resultados
     */
    public void printResultsToFile(EditionDistance editionDistance, File file) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
            String printString = "";
            if (editionDistance.getChanges() > 0) {
                 printString += editionDistance.getChanges() + "\r\n";
                for (Transformation t : editionDistance.getTransformations()) {
                    if (t != null) {
                        printString += t.getTransformationType().operation() + " " + (t.getPosition() + 1) + " " + t.getChangedString() + "\r\n";
                    }
                }
            } else {
                printString += "Las palabras son iguales.";
            }
            writer.write(printString);
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Imprime los resultados por consola
     *
     * @param editionDistance EditionDistance con los resultados
     */
    public void printResultsToConsole(EditionDistance editionDistance) {
        if (editionDistance.getChanges() > 0) {
            System.out.println("\n\rNúmeros de cambios realizados: " + editionDistance.getChanges());
            System.out.println("\n\rTransformaciones realizadas:");
            for (Transformation t : editionDistance.getTransformations()) {
                if (t != null) {
                    System.out.println(t.toString());
                }
            }
        } else {
            System.out.println("Las palabras son iguales.");
        }
    }

    /**
     * Lee una línea de texto entrada por teclado por consola
     *
     * @param msg Mensaje a mostrar antes de leer
     * @return Cadena de texto leída
     */
    private String readLineFromConsole(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\r" + msg);
        return sc.nextLine().trim().replaceAll(" +", " ");
    }

}
