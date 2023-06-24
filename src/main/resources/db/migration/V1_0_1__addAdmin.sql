CREATE TABLE public.person (
                               id int8 NOT NULL,
                               date_created timestamp(6) NOT NULL,
                               email varchar(255) NOT NULL,
                               enabled bool NULL,
                               full_name varchar(255) NOT NULL,
                               "password" varchar(255) NOT NULL,
                               username varchar(255) NULL,
                               CONSTRAINT person_pkey PRIMARY KEY (id),
                               CONSTRAINT uk_n0i6d7rc1hqkjivk494g8j2qd UNIQUE (username)
);

INSERT INTO public.person(
    id, date_created, email, enabled, full_name, password, username)
VALUES (1, 'now', 'admin@admin.admin', true, 'ADMIN', '$2a$10$sAt4CNOkf3JdjjRt9vEQY.xJ6JMr1hw9yG2TKvGUgsuq6H9JRh6C.', 'admin');


CREATE TABLE public.person_authorities (
                                           person_id int8 NOT NULL,
                                           authority_id int8 NOT NULL,
                                           CONSTRAINT person_authorities_pkey PRIMARY KEY (person_id, authority_id),
                                           CONSTRAINT fk78esvmasulg2gqy3pbkbkxqdq FOREIGN KEY (person_id) REFERENCES public.person(id),
                                           CONSTRAINT fkt6ynxpt1unhq4kx5c25fyfu49 FOREIGN KEY (authority_id) REFERENCES public.authority(id)
);


INSERT INTO public.person_authorities (person_id,authority_id) VALUES
                                                                   (1,1),
                                                                   (1,2),
                                                                   (1,3);
