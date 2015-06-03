/*  1:   */ package br.com.upf.agendamento.model.util;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import java.io.FileInputStream;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ import java.util.Enumeration;
/*  8:   */ import java.util.Properties;
/*  9:   */ import org.hibernate.HibernateException;
/* 10:   */ import org.hibernate.Session;
/* 11:   */ import org.hibernate.SessionFactory;
/* 12:   */ import org.hibernate.cfg.Configuration;
/* 13:   */ import org.hibernate.service.ServiceRegistry;
/* 14:   */ import org.hibernate.service.ServiceRegistryBuilder;
/* 15:   */ 
/* 16:   */ public class HibernateUtil
/* 17:   */ {
/* 18:   */   private static final SessionFactory sessionFactory;
/* 19:   */   private static final ServiceRegistry serviceRegistry;
/* 20:   */   private static Properties properties;
/* 21:   */   
/* 22:   */   static
/* 23:   */   {
/* 24:   */     try
/* 25:   */     {
/* 26:33 */       Configuration annotationConfiguration = new Configuration().configure();
/* 27:   */       
/* 28:35 */       properties = annotationConfiguration.getProperties();
/* 29:   */       
/* 58:65 */       serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
/* 59:   */       
/* 60:67 */       sessionFactory = annotationConfiguration.buildSessionFactory(serviceRegistry);
/* 61:   */     }
/* 62:   */     catch (HibernateException ex)
/* 63:   */     {
/* 64:70 */       System.err.println("Erro ao criar a SessionFactory." + ex);
/* 65:71 */       throw new ExceptionInInitializerError(ex);
/* 66:   */     }
/* 67:   */   }
/* 68:   */   
/* 69:   */   public static SessionFactory getSessionFactory()
/* 70:   */   {
/* 71:76 */     return sessionFactory;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public static Session getSession()
/* 75:   */   {
/* 76:80 */     return sessionFactory.openSession();
/* 77:   */   }
/* 78:   */   
/* 79:   */   public static String getProperty(String key)
/* 80:   */   {
/* 81:84 */     return properties.getProperty(key);
/* 82:   */   }
/* 83:   */   
/* 84:   */   public static void setProperty(String key, String value)
/* 85:   */   {
/* 86:88 */     properties.setProperty(key, value);
/* 87:   */   }
/* 88:   */   
/* 89:   */   public static void reload() {}
/* 90:   */ }
