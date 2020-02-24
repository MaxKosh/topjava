DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
('2020-02-15 15:15', 'обед', '1000', '100000'),
('2020-02-16 10:10', 'завтрак', '500', '100000'),
('2020-02-16 18:18', 'ужин', '1500', '100000'),
('2020-02-16 08:00', 'завтрак', '100', '100000'),

('2020-01-15 09:15', 'завтрак', '600', '100001'),
('2020-01-15 14:15', 'обед', '800', '100001'),
('2020-01-15 18:20', 'ужин', '600', '100001'),
('2020-02-16 10:15', 'завтрак', '500', '100001');




