package ServiceInjector;

import interfaces.CalcServiceInjector;
import interfaces.IConsumer;
import resources.CalcCientific;
import resources.CalcDefault;
import services.Calc;
import services.CalcCientificService;
import services.CalcDefaultService;

public class CalcDefaultServiceInjector implements CalcServiceInjector {

   @Override
   public IConsumer getConsumerDefault() {
      return new CalcDefault(new CalcDefaultService(), new Calc("", new String[2], 0));
   }

   @Override
   public IConsumer getConsumerCientific() {
      return new CalcCientific(new CalcDefaultService(), new CalcCientificService(), new Calc("", new String[2], 0));
   }

}
