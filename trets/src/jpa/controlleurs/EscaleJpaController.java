/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controlleurs;

import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.beans.Escale;
import jpa.beans.GeneralInfo;
import jpa.beans.Navire;

/**
 *
 * @author Admin
 */
public class EscaleJpaController implements Serializable {

    public EscaleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Escale escale) throws PreexistingEntityException, Exception {
        if (escale.getGeneralInfoCollection() == null) {
            escale.setGeneralInfoCollection(new ArrayList<GeneralInfo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Navire nacleunik = escale.getNacleunik();
            if (nacleunik != null) {
                nacleunik = em.getReference(nacleunik.getClass(), nacleunik.getNacleunik());
                escale.setNacleunik(nacleunik);
            }
            Collection<GeneralInfo> attachedGeneralInfoCollection = new ArrayList<GeneralInfo>();
            for (GeneralInfo generalInfoCollectionGeneralInfoToAttach : escale.getGeneralInfoCollection()) {
                generalInfoCollectionGeneralInfoToAttach = em.getReference(generalInfoCollectionGeneralInfoToAttach.getClass(), generalInfoCollectionGeneralInfoToAttach.getId());
                attachedGeneralInfoCollection.add(generalInfoCollectionGeneralInfoToAttach);
            }
            escale.setGeneralInfoCollection(attachedGeneralInfoCollection);
            em.persist(escale);
            if (nacleunik != null) {
                nacleunik.getEscaleCollection().add(escale);
                nacleunik = em.merge(nacleunik);
            }
            for (GeneralInfo generalInfoCollectionGeneralInfo : escale.getGeneralInfoCollection()) {
                Escale oldIdEscaleOfGeneralInfoCollectionGeneralInfo = generalInfoCollectionGeneralInfo.getIdEscale();
                generalInfoCollectionGeneralInfo.setIdEscale(escale);
                generalInfoCollectionGeneralInfo = em.merge(generalInfoCollectionGeneralInfo);
                if (oldIdEscaleOfGeneralInfoCollectionGeneralInfo != null) {
                    oldIdEscaleOfGeneralInfoCollectionGeneralInfo.getGeneralInfoCollection().remove(generalInfoCollectionGeneralInfo);
                    oldIdEscaleOfGeneralInfoCollectionGeneralInfo = em.merge(oldIdEscaleOfGeneralInfoCollectionGeneralInfo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEscale(escale.getEscleunik()) != null) {
                throw new PreexistingEntityException("Escale " + escale + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Escale escale) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Escale persistentEscale = em.find(Escale.class, escale.getEscleunik());
            Navire nacleunikOld = persistentEscale.getNacleunik();
            Navire nacleunikNew = escale.getNacleunik();
            Collection<GeneralInfo> generalInfoCollectionOld = persistentEscale.getGeneralInfoCollection();
            Collection<GeneralInfo> generalInfoCollectionNew = escale.getGeneralInfoCollection();
            if (nacleunikNew != null) {
                nacleunikNew = em.getReference(nacleunikNew.getClass(), nacleunikNew.getNacleunik());
                escale.setNacleunik(nacleunikNew);
            }
            Collection<GeneralInfo> attachedGeneralInfoCollectionNew = new ArrayList<GeneralInfo>();
            for (GeneralInfo generalInfoCollectionNewGeneralInfoToAttach : generalInfoCollectionNew) {
                generalInfoCollectionNewGeneralInfoToAttach = em.getReference(generalInfoCollectionNewGeneralInfoToAttach.getClass(), generalInfoCollectionNewGeneralInfoToAttach.getId());
                attachedGeneralInfoCollectionNew.add(generalInfoCollectionNewGeneralInfoToAttach);
            }
            generalInfoCollectionNew = attachedGeneralInfoCollectionNew;
            escale.setGeneralInfoCollection(generalInfoCollectionNew);
            escale = em.merge(escale);
            if (nacleunikOld != null && !nacleunikOld.equals(nacleunikNew)) {
                nacleunikOld.getEscaleCollection().remove(escale);
                nacleunikOld = em.merge(nacleunikOld);
            }
            if (nacleunikNew != null && !nacleunikNew.equals(nacleunikOld)) {
                nacleunikNew.getEscaleCollection().add(escale);
                nacleunikNew = em.merge(nacleunikNew);
            }
            for (GeneralInfo generalInfoCollectionOldGeneralInfo : generalInfoCollectionOld) {
                if (!generalInfoCollectionNew.contains(generalInfoCollectionOldGeneralInfo)) {
                    generalInfoCollectionOldGeneralInfo.setIdEscale(null);
                    generalInfoCollectionOldGeneralInfo = em.merge(generalInfoCollectionOldGeneralInfo);
                }
            }
            for (GeneralInfo generalInfoCollectionNewGeneralInfo : generalInfoCollectionNew) {
                if (!generalInfoCollectionOld.contains(generalInfoCollectionNewGeneralInfo)) {
                    Escale oldIdEscaleOfGeneralInfoCollectionNewGeneralInfo = generalInfoCollectionNewGeneralInfo.getIdEscale();
                    generalInfoCollectionNewGeneralInfo.setIdEscale(escale);
                    generalInfoCollectionNewGeneralInfo = em.merge(generalInfoCollectionNewGeneralInfo);
                    if (oldIdEscaleOfGeneralInfoCollectionNewGeneralInfo != null && !oldIdEscaleOfGeneralInfoCollectionNewGeneralInfo.equals(escale)) {
                        oldIdEscaleOfGeneralInfoCollectionNewGeneralInfo.getGeneralInfoCollection().remove(generalInfoCollectionNewGeneralInfo);
                        oldIdEscaleOfGeneralInfoCollectionNewGeneralInfo = em.merge(oldIdEscaleOfGeneralInfoCollectionNewGeneralInfo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = escale.getEscleunik();
                if (findEscale(id) == null) {
                    throw new NonexistentEntityException("The escale with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Escale escale;
            try {
                escale = em.getReference(Escale.class, id);
                escale.getEscleunik();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The escale with id " + id + " no longer exists.", enfe);
            }
            Navire nacleunik = escale.getNacleunik();
            if (nacleunik != null) {
                nacleunik.getEscaleCollection().remove(escale);
                nacleunik = em.merge(nacleunik);
            }
            Collection<GeneralInfo> generalInfoCollection = escale.getGeneralInfoCollection();
            for (GeneralInfo generalInfoCollectionGeneralInfo : generalInfoCollection) {
                generalInfoCollectionGeneralInfo.setIdEscale(null);
                generalInfoCollectionGeneralInfo = em.merge(generalInfoCollectionGeneralInfo);
            }
            em.remove(escale);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Escale> findEscaleEntities() {
        return findEscaleEntities(true, -1, -1);
    }

    public List<Escale> findEscaleEntities(int maxResults, int firstResult) {
        return findEscaleEntities(false, maxResults, firstResult);
    }

    private List<Escale> findEscaleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Escale.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Escale findEscale(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Escale.class, id);
        } finally {
            em.close();
        }
    }

    public int getEscaleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Escale> rt = cq.from(Escale.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
