package services;

import java.util.Arrays;

import interfaces.ArrayFunctions;
import interfaces.CalcFunctions;
import interfaces.CalcManager;
import interfaces.ValidateSignal;

public class Calc implements CalcManager, ValidateSignal, ArrayFunctions, CalcFunctions {

   private String result;
   private String[] values;
   private int index;

   public Calc(String result, String[] values, int index) {
      this.result = result;
      this.values = values;
      this.index = index;
   }

   public String getResult() {
      return result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String[] getValues() {
      return values;
   }

   public void setValues(String[] values) {
      this.values = values;
   }

   public int getIndex() {
      return index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   @Override
   public void runOperations() {
      index = limpaArrayValues();
      verificaPontoFlutante();
      setValues(result);
   }

   @Override
   public void mostraResultadoNaTela() {
      System.out.printf("\033c"); // Limpar tela

      if (result.isEmpty()) {
         for (String val : values) {
            if (val != null) {
               System.out.printf("%s ", val);
            }
         }
      } else {
         System.out.print(result);
         result = "";
      }
   }

   @Override
   public void setValues(String value) {
      values[index] = value;
      index++;
   }

   @Override
   public boolean isClear(String option) {
      return option.equals("C") || option.equals("c");
   }

   @Override
   public void verificaPontoFlutante() {
      if (result.contains(".")) {
         String[] split = result.split("\\.");
         float zero = Float.parseFloat(split[1]);

         if (zero == 0) {
            result = split[0];
         }
      }
   }

   @Override
   public boolean verificaSinal(String option) {
      switch (option) {
         case "+":
            return true;
         case "-":
            return true;
         case "*":
            return true;
         case "/":
            return true;
         case "x2":
            return true;
         case "x3":
            return true;
         case "exp":
            return true;
         case "10eX":
            return true;
         case "radic2":
            return true;
         case "radic3":
            return true;
         case "radic":
            return true;
         case "!":
            return true;
         case "log":
            return true;
         case "sin":
            return true;
         case "cos":
            return true;
         case "tan":
            return true;
         case "1/x":
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean verificaOperadores(String operator) {
      switch (operator) {
         case "+":
            return false;
         case "-":
            return false;
         case "*":
            return false;
         case "/":
            return false;
         case "x2":
            return true;
         case "x3":
            return true;
         case "exp":
            return false;
         case "10eX":
            return true;
         case "radic2":
            return true;
         case "radic3":
            return true;
         case "radic":
            return false;
         case "!":
            return true;
         case "log":
            return true;
         case "sin":
            return true;
         case "cos":
            return true;
         case "tan":
            return true;
         case "1/x":
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean verificaSinalDuplicado(String value) {

      boolean ehSinal = value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");

      if (index - 1 > 0) {
         String valorAnterior = values[index - 1];
         int sub = valorAnterior.length() - 1;

         boolean sinalAnteriorehOperador = index - 1 > 0 && verificaOperadores(valorAnterior);
         boolean valorAnteriorEhNumero = index - 1 > 0
               && valorAnterior.substring(sub).matches("^[0-9]*$");

         if (ehSinal && !sinalAnteriorehOperador && !valorAnteriorEhNumero) {
            return true;
         }

      }

      return false;
   }

   @Override
   public boolean verificaOperadoresDuplicados(String value) {
      boolean valorAnteriorEhSinal = index - 1 > 0 && verificaSinal(values[index - 1]);
      boolean ehOperador = verificaOperadores(value);

      if (valorAnteriorEhSinal && ehOperador) {
         return true;
      }

      return false;
   }

   @Override
   public boolean verificaValorDigitadoAnteriormente() {
      if (index - 1 < 0 || verificaSinal(values[index - 1])) {
         return false;
      }

      return true;
   }

   @Override
   public int ordemDePrecedencia(String operation) {
      switch (operation) {
         case "+":
            return 1;
         case "-":
            return 2;
         case "*":
            return 3;
         case "/":
            return 4;
         case "x2":
            return 5;
         case "x3":
            return 5;
         case "exp":
            return 5;
         case "10eX":
            return 5;
         case "radic2":
            return 6;
         case "radic3":
            return 6;
         case "radic":
            return 6;
         case "!":
            return 7;
         case "log":
            return 8;
         case "sin":
            return 8;
         case "cos":
            return 8;
         case "tan":
            return 8;
         case "1/x":
            return 4;
         default:
            return 0;
      }
   }

   @Override
   public int limpaArrayValues() {
      Arrays.fill(values, null);
      return 0;
   }

   @Override
   public String[] redimensionaArray(String[] values, int len) {
      String[] newArray = new String[len];
      int j = 0;

      for (String val : values) {
         if (val != null) {
            newArray[j] = val;
            j++;
         }
      }

      return newArray;
   }

   @Override
   public void aumentaTamanhoArray() {
      if (index == values.length) {
         values = Arrays.copyOf(values, (index + 1));
      }
   }

   @Override
   public String ErroDivisaoPorZero() {
      Arrays.fill(this.values, null);
      this.index = 0;
      return "Erro";
   }

   @Override
   public boolean verificaDivisaoPorZero(float value) {
      if (value == 0) {
         return true;
      }

      return false;
   }
}
