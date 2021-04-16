Statement statement = connection.createStatement();
statement.execute("CREATE TABLE product(id INT IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(255), price DECIMAL(20, 2))");