--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-03-25 04:13:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 25576)
-- Name: dishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dishes (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    name character varying(255),
    calories integer,
    proteins numeric(10,2),
    fats numeric(10,2),
    carbohydrates numeric(10,2)
);


ALTER TABLE public.dishes OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25646)
-- Name: meal_intake_dishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meal_intake_dishes (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    meal_intake_id uuid NOT NULL,
    dish_id uuid NOT NULL
);


ALTER TABLE public.meal_intake_dishes OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25582)
-- Name: meal_intakes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meal_intakes (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    user_id uuid,
    date date NOT NULL
);


ALTER TABLE public.meal_intakes OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25566)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    name character varying(255),
    email character varying(255),
    age integer,
    weight numeric,
    height numeric,
    goal character varying(255),
    daily_calories integer,
    CONSTRAINT users_daily_calories_check CHECK ((daily_calories > 0))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 4870 (class 0 OID 25576)
-- Dependencies: 218
-- Data for Name: dishes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dishes (id, name, calories, proteins, fats, carbohydrates) FROM stdin;
ac242533-ffaf-437f-8698-aebac3d09f1e	\N	\N	\N	\N	\N
51d39963-516f-4242-982a-b9bcfaf6e93f	Grilled Chicken	250	30.50	5.20	2.80
d64bb23c-24f0-4241-aef5-4e59932f9f0c	Grilled Chicken2	250	30.50	5.20	2.80
5b52d6b8-0051-4b07-98b9-275b1858b3fc	Grilled Chicken3	250	30.50	5.20	2.80
26bb0bff-a757-42ee-8cb9-5b6cac9f8cad	Grilled Chicken4	250	30.50	5.20	2.80
cfa98044-ab84-4e8e-8c1f-fcf6572830af	Grilled Chicken5	250	30.50	5.20	2.80
0b3fb4d2-75bd-4c58-b0bf-a190f1cd556c	Курочка	250	30.50	5.20	2.80
4644fdf9-0786-40d1-9a85-209321879f5c	Курочка	250	30.50	-5.20	2.80
7e0d9416-86d1-4bfb-848a-2580e65912a3	Курочка	250	30.50	\N	2.80
602038e4-83c8-4c6d-853e-ad9537b61d2c	Курочка	250	30.50	8.00	2.80
54c7603d-c0c7-4c17-bea6-f438ab449ff7	Курочка	250	30.50	8.00	2.80
\.


