package ped2_preda;

public class EditionDistance {

    private char[] sourceString, targetString;
    private int[][] table;
    private Transformation[] transformations;
    private int changes = 0;

    public EditionDistance(String sourceString, String targetString) {
        this.sourceString = sourceString.toCharArray();
        this.targetString = targetString.toCharArray();
        this.table = new int[this.sourceString.length][this.targetString.length];
        this.transformations = new Transformation[this.sourceString.length * this.targetString.length];
    }

    public void computeEditionDistance() {
        compute(sourceString, targetString, sourceString.length, targetString.length, table);
        findTransformations(sourceString, targetString, sourceString.length, targetString.length, table, transformations);
    }

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
    }

    private void findTransformations(char[] sourceString, char[] targetString, int n, int m, int[][] table, Transformation[] transformations) {
        int i, j, k;
        i = n - 1;
        j = m - 1;
        k = table[n - 1][m - 1];
        changes = k;
        while (k > 0) {
            if (table[i][j] == (table[i - 1][j] + 1)) {
                transformations[k] = new Transformation(TransformationType.BORRADO, j + 1);
                k--;
                i--;
            } else if (table[i][j] == (table[i][j - 1] + 1)) {
                transformations[k] = new Transformation(TransformationType.INSERCION, j, targetString[j]);
                k--;
                j--;
            } else if (table[i][j] == (table[i - 1][j - 1] + 1)) {
                transformations[k] = new Transformation(TransformationType.SUSTITUCION, j, targetString[j]);
                k--;
                j--;
                i--;
            } else if (table[i][j] == table[i - 1][j - 1]) {
                j--;
                i--;
            }
        }
    }

    public void printResults() {
        for (int[] row : table) {
            for (int n : row) {
                System.out.print(" " + n);
            }
            System.out.println(" ");
        }
        System.out.println("\n\r ---------------------------------------- \n\r");
        System.out.println("Cambios -> " + changes);
        for (Transformation t : transformations) {
            if (t != null) {
                System.out.println(t.toString());
            }
        }
    }

    public String getSourceString() {
        return new String(sourceString);
    }

    public String getTargetString() {
        return new String(targetString);
    }

    public Transformation[] getTransformations() {
        return transformations;
    }

    public int getChanges() {
        return changes;
    }
}
