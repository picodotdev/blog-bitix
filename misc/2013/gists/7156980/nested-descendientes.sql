select c.id as id
	from Categoria as c, Categoria as p
	where c.left > p.left and c.right < p.right
		and p.id = ?