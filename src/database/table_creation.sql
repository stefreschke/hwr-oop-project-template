create table users {
    id int primary key not null,
    'name' varchar(50) not null,
    'password' varchar(200) not null
}

create table tasks {
    id int primary key not null,
    assignedUserId int foreign key,
    description varchar(500),
    status int not null,
    priority int not null,
    dateTimeCreated text not null,
    dateTimeDone text,
    dateTimePlannedStart text,
    dateTimePlannedEnd text,
    dateTimeDeadline text,
    projectId int foreign key
}

create table assigned_to_task {
    userId int foreign key,
    taskId int foreign key
}

create table tagged {
    taskId int foreign key,
    tagId int foreign key
}

create table assigned_to_projects {
    userId int foreign key,
    projectId int foreign key
}

create table projects {
    id int primary key not null,
    title varchar(100) not null,
    description varchar(500),
    dateTimeCreated text not null,
    dateTimeDeadline text not null,
    ownerId int foreign key
}

create table tags {
    id int primary key not null,
    title varchar(100) not null
}
