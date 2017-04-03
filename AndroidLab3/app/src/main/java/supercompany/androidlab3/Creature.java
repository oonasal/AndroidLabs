package supercompany.androidlab3;

import java.io.Serializable;

/**
 * Created by Oona on 23-Mar-16.
 */
public class Creature implements Serializable {

    private String creatureName;
    private static final long serialVersionUID = 2L;

    public Creature(String creatureName) {
        this.creatureName = creatureName;
    }

    public String getCreatureName() {
        return this.creatureName;
    }


}
