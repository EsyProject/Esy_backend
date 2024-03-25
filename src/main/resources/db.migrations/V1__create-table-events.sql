create table events(

    id bigint not null auto_increment,
    nome_event varchar(100) not null unique,
    category varchar(100) not null,
    place varchar(6) not null,
    data varchar(100) not null,
    responsible varchar(100) not null,
    cost_center varchar(100) not null,
    description varchar(9) not null,
    duration varchar(100),
    images TEXT not null,
    participating_areas char(6),
    number_of_participants varchar(100),

    primary key(id)

);