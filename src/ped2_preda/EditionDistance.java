package ped2_preda;

/**
 * @author Miquel Ginés Borràs
 */
public class EditionDistance {

    private char[] sourceString, targetString;
    private int[][] table;
    private Transformation[] transformations;
    private int changes = 0;

    private boolean trace = false;

    /**
     * Constructor
     *
     * @param sourceString Palabra origen
     * @param targetString Palabra objetivo
     */
    public EditionDistance(String sourceString, String targetString) {
            this.sourceString = sourceString.toCharArray();
            this.targetString = targetString.toCharArray();
            this.table = new int[this.sourceString.length][this.targetString.length];
            this.transformations = new Transformation[this.sourceString.length * this.targetString.length];
            System.out.println("Palabra original: " + sourceString);
            System.out.println("Palabra objetivo: " + targetString);
    }

    /**
     * Llama a los métodos de computo del algoritmo
     */
    public void computeEditionDistance() {
        if (!sourceString.equals(targetString)) {
            compute(sourceString, targetString, sourceString.length, targetString.length, table);
            findTransformations(sourceString, targetString, sourceString.length, targetString.length, table, transformations);
        }
    }

    /**
     * Realiza las iteraciones necesarias para calcular la tabla de transformaciones
     */
    private void compute(char[] sourceString, char[] targetString, int n, int m, int[][] table) {
        int i, j, tmp;
        for (i = 0; i < n; i++) {
            table[i][0] = i;
        }
        for (j = 0; j < m; j++) {
            table[0][j] = j;
        }
        for (i = 1; i < n; i++) {
            for (j = 1; j < m; j++) {
                tmp = Math.min(1 + table[i - 1][j], 1 + table[i][j - 1]);
                if (sourceString[i] == targetString[j]) {
                    table[i][j] = Math.min(tmp, table[i - 1][j - 1]);
                } else {
                    table[i][j] = Math.min(tmp, table[i - 1][j - 1] + 1);
                }
            }
        }
        if (trace) {
            showCalculatedTable();
        }
    }

    /**
     * Muestra la tabla de transformaciones por consola
     */
    private void showCalculatedTable() {
        System.out.println("\r\nTabla calculada:");
        for (int[] row : table) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("");
        }
    }

    /**
     * Calcula las transformaciones necesarias a partir de la tabla de transformaciones
     */
    private void findTransformations(char[] sourceString, char[] targetString, int n, int m, int[][] table, Transformation[] transformations) {
        int i = n - 1;
        int j = m - 1;
        int k = table[n - 1][m - 1];
        changes = k;
        char[] changedString = targetString.clone();
        while (k > 0) {
            if (table[i][j] == (table[i - 1][j] + 1)) {
                transformations[k] = new Transformation(TransformationType.BORRADO, j + 1);
                changedString = transformations[k].setChangedString(sourceString, changedString);
                k--;
                i--;
            } else if (table[i][j] == (table[i][j - 1] + 1)) {
                transformations[k] = new Transformation(TransformationType.INSERCION, j, targetString[j]);
                changedString = transformations[k].setChangedString(sourceString, changedString);
                k--;
                j--;
            } else if (table[i][j] == (table[i - 1][j - 1] + 1)) {
                transformations[k] = new Transformation(TransformationType.SUSTITUCION, j, targetString[j]);
                changedString = transformations[k].setChangedString(sourceString, changedString);
                k--;
                j--;
                i--;
            } else if (table[i][j] == table[i - 1][j - 1]) {
                j--;
                i--;
            }
        }
    }

    public Transformation[] getTransformations() {
        return transformations;
    }

    public int getChanges() {
        return changes;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }
}
