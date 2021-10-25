...
Locale enLocale = Locale.ENGLISH;
Locale esLocale = new Locale("es");

String locales = Arrays.stream(Locale.getAvailableLocales()).map(it -> it.toLanguageTag()).sorted().collect(Collectors.joining(", "));
System.out.println(locales);
...

