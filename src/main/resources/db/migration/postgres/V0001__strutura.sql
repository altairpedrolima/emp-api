--
-- PostgreSQL database dump
--


SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


SET default_with_oids = false;

--
-- Name: auditoria; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE auditoria (
    hash character varying(255) NOT NULL,
    chave character varying(255),
    data_hora timestamp without time zone,
    entidade_cnpj character varying(255),
    observacoes text,
    operacao character varying(255),
    recurso character varying(255),
    usuario_cpf character varying(255),
    usuario_ip character varying(255),
    usuario_serial character varying(255)
);

--
-- Name: certificado_digital; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE certificado_digital (
    id bigint NOT NULL,
    commonname character varying(255),
    commonnameemissor character varying(255),
    serial numeric(19,2),
    id_cliente_ws bigint
);



--
-- Name: certificado_digital_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE certificado_digital_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    commonname character varying(255),
    commonnameemissor character varying(255),
    serial numeric(19,2)
);


--
-- Name: certificado_digital_entidade_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE certificado_digital_entidade_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: cliente_ws_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE cliente_ws_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: clientews_certificadodigital_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE clientews_certificadodigital_aud (
    rev integer NOT NULL,
    id_cliente_ws bigint NOT NULL,
    id bigint NOT NULL,
    revtype smallint
);

--
-- Name: endereco; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE endereco (
    id bigint NOT NULL,
    bairro character varying(100),
    cep character varying(8),
    complemento character varying(100),
    logradouro character varying(255),
    numero character varying(50),
    id_municipio bigint
);



--
-- Name: endereco_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE endereco_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    bairro character varying(100),
    cep character varying(8),
    complemento character varying(100),
    logradouro character varying(255),
    numero character varying(50),
    id_municipio bigint
);

--
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE endereco_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

    --
    -- Name: itl; Type: TABLE; Schema: siscsv; Owner: -
    --

CREATE TABLE empresa (
    id bigint NOT NULL,
    nome character varying(255),
    cnpj character varying(255),
    data_licenciamento timestamp without time zone,
    email character varying(255),
    fax character varying(255),
    telefone character varying(255),
    validade_acreditacao_inmetro timestamp without time zone,
    validade_alvara timestamp without time zone,
    id_endereco bigint
);


--
-- Name: itl_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE empresa_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    nome character varying(255),
    cnpj character varying(255),
    data_licenciamento timestamp without time zone,
    email character varying(255),
    fax character varying(255),
    telefone character varying(255),
    validade_acreditacao_inmetro timestamp without time zone,
    validade_alvara timestamp without time zone,
    id_endereco bigint
);

--
-- Name: operacao; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE operacao (
    id bigint NOT NULL,
    descricao character varying(100)
);



--
-- Name: operacao_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE operacao_aud
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    descricao character varying(100)
);


--
-- Name: operacao_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE operacao_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: perfil; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE perfil (
    id bigint NOT NULL,
    descricao character varying(100)
);



--
-- Name: perfil_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE perfil_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    descricao character varying(100)
);


--
-- Name: perfil_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE perfil_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: perfil_permissao; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE perfil_permissao (
      id_perfil bigint NOT NULL,
      id_permissao bigint NOT NULL
);


--
-- Name: perfil_permissao_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE perfil_permissao_aud (
      rev integer NOT NULL,
      id_perfil bigint NOT NULL,
      id_permissao bigint NOT NULL,
      revtype smallint
);


--
-- Name: permissao; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE permissao (
    id bigint NOT NULL,
    descricao character varying(100),
    id_operacao bigint,
    id_recurso bigint
);


--
-- Name: permissao_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE permissao_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    descricao character varying(100),
    id_operacao bigint,
    id_recurso bigint
);


--
-- Name: permissao_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE permissao_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: recurso; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE recurso (
    id bigint NOT NULL,
    descricao character varying(255)
);


--
-- Name: recurso_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE recurso_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    descricao character varying(255)
);


--
-- Name: recurso_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE recurso_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: sancao; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE sancao (
    id bigint NOT NULL,
    cpf_cadastrador character varying(255),
    data_inicio_vigencia date,
    motivo integer,
    tipo integer,
    id_itl bigint
);


--
-- Name: sancao_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE sancao_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    cpf_cadastrador character varying(255),
    data_inicio_vigencia date,
    motivo integer,
    tipo integer,
    id_itl bigint
);


--
-- Name: sancao_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE sancao_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
--
-- Name: usuario; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    bloqueado boolean,
    cpf character varying(11),
    data_cadastro date,
    data_nascimento date,
    email character varying(100),
    nome character varying(100),
    telefone character varying(255),
    id_endereco bigint,
    id_orgao_executivo bigint
);



--
-- Name: usuario_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE usuario_aud (
    id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    bloqueado boolean,
    cpf character varying(11),
    data_cadastro date,
    data_nascimento date,
    email character varying(100),
    nome character varying(100),
    telefone character varying(255),
    id_endereco bigint,
    id_orgao_executivo bigint
);

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: siscsv; Owner: -
--

CREATE SEQUENCE usuario_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
--
-- Name: usuario_perfil; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE usuario_perfil (
    id_usuario bigint NOT NULL,
    id_perfil bigint NOT NULL
);

--
-- Name: usuario_perfil_aud; Type: TABLE; Schema: siscsv; Owner: -
--

CREATE TABLE usuario_perfil_aud (
    rev integer NOT NULL,
    id_usuario bigint NOT NULL,
    id_perfil bigint NOT NULL,
    revtype smallint
);

--
-- Name: auditoria_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
--

ALTER TABLE ONLY auditoria
    ADD CONSTRAINT auditoria_pkey PRIMARY KEY (hash);

    --
    -- Name: certificado_digital_aud_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
    --

    ALTER TABLE ONLY certificado_digital_aud
        ADD CONSTRAINT certificado_digital_aud_pkey PRIMARY KEY (id, rev);


    --
    -- Name: certificado_digital_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
    --

    ALTER TABLE ONLY certificado_digital
        ADD CONSTRAINT certificado_digital_pkey PRIMARY KEY (id);


    --
    -- Name: clientews_certificadodigital_aud_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
    --

    ALTER TABLE ONLY clientews_certificadodigital_aud
        ADD CONSTRAINT clientews_certificadodigital_aud_pkey PRIMARY KEY (rev, id_cliente_ws, id);

        ALTER TABLE ONLY endereco_aud
            ADD CONSTRAINT endereco_aud_pkey PRIMARY KEY (id, rev);


        --
        -- Name: endereco_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
        --

        ALTER TABLE ONLY endereco
            ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


            --
            -- Name: itl_aud_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
            --

            ALTER TABLE ONLY empresa_aud
                ADD CONSTRAINT empresa_aud_pkey PRIMARY KEY (id, rev);

--
--
-- Name: itl_pkey; Type: CONSTRAINT; Schema: siscsv; Owner: -
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id);
