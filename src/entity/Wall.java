package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("52de8964-8901-4bbf-ac6f-32a5101f6893")
public class Wall extends Cell {
    @objid ("38872569-cab2-41af-b105-3c85649616b6")
    public Wall(int row, int col) {
        super(row, col);
    }

    @objid ("f847b64b-cc06-440b-ac8a-46d3d7b3bcd7")
    protected void moveInMovable(Movable movable) {
    }

    @objid ("77f61a9a-cb45-42e8-bc88-aba1e4a4222f")
    protected Movable moveOutMovable() {
        return null;
    }

    @objid ("43018773-8804-4f22-b509-7cba2f6f6269")
    protected CellStatus getCellStatus() {
        // Renvoie le statut de la cellule
        return CellStatus.WALL;
    }

    @objid ("4697362f-9e77-4471-b00a-37592e4da212")
    @Override
    protected boolean isTarget() {
        return false;
    }

}
