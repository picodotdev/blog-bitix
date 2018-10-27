with recursive descendientes as (
	select id as id
	from categoria c
		where id = ?
	union all
	select c.id as id
		from descendientes
		join categoria c on c.parent = descendientes.id)
select id from descencientes