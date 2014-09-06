/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kpc.bandeira.core;

import javax.ejb.Remote;

/**
 * @author KennediMalheiros
 * @author pcego
 */
@Remote
public interface IRepositoryBandeira extends IRepository<Bandeira> {

    Bandeira getBandeiraSigla(String sigla);

}
