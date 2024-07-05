--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2

-- Started on 2024-07-05 09:34:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- TOC entry 216 (class 1259 OID 16386)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    department_id bigint NOT NULL,
    created_date timestamp(6) without time zone NOT NULL,
    description character varying(255),
    last_modified_date timestamp(6) without time zone,
    name character varying(255)
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16385)
-- Name: department_department_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.department_department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.department_department_id_seq OWNER TO postgres;

--
-- TOC entry 3384 (class 0 OID 0)
-- Dependencies: 215
-- Name: department_department_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.department_department_id_seq OWNED BY public.department.department_id;


--
-- TOC entry 218 (class 1259 OID 16395)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    employee_id bigint NOT NULL,
    account_locked boolean NOT NULL,
    birth_date timestamp(6) without time zone,
    created_date timestamp(6) without time zone NOT NULL,
    email character varying(255),
    enabled boolean NOT NULL,
    first_name character varying(255),
    hire_date timestamp(6) without time zone,
    last_modified_date timestamp(6) without time zone,
    last_name character varying(255),
    password_hash character varying(255),
    salary double precision,
    department_id bigint NOT NULL,
    position_id bigint NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16394)
-- Name: employee_employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.employee_employee_id_seq OWNER TO postgres;

--
-- TOC entry 3385 (class 0 OID 0)
-- Dependencies: 217
-- Name: employee_employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_employee_id_seq OWNED BY public.employee.employee_id;


--
-- TOC entry 220 (class 1259 OID 16404)
-- Name: position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."position" (
    position_id bigint NOT NULL,
    created_date timestamp(6) without time zone NOT NULL,
    description character varying(255),
    last_modified_date timestamp(6) without time zone,
    name character varying(255)
);


ALTER TABLE public."position" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16403)
-- Name: position_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.position_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.position_position_id_seq OWNER TO postgres;

--
-- TOC entry 3386 (class 0 OID 0)
-- Dependencies: 219
-- Name: position_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.position_position_id_seq OWNED BY public."position".position_id;


--
-- TOC entry 3213 (class 2604 OID 16389)
-- Name: department department_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department ALTER COLUMN department_id SET DEFAULT nextval('public.department_department_id_seq'::regclass);


