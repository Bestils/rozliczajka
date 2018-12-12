ALTER TABLE member ADD COLUMN user_id int;
ALTER TABLE member ADD FOREIGN KEY (user_id) REFERENCES user(id);