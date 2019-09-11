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
import jpa.beans.Navire;

/**
 *
 * @author Admin
 */
public class NavireJpaController implements Serializable {

    public NavireJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Navire navire) throws PreexistingEntityException, Exception {
        if (navire.getEscaleCollection() == null) {
            navire.setEscaleCollection(new ArrayList<Escale>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Escale> attachedEscaleCollection = new ArrayList<Escale>();
            for (Escale escaleCollectionEscaleToAttach : navire.getEscaleCollection()) {
                escaleCollectionEscaleToAttach = em.getReference(escaleCollectionEscaleToAttach.getClass(), escaleCollectionEscaleToAttach.getEscleunik());
                attachedEscaleCollection.add(escaleCollectionEscaleToAttach);
            }
            navire.setEscaleCollection(attachedEscaleCollection);
            em.persist(navire);
            for (Escale escaleCollectionEscale : navire.getEscaleCollection()) {
                Navire oldNacleunikOfEscaleCollectionEscale = escaleCollectionEscale.getNacleunik();
                escaleCollectionEscale.setNacleunik(navire);
                escaleCollectionEscale = em.merge(escaleCollectionEscale);
                if (oldNacleunikOfEscaleCollectionEscale != null) {
                    oldNacleunikOfEscaleCollectionEscale.getEscaleCollection().remove(escaleCollectionEscale);
                    oldNacleunikOfEscaleCollectionEscale = em.merge(oldNacleunikOfEscaleCollectionEscale);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNavire(navire.getNacleunik()) != null) {
                throw new PreexistingEntityException("Navire " + navire + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Navire navire) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Navire persistentNavire = em.find(Navire.class, navire.getNacleunik());
            Collection<Escale> escaleCollectionOld = persistentNavire.getEscaleCollection();
            Collection<Escale> escaleCollectionNew = navire.getEscaleCollection();
            Collection<Escale> attachedEscaleCollectionNew = new ArrayList<Escale>();
            for (Escale escaleCollectionNewEscaleToAttach : escaleCollectionNew) {
                escaleCollectionNewEscaleToAttach = em.getReference(escaleCollectionNewEscaleToAttach.getClass(), escaleCollectionNewEscaleToAttach.getEscleunik());
                attachedEscaleCollectionNew.add(escaleCollectionNewEscaleToAttach);
            }
            escaleCollectionNew = attachedEscaleCollectionNew;
            navire.setEscaleCollection(escaleCollectionNew);
            navire = em.merge(navire);
            for (Escale escaleCollectionOldEscale : escaleCollectionOld) {
                if (!escaleCollectionNew.contains(escaleCollectionOldEscale)) {
                    escaleCollectionOldEscale.setNacleunik(null);
                    escaleCollectionOldEscale = em.merge(escaleCollectionOldEscale);
                }
            }
            for (Escale escaleCollectionNewEscale : escaleCollectionNew) {
                if (!escaleCollectionOld.contains(escaleCollectionNewEscale)) {
                    Navire oldNacleunikOfEscaleCollectionNewEscale = escaleCollectionNewEscale.getNacleunik();
                    escaleCollectionNewEscale.setNacleunik(navire);
                    escaleCollectionNewEscale = em.merge(escaleCollectionNewEscale);
                    if (oldNacleunikOfEscaleCollectionNewEscale != null && !oldNacleunikOfEscaleCollectionNewEscale.equals(navire)) {
                        oldNacleunikOfEscaleCollectionNewEscale.getEscaleCollection().remove(escaleCollectionNewEscale);
                        oldNacleunikOfEscaleCollectionNewEscale = em.merge(oldNacleunikOfEscaleCollectionNewEscale);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = navire.getNacleunik();
                if (findNavire(id) == null) {
                    throw new NonexistentEntityException("The navire with id " + id + " no longer exists.");
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
            Navire navire;
            try {
                navire = em.getReference(Navire.class, id);
                navire.getNacleunik();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The navire with id " + id + " no longer exists.", enfe);
            }
            Collection<Escale> escaleCollection = navire.getEscaleCollection();
            for (Escale escaleCollectionEscale : escaleCollection) {
                escaleCollectionEscale.setNacleunik(null);
                escaleCollectionEscale = em.merge(escaleCollectionEscale);
            }
            em.remove(navire);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Navire> findNavireEntities() {
        return findNavireEntities(true, -1, -1);
    }

    public List<Navire> findNavireEntities(int maxResults, int firstResult) {
        return findNavireEntities(false, maxResults, firstResult);
    }

    private List<Navire> findNavireEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Navire.class));
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

    public Navire findNavire(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Navire.class, id);
        } finally {
            em.close();
        }
    }

    public int getNavireCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Navire> rt = cq.from(Navire.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
