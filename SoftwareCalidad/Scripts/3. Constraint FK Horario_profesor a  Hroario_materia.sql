
alter table horario_profesor add column id_horario_materia integer;
alter table horario_profesor add constraint id_horario_materia_profe_fk foreign key (id_horario_materia) references horario_materia (id_horario_materia);