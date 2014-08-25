-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014-08-24 04:45:01
-- 服务器版本: 5.5.37-0ubuntu0.13.10.1
-- PHP 版本: 5.5.3-1ubuntu2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `hnust_qa`
--

-- --------------------------------------------------------

--
-- 表的结构 `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_description` varchar(2000) NOT NULL,
  `answer_time` varchar(50) NOT NULL,
  `answer_mark` int(11) NOT NULL,
  `answer_user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_best` int(11) NOT NULL,
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `answer`
--

INSERT INTO `answer` (`answer_id`, `answer_description`, `answer_time`, `answer_mark`, `answer_user_id`, `question_id`, `answer_best`) VALUES
(1, '据我所知没有.\r\n只有一个参数-Xss可以用来设置每个栈的大小', '2014-05-13', 0, 1, 1, 0),
(2, '进程是可以控制的，线程的是没法控制的。只能增大Xmx Xms这些参数，或者优化程序。\r\n\r\n避免因为用户查询大量数据占用内存 这个可以通过程序优化的方式来解决，比如：用分页、动态分页、流的方式向前台输出数据（估计jvm没问题浏览器都改卡死了）', '2014-05-13', 0, 2, 1, 0),
(3, '监视文件变动，可以使用跨平台的 watchdog 模块，不过可能不支持 Python 3。\r\n\r\n在 Linux 平台可以使用 pyinotify 模块。\r\n\r\n一个简单的办法是每隔几秒检查一下文件的 mtime（最后修改时间）（Tornado 使用此法），不过比较耗资源。\r\n\r\nPS: 这些都和 git 不一样。git 有自己的数据库，里边存储了文件的上一个版本。你 git status 时它会把当前工作区的文件和它已经储存的版本进行比对。\r\n\r\nPPS: 所以你的需求还没说清楚。', '2014-05-13', 0, 1, 2, 1),
(4, '可以参考 django 或 flask 的 debug 模式，这些都会自动检测文件变动，然后重启开发服务器。不过具体我也没研究过它们的实现方式，你可以看看它们的源码', '2014-05-13', 0, 2, 2, 0),
(5, '这个问题问的好\\(^o^)/~', '2014-5-20-  12:54:31', 0, 1, 0, 0),
(6, '我不知道这个问题，表问我！！！\r\n', '2014-5-20-  13:13:56', 0, 1, 0, 1),
(7, '智商捉鸡啊吖，这么弱智的问题也问', '2014-5-20-  13:15:9', 0, 1, 0, 0),
(8, '今天不下雨，逗比！\r\n', '2014-5-20-  13:18:27', 0, 1, 0, 0),
(9, '这个........', '2014-5-20-  13:20:4', 0, 1, 0, 1),
(10, '这个........', '2014-5-20-  13:20:38', 0, 1, 0, 0),
(11, '两个的值都会提交到服务器端，就看服务器端用的是什么语言了。\r\nasp中根据name取值的话，会是以逗号加空格连接取来的字符串\r\nasp.net中则是以逗号连接起来的字符串\r\n其他语言的就不太清楚了 \r\n\r\nform里面input大概如下3个么？\r\n<input type="text" name="test" value="" style="display:'''';" ></input>\r\n<input type="text" name="test" value="test" style="display:none;" ></input>\r\n<input type="hidden" name="test" value="h" />\r\n我用Firefox的firebug监控了post的参数，都会提交的，参数名称都是test，只是post的值不一样，这说明浏览器都不把input提交到服务器端的，只是服务器端根据所用的平台及语言接收时候，写法不一样罢了。你在java里面遍历看看 \r\n\r\n查到了，java中如果用request.getParameter（String   name）是获得相应名的数据，如果有重复的名，则返回第一个的值 .如果用request.getParameterValues(String   name)是获得名字相同，但值有多个的数据， 接收数组变量 。你可以用的是第一个，改用request.getParameterValues就会得到数组，如上面输入abc的话，得到的就是[abc, test, h],至于数组java该怎么用就怎么用了', '2014-6-28-  15:14:10', 0, 1, 0, 0),
(12, '计算机科学 C语言 等等', '2014-6-28-  15:22:17', 0, 1, 0, 0),
(13, '我是计算机科学与技术专业的，我们的课程偏软件和网络方向，C语言，数据结构，数电与模电，汇编语言，C++，VC++，数据库，操作系统，计算机网络，计算机组成原理，软件工程，asp，ps，flash，perimer，计算机图形学，Linux（红旗，红帽子，Ubuntu）下的编程与组网服务器的应用，网络工程，计算机前沿技术 一下子就只能想出这么多专业课\r\n各个大学各个专业侧重点会不一样，不过给你说下考研的课程吧。全国都一样 数据结构，操作系统，计算机网络，计算机组成原理 应该是本科阶段的核心课程。\r\n至于编译原理 看自己的兴趣，反正我本科没学，翻过几页', '2014-6-28-  15:29:6', 0, 1, 10, 0),
(14, '这个不就是科大食府吗？味道一般。。。。。。有时候有石头小青虫会跑出来~~~啦啦啦啦~~', '2014-6-28-  15:33:26', 0, 1, 9, 0),
(15, '没吃过，不谢\r\n', '2014-6-28-  15:33:50', 0, 1, 9, 0),
(16, 'MongoDB数据不存在时插入，已存在时更新', '2014-6-28-  15:35:12', 0, 1, 9, 0),
(17, 'MongoDB数据不存在时插入，已存在时更新', '2014-6-28-  15:39:0', 0, 1, 9, 0),
(18, '不可以，逗比。大三可以', '2014-6-28-  15:43:33', 0, 1, 8, 0),
(19, '半个月左右，不放假', '2014-6-28-  15:47:59', 0, 1, 7, 0),
(20, '周五', '2014-6-28-  20:21:24', 0, 1, 22, 0),
(21, '你是 范德萨 地方', '2014-6-28-  20:21:38', 0, 1, 22, 0),
(22, '不认识，呵呵', '2014-7-7-  9:45:31', 0, 1, 23, 0),
(24, '那就不说啊', '2014-7-13-  22:47:54', 0, 1, 24, 0),
(25, '问题是个好问题，就是回答不出来 啊', '2014-7-13-  22:48:17', 0, 1, 24, 0),
(26, '在二本上还可以，湖南比较好的二本院校是：湖南农大，中南林科大，湖南科大，南华大，湖南商学院。\r\n湖南科大主要是在与“采矿”有关的专业比较好，其他就非常一般，假如不想报考与采矿有关的专业建议最好别去，在加上，湖南科大在湘潭这地方不怎么样……除了了毛泽东什么也没有了，长株潭是长沙最好，株洲也不错，湘潭最垃圾，大学地点也比较重要，最好去长沙，湖南农大实力强在长沙，中南林科大也不错，也在长沙，校园风景很好……在市中心！\r\n去湖南科大建议报  采矿  因为湖南科大是前几年合并的大学，以前就没有湖南科大，它是有湘潭工学院与湘潭师专……合并的！\r\n不过现在已经升一本了，貌似出北京广州外均是一本招生。', '2014-7-13-  22:50:52', 0, 1, 26, 0),
(27, '湖南科技大学地处湘潭，在湖南省排名在第5或第6左右，属於省重点建设大学，第一批本科招生。其中地质勘探专业比较牛。湖南科技大学的综合实力绝对高於湖南农业大学，总所周知，湖南农业大学除了农林方面比较厉害，其他就属於鸡肋专业。湖南科技大学坐落在湘潭，没有省会那麼浮躁，和湘潭大学彼肩而立。绝对是读书的好地方。学生的素质相对还是比较高的。你可以去官网具体瞭解。', '2014-7-13-  22:52:7', 0, 1, 27, 0),
(28, '我这边也经常进不去，还校园网呢，不过一般早上好进去，试试。。。 不过、、、现在15:49，咦？可以进。。。 这学期的校园网 就是这样让人琢磨不透。', '2014-7-13-  22:55:2', 0, 1, 28, 0),
(29, '这个系统是湖南科技大学的问答平台，为hnuster提供一个自己学校的百科全书，大家可以提出自己的问题，也可以帮助别人，建立一个互帮互助的网络平台，在开发后期，可以根据相关的信息，构建一个问答网络和好友网络，实现答案推荐和好友推荐。请大家一起维护我们自己的平台，不要灌水，追求绿色和谐！我们一起努力，加油！\r\n                                                                                                                                                                         王大锤', '2014-7-14-  15:9:8', 0, 1, 1, 0),
(30, '就我们四个人玩，太没意思了。都想@许永利了。', '2014-7-14-  20:39:22', 0, 1, 29, 0),
(31, '比好', '2014-7-19-  16:17:37', 0, 1, 16, 0),
(32, '我次啊，不用登陆啊', '2014-7-19-  16:18:12', 0, 1, 16, 0),
(33, '天知道', '2014-7-19-  16:18:53', 0, 1, 26, 0),
(34, '邮箱验证功能可能无法重复验证多次，这是用于smtp邮箱协议和一些邮箱服务提供商所决定的，他们会把短时间重复发送多次的邮箱比较为垃圾邮箱，所以可能无法验证。如遇到哪这种情况，亲们可以发送邮件给：707406343@qq.com，管理员收到邮件后会第一时间处理。', '2014-7-24-  10:47:19', 0, 4, 30, 0),
(35, '对面相关问题是假的，链接功能根本用不了。还有标签添加了为什么没有反应啊，添加后还是没有显示。求解释？？？？？', '2014-7-24-  10:48:58', 0, 9, 30, 0),
(36, '有', '2014-7-30-  9:23:6', 0, 17, 31, 0),
(37, '竟敢如此地侮辱我，你这是作死滴节奏么???', '2014-7-30-  10:31:28', 0, 1, 31, 0),
(38, '我能经得起多少诋毁，就能经得起多少赞美。Lumia ——不跟随！', '2014-7-30-  10:33:18', 0, 4, 31, 0),
(39, '测试', '2014-7-30-  15:17:59', 0, 17, 31, 0),
(40, 'CSS网页中的相对定位与绝对定位在CSS中有这样的一个指令：(position)，在DreamWeaver中文版中翻译为“定位”，常用的属性有两个：relative(相对)与 absolute(绝对)。有很多朋友对这条指令的用法还是不清楚，这里做一些细致的讲解。position:relative; 表示相对定位，被定位了这个属性的标签在所属的范围内可以进行上下左右的移，这里的移动与padding或是margin所产生的位置变化是不一样的。padding与margin是元素本身的一种边距与填充距离并不是真正的移动，而被定义为relative的元素是真正的移动，这所产生的移动距离是从margin的外围到父级标签内侧之间这一段。position:absolute; 表示绝对定位，如果定义了这个属性的元素，其位置将依据浏览器左上角的0点开始计算，并且是浮动正常元素之上的。那么当你需要某个元素定位在浏览器内容区的某个地方就可以用到这个属性。于是产生了一个问题：现在大家做的网页大部分是居中的，如果我需要这个元素跟着网页中的某个元素位置，不论屏幕的分辨率是多少它的位置始终是针对页内的某个元素的，靠单纯的absolute是不行的。正确的解决方法是：在元素的父级元素定义为position:relative;(这里可以是祖父级，也可以是position:absolute;，多谢谢old9的提出)需要绝对定位的元素设为position:absolute;这样再设定top,right,bottom,left的值就可以了，这样其定位的参照标准就是父级的左上角padding的左上侧。', '2014-7-31-  12:12:7', 0, 4, 32, 0),
(41, 'SD ', '2014-8-13-  21:29:20', 0, 1, 32, 0);

-- --------------------------------------------------------


--
-- 表的结构 `answers_keywords`
--

CREATE TABLE IF NOT EXISTS `answers_keywords` (
  `answers_keywords_id` int(11) NOT NULL AUTO_INCREMENT,
  `answers_id` int(11) NOT NULL,
  `answers_keywords_topN` varchar(2000) DEFAULT NULL,
  `answers_keywords_counts` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`answers_keywords_id`),
  KEY `answer_id_keywords` (`answers_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `answers_keywords`
--

INSERT INTO `answers_keywords` (`answers_keywords_id`, `answers_id`, `answers_keywords_topN`, `answers_keywords_counts`) VALUES
(1, 1, '参数,大小,设置,xss,', '1,1,1,1,'),
(2, 2, '优化,程序,数据,控制,方式,分页,参数,只能,内存,jvm,线程,查询,进程,前台,动态,浏览器,xmx,增大,xms,输出,', '2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(3, 3, '文件,git,平台,版本,模块,比对,不支持,需求,里边,资源,时间,pyinotify,python,变动,不一样,还没,上一个,检查一下,简单,储存,', '4,3,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(4, 4, '自动检测,源码,参考,flask,开发,服务器,django,debug,都会,变动,模式,方式,重启,研究,文件,', '1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(5, 5, '', ''),
(6, 6, '不知道,问我,', '1,1,'),
(7, 7, '弱智,智商,', '1,1,'),
(8, 8, '不下雨,', '1,'),
(9, 9, '', ''),
(10, 10, '', ''),
(11, 11, 'input,test,服务器端,type,数组,语言,java,abc,request.getparametervalues,text,交到,display,逗号,参数,数据,都会,不一样,style,post,第一个,', '7,6,4,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,'),
(12, 12, '计算机,科学,语言,', '1,1,1,'),
(13, 13, '计算机,原理,课程,本科,数据结构,操作系统,专业,计算机网络,flash,只能,技术,红帽子,网络,大学,说下,几页,没学,图形学,前沿技术,编译,', '5,3,3,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,'),
(14, 14, '大食,不就是,有时候,石头,味道,跑出来,', '1,1,1,1,1,1,'),
(15, 15, '没吃过,', '1,'),
(16, 16, '数据,更新,mongodb,插入,不存在,', '1,1,1,1,1,'),
(17, 17, '数据,更新,mongodb,插入,不存在,', '1,1,1,1,1,'),
(18, 18, '大三,不可以,', '1,1,'),
(19, 19, '放假,半个月,', '1,1,'),
(20, 20, '周五,', '1,'),
(21, 21, '地方,', '1,'),
(22, 22, '不认识,', '1,'),
(23, 24, '那就,不说,', '1,1,'),
(24, 25, '问题是,不出来,回答,', '1,1,1,'),
(25, 26, '湖南,科大,湘潭,长沙,采矿,二本,专业,建议,大学,一本,不错,比较好,农大,中南林,合并,北京,南华,师专,报考,地方,', '10,8,4,4,3,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,'),
(26, 27, '湖南,科技大学,湘潭,专业,大学,农业大学,厉害,落在,地处,鸡肋,农林,读书,素质,地质勘探,本科,综合,去官,排名,学生,好地方,', '5,3,3,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(27, 28, '校园网,49,学期,早上好,琢磨不透,15,试试,进不去,', '2,1,1,1,1,1,1,1,'),
(28, 29, '平台,网络,推荐,和好友,问答,开发,构建,加油,绿色,科技大学,信息,答案,提供,后期,百科全书,灌水,互帮互助,和谐,建立,学校,', '3,3,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(29, 30, '太没,四个人,都想,', '1,1,1,'),
(30, 31, '', ''),
(31, 32, '登陆,不用,', '1,1,'),
(32, 33, '天知道,', '1,'),
(33, 34, '邮箱,验证,邮件,发送,重复,smtp,会把,收到,提供商,第一时间,议和,这是,707406343@qq.com,用于,后会,情况,短时间,管理员,功能,服务,', '5,3,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(34, 35, '没有反应,显示,用不了,问题是,对面,标签,加了,功能,链接,相关,添加,解释,', '1,1,1,1,1,1,1,1,1,1,1,1,'),
(35, 36, '', ''),
(36, 37, '节奏,作死,竟敢,这是,侮辱,', '1,1,1,1,1,'),
(37, 38, '经得起,诋毁,就能,lumia,跟随,赞美,', '2,1,1,1,1,1,'),
(38, 39, '测试,', '1,'),
(39, 40, '元素,定位,position,absolute,relative,位置,属性,padding,margin,定义,网页,就可以,位在,浏览器,以是,指令,css,距离,标签,左上角,', '11,7,6,5,4,4,4,3,3,3,3,2,2,2,2,2,2,2,2,2,'),
(40, 41, 'sd,', '1,');

-- --------------------------------------------------------


--
-- 表的结构 `discuss`
--

CREATE TABLE IF NOT EXISTS `discuss` (
  `discuss_id` int(11) NOT NULL AUTO_INCREMENT,
  `discuss_content` varchar(500) NOT NULL,
  `discuss_time` varchar(50) NOT NULL,
  `discuss_user_id` int(11) NOT NULL,
  `discuss_answer_id` int(11) NOT NULL,
  PRIMARY KEY (`discuss_id`),
  KEY `answer_discuss` (`discuss_answer_id`),
  KEY `user_discuss` (`discuss_user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `discuss`
--

INSERT INTO `discuss` (`discuss_id`, `discuss_content`, `discuss_time`, `discuss_user_id`, `discuss_answer_id`) VALUES
(1, '为什么mongodb插入数据经常出现时间相同的情况', '2014-07-21 02:24:53', 4, 23),
(6, '梵蒂冈梵蒂冈', '2014-07-21 05:28:53', 1, 24),
(7, 'sdfds dsf ', '2014-07-21 05:40:55', 1, 2),
(9, 'sdfds dsf ', '2014-07-21 05:41:17', 1, 5),
(12, '这问的有水平啊', '2014-07-21 09:23:20', 5, 22),
(13, '为什么又可以了？', '2014-07-21 09:23:39', 5, 23),
(14, '小飞机到此一游，不谢', '2014-07-21 09:49:04', 4, 30),
(15, '傻，你看你都回答了什么，灌水么？', '2014-07-22 12:41:26', 4, 25),
(16, '我是冒充哦', '2014-07-22 03:57:40', 9, 30),
(17, '测试你妹啊，不许灌水。', '2014-07-30 07:00:20', 4, 39);

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
  `question_categories_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`question_id`),
  KEY `question` (`question_categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `question`
--

INSERT INTO `question` (`question_id`, `question_title`, `question_description`, `question_time`, `question_mark`, `question_user_id`, `question_tags`) VALUES
(1, '这是做什么的？', '这个系统是做什么的？', '2014-05-13', 1, 2, '2'),
(2, 'python', '是中编程语言么？', '2014-05-13', 20, 3, '2,5,8'),
(3, '今天星期几？', '时间', '2014-05-21 08:03:12', 1, 0, '生活'),
(4, '你是不是二货？', '迷惑', '2014-05-21 08:05:19', 23, 0, '娱乐'),
(5, 'SQL语句区分大小写么？', '数据库查询语句区分大小写么？', '2014-05-21 08:06:33', 6, 0, '数据库'),
(7, '大一军训多久？', '湖南科技大学大一新生军训多长时间，严不严？可以不军训么....', '2014-05-21 10:21:43', 0, 0, '新生'),
(8, '大一可以谈恋爱么？', '大一新生谈恋爱的多不多？科大美女多不多，是南校女生多，还是北校多呀，那个校区女生整体质量比较好？', '2014-05-21 10:28:12', 0, 0, '其它'),
(9, '美食', '湖南科技大学那个食堂的饭比较好吃，那个食堂的最不好吃？', '2014-05-21 11:59:08', 0, 0, '美食'),
(10, '专业课程', '有谁知道大一计算机系有哪些课程?跪求解答！！！', '2014-05-22 12:00:48', 0, 0, '专业课程'),
(11, '系统问题', '为什么我发的答案显示不了？求许永利解释@许永利', '2014-05-22 12:12:17', 0, 0, '许永利'),
(12, '熬夜', '现在都00点13分了，有木有还没有睡的？', '2014-05-22 12:14:16', 0, 0, '困'),
(13, '问答有问题', '为什么每次发表问题后返回的页面中都没有我刚刚题的问题，求王大神解释！！！', '2014-05-22 12:15:44', 0, 0, '问答系统'),
(14, 'html按钮button怎么加超链接', '<input name="注册"  type="button"  id="btn1" title="登注册" value="注册" />怎么让这个点下，转向一个链接？ 晕。。 我不是要提交数据，只是想用按钮当个超链接啊！ ', '2014-05-25 11:07:49', 0, 0, 'HTML'),
(15, '返回值:jQueryjQuery', 'jQuery 的核心功能都是通过这个函数实现的。 jQuery中的一切都基于这个函数，或者说都是在以某种方式使用这个函数。这个函数最基本的用法就是向它传递一个表达式（通常由 CSS 选择器组成），然后根据这个表达式来查找所有匹配的元素。\r\n\r\n默认情况下, 如果没有指定context参数，$()将在当前的 HTML document中查找 DOM 元素；如果指定了 context 参数，如一个 DOM 元素集或 jQuery 对象，那就会在这个 context 中查找。在jQuery 1.3.2以后，其返回的元素顺序等同于在context中出现的先后顺序。\r\n', '2014-05-25 11:30:04', 0, 0, 'jQuery'),
(16, '13124', '32423', '2014-06-15 01:37:41', 0, 0, '543543'),
(17, '求GTASA里面的DATA文件 不要装MOD的', '还有我添加了个双枪WEAPON.DOT文件  开枪为什么会弹出', '2014-06-28 02:45:30', 0, 0, ''),
(18, '求GTASA里面的DATA文件 不要装MOD的', '还有我添加了个双枪WEAPON.DOT文件  开枪为什么会弹出', '2014-06-28 02:47:12', 0, 0, ''),
(19, '求GTASA里面的DATA文件 不要装MOD的', '还有我添加了个双枪WEAPON.DOT文件  开枪为什么会弹出', '2014-06-28 02:47:59', 0, 0, ''),
(20, 'input元素hidden属性和text属性的取值问题', '两个input元素，一个hidden属性，一个text属性，名字都叫name的话，提交到表单的值是hidden的还是text的\r\n是jsp的，两个同名input，属性均为text，但是设置一个display：“”\r\n一个display：none；显示出来的input值是有的，不显示的input的值是空，交到后台，得不到值，加上一个同名hidden就能取到了，就想知道同名元素怎么取值的', '2014-06-28 03:12:11', 0, 0, ''),
(21, 'input元素hidden属性和text属性的取值问题', '两个input元素，一个hidden属性，一个text属性，名字都叫name的话，提交到表单的值是hidden的还是text的\r\n是jsp的，两个同名input，属性均为text，但是设置一个display：“”\r\n一个display：none；显示出来的input值是有的，不显示的input的值是空，交到后台，得不到值，加上一个同名hidden就能取到了，就想知道同名元素怎么取值的', '2014-06-28 03:14:35', 0, 0, ''),
(22, '''几天周几？', '今天时间...', '2014-06-28 08:21:02', 0, 0, ''),
(23, '我们认识么？', '我想知道我们究竟认识么、？', '2014-04-13', 34, 1, '情感'),
(24, '这事儿一句话说不清楚', '这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚', '2014-07-13 03:07:04', 0, 5, ''),
(26, '湖南科技大学怎么样', '湖南科技大学怎么样', '2014-07-13 10:49:58', 0, 1, ''),
(27, '湖南科技大学什么专业最好？', '想报考那个学校。', '2014-07-13 10:51:55', 0, 1, ''),
(28, '湖南科技大学图书馆在哪？', '位置在哪儿', '2014-07-13 10:54:46', 2, 1, ''),
(29, '计算机专业下个学期的课程设计是做什么，做多久啊？', '如题，请大声告诉我一下，感激不尽！！！', '2014-07-14 02:58:28', -1, 7, '课程设计'),
(30, '智能问答平台功能更新', '湖南科技大学智能问答平台功能更新说明：\r\n1、系统安全方面注册和登录增加验证码功能；\r\n2、用户的注册成功邮箱提醒和密码邮箱验证密码找回。', '2014-07-24 10:43:52', 0, 1, ''),
(31, '请问还有没有第六个人在！！', '哈哈哈哈哈哈哈哈哈哈，我是来打酱油的！！！', '2014-07-28 08:51:48', 1, 18, '酱油'),
(32, 'CSS绝对定位与相对定位的区别？', 'CSS绝对定位与相对定位的区别？', '2014-07-31 12:11:34', 1, 4, '网页设计');

-- --------------------------------------------------------


--
-- 表的结构 `questions_keywords`
--

CREATE TABLE IF NOT EXISTS `questions_keywords` (
  `questions_keywords_id` int(11) NOT NULL AUTO_INCREMENT,
  `questions_id` int(11) NOT NULL,
  `quesitons_keywords_topN` varchar(2000) DEFAULT NULL,
  `questions_keywords_counts` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`questions_keywords_id`),
  KEY `questions_keywords` (`questions_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `questions_keywords`
--

INSERT INTO `questions_keywords` (`questions_keywords_id`, `questions_id`, `quesitons_keywords_topN`, `questions_keywords_counts`) VALUES
(1, 31, '哈哈哈,请问,第六个,酱油,哈哈哈哈,人在,', '2,1,1,1,1,1,'),
(2, 30, '功能,问答,注册,智能,平台,更新,邮箱,验证码,成功,用户,登录,密码,科技大学,系统安全,湖南,提醒,密码找回,验证,增加,', '3,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,'),
(3, 29, '如题,专业,大声,做什么,计算机,学期,感激不尽,多久,告诉我,课程设计,下个,', '1,1,1,1,1,1,1,1,1,1,1,'),
(4, 28, '科技大学,图书馆,位置,湖南,在哪儿,在哪,', '1,1,1,1,1,1,'),
(5, 27, '科技大学,专业,学校,湖南,报考,', '1,1,1,1,1,'),
(6, 26, '科技大学,湖南,', '2,2,'),
(7, 24, '说不清楚,一句话,这事儿,', '16,16,16,'),
(8, 23, '想知道,', '1,'),
(9, 22, '天周,时间,', '1,1,'),
(10, 21, 'input,属性,text,hidden,元素,同名,交到,display,两个,取值,显示,表单,jsp,显示出来,就能,就想,得不到,设置,名字,均为,', '5,5,4,4,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,'),
(11, 20, 'input,属性,text,hidden,元素,同名,交到,display,两个,取值,显示,表单,jsp,显示出来,就能,就想,得不到,设置,名字,均为,', '5,5,4,4,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,'),
(12, 19, '文件,weapon.dot,开枪,mod,加了,data,弹出,gtasa,', '2,1,1,1,1,1,1,1,'),
(13, 18, '文件,weapon.dot,开枪,mod,加了,data,弹出,gtasa,', '2,1,1,1,1,1,1,1,'),
(14, 17, '文件,weapon.dot,开枪,mod,加了,data,弹出,gtasa,', '2,1,1,1,1,1,1,1,'),
(15, 16, '1312432423,', '1,'),
(16, 15, '函数,元素,context,jquery,查找,参数,dom,指定,表达式,都是,1.3.2,如一,某种,匹配,document,默认,jqueryjqueryjquery,顺序,将在,选择器,', '4,4,4,3,3,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,'),
(17, 14, '注册,超链接,按钮,button,当个,转向,链接,type,数据,input,title,想用,btn1,html,提交,', '3,2,2,2,1,1,1,1,1,1,1,1,1,1,1,'),
(18, 13, '发表,大神,每次,问答,中都,解释,刚刚,页面,返回,', '1,1,1,1,1,1,1,1,1,'),
(19, 12, '还没有,00点,分了,熬夜,13,', '1,1,1,1,1,'),
(20, 11, '显示,答案,系统,解释,', '1,1,1,1,'),
(21, 10, '计算机系,解答,有谁,大一,跪求,专业课程,课程,', '1,1,1,1,1,1,1,'),
(22, 9, '好吃,食堂,科技大学,最不,湖南,美食,', '2,2,1,1,1,1,'),
(23, 8, '谈恋爱,女生,大一,多不多,新生,质量,校区,美女,科大,整体,比较好,', '2,2,2,2,1,1,1,1,1,1,1,'),
(24, 7, '军训,大一,科技大学,多长时间,新生,不严,湖南,多久,', '3,2,1,1,1,1,1,1,'),
(25, 5, '语句,区分,大小写,sql,数据库,查询,', '2,2,2,1,1,1,'),
(26, 4, '迷惑,', '1,'),
(27, 3, '星期,时间,', '1,1,'),
(28, 2, 'python,编程,语言,', '1,1,1,'),
(29, 1, '做什么,系统,这是,', '2,1,1,'),
(30, 32, '定位,区别,css,', '4,2,2,');

-- --------------------------------------------------------


--
-- 表的结构 `tags_info`
--

CREATE TABLE IF NOT EXISTS `tags_info` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) NOT NULL,
  `tags_categories_id` varchar(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`tag_id`),
  KEY `tags_info` (`tags_categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `tags_info`
--

INSERT INTO `tags_info` (`tag_id`, `tag_name`) VALUES
(2, 'C++'),
(4, 'java'),
(5, 'python'),
(6, 'mysql'),
(7, 'http'),
(8, 'linux'),
(11, '无'),
(12, 'CET-6'),
(13, '堕落街'),
(14, 'CET-2');

-- --------------------------------------------------------


--
-- 表的结构 `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `user_mark` int(11) DEFAULT NULL,
  `user_tags` varchar(100) DEFAULT NULL,
  `user_logo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`user_id`, `user_name`, `user_password`, `user_email`, `user_mark`, `user_tags`, `user_logo`) VALUES
(1, 'admin', 'admin', '306672962@qq.com', 0, '1,2', NULL),
(2, 'xuyongli', 'xuyongli', '306672962@qq.com', 1, '1,2,3', NULL),
(3, 'niu', 'niu', '306672962@qq.com', 0, '2.5.8', NULL),
(4, 'xiaofei', 'xiaofei', '707406343@qq.com', 0, '1,2,3', NULL),
(5, 'bubu', 'bubu', 'fsd3f4131@qq.com', 0, NULL, NULL),
(6, 'niuyichao', 'nyc', '306672962@qq.com', 0, NULL, NULL),
(7, '王大锤', 'wangdachui', '963450814@qq.com', 0, NULL, NULL),
(8, 'xutao', 'xutao', '1726851129@qq.com', 0, NULL, NULL),
(9, 'gmf', 'gmf', '987621911@qq.com', 0, NULL, NULL),
(10, 'daxia', 'daxia', '987621911@qq.com', 0, NULL, NULL),
(13, 'zxc', 'zxczxc', '707406343@qq.com', 0, NULL, NULL),
(15, '小飞', 'xiaofei', '987621911@qq.com', 0, NULL, NULL),
(16, '24', '24', '1726851129@qq.com', 0, NULL, NULL),
(17, 'Devilsen', 'dmc375913436', '375913436@qq.com', 0, NULL, NULL),
(18, '许涛', '24', '24@qq.com', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `tags_categories`
--

CREATE TABLE IF NOT EXISTS `tags_categories` (
  `tags_categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `tags_categories_name` varchar(20) NOT NULL,
  `tags_categories_description` varchar(200) NOT NULL,
  PRIMARY KEY (`tags_categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;


--
-- 表的结构 `questions_categories`
--

CREATE TABLE IF NOT EXISTS `question_categories` (
  `question_categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_categories_name` varchar(20) NOT NULL,
  `question_categories_description` varchar(200) NOT NULL,
  PRIMARY KEY (`question_categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

--
-- 限制导出的表
--

--
-- 限制表 `answers_keywords`
--
ALTER TABLE `answers_keywords`
  ADD CONSTRAINT `answer_id_keywords` FOREIGN KEY (`answers_id`) REFERENCES `answer` (`answer_id`);

--
-- 限制表 `questions_keywords`
--
ALTER TABLE `questions_keywords`
  ADD CONSTRAINT `questions_keywords` FOREIGN KEY (`questions_id`) REFERENCES `question` (`question_id`);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
