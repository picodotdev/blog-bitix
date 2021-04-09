PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, price FROM product");

ResultSet resultSet = preparedStatement.executeQuery();
while (resultSet.next()) {
    System.out.printf("Product (id: %s, name: %s, price: %s)%n", resultSet.getLong(1), resultSet.getString(2), DecimalFormat.getCurrencyInstance(new Locale("es", "ES")).format(resultSet.getBigDecimal(3)));
}
resultSet.close();
