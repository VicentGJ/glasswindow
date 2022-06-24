package quantity.glasswindow.core.customExceptions;

import quantity.glasswindow.core.Model;

public class ModelNotFoundException extends Exception{
    public ModelNotFoundException(Model m) {
        super("Model for " + m.getType() + " not found");
    }
    public ModelNotFoundException(String id) {
        super("Model with id "+ id +" not found");
    }
}
