CREATE TABLE `answer` (
  `question_id` int(11) NOT NULL,
  `author_id` varchar(255) NOT NULL,
  `like_num` int(11) DEFAULT NULL,
  `thanks_num` int(11) DEFAULT NULL,
  `comment_num` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 动态 2 精华 3等待回答',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`question_id`,`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `description` text,
  `questioner` varchar(255) DEFAULT NULL,
  `raiseTime` datetime DEFAULT NULL,
  `answer_num` int(11) DEFAULT NULL,
  `follow_num` int(11) DEFAULT NULL,
  `tags` varchar(1024) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `look_time` int(11) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `father_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `follower_num` int(11) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `question_num` int(11) DEFAULT NULL,
  `essence_num` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `zhihu_user` (
  `zhihu_id` varchar(255) NOT NULL,
  `gander` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 普通用户 2 机构',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `follower_num` int(11) DEFAULT NULL,
  `like_num` int(11) DEFAULT NULL,
  `thank_num` int(11) DEFAULT NULL,
  `anser_num` int(11) DEFAULT NULL,
  `question_num` int(11) DEFAULT NULL,
  `essay_num` int(11) DEFAULT NULL,
  `education` varchar(1024) DEFAULT NULL,
  `job` varchar(1024) DEFAULT NULL,
  `industry` varchar(255) DEFAULT NULL,
  `collection_num` int(11) DEFAULT NULL COMMENT '收藏次数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`zhihu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;