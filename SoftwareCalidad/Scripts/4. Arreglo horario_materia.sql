

delete from horario_materia;
alter table horario_materia  drop constraint uq_id_materia_grupo_dia;
alter table horario_materia add constraint uq_id_materia_grupo unique (id_materia,grupo);