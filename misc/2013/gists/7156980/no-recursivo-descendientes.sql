select c1.id as id1, c2.id as id2, c3.id as id3, c4.id as id4, c5.id as id5, c6.id as id6, c7.id as id7, c8.id as id8, c9.id as id9, c10.id as id10
	from categoria c1
		left join categoria c2 on c2.categoria_id = c1.id
		left join categoria c3 on c3.categoria_id = c2.id
		left join categoria c4 on c4.categoria_id = c3.id
		left join categoria c5 on c5.categoria_id = c4.id
		left join categoria c6 on c6.categoria_id = c5.id
		left join categoria c7 on c7.categoria_id = c6.id
		left join categoria c8 on c8.categoria_id = c7.id
		left join categoria c9 on c9.categoria_id = c8.id
		left join categoria c10 on c10.categoria_id = c9.id
	where c1.id = ?