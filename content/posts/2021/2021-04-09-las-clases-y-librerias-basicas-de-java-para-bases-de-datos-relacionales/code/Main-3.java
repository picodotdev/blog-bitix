PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name, price) values (?, ?)", new String[] { "id" });

preparedStatement.setString(1, "PlayStation 5");
preparedStatement.setBigDecimal(2, new BigDecimal("499.99"));
preparedStatement.executeUpdate();

ResultSet resultSet1 = preparedStatement.getGeneratedKeys();
while (resultSet1.next()) {
    System.out.printf("Primary key: %s%n", resultSet1.getLong(1));
}
resultSet1.close();

preparedStatement.setString(1, "Xbox Series X");
preparedStatement.setBigDecimal(2, new BigDecimal("499.99"));
preparedStatement.executeUpdate();

ResultSet resultSet2 = preparedStatement.getGeneratedKeys();
while (resultSet2.next()) {
    System.out.printf("Primary key: %s%n", resultSet2.getLong(1));
}
resultSet2.close();

connection.commit();