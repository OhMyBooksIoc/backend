--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `last_name`, `email`, `password`, `id_col`) VALUES
(1, 'Anna', 'Carmona', 'anna@gmail.com', 'anna', NULL),
(2, 'Pablo', 'García', 'pablo@gmail.com', 'pablo', NULL),
(3, 'Andrés', 'Díaz', 'andres@gmail.com', 'andres', NULL),
(4, 'Marta', 'Reyes', 'marta@gmail.com', 'marta', NULL),
(5, 'Esther', 'Nuñez', 'esther@gmail.com', 'esther', NULL);


--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
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

