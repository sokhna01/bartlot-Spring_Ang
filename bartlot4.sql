--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

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
-- Name: access_rules; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.access_rules (
    id integer NOT NULL,
    id_user integer,
    copy character varying(100),
    created_date timestamp without time zone DEFAULT now(),
    download character varying(100),
    id_directory integer,
    message character varying(100),
    read character varying(100),
    upload character varying(100),
    write character varying(100),
    write_content character varying(100),
    iduser integer,
    iddirectory integer,
    writecontent character varying(100)
);


ALTER TABLE public.access_rules OWNER TO postgres;

--
-- Name: access_rules_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.access_rules_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.access_rules_id_seq OWNER TO postgres;

--
-- Name: access_rules_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.access_rules_id_seq OWNED BY public.access_rules.id;


--
-- Name: action; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.action (
    act_code character varying(100) NOT NULL,
    act_description character varying(200) NOT NULL,
    act_name character varying(100) NOT NULL,
    action_label character varying(100) NOT NULL,
    allow boolean NOT NULL,
    category character varying(100) NOT NULL,
    url_code character varying(100) NOT NULL
);


ALTER TABLE public.action OWNER TO postgres;

--
-- Name: admin_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin_users (
    iduser integer NOT NULL,
    active boolean DEFAULT true,
    address character varying(100),
    usercellphone character varying(30),
    city character varying(100),
    country character varying(100),
    date_created date,
    useremail character varying(30),
    userfname character varying(100),
    userlname character varying(30),
    login character varying(30),
    password character varying(50),
    pf_code character varying(32),
    prefered_language character varying(4),
    reset_password timestamp(6) without time zone,
    state character varying(100),
    userworkphone character varying(30),
    zipcode character varying(100),
    idcompany integer
);


ALTER TABLE public.admin_users OWNER TO postgres;

--
-- Name: admin_users_iduser_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admin_users_iduser_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_users_iduser_seq OWNER TO postgres;

--
-- Name: admin_users_iduser_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admin_users_iduser_seq OWNED BY public.admin_users.iduser;


--
-- Name: companies_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companies_info (
    idcompany integer NOT NULL,
    active boolean,
    billingaccountnumber character varying(20),
    comment character varying(200),
    companycode character varying(20),
    dispatchtype character varying(15),
    language character varying(5),
    legalname character varying(30),
    maxdeviceiduse integer,
    modifydate timestamp(6) without time zone,
    name character varying(30),
    phoneareacode integer,
    timezone character varying(50),
    type character varying(20),
    useautocompletion boolean
);


ALTER TABLE public.companies_info OWNER TO postgres;

--
-- Name: companiesinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companiesinfo (
    idcompany integer NOT NULL,
    active boolean,
    billingaccountnumber character varying(20),
    comment character varying(200),
    companycode character varying(20),
    dispatchtype character varying(15),
    language character varying(5),
    legalname character varying(30),
    maxdeviceiduse integer,
    modifydate timestamp(6) without time zone,
    name character varying(30),
    phoneareacode integer,
    timezone character varying(50),
    useautocompletion boolean,
    type character varying(20)
);


ALTER TABLE public.companiesinfo OWNER TO postgres;

--
-- Name: companies_info_idcompany_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.companies_info_idcompany_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.companies_info_idcompany_seq OWNER TO postgres;

--
-- Name: companies_info_idcompany_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.companies_info_idcompany_seq OWNED BY public.companiesinfo.idcompany;


--
-- Name: companies_info_idcompany_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.companies_info_idcompany_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.companies_info_idcompany_seq1 OWNER TO postgres;

--
-- Name: companies_info_idcompany_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.companies_info_idcompany_seq1 OWNED BY public.companies_info.idcompany;


--
-- Name: company_adress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_adress (
    idcompanyaddress integer NOT NULL,
    address character varying(100),
    addresstype character varying(20),
    city character varying(30),
    country character varying(30),
    state character varying(30),
    idcompany integer
);


ALTER TABLE public.company_adress OWNER TO postgres;

--
-- Name: companyadress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companyadress (
    idcompanyaddress integer NOT NULL,
    address character varying(100),
    addresstype character varying(20),
    city character varying(30),
    country character varying(30),
    state character varying(30),
    idcompany integer
);


ALTER TABLE public.companyadress OWNER TO postgres;

--
-- Name: company_adress_idcompanyaddress_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_adress_idcompanyaddress_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_adress_idcompanyaddress_seq OWNER TO postgres;

--
-- Name: company_adress_idcompanyaddress_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.company_adress_idcompanyaddress_seq OWNED BY public.companyadress.idcompanyaddress;


