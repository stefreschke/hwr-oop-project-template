create table IF NOT EXISTS users (
    id int primary key not null,
    'name' varchar(50) not null,
    'password' varchar(200) not null
)
;


create table IF NOT EXISTS tasks
(
    id                   int primary key not null,
    description          varchar(500),
    status               int             not null,
    priority             int             not null,
    dateTimeCreated      text            not null,
    dateTimeDone         text,
    dateTimePlannedStart text,
    dateTimePlannedEnd   text,
    dateTimeDeadline     text,
    projectId            int,
	foreign key (projectId) REFERENCES projects(id)
) 
;

create table IF NOT EXISTS assigned_to_task
(
    userId int, 
    taskId int, 
	foreign key (userId) REFERENCES users(id),
	foreign key (taskId) REFERENCES tasks(id)
) 
;

create table IF NOT EXISTS tagged
(
    taskId int,
	tagId  int,
	foreign key (taskId) REFERENCES tasks(id),
	FOREIGN key (tagId) REFERENCES tags(id)
) 
;
create table IF NOT EXISTS assigned_to_projects
(
    userId    int,
	projectId int, 
	foreign key (userId) REFERENCES users(id),
	foreign key (projectId) REFERENCES projects(id)
) 
;
create table IF NOT EXISTS projects
(
    id               int primary key not null,
    title            varchar(100)    not null,
    description      varchar(500),
    dateTimeCreated  text            not null,
    dateTimeDeadline text            not null,
    ownerId          int,
	foreign key (ownerId) REFERENCES owner(id)
) 
;
create table IF NOT EXISTS tags
(
    id    int primary key not null,
    title varchar(100)    not null
) 
;
