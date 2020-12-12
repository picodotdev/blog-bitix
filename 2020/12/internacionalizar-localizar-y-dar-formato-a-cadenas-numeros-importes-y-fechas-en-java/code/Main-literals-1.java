...
ResourceBundle enBundle = ResourceBundle.getBundle("resource", enLocale);
ResourceBundle esBundle = ResourceBundle.getBundle("resource", esLocale);

// Literales
System.out.println();
System.out.println("# Literals");
System.out.println("Message (en): " + enBundle.getString("message"));
System.out.println("Message (es): " + esBundle.getString("message"));
...