--
-- TOC entry 4872 (class 0 OID 25646)
-- Dependencies: 220
-- Data for Name: meal_intake_dishes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meal_intake_dishes (id, meal_intake_id, dish_id) FROM stdin;
e2cec9b0-a3a9-4587-a741-8b5c480bee6f	378e4da2-bdb6-4293-aa41-7e44f4ef523e	5b52d6b8-0051-4b07-98b9-275b1858b3fc
c939ea64-e52b-40a6-b1f1-c304238bb57b	378e4da2-bdb6-4293-aa41-7e44f4ef523e	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
cff1c916-d3e4-47bb-a0a2-fbf975945b82	b8368ab3-5448-40ab-a6c9-7f8dbc5e9aad	5b52d6b8-0051-4b07-98b9-275b1858b3fc
1f1ab621-ff28-4f44-8663-9df2cb6e6374	b8368ab3-5448-40ab-a6c9-7f8dbc5e9aad	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
e2dcb60e-0b1b-40b1-815c-1873c40235e3	e9a8460b-bffb-4464-ad7c-054a331fe321	5b52d6b8-0051-4b07-98b9-275b1858b3fc
140dbf51-f208-43dd-b6b5-7843fe034054	e9a8460b-bffb-4464-ad7c-054a331fe321	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
3aba8286-8caa-4793-985f-ef9c5d183dce	27b27420-fe35-4a74-82bc-a9faa918b914	5b52d6b8-0051-4b07-98b9-275b1858b3fc
f1d62386-2b40-4395-8f51-312cd665f02d	27b27420-fe35-4a74-82bc-a9faa918b914	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
be8c91b5-502e-4fde-818f-d735f0d163f4	3fdb6117-34d3-414c-aac8-d936e3f1b644	5b52d6b8-0051-4b07-98b9-275b1858b3fc
e6cd5d62-ccca-4614-83c4-97729fdcdac4	3fdb6117-34d3-414c-aac8-d936e3f1b644	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
1d9b644d-0f4e-49be-9726-d5de2ac67013	bbd3a698-1a0c-4500-9d4d-928e16c645e0	5b52d6b8-0051-4b07-98b9-275b1858b3fc
bf7be09b-f3e6-4b7d-a21e-80edf7f4d20e	bbd3a698-1a0c-4500-9d4d-928e16c645e0	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
b9755db9-de2b-4440-9d20-f8fd230373c3	6504578e-45fb-41b4-a259-6d59df84cad4	5b52d6b8-0051-4b07-98b9-275b1858b3fc
7fe9d45d-09d1-469c-b982-9048bb2aee24	6504578e-45fb-41b4-a259-6d59df84cad4	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
8b2c6fb0-3167-4d9a-b164-b0bf9244f5de	94d26779-1c1c-416e-9e19-89c7308fcb47	5b52d6b8-0051-4b07-98b9-275b1858b3fc
79398a47-95cd-4c73-b4a0-d9e8de45b203	94d26779-1c1c-416e-9e19-89c7308fcb47	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
90f450a1-310a-4728-b0c3-bc8c001946ea	1c26a29a-0466-4240-8012-0725fe7fab33	5b52d6b8-0051-4b07-98b9-275b1858b3fc
0a2354f2-c86b-4fa3-bd47-be9a342e2a0a	1c26a29a-0466-4240-8012-0725fe7fab33	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
c299d9f1-c3b4-4c6c-b240-ecd3e542c0fe	fb7b9e11-9bb6-4110-864f-0f641acecc62	5b52d6b8-0051-4b07-98b9-275b1858b3fc
95edcfa2-08d8-4bbe-8075-57d49b387e24	fb7b9e11-9bb6-4110-864f-0f641acecc62	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
7d77bdb3-9cb2-48c7-b13c-4e70ffd293a3	41973ddf-ecc5-4544-b2e2-3c80996d42b4	5b52d6b8-0051-4b07-98b9-275b1858b3fc
c0a55072-41f9-4458-9412-cb26b09c4c8e	41973ddf-ecc5-4544-b2e2-3c80996d42b4	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
aa16e154-fae4-4987-b5fc-a2378ec83c50	9fa7a4db-c3ed-4675-b803-8656f325ca7c	5b52d6b8-0051-4b07-98b9-275b1858b3fc
ba1ebe2c-cfc2-46ad-a493-6380dbc79877	9fa7a4db-c3ed-4675-b803-8656f325ca7c	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
3f657b9d-e3e9-400b-9c57-2a8be3dcd6ba	78164f66-5f2f-4bdd-8068-c1eeef1169dc	5b52d6b8-0051-4b07-98b9-275b1858b3fc
3d7cf39d-3a9c-486b-a654-54423c50551e	78164f66-5f2f-4bdd-8068-c1eeef1169dc	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
6e3a7e8a-7de5-4fd8-9667-3b887a46a07c	37523f2c-4424-47e4-b65f-8de18f186418	5b52d6b8-0051-4b07-98b9-275b1858b3fc
d2192b92-edbc-4ac8-85bd-9e87b36519d1	2d50a606-3a3e-452b-93e8-f7ceb4da8a07	5b52d6b8-0051-4b07-98b9-275b1858b3fc
7a8c877b-fff6-43ce-a50e-62b537063b7e	2d50a606-3a3e-452b-93e8-f7ceb4da8a07	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
3da4ffaa-f771-4de8-bd40-e0b58376f537	7f71ba2f-09e6-48de-8c5b-7fb238a18e11	5b52d6b8-0051-4b07-98b9-275b1858b3fc
001c14bc-4742-4efb-90ec-da63c6410dab	0a8dc002-7b72-4a13-be73-3eebbb0647d2	5b52d6b8-0051-4b07-98b9-275b1858b3fc
ad3c2c8d-9ef6-4912-9ef0-67c9846c0c40	a9a82b4d-ae8d-463a-963d-d424d1223c74	5b52d6b8-0051-4b07-98b9-275b1858b3fc
f15c2bd2-a0d3-43ee-b09e-14f363ba466c	a9a82b4d-ae8d-463a-963d-d424d1223c74	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
fa100d95-0fba-470f-9ee4-4eb5e6a1ba5b	5d312c50-3374-4fe0-8c35-a979c8a0695c	5b52d6b8-0051-4b07-98b9-275b1858b3fc
d7a9d259-2ea7-4e44-8b8c-32216f5c401e	5d312c50-3374-4fe0-8c35-a979c8a0695c	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
abd75ebe-ce44-4252-85fd-195621412619	0ba116b5-0dba-4386-a94c-fea870cebfb5	5b52d6b8-0051-4b07-98b9-275b1858b3fc
40c30419-9622-427b-8c76-1b8b4b5b5183	0ba116b5-0dba-4386-a94c-fea870cebfb5	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
03a5df3c-e9d4-4412-87e2-eb9fea5de79f	fb44f2fc-93d1-4ecd-b5d9-34b37ba6efd0	5b52d6b8-0051-4b07-98b9-275b1858b3fc
79174afd-5717-48c7-9646-6f31e741e16d	fb44f2fc-93d1-4ecd-b5d9-34b37ba6efd0	26bb0bff-a757-42ee-8cb9-5b6cac9f8cad
\.


