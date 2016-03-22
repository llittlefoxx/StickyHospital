/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class ValidationResult {
 public enum Type { ERROR, WARNING, SUCCESS }
    private final String message;
    private final Type type;
 
    public ValidationResult(String message, Type type) {
        this.message = message;
        this.type = type;
    }
 
    public final String getMessage() {
        return message;
    }
 
    public final Type getType() {
        return type;
    }   
}
