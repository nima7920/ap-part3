package GUI_components;

import GUI_components.GCard;
import GUI_components.GUIConsts;

import java.awt.*;
import java.io.File;

public class GHeroPower extends GCard {

    public GHeroPower(Dimension cardDimension, String cardName, int xPos, int yPos) {
        super(cardDimension, cardName, xPos, yPos);
    }

    public GHeroPower(Dimension cardDimension, String cardName, Point O) {
        super(cardDimension, cardName, O);
    }

    @Override
    protected File getImageFile() {
        return GUIConsts.gHeroPower.getFile(getCardName());
    }
}
