package ped2_preda;

/**
 * @author Miquel Ginés Borràs
 */
public class Transformation {

    /**
     * Tipo de la transformación
     */
    private TransformationType transformationType;
    /**
     * POsición de la transformación
     */
    private int position;
    /**
     * Caracter nuevo (inserción o sustitución)
     */
    private char character;
    /**
     * Cadena tras modificación
     */
    private char[] changedString;

    /**
     * Constructor
     *
     * @param transformationType Tipo de transformación
     * @param position Posición de la transformación
     * @param character Nuevo catracter
     */
    public Transformation(TransformationType transformationType, int position, char character) {
        this.transformationType = transformationType;
        this.position = position;
        this.character = character;
    }

    /**
     * Constructor
     *
     * @param transformationType Tipo de transformación
     * @param position Posición de la transformación
     */
    public Transformation(TransformationType transformationType, int position) {
        this.transformationType = transformationType;
        this.position = position;
    }

    /**
     * Genera la cadena post-transformación
     *
     * @param sourceString Cadena inicial
     * @param targetString Cadena post-transformación
     * @return Cadena pre-transformación
     */
    public char[] setChangedString(char[] sourceString, char[] targetString) {
        this.changedString = targetString.clone();

        switch (transformationType) {
            case INSERCION:
                targetString[position] = 0;
                break;
            case SUSTITUCION:
                targetString[position] = sourceString[position];
                break;
            case BORRADO:
                break;
        }
        return targetString;
    }

    public TransformationType getTransformationType() {
        return transformationType;
    }

    public int getPosition() {
        return position;
    }

    public String getChangedString() {
        return new String(changedString);
    }

    @Override
    public String toString() {
        String msg = "Realizada operación de " + transformationType.operation() + " en la posición " + (position + 1);
        if (character != 0) {
            msg += ", nuevo carácter \'" + character + "\'";
        }
        msg += " -> " + new String(changedString).replaceAll(" ", "");
        return msg;
    }
}
