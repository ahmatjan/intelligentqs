-- phpMyAdmin SQL Dump
-- version 4.1.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2014-05-13 07:14:21
-- 服务器版本： 5.5.34-0ubuntu0.13.10.1
-- PHP Version: 5.5.3-1ubuntu2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `qs`
--

-- --------------------------------------------------------

--
-- 表的结构 `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_description` varchar(500) NOT NULL,
  `answer_time` varchar(50) NOT NULL,
  `answer_mark` int(11) NOT NULL,
  `answer_user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_best` int(11) NOT NULL,
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `answer`
--

INSERT INTO `answer` (`answer_id`, `answer_description`, `answer_time`, `answer_mark`, `answer_user_id`, `question_id`, `answer_best`) VALUES
(1, '据我所知没有.\r\n只有一个参数-Xss可以用来设置每个栈的大小', '2014-05-13', 0, 1, 1, 0),
(2, '进程是可以控制的，线程的是没法控制的。只能增大Xmx Xms这些参数，或者优化程序。\r\n\r\n避免因为用户查询大量数据占用内存 这个可以通过程序优化的方式来解决，比如：用分页、动态分页、流的方式向前台输出数据（估计jvm没问题浏览器都改卡死了）', '2014-05-13', 0, 2, 1, 0),
(3, '监视文件变动，可以使用跨平台的 watchdog 模块，不过可能不支持 Python 3。\r\n\r\n在 Linux 平台可以使用 pyinotify 模块。\r\n\r\n一个简单的办法是每隔几秒检查一下文件的 mtime（最后修改时间）（Tornado 使用此法），不过比较耗资源。\r\n\r\nPS: 这些都和 git 不一样。git 有自己的数据库，里边存储了文件的上一个版本。你 git status 时它会把当前工作区的文件和它已经储存的版本进行比对。\r\n\r\nPPS: 所以你的需求还没说清楚。', '2014-05-13', 0, 1, 2, 0),
(4, '可以参考 django 或 flask 的 debug 模式，这些都会自动检测文件变动，然后重启开发服务器。不过具体我也没研究过它们的实现方式，你可以看看它们的源码', '2014-05-13', 0, 2, 2, 0);

-- --------------------------------------------------------

--
-- 表的结构 `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_title` varchar(100) NOT NULL,
  `question_description` varchar(500) DEFAULT NULL,
  `question_time` varchar(50) NOT NULL,
  `question_mark` int(11) NOT NULL,
  `question_user_id` int(11) NOT NULL,
  `question_tags` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `question`
--

INSERT INTO `question` (`question_id`, `question_title`, `question_description`, `question_time`, `question_mark`, `question_user_id`, `question_tags`) VALUES
(1, '在java中是否能限制每个线程的占用内存？', NULL, '2014-05-13', 0, 2, '2，4'),
(2, 'python怎么实现文件跟踪？', '问题说的可能不够严谨。比如使用git，在一个仓库内，git会判断出哪个文件做过修改，哪些已经加到缓冲区。类似的功能如果用python怎么实现？我的想法是给文件加一个标记（或者一个属性，有没有这种说法？），或者建立一个临时文件来做记录。\r\n这块知识对于我属于真空...以上说法或想法只是我自己想得。希望大家能给点思路或者实现方法。', '2014-05-13', 0, 3, '2,5,8');

-- --------------------------------------------------------

--
-- 表的结构 `tags_info`
--

CREATE TABLE IF NOT EXISTS `tags_info` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `tags_info`
--

INSERT INTO `tags_info` (`tag_id`, `tag_name`) VALUES
(1, '考研'),
(2, '计算机'),
(3, '数学'),
(4, 'java'),
(5, 'python'),
(6, 'mysql'),
(7, 'http'),
(8, 'linux'),
(9, '软件工程'),
(10, '算法');

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_email` varchar(40) DEFAULT NULL,
  `user_mark` int(11) NOT NULL,
  `user_tags` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`userid`, `user_name`, `user_password`, `user_email`, `user_mark`, `user_tags`) VALUES
(1, 'admin', 'admin', '306672962@qq.com', 0, '1,2'),
(2, 'xuyongli', 'xuyongli', '306672962@qq.com', 1, '1,2,3'),
(3, 'niu', 'niu', '306672962@qq.com', 0, '2.5.8');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
