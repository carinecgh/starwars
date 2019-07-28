# starwars
Desafio B2W - Star Wars Project

O desenvolvimento foi realizado na IDE NetBeans, com um projeto Mavem, usando a API REST e linguagem Java. O mesmo foi testado no browsers Google Chrome e Microsoft Edge. E todas as funcionalidades solicitadas, inclusive o consumo da API pública do star wars foram implementadas.

De todos os requisitos solicitados apenas o banco de dados usado não seguiu as sugestões passadas, sendo utilizado o postgresql. A seguir o script para criação do mesmo, porém a aplicação já está apontando para um banco criado e ativo que estou disponibilizando para os testes de vocês.

--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: tb_planetas; Type: TABLE; Schema: public; Owner: carinehenriques1; Tablespace: 
--

CREATE TABLE tb_planetas (
    id integer NOT NULL,
    nm_nome character varying(50),
    nm_clima character varying(50),
    nm_terreno character varying(50),
    qt_aparicao bigint DEFAULT 0,
    dt_inclusao date
);


ALTER TABLE public.tb_planetas OWNER TO carinehenriques1;

--
-- Name: tb_planetas_id_seq; Type: SEQUENCE; Schema: public; Owner: carinehenriques1
--

CREATE SEQUENCE tb_planetas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tb_planetas_id_seq OWNER TO carinehenriques1;

--
-- Name: tb_planetas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: carinehenriques1
--

ALTER SEQUENCE tb_planetas_id_seq OWNED BY tb_planetas.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: carinehenriques1
--

ALTER TABLE ONLY tb_planetas ALTER COLUMN id SET DEFAULT nextval('tb_planetas_id_seq'::regclass);


--
-- Name: tb_planetas_pkey; Type: CONSTRAINT; Schema: public; Owner: carinehenriques1; Tablespace: 
--

ALTER TABLE ONLY tb_planetas
    ADD CONSTRAINT tb_planetas_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: carinehenriques1
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM carinehenriques1;
GRANT ALL ON SCHEMA public TO carinehenriques1;


--
-- PostgreSQL database dump complete
--
