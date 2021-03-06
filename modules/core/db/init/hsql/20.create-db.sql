-- begin DVDEXCHANGE_DISK
alter table DVDEXCHANGE_DISK add constraint FK_DVDEXCHANGE_DISK_ON_OWNER foreign key (OWNER_ID) references SEC_USER(ID)^
create index IDX_DVDEXCHANGE_DISK_ON_OWNER on DVDEXCHANGE_DISK (OWNER_ID)^
-- end DVDEXCHANGE_DISK
-- begin DVDEXCHANGE_TAKEN_ITEM
alter table DVDEXCHANGE_TAKEN_ITEM add constraint FK_DVDEXCHANGE_TAKEN_ITEM_ON_DISK foreign key (DISK_ID) references DVDEXCHANGE_DISK(ID)^
alter table DVDEXCHANGE_TAKEN_ITEM add constraint FK_DVDEXCHANGE_TAKEN_ITEM_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
create index IDX_DVDEXCHANGE_TAKEN_ITEM_ON_DISK on DVDEXCHANGE_TAKEN_ITEM (DISK_ID)^
create index IDX_DVDEXCHANGE_TAKEN_ITEM_ON_USER on DVDEXCHANGE_TAKEN_ITEM (USER_ID)^
-- end DVDEXCHANGE_TAKEN_ITEM
