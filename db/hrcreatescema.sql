CREATE TYPE gender AS ENUM (
    'f',
    'm'    
);
CREATE TYPE marital AS ENUM (
    'married',
    'single',
    'widow'
);
CREATE TYPE employee_status AS ENUM (
    'active',
    'resigned',
    'retired',
    'terminated',
    'suspended'
 );
CREATE TABLE employee(
	emp_id serial NOT NULL PRIMARY KEY,
	first_name varchar(35) NOT NULL,
	middle_name varchar(35),
	last_name varchar(35),
	father_name varchar(70),
	dob date NOT NULL,
	gender gender NOT NULL,
	marital_status marital,
	uid varchar(15) NOT NULL,
	email varchar(254) NOT NULL,
	phone varchar(15) NOT NULL,
	blood_group varchar(4),
	doj date, 
	address_line1 char(50) NOT NULL,
	address_line2 char(50),
	city varchar(50) NOT NULL,
	state varchar(35) NOT NULL,
	postal_code varchar(10) NOT NULL,
	country varchar(56) NOT NULL, 
	title varchar(30),
	resume bytea NOT NULL, 
	photo bytea NOT NULL,
	status employee_status NOT NULL,
	created_date timestamp with time zone DEFAULT now(),
	modified_date timestamp with time zone DEFAULT now()
);

CREATE FUNCTION set_updated_timestamp() 
RETURNS TRIGGER AS $$ 
BEGIN 
  NEW.modified_date= NOW(); 
  RETURN NEW; 
  END; 
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_timestamp 
BEFORE UPDATE ON employee
FOR EACH ROW 
EXECUTE PROCEDURE set_updated_timestamp();
