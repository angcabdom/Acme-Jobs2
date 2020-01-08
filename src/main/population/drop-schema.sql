
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `audit_record` 
       drop 
       foreign key `FKdcrrgv6rkfw2ruvdja56un4ji`;

    alter table `audit_record` 
       drop 
       foreign key `FKlbvbyimxf6pxvbhkdd4vfhlnd`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `authenticated_message_thread` 
       drop 
       foreign key `FKt8r6monb8mr4j7lt956c4mn9n`;

    alter table `authenticated_message_thread` 
       drop 
       foreign key `FKhwg236pcsp6wtya372osd10qy`;

    alter table `configuration_spam_words` 
       drop 
       foreign key `FK5lk29cpqe3960a943x8x8j4yh`;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `mandatory_duty` 
       drop 
       foreign key `FK1hlimp4r545wpojqep3x63adv`;

    alter table `message` 
       drop 
       foreign key `FKe1edpykjs39o98sfkjafa0dtn`;

    alter table `message` 
       drop 
       foreign key `FKn5adlx3oqjna7aupm8gwg3fuj`;

    alter table `message_thread` 
       drop 
       foreign key `FK3fa4h4tfet2kocvatib2ovhsa`;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcement`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit_record`;

    drop table if exists `auditor`;

    drop table if exists `authenticated`;

    drop table if exists `authenticated_message_thread`;

    drop table if exists `caballero_bulletin`;

    drop table if exists `challenge`;

    drop table if exists `company_record`;

    drop table if exists `configuration`;

    drop table if exists `configuration_spam_words`;

    drop table if exists `consumer`;

    drop table if exists `correa_bulletin`;

    drop table if exists `employer`;

    drop table if exists `granja_bulletin`;

    drop table if exists `investor_record`;

    drop table if exists `job`;

    drop table if exists `mandatory_duty`;

    drop table if exists `message`;

    drop table if exists `message_thread`;

    drop table if exists `morante_bulletin`;

    drop table if exists `offer`;

    drop table if exists `provider`;

    drop table if exists `request_`;

    drop table if exists `shout`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `hibernate_sequence`;
