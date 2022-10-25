package resources;

import interfaces.IBasic;
import services.Calc;
import interfaces.IConsumer;

public class CalcDefault extends Consumer implements IConsumer {

   private IBasic calc;

   public CalcDefault(IBasic calc, Calc manager) {
      this.calc = calc;
      super.setManager(manager);
   }

   @Override
   public void organizaOperacoesPorPrecedencia() {
      String[] values = super.getManager().getValues();
      String signal;
      int valuesLength = values.length;

      super.setLen(valuesLength);
      super.setPrioridade(0);

      while (valuesLength > 0) {
         for (int i = 0; i < super.getLen(); i++) {
            if (values[i] != null && super.getManager().verificaSinal(values[i])) {
               int temp = super.getManager().ordemDePrecedencia(values[i]);

               if (temp > super.getPrioridade()) {
                  super.setPrioridade(temp);
                  super.setIndex(i);
               }
            }
         }

         signal = values[super.getIndex()];

         super.setOperandos(values);
         values[super.getIndex()] = efetuaOperacaoMatematica(signal, super.getA(), super.getB());
         super.getManager().setResult(values[super.getIndex()]);

         valuesLength = 0;

         for (String val : values) {
            if (val != null) {
               valuesLength++;
            }
         }

         if (valuesLength == 1) {
            valuesLength = 0;
         }

         if (valuesLength > 0) {
            values = super.getManager().redimensionaArray(values, super.getLen());
         }

         super.setPrioridade(0);
         super.setIndex(0);
      }
   }

   @Override
   public String efetuaOperacaoMatematica(String operation, float a, float b) {
      try {
         switch (operation) {
            case "+":
               return this.calc.somar(a, b);
            case "-":
               return this.calc.subtrair(a, b);
            case "*":
               return this.calc.multiplicar(a, b);
            case "/":
               boolean divForZero = super.getManager().verificaDivisaoPorZero(b);
               if (divForZero) {
                  return super.getManager().ErroDivisaoPorZero();
               }
               return this.calc.dividir(a, b);
            default:
               return "";
         }
      } catch (Exception e) {
         return "Erro";
      }
   }

   @Override
   public void frontEnd() {
      System.out.println();
      System.out.println("-----------------");
      System.out.println("| C | ( | ) | / |");
      System.out.println("-----------------");
      System.out.println("| 7 | 8 | 9 | x |");
      System.out.println("-----------------");
      System.out.println("| 4 | 5 | 6 | - |");
      System.out.println("-----------------");
      System.out.println("| 1 | 2 | 3 | + |");
      System.out.println("-----------------");
      System.out.println("| 0     | , | = |");
      System.out.println("-----------------");
   }

}
