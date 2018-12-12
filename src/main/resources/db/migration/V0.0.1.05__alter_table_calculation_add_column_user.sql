ALTER TABLE calculation ADD COLUMN user_id int;
ALTER TABLE calculation ADD FOREIGN KEY (user_id) REFERENCES user(id);