/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kpc.bandeira.resource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author KennediMalheiros
 */
public class ContextoInicial {

    private static Context ctx;

    private ContextoInicial() {
        try {
            ctx = (Context) new InitialContext();
        } catch (NamingException ex) {
            System.out.println(ex.getCause());
        }
    }

    public static Context getContext() {

        if (ctx == null) {
            new ContextoInicial();
        }
        return ctx;

    }
}
