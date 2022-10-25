package resources;

import java.util.Scanner;

import interfaces.CalcServiceInjector;
import interfaces.IConsumer;
import interfaces.IGenericCalc;

public class GenericCalc implements IGenericCalc {

   private IConsumer app;
   private CalcServiceInjector injector;
   private Scanner inputApp = new Scanner(System.in);
   private Scanner inputCalc = new Scanner(System.in);

   public GenericCalc(CalcServiceInjector injector) {
      this.injector = injector;
   }

   @Override
   public void runApp() {

      int select = 0;

      do {
         selectCalc();
         select = inputApp.nextInt();

         switch (select) {
            case 1:
               app = injector.getConsumerDefault();
               break;

            case 2:
               app = injector.getConsumerCientific();
               break;
            default:
               break;
         }

         if (select != 0) {
            runCalc();
         }
      } while (select != 0);

      inputCalc.close();
      inputApp.close();
   }

   @Override
   public void runCalc() {
      String option;

      do {
         app.getManager().mostraResultadoNaTela();
         app.frontEnd();

         option = inputCalc.nextLine();

         boolean clear = app.getManager().isClear(option);

         if (clear) {
            app.getManager().setIndex(app.getManager().limpaArrayValues());
         } else if (option.equals("=")) {
            app.organizaOperacoesPorPrecedencia();
            app.getManager().runOperations();
         } else if (app.getManager().verificaValorDigitadoAnteriormente() && !app.getManager().verificaSinal(option)) {
            app.getManager().setIndex(0);
            app.getManager().setResult(option);
            app.getManager().runOperations();
         } else if (app.getManager().verificaSinalDuplicado(option)) {
            continue;
         } else if (app.getManager().verificaOperadoresDuplicados(option)) {
            continue;
         } else {
            app.getManager().aumentaTamanhoArray();
            app.getManager().setValues(option);
         }

      } while (!option.equals("exit"));
   }

   private void selectCalc() {
      System.out.printf("\033c"); // Limpar tela
      System.out.println("1 - Padrão");
      System.out.println("2 - Científica");
      System.out.println("0 - Sair");
   }

}
