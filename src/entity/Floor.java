package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7e214f86-7b18-4c0b-a5ad-6f6bb24f39f6")
public class Floor extends Cell {
    @objid ("0151dd6d-d775-4307-a760-97476b6050ff")
    private boolean isTarget;

    @objid ("a0280e9f-d442-4e26-8188-2b79f34251cc")
    private Movable movable;

    @objid ("73a986e0-ab34-4e14-9f9b-0c1064323132")
    public Floor(int row, int col, boolean isTarget) {
        super(row, col);
        this.isTarget = isTarget;
    }

    @objid ("5471bef8-ab8e-4634-a1cf-5156d67f23cd")
    public void moveInMovable(Movable movable) {
        // Déplace un movable sur cette cellule
        this.movable = movable;
        movable.changeFloor(this);
    }

    @objid ("1d7de189-ed42-4907-bb4c-5d66bf3e33b2")
    public Movable moveOutMovable() {
        // Déplace un movable hors de cette cellule
        Movable movableOut = this.movable;
        this.movable = null;
        return movableOut;
    }

    @objid ("1beb9af6-6301-4aa5-8220-777010d8134a")
    protected CellStatus getCellStatus() {
        // Renvoie le statut de la cellule
        if (movable != null){
            if (isTarget) {
                switch (movable.getMovableStatus()) {
                case BOX:
                    return CellStatus.BOX_TARGET;
                case PLAYER:
                    return CellStatus.PLAYER_TARGET;
                default:
                    return null;
                }
            }
            else {
                return movable.getMovableStatus();
            }
        }
        if (isTarget) return CellStatus.TARGET;
        return CellStatus.FLOOR;
    }

    @objid ("62b75a4e-489b-466c-af67-52b49f6d7ce6")
    protected boolean isTarget() {
        return isTarget;
    }

}
