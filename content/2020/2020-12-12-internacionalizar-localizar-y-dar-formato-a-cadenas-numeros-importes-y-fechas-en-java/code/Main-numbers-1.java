...
long millionNumber = 1_000_000;
double decimalNumber = 1000.35d;

System.out.println();
System.out.println("# Numbers");
System.out.println("Number (en): " + NumberFormat.getInstance(enLocale).format(millionNumber));
System.out.println("Decimal number (en): " + NumberFormat.getInstance(enLocale).format(decimalNumber));
System.out.println("Number (es): " + NumberFormat.getInstance(esLocale).format(millionNumber));
System.out.println("Decimal number (es): " + NumberFormat.getInstance(esLocale).format(decimalNumber));
...