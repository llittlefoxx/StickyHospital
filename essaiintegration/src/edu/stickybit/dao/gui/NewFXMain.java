/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextInputControl;
/**
 *
 * @author Tlili Mohamed Ali
 */
class TextInputValidatorPane<C extends TextInputControl> extends ValidatorPane<C> {
 
    private InvalidationListener textListener = new InvalidationListener() {           
        @Override public void invalidated(Observable o) {
            final Validator v = getValidator();
            final ValidationResult result = v != null ?
                v.validate(getContent()) :
                new ValidationResult("", ValidationResult.Type.SUCCESS);
 
            handleValidationResult(result);
        }
    };
 
    public TextInputValidatorPane() {
        contentProperty().addListener(new ChangeListener<C>() {                
            @Override public void changed(ObservableValue<? extends C> ov, C oldValue, C newValue) {
                if (oldValue != null) oldValue.textProperty().removeListener(textListener);
                if (newValue != null) newValue.textProperty().addListener(textListener);
            }
        });
    }
 
    public TextInputValidatorPane(C field) {
        this();
        setContent(field);
    }
 
}