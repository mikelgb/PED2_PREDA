package ped2_preda;

/**
 * @author Miquel Ginés Borràs
 */
public class Transformation {

    private TransformationType transformationType;
    private int position;
    private char character;

    public Transformation(TransformationType transformationType, int position, char character) {
        this.transformationType = transformationType;
        this.position = position;
        this.character = character;
    }

    public Transformation(TransformationType transformationType, int position) {
        this.transformationType = transformationType;
        this.position = position;
    }

    @Override
    public String toString() {
        String msg = "Realizada operación de " + transformationType.operation() + " en la posición " + position;
        if (character != 0) {
            msg += ", nuevo carácter \'" + character + "\'.";
        } else {
            msg += ".";
        }
        return msg;
    }
}
