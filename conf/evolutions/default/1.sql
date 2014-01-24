# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table spot (
  id                        bigint not null,
  label                     varchar(255),
  last_update               timestamp,
  status                    varchar(9),
  constraint ck_spot_status check (status in ('AVAILABLE','OCCUPIED','TOXIC','UNKNOWN')),
  constraint pk_spot primary key (id))
;

create sequence spot_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists spot;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists spot_seq;


