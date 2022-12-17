-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2022 a las 02:28:05
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ohmybooks`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book`
--

CREATE TABLE `book` (
  `id` int(10) NOT NULL,
  `author` varchar(155) DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `genre` varchar(155) DEFAULT NULL,
  `name` varchar(155) NOT NULL,
  `pages` varchar(10) DEFAULT NULL,
  `saga` varchar(155) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `book`
--

INSERT INTO `book` (`id`, `author`, `cover`, `created_at`, `genre`, `name`, `pages`, `saga`, `year`) VALUES
(1, 'J.R.R. Tolkien', '', '2022-11-22 01:43:16', 'Narrativa fantástica', 'La Comunidad del Anillo', '423', 'El señor de los anillos', '1954'),
(2, 'Miguel de Cervantes Saavedra', 'https://imagessl8.casadellibro.com/a/l/t5/68/9788412222968.jpg', '2022-11-27 18:15:47', 'Sátira, Parodia, Farsa', 'Don Quijote de la Mancha', '456', '', '1605'),
(3, 'Los Hermanos Grimm', 'https://imagessl6.casadellibro.com/a/l/t5/16/9788418304316.jpg', '2022-11-27 18:20:36', 'Cuento Infantil', 'Caperucita Roja', '12', '', '1812'),
(4, 'Ray Bradbury', 'https://imagessl3.casadellibro.com/a/l/t7/63/9788466358163.jpg', '2022-12-01 20:06:51', 'Ciencia Ficción', 'Fahrenheit 451', '192', '', '1953'),
(5, 'J.R.R. Tolkien', NULL, '2022-12-01 20:13:58', 'Narrativa fantástica', 'Las dos Torres', '352', 'El señor de los anillos', '1954'),
(6, 'J.R.R. Tolkien', NULL, '2022-12-01 20:15:59', 'Narrativa fantástica', 'El retorno del Rey', '416', 'El señor de los anillos', '1955');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id` int(10) NOT NULL,
  `name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(3, 'ROLE_AUTHOR'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `disable_at` datetime DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `user_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `created_at`, `disable_at`, `email`, `name`, `password`, `picture`, `status`, `user_name`) VALUES
(2, '2022-11-27 16:06:43', NULL, 'admin@admin.com', 'admin', '$2a$10$M9gwaCXzmZhZOyPXXONq8uxFKBhqkMVA5XowjQZFhzY1IfxxUfzWO', NULL, 1, 'admin'),
(4, '2022-11-27 16:06:48', NULL, 'author@author.com', 'author', '$2a$10$nVuDKOjZJTNLsEtgDYzIIOOSxM2VUxSCHXgKSXP9sTFeXAeKXVp5y', NULL, 1, 'author'),
(6, '2022-11-27 17:02:54', NULL, 'user@user.com', 'user', '$2a$10$JsFxp4bR/5.Am5zxbh5ISOYRCffLneAxizOBa8n/ONzFB5Ey0eiPq', NULL, 1, 'user');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_book`
--

CREATE TABLE `user_book` (
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `hide` tinyint(1) DEFAULT NULL,
  `readd` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `trade` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_book`
--

INSERT INTO `user_book` (`book_id`, `user_id`, `comment`, `created_at`, `hide`, `readd`, `status`, `trade`) VALUES
(1, 2, NULL, '2022-12-01 21:08:34', 1, 0, 1, 1),
(1, 4, NULL, '2022-11-29 00:24:17', 0, 0, 1, 0),
(1, 6, NULL, '2022-12-03 21:46:19', 0, 0, 1, 0),
(2, 6, NULL, '2022-11-29 00:22:34', 0, 1, 1, 1),
(3, 6, NULL, '2022-11-29 00:23:19', 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(2, 1),
(2, 2),
(2, 3),
(4, 2),
(4, 3),
(6, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`);

--
-- Indices de la tabla `user_book`
--
ALTER TABLE `user_book`
  ADD PRIMARY KEY (`book_id`,`user_id`),
  ADD KEY `FKbc0bwdnndnxhct38sinbem0n0` (`user_id`);

--
-- Indices de la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `book`
--
ALTER TABLE `book`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `user_book`
--
ALTER TABLE `user_book`
  ADD CONSTRAINT `FK85pwltn867pjxog1gk5smtqcw` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKbc0bwdnndnxhct38sinbem0n0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
