INSERT INTO public.authority (id,"name") VALUES ('c880be12-5a57-4469-abf1-2c28bb390ba7','CAN_RETRIEVE_ALL_USERS');
INSERT INTO public.authority (id,"name") VALUES ('c7a3eb9c-6eaf-45a8-a6d0-e3d7b8d4ebc9','CAN_RETRIEVE_USER');
INSERT INTO public.authority (id,"name") VALUES ('51248747-6412-4ca6-8d68-adbc4b177828','CAN_RETRIEVE_OWN_USER');
INSERT INTO public.authority (id,"name") VALUES ('7d14035a-e0b2-488f-915f-a03a64bfaa1c','CAN_CREATE_USER');
INSERT INTO public.authority (id,"name") VALUES ('b40c1838-81e2-4c26-87f8-1f7430189528','CAN_MANAGE_USER');
INSERT INTO public.authority (id,"name") VALUES ('03b62363-340a-431b-a03b-fbe2618fc084','CAN_MANAGE_OWN_USER');
INSERT INTO public.authority (id,"name") VALUES ('9af4ee77-b1dc-4f55-848f-52e35b1d812c','CAN_MANAGE_BLOG');
INSERT INTO public.authority (id,"name") VALUES ('57c32df8-7093-42e8-bca1-7d5a89f27f2d','CAN_MANAGE_OWN_BLOG');
INSERT INTO public.authority (id,"name") VALUES ('918c4328-185e-4d64-9777-9b8dcef7a5fd','CAN_RETRIEVE_ALL_BLOGS');
INSERT INTO public.authority (id,"name") VALUES ('b62328b6-f511-4eed-89f4-ac7cd6eabbab','CAN_RETRIEVE_BLOG');
INSERT INTO public.authority (id,"name") VALUES ('99672754-c82b-47dd-b56c-2c405eaef6dd','CAN_CREATE_BLOG');

INSERT INTO public."role" (id,"name") VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e','DEFAULT');
INSERT INTO public."role" (id,"name") VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','ADMIN');
INSERT INTO public."role" (id,"name") VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','AUTHOR');

INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e','b62328b6-f511-4eed-89f4-ac7cd6eabbab');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e','918c4328-185e-4d64-9777-9b8dcef7a5fd');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e','99672754-c82b-47dd-b56c-2c405eaef6dd');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('5f715e1d-f585-47c5-85db-996deade4f1e','03b62363-340a-431b-a03b-fbe2618fc084');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','c880be12-5a57-4469-abf1-2c28bb390ba7');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','c7a3eb9c-6eaf-45a8-a6d0-e3d7b8d4ebc9');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','51248747-6412-4ca6-8d68-adbc4b177828');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','7d14035a-e0b2-488f-915f-a03a64bfaa1c');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','b40c1838-81e2-4c26-87f8-1f7430189528');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','03b62363-340a-431b-a03b-fbe2618fc084');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','9af4ee77-b1dc-4f55-848f-52e35b1d812c');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','57c32df8-7093-42e8-bca1-7d5a89f27f2d');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','918c4328-185e-4d64-9777-9b8dcef7a5fd');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','b62328b6-f511-4eed-89f4-ac7cd6eabbab');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('74f04db8-71a9-4c3b-9fcc-59341c690e92','99672754-c82b-47dd-b56c-2c405eaef6dd');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','51248747-6412-4ca6-8d68-adbc4b177828');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','03b62363-340a-431b-a03b-fbe2618fc084');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','57c32df8-7093-42e8-bca1-7d5a89f27f2d');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','51248747-6412-4ca6-8d68-adbc4b177828');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','b62328b6-f511-4eed-89f4-ac7cd6eabbab');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','918c4328-185e-4d64-9777-9b8dcef7a5fd');
INSERT INTO public.role_authorities (role_id,authority_id) VALUES ('0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3','99672754-c82b-47dd-b56c-2c405eaef6dd');

INSERT INTO public.users (id,email,"password",username) VALUES ('f2e0ea7d-b3ad-4510-a611-983eb81306ca','james.bond@mi6.com','bond','james');
INSERT INTO public.users (id,email,"password",username) VALUES ('bce51025-c47f-4777-8bbd-3804778e0255','andrin.klarer@gmail.com','klarer','andrin');
INSERT INTO public.users (id,email,"password",username) VALUES ('3ad10bbb-1ffa-4baa-a10a-95ccde9b4452','davide@marcoli.ch','marcoli','davide');

INSERT INTO public.users_roles (user_id,role_id) VALUES ('f2e0ea7d-b3ad-4510-a611-983eb81306ca','5f715e1d-f585-47c5-85db-996deade4f1e');
INSERT INTO public.users_roles (user_id,role_id) VALUES ('bce51025-c47f-4777-8bbd-3804778e0255','74f04db8-71a9-4c3b-9fcc-59341c690e92');
INSERT INTO public.users_roles (user_id,role_id) VALUES ('3ad10bbb-1ffa-4baa-a10a-95ccde9b4452','0bbda63c-9b0a-4001-b0c6-9f9b2b1ca8a3');

INSERT INTO public.blog_post (id,category,"content",title,user_id) VALUES ('b8ad1d61-8d17-4725-9589-fae3c71207e0','Environment','Climate Change get''s worse, here is what to do:','Climate Change','bce51025-c47f-4777-8bbd-3804778e0255');
INSERT INTO public.blog_post (id,category,"content",title,user_id) VALUES ('54998d7d-c124-45f8-8345-d3fbc3cfbdbd','traffic','What are the benefits and the disbenefits of this drastic change?','30km/h in the city','3ad10bbb-1ffa-4baa-a10a-95ccde9b4452');