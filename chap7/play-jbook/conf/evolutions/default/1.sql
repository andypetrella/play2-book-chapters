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

create table chat (
  internal_id               bigint not null,
  topic                     varchar(255),
  date                      date,
  occurrence                integer,
  constraint pk_chat primary key (internal_id))
;

create table image (
  internal_id               bigint not null,
  CHAT_ID                   bigint not null,
  timestamp                 time,
  caption                   varchar(255),
  file_path                 varchar(255),
  user_email                varchar(255),
  constraint pk_image primary key (internal_id))
;

create table item (
  internal_id               bigint not null,
  CHAT_ID                   bigint not null,
  user_email                varchar(255),
  timestamp                 time,
  message                   varchar(255),
  constraint pk_item primary key (internal_id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  age                       integer,
  female                    boolean,
  address_internal_id       bigint,
  constraint pk_user primary key (email))
;

create sequence address_seq;

create sequence chat_seq;

create sequence image_seq;

create sequence item_seq;

create sequence user_seq;

alter table image add constraint fk_image_chat_1 foreign key (CHAT_ID) references chat (internal_id) on delete restrict on update restrict;
create index ix_image_chat_1 on image (CHAT_ID);
alter table image add constraint fk_image_user_2 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_image_user_2 on image (user_email);
alter table item add constraint fk_item_chat_3 foreign key (CHAT_ID) references chat (internal_id) on delete restrict on update restrict;
create index ix_item_chat_3 on item (CHAT_ID);
alter table item add constraint fk_item_user_4 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_item_user_4 on item (user_email);
alter table user add constraint fk_user_address_5 foreign key (address_internal_id) references address (internal_id) on delete restrict on update restrict;
create index ix_user_address_5 on user (address_internal_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists chat;

drop table if exists image;

drop table if exists item;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists chat_seq;

drop sequence if exists image_seq;

drop sequence if exists item_seq;

drop sequence if exists user_seq;

