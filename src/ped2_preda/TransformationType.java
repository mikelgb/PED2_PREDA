package ped2_preda;

/**
 * @author Miquel Ginés Borràs
 */
public enum TransformationType {
    BORRADO("borrado"),
    INSERCION("inserción"),
    SUSTITUCION("sustitución");

    private String operation;

    /**
     * Enumerado con los diferentes tipos de transformación posible
     *
     * @param operation Transformación realizada
     */
    TransformationType(String operation) {
        this.operation = operation;
    }

    public String operation() {
        return operation;
    }
}
