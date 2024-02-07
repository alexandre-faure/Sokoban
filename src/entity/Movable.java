package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("3179ca75-59a4-426b-99fb-327fe819f57d")
public abstract class Movable {
    @objid ("b3e7a9ff-f76a-4d6e-8c17-99ad54fbe87f")
    private Floor floor;

    @objid ("2a11263b-9bb5-43e6-ba04-781ce6cd4e4b")
    protected abstract CellStatus getMovableStatus();

    @objid ("35a84b36-3418-4a32-9c02-8dc928631129")
    protected void changeFloor(Floor floor) {
        this.floor = floor;
    }

    @objid ("7c46f65d-ddc3-45fc-8067-826dc7ced6c2")
    protected Floor getCell() {
        return floor;
    }

}
