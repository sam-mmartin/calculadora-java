package services;

import interfaces.IBasic;

public class CalcDefaultService implements IBasic {

   @Override
   public String somar(float a, float b) {
      return String.valueOf(a + b);
   }

   @Override
   public String subtrair(float a, float b) {
      return String.valueOf(a - b);
   }

   @Override
   public String multiplicar(float a, float b) {
      return String.valueOf(a * b);
   }

   @Override
   public String dividir(float a, float b) {
      return String.valueOf(a / b);
   }

}
