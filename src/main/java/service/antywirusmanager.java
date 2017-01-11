package service;

/**
 * Created by Redbullek on 2017-01-10.
 */


import domain.antywirus;
import domain.pakiet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class antywirusmanager implements Iantywirusmanager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Long addantywirus(antywirus antywirus) {
        antywirus.setId(null);
        return (Long) sessionFactory.getCurrentSession()
                .save(antywirus);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<antywirus> Wszystkieantywirusy() {
        return sessionFactory
                .getCurrentSession()
                .getNamedQuery("antywirus.all")
                .list();
    }

    @Override
    public antywirus findByIdantywirus(Long id) {
        return (antywirus) sessionFactory
                .getCurrentSession()
                .get(antywirus.class,id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public antywirus findByNazwa(String nazwa) {
        return (antywirus) sessionFactory
                .getCurrentSession()
                .getNamedQuery("antywirus.byNazwa")
                .setString("name",nazwa)
                .uniqueResult();
    }

    @Override
    public void deleteantywirus(antywirus antywirus) {
        antywirus = (domain.antywirus) sessionFactory.getCurrentSession()
                .get(domain.antywirus.class, antywirus.getId());
        sessionFactory.getCurrentSession().delete(antywirus);
    }


    //---------------------------------------------------------------
    //                  PAKIET
    //---------------------------------------------------------------

    @Override
    public Long addpakiet(pakiet pakiet) {
        pakiet.setId(null);
        return (Long) sessionFactory.getCurrentSession()
                .save(pakiet);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<pakiet> WszystkiePakiety() {
        return sessionFactory
                .getCurrentSession()
                .getNamedQuery("pakiet.all")
                .list();
    }

    @Override
    public pakiet findByIdpakiet(Long id) {
        return (pakiet) sessionFactory
                .getCurrentSession()
                .get(pakiet.class,id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public pakiet findBynazwa(String prod) {
        return (pakiet) sessionFactory
                .getCurrentSession()
                .getNamedQuery("pakiet.byfunkcje")
                .setString("prod",prod)
                .uniqueResult();
    }

    @Override
    public void deletepakiet(pakiet pakiet) {
        pakiet = (domain.pakiet) sessionFactory
                .getCurrentSession()
                .get(domain.pakiet.class,pakiet.getId());
        sessionFactory.getCurrentSession().delete(pakiet);
    }

   // @Override
    @SuppressWarnings("unchecked")
    public List<antywirus>  getAllantywiruspakiet(Long id_antywirus) {
        antywirus antywirus = (domain.antywirus) sessionFactory.getCurrentSession()
                .get(domain.antywirus.class, id_antywirus);
        List<domain.antywirus> allCategoryModel=new ArrayList();
        allCategoryModel=sessionFactory.getCurrentSession()
                .getNamedQuery("antywirus.bypakiet")
                .setLong("category",id_antywirus)
                .list();

        return allCategoryModel;
    }
/*
    @Override
    public void addantywirusTopakiet(Long id_antywirus, Long id_pakiet) {
        pakiet paczka = (pakiet) sessionFactory.getCurrentSession()
                .get(pakiet.class, id_pakiet);
        antywirus antywirus = (antywirus) sessionFactory.getCurrentSession()
                .get(antywirus.class, id_antywirus);
        antywirus.setPakiety();
    }
*/
}
