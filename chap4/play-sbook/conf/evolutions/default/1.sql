# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  internal_id               bigint not null,
  full_street               varchar(255),
  county                    varchar(255),
  country                   varchar(255),
  constraint pk_address primary key (internal_id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  age                       integer,
  gender                    boolean,
  address_internal_id       bigint,
  constraint pk_user primary key (email))
;

create sequence address_seq;

create sequence user_seq;

alter table user add constraint fk_user_address_1 foreign key (address_internal_id) references address (internal_id) on delete restrict on update restrict;
create index ix_user_address_1 on user (address_internal_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists user_seq;

