# -- Add the 'hidden' column to the 'teacher' table
# ALTER TABLE teacher ADD COLUMN hidden BOOLEAN DEFAULT FALSE;
#
# -- Update any existing records where 'hidden' is NULL (if any)
# UPDATE teachers SET hidden = FALSE WHERE hidden IS NULL;
#
# -- Add the 'hidden' column to the 'teacher' table
# ALTER TABLE teachers ADD COLUMN hidden BOOLEAN DEFAULT FALSE;
#
# -- Update any existing records where 'hidden' is NULL (if any)
# UPDATE teachers SET hidden = FALSE WHERE hidden IS NULL;
#
# ALTER TABLE lessons ENGINE=InnoDB;
# ALTER TABLE groups ENGINE=InnoDB;
#
# CREATE TABLE lesson_group (
#                               lesson_id bigint(20) NOT NULL,
#                               group_id bigint(20) NOT NULL,
#                               PRIMARY KEY (lesson_id, group_id),
#                               FOREIGN KEY (lesson_id) REFERENCES lessons(id),
#                               FOREIGN KEY (group_id) REFERENCES groups(id)
# ) ENGINE=InnoDB default charset=utf8;
#
# ALTER TABLE lesson_group ENGINE=InnoDB;
#
# INSERT INTO lesson_group (lesson_id, group_id) SELECT id AS lesson_id, group_id FROM lessons;
#
# ALTER TABLE lessons DROP COLUMN group_id;


# ALTER TABLE lessons ADD COLUMN comment NVARCHAR(255) default NULL;
# INSERT INTO discipline_names(id, name)  VALUES (40, ' ');
# INSERT INTO teachers (id, fio, role_id, hidden) VALUES (14,' ', 2, true);
# INSERT INTO disciplines(id, credits, semester, control_form_id, group_id, education_form_id, discipline_name_id)
# VALUES (1,1,1,1, 30,1,40);

# ALTER TABLE users ADD COLUMN email VARCHAR(255);
# ALTER TABLE users ADD COLUMN password VARCHAR(255);

# CREATE TABLE users_roles (
#                              user_id BIGINT NOT NULL,
#                              role_id BIGINT NOT NULL,
#                              PRIMARY KEY (user_id, role_id),
#                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
#                              FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
# );

# INSERT INTO users (id, first_name, last_name, role_id, email, password)
# VALUES (2, 'Alice', 'Smith', 1, 'alice@gmail.com', '$2a$10$DyQ9rREhIeKyK7q9gpyXb.KG.u0dmdU/b9yQzZd0Rs8JAZ7X.leyG');
