...
System.out.println();
System.out.println("# Plural forms");

String enPluralForm = enBundle.getString("elements");
String esPluralForm = esBundle.getString("elements");

ChoiceFormat enChoiceFormat = new ChoiceFormat(enPluralForm);
ChoiceFormat esChoiceFormat = new ChoiceFormat(esPluralForm);

String enPluralForm0 = enChoiceFormat.format(0);
String enPluralForm1 = enChoiceFormat.format(1);
String enPluralForm2 = enChoiceFormat.format(2);
String esPluralForm0 = esChoiceFormat.format(0);
String esPluralForm1 = esChoiceFormat.format(1);
String esPluralForm2 = esChoiceFormat.format(2);

System.out.println("Plural form (en, " + 0 + "): " + MessageFormat.format(enPluralForm0, 0));
System.out.println("Plural form (en, " + 1 + "): " + MessageFormat.format(enPluralForm1, 1));
System.out.println("Plural form (en, " + 2 + "): " + MessageFormat.format(enPluralForm2, 2));
System.out.println("Plural form (es, " + 0 + "): " + MessageFormat.format(esPluralForm0, 0));
System.out.println("Plural form (es, " + 1 + "): " + MessageFormat.format(esPluralForm1, 1));
System.out.println("Plural form (es, " + 2 + "): " + MessageFormat.format(esPluralForm2, 2));
...