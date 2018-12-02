ALTER TABLE task ADD COLUMN category_id int;
ALTER TABLE task ADD FOREIGN KEY (category_id) REFERENCES category(id);