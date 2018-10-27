select c10.id as id10, c9.id as id9, c8.id as id8, c7.id as id7, c6.id as id6, c5.id as id5, c4.id as id4, c3.id as id3, c2.id as id2, c1.id as id1
	from categoria c1 
		left join categoria c2 on c2.id = c1.categoria_id
		left join categoria c3 on c3.id = c2.categoria_id
		left join categoria c4 on c4.id = c3.categoria_id
		left join categoria c5 on c5.id = c4.categoria_id
		left join categoria c6 on c6.id = c5.categoria_id
		left join categoria c7 on c7.id = c6.categoria_id
		left join categoria c8 on c8.id = c7.categoria_id
		left join categoria c9 on c9.id = c8.categoria_id
		left join categoria c10 on c10.id = c9.categoria_id
	where c1.id = ?