--
-- Name: company_adress_idcompanyaddress_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_adress_idcompanyaddress_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_adress_idcompanyaddress_seq1 OWNER TO postgres;

--
-- Name: company_adress_idcompanyaddress_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.company_adress_idcompanyaddress_seq1 OWNED BY public.company_adress.idcompanyaddress;


--
-- Name: company_contact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_contact (
    id integer NOT NULL,
    address_type character varying(50),
    contact_email character varying(100),
    contact_firstname character varying(100) NOT NULL,
    contact_lastname character varying(100) NOT NULL,
    contact_phone character varying(100),
    contact_workphone character varying(100),
    idcompany integer
);


ALTER TABLE public.company_contact OWNER TO postgres;

--
-- Name: companycontact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companycontact (
    id integer NOT NULL,
    address_type character varying(50),
    contact_email character varying(100),
    contact_firstname character varying(100) NOT NULL,
    contact_lastname character varying(100) NOT NULL,
    contact_phone character varying(100),
    contact_workphone character varying(100),
    idcompany integer
);


ALTER TABLE public.companycontact OWNER TO postgres;

--
-- Name: company_contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_contact_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_contact_id_seq OWNER TO postgres;

--
-- Name: company_contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.company_contact_id_seq OWNED BY public.companycontact.id;


--
-- Name: company_contact_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_contact_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_contact_id_seq1 OWNER TO postgres;

--
-- Name: company_contact_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.company_contact_id_seq1 OWNED BY public.company_contact.id;


--
-- Name: company_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_users (
    id integer NOT NULL,
    active boolean,
    address character varying(100),
    city character varying(50),
    country character varying(50),
    created_date date,
    firstname character varying(100),
    homephone character varying(50),
    lastname character varying(100),
    password character varying(100),
    phone character varying(100),
    reset_password date,
    state character varying(50),
    username character varying(100),
    workphone character varying(50),
    idcompany integer
);


ALTER TABLE public.company_users OWNER TO postgres;

--
-- Name: company_users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_users_id_seq OWNER TO postgres;

--
-- Name: company_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.company_users_id_seq OWNED BY public.company_users.id;


--
-- Name: company_users_profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_users_profiles (
    id integer NOT NULL,
    id_company_users integer,
    pf_code character varying(100)
);


ALTER TABLE public.company_users_profiles OWNER TO postgres;

--
-- Name: company_users_profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.company_users_profiles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_users_profiles_id_seq OWNER TO postgres;

--
-- Name: company_users_profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.company_users_profiles_id_seq OWNED BY public.company_users_profiles.id;


--
-- Name: country_companies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country_companies (
    id integer NOT NULL,
    active boolean,
    centerlat double precision,
    centerlong double precision,
    code character varying(5),
    idcompany integer,
    name character varying(50),
    state character varying(50)
);


ALTER TABLE public.country_companies OWNER TO postgres;

--
-- Name: country_companies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_companies_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_companies_id_seq OWNER TO postgres;

--
-- Name: country_companies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_companies_id_seq OWNED BY public.country_companies.id;


--
-- Name: intervention; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.intervention (
    id integer NOT NULL,
    horodotage_fin character varying(255),
    idclient character varying(255),
    idcompteur character varying(255),
    idsite character varying(255),
    horodotage_debut character varying(255)
);


ALTER TABLE public.intervention OWNER TO postgres;

--
-- Name: intervention_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.intervention_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.intervention_id_seq OWNER TO postgres;

--
-- Name: intervention_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.intervention_id_seq OWNED BY public.intervention.id;


