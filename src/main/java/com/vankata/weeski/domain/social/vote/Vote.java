//package com.vankata.weeski.domain.social.vote;
//
//import com.vankata.weeski.domain.user.model.User;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "votes")
//public class Vote {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(nullable = false, unique = true)
//    private String id;
//
//    @Column(name = "entity_id", nullable = false)
//    private String entityId;
//
//    @Column(nullable = false)
//    private Boolean like;
//
//    @Column(nullable = false)
//    private Boolean dislike;
//
//    @ManyToOne
////    @JoinColumn(name = "user_id")
//    private User user;
//}
