with recursive ascendientes as (
	select id as id
		from Categoria c
		where id = ?
	union all
	select c.id as id
		from ascendientes
		join categoria c on ascendientes.id = c.parent)
select id from ascendientes