--
-- Name: meter_configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meter_configuration (
    id integer NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    idcompteur character varying(255) NOT NULL,
    inverse boolean DEFAULT false NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.meter_configuration OWNER TO postgres;

--
-- Name: meter_configuration_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meter_configuration_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meter_configuration_id_seq OWNER TO postgres;

--
-- Name: meter_configuration_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meter_configuration_id_seq OWNED BY public.meter_configuration.id;


--
-- Name: meter_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meter_data (
    id integer NOT NULL,
    dataamoins real,
    dataaplus real,
    datarmoins real,
    datarplus real,
    horodatage timestamp without time zone,
    idclient character varying(255),
    idcompany integer,
    idcompteur character varying(255),
    idsite character varying(255),
    idpointcomptage character varying(255),
    presence character varying(255),
    puissance_reactive_qualite character varying(2) DEFAULT '5'::character varying,
    qualite character varying(255),
    source character varying(255),
    created_date timestamp without time zone DEFAULT now()
);


ALTER TABLE public.meter_data OWNER TO postgres;

--
-- Name: meter_data_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meter_data_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meter_data_id_seq OWNER TO postgres;

--
-- Name: meter_data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meter_data_id_seq OWNED BY public.meter_data.id;


--
-- Name: meter_data_source_externe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meter_data_source_externe (
    id integer NOT NULL,
    created_date timestamp without time zone DEFAULT now(),
    dataamoins double precision,
    dataaplus double precision,
    datarmoins double precision,
    datarplus double precision,
    horodatage timestamp without time zone,
    idclient character varying(255),
    presence character varying(255),
    qualite character varying(255),
    source character varying(255)
);


ALTER TABLE public.meter_data_source_externe OWNER TO postgres;

--
-- Name: meter_data_source_externe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meter_data_source_externe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meter_data_source_externe_id_seq OWNER TO postgres;

--
-- Name: meter_data_source_externe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meter_data_source_externe_id_seq OWNED BY public.meter_data_source_externe.id;


--
-- Name: profil_action; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profil_action (
    pac_id integer NOT NULL,
    allow boolean,
    act_code character varying(100) NOT NULL,
    pf_code character varying(100) NOT NULL
);


ALTER TABLE public.profil_action OWNER TO postgres;

--
-- Name: profil_action_pac_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profil_action_pac_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profil_action_pac_id_seq OWNER TO postgres;

--
-- Name: profil_action_pac_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profil_action_pac_id_seq OWNED BY public.profil_action.pac_id;


--
-- Name: profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profiles (
    id integer NOT NULL,
    active boolean NOT NULL,
    category character varying(100) NOT NULL,
    pf_code character varying(100) NOT NULL,
    pf_description character varying(100) NOT NULL,
    pf_name character varying(100) NOT NULL
);


ALTER TABLE public.profiles OWNER TO postgres;

--
-- Name: profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profiles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profiles_id_seq OWNER TO postgres;

--
-- Name: profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profiles_id_seq OWNED BY public.profiles.id;


--
-- Name: tokens; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tokens (
    id integer NOT NULL,
    expiration timestamp(6) without time zone,
    token character varying(255),
    user_id integer,
    expiration_date timestamp(6) without time zone
);


ALTER TABLE public.tokens OWNER TO postgres;

--
-- Name: user_profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_profiles (
    upf_id integer NOT NULL,
    iduser integer NOT NULL,
    pf_code character varying(100) NOT NULL
);


ALTER TABLE public.user_profiles OWNER TO postgres;

--
-- Name: user_profiles_upf_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_profiles_upf_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_profiles_upf_id_seq OWNER TO postgres;

--
-- Name: user_profiles_upf_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_profiles_upf_id_seq OWNED BY public.user_profiles.upf_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    active boolean DEFAULT false,
    city character varying(100),
    company_name character varying(100),
    country character varying(100),
    date_created date,
    email character varying(100),
    firstname character varying(100),
    idcompany integer,
    lastname character varying(100),
    password character varying(100),
    address_postal character varying(4),
    prefered_language character varying(10),
    reset_password timestamp without time zone,
    state character varying(100),
    telephone character varying(100),
    code_verif character varying(100)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: users_questions_answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_questions_answers (
    id integer NOT NULL,
    answer character varying(100),
    id_question integer,
    id_user integer
);


ALTER TABLE public.users_questions_answers OWNER TO postgres;

--
-- Name: users_questions_answers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_questions_answers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_questions_answers_id_seq OWNER TO postgres;

--
-- Name: users_questions_answers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_questions_answers_id_seq OWNED BY public.users_questions_answers.id;


--
-- Name: work_table; Type: TABLE; Schema: public; Owner: maoumi
--

CREATE TABLE public.work_table (
    id integer NOT NULL,
    attente_action character varying(255),
    created_date timestamp without time zone DEFAULT now(),
    dataamoins character varying(255),
    dataaplus character varying(255),
    datarmoins character varying(255),
    datarplus character varying(255),
    horodatage timestamp without time zone,
    idclient character varying(255),
    idcompteur character varying(255),
    idpointcomptage character varying(255),
    presence character varying(255),
    qualite character varying(255),
    source character varying(255),
    validation character varying(255),
    commentaires character varying(255)
);


ALTER TABLE public.work_table OWNER TO maoumi;

--
-- Name: access_rules id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.access_rules ALTER COLUMN id SET DEFAULT nextval('public.access_rules_id_seq'::regclass);


--
-- Name: admin_users iduser; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_users ALTER COLUMN iduser SET DEFAULT nextval('public.admin_users_iduser_seq'::regclass);


--
-- Name: companies_info idcompany; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies_info ALTER COLUMN idcompany SET DEFAULT nextval('public.companies_info_idcompany_seq1'::regclass);


--
-- Name: companiesinfo idcompany; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companiesinfo ALTER COLUMN idcompany SET DEFAULT nextval('public.companies_info_idcompany_seq'::regclass);


--
-- Name: company_adress idcompanyaddress; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_adress ALTER COLUMN idcompanyaddress SET DEFAULT nextval('public.company_adress_idcompanyaddress_seq1'::regclass);


--
-- Name: company_contact id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_contact ALTER COLUMN id SET DEFAULT nextval('public.company_contact_id_seq1'::regclass);


--
-- Name: company_users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users ALTER COLUMN id SET DEFAULT nextval('public.company_users_id_seq'::regclass);


--
-- Name: company_users_profiles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users_profiles ALTER COLUMN id SET DEFAULT nextval('public.company_users_profiles_id_seq'::regclass);


--
-- Name: companyadress idcompanyaddress; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companyadress ALTER COLUMN idcompanyaddress SET DEFAULT nextval('public.company_adress_idcompanyaddress_seq'::regclass);


--
-- Name: companycontact id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companycontact ALTER COLUMN id SET DEFAULT nextval('public.company_contact_id_seq'::regclass);


--
-- Name: country_companies id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country_companies ALTER COLUMN id SET DEFAULT nextval('public.country_companies_id_seq'::regclass);


--
-- Name: intervention id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.intervention ALTER COLUMN id SET DEFAULT nextval('public.intervention_id_seq'::regclass);


--
-- Name: meter_configuration id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_configuration ALTER COLUMN id SET DEFAULT nextval('public.meter_configuration_id_seq'::regclass);


--
-- Name: meter_data id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_data ALTER COLUMN id SET DEFAULT nextval('public.meter_data_id_seq'::regclass);


--
-- Name: meter_data_source_externe id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_data_source_externe ALTER COLUMN id SET DEFAULT nextval('public.meter_data_source_externe_id_seq'::regclass);


--
-- Name: profil_action pac_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profil_action ALTER COLUMN pac_id SET DEFAULT nextval('public.profil_action_pac_id_seq'::regclass);


--
-- Name: profiles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles ALTER COLUMN id SET DEFAULT nextval('public.profiles_id_seq'::regclass);


--
-- Name: user_profiles upf_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profiles ALTER COLUMN upf_id SET DEFAULT nextval('public.user_profiles_upf_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: users_questions_answers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_questions_answers ALTER COLUMN id SET DEFAULT nextval('public.users_questions_answers_id_seq'::regclass);


--
-- Data for Name: access_rules; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.access_rules (id, id_user, copy, created_date, download, id_directory, message, read, upload, write, write_content, iduser, iddirectory, writecontent) FROM stdin;
\.


--
-- Data for Name: action; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.action (act_code, act_description, act_name, action_label, allow, category, url_code) FROM stdin;
act_001	add a profile	addProfil	Add_Profile	t	Adding	add_profil
act_105	Modify information profil	ModifyProfilAdmin	Modify_Profile	t	Modifying	modify_profil
act_202	Add action to profil	ManageProfilAction	Manage_Profile	t	Manage	manage_profil_action
act_009	Add a user admin for admin app	addUserAdmin	Add_User	t	Adding	add_user
act_104	Modify user in admin app	ModifyUserAdmin	Modify_User	t	Modifying	modify_user
act_003	Add a company	addCompanyAdmin	Add_Company	t	Adding	add_company
act_103	Modify information company	ModifyCompanyAdmin	Modify_Company	t	Modifying	modify_company
act_006	Add a company admin to manage his own company	addCompanyAdminUser	Add_Company_admin	t	Adding	add_company_admin
act_004	add a driver by the company admin	addDriverAdmin	Add_Driver	t	Adding	add_driver
act_101	Modify a driver in company app	ModifyDriverAdmin	Modify_Driver	t	Modifying	modify_driver
act_002	add new trip	addTrip	Add_Trip	t	Adding	add_trip
act_106	Modify information company by the company admin himself	ModifyCompanyAdmin	Modify_Company_Company	t	Modifying	update_company
act_102	Modify a trip in company app	ModifyTripAdmin	Display_Trip	t	Modifying	modify_trip
act_010	Add a user for company app	AddCompanyUser	Add_User_Company	t	Adding	add_user_company
act_109	Modify user in company app	ModifyUserCompany	Modify_User_Company	t	Modifying	modify_user_company
act_007	Add a trip file for trips care	TripCare	Add_Trip_File	t	Adding	add_trip_file
act_201	View graph of drivers with parameter page	Stats	View_Graph	t	Manage	stats
act_203	Used to view result selection for a date	TripCare	View_Select_Trip_care	t	Manage	view_select_trip_care
act_204	Used to view billing of trips care	TripCare	Billing	t	Manage	billing
act_108	Find a trip to modify trip information	ModifyTripAdmin	Find_Trip	t	Manage	find_trip
act_011	Adding new trip care	TripCare	Add_Trip	t	Adding	add_trip_care
act_110	Monitor trips care in company app	MonitorTripCare	Display_Trip	t	Manage	monitor_trip_care
act_008	 Add a dispatched trip file for trips care	TripCare	Add_Dispatched_Trip_File	t	Adding	add_dispatched_trip_file
act_012	 Add a demo file for demo	Demo	Add_Demo_File	t	Adding	add_demo_file
act_111	View meter data rows	ResultUploadDemo	Result_Upload_Demo	t	Manage	result_upload_file_demo
act_112	View task 3 data rows	ResultUpdateSourceDemo	Result_Update_Source_Demo	t	Manage	result_update_source_demo
act_113	View task 2 data rows	ResultUploadMissedData	Result_Upload_Missed_Data_Demo	t	Manage	result_upload_missed_data_demo
act_114	View task 4 data rows	ResultUpdateQualiteDemo	Result_Update_Qualite_Demo	t	Manage	result_update_qualite_demo
act_116	View Chart	ChartDemoLinePage	Chart_Demo_Line	t	Manage	chart_demo_line
act_115	View Chart	ChartDemoPage	Chart_Demo	t	Manage	chart_demo
\.


--
-- Data for Name: admin_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin_users (iduser, active, address, usercellphone, city, country, date_created, useremail, userfname, userlname, login, password, pf_code, prefered_language, reset_password, state, userworkphone, zipcode, idcompany) FROM stdin;
\.


--
-- Data for Name: companies_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companies_info (idcompany, active, billingaccountnumber, comment, companycode, dispatchtype, language, legalname, maxdeviceiduse, modifydate, name, phoneareacode, timezone, type, useautocompletion) FROM stdin;
\.


--
-- Data for Name: companiesinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companiesinfo (idcompany, active, billingaccountnumber, comment, companycode, dispatchtype, language, legalname, maxdeviceiduse, modifydate, name, phoneareacode, timezone, useautocompletion, type) FROM stdin;
1	t	12345	A company based in California	ABC	type1	en	ABC Inc.	2	2022-01-01 12:00:00	ABC Inc.	408	UTC-8	t	societe
\.


--
-- Data for Name: company_adress; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_adress (idcompanyaddress, address, addresstype, city, country, state, idcompany) FROM stdin;
\.


--
-- Data for Name: company_contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_contact (id, address_type, contact_email, contact_firstname, contact_lastname, contact_phone, contact_workphone, idcompany) FROM stdin;
\.


--
-- Data for Name: company_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_users (id, active, address, city, country, created_date, firstname, homephone, lastname, password, phone, reset_password, state, username, workphone, idcompany) FROM stdin;
11	t	123 Main St	Anytown	USA	2022-01-01	John	555-5678	Doe	password1	555-1234	\N	CA	user1	555-7890	1
\.


--
-- Data for Name: company_users_profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_users_profiles (id, id_company_users, pf_code) FROM stdin;
14	11	pfCode1
\.


--
-- Data for Name: companyadress; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companyadress (idcompanyaddress, address, addresstype, city, country, state, idcompany) FROM stdin;
1	123 Main St	billing	Anytown	USA	CA	1
\.


--
-- Data for Name: companycontact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companycontact (id, address_type, contact_email, contact_firstname, contact_lastname, contact_phone, contact_workphone, idcompany) FROM stdin;
\.


--
-- Data for Name: country_companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country_companies (id, active, centerlat, centerlong, code, idcompany, name, state) FROM stdin;
1	t	\N	\N	US	1	USA	\N
\.


--
-- Data for Name: intervention; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.intervention (id, horodotage_fin, idclient, idcompteur, idsite, horodotage_debut) FROM stdin;
\.


--
-- Data for Name: meter_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meter_configuration (id, created_date, idcompteur, inverse, type) FROM stdin;
11	2022-07-22 13:34:46.425963	CPT-11	f	Re
10	2022-07-22 13:34:46.261997	CPT-10	f	Re
9	2022-07-22 13:34:46.097978	CPT-9	f	PR
8	2022-07-22 13:34:45.930413	CPT-8	f	Re
7	2022-07-22 13:34:45.750381	CPT-7	f	Re
6	2022-07-22 13:34:45.566072	CPT-6	f	PR
5	2022-07-22 13:34:45.386847	CPT-5	f	PR
4	2022-07-22 13:34:45.206238	CPT-4	f	PR
1	2022-07-22 13:34:44.494788	CPT-P	f	Pr
3	2022-07-22 13:34:44.986031	CPT-R	f	PR
2	2022-07-22 13:34:44.79399	CPT-R	f	Re
\.


--
-- Data for Name: meter_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meter_data (id, dataamoins, dataaplus, datarmoins, datarplus, horodatage, idclient, idcompany, idcompteur, idsite, idpointcomptage, presence, puissance_reactive_qualite, qualite, source, created_date) FROM stdin;
87	0	99	0	30	2022-01-02 00:00:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
88	0	101	0	31	2022-01-02 00:00:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
89	0	99	0	30	2022-01-02 00:10:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
90	0	101	0	31	2022-01-02 00:10:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
91	0	99	0	30	2022-01-02 00:20:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
92	0	101	0	31	2022-01-02 00:20:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
93	0	99	0	30	2022-01-02 00:30:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
94	0	101	0	31	2022-01-02 00:30:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
95	0	99	0	30	2022-01-02 00:40:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
96	0	101	0	31	2022-01-02 00:40:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
97	0	99	0	30	2022-01-02 00:50:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
98	0	101	0	31	2022-01-02 00:50:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
99	0	99	0	30	2022-01-02 01:00:00	Client_1	\N	CPT-P	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
100	0	101	0	31	2022-01-02 01:00:00	Client_1	\N	CPT-R	DIASS	Point_1	\N	5	\N	\N	2023-05-12 18:16:05.447093
\.


--
-- Data for Name: meter_data_source_externe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meter_data_source_externe (id, created_date, dataamoins, dataaplus, datarmoins, datarplus, horodatage, idclient, presence, qualite, source) FROM stdin;
\.


--
-- Data for Name: profil_action; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profil_action (pac_id, allow, act_code, pf_code) FROM stdin;
9	t	act_011	pfCode1
10	t	act_116	pfCode3
11	t	act_010	pfCode1
12	t	act_012	pfCode3
13	t	act_111	pfCode3
14	t	act_114	pfCode3
15	t	act_115	pfCode3
16	t	act_112	pfCode3
17	t	act_113	pfCode3
18	t	act_109	pfCode1
19	t	act_108	pfCode1
20	t	act_204	pfCode1
21	t	act_203	pfCode1
22	t	act_008	pfCode1
23	t	act_106	pfCode1
24	t	act_007	pfCode1
25	t	act_101	pfCode1
26	t	act_002	pfCode1
27	t	act_004	pfCode1
28	t	act_102	pfCode1
29	t	act_001	pfCode2
30	t	act_003	pfCode2
31	t	act_104	pfCode2
32	t	act_202	pfCode2
33	t	act_103	pfCode2
34	t	act_105	pfCode2
35	t	act_006	pfCode2
36	t	act_009	pfCode2
37	t	act_116	pfCode1
38	t	act_115	pfCode1
39	t	act_112	pfCode1
40	t	act_111	pfCode1
41	t	act_012	pfCode1
42	t	act_114	pfCode1
43	t	act_113	pfCode1
46	t	act_001	pfCode1
\.


--
-- Data for Name: profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profiles (id, active, category, pf_code, pf_description, pf_name) FROM stdin;
2	t	public	pfCode2	simpleUser	public_user
3	t	intra	pfCode3	simpleUser	intranet_user
1	t	admin	pfCode1	simpleUser	admin
\.


--
-- Data for Name: tokens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tokens (id, expiration, token, user_id, expiration_date) FROM stdin;
1647710239	\N	eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMSIsImV4cCI6MTY4MjM2MTQ5Mn0.VVKDeiZ9j0mNB0eY6FexpV11NFkPkA7wBl3PuBzHp3fb8fd0aa4HqjE6devhBdThCgu4E4szlQx2m5ljcCGs8Q	11	2023-04-24 18:38:12.34994
\.


--
-- Data for Name: user_profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_profiles (upf_id, iduser, pf_code) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, active, city, company_name, country, date_created, email, firstname, idcompany, lastname, password, address_postal, prefered_language, reset_password, state, telephone, code_verif) FROM stdin;
\.


--
-- Data for Name: users_questions_answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_questions_answers (id, answer, id_question, id_user) FROM stdin;
\.


--
-- Data for Name: work_table; Type: TABLE DATA; Schema: public; Owner: maoumi
--

COPY public.work_table (id, attente_action, created_date, dataamoins, dataaplus, datarmoins, datarplus, horodatage, idclient, idcompteur, idpointcomptage, presence, qualite, source, validation, commentaires) FROM stdin;
1	non	2023-04-23 17:44:05	\N	\N	\N	56	2023-04-28 09:10:00	SENELEC	CPT-P	BART	\N	\N	Pr	valid├®e	wcwxc
2	non	2023-04-24 23:05:17	\N	\N	\N	56	2023-04-28 09:10:00	SENELEC	CPT-R	BART	\N	\N	Re	valid├®e	wcwxc
3	non	2023-04-24 23:05:17	\N	\N	\N	32.0	2023-04-28 09:20:00	SENELEC	CPT-P	BART	\N	\N	Pr	valid├®e	wcwxc
4	non	2023-05-08 15:36:50.867501	10	15	15	65	2023-04-26 09:10:00	SENELEC	CPT-P	BART	\N	\N	Pr	\N	\N
\.


--
-- Name: access_rules_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.access_rules_id_seq', 1, false);


--
-- Name: admin_users_iduser_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admin_users_iduser_seq', 1, false);


--
-- Name: companies_info_idcompany_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.companies_info_idcompany_seq', 4, true);


--
-- Name: companies_info_idcompany_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.companies_info_idcompany_seq1', 1, false);


--
-- Name: company_adress_idcompanyaddress_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_adress_idcompanyaddress_seq', 5, true);


--
-- Name: company_adress_idcompanyaddress_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_adress_idcompanyaddress_seq1', 1, false);


--
-- Name: company_contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_contact_id_seq', 1, false);


--
-- Name: company_contact_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_contact_id_seq1', 1, false);


--
-- Name: company_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_users_id_seq', 13, true);


--
-- Name: company_users_profiles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_users_profiles_id_seq', 17, true);


--
-- Name: country_companies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_companies_id_seq', 9, true);


--
-- Name: intervention_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.intervention_id_seq', 1, false);


--
-- Name: meter_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meter_configuration_id_seq', 1, false);


--
-- Name: meter_data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meter_data_id_seq', 220, true);


--
-- Name: meter_data_source_externe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.meter_data_source_externe_id_seq', 1180, true);


--
-- Name: profil_action_pac_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profil_action_pac_id_seq', 46, true);


--
-- Name: profiles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profiles_id_seq', 4, true);


--
-- Name: user_profiles_upf_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_profiles_upf_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- Name: users_questions_answers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_questions_answers_id_seq', 1, false);


--
-- Name: access_rules access_rules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.access_rules
    ADD CONSTRAINT access_rules_pkey PRIMARY KEY (id);


--
-- Name: action action_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.action
    ADD CONSTRAINT action_pkey PRIMARY KEY (act_code);


--
-- Name: admin_users admin_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_users
    ADD CONSTRAINT admin_users_pkey PRIMARY KEY (iduser);


--
-- Name: companiesinfo companies_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companiesinfo
    ADD CONSTRAINT companies_info_pkey PRIMARY KEY (idcompany);


--
-- Name: companies_info companies_info_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies_info
    ADD CONSTRAINT companies_info_pkey1 PRIMARY KEY (idcompany);


--
-- Name: companyadress company_adress_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companyadress
    ADD CONSTRAINT company_adress_pkey PRIMARY KEY (idcompanyaddress);


--
-- Name: company_adress company_adress_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_adress
    ADD CONSTRAINT company_adress_pkey1 PRIMARY KEY (idcompanyaddress);


--
-- Name: companycontact company_contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companycontact
    ADD CONSTRAINT company_contact_pkey PRIMARY KEY (id);


--
-- Name: company_contact company_contact_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_contact
    ADD CONSTRAINT company_contact_pkey1 PRIMARY KEY (id);


--
-- Name: company_users company_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users
    ADD CONSTRAINT company_users_pkey PRIMARY KEY (id);


--
-- Name: company_users_profiles company_users_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users_profiles
    ADD CONSTRAINT company_users_profiles_pkey PRIMARY KEY (id);


--
-- Name: country_companies country_companies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country_companies
    ADD CONSTRAINT country_companies_pkey PRIMARY KEY (id);


--
-- Name: intervention intervention_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.intervention
    ADD CONSTRAINT intervention_pkey PRIMARY KEY (id);


--
-- Name: meter_configuration meter_configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_configuration
    ADD CONSTRAINT meter_configuration_pkey PRIMARY KEY (id);


--
-- Name: meter_data meter_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_data
    ADD CONSTRAINT meter_data_pkey PRIMARY KEY (id);


--
-- Name: meter_data_source_externe meter_data_source_externe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meter_data_source_externe
    ADD CONSTRAINT meter_data_source_externe_pkey PRIMARY KEY (id);


--
-- Name: profil_action profil_action_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profil_action
    ADD CONSTRAINT profil_action_pkey PRIMARY KEY (pac_id);


--
-- Name: profiles profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT profiles_pkey PRIMARY KEY (id);


--
-- Name: tokens tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens
    ADD CONSTRAINT tokens_pkey PRIMARY KEY (id);


--
-- Name: company_users uk_8sfw3ojsjciya4u82q7ttyd0n; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users
    ADD CONSTRAINT uk_8sfw3ojsjciya4u82q7ttyd0n UNIQUE (idcompany);


--
-- Name: profiles uk_d1eewrk0yb1muwj8g2q51thbk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT uk_d1eewrk0yb1muwj8g2q51thbk UNIQUE (pf_name);


--
-- Name: companiesinfo uk_ej8aycg21ldwwit9r0u73whpi; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companiesinfo
    ADD CONSTRAINT uk_ej8aycg21ldwwit9r0u73whpi UNIQUE (companycode);


--
-- Name: profiles uk_hqnlkg35f8b4nvk7usviyhkux; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profiles
    ADD CONSTRAINT uk_hqnlkg35f8b4nvk7usviyhkux UNIQUE (pf_code);


--
-- Name: profil_action ukk8jypayjdn0o6buarfnm0v1i8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profil_action
    ADD CONSTRAINT ukk8jypayjdn0o6buarfnm0v1i8 UNIQUE (pf_code, act_code);


--
-- Name: user_profiles user_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profiles
    ADD CONSTRAINT user_profiles_pkey PRIMARY KEY (upf_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_questions_answers users_questions_answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_questions_answers
    ADD CONSTRAINT users_questions_answers_pkey PRIMARY KEY (id);


--
-- Name: profil_action act_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profil_action
    ADD CONSTRAINT act_code_fkey FOREIGN KEY (act_code) REFERENCES public.action(act_code);


--
-- Name: companycontact companycontact_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companycontact
    ADD CONSTRAINT companycontact_idcompany_fkey FOREIGN KEY (idcompany) REFERENCES public.companiesinfo(idcompany);


--
-- Name: company_contact companycontact_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_contact
    ADD CONSTRAINT companycontact_idcompany_fkey FOREIGN KEY (idcompany) REFERENCES public.companies_info(idcompany);


--
-- Name: admin_users fk_companyaddress_companiesinfo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin_users
    ADD CONSTRAINT fk_companyaddress_companiesinfo FOREIGN KEY (idcompany) REFERENCES public.companiesinfo(idcompany) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: companyadress fk_companyaddress_companiesinfo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companyadress
    ADD CONSTRAINT fk_companyaddress_companiesinfo FOREIGN KEY (idcompany) REFERENCES public.companiesinfo(idcompany) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: company_adress fk_companyaddress_companiesinfo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_adress
    ADD CONSTRAINT fk_companyaddress_companiesinfo FOREIGN KEY (idcompany) REFERENCES public.companies_info(idcompany) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: tokens fkdt6dmkwh4830bkceym9lc4ldp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens
    ADD CONSTRAINT fkdt6dmkwh4830bkceym9lc4ldp FOREIGN KEY (user_id) REFERENCES public.company_users(id);


--
-- Name: company_users_profiles idcompany_users_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users_profiles
    ADD CONSTRAINT idcompany_users_fkey FOREIGN KEY (id_company_users) REFERENCES public.company_users(id);


--
-- Name: company_users idcompanycomusers_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users
    ADD CONSTRAINT idcompanycomusers_fkey FOREIGN KEY (idcompany) REFERENCES public.companiesinfo(idcompany);


--
-- Name: company_users_profiles pf_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_users_profiles
    ADD CONSTRAINT pf_code_fkey FOREIGN KEY (pf_code) REFERENCES public.profiles(pf_code);


--
-- Name: profil_action pf_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profil_action
    ADD CONSTRAINT pf_code_fkey FOREIGN KEY (pf_code) REFERENCES public.profiles(pf_code);


--
-- Name: user_profiles pf_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profiles
    ADD CONSTRAINT pf_code_fkey FOREIGN KEY (pf_code) REFERENCES public.profiles(pf_code);


--
-- PostgreSQL database dump complete
--

