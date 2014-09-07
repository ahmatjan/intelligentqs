-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014-09-07 15:55:36
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=45 ;

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
(34, '邮箱验证功能可能无法重复验证多次，这是用于smtp邮箱协议和一些邮箱服务提供商所决定的，他们会把短时间重复发送多次的邮箱比较为垃圾邮箱，所以可能无法验证。如遇到哪这种情况，亲们可以发送邮件给：707406343@qq.com，管理员收到邮件后会第一时间处理。', '2014-7-24-  10:47:19', 1, 4, 30, 0),
(35, '对面相关问题是假的，链接功能根本用不了。还有标签添加了为什么没有反应啊，添加后还是没有显示。求解释？？？？？', '2014-7-24-  10:48:58', 0, 9, 30, 0),
(36, '有', '2014-7-30-  9:23:6', 0, 17, 31, 0),
(37, '竟敢如此地侮辱我，你这是作死滴节奏么???', '2014-7-30-  10:31:28', 0, 1, 31, 0),
(38, '我能经得起多少诋毁，就能经得起多少赞美。Lumia ——不跟随！', '2014-7-30-  10:33:18', 0, 4, 31, 0),
(39, '测试', '2014-7-30-  15:17:59', 0, 17, 31, 0),
(40, 'CSS网页中的相对定位与绝对定位在CSS中有这样的一个指令：(position)，在DreamWeaver中文版中翻译为“定位”，常用的属性有两个：relative(相对)与 absolute(绝对)。有很多朋友对这条指令的用法还是不清楚，这里做一些细致的讲解。position:relative; 表示相对定位，被定位了这个属性的标签在所属的范围内可以进行上下左右的移，这里的移动与padding或是margin所产生的位置变化是不一样的。padding与margin是元素本身的一种边距与填充距离并不是真正的移动，而被定义为relative的元素是真正的移动，这所产生的移动距离是从margin的外围到父级标签内侧之间这一段。position:absolute; 表示绝对定位，如果定义了这个属性的元素，其位置将依据浏览器左上角的0点开始计算，并且是浮动正常元素之上的。那么当你需要某个元素定位在浏览器内容区的某个地方就可以用到这个属性。于是产生了一个问题：现在大家做的网页大部分是居中的，如果我需要这个元素跟着网页中的某个元素位置，不论屏幕的分辨率是多少它的位置始终是针对页内的某个元素的，靠单纯的absolute是不行的。正确的解决方法是：在元素的父级元素定义为position:relative;(这里可以是祖父级，也可以是position:absolute;，多谢谢old9的提出)需要绝对定位的元素设为position:absolute;这样再设定top,right,bottom,left的值就可以了，这样其定位的参照标准就是父级的左上角padding的左上侧。', '2014-7-31-  12:12:7', 3, 4, 32, 0),
(41, 'SD ', '2014-8-13-  21:29:20', 1, 1, 32, 0),
(42, '如果是本地没问题，到BAE上就404，基本上就是因为在BAE上部署的时候报错了。你可以看看部署的时候是不是提示：异常，然后再看看userlog，是不是有对应的错误日志。', '2014-8-26-  18:29:14', 2, 1, 33, 0),
(43, '直接将从Myeclips中导出的.war文件上传就行。', '2014-8-26-  18:32:51', 2, 1, 33, 0),
(44, '', '2014-8-31-  11:44:10', 1, 4, 33, 0);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=44 ;

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
(40, 41, 'sd,', '1,'),
(41, 42, '是不是,bae,部署,看看,时候,错误,可以,然后再,提示,基本上,userlog,异常,错了,就是,如果是,因为,日志,对应,404,本地,', '2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(42, 43, '上传,直接,将从,文件,就行,war,导出,myeclips,', '1,1,1,1,1,1,1,1,'),
(43, 44, '', '');

-- --------------------------------------------------------

--
-- 表的结构 `crawl`
--

CREATE TABLE IF NOT EXISTS `crawl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `title` varchar(500) COLLATE utf8_bin NOT NULL,
  `url` varchar(500) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=61 ;

--
-- 转存表中的数据 `crawl`
--

INSERT INTO `crawl` (`id`, `question_id`, `title`, `url`) VALUES
(1, 31, '请问乡情爱情还有没有第6部_360问答', 'http://wenda.so.com/q/1404453086726473'),
(2, 31, 'QQ飞车里的剧情(暴风第6人)怎么没有了!还有请问怎样可以刷级...', 'http://wenda.so.com/q/1371017337066865'),
(3, 31, '新银~请问~还有第六季吗_虚幻勇士吧_百度贴吧', 'http://tieba.baidu.com/p/2819270102'),
(4, 31, '药物流产第一天流出胎儿之后就没再流血第六天B超显示宫内有...', 'http://wenda.so.com/q/1366221882069364'),
(5, 31, '生化危机5还没有结束生化。请问还有第6部? - 已回答 - 搜搜问...', 'http://wenwen.sogou.com/z/q406484337.htm'),
(6, 31, '请问月经第六天了怎么还有血_好大夫在线', 'http://www.haodf.com/wenda/licunli_g_755197744.htm'),
(7, 31, '请问我人流第6天,可是还有血流出,量不是很大,一直在吃新生化...', 'http://club.xywy.com/static/2454/1226875.htm'),
(8, 31, '你好:请问第六城摩卡商业街小平米商铺还有吗?是多在平米的?...', 'http://www.jiwu.com/wenda/3920403.html'),
(9, 31, '我的月经推迟了7天了,昨天第六天晚上的时候我测试了一下,没...', 'http://wenda.so.com/q/1367946721067379'),
(10, 31, '《嘻哈四重奏-第五季》结束,请问何时出第六季?_卢正雨吧_百...', 'http://tieba.baidu.com/p/2749074239'),
(11, 33, '关于java程序在BAE3.0上部署关于java程序在BAE3.0上部署80...', 'http://www.uukkuu.com/v617092905534185612/'),
(12, 33, '【BAE3.0使用系列】Java Runtime简介_bae吧_百度贴吧', 'http://tieba.baidu.com/p/2574882923'),
(13, 33, '如何在BAE3.0中部署wordpress_互联网_百度经验', 'http://jingyan.baidu.com/album/fb48e8be5bac1d6e622e14c1.html'),
(14, 33, '百度BAE2.0 JAVA环境项目部署和调试- using - 开源中国社区', 'http://my.oschina.net/using/blog/167038'),
(15, 33, '【每日一博】百度BAE JAVA环境项目部署和调试- 看引擎KEN...', 'http://www.kankanews.com/ICkengine/archives/56648.shtml'),
(16, 33, 'servlet相关jar包位置BAE上部署web应用- Yogurshine - 博客园', 'http://www.cnblogs.com/Yogurshine/p/3171142.html'),
(17, 33, '【BAE3.0使用系列】Java博客--Dlog4j的移植| BAE技术博客', 'http://godbae.duapp.com/?p=249'),
(18, 33, '(转)微信公众平台开发之基于百度BAE3.0 的开发环境搭建(采用...', 'http://www.cnblogs.com/ixxonline/p/3659434.html'),
(19, 33, '百度BAE JAVA环境项目部署和调试-Java-第七城市', 'http://www.th7.cn/Program/java/201310/153446.shtml'),
(20, 33, '百度开放云平台', 'http://developer.baidu.com/events/bae3'),
(21, 33, '关于java程序在BAE3.0上部署关于java程序在BAE3.0上部署80...', 'http://www.uukkuu.com/v617092905534185612/'),
(22, 33, '【BAE3.0使用系列】Java Runtime简介_bae吧_百度贴吧', 'http://tieba.baidu.com/p/2574882923'),
(23, 33, '如何在BAE3.0中部署wordpress_互联网_百度经验', 'http://jingyan.baidu.com/album/fb48e8be5bac1d6e622e14c1.html'),
(24, 33, '百度BAE2.0 JAVA环境项目部署和调试- using - 开源中国社区', 'http://my.oschina.net/using/blog/167038'),
(25, 33, '【每日一博】百度BAE JAVA环境项目部署和调试- 看引擎KEN...', 'http://www.kankanews.com/ICkengine/archives/56648.shtml'),
(26, 33, 'servlet相关jar包位置BAE上部署web应用- Yogurshine - 博客园', 'http://www.cnblogs.com/Yogurshine/p/3171142.html'),
(27, 33, '【BAE3.0使用系列】Java博客--Dlog4j的移植| BAE技术博客', 'http://godbae.duapp.com/?p=249'),
(28, 33, '(转)微信公众平台开发之基于百度BAE3.0 的开发环境搭建(采用...', 'http://www.cnblogs.com/ixxonline/p/3659434.html'),
(29, 33, '百度BAE JAVA环境项目部署和调试-Java-第七城市', 'http://www.th7.cn/Program/java/201310/153446.shtml'),
(30, 33, 'Bae3.0计费说明| 岭南六少- 一朵在LAMP架构下挣扎的云', 'http://blog.chedushi.com/archives/7297'),
(31, 27, '湖南科技大学什么专业最好?_360问答', 'http://wenda.so.com/q/1365838327064957'),
(32, 27, '湖南科技大学优势专业排名及最好的专业有哪些_大学专业', 'http://www.ccutu.com/gaokao/17290.shtml'),
(33, 27, '湖南科技大学最好的专业 - 特色专业 - 萝卜侠网 - 高考,志愿,填...', 'http://www.luoboxia.com/best-majors/10534.html'),
(34, 27, '我的大学-湖南科技大学最好的本科专业是哪个啊', 'http://bbs.rednet.cn/thread-25074484-1-1.html'),
(35, 27, '湖南科技大学，的重点专业是哪些?', 'http://wenwen.sogou.com/z/q210664149.htm'),
(36, 27, '湖南科技大学什么专业好?_湖南科技大学吧_百度贴吧', 'http://tieba.baidu.com/p/125493668'),
(37, 27, '湖南科技大学什么专业最好_2014湖南科技大学的专业怎么样_...', 'http://gaokao.0s.net.cn/tesezhuanye/hnkjdx/'),
(38, 27, '湖南科技大学里面什么专业比较好_百度知道', 'http://zhidao.baidu.com/question/103809922.html'),
(39, 27, '湖南科技大学哪个专业好?_百度知道', 'http://zhidao.baidu.com/question/97252518.html'),
(40, 27, '湖南科技大学有什么专业比较好~~_天涯问答', 'http://wenda.tianya.cn/question/6b05425863e2a44c'),
(41, 24, '一句话说不清楚,形容事情很复杂 是什么成语', 'http://iask.sina.com.cn/b/f1rGsxouR.html'),
(42, 24, '求求大家帮帮忙谢谢了:事情是我与邻居之间的矛盾,听起来感觉...', 'http://wenda.so.com/q/1377339787064329'),
(43, 24, '怎么在中国知网下东西?_firefox吧_百度贴吧', 'http://tieba.baidu.com/p/2083814776'),
(44, 24, 'V 357 缘嫁首长老公- 请看小说网', 'http://www.qingkan.net/book/yuanjiashouchanglaogong/4694837.html'),
(45, 24, '如何看待中国学术造假? - 步行街- 虎扑篮球论坛', 'http://bbs.hupu.com/8073776.html'),
(46, 24, '艺术,一句话说不清楚(《国家美术》杂志,王天德创作访谈) - 《国...', 'http://blog.artintern.net/article/58072'),
(47, 24, '《网游之我是NPC》第十三章豆豆删号了- 原创首发- 《小说阅...', 'http://www.readnovel.com/novel/12230/13.html'),
(48, 24, '好想给前男友打电话,可是我不知道第一句话说什么', 'http://wenwen.sogou.com/z/q421755508.htm'),
(49, 24, '有没有萍乡的朋友?急事相求_李宇春吧_百度贴吧', 'http://tieba.baidu.com/p/488896278'),
(50, 24, '一句话两句话说不清楚的问题看看吧100分! - 已解决- 搜狗问问', 'http://wenwen.sogou.com/z/q209292169.htm'),
(51, 36, 'TAG: 检测- 分析测试百科网', 'http://www.antpedia.com/?action-tag-tagname-%E6%A3%80%E6%B5%8B'),
(52, 36, 'Vlan 测试实战(tag untag pvid vid )_百度文库', 'http://wenku.baidu.com/view/da3be3c608a1284ac85043e8.html'),
(53, 36, '【安卓龙珠TAG VS游戏测试】完美能玩了我是有好激动!BGM...', 'http://tieba.baidu.com/p/2368565279'),
(54, 36, 'VLAN 测试实战(TAG UNTAG PVID VID ) - 豆丁网', 'http://www.docin.com/p-392969719.html'),
(55, 36, '趣味测试TAG标签_Txt电子书下载_一博书库', 'http://www.ebookcn.com/tag/%E8%B6%A3%E5%91%B3%E6%B5%8B%E8%AF%95'),
(56, 36, '[个人测试] 龙珠TAG VS。_ppsspp吧_百度贴吧', 'http://tieba.baidu.com/p/2374227481'),
(57, 36, '《剑网三》团队事件监控官方测试TAG--可随意修改资料库', 'http://tag.j3ui.com/10/Buff'),
(58, 36, 'VLAN 测试实战(TAG UNTAG PVID VID ) - 技术总结- 道客巴巴', 'http://www.doc88.com/p-436727263401.html'),
(59, 36, '测试tag 临时用- 都市小说- 红袖添香', 'http://novel.hongxiu.com/a/69760/'),
(60, 36, 'Vlan 测试实战(tag untag pvid vid ) - 豆丁网', 'http://www.docin.com/p-537535966.html');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

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
(17, '测试你妹啊，不许灌水。', '2014-07-30 07:00:20', 4, 39),
(18, '我就是用的这个。', '2014-08-26 06:35:21', 4, 43),
(19, '查看日志就行。', '2014-08-31 10:38:26', 4, 42),
(20, '这个问题是你自己写的么？', '2014-09-06 11:04:16', 1, 40);

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `message` varchar(500) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=65 ;

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`id`, `user_id`, `message`) VALUES
(1, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(2, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(3, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(4, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(5, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(6, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(7, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(8, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(9, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(10, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(11, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>回答了'),
(12, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(13, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(14, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(15, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(16, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(17, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(18, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(19, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(20, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(21, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(22, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(23, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(24, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(25, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(26, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(27, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(28, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(29, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(30, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(31, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(32, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(33, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(34, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(35, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(36, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(37, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(38, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(39, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(40, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(41, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>收藏了'),
(42, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>赞了'),
(43, 1, '<a href=''getDetilQuestion?question_id=33''>你的回答被采纳了</a>'),
(44, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(45, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(46, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(47, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(48, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>回答了'),
(49, 1, '你的问题<a href=''getDetilQuestion?question_id=33''>关于java程序在BAE3.0上部署</a>被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>回答了'),
(50, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(51, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(52, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(53, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(54, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(55, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(56, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(57, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(58, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(59, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(60, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(61, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(62, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(63, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了'),
(64, 1, '你被<a href=''GetPersionInfoServlet?user_id=1''>admin</a>关注了');

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
  `question_categories_id` int(11) DEFAULT '0',
  PRIMARY KEY (`question_id`),
  KEY `question` (`question_categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=37 ;

--
-- 转存表中的数据 `question`
--

INSERT INTO `question` (`question_id`, `question_title`, `question_description`, `question_time`, `question_mark`, `question_user_id`, `question_tags`, `question_categories_id`) VALUES
(1, '这是做什么的？', '这个系统是做什么的？', '2014-05-13', -1, 2, '2', 0),
(2, 'python', '是中编程语言么？', '2014-05-13', 0, 3, '2,5,8,46', 0),
(3, '今天星期几？', '时间', '2014-05-21 08:03:12', 1, 0, '生活', 0),
(4, '你是不是二货？', '迷惑', '2014-05-21 08:05:19', -1, 0, '娱乐', 0),
(5, 'SQL语句区分大小写么？', '数据库查询语句区分大小写么？', '2014-05-21 08:06:33', 0, 0, '55', 0),
(7, '大一军训多久？', '湖南科技大学大一新生军训多长时间，严不严？可以不军训么....', '2014-05-21 10:21:43', 0, 0, '6', 0),
(8, '大一可以谈恋爱么？', '大一新生谈恋爱的多不多？科大美女多不多，是南校女生多，还是北校多呀，那个校区女生整体质量比较好？', '2014-05-21 10:28:12', 0, 0, '其它', 0),
(9, '美食', '湖南科技大学那个食堂的饭比较好吃，那个食堂的最不好吃？', '2014-05-21 11:59:08', 0, 0, '66', 0),
(10, '专业课程', '有谁知道大一计算机系有哪些课程?跪求解答！！！', '2014-05-22 12:00:48', 0, 0, '22', 0),
(11, '系统问题', '为什么我发的答案显示不了？求许永利解释@许永利', '2014-05-22 12:12:17', 0, 0, '许永利', 0),
(12, '熬夜', '现在都00点13分了，有木有还没有睡的？', '2014-05-22 12:14:16', 0, 0, '困', 0),
(13, '问答有问题', '为什么每次发表问题后返回的页面中都没有我刚刚题的问题，求王大神解释！！！', '2014-05-22 12:15:44', 0, 0, '问答系统', 0),
(14, 'html按钮button怎么加超链接', '<input name="注册"  type="button"  id="btn1" title="登注册" value="注册" />怎么让这个点下，转向一个链接？ 晕。。 我不是要提交数据，只是想用按钮当个超链接啊！ ', '2014-05-25 11:07:49', 0, 0, '56', 0),
(15, '返回值:jQueryjQuery', 'jQuery 的核心功能都是通过这个函数实现的。 jQuery中的一切都基于这个函数，或者说都是在以某种方式使用这个函数。这个函数最基本的用法就是向它传递一个表达式（通常由 CSS 选择器组成），然后根据这个表达式来查找所有匹配的元素。\r\n\r\n默认情况下, 如果没有指定context参数，$()将在当前的 HTML document中查找 DOM 元素；如果指定了 context 参数，如一个 DOM 元素集或 jQuery 对象，那就会在这个 context 中查找。在jQuery 1.3.2以后，其返回的元素顺序等同于在context中出现的先后顺序。\r\n', '2014-05-25 11:30:04', 0, 0, '56', 0),
(16, '13124', '32423', '2014-06-15 01:37:41', 0, 0, '1', 0),
(19, '求GTASA里面的DATA文件 不要装MOD的', '还有我添加了个双枪WEAPON.DOT文件  开枪为什么会弹出', '2014-06-28 02:47:59', 0, 0, '', 0),
(20, 'input元素hidden属性和text属性的取值问题', '两个input元素，一个hidden属性，一个text属性，名字都叫name的话，提交到表单的值是hidden的还是text的\r\n是jsp的，两个同名input，属性均为text，但是设置一个display：“”\r\n一个display：none；显示出来的input值是有的，不显示的input的值是空，交到后台，得不到值，加上一个同名hidden就能取到了，就想知道同名元素怎么取值的', '2014-06-28 03:12:11', 0, 0, '', 0),
(21, 'input元素hidden属性和text属性的取值问题', '两个input元素，一个hidden属性，一个text属性，名字都叫name的话，提交到表单的值是hidden的还是text的\r\n是jsp的，两个同名input，属性均为text，但是设置一个display：“”\r\n一个display：none；显示出来的input值是有的，不显示的input的值是空，交到后台，得不到值，加上一个同名hidden就能取到了，就想知道同名元素怎么取值的', '2014-06-28 03:14:35', 0, 0, '', 0),
(22, '''几天周几？', '今天时间...', '2014-06-28 08:21:02', 0, 0, '', 0),
(23, '我们认识么？', '我想知道我们究竟认识么、？', '2014-04-13', 0, 1, '情感', 0),
(24, '这事儿一句话说不清楚', '这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚这事儿一句话说不清楚', '2014-07-13 03:07:04', 0, 5, '', 0),
(26, '湖南科技大学怎么样', '湖南科技大学怎么样', '2014-07-13 10:49:58', 2, 1, '', 0),
(27, '湖南科技大学什么专业最好？', '想报考那个学校。', '2014-07-13 10:51:55', 3, 1, '', 0),
(28, '湖南科技大学图书馆在哪？', '位置在哪儿', '2014-07-13 10:54:46', 2, 1, '', 0),
(29, '计算机专业下个学期的课程设计是做什么，做多久啊？', '如题，请大声告诉我一下，感激不尽！！！', '2014-07-14 02:58:28', -1, 7, '课程设计', 0),
(30, '智能问答平台功能更新', '湖南科技大学智能问答平台功能更新说明：\r\n1、系统安全方面注册和登录增加验证码功能；\r\n2、用户的注册成功邮箱提醒和密码邮箱验证密码找回。', '2014-07-24 10:43:52', 2, 1, '', 0),
(31, '请问还有没有第六个人在！！', '哈哈哈哈哈哈哈哈哈哈，我是来打酱油的！！！', '2014-07-28 08:51:48', 1, 18, '酱油', 0),
(32, 'CSS绝对定位与相对定位的区别？', 'CSS绝对定位与相对定位的区别？', '2014-07-31 12:11:34', 1, 4, '网页设计', 0),
(33, '关于java程序在BAE3.0上部署', '是这样，原来用BAE2.0的时候，创建完程序，直接将从Myeclips中导出的.war文件上传就行，但是现在改成BAE3.0之后，就放在SVN里面发布。我将一个能够正常运行的java程序的.war文件发布之后，点击查看页面，出来404错误。。这是为什么。', '2014-08-26 06:20:00', 4, 1, '39', 0),
(34, 's', 's', '2014-09-07 09:05:53', 0, 1, 'b', 0),
(35, '', '', '2014-09-07 09:16:15', 0, 1, '', 0),
(36, '测试tag', 'python javascript', '2014-09-07 11:36:22', 0, 1, '46,47', 0);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

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
(30, 32, '定位,区别,css,', '4,2,2,'),
(31, 33, '程序,war,文件,之后,java,bae3.0,发布,bae2.0,点击,放在,改成,这是,就行,部署,能够,这样,正常,错误,上传,为什么,', '3,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,'),
(32, 34, 'ss,', '1,'),
(33, 35, '', ''),
(34, 36, 'python,测试,tag,javascript,', '1,1,1,1,');

-- --------------------------------------------------------

--
-- 表的结构 `question_categories`
--

CREATE TABLE IF NOT EXISTS `question_categories` (
  `question_categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_categories_name` varchar(20) DEFAULT NULL,
  `question_categories_description` varchar(200) NOT NULL,
  `last_categories_id` int(11) NOT NULL,
  PRIMARY KEY (`question_categories_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `tags_categories`
--

CREATE TABLE IF NOT EXISTS `tags_categories` (
  `tags_categories_id` int(11) NOT NULL,
  `tags_categories_name` varchar(20) NOT NULL,
  `tags_categories_description` varchar(200) NOT NULL,
  `last_categories_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`tags_categories_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tags_categories`
--

INSERT INTO `tags_categories` (`tags_categories_id`, `tags_categories_name`, `tags_categories_description`, `last_categories_id`) VALUES
(1, '我是新生', '作为一名新生对于校园的种种疑惑，在这里为你解答。', 0),
(2, '考证专区', '大学考证五花八门，你有不懂我来回答。', 0),
(3, '专业知识', '专业方面的疑惑或许这里有你需要的答案哦！', 0),
(4, '工作离校', '大四离校、三方协议、工作实习、这里有你想要。', 0),
(5, 'IT领域', '为计算机专业特色定制的编程语言版块。', 0),
(6, '科大周边', '科大周边，吃喝玩乐统统搞定。', 0),
(7, '学校后勤', ' 学校后勤方面的疑问，也可以在这里得到解决哦。', 0),
(8, '学长学姐', '学霸经验，学渣奋斗史，看屌丝如何逆袭高富帅。', 0),
(9, '校园活动', '社团竞选，学生会，晚会，比赛应有尽有。', 0),
(10, '考研攻略', '选你所爱，爱你所选。考研辅导，给你想要的。', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tags_info`
--

CREATE TABLE IF NOT EXISTS `tags_info` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) NOT NULL,
  `tags_categories_id` varchar(11) DEFAULT '0',
  `tags_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  KEY `tags_info` (`tags_categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=97 ;

--
-- 转存表中的数据 `tags_info`
--

INSERT INTO `tags_info` (`tag_id`, `tag_name`, `tags_categories_id`, `tags_description`) VALUES
(1, '迎新', '1', NULL),
(2, '报到', '1', NULL),
(3, '住宿', '1', NULL),
(4, '入学教育', '1', NULL),
(5, '社团竞选', '1', NULL),
(6, '军训', '1', NULL),
(7, '学生会', '1', NULL),
(8, '校历', '1', NULL),
(9, '新生规划', '1', NULL),
(10, '英语考证', '2', NULL),
(11, '会计资格证', '2', NULL),
(12, '计算机证书', '2', NULL),
(13, '教师资格证', '2', NULL),
(14, '导游资格证', '2', NULL),
(15, '普通话考试', '2', NULL),
(16, '经济学类', '3', NULL),
(17, '管理学类', '3', NULL),
(18, '土建类', '3', NULL),
(19, '文学类', '3', NULL),
(20, '农学类', '3', NULL),
(21, '教育学类', '3', NULL),
(22, '计算机类', '3', NULL),
(23, '能源与材料类', '3', NULL),
(24, '历史学类', '3', NULL),
(25, '法学类', '3', NULL),
(26, '电气电子类', '3', NULL),
(27, '生化与药品类', '3', NULL),
(28, '传媒与艺术类', '3', NULL),
(29, '实习', '4', NULL),
(30, '工作', '4', NULL),
(31, '就业信息', '4', NULL),
(32, '三方协议', '4', NULL),
(33, '数字离校', '4', NULL),
(34, '劳动合同', '4', NULL),
(35, '薪资待遇', '4', NULL),
(36, '毕业相关', '4', NULL),
(37, '校友', '4', NULL),
(38, '培训', '4', NULL),
(39, 'java', '5', NULL),
(40, 'php', '5', NULL),
(41, 'c', '5', NULL),
(42, 'c++', '5', NULL),
(43, 'c#', '5', NULL),
(44, '.net', '5', NULL),
(45, 'asp', '5', NULL),
(46, 'python', '5', NULL),
(47, 'javascript', '5', NULL),
(48, 'objective-c', '5', NULL),
(49, 'node.js', '5', NULL),
(50, '软件破解', '5', NULL),
(51, 'mobil OS', '5', NULL),
(52, 'PC OS', '5', NULL),
(53, '手机APP', '5', NULL),
(55, 'DataBase', '5', NULL),
(56, '其它语言', '5', NULL),
(57, 'IDE', '5', NULL),
(58, 'PS', '5', NULL),
(59, '游戏影音', '5', NULL),
(60, '游戏办公', '5', NULL),
(64, '其它', '5', NULL),
(65, '网页前端', '5', NULL),
(66, '美食', '6', NULL),
(67, '娱乐', '6', NULL),
(68, '景点', '6', NULL),
(69, '活动', '6', NULL),
(70, '打折优惠', '6', NULL),
(71, '爱心公益', '6', NULL),
(72, '公交路线', '6', NULL),
(73, '基础设施', '7', NULL),
(74, '保修', '7', NULL),
(75, '建议', '7', NULL),
(76, '咨询', '7', NULL),
(77, '基础设施', '7', NULL),
(78, '保修', '7', NULL),
(79, '建议', '7', NULL),
(80, '咨询', '7', NULL),
(81, '其它', '7', NULL),
(82, '二手市场', '8', NULL),
(83, '考试攻略', '8', NULL),
(84, '选修推荐', '8', NULL),
(85, '经验分享', '8', NULL),
(86, '其它相关', '8', NULL),
(87, '社团活动', '9', NULL),
(88, '素质拓展', '9', NULL),
(89, '俱乐部', '9', NULL),
(90, '文艺晚会', '9', NULL),
(91, '热门推荐', '9', NULL),
(92, '前期准备', '10', NULL),
(93, '考研必备', '10', NULL),
(94, '培训推荐', '10', NULL),
(95, '相关资料', '10', NULL),
(96, '录取信息', '1', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

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
