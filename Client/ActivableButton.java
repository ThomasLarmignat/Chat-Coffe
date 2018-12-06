import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;


class ActivableButton implements DocumentListener {
 
   private JButton button;
   private JTextField[] textFields;
 
   public ActivableButton(JButton button, JTextField[] textFields) {
      this.button = button;
      this.textFields = textFields;
      for (int i = 0; i < textFields.length; i++) {
         textFields[i].getDocument().addDocumentListener(this);
      }
   }
 
   public void insertUpdate(DocumentEvent e) {
      scanTextFields();
   }
 
   public void removeUpdate(DocumentEvent e) {
      scanTextFields();
   }
 
   public void changedUpdate(DocumentEvent e) {
   	}
 
   private void scanTextFields() {
      boolean filled = true;
      for (int i = 0; i < textFields.length; i++) {
         if (textFields[i].getText().length() == 0) {
            filled = false;
            break;
         } 
      }
      button.setEnabled(filled);
   }
}
 
 
