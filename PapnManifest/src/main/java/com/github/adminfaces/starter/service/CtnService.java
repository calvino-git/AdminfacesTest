/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adminfaces.starter.service;

import com.github.adminfaces.persistence.model.Filter;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.starter.model.Ctn;
import com.github.adminfaces.starter.model.Ctn_;
import com.github.adminfaces.starter.model.Ctn;
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
public class CtnService extends CrudService<Ctn, Integer> implements Serializable {

    protected Criteria<Ctn, Ctn> configRestrictions(Filter<Ctn> filter) {

        Criteria<Ctn, Ctn> criteria = criteria();

        //create restrictions based on parameters map
        if (filter.hasParam("id")) {
            criteria.eq(Ctn_.id, filter.getIntParam("id"));
        }

        if (filter.hasParam("debutDate") && filter.hasParam("finDate")) {
            criteria.between(Ctn_.date, filter.getStringParam("debutDate"), filter.getStringParam("finDate"));
        } else if (filter.hasParam("debutDate")) {
            criteria.gtOrEq(Ctn_.date, filter.getStringParam("debutDate"));
        } else if (filter.hasParam("finDate")) {
            criteria.ltOrEq(Ctn_.date, filter.getStringParam("finDate"));
        }

        //create restrictions based on filter entity
        if (has(filter.getEntity())) {
            Ctn filterEntity = filter.getEntity();
            if (has(filterEntity.getEscale())) {
                criteria.likeIgnoreCase(Ctn_.escale, "%" + filterEntity.getEscale());
            }

            if (has(filterEntity.getDate())) {
                criteria.eq(Ctn_.date, filterEntity.getDate());
            }

            if (has(filterEntity.getTrafic())) {
                criteria.likeIgnoreCase(Ctn_.trafic, "%" + filterEntity.getTrafic() + "%");
            }
        }
        return criteria;
    }

    public void beforeInsert(Ctn cct) {
        validate(cct);
    }

    public void beforeUpdate(Ctn cct) {
        validate(cct);
    }

    public void validate(Ctn cct) {
        BusinessException be = new BusinessException();
        if (!cct.hasEscale()) {
            be.addException(new BusinessException("Ctn escale cannot be empty"));
        }
        if (!cct.hasTrafic()) {
            be.addException(new BusinessException("Ctn trafic cannot be empty"));
        }

        if (!has(cct.getDate())) {
            be.addException(new BusinessException("Ctn date cannot be empty"));
        }

        if (count(criteria()
                .eqIgnoreCase(Ctn_.trafic, cct.getTrafic())
                .notEq(Ctn_.id, cct.getId())) > 0) {

            be.addException(new BusinessException("Ctn trafic must be unique"));
        }

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    public List<Ctn> listByModel(String escale) {
        return criteria()
                .likeIgnoreCase(Ctn_.escale, escale)
                .getResultList();
    }

    public List<String> getEscales(String query) {
        return criteria()
                .select(String.class, attribute(Ctn_.escale))
                .likeIgnoreCase(Ctn_.escale, "%" + query + "%")
                .getResultList();
    }
}
