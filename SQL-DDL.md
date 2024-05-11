## 数据库构建DDL

### drm_group

```mariadb
create table drm_group
(
    id                              bigint auto_increment
        primary key,
    group_name                      varchar(128)         not null,
    permission_show_profile         tinyint(1) default 0 not null,
    permission_login                tinyint(1) default 0 not null,
    permission_create_chain_account tinyint(1) default 0 not null,
    permission_create_right         tinyint(1) default 0 not null,
    permission_verify_right         tinyint    default 0 not null,
    permission_create_license       tinyint(1) default 0 not null,
    permission_verify_license       tinyint(1) default 0 not null,
    constraint group_name
        unique (group_name)
);
```

### drm_notice

```mariadb
create table drm_notice
(
    id           bigint auto_increment
        primary key,
    title        varchar(128) not null,
    content      text         not null,
    receiver_id  bigint       not null,
    sent_time    bigint       not null,
    status       varchar(128) not null,
    target_route varchar(128) null
);
```

### drm_pending_license

```mariadb
create table drm_pending_license
(
    id             bigint auto_increment
        primary key,
    right_title    varchar(128) not null,
    right_deployer varchar(128) not null,
    right_index    bigint       not null,
    deployer       varchar(128) not null,
    owner          varchar(128) not null,
    issue_time     bigint       not null,
    expire_time    bigint       not null,
    description    text         null,
    status         varchar(128) not null,
    estimate_price bigint       null,
    comment        text         null,
    create_time    bigint       not null
);
```

### drm_pending_right

```mariadb
create table drm_pending_right
(
    id                  bigint auto_increment
        primary key,
    title               varchar(128) not null,
    deployer            varchar(128) not null,
    owner               varchar(128) not null,
    registration_number varchar(128) not null,
    issue_time          bigint       not null,
    expire_time         bigint       not null,
    description         text         null,
    file_name           varchar(128) not null,
    file_hash           varchar(128) not null,
    status              varchar(128) not null,
    estimate_price      bigint       null,
    comment             text         null,
    create_time         bigint       not null,
    constraint FILE_HASH_UK
        unique (file_hash),
    constraint REGISTRATION_NUMBER_UK
        unique (registration_number)
);

create index TITLE_INDEX
    on drm_pending_right (title);
```

### drm_receipt

```mariadb
create table drm_receipt
(
    id               bigint auto_increment
        primary key,
    status           varchar(128) not null,
    transaction_hash varchar(128) not null,
    block_hash       varchar(128) not null,
    block_number     bigint       not null,
    gas_used         bigint       not null,
    deployer         varchar(128) not null,
    deploy_index     bigint       not null,
    create_time      bigint       not null,
    constraint drm_receipt_pk_2
        unique (transaction_hash)
);
```

### drm_user

```mariadb
create table drm_user
(
    id            bigint auto_increment
        primary key,
    permission_id bigint       not null,
    username      varchar(128) not null,
    nickname      varchar(128) not null,
    password      varchar(128) not null,
    email         varchar(128) null,
    phone_number  varchar(128) null,
    constraint user_pk_username
        unique (username),
    constraint drm_user_group_id_fk
        foreign key (permission_id) references drm_group (id)
);
```

### drm_wallet

```mariadb
create table drm_user
(
    id            bigint auto_increment
        primary key,
    permission_id bigint       not null,
    username      varchar(128) not null,
    nickname      varchar(128) not null,
    password      varchar(128) not null,
    email         varchar(128) null,
    phone_number  varchar(128) null,
    constraint user_pk_username
        unique (username),
    constraint drm_user_group_id_fk
        foreign key (permission_id) references drm_group (id)
);
```