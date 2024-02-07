package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7721ece9-2896-45ba-98fd-f0a56b04894c")
public abstract class Cell {
    @objid ("bb8a192b-7089-4322-9a8e-a2d5a4a2d227")
    private int row;

    @objid ("5e7b575d-17ce-44b5-b5af-15a191126f8c")
    private int col;

    @objid ("95ab5487-3009-478e-b704-e893e32099cb")
    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    @objid ("633fb1d2-7be3-4487-b977-baf52595b117")
    protected abstract void moveInMovable(Movable movable);

    @objid ("d0d853ae-72f3-4722-bdf1-e3edada6fb1a")
    protected abstract Movable moveOutMovable();

    @objid ("681cca90-2342-474b-b2fd-d95a0aa33643")
    protected abstract CellStatus getCellStatus();

    @objid ("d28cb264-856f-458b-a5d7-2838b4dc2367")
    protected int getRow() {
        return row;
    }

    @objid ("92c89b02-bbe9-4636-9bcc-bf7dda8fa94f")
    protected int getCol() {
        return col;
    }

    @objid ("b89690f6-5385-4f31-a104-e64a64356e52")
    protected abstract boolean isTarget();

}
