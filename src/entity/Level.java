package entity;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import control.Controller;

@objid ("fecd1c3c-c326-4994-a5c2-1c2e5cb5595e")
public class Level {
// Définition des niveaux
    @objid ("b0f4a9d6-7c0b-4625-820a-ab1d6f0a9dec")
     static final String[] LVL1 = {"WWWWWW",
	  		  			   "W    W",
	  		  			   "WP B W",
	  		  			   "W   TW",
			  			   "WWWWWW"};

    @objid ("b50a2f62-9339-4ca2-84ba-1cec5d8ceb8f")
     static final String[] LVL2 = {
    		"WWWWWWW",
    		"WWTT  W",
    		"WW WBPW",
    		"WW B  W",
    		"WW B WW",
    		"WT  WWW",
    		"WWWWWWW"};

    @objid ("8924fdc9-2877-43e7-9985-ae65b5beafa9")
     static final String[] LVL3 = {
    		"WWWWWWWWWWW",
    		"W         W",
    		"W B  W B  W",
    		"WWW  WWW  W",
    		"WT    PW TW",
    		"WWWWWWWWWWW"};

    @objid ("7a135cd9-f53d-4c33-906f-b040f8bcec10")
     static final String[] LVL4 = {
    		"WWWWWWWWWW",
    		"WWW WW  WW",
    		"WT  P  BWW",
    		"WW WWWW WW",
    		"WW WWWW  W",
    		"WWB T T  W",
    		"W   WWW WW",
    		"W    B  WW",
    		"WTB WWWWWW",
    		"WW  WWWWWW",
    		"WWWWWWWWWW"};

    @objid ("9f4893ff-2e99-4dc1-8fd1-017e8113fc1a")
     static final String[] LVL5 = {
    		"WWWWWWWWWWWW",
    		"WWWWWWWW  WW",
    		"WWWWWWWW BWW",
    		"WWPWWWWW  WW",
    		"WW WT  W  WW",
    		"W  B   B  WW",
    		"W   W     TW",
    		"WWB WWTWW WW",
    		"WWT  WWWW WW",
    		"WW B WWWW WW",
    		"WW  WWWWWTWW",
    		"WWWWWWWWWWWW"};

    @objid ("5afc87a8-28be-40c9-ba5a-3fda17ce940d")
    public static String[] getBoardTemplate(int level) {
        // Renvoie le board correspondant au niveau demandé
           switch(level) {
           case 1:
               return LVL1;
           case 2:
               return LVL2;
           case 3:
               return LVL3;
           case 4:
               return LVL4;
           case 5:
               return LVL5;
        default:
            throw new InvalidParameterException("Un niveau inexistant a été demandé.");
               }
    }

}
