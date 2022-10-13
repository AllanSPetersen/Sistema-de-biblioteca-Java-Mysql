-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.9-rc-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para allan
CREATE DATABASE IF NOT EXISTS `allan` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `allan`;

-- Copiando estrutura para tabela allan.administrativo
CREATE TABLE IF NOT EXISTS `administrativo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `genero` enum('m','f') NOT NULL,
  `dnascimento` date NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela allan.administrativo: ~4 rows (aproximadamente)
INSERT INTO `administrativo` (`id`, `nome`, `cpf`, `genero`, `dnascimento`, `endereco`, `telefone`, `username`, `password`) VALUES
	(1, 'root', '0          ', 'f', '2000-01-01', 'withoutaddress', '0', 'root', '7b24afc8bc80e548d66c4e7ff72171c5'),
	(18, 'allan', '11111111111', 'm', '1010-10-00', 'aaaa', '11111111111', 'allan', 'e10adc3949ba59abbe56e057f20f883e'),
	(19, 'aaaaaaa', '11111111111', 'm', '1111-11-11', 'ashsahsahsah', '11111111111', 'sahsahsah', 'e10adc3949ba59abbe56e057f20f883e');

-- Copiando estrutura para tabela allan.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela allan.categoria: ~1 rows (aproximadamente)
INSERT INTO `categoria` (`idCategoria`, `descricao`, `periodo`) VALUES
	(1, 'algo', 10);

-- Copiando estrutura para tabela allan.contaemprestimo
CREATE TABLE IF NOT EXISTS `contaemprestimo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `genero` enum('m','f') NOT NULL,
  `dnascimento` date NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela allan.contaemprestimo: ~3 rows (aproximadamente)
INSERT INTO `contaemprestimo` (`id`, `nome`, `cpf`, `genero`, `dnascimento`, `endereco`, `telefone`, `email`) VALUES
	(12, 'teste', '11111111111', 'm', '1111-11-11', 'sahsah', '11111111111', 'sah'),
	(14, 'testedb', '11111111111', 'm', '1111-11-11', 'hhsahsa', '11111111111', 'sah');

-- Copiando estrutura para tabela allan.emprestimo
CREATE TABLE IF NOT EXISTS `emprestimo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_contaemprestimo` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL,
  `data_emprestimo` date NOT NULL,
  `retorno_data` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela allan.emprestimo: ~1 rows (aproximadamente)
INSERT INTO `emprestimo` (`id`, `id_contaemprestimo`, `id_livro`, `data_emprestimo`, `retorno_data`) VALUES
	(31, 12, 18, '2022-06-13', '2022-06-27');

-- Copiando estrutura para tabela allan.livro
CREATE TABLE IF NOT EXISTS `livro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) NOT NULL,
  `autor` varchar(200) NOT NULL,
  `edicao` varchar(200) NOT NULL,
  `ano` varchar(4) NOT NULL,
  `disponivel` enum('yes','no') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela allan.livro: ~3 rows (aproximadamente)
INSERT INTO `livro` (`id`, `titulo`, `autor`, `edicao`, `ano`, `disponivel`) VALUES
	(18, 'ashsah', 'sahsah', 'ashsa', '2000', 'no'),
	(19, 'sahsah', 'sahsah', 'hsahsa', '2001', 'yes'),
	(20, 'sahash', 'ashash', 'ashsah', '2000', 'yes');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
