create sequence hibernate_sequence;

CREATE TABLE blog_post
(
    id UUID NOT NULL
      CONSTRAINT blog_post_pkey
      PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    content TEXT NOT NULL,
    tags VARCHAR(256) NOT NULL,
    author VARCHAR(256) NOT NULL,
    published_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE association_value_entry
(
  id                bigint       not null
    constraint association_value_entry_pkey
    primary key,
  association_key   varchar(255) not null,
  association_value varchar(255),
  saga_id           varchar(255) not null,
  saga_type         varchar(255)
);

create index idxk45eqnxkgd8hpdn6xixn8sgft
  on association_value_entry (saga_type, association_key, association_value);

create index idxgv5k1v2mh6frxuy5c0hgbau94
  on association_value_entry (saga_id, saga_type);


create table domain_event_entry
(
  global_index         bigint       not null
    constraint domain_event_entry_pkey
    primary key,
  event_identifier     varchar(255) not null
    constraint uk_fwe6lsa8bfo6hyas6ud3m8c7x
    unique,
  meta_data            oid,
  payload              oid          not null,
  payload_revision     varchar(255),
  payload_type         varchar(255) not null,
  time_stamp           varchar(255) not null,
  aggregate_identifier varchar(255) not null,
  sequence_number      bigint       not null,
  type                 varchar(255),
  constraint uk8s1f994p4la2ipb13me2xqm1w
  unique (aggregate_identifier, sequence_number)
);

create table saga_entry
(
  saga_id         varchar(255) not null
    constraint saga_entry_pkey
    primary key,
  revision        varchar(255),
  saga_type       varchar(255),
  serialized_saga oid
);

create table snapshot_event_entry
(
  aggregate_identifier varchar(255) not null,
  sequence_number      bigint       not null,
  type                 varchar(255) not null,
  event_identifier     varchar(255) not null
    constraint uk_e1uucjseo68gopmnd0vgdl44h
    unique,
  meta_data            oid,
  payload              oid          not null,
  payload_revision     varchar(255),
  payload_type         varchar(255) not null,
  time_stamp           varchar(255) not null,
  constraint snapshot_event_entry_pkey
  primary key (aggregate_identifier, sequence_number, type)
);