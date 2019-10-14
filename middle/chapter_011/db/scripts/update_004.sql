ALTER TABLE users
  ADD COLUMN role character varying NOT NULL DEFAULT 'everyone';

ALTER TABLE users
  ADD COLUMN password character varying NOT NULL DEFAULT 'password';

ALTER TABLE users
  ALTER COLUMN name SET DEFAULT 'name',
  ALTER COLUMN login SET DEFAULT 'login',
  ALTER COLUMN email SET DEFAULT 'some-mail@email.com',
  ALTER COLUMN createddate SET DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE users
  ALTER COLUMN name SET NOT NULL,
  ALTER COLUMN login SET NOT NULL,
  ALTER COLUMN email SET NOT NULL,
  ALTER COLUMN createddate SET NOT NULL;

ALTER TABLE users
  ADD UNIQUE (login);