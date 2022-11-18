--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `user` (`id`, `name`, `user_name`, `email`, `password`) VALUES
(1, 'Anna Carmona', 'anna.carmona', 'anna@gmail.com', 'anna'),
(2, 'Pablo García', 'pablo.garcia', 'pablo@gmail.com', 'pablo'),
(3, 'Andrés Díaz', 'andres.diaz', 'andres@gmail.com', 'andres'),
(4, 'Marta Reyes', 'marta.reyes', 'marta@gmail.com', 'marta'),
(5, 'Esther Nuñez', 'esther.nuñez', 'esther@gmail.com', 'esther');


--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_AUTHOR');


--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(4, 3),
(5, 2);


--
-- Volcado de datos para la tabla `book`
--

INSERT INTO `book` (`id`, `name`, `author`, `genre`, `saga`, `year`, `pages`) VALUES
(1, 'El Ocho', 'Katherine Neville', 'Policiaca', '', '2008', '632'),
(2, 'El hingenioso Hidalgo Don Quijote de la Mancha', 'Miguel de Cervantes Saavedra', 'Narrativa española', '', '2004', '920'),
(3, 'El Color de la Magia', 'Terry Pratchett', 'Fantástica', 'Mundodisco', '2003', '288'),
(4, 'Brujerías', 'Terry Pratchett', 'Fantástica', 'Mundodisco', '2003', '328');


--
-- Volcado de datos para la tabla `user_book`
--

INSERT INTO `user_book` (`user_id`, `book_id`) VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 1),
(4, 3),
(4, 4),
(5, 2);
