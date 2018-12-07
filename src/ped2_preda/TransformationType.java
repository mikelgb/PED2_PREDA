package ped2_preda;

/**
 * @author Miquel Ginés Borràs
 */
public enum TransformationType {
    BORRADO("borrado"),
    INSERCION("inserción"),
    SUSTITUCION("sustitución");

    private String operation;

    TransformationType(String operation) {
        this.operation = operation;
    }

    public String operation() {
        return operation;
    }
}