--
-- TOC entry 4871 (class 0 OID 25582)
-- Dependencies: 219
-- Data for Name: meal_intakes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meal_intakes (id, user_id, date) FROM stdin;
2fb06ace-5667-4240-909c-698a65f41328	\N	2025-03-22
ddac266b-dab4-4bf1-9de5-54a21e688bb6	\N	2025-03-22
d82a4a99-7053-4381-9352-7d8bf979bb34	\N	2025-03-22
9d568190-1675-4f20-a846-342b2ed71b34	6e454acd-df2a-4d42-88a7-55d4ec8a96ad	2025-03-22
cff3e87a-eb0e-46a8-9466-0c5e16db5654	6e454acd-df2a-4d42-88a7-55d4ec8a96ad	2025-03-22
9920bdf7-0970-476d-ba90-435b3e8e9500	6e454acd-df2a-4d42-88a7-55d4ec8a96ad	2025-03-22
4e87a814-9d73-4103-9260-645ed32a004b	6e454acd-df2a-4d42-88a7-55d4ec8a96ad	2025-03-22
378e4da2-bdb6-4293-aa41-7e44f4ef523e	6e454acd-df2a-4d42-88a7-55d4ec8a96ad	2025-03-22
b8368ab3-5448-40ab-a6c9-7f8dbc5e9aad	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
e9a8460b-bffb-4464-ad7c-054a331fe321	20b07d09-6d4b-4a5d-8922-25b2cf3a6e0c	2025-03-23
27b27420-fe35-4a74-82bc-a9faa918b914	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
c071f0d0-f52c-476c-9e96-2fbb4dc4cab6	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
3fdb6117-34d3-414c-aac8-d936e3f1b644	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
bbd3a698-1a0c-4500-9d4d-928e16c645e0	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
6504578e-45fb-41b4-a259-6d59df84cad4	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
94d26779-1c1c-416e-9e19-89c7308fcb47	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
1c26a29a-0466-4240-8012-0725fe7fab33	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
fb7b9e11-9bb6-4110-864f-0f641acecc62	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
71b4e460-3e3b-49c5-92c4-fa77444be9e4	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
6692b4e6-e918-4288-8352-75483d77d83b	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
41973ddf-ecc5-4544-b2e2-3c80996d42b4	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
9fa7a4db-c3ed-4675-b803-8656f325ca7c	a174a85d-cbe6-4e23-8954-4bf4367bf256	2025-03-23
78164f66-5f2f-4bdd-8068-c1eeef1169dc	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-21
37523f2c-4424-47e4-b65f-8de18f186418	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-22
2d50a606-3a3e-452b-93e8-f7ceb4da8a07	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-23
7f71ba2f-09e6-48de-8c5b-7fb238a18e11	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-23
0a8dc002-7b72-4a13-be73-3eebbb0647d2	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-23
a9a82b4d-ae8d-463a-963d-d424d1223c74	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-23
5d312c50-3374-4fe0-8c35-a979c8a0695c	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-24
0ba116b5-0dba-4386-a94c-fea870cebfb5	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-24
fb44f2fc-93d1-4ecd-b5d9-34b37ba6efd0	c3b7e7ad-1838-41b7-ab41-86c319fa6da9	2025-03-24
\.


