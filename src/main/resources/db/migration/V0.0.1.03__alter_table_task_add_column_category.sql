ALTER TABLE calculation ADD COLUMN category_id int;
ALTER TABLE calculation ADD FOREIGN KEY (category_id) REFERENCES category(id);