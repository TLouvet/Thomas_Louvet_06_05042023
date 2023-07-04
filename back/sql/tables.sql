CREATE TABLE USER (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  email VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE THEME (
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL, 
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE ARTICLE (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  author_id INT NOT NULL,
  theme_id INT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES USER(id),
  FOREIGN KEY (theme_id) REFERENCES THEME(id),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE COMMENT (
  id INT PRIMARY KEY AUTO_INCREMENT,
  content TEXT NOT NULL,
  author_id INT NOT NULL,
  article_id INT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES USER(id),
  FOREIGN KEY (article_id) REFERENCES ARTICLE(id),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_theme_subscription (
  user_id INT NOT NULL,
  theme_id INT NOT NULL
);

INSERT INTO USER (username, email, `password`) VALUES ('admin', 'admin@admin.test', '')
INSERT INTO THEME (`name`, `description`) VALUES 
("Javascript", "JavaScript is a versatile scripting language used to enhance interactivity and functionality on websites."),
("Object Oriented Programming", "Object-Oriented Programming (OOP) is a programming paradigm that organizes code into objects."),
("Web Development", "Web development involves designing, building, and maintaining websites or web applications."),
("Data Structures", "Data structures are fundamental components for organizing and managing data efficiently."),
("Algorithms", "Algorithms are step-by-step instructions used to solve computational problems."),
("Web Architecture", "Web architecture refers to the overall structure and design of web applications."),
("Databases", "Databases store and manage structured data efficiently."),
("C", "C is a powerful and widely used programming language known for its efficiency and low-level control."),
("C++", "C++ is an extension of the C programming language, introducing object-oriented programming and additional features."),
("Computer Engineering", "Computer engineering encompasses the design and development of computer systems and hardware components."),
("WebSockets", "WebSockets is a communication protocol that provides full-duplex, bidirectional communication between a client and a server over a single, long-lived connection."),
("TypeScript", "TypeScript is a typed superset of JavaScript that adds static typing and advanced features to enhance JavaScript development."),
("Python", "Python is a high-level, versatile programming language known for its readability and simplicity.");