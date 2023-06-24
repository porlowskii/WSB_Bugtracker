CREATE TABLE public.authority (
                                  id int8 NOT NULL,
                                  authority varchar(255) NOT NULL,
                                  CONSTRAINT authority_pkey PRIMARY KEY (id),
                                  CONSTRAINT uk_nrgoi6sdvipfsloa7ykxwlslf UNIQUE (authority)
);

INSERT INTO authority (id,authority) VALUES
                                                (1,'ROLE_MANAGE_PROJECT'),
                                                (2,'ROLE_USER_TAB'),
                                                (3,'ROLE_MANAGE_USERS');
