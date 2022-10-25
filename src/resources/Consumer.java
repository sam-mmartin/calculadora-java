package resources;

import interfaces.IGenerics;
import services.Calc;

public class Consumer implements IGenerics {

   private Calc manager;
   private float a;
   private float b;
   private int index;
   private int prioridade;
   private int len;

   public Calc getManager() {
      return manager;
   }

   public void setManager(Calc manager) {
      this.manager = manager;
   }

   public float getA() {
      return a;
   }

   public float getB() {
      return b;
   }

   public int getIndex() {
      return index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public int getPrioridade() {
      return prioridade;
   }

   public void setPrioridade(int prioridade) {
      this.prioridade = prioridade;
   }

   public int getLen() {
      return len;
   }

   public void setLen(int len) {
      this.len = len;
   }

   @Override
   public void parseFloatOperandos(String a, String b) {
      this.a = Float.parseFloat(a);
      this.b = Float.parseFloat(b);
   }

   @Override
   public void setOperandos(String[] values) {
      if (values[this.index + 1] == null) {
         parseFloatOperandos(values[this.index - 1], "0");
         values[index - 1] = null;
         this.len--;
      } else if (values[this.index - 1] == null) {
         parseFloatOperandos(values[this.index + 1], "0");
         values[this.index + 1] = null;
         this.len--;
      } else {
         parseFloatOperandos(values[this.index - 1], values[this.index + 1]);
         values[index - 1] = null;
         values[index + 1] = null;
         this.len = this.len - 2;
      }
   }

   public void setResultAndValuesAndLen(String[] values) {
      this.manager.setResult(values[index]);
      values[index - 1] = null;
      len--;
   }

}