--
-- TOC entry 3214 (class 2604 OID 16398)
-- Name: employee employee_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee ALTER COLUMN employee_id SET DEFAULT nextval('public.employee_employee_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 16407)
-- Name: position position_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position" ALTER COLUMN position_id SET DEFAULT nextval('public.position_position_id_seq'::regclass);


--
-- TOC entry 3374 (class 0 OID 16386)
-- Dependencies: 216
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (department_id, created_date, description, last_modified_date, name) FROM stdin;
3	2024-06-12 10:01:00	Відділ фінансів відповідає за фінансове планування, управління та звітність компанії. Вони відповідають за складання бюджету, фінансовий прогнозування та управління інвестиціями для забезпечення фінансової стабільності організації.	2024-06-12 10:01:00	Фінанси
4	2024-06-12 10:02:00	Маркетинговий відділ відповідає за просування продуктів і послуг компанії. Вони розробляють маркетингові стратегії, проводять маркетингові дослідження і керують рекламними кампаніями для підвищення видимості бренду та збільшення продажів.	2024-06-12 10:02:00	Маркетинг
5	2024-06-12 10:03:00	Відділ продажів спрямований на продаж продуктів і послуг компанії клієнтам. Вони розробляють стратегії продажів, будують відносини з клієнтами і працюють на досягнення або перевищення продажів для зростання прибутку.	2024-06-12 10:03:00	Продажі
6	2024-06-12 10:04:00	Відділ операцій забезпечує ефективне та ефективне виробництво товарів і послуг. Вони управляють ланцюгом постачання, контролюють виробничі процеси і працюють над покращенням операційної ефективності для досягнення бізнес-цілей.	2024-06-12 10:04:00	Операції
1	2024-05-04 15:03:40	Відділ ІТ відповідає за управління інформаційними технологіями компанії. Вони надають технічну підтримку, управляють мережами, забезпечують безпеку даних і підтримують розробку програмного забезпечення.	2024-06-12 13:16:21.065984	ІТ
\.


--
-- TOC entry 3376 (class 0 OID 16395)
-- Dependencies: 218
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (employee_id, account_locked, birth_date, created_date, email, enabled, first_name, hire_date, last_modified_date, last_name, password_hash, salary, department_id, position_id) FROM stdin;
10	f	1988-09-05 04:00:00	2024-06-12 11:20:00	olha.shevchenko@example.com	t	Ольга	2023-02-20 02:00:00	2024-06-23 15:57:23.705522	Шевченко	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	52000	3	4
8	f	1983-03-20 00:00:00	2024-06-12 11:10:00	oleksandr.petrov@example.com	t	Олександр	2021-07-15 09:30:00	2024-06-12 11:10:00	Петров	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	70000	5	6
9	f	1990-11-11 00:00:00	2024-06-12 11:15:00	maxym.kovalchuk@example.com	t	Максим	2020-11-07 10:15:00	2024-06-12 11:15:00	Ковальчук	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	75000	5	6
11	f	1984-04-22 00:00:00	2024-06-12 11:25:00	andriy.ivanov@example.com	t	Андрій	2022-02-25 11:00:00	2024-06-12 11:25:00	Іванов	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	55000	3	4
12	f	1987-07-19 00:00:00	2024-06-12 11:30:00	yurii.moroz@example.com	t	Юрій	2020-09-10 13:20:00	2024-06-12 11:30:00	Мороз	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	68000	3	10
14	f	1991-06-17 00:00:00	2024-06-12 11:40:00	iryna.kozlova@example.com	t	Ірина	2022-05-30 14:30:00	2024-06-12 11:40:00	Козлова	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	69000	4	5
15	f	1992-10-01 00:00:00	2024-06-12 11:45:00	olena.litvinenko@example.com	t	Олена	2021-12-18 15:45:00	2024-06-12 11:45:00	Литвиненко	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	67000	6	7
7	f	1992-02-25 00:00:00	2024-06-12 11:05:00	pavlo.boiko@example.com	t	Павло	2020-04-01 16:50:00	2024-06-12 11:05:00	Бойко	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	60000	4	5
16	f	1986-03-11 00:00:00	2024-06-12 11:50:00	sofiia.shevchuk@example.com	t	Софія	2023-01-05 17:00:00	2024-06-12 11:50:00	Шевчук	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	64000	6	7
3	f	1990-01-01 03:00:00	2024-05-06 20:52:51.935697	ivan.petrov@example.com	t	Іван	2021-10-10 08:20:00	2024-06-12 12:44:21.732799	Петров	$2a$10$ySHTOHpyKHBCo9X7n88rFeExvVlKh4utHUZcag9zznQkzYJnGeoIi	50000	1	1
17	f	1990-07-03 00:00:00	2024-06-12 11:55:00	viktor.tkachenko@example.com	t	Віктор	2020-09-01 18:20:00	2024-06-12 11:55:00	Ткаченко	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	61000	6	7
18	f	1995-09-09 00:00:00	2024-06-12 12:00:00	maryna.melnyk@example.com	t	Марія	2023-03-12 19:55:00	2024-06-12 12:00:00	Мельник	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	58000	5	6
20	f	1994-08-14 00:00:00	2024-06-12 12:10:00	natalia.koval@example.com	t	Наталя	2022-10-15 23:25:00	2024-06-12 12:10:00	Коваль	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	59000	3	4
22	f	1987-05-19 00:00:00	2024-06-12 12:20:00	mykola.kravchuk@example.com	t	Микола	2021-12-30 10:05:00	2024-06-12 12:20:00	Кравчук	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	67000	6	7
23	f	1988-06-24 00:00:00	2024-06-12 12:25:00	yevhen.dmytriev@example.com	t	Євген	2023-06-25 09:40:00	2024-06-12 12:25:00	Дмитрієв	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	52000	3	10
24	f	1986-01-18 00:00:00	2024-06-12 12:30:00	oleh.kozlov@example.com	t	Олег	2021-04-18 11:15:00	2024-06-12 12:30:00	Козлов	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	73000	1	1
26	f	1982-03-16 00:00:00	2024-06-12 12:40:00	denys.solovyov@example.com	t	Денис	2020-12-28 15:35:00	2024-06-12 12:40:00	Соловйов	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	80000	1	3
6	f	1985-08-15 00:00:00	2024-06-12 11:00:00	iryna.melnyk@example.com	t	Ірина	2021-07-15 14:25:00	2024-06-12 11:00:00	Мельник	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	90000	5	7
5	f	1990-05-20 04:00:00	2024-05-08 14:57:26.682391	bohdan.koval@example.com	t	Богдан	2022-12-05 02:00:00	2024-06-17 13:39:42.702398	Коваль	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	70000	1	3
4	f	1994-05-09 03:00:00	2024-05-06 21:05:01.294256	anna.petrenko@example.com	t	Анна	2023-01-20 02:00:00	2024-06-18 00:31:54.414606	Петренко	$2a$10$u6J5KFR8XBwMt9708zJz6.HPXMjROe6aTN0P4xlvCPkKI4slpmhIC	75000	1	2
21	f	1991-12-07 00:00:00	2024-06-12 12:15:00	yuliia.sidorova@example.com	t	Юлія	2023-08-05 08:30:00	2024-06-12 12:15:00	Сидорова	$2a$10$3foFZj.QT018w3pO5mz9yuw2dfexDARoVC8GutkEdYPvIjtXvl3O.	85000	3	4
19	f	1989-11-23 00:00:00	2024-06-12 12:05:00	tetiana.shevchuk@example.com	t	Тетяна	2020-11-28 22:10:00	2024-06-18 01:47:30.221027	Шевчук	$2a$10$/VnVK..ZXzn1XThVEZp79.zzxig1wxSJK1cXEfBWWLMyzCzkXLsia	63000	4	5
25	f	1984-10-10 00:00:00	2024-06-12 12:35:00	myk.pav@example.com	t	Михайло	2022-11-15 14:00:00	2024-06-23 15:56:45.413646	Павлов	$2a$10$n2X544KyyNLz3qhU/7GQG.u6YsCiGHK94C5KIDjxtaFRoIdakocrO	91000	1	2
28	f	2003-02-27 02:00:00	2024-06-23 16:11:37.026906	example.email@gmail.com	t	Владислав	2024-06-03 03:00:00	\N	Гаркуша	$2a$10$RM.3BhAXpi4ZN.EvWMpH3.b10abAWw7A149vkk9IC6r/syotchDbS	70000	1	10
\.


--
-- TOC entry 3378 (class 0 OID 16404)
-- Dependencies: 220
-- Data for Name: position; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."position" (position_id, created_date, description, last_modified_date, name) FROM stdin;
4	2024-06-12 10:10:00	Менеджер фінансів відповідає за управління фінансовим здоров'ям компанії, контроль за бюджетуванням і фінансове планування.	2024-06-12 10:10:00	Менеджер фінансів
5	2024-06-12 10:11:00	Спеціаліст з маркетингу зосереджується на розробці і впровадженні маркетингових стратегій для просування продуктів компанії.	2024-06-12 10:11:00	Спеціаліст з маркетингу
6	2024-06-12 10:12:00	Представник з продажу взаємодіє з потенційними клієнтами для продажу продуктів і послуг компанії.	2024-06-12 10:12:00	Представник з продажу
3	2024-05-06 21:03:43	Менеджер з людських ресурсів відповідає за нагляд за всіма аспектами практик та процесів у сфері людських ресурсів.	2024-06-10 14:03:10.516971	Менеджер з людських ресурсів
2	2024-05-06 21:03:18	Адміністратор систем відповідає за обслуговування, налаштування та надійну роботу комп'ютерних систем і серверів, забезпечуючи цілісність та ефективність системи.	2024-06-10 14:07:30.433809	Системний адміністратор
1	2024-05-04 15:02:52	Інженер з програмного забезпечення розробляє та підтримує програмні додатки, співпрацює з міжфункціональними командами і забезпечує якість та продуктивність програмного забезпечення.	2024-06-10 14:08:48.277611	Інженер з програмного забезпечення
7	2024-06-12 10:13:00	Менеджер з оперативного управління забезпечує плавний і ефективний роботу компанії.	2024-06-12 10:13:00	Менеджер з операцій
8	2024-06-12 10:14:00	Менеджер проектів відповідає за планування, виконання та завершення проектів, забезпечуючи досягнення їх цілей та дотримання строків.	2024-06-12 10:14:00	Проєктний менеджер
9	2024-06-12 10:15:00	Спеціаліст з підтримки клієнтів забезпечує допомогу та підтримку клієнтам щодо продуктів та послуг компанії.	2024-06-12 10:15:00	Спеціаліст з підтримки клієнтів
10	2024-06-12 10:16:00	Аналітик даних інтерпретує складні дані та перетворює їх на інформацію, яка може пропонувати способи для покращення бізнесу.	2024-06-23 16:12:27.134923	Аналітик даних
\.


--
-- TOC entry 3387 (class 0 OID 0)
-- Dependencies: 215
-- Name: department_department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.department_department_id_seq', 7, true);


--
-- TOC entry 3388 (class 0 OID 0)
-- Dependencies: 217
-- Name: employee_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_employee_id_seq', 28, true);


--
-- TOC entry 3389 (class 0 OID 0)
-- Dependencies: 219
-- Name: position_position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.position_position_id_seq', 5, true);


--
-- TOC entry 3217 (class 2606 OID 16393)
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (department_id);


--
-- TOC entry 3221 (class 2606 OID 16402)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (employee_id);


--
-- TOC entry 3225 (class 2606 OID 16411)
-- Name: position position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (position_id);


--
-- TOC entry 3219 (class 2606 OID 16413)
-- Name: department uk_1t68827l97cwyxo9r1u6t4p7d; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT uk_1t68827l97cwyxo9r1u6t4p7d UNIQUE (name);


--
-- TOC entry 3223 (class 2606 OID 16415)
-- Name: employee uk_fopic1oh5oln2khj8eat6ino0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_fopic1oh5oln2khj8eat6ino0 UNIQUE (email);


--
-- TOC entry 3227 (class 2606 OID 16417)
-- Name: position uk_qe48lxuex3swuovou3giy8qpk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."position"
    ADD CONSTRAINT uk_qe48lxuex3swuovou3giy8qpk UNIQUE (name);


--
-- TOC entry 3228 (class 2606 OID 16423)
-- Name: employee fkbc8rdko9o9n1ri9bpdyxv3x7i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkbc8rdko9o9n1ri9bpdyxv3x7i FOREIGN KEY (position_id) REFERENCES public."position"(position_id);


--
-- TOC entry 3229 (class 2606 OID 16418)
-- Name: employee fkbejtwvg9bxus2mffsm3swj3u9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkbejtwvg9bxus2mffsm3swj3u9 FOREIGN KEY (department_id) REFERENCES public.department(department_id);


-- Completed on 2024-07-05 09:34:14

--
-- PostgreSQL database dump complete
--

