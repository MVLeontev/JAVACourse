


DROP TABLE IF EXISTS logins;
DROP TABLE IF EXISTS users;

CREATE TABLE  users (
                                     id serial  primary key ,
                                     username varchar(255) UNIQUE not null ,
                                     fio varchar(255) not null
                                 );
CREATE TABLE  logins (
                                     id serial  primary key ,
                                     access_date timestamp,
                                     user_id bigint references USERS(id),
                                     application varchar(255) not null
                                  );
