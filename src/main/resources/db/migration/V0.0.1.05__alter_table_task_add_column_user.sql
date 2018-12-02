ALTER TABLE task ADD COLUMN user_id int;
ALTER TABLE task ADD FOREIGN KEY (user_id) REFERENCES user(id);