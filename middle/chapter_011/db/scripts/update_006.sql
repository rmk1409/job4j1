ALTER TABLE users
  ADD COLUMN country character varying NOT NULL DEFAULT 'Russia',
  ADD COLUMN city character varying NOT NULL DEFAULT 'Saint-Petersburg';