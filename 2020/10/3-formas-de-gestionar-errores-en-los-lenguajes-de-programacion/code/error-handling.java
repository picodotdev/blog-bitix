try {
   InputStream inputStream = new FileInputStream(new File(pathname));
   ...
} catch (IOException e) {
  System.out.println(e.getMessage());
}