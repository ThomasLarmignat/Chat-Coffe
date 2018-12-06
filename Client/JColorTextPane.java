import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
 
public class JColorTextPane extends JTextPane {
     
    private Color defColor = Color.BLACK;
     
    public void setDefColor(Color c) {
        defColor = c;
    }
     
    public Color getDefColor() {
        return defColor;
    }
     
    public void append(String msg) {
        append(msg, defColor);
    }
     
    public void append(String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, c);   
        setCaretPosition(getDocument().getLength());
        setCharacterAttributes(aset, false);
        replaceSelection(msg);
    }
     
}