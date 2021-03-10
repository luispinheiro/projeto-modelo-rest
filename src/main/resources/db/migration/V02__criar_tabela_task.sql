CREATE TABLE task (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    whentodo DATE NOT NULL,
    done BOOLEAN DEFAULT false,
    app_user_id INT,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id)
) /*ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4*/;