CREATE TABLE libro (
  ISBN varchar(11) NOT NULL,
  titulo varchar(255) NOT NULL,
  autor varchar(100) NOT NULL,
  editorial varchar(45) NOT NULL,
  anio int(11) NOT NULL,
  slug varchar(255) NOT NULL,
  id int(11) NOT NULL
);


INSERT INTO libro (ISBN, titulo, autor, editorial, anio, slug, id) VALUES
('978958612', 'Aprender PHP, MySQL y JavaScript', 'Robin Nixon', 'Marcombo', 2019, 'aprender-php-mysql-y-javascript', 1),
('978958682', 'JEE 7 a Fondo', 'Pablo Sznajdleder', 'Alfaomega', 2015, 'jee-7-a-fondo', 2),
('978958778', 'Desarrollo de aplicaciones Web con Jakarta EE', 'Cesar Francisco Castillo', 'Marcombo', 2022, 'desarrollo-de-aplicaciones-web-con-jakarta-ee', 3),
('978958850', 'Creacion de un portal con PHP y MySQL', 'Jacobo Pavón Puertas', 'Alfaomega', 2011, 'creacion-de-un-portal-con-php-y-mysql', 4);
ALTER TABLE libro
  ADD PRIMARY KEY (id),
  ADD KEY id (id);

ALTER TABLE libro
  MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;