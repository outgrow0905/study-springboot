CREATE TABLE IF NOT EXISTS users (
  id number PRIMARY KEY,
  name varchar2,
  visit number,
  "like" number,
  level number
);

CREATE TABLE IF NOT EXISTS departments (
  id number PRIMARY KEY,
  name varchar2
);