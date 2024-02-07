package entity;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fdbb5cb5-918b-4f98-86ad-2bd2f5e6421a")
public enum CellStatus {
    WALL,
    FLOOR,
    PLAYER,
    TARGET,
    BOX,
    PLAYER_TARGET,
    BOX_TARGET;
}
