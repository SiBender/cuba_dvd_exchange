-- begin DVDEXCHANGE_DISK
create table DVDEXCHANGE_DISK (
    ID bigint not null,
    UUID varchar(36),
    --
    TITLE varchar(255) not null,
    OWNER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end DVDEXCHANGE_DISK
-- begin DVDEXCHANGE_TAKEN_ITEM
create table DVDEXCHANGE_TAKEN_ITEM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DISK_ID bigint not null,
    USER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end DVDEXCHANGE_TAKEN_ITEM
-- begin SEC_USER
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'dvdexchange_DvdUser' where DTYPE is null ^
-- end SEC_USER
