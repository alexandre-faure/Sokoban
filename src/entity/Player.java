package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("23888269-f683-4c4c-b540-24e139bdb803")
public class Player extends Movable {
    @objid ("8b99c257-facc-494b-a410-936eba36c667")
    protected CellStatus getMovableStatus() {
        // Renvoie le statut de l'objet
        return CellStatus.PLAYER;
    }

}
