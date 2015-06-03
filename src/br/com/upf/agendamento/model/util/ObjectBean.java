/*  1:   */ package br.com.upf.agendamento.model.util;

import br.com.parcerianet.generic.modelo.util.controls.Bean;

/*  2:   */ 
/*  3:   */ public class ObjectBean
/*  4:   */ {
/*  5:   */   private Bean objeto;
/*  6:   */   private char acao;
/*  7:   */   
/*  8:   */   public ObjectBean(Bean objeto, char acao)
/*  9:   */   {
/* 10:13 */     this.objeto = objeto;
/* 11:14 */     this.acao = acao;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public Bean getObjeto()
/* 15:   */   {
/* 16:21 */     return this.objeto;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setObjeto(Bean objeto)
/* 20:   */   {
/* 21:28 */     this.objeto = objeto;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public char getAcao()
/* 25:   */   {
/* 26:35 */     return this.acao;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setAcao(char acao)
/* 30:   */   {
/* 31:42 */     this.acao = acao;
/* 32:   */   }
/* 33:   */ }



/* Location:           C:\Users\Ademilsom\Documents\NetBeansProjects\ConsultorioOdontologicoAgendamento\libs\ParceriaGeneric-1.6.12.jar

 * Qualified Name:     br.com.parcerianet.generic.modelo.util.controls.ObjectBean

 * JD-Core Version:    0.7.0.1

 */