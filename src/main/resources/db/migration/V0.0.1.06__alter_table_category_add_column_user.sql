ALTER TABLE category ADD COLUMN user_id int;
ALTER TABLE category ADD FOREIGN KEY (user_id) REFERENCES user(id);