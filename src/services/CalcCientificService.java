package services;

import interfaces.ICientific;

public class CalcCientificService implements ICientific {

   @Override
   public String quadrado(float base) {
      return String.valueOf(Math.pow(base, 2));
   }

   @Override
   public String cubo(float base) {
      return String.valueOf(Math.pow(base, 3));
   }

   @Override
   public String dezElevadoAX(float expoente) {
      return String.valueOf(Math.pow(10, expoente));
   }

   @Override
   public String exponeciacao(float base, float expoente) {
      return String.valueOf(Math.pow(base, expoente));
   }

   @Override
   public String raizQuadrada(float radicando) {
      return String.valueOf(Math.sqrt(radicando));
   }

   @Override
   public String raizEnesima(float radicando, float indice) {
      return String.valueOf(Math.pow(radicando, 1 / indice));
   }

   @Override
   public String raizCubica(float radicando) {
      return String.valueOf(Math.cbrt(radicando));
   }

   @Override
   public String logaritmo(float logaritmando) {
      return String.valueOf(Math.log10(logaritmando));
   }

   @Override
   public String seno(float valor) {
      return String.valueOf(Math.sin(valor));
   }

   @Override
   public String cosseno(float valor) {
      return String.valueOf(Math.cos(valor));
   }

   @Override
   public String tangente(float valor) {
      return String.valueOf(Math.tan(valor));
   }

   @Override
   public String fatorial(float valor) {
      float res = valor;

      if (valor == 0) {
         return "1";
      } else {
         while (valor > 1) {
            res *= (valor - 1);
            valor--;
         }
      }

      return String.valueOf(res);
   }

   @Override
   public String baseElevadoMenosUm(float valor) {
      return String.valueOf(1 / valor);
   }

}
