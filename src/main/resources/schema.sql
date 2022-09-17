CREATE TABLE users (
  id number PRIMARY KEY,
  name varchar2,
  visit number,
  "like" number,
  level number
);

CREATE TABLE departments (
  id number PRIMARY KEY,
  name varchar2
);