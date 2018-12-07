package ped2_preda;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class PED2_PREDA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new PED2_PREDA(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PED2_PREDA(String[] args) throws Exception {

        System.out.println("Iniciando aplicación...\n\r");

        ArgsHelper params = new ArgsHelper(args);

        IOHelper ioHelper = new IOHelper();

        EditionDistance editionDistance;
        if (params.getFileIn() != null) {
            editionDistance = ioHelper.readFromFile(params.getFileIn());
        } else {
            editionDistance = ioHelper.readFromKeyboard();
        }

        if (editionDistance != null) {
            editionDistance.computeEditionDistance();
            if (params.getFileOut() != null) {
                ioHelper.printResultsToFile(editionDistance, params.getFileOut());
            } else {
                ioHelper.printResultsToConsole(editionDistance);
            }
        }

        System.out.println("\n\rFinalizando aplicación...");

    }

}
