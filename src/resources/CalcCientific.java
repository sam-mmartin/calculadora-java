package resources;

import interfaces.IBasic;
import interfaces.ICientific;
import interfaces.IConsumer;
import services.Calc;

public class CalcCientific extends Consumer implements IConsumer {

   private IBasic calcBasic;
   private ICientific calc;

   public CalcCientific(IBasic calcBasic, ICientific calc, Calc manager) {
      this.calcBasic = calcBasic;
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

         if (super.getManager().verificaOperadores(signal)) {
            super.parseFloatOperandos(values[super.getIndex() - 1], "0");
            values[super.getIndex()] = efetuaOperacaoMatematica(signal, super.getA(), super.getB());
            super.setResultAndValuesAndLen(values);
         } else {
            super.setOperandos(values);
            values[super.getIndex()] = efetuaOperacaoMatematica(signal, super.getA(), super.getB());
            super.getManager().setResult(values[super.getIndex()]);
         }

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
               return this.calcBasic.somar(a, b);
            case "-":
               return this.calcBasic.subtrair(a, b);
            case "*":
               return this.calcBasic.multiplicar(a, b);
            case "/":
               boolean divForZero = super.getManager().verificaDivisaoPorZero(b);
               if (divForZero) {
                  return super.getManager().ErroDivisaoPorZero();
               }
               return this.calcBasic.dividir(a, b);
            case "x2":
               return this.calc.quadrado(a);
            case "x3":
               return this.calc.cubo(a);
            case "exp":
               return this.calc.exponeciacao(a, b);
            case "10eX":
               return this.calc.dezElevadoAX(a);
            case "radic2":
               return this.calc.raizQuadrada(a);
            case "radic3":
               return this.calc.raizCubica(a);
            case "radic":
               return this.calc.raizEnesima(a, b);
            case "!":
               return this.calc.fatorial(a);
            case "log":
               return this.calc.logaritmo(a);
            case "sin":
               return this.calc.seno(a);
            case "cos":
               return this.calc.cosseno(a);
            case "tan":
               return this.calc.tangente(a);
            case "1/x":
               return this.calc.baseElevadoMenosUm(a);
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
      System.out.println("-----------------------------------");
      System.out.println("|     |  x! | +/- | C | ( | ) | / |");
      System.out.println("-----------------------------------");
      System.out.println("| 1/x | log | 10ᵞ | 7 | 8 | 9 | x |");
      System.out.println("-----------------------------------");
      System.out.println("| tan | ᵞ√x | xᵞ  | 4 | 5 | 6 | - |");
      System.out.println("-----------------------------------");
      System.out.println("| cos | ³√x | x³  | 1 | 2 | 3 | + |");
      System.out.println("-----------------------------------");
      System.out.println("| sin | ²√x | x²  | 0     | . | = |");
      System.out.println("-----------------------------------");
   }

}
