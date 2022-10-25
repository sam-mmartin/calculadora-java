import ServiceInjector.CalcDefaultServiceInjector;
import ServiceInjector.GenericInjector;
import interfaces.CalcServiceInjector;
import interfaces.IGenericCalc;
import interfaces.IGenericInjector;

public class App {
    public static void main(String[] args) throws Exception {

        IGenericInjector generic = new GenericInjector();
        CalcServiceInjector injector = new CalcDefaultServiceInjector();
        IGenericCalc calc = generic.getGenericCalc(injector);

        calc.runApp();
    }
}
