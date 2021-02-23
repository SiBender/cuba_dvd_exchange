create table DVDEXCHANGE_DISK (
    ID bigint not null,
    UUID varchar(36),
    --
    TITLE varchar(255) not null,
    OWNER_ID varchar(36) not null,
    --
    primary key (ID)
);