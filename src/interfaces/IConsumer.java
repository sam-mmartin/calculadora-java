package interfaces;

import services.Calc;

public interface IConsumer {

   public Calc getManager();

   public void organizaOperacoesPorPrecedencia();

   public String efetuaOperacaoMatematica(String operation, float a, float b);

   public void frontEnd();

}
