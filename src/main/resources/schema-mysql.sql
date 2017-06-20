CREATE TABLE IF NOT EXISTS `users`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `registerDate` DATE NOT NULL ,
  `role` ENUM('USER', 'ADMIN'),
  `balance` DECIMAL(10, 2) DEFAULT 0,
  PRIMARY KEY (`id`)
);

CREATE UNIQUE INDEX users_unique_email ON users(email);

CREATE TABLE IF NOT EXISTS `balances`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10, 2) DEFAULT 0,
  `date` DATE DEFAULT NOT NULL,
  `user_email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_email`) REFERENCES users(email) ON DELETE CASCADE
);