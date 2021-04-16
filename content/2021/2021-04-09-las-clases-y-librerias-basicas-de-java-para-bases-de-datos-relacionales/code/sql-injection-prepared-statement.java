PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?"});
preparedStatement.setInt(1, 1);
preparedStatement.executeUpdate();