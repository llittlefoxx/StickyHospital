/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

import javafx.scene.control.Control;

/**
 *
 * @author Tlili Mohamed Ali
 */
interface Validator<C extends Control> {
    public ValidationResult validate(C control);
}