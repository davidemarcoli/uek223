INSERT INTO public.authority (id, "name")
VALUES ('c880be12-5a57-4469-abf1-2c28bb390ba7', 'CAN_RETRIEVE_ALL_USERS');
INSERT INTO public.authority (id, "name")
VALUES ('c7a3eb9c-6eaf-45a8-a6d0-e3d7b8d4ebc9', 'CAN_RETRIEVE_USER');
INSERT INTO public.authority (id, "name")
VALUES ('b40c1838-81e2-4c26-87f8-1f7430189528', 'CAN_MANAGE_USER');
INSERT INTO public.authority (id, "name")
VALUES ('03b62363-340a-431b-a03b-fbe2618fc084', 'CAN_MANAGE_OWN_USER');
INSERT INTO public.authority (id, "name")
VALUES ('9af4ee77-b1dc-4f55-848f-52e35b1d812c', 'CAN_MANAGE_BLOG');
INSERT INTO public.authority (id, "name")
VALUES ('57c32df8-7093-42e8-bca1-7d5a89f27f2d', 'CAN_MANAGE_OWN_BLOG');
INSERT INTO public.authority (id, "name")
VALUES ('99672754-c82b-47dd-b56c-2c405eaef6dd', 'CAN_CREATE_BLOG');

INSERT INTO public."role" (id, "name")
VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e', 'USER');
INSERT INTO public."role" (id, "name")
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', 'ADMIN');
INSERT INTO public."role" (id, "name")
VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3', 'AUTHOR');

INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e', '99672754-c82b-47dd-b56c-2c405eaef6dd');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e', '03b62363-340a-431b-a03b-fbe2618fc084');

INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', '99672754-c82b-47dd-b56c-2c405eaef6dd');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', '03b62363-340a-431b-a03b-fbe2618fc084');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', '57c32df8-7093-42e8-bca1-7d5a89f27f2d');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', 'c880be12-5a57-4469-abf1-2c28bb390ba7');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', 'c7a3eb9c-6eaf-45a8-a6d0-e3d7b8d4ebc9');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', 'b40c1838-81e2-4c26-87f8-1f7430189528');
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92', '9af4ee77-b1dc-4f55-848f-52e35b1d812c');

INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3', '57c32df8-7093-42e8-bca1-7d5a89f27f2d');

INSERT INTO public.users (id, email, "password", username)
VALUES ('f2e0ea7d-b3ad-4510-a611-983eb81306ca', 'james.bond@mi6.com',
        '$2a$12$sOFFUCf7HL0Jwuv9XQx.W.UGuDkrGCldNsShwRxC6AlrfhH4pKoZq', 'james');
INSERT INTO public.users (id, email, "password", username)
VALUES ('bce51025-c47f-4777-8bbd-3804778e0255', 'andrin.klarer@gmail.com',
        '$2a$12$Snp.XzzzXWHEENckz0CgfOIPoEq3T2uqie4I5Tz4jMoytgfXnfIiO', 'andrin');
INSERT INTO public.users (id, email, "password", username)
VALUES ('3ad10bbb-1ffa-4baa-a10a-95ccde9b4452', 'davide@marcoli.ch',
        '$2a$12$GHgpT/gPBUeR7AAhu8j8lesHx7NyEe4L095jk9GbFA.A184zKxsCS', 'davide');

INSERT INTO public.users_roles (user_id, role_id)
VALUES ('f2e0ea7d-b3ad-4510-a611-983eb81306ca', '5f715e1d-f585-47c5-85db-996deade4f1e');
INSERT INTO public.users_roles (user_id, role_id)
VALUES ('bce51025-c47f-4777-8bbd-3804778e0255', '74f04db8-71a9-4c3b-9fcc-59341c690e92');
INSERT INTO public.users_roles (user_id, role_id)
VALUES ('3ad10bbb-1ffa-4baa-a10a-95ccde9b4452', '0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3');

INSERT INTO public.category (id, name)
VALUES ('b309713c-d688-4877-b393-271a463c5d17', 'environment');
INSERT INTO public.category (id, name)
VALUES ('4bddb2a2-bacd-4baf-9663-36991c0fb437', 'traffic');

INSERT INTO public.blog_post (id, "content", title, created_at, user_id)
VALUES ('b8ad1d61-8d17-4725-9589-fae3c71207e0', 'Climate Change get''s worse, here is what to do:', 'Climate Change',
        localtimestamp, 'bce51025-c47f-4777-8bbd-3804778e0255');
INSERT INTO public.blog_post (id, "content", title, created_at, user_id)
VALUES ('54998d7d-c124-45f8-8345-d3fbc3cfbdbd', 'What are the benefits and the disbenefits of this drastic change?',
        '30km/h in the city', localtimestamp, '3ad10bbb-1ffa-4baa-a10a-95ccde9b4452');
INSERT INTO public.blog_post (id, "content", title, created_at, user_id)
VALUES ('278b22cc-9ecb-481e-941d-456ba33bd5cf', 'What are the consequences if there''s no climate? Is life in danger?',
        'Without climate no life!', localtimestamp, '3ad10bbb-1ffa-4baa-a10a-95ccde9b4452');

INSERT INTO public.blog_post_category (blog_post_id, category_id)
VALUES ('b8ad1d61-8d17-4725-9589-fae3c71207e0', 'b309713c-d688-4877-b393-271a463c5d17');
INSERT INTO public.blog_post_category (blog_post_id, category_id)
VALUES ('54998d7d-c124-45f8-8345-d3fbc3cfbdbd', '4bddb2a2-bacd-4baf-9663-36991c0fb437');
INSERT INTO public.blog_post_category (blog_post_id, category_id)
VALUES ('278b22cc-9ecb-481e-941d-456ba33bd5cf', 'b309713c-d688-4877-b393-271a463c5d17');