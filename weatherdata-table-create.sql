-- Table: weatherdata

-- DROP TABLE weatherdata;

CREATE TABLE weatherdata
(
  stn integer NOT NULL,
  date date,
  "time" time without time zone,
  temp double precision,
  dewp double precision,
  stp double precision,
  slp double precision,
  visib double precision,
  prcp double precision,
  sndp double precision,
  frshtt bit(6),
  cldc double precision,
  wnddir integer,
  wdsp double precision,
  CONSTRAINT "PK" PRIMARY KEY (stn),
  CONSTRAINT "FK" FOREIGN KEY (stn)
      REFERENCES stations (stn) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE weatherdata
  OWNER TO postgres;
