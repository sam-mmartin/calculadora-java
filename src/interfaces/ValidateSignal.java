package interfaces;

public interface ValidateSignal {

   public boolean verificaSinal(String signal);

   public boolean verificaOperadores(String operator);

   public boolean verificaSinalDuplicado(String value);

   public boolean verificaOperadoresDuplicados(String value);

   public int ordemDePrecedencia(String operation);
}
