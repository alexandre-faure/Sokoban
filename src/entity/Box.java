package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9870b974-bc26-4c7d-a0eb-ebcf930d7a87")
public class Box extends Movable {
    @objid ("b0139c0b-2c69-43d2-b4ca-89d75e847c97")
    protected CellStatus getMovableStatus() {
        // Renvoie le statut de l'objet
        return CellStatus.BOX;
    }

}
