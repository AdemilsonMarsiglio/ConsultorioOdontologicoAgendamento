/*  1:   */ package br.com.upf.agendamento.model.util;
/*  2:   */ 
/*  3:   */ import org.hibernate.criterion.Order;
/*  4:   */ 
/*  5:   */ public class OrderBy
/*  6:   */ {
/*  7:   */   private Order order;
/*  8:   */   private String associationPath;
/*  9:   */   
/* 10:   */   public OrderBy(Order order)
/* 11:   */   {
/* 12:19 */     this(order, "this");
/* 13:   */   }
/* 14:   */   
/* 15:   */   public OrderBy(Order order, String associationPath)
/* 16:   */   {
/* 17:23 */     this.order = order;
/* 18:24 */     this.associationPath = associationPath;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public Order getOrder()
/* 22:   */   {
/* 23:31 */     return this.order;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void setOrder(Order order)
/* 27:   */   {
/* 28:38 */     this.order = order;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public String getAssociationPath()
/* 32:   */   {
/* 33:45 */     return this.associationPath;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setAssociationPath(String associationPath)
/* 37:   */   {
/* 38:52 */     this.associationPath = associationPath;
/* 39:   */   }
/* 40:   */ }
