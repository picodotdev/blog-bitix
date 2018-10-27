// Usando HQL
String hql = "from Producto p where p.cantidad < :cantidad";
List productos = session.createQuery(hql).setParameter("cantidad", 10).list();

// Usando JPQL
String jpql = "from Producto p where p.cantidad < :cantidad";
List productos = getEntityManager().createQuery(queryString).setParameter("cantidad", 10).getResultList();

// Usando PreparedStatement
PreparedStatement ps = con.prepareStatement("select * from Producto p where p.cantidad < ?");
ps.setInteger(1, 10);
ResourSet rs = ps.executeQuery();

// Mala práctica
PreparedStatement ps = connection.prepareStatement("select * from Producto p where p.cantidad < " + cantidad);
ResourSet rs = ps.executeQuery();
