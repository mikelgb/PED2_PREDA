package ped2_preda;

/**
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

    /**
     * Método principal, realiza las llamadas a las clases auxiliares para el correcto funcionamiento
     * del proyecto
     *
     * @param args
     * @throws Exception
     */
    public PED2_PREDA(String[] args) throws Exception {

        System.out.println("Iniciando aplicación...\n\r");
        /**
         * Llama al validador de argumentos de entrada pasando la lista de argumentos a ser tratados
        */
        ArgsHelper params = new ArgsHelper(args);
        /**
         * Crea el gestor de entrada/salida de datos
         */
        IOHelper ioHelper = new IOHelper();
        /**
         * Si se ha especificado el argumento de mostrar ayuda, la muestra
         */
        if (params.showHelp()) {
            params.printHelp();
        } else {
            /**
             * Gestiona la entrada de datos mediante fichero o consola
             */
            EditionDistance editionDistance;
            if (params.getFileIn() != null) {
                editionDistance = ioHelper.readFromFile(params.getFileIn());
            } else {
                editionDistance = ioHelper.readFromKeyboard();
            }
            /**
             * Asigna la salida o no de traza
            */
            editionDistance.setTrace(params.showTrace());

            if (editionDistance != null) {
                /**
                 * Llama al método de computación del algoritmo principal
                 */
                editionDistance.computeEditionDistance();
                /**
                 * Muestra el resultado de la ejecución en fichero o consola
                 */
                if (params.getFileOut() != null) {
                    ioHelper.printResultsToFile(editionDistance, params.getFileOut());
                    System.out.println("Resultado impreso en " + params.getFileOut().getPath());
                } else {
                    ioHelper.printResultsToConsole(editionDistance);
                }
            }

            System.out.println("\n\rFinalizando aplicación...");
        }
    }

}
