package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.persistence.util.Messages.addDetailMessage;
import static com.github.adminfaces.persistence.util.Messages.getMessage;
import static com.github.adminfaces.template.util.Assert.has;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.github.adminfaces.persistence.bean.CrudMB;
import com.github.adminfaces.persistence.service.CrudService;
import com.github.adminfaces.persistence.service.Service;
import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.ConteneurCongoTerminal;
import com.github.adminfaces.starter.service.CarService;
import com.github.adminfaces.starter.service.ConteneurService;
import com.github.adminfaces.template.exception.BusinessException;

/**
 * Created by rmpestano on 12/02/17.
 */
@Named
@ViewScoped
public class CctListMB extends CrudMB<ConteneurCongoTerminal> implements Serializable {

    @Inject
    ConteneurService ctnService;

    @Inject
    @Service
    CrudService<ConteneurCongoTerminal, Integer> ctnCrudService; //generic injection example

    @Inject
    public void initService() {
        setCrudService(ctnService);
    }

    public List<String> completeModel(String query) {
        List<String> result = ctnService.getEscales(query);
        return result;
    }

    public void findCtnById(Integer id) {
        if (id == null) {
            throw new BusinessException("Provide Conteneur ID to load");
        }
        ConteneurCongoTerminal ctnFound = ctnCrudService.findById(id);
        if (ctnFound == null) {
            throw new BusinessException(String.format("No ctn found with id %s", id));
        }
        selectionList.add(ctnFound);
        getFilter().addParam("id", id);
    }

    public void delete() {
        int numCtns = 0;
        for (ConteneurCongoTerminal selectedCtn : selectionList) {
            numCtns++;
            ctnService.remove(selectedCtn);
        }
        selectionList.clear();
        addDetailMessage(numCtns + " ctns deleted successfully!");
        clear();
    }

    public String getSearchCriteria() {
        StringBuilder sb = new StringBuilder(21);

        String traficParam = null;
        ConteneurCongoTerminal ctnFilter = filter.getEntity();

        Integer idParam = null;
        if (filter.hasParam("id")) {
            idParam = filter.getIntParam("id");
        }

        if (has(idParam)) {
            sb.append("<b>id</b>: ").append(idParam).append(", ");
        }

        if (filter.hasParam("trafic")) {
            traficParam = filter.getStringParam("trafic");
        } else if (has(ctnFilter) && ctnFilter.getTrafic() != null) {
            traficParam = ctnFilter.getTrafic();
        }

        if (has(traficParam)) {
            sb.append("<b>trafic</b>: ").append(traficParam).append(", ");
        }

        String escaleParam = null;
        if (filter.hasParam("model")) {
            escaleParam = filter.getStringParam("model");
        } else if (has(ctnFilter) && ctnFilter.getModel() != null) {
            escaleParam = ctnFilter.getModel();
        }

        if (has(modelParam)) {
            sb.append("<b>model</b>: ").append(modelParam).append(", ");
        }

        Double priceParam = null;
        if (filter.hasParam("price")) {
            priceParam = filter.getDoubleParam("price");
        } else if (has(carFilter) && carFilter.getModel() != null) {
            priceParam = carFilter.getPrice();
        }

        if (has(priceParam)) {
            sb.append("<b>price</b>: ").append(priceParam).append(", ");
        }

        if (filter.hasParam("minPrice")) {
            sb.append("<b>").append(getMessage("label.minPrice")).append("</b>: ").append(filter.getParam("minPrice")).append(", ");
        }

        if (filter.hasParam("maxPrice")) {
            sb.append("<b>").append(getMessage("label.maxPrice")).append("</b>: ").append(filter.getParam("maxPrice")).append(", ");
        }

        int commaIndex = sb.lastIndexOf(",");

        if (commaIndex != -1) {
            sb.deleteCharAt(commaIndex);
        }

        if (sb.toString().trim().isEmpty()) {
            return "No search criteria";
        }

        return sb.toString();
    }

}
