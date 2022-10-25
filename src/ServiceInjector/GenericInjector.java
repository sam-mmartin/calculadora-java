package ServiceInjector;

import interfaces.CalcServiceInjector;
import interfaces.IGenericCalc;
import interfaces.IGenericInjector;
import resources.GenericCalc;

public class GenericInjector implements IGenericInjector {

   @Override
   public IGenericCalc getGenericCalc(CalcServiceInjector injector) {
      return new GenericCalc(injector);
   }

}
