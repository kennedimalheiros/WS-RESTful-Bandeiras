/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kpc.bandeira.dao;

import br.com.kpc.bandeira.core.Bandeira;
import br.com.kpc.bandeira.core.IRepositoryBandeira;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author KennediMalheiros
 */
@Stateless
public class BandeiraDao extends GenericDao<Bandeira> implements IRepositoryBandeira {

    public BandeiraDao() {
        super(Bandeira.class);
    }

    @Override
    public Bandeira getBandeiraSigla(String sg) {
        try {

            Query query = getManager().createQuery("select bd from Bandeira bd where bd.sigla = :sigla");
            query.setParameter("sigla", sg);
            return (Bandeira) query.getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bandeira> getTodasBandeiras() {
        try {

            Query query = getManager().createQuery("select b from Bandeira b order by b.nome ");

            return query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
