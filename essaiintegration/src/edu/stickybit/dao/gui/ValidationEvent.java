/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class ValidationEvent extends Event {
    public static final EventType<ValidationEvent> ANY =
            new EventType<ValidationEvent>(Event.ANY, "VALIDATION");
 
    private final ValidationResult result;
 
    public ValidationEvent(ValidationResult result) {
        super(ANY);
        this.result = result;
    }
 
    public final ValidationResult getResult() { return result; }
}