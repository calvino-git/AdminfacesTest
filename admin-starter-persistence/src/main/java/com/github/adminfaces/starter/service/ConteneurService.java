/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.ConteneurCongoTerminal;
import com.github.adminfaces.starter.model.ConteneurCongoTerminal_;
import com.github.adminfaces.starter.model.ConteneurCongoTerminal;
import com.github.adminfaces.template.exception.BusinessException;
import static com.github.adminfaces.template.util.Assert.has;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.deltaspike.data.api.criteria.Criteria;

/**
 *
 * @author Calvin ILOKI
 */
@Stateless
public class ConteneurService extends CrudService<ConteneurCongoTerminal, Integer> implements Serializable {

    protected Criteria<ConteneurCongoTerminal, ConteneurCongoTerminal> configRestrictions(Filter<ConteneurCongoTerminal> filter) {

        Criteria<ConteneurCongoTerminal, ConteneurCongoTerminal> criteria = criteria();

        //create restrictions based on parameters map
        if (filter.hasParam("id")) {
            criteria.eq(ConteneurCongoTerminal_.id, filter.getIntParam("id"));
        }

        if (filter.hasParam("debutDate") && filter.hasParam("finDate")) {
            criteria.between(ConteneurCongoTerminal_.date, filter.getStringParam("debutDate"), filter.getStringParam("finDate"));
        } else if (filter.hasParam("debutDate")) {
            criteria.gtOrEq(ConteneurCongoTerminal_.date, filter.getStringParam("debutDate"));
        } else if (filter.hasParam("finDate")) {
            criteria.ltOrEq(ConteneurCongoTerminal_.date, filter.getStringParam("finDate"));
        }

        //create restrictions based on filter entity
        if (has(filter.getEntity())) {
            ConteneurCongoTerminal filterEntity = filter.getEntity();
            if (has(filterEntity.getEscale())) {
                criteria.likeIgnoreCase(ConteneurCongoTerminal_.escale, "%" + filterEntity.getEscale());
            }

            if (has(filterEntity.getDate())) {
                criteria.eq(ConteneurCongoTerminal_.date, filterEntity.getDate());
            }

            if (has(filterEntity.getTrafic())) {
                criteria.likeIgnoreCase(ConteneurCongoTerminal_.trafic, "%" + filterEntity.getTrafic() + "%");
            }
        }
        return criteria;
    }

    public void beforeInsert(ConteneurCongoTerminal cct) {
        validate(cct);
    }

    public void beforeUpdate(ConteneurCongoTerminal cct) {
        validate(cct);
    }

    public void validate(ConteneurCongoTerminal cct) {
        BusinessException be = new BusinessException();
        if (!cct.hasEscale()) {
            be.addException(new BusinessException("ConteneurCongoTerminal escale cannot be empty"));
        }
        if (!cct.hasTrafic()) {
            be.addException(new BusinessException("ConteneurCongoTerminal trafic cannot be empty"));
        }

        if (!has(cct.getDate())) {
            be.addException(new BusinessException("ConteneurCongoTerminal date cannot be empty"));
        }

        if (count(criteria()
                .eqIgnoreCase(ConteneurCongoTerminal_.trafic, cct.getTrafic())
                .notEq(ConteneurCongoTerminal_.id, cct.getId())) > 0) {

            be.addException(new BusinessException("ConteneurCongoTerminal trafic must be unique"));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<ConteneurCongoTerminal> listByModel(String escale) {
        return criteria()
                .likeIgnoreCase(ConteneurCongoTerminal_.escale, escale)
                .getResultList();
    }

    public List<String> getEscales(String query) {
        return criteria()
                .select(String.class, attribute(ConteneurCongoTerminal_.escale))
                .likeIgnoreCase(ConteneurCongoTerminal_.escale, "%" + query + "%")
                .getResultList();
    }
}
