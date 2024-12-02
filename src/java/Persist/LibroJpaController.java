/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persist;

import Model.Libro;
import Persist.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author oscar
 */
public class LibroJpaController implements Serializable {

    private static final EntityManagerFactory emf;
    
    static{
        try{
            emf = Persistence.createEntityManagerFactory("LibreriaPU");
        }catch(Throwable ex){
            throw new ExceptionInInitializerError();
        }
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libro.getId();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
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

    public List<Libro> filterLibro(Libro libro) {
        EntityManager em = getEntityManager();
        List<Libro> libros = null; 
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Libro> cq = cb.createQuery(Libro.class);
            Root<Libro> libroRoot = cq.from(Libro.class);

            Predicate tituloFiltro = cb.like(libroRoot.get("titulo"), "%" + libro.getTitulo() + "%");
            Predicate autorFilto = cb.like(libroRoot.get("autor"), "%" + libro.getAutor() + "%");
            Predicate editorialFiltro = cb.like(libroRoot.get("editorial"), "%" + libro.getEditorial() + "%");
            Predicate anioFiltro = cb.like(libroRoot.get("anio"), "%" + libro.getAnio() + "%");
            
            Predicate condicionF = cb.or(tituloFiltro, autorFilto, editorialFiltro, anioFiltro);
            cq.select(libroRoot).where(condicionF);

            TypedQuery<Libro> query = em.createQuery(cq);
            libros = query.getResultList();
        } catch (PersistenceException e) {
            // Manejar la excepción, por ejemplo, registrarla o lanzar una excepción personalizada
            e.printStackTrace(); 
        } finally {
            em.close();
        }
        return libros;
    }
    
    public Libro findLibro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
