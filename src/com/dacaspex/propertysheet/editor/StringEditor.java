package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.StringProperty;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StringEditor extends DefaultCellEditor implements TableCellEditor, KeyListener {

    private StringProperty property;
    private PropertySheet sheet;

    public StringEditor(StringProperty property, PropertySheet sheet) {
        super(new JTextField());

        this.property = property;
        this.sheet = sheet;

        super.getComponent().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent event) {

        // Get value from editor component
        JTextField textField = (JTextField) super.getComponent();
        String value = textField.getText();

        // Validate input
        if (property.getValidator().validate(value)) {
            property.setValue(value);
            textField.setBackground(sheet.getBackgroundColor());

            // Dispatch event to indicate something happened
            sheet.dispatchUpdateEvent(property);
        } else {
            textField.setBackground(sheet.getInvalidColor());
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }

}

