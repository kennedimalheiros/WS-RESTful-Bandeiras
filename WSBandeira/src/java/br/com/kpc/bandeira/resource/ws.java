/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kpc.bandeira.resource;

import br.com.kpc.bandeira.core.Bandeira;
import br.com.kpc.bandeira.core.IRepositoryBandeira;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pcego
 * @author KennediMalheiros
*/
@XmlRootElement
@Path("/wsbandeira")
public class ws {

    private Bandeira bandeira = new Bandeira();

    private IRepositoryBandeira rpbBandeira;

    // anotation @GET define o método HTTP ao qual o recurso responde
    @GET
    // anotation @Produces define o tipo de retorno do recurso
    @Produces(MediaType.APPLICATION_JSON)
    // anotation @Path define o caminho para o recurso
    @Path("/getBandeira/{sigla}")
    public String keyValidatorGen(@PathParam("sigla") String param) {
        try {
            rpbBandeira = (IRepositoryBandeira) ContextoInicial.getContext().lookup("java:global/WSBandeira/BandeiraDao");

            //Loading object computer
            bandeira = rpbBandeira.getBandeiraSigla(param);

        } catch (Exception ex) {
            Logger.getLogger(ws.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(bandeira.getUrl());
        return bandeira.getUrl();

    }

}
