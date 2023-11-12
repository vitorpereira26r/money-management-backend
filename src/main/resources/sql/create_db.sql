
CREATE TABLE public.tb_accounts (
    account_id integer NOT NULL,
    balance double precision,
    name character varying(255),
    user_id integer
);


--
-- Name: tb_accounts_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_accounts_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_categories; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_categories (
    id integer NOT NULL,
    name character varying(255)
);


--
-- Name: tb_categories_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_categories_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_roles (
    role_id integer NOT NULL,
    authority character varying(255)
);


--
-- Name: tb_roles_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_roles_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_transactions; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_transactions (
    id integer NOT NULL,
    amount double precision,
    date timestamp(6) with time zone,
    description character varying(255),
    type integer,
    account_id integer,
    category_id integer,
    user_id integer
);


--
-- Name: tb_transactions_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_transactions_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tb_users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tb_users (
    user_id integer NOT NULL,
    balance double precision,
    password character varying(255),
    username character varying(255)
);


--
-- Name: tb_users_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tb_users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_role_junction; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.user_role_junction (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


--
-- Name: tb_accounts tb_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_accounts
    ADD CONSTRAINT tb_accounts_pkey PRIMARY KEY (account_id);


--
-- Name: tb_categories tb_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_categories
    ADD CONSTRAINT tb_categories_pkey PRIMARY KEY (id);


--
-- Name: tb_roles tb_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_roles
    ADD CONSTRAINT tb_roles_pkey PRIMARY KEY (role_id);


--
-- Name: tb_transactions tb_transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_transactions
    ADD CONSTRAINT tb_transactions_pkey PRIMARY KEY (id);


--
-- Name: tb_users tb_users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_users
    ADD CONSTRAINT tb_users_pkey PRIMARY KEY (user_id);


--
-- Name: user_role_junction user_role_junction_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_role_junction
    ADD CONSTRAINT user_role_junction_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: tb_transactions fk4jn3g5lu0mr1rhsacyt559med; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_transactions
    ADD CONSTRAINT fk4jn3g5lu0mr1rhsacyt559med FOREIGN KEY (user_id) REFERENCES public.tb_users(user_id);


--
-- Name: tb_transactions fk7ojv27vx0u9fvdxx4fjuo5n3c; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_transactions
    ADD CONSTRAINT fk7ojv27vx0u9fvdxx4fjuo5n3c FOREIGN KEY (account_id) REFERENCES public.tb_accounts(account_id);


--
-- Name: user_role_junction fkchdccrfn0tcwjd4bmdvldxtgc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_role_junction
    ADD CONSTRAINT fkchdccrfn0tcwjd4bmdvldxtgc FOREIGN KEY (role_id) REFERENCES public.tb_roles(role_id);


--
-- Name: user_role_junction fkftqgawx29ymi3kd74dxwkukry; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.user_role_junction
    ADD CONSTRAINT fkftqgawx29ymi3kd74dxwkukry FOREIGN KEY (user_id) REFERENCES public.tb_users(user_id);


--
-- Name: tb_transactions fkqvomqo7bcrswlboytrx6rumy0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_transactions
    ADD CONSTRAINT fkqvomqo7bcrswlboytrx6rumy0 FOREIGN KEY (category_id) REFERENCES public.tb_categories(id);


--
-- Name: tb_accounts fks2lyrp2wk8tvsgy5r679fhndn; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tb_accounts
    ADD CONSTRAINT fks2lyrp2wk8tvsgy5r679fhndn FOREIGN KEY (user_id) REFERENCES public.tb_users(user_id);


--
-- PostgreSQL database dump complete
--

