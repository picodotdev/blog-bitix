select c.id as id
	from Categoria as c, Categoria as p 
	where c.left between p.left and p.right
		and c.id = ?