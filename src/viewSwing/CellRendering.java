package viewSwing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import entity.CellStatus;

@objid ("e750eb88-b9fc-44af-8cf7-4a39ea9738e6")
public class CellRendering {
    @objid ("91bc4d1e-a266-406d-816b-e90ab70a9e29")
    private BufferedImage wall;

    @objid ("41e3eb1e-887d-4ef5-80ee-f9e4295268fe")
    private BufferedImage floor;

    @objid ("d79e5703-f955-4b41-899f-f64dc5cc5dba")
    private BufferedImage target;

    @objid ("a5c0605b-ef1c-4957-989a-e73a710c3320")
    private BufferedImage box;

    @objid ("f97e4db5-d3dd-4baf-b44f-e403c5cb5b76")
    private BufferedImage player;

    @objid ("8f99c960-a89a-4aab-aeea-12db474f5fb8")
    private BufferedImage boxTarget;

    @objid ("f8ec8981-5cbc-4bd8-ab57-accbef25577d")
    private BufferedImage playerTarget;

    @objid ("5f43b422-f4b1-4b69-a48f-c5666f3d6733")
    public CellRendering() {
        try {
            wall = ImageIO.read(new File("images/wall.png"));
            floor = ImageIO.read(new File("images/floor.png"));
            target = ImageIO.read(new File("images/target.png"));
            box = ImageIO.read(new File("images/box.png"));
            player = ImageIO.read(new File("images/player.png"));
            boxTarget = ImageIO.read(new File("images/box-target.png"));
            playerTarget = ImageIO.read(new File("images/player-target.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @objid ("c16d3804-7aad-4c1d-a8a9-3af9fe012054")
    public Image getCellRendering(CellStatus cellStatus, int size) {
        switch(cellStatus) {
        case WALL:
            return wall.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        case FLOOR:
            return floor.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        case TARGET:
            return target.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        case BOX:
            return box.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        case PLAYER:
            return player.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        case BOX_TARGET:
            return boxTarget.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        case PLAYER_TARGET:
            return playerTarget.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        default:
            return null;
        }
    }

}
