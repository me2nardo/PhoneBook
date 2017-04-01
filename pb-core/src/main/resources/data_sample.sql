INSERT INTO authority (name) VALUES('ROLE_DEMO');
INSERT INTO authority (name) VALUES('ROLE_USER');
INSERT INTO authority (name) VALUES('ROLE_ADMIN');

INSERT INTO user (id,email,first_name,last_name,name,password,user_name,account_non_expired,account_non_locked,credentials_non_expired,enable)
VALUES (100,'demo@demo.com','Smith','Adam','John','$2a$10$m3yuFy6fSGe0JjI.sv0QH.6ayA6gvU2NUwvwmwD4wPj1zpNxPoM1W','sJohn',1,1,1,1);

INSERT INTO user (id,email,first_name,last_name,name,password,user_name,account_non_expired,account_non_locked,credentials_non_expired,enable)
VALUES (101,'demo2@demo.com','Smith2','Adam2','John2','$2y$10$IifBDFNs36FVk6El7/SFdOKR389MNA3rZeyzi2KmNO9fOr9/XXYvS','sJohn2',0,0,0,1);

INSERT INTO user_authority (user_id,authority_name) VALUES(100,'ROLE_DEMO');
INSERT INTO user_authority (user_id,authority_name) VALUES(100,'ROLE_USER');
INSERT INTO user_authority (user_id,authority_name) VALUES(100,'ROLE_ADMIN');