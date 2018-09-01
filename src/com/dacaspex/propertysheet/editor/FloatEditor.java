package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.FloatProperty;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FloatEditor extends DefaultCellEditor implements TableCellEditor, KeyListener {

    private FloatProperty property;
    private PropertySheet sheet;

    public FloatEditor(FloatProperty property, PropertySheet sheet) {
        super(new JTextField());

        this.property = property;
        this.sheet = sheet;

        super.getComponent().addKeyListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent event) {

        JTextField textField = (JTextField) super.getComponent();
        String value = (String) textField.getText();

        if (property.getValidator().validate(value)) {
            property.setValue(Float.parseFloat(value));
            textField.setBackground(sheet.getBackgroundColor());

            sheet.dispatchUpdateEvent(property);
        } else {
            textField.setBackground(sheet.getInvalidColor());
        }

        // TODO: Validate
        // TODO: Fire update event

    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }

}
