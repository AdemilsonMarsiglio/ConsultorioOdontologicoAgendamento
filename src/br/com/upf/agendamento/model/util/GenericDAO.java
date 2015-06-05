package br.com.upf.agendamento.model.util;

import br.com.parcerianet.generic.modelo.util.controls.Bean;
import br.com.parcerianet.generic.modelo.util.controls.ObjectBean;
import br.com.parcerianet.generic.modelo.util.controls.OrderBy;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Id;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Dialect;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Ademilsom
 */
public abstract class GenericDAO<T> {

    private String mensagem = "";
    private Object[] defaultCriterions = null;
    private Object[] criterions = null;
    private Object[] projections = null;
    private OrderBy[] orderByClause;
    private OrderBy[] defaultOrderBy;
    private Object[] join;
    private int maxFetchSize = -1;
    private boolean execCommit = true;
    private Session session;
    private Session currentSession;

    public boolean incluir(List obj) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
        }
        try {
            for (Iterator localIterator = obj.iterator(); localIterator.hasNext();) {
                Object item = localIterator.next();
                sessao.save(item);
            }
            if (this.execCommit) {
                if (!sessao.getTransaction().isActive()) {
                    sessao.getTransaction().begin();
                }
                sessao.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            Object item;
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "", e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean alterar(List obj) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
        }
        try {
            for (Iterator localIterator = obj.iterator(); localIterator.hasNext();) {
                Object item = localIterator.next();
                sessao.update(item);
            }
            if (this.execCommit) {
                if (!sessao.getTransaction().isActive()) {
                    sessao.getTransaction().begin();
                }
                sessao.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            Object item;
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean alterar(Class type, Object[] criterions, Object[][] updates) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            Criteria criteria = sessao.createCriteria(type);
            for (Object criterion : criterions) {
                criteria.add((Criterion) criterion);
            }
            List list = criteria.list();
            for (Object item : list) {
                for (int i = 0; i < updates.length; i++) {
                    ((Bean) item).setAttribute(updates[i][0].toString(), updates[i][1]);
                }
                sessao.update(item);
            }
            sessao.getTransaction().commit();
            return true;
        } catch (Exception e) {
            List list;
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean incluirOuAlterar(ArrayList obj) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
        }
        try {
            for (Iterator localIterator = obj.iterator(); localIterator.hasNext();) {
                Object item = localIterator.next();
                sessao.saveOrUpdate(item);
            }
            if (this.execCommit) {
                if (!sessao.getTransaction().isActive()) {
                    sessao.getTransaction().begin();
                }
                sessao.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            Object item;
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean adicionarObjetosNaSessao(List<ObjectBean> obj) {
        if (!isConnected()) {
            throw new RuntimeException();
        }
        try {
            for (ObjectBean item : obj) {
                if (item.getAcao() == 'I') {
                    this.session.save(item.getObjeto());
                } else if (item.getAcao() == 'A') {
                    this.session.update(item.getObjeto());
                }
            }
        } catch (Exception e) {
            this.mensagem = ("Erro ao marcar objetos para inserção/alteração:\n" + e.getMessage());
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        }
        return true;
    }

    public boolean excluir(Object obj) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            sessao.delete(obj);
            if (this.execCommit) {
                sessao.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean excluir(Class type, Criterion[] criterions) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            Criteria criteria = sessao.createCriteria(type);
            for (Object criterion : criterions) {
                criteria.add((Criterion) criterion);
            }
            List list = criteria.list();
            for (Object item : list) {
                sessao.delete(item);
            }
            if (this.execCommit) {
                sessao.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            List list;
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean excluir(Object[] objects) {
        if ((objects == null) || (objects.length == 0)) {
            return false;
        }
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            for (Object obj : objects) {
                if (obj != null) {
                    sessao.delete(obj);
                }
            }
            if (this.execCommit) {
                sessao.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            sessao.getTransaction().rollback();

            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public T getRegistro(Class type, int id) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            if (getJoin() != null) {
                Criteria criteria = sessao.createCriteria(type);
                String nmField = null;
                for (Field field : type.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        nmField = field.getName();
                        break;
                    }
                }
                if (nmField != null) {
                    criteria.add(Restrictions.eq(nmField, Integer.valueOf(id)));
                } else {
                    return null;
                }
                for (Object novoJoin : getJoin()) {
                    String associationPath = (String) ((Object[]) (Object[]) novoJoin)[0];
                    JoinType joinType = (JoinType) ((Object[]) (Object[]) novoJoin)[1];

                    criteria.createCriteria(associationPath, joinType);
                }
                return (T) criteria.uniqueResult();
            }
            T encontrado = (T) sessao.load(type, Integer.valueOf(id));
            Hibernate.initialize(type.cast(encontrado));
            return encontrado;
        } catch (Exception e) {
            String nmField;

            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public T getRegistro(Class type, String id) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            if (getJoin() != null) {
                Criteria criteria = sessao.createCriteria(type);
                String nmField = null;
                for (Field field : type.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        nmField = field.getName();
                        break;
                    }
                }
                if (nmField != null) {
                    criteria.add(Restrictions.eq(nmField, id));
                } else {
                    return null;
                }
                for (Object novoJoin : getJoin()) {
                    String associationPath = (String) ((Object[]) (Object[]) novoJoin)[0];
                    JoinType joinType = (JoinType) ((Object[]) (Object[]) novoJoin)[1];

                    criteria.createCriteria(associationPath, joinType);
                }
                return (T) criteria.uniqueResult();
            }
            T encontrado = (T) sessao.load(type, id);
            Hibernate.initialize(type.cast(encontrado));
            return encontrado;
        } catch (Exception e) {
            String nmField;

            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public T getRegistro(Class type, Integer id, LockMode lockMode) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            T encontrado = (T) sessao.load(type, id, new LockOptions(lockMode));
            Hibernate.initialize(type.cast(encontrado));
            return encontrado;
        } catch (Exception e) {
            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean hasRegistro(Class type, Integer id) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            Object encontrado = sessao.get(type, id);
            return encontrado != null;
        } catch (Exception e) {
            boolean bool;
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public boolean hasRegistro(Class type, String id) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            Object encontrado = sessao.get(type, id);
            return encontrado != null;
        } catch (Exception e) {
            boolean bool;
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return false;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public List<T> getLista(Class type) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        this.currentSession = sessao;
        try {
            HashMap criterias = new HashMap();

            criterias.put("this", sessao.createCriteria(type));
            if (getJoin() != null) {
                for (Object novoJoin : getJoin()) {
                    String associationPath = (String) ((Object[]) (Object[]) novoJoin)[0];

                    Object criteria = criterias.get(associationPath);
                    if (criteria == null) {
                        if ((((Object[]) (Object[]) novoJoin)[1] instanceof JoinType)) {
                            criteria = ((Criteria) criterias.get("this")).createCriteria(associationPath, (JoinType) ((Object[]) (Object[]) novoJoin)[1]);
                        }
                        criterias.put(associationPath, criteria);
                    }
                    if ((((Object[]) (Object[]) novoJoin)[1] instanceof FetchMode)) {
                        ((Criteria) criterias.get("this")).setFetchMode(associationPath, (FetchMode) ((Object[]) (Object[]) novoJoin)[1]);
                    }
                }
            }
            if (getDefaultCriterions() != null) {
                for (Object defaultClause : getDefaultCriterions()) {
                    String associationPath = (String) ((Object[]) (Object[]) defaultClause)[0];
                    Criterion criterion = (Criterion) ((Object[]) (Object[]) defaultClause)[1];

                    Object defaultCriteria = criterias.get(associationPath);
                    if (defaultCriteria == null) {
                        defaultCriteria = ((Criteria) criterias.get("this")).createCriteria(associationPath);
                        criterias.put(associationPath, defaultCriteria);
                    }
                    ((Criteria) defaultCriteria).add(criterion);
                }
            }
            if (getCriterions() != null) {
                for (Object clause : getCriterions()) {
                    String associationPath = (String) ((Object[]) (Object[]) clause)[0];
                    Criterion criterion = (Criterion) ((Object[]) (Object[]) clause)[1];

                    Object criteria = criterias.get(associationPath);
                    if (criteria == null) {
                        criteria = ((Criteria) criterias.get("this")).createCriteria(associationPath);
                        criterias.put(associationPath, criteria);
                    }
                    ((Criteria) criteria).add(criterion);
                }
            }
            if (getProjections() != null) {
                for (Object aggregates : getProjections()) {
                    String associationPath = (String) ((Object[]) (Object[]) aggregates)[0];
                    Projection projection = (Projection) ((Object[]) (Object[]) aggregates)[1];

                    Object criteria = criterias.get(associationPath);
                    if (criteria == null) {
                        criteria = ((Criteria) criterias.get("this")).createCriteria(associationPath);
                        criterias.put(associationPath, criteria);
                    }
                    ((Criteria) criteria).setProjection(projection);
                }
            }
            if (getDefaultOrderBy() != null) {
                for (OrderBy dfltOrder : getDefaultOrderBy()) {
                    Object criteria = criterias.get(dfltOrder.getAssociationPath());
                    if (criteria == null) {
                        criteria = ((Criteria) criterias.get("this")).createCriteria(dfltOrder.getAssociationPath());
                        criterias.put(dfltOrder.getAssociationPath(), criteria);
                    }
                    ((Criteria) criteria).addOrder(dfltOrder.getOrder());
                }
            }
            if (getOrderByClause() != null) {
                for (OrderBy order : getOrderByClause()) {
                    Object criteria = criterias.get(order.getAssociationPath());
                    if (criteria == null) {
                        criteria = ((Criteria) criterias.get("this")).createCriteria(order.getAssociationPath());
                        criterias.put(order.getAssociationPath(), criteria);
                    }
                    ((Criteria) criteria).addOrder(order.getOrder());
                }
            }
            Criteria criteriaThis = (Criteria) criterias.get("this");
            Object localObject2;
            if (getMaxFetchSize() > 0) {
                criteriaThis = criteriaThis.setMaxResults(getMaxFetchSize());
            } else if (getMaxFetchSize() == 0) {
                return new ArrayList();
            }
            return criteriaThis.list();
        } catch (Exception e) {
            Criteria criteriaThis;
            sessao.getTransaction().rollback();
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
                this.currentSession = null;
            }
        }
    }

    public List<T> getLista(Class type, int inicio, int maximo) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {

            return sessao.createCriteria(type).setMaxResults(maximo).setFirstResult(inicio).list();
        } catch (Exception e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public List execQuerySQL(String querySQL, boolean exec) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            Query select = (Query) sessao.createSQLQuery(querySQL);
            if (exec) {
                select.executeUpdate();
                return null;
            }
            return select.list();
        } catch (Exception e) {
            List localList;
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public List execQueryHQL(String queryHQL) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
            sessao.beginTransaction();
        }
        try {
            Query select = sessao.createQuery(queryHQL);
            if (this.maxFetchSize > 0) {
                select.setMaxResults(this.maxFetchSize);
            }
            return select.list();
        } catch (Exception e) {
            List localList;
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "", e);
            return null;
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object[] getCriterions() {
        return this.criterions;
    }

    public void setCriterions(Object[] criterions) {
        this.criterions = criterions;
    }

    public Object[] getProjections() {
        return this.projections;
    }

    public void setProjections(Object[] projections) {
        this.projections = projections;
    }

    public OrderBy[] getOrderByClause() {
        return this.orderByClause;
    }

    public void setOrderByClause(OrderBy[] orderByClause) {
        this.orderByClause = orderByClause;
    }

    public OrderBy[] getDefaultOrderBy() {
        return this.defaultOrderBy;
    }

    public void setDefaultOrderBy(OrderBy[] defaultOrderBy) {
        this.defaultOrderBy = defaultOrderBy;
    }

    public void setMaxFetchSize(int maxFetchSize) {
        this.maxFetchSize = maxFetchSize;
    }

    public int getMaxFetchSize() {
        return this.maxFetchSize;
    }

    public Object[] getJoin() {
        return this.join;
    }

    public void setJoin(Object[] join) {
        this.join = join;
    }

    public Object[] getDefaultCriterions() {
        return this.defaultCriterions;
    }

    public void setDefaultCriterions(Object[] defaultCriterions) {
        this.defaultCriterions = defaultCriterions;
    }

    public void openTransaction(boolean exec) {
        this.execCommit = exec;
        if (this.session == null) {
            this.session = HibernateUtil.getSession();
            this.session.beginTransaction();
        }
    }

    public boolean closeTransaction(boolean exec) {
        if (this.session != null) {
            if (!this.session.getTransaction().isActive()) {
                this.session.beginTransaction();
            }
            if (exec) {
                try {
                    this.session.getTransaction().commit();
                    return true;
                } catch (Exception e) {
                    this.session.getTransaction().rollback();

                    Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
                    return false;
                } finally {
                    this.session.close();
                    this.session = null;
                    this.execCommit = true;
                }
            }
            this.session.getTransaction().rollback();
            this.session.close();
        }
        this.session = null;
        this.execCommit = true;

        return true;
    }

    private boolean isConnected() {
        return (this.session != null) && (this.session.isConnected());
    }

    public Integer getNextValueSequence(String nmSequence) {
        try {
            String obj = HibernateUtil.getProperty("hibernate.dialect");

            Class klass = Class.forName(obj);

            Dialect hsql = (Dialect) klass.newInstance();

            Session sessao = this.session;
            if (!isConnected()) {
                sessao = HibernateUtil.getSession();
                sessao.beginTransaction();
            }
            try {
                BigInteger val = (BigInteger) sessao.createSQLQuery(hsql.getSequenceNextValString(nmSequence)).list().get(0);
                return Integer.valueOf(val.intValue());
            } catch (SQLGrammarException ex) {
                sessao.getTransaction().rollback();

                sessao.createSQLQuery(hsql.getCreateSequenceStrings(nmSequence, 1, 1)[0]).executeUpdate();

                BigInteger val = (BigInteger) sessao.createSQLQuery(hsql.getSequenceNextValString(nmSequence)).list().get(0);
                return Integer.valueOf(val.intValue());
            } catch (Exception e) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "", e);
            } finally {
                if (!isConnected()) {
                    sessao.close();
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void cancelQuery() {
        if (this.currentSession != null) {
            this.currentSession.cancelQuery();
        }
    }

    public void lock(Object obj) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
        }
        try {
            sessao.buildLockRequest(new LockOptions(LockMode.PESSIMISTIC_WRITE)).lock(obj);
            if (!isConnected()) {
                sessao.close();
            }
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }

    public void recarregar(Object obj) {
        Session sessao = this.session;
        if (!isConnected()) {
            sessao = HibernateUtil.getSession();
        }
        try {
            sessao.refresh(obj);
        } catch (Exception e) {
            this.mensagem = "Não foi possível recarregar objeto.";
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, this.mensagem, e);
        } finally {
            if (!isConnected()) {
                sessao.close();
            }
        }
    }
}
