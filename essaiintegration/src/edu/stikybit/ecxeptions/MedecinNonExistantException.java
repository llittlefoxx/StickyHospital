/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stikybit.ecxeptions;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class MedecinNonExistantException extends Exception{
    @Override
    public String getMessage() {
		return "Operation non aboutie : Le medecin n'existe pas !.";
	}
}
