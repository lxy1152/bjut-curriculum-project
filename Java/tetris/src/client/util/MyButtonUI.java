package client.util;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButtonUI extends BasicButtonUI implements Serializable{
	protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect){
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        Icon icon = b.getIcon();
        Icon tmpIcon = null;
        if(icon == null) {
            return;
        }
        Icon selectedIcon = null;
        if (model.isSelected()) {
            selectedIcon = (Icon) b.getSelectedIcon();
            if (selectedIcon != null) {
                icon = selectedIcon;
            }
        }
        if(model.isPressed() && model.isArmed()) {
            tmpIcon = (Icon) b.getPressedIcon();
            if(tmpIcon != null) {
                clearTextShiftOffset();
            }
        } else if(b.isRolloverEnabled() && model.isRollover()) {
            if(model.isSelected()) {
                tmpIcon = (Icon) b.getRolloverSelectedIcon();
                if (tmpIcon == null) {
                    tmpIcon = selectedIcon;
                }
            }
            if (tmpIcon == null) {
                tmpIcon = (Icon) b.getRolloverIcon();
            }
        }
        if(tmpIcon != null) {
            icon = tmpIcon;
        }
        if(model.isPressed() && model.isArmed()) {
            icon.paintIcon(c, g, iconRect.x + getTextShiftOffset(),
                    iconRect.y + getTextShiftOffset());
        } else {
            icon.paintIcon(c, g, iconRect.x, iconRect.y);
        }
    }
}
