INSERT INTO authority (id,name) VALUES(100,'ROLE_DEMO');

INSERT INTO user (id,email,first_name,last_name,name,password,user_name,account_non_expired,account_non_locked,credentials_non_expired,enable)
VALUES (100,'demo@demo.com','Smith','Adam','John','11','sJohn',0,0,0,1);

INSERT INTO user (id,email,first_name,last_name,name,password,user_name,account_non_expired,account_non_locked,credentials_non_expired,enable)
VALUES (101,'demo2@demo.com','Smith2','Adam2','John2','11','sJohn2',0,0,0,1);

INSERT INTO user_authority (user_id,authority_name) VALUES(100,'ROLE_DEMO');
