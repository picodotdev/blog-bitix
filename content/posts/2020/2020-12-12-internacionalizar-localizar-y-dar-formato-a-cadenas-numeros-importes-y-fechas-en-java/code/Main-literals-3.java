...
Locale esesLocale = new Locale("es", "ES");
ResourceBundle esesBundle = ResourceBundle.getBundle("resource", esesLocale);
...
System.out.println("Fallback (es-ES): " + esesBundle.getString("fallback"));
...