--
-- TOC entry 4869 (class 0 OID 25566)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, age, weight, height, goal, daily_calories) FROM stdin;
6e454acd-df2a-4d42-88a7-55d4ec8a96ad	Иван Иванов	ivan@example.com	30	75.5	180.0	ПОХУДЕНИЕ	1796
aefe1f98-e271-41e4-9195-d656d578d013	Иван Иванов	ivan1@example.com	30	75.5	180.0	ПОХУДЕНИЕ	1793
ef728d01-ec0d-40d4-9421-6f65679ffda8	Иван Иванов	ivan2@example.com	30	75.5	180.0	ПОХУДЕНИЕ	1793
a02b10f5-c536-489f-b701-a3f3cd8bdbe7	Иван Иванов	ivan3@example.com	30	75.5	180.0	ПОХУДЕНИЕ	1434
7b7d7a4d-48bf-4fda-85f8-828fdf8c6eb0	Иван Иванов	ivan4@example.com	30	75.5	180.0	НАБОР_МАССЫ	1972
21598bfd-6872-467e-9156-fea70019ff9e	Иван Иванов	ivan5@example.com	30	75.5	180.0	НАБОР_МАССЫ	2152
cb9a639c-4c3c-4814-9397-b6bb0360d968	Иван Иванов	ivan6@example.com	30	75.5	180.0	НАБОР_МАССЫ	2152
1710585e-314d-4c57-8bad-750c7af25c1c	Иван Иванов	ivan7@example.com	300	75.5	180.0	НАБОР_МАССЫ	312
a174a85d-cbe6-4e23-8954-4bf4367bf256	Валера	valera@example.com	22	63.3	180.0	НАБОР_МАССЫ	2010
20b07d09-6d4b-4a5d-8922-25b2cf3a6e0c	Валера	valera2@example.com	22	63.3	180.0	ПОДДЕРЖАНИЕ	1675
88b20c91-b84e-4373-a1ba-87f5722fb59f	Валера	valera3@example.com	22	63.3	180.0	НАБОР_МАССЫ	2010
21e4148d-a21d-4d11-9d3b-0bbb2f5f0279	Валера	valera4@example.com	22	63.3	180.0	НАБОР_МАССЫ	2010
33946a41-9307-4b5d-9e00-ecce63cad188	Валера	valera5@example.com	22	63.3	180.0	НАБОР_МАССЫ	2010
aed09555-965e-4627-aace-d3adb4688eca	Валера	valera6@example.com	22	70.0	175.0	ПОДДЕРЖАНИЕ	1741
878a8821-8869-44c8-bb50-4e3e938a2ce5	Валера	valera7@example.com	22	70.0	175.0	НАБОР_МАССЫ	2089
70a1719a-e043-4b26-8751-e54e17ce5d0a	Валера	valera8@example.com	22	70.0	175.0	ПОХУДЕНИЕ	1392
6949ab93-bf8d-41d1-bf8a-c2b2e81216df	Валера	valera9@example.com	22	70.0	175.0	ПОХУДЕНИЕ	1392
450b29d4-e33c-4342-8fe4-020edc18ef98	Валера	valera10@example.com	22	70.0	175.0	ПОХУДЕНИЕ	1392
c3b7e7ad-1838-41b7-ab41-86c319fa6da9	Валера	valera11@example.com	22	70.0	175.0	ПОХУДЕНИЕ	1392
273689ac-4249-48b1-bc63-159b3cb6466d	Валера	valera12@example.com	22	70.0	175.0	ПОХУДЕНИЕ	1392
\.


--
-- TOC entry 4716 (class 2606 OID 25581)
-- Name: dishes dishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dishes
    ADD CONSTRAINT dishes_pkey PRIMARY KEY (id);


--
-- TOC entry 4720 (class 2606 OID 25651)
-- Name: meal_intake_dishes meal_intake_dishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_intake_dishes
    ADD CONSTRAINT meal_intake_dishes_pkey PRIMARY KEY (id);


--
-- TOC entry 4718 (class 2606 OID 25589)
-- Name: meal_intakes meal_intakes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_intakes
    ADD CONSTRAINT meal_intakes_pkey PRIMARY KEY (id);


--
-- TOC entry 4712 (class 2606 OID 25575)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4714 (class 2606 OID 25573)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4722 (class 2606 OID 25657)
-- Name: meal_intake_dishes meal_intake_dishes_dish_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_intake_dishes
    ADD CONSTRAINT meal_intake_dishes_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dishes(id) ON DELETE CASCADE;


--
-- TOC entry 4723 (class 2606 OID 25652)
-- Name: meal_intake_dishes meal_intake_dishes_meal_intake_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_intake_dishes
    ADD CONSTRAINT meal_intake_dishes_meal_intake_id_fkey FOREIGN KEY (meal_intake_id) REFERENCES public.meal_intakes(id) ON DELETE CASCADE;


--
-- TOC entry 4721 (class 2606 OID 25590)
-- Name: meal_intakes meal_intakes_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meal_intakes
    ADD CONSTRAINT meal_intakes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


-- Completed on 2025-03-25 04:13:29

--
-- PostgreSQL database dump complete
--

