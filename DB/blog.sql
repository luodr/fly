/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 02/01/2020 23:19:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `views` int(11) NULL DEFAULT 0,
  `goods` int(11) NULL DEFAULT 0,
  `rubbis` int(11) NULL DEFAULT 0,
  `creationDate` datetime(0) NOT NULL,
  `Introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coverimage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `report` int(255) NULL DEFAULT 0,
  `reply` int(255) NULL DEFAULT 0,
  `type2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `L_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `audit` int(255) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE COMMENT '用户表示',
  INDEX `type`(`type`) USING BTREE COMMENT '类型标识',
  INDEX `name`(`name`) USING BTREE COMMENT '用户名称',
  INDEX `audit`(`audit`) USING BTREE COMMENT '审核',
  CONSTRAINT `name` FOREIGN KEY (`name`) REFERENCES `user` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 375 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (330, 'egg 插件如何进行单元测试', '自己写了一个 egg 的插件，在插件代码 agent.js中 beforeStart 生命周期内添加了插件启动前的一些必要操作，在脚手架生成的单元测试代码中并没有执行，查看插件官方文档也没有对这个情况的单元测试进行说明，请问有了解的大佬吗？\n测试代码如下\n[pre]\n\'use strict\';\n\nconst mock = require(\'egg-mock\');\nconst assert = require(\'assert\');\n\ndescribe(\'test/apollo-ddz.test.js\', () => {\n  let app;\n  before(() => {\n    app = mock.app({\n      baseDir: \'apps/apollo-ddz-test\',\n    });\n    return app.ready();\n  });\n\n  after(() => app.close());\n  afterEach(mock.restore);\n\n  it(\'should GET /\', async () => {\n    await app.initApollo();\n    assert(app.config.nodeConfig, \' apollo 配置获取失败\');\n  });\n});\n[/pre]', 5, 0, 0, '2019-12-25 12:21:27', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (331, '大家都分享下自己熬夜后快速恢复的心得', '最近这几天赶项目疯狂通宵 心脏无法控制得跳得贼块  人也变得负能量又爆燥\n然后今天喝了点红酒配牛肉\n身体突然就好了很多 这是能感受到的 感觉和吃其他食物有所区别\n\n以后每天来一点 好保住自己的狗命', 0, 0, 0, '2019-12-25 12:30:49', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (332, 'node express文件数据转发', '两台node后端，一台在本地，另一台在服务器，现在要本地后台把文件发送给服务器上的后台，有没有什么办法？\n之前有试过把本地文件流凑成FormData, 发送给服务器后台，用formidable解析，但是node不支持File, 服务器上的formidable不能识别文件。', 0, 0, 0, '2019-12-25 12:31:29', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (333, '关于快速排序算法的一个问题', '[pre]\n//快速排序优化=>基于冒泡+二分查找\nconst quickSort = (arr) => {\n    if (arr.length <= 1) {\n        return arr;\n    }\n    let mid = Math.floor(arr.length / 2);\n    let midNum = arr.splice(mid, 1)[0];\n    // let midNum=arr[mid];//这里为什么要用splice?如果用下面这种,会循环递归,死循环报错,用上面的方式就会执行结束\n    console.log(mid);\n    let left = [];\n    let right = [];\n    for (let i = 0; i < arr.length; i++) {\n        if (arr[i] < midNum) {\n            left.push(arr[i]);\n        } else {\n            right.push(arr[i]);\n        }\n    }\n    // console.log(left,right)\n    return quickSort(left).concat(midNum, quickSort(right))\n\n};\nlet arr = [5, 7, 2, 9, 3, 8, 4, 17, 1, 11];\n\nconsole.log(quickSort(arr));\n[/pre]\n代码在上面,如果使用注释的那种方式获取中间值,则会死循环,值一直是1,但是用splice就会自动终止.\n这是为什么?', 0, 0, 0, '2019-12-25 12:33:48', NULL, NULL, 'admin', 'JavaScript', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (334, 'sequlize如何新增多个create()', '新增一个成功：\n[pre]\n     await this.ctx.model.RolePermission.create({\n                role_id: 2,\n                permission_id: 2\n			)}\n[/pre]\n新增多个失败:\n[pre]\n   let id = body.roleId;\n            let permissionId = body.access_node;\n            console.log(\'wwwwww\')\n            console.log(id) //1\n            console.log(permissionId) //[2,3,4]\n\n            // 新增多个，失败\n             permissionId.forEach(item => {\n                 await this.ctx.model.RolePermission.create({\n                     role_id: id,\n                     permission_id: parseInt(item)\n                 })\n             })\n[/pre]', 0, 0, 0, '2019-12-25 12:37:39', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (335, 'k8s如何实现node.js服务使用pubsubN个实例只通知单个任一实例。', '现在服务要上k8s，使用了pubsub，多个实例都会订阅redis事件，如何只让任一个单一实例收到通知，而非所有实例。\n还是会有更好的方式处理这种多实例的问题？', 0, 0, 0, '2019-12-25 12:37:59', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (336, 'node.js cluster模块', '可以把node.js得内置模块得源码下载到本地后运行吗', 1, 0, 0, '2019-12-25 12:38:19', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (337, 'node中import老大难问题', '这都node11，12版本了，找了一圈还是没有简单直接的用import方法，什么时候能原生支持啊', 2, 0, 0, '2019-12-25 12:38:47', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (338, '游戏后端用Node的多吗？', '前两年在边锋开发“侠客风云传”和棋牌游戏的时候用过，但是很少听说其它公司或项目用。感觉找工作和招人都不容易。用Node最多的还是前端开发。最近应聘的两家都是想做小游戏的，要求挺高但是待遇或地位不怎么样。\n\n', 33, 0, 0, '2019-12-25 12:39:23', NULL, NULL, 'admin', 'Node.js', 0, 1, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (339, '关于 Nodejs 服务器高并发的疑问', '从学习 Nodejs 以来，听各路大神说 nodejs 的一大优势就是处理高并发。我是非科班出身，对于高并发的理解就是能够同时处理大量请求，第一印象就是每个请求单独起个线程处理，请求与请求之间互不干扰，然鹅…\n\n用 http 原生代码起一个服务器，接收两个请求：\n\n/a：该接口内部写了个斐波拉契递归函数，执行时间大约 10s 左右\n/b\n实际起服务后，先请求 /a 接口，再请求 /b 接口。由于 /a 接口被斐波拉契函数阻塞了 10s 左右，惊讶的发现 /b 接口竟然一直要等到 /a 接口跑完才能响应。这说明 http 处理接口是一个处理完再处理下一个的，那么问题来了，10个用户同时请求同一个查询接口，假设每次查询时间为 1s，那岂不是运气差的人拿到响应肯定是在 10 秒之后。\n\n所谓高并发又到底怎么理解呢？\n\n\n[pre]\nvar http = require(\'http\')\n\nvar app = http.createServer(function (req, res) {\n    if (req.url === \'/a\') {\n        // 斐波拉契函数\n        function fib(n) {\n            if (n === 0) return 0;\n            else if (n === 1) return 1;\n            else return fib(n - 1) + fib(n - 2)\n        }\n        fib(44) // 执行时间要 10s 左右\n        res.end(\'a is \' + new Date())\n    } else if (req.url === \'/b\') {\n        res.end(\'b is \' + new Date())\n    }\n})\n\napp.listen(3600, function () {\n    console.log(\'服务已启动\')\n})\n[/pre]', 0, 0, 0, '2019-12-25 12:40:13', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (340, 'session在node中是如何存储的 ？', '问题：session在node中是如何存储的 ？\n最近了解 session cookie的相关概念，然后使用了egg-passport实践了一下\n发现对session的存储有一些疑惑\n通过阅读passport的源码，看到最后是执行到ctx.req.session = xxxx； 这样就保存了session到服务器中吗？\n在脑海里没有很清晰的思路，究竟session是存在哪里，怎么存的\n通过编辑器的提醒 Context.req: IncomingMessage，又找到了node http模块中IncomingMessage相关内容 并没有发现与session 有什么关联\n感觉ctx.req.session = xxxx;是个黑盒子，里面完成了什么内容完全不清楚？\n请问有大佬说说node中 session的理解吗？或者有什么思路\ngoogle了很多 都是如何使用框架进行可持续化的session存储，但是对原理好像没有什么讲述到\n尝试了 ctx.req.abc = 123 进行赋值\n反序列化的时候取ctx.req.abc 是undefined的，并没有存下来，那么就是对session这个字段有特别处理？', 2, 0, 0, '2019-12-25 12:41:06', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (341, '使用express，模拟数据文件能热加载吗', '在vue项目中装了webpack-dev-server。webpack.dev.conf.js中 before里对一些请求返回json数据，数据来自mock文件，例如：\nbefore(mockRoute) {\nmockRoute.get(’/foo/bar’, (req,resp) => {\nvar content = require(’…/mock/foo/bar.json’)\n	resp.json(content)\n});\n}\n工作正常，但修改了bar.json中的内容后，每次都要重启node才能获取新数据，有没有什么配置项/途径可以让我不重启node修改bar.json后能拿到新数据？', 0, 0, 0, '2019-12-25 12:41:58', NULL, NULL, 'lp', 'Node.js', 0, 0, NULL, NULL, 'lp', 1);
INSERT INTO `article` VALUES (342, 'node起的进程怎么才能使用科学上网工具呀？', '我用的是lantern,开了全局代理。\n把连接放在浏览器可以访问，但是用node进程去访问就不可以，怎么才能让我起的node进程也科学上网呀', 0, 0, 0, '2019-12-25 12:42:17', NULL, NULL, 'lp', 'Node.js', 0, 0, NULL, NULL, 'lp', 1);
INSERT INTO `article` VALUES (343, '通过Js获取字符串的拼音首字母', '获取拼音首拼是非常常见的需求，举个例子: 微信的通讯录，手机的通讯录，各种联系人列表。\n\n要做到这一点很容易，比如说引用一个拼音库来读出拼音的首拼。但是往往这些拼音库都是非常完整的体积非常大的库。\n\n如果你只是想要完成以上的一个小功能。pyfl将会非常适合你。pyfl的全称是pinyin first letters拼音全拼的意思。\n\npyfl是基于pinyinjs写的一个专门获取汉字首拼的库。已经经过作者的同意才开源使用的。\n  pyfl 的用法非常简单如下, 在支持import语法的前端项目中:\n[pre]\nimport pyfl from \'pyfl\';\npyfl(\'喵\'); // M\npyfl(\'好笑吗跟傻子一样整天就知道哈哈哈哈哈哈哈\')); // HXMGSZYYZTJZDHHHHHHH\npyfl(\'罤夶繙着洗\'); // TBFZX\npyfl(\'Pure\'); // Pure\npyfl(\'Made by ❤\'); // Made by ❤\npyfl(\'أشتون\'); // أشتون\n[/pre]\n\n如果你想直接在 node 中里面使用的话:\n[pre]\nconst pyfl = require(\'pyfl\').default;\npyfl(\'喵\'); // M\npyfl(\'好笑吗跟傻子一样整天就知道哈哈哈哈哈哈哈\')); // HXMGSZYYZTJZDHHHHHHH\npyfl(\'罤夶繙着洗\'); // TBFZX\npyfl(\'Pure\'); // Pure\npyfl(\'Made by ❤\'); // Made by ❤\npyfl(\'أشتون\'); // أشتون\n[/pre]\n\npyfl会把支持的汉字转换成大写的拼音输出出来。如果是英文或者奇怪的文字的话会原样输出出来。\n\n如果这个库对你有帮助的话，希望你能在 github 上给我一个 star ~', 0, 0, 0, '2019-12-25 12:44:39', NULL, NULL, 'lp', 'JavaScript', 0, 0, NULL, NULL, 'lp', 1);
INSERT INTO `article` VALUES (344, 'nodeJS使用redis存储token时，如何在用户重新登录时清除之前的token？', '我想用 { token: userId } 的形式将登录后产生的token保存在redis中，token有效期时半小时，存入redis时，设置过期时间为一小时，这样做是为了当用户处于活跃状态时，到过期时间，有半小时刷新token重新发送请求，如果同一个账号在其他设备登录，无法做到清除之前的token。这个要怎么做', 0, 0, 0, '2019-12-25 12:45:43', NULL, NULL, 'lp', 'Node.js', 0, 0, NULL, NULL, 'lp', 1);
INSERT INTO `article` VALUES (345, '使用 JS 开发的 H5 小游戏，但是分数被刷爆了', '这是一个跑分类游戏，游戏结束后更新用户分数，列出排名。 js文件使用uglifier，压缩成一个文件，分数通过js MD5处理，在游戏结束后上传到到服务器。 但是这两天，游戏分数被刷爆， 主要原因应该是JS源码暴露，请问大家是如何处理的？', 5, 0, 0, '2019-12-25 12:48:27', NULL, NULL, '456', 'JavaScript', 0, 1, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (346, '如何把 js 变量加到加到 render 的 hash 中？ ', 'js生成了一个数组，我现在想把这个数组传到erb中。。要怎么做呢？ 我的最终目的是根据这个数组生成一个表单\n[pre]\naArray = [1,2,3,4]\n$(\"#player_role\").append(\"<%= escape_javascript( render \'games/player_role\' ,:player => aArray) %>\");\n[/pre]\n这样是错误的。。但是\n[pre]\naArray = [1,2,3,4]\n$(\"#player_role\").append(\"<%= escape_javascript( render \'games/player_role\' ,:player => [1,2,3,4]) %>\");\n\n[/pre]\n这样能传递过去。。说明可以传递数组。', 0, 0, 0, '2019-12-25 12:49:21', NULL, NULL, '456', 'JavaScript', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (347, 'JS 简单方法取中文单词的简拼', '中文名排序，利用中文名简拼筛选名字或其它中文单词\n\n[pre]\nvar names = [\"王思聪\", \"张柏芝\", \"郭德纲\", \"林志颖\", \"潘长江\", \"马云\", \"冯小刚\"];\n\nfunction getShortPinyin(word) {\n  var idx = -1;\n  var MAP = \'ABCDEFGHJKLMNOPQRSTWXYZ\';\n  var boundaryChar = \'驁簿錯鵽樲鰒餜靃攟鬠纙鞪黁漚曝裠鶸蜶籜鶩鑂韻糳\';\n\n  if (!String.prototype.localeCompare) {\n    throw Error(\'String.prototype.localeCompare not supported.\');\n  }\n\n  return _(word.split(\'\')).map(function(c) {\n    if (/[^\\u4e00-\\u9fa5]/.test(c)) {\n      return c;\n    }\n    for (var i = 0; i < boundaryChar.length; i++) {\n      if (boundaryChar[i].localeCompare(c, \'zh-CN-u-co-pinyin\') >= 0) {\n        idx = i;\n        break;\n      }\n    }\n    return MAP[idx];\n  }).value().join(\'\');\n}\n\nvar names_with_pinyin = _(names).map(function(name) {\n    var name_with_pinyin = {\n    name: name,\n    pinyin: getShortPinyin(name)\n  }\n  return name_with_pinyin;\n}).sortBy(\'pinyin\').value();\n\n/* Output\n** 冯小刚(FXG)\n** 郭德纲(GDG)\n** 林志颖(LZY)\n** 马云(MY)\n** 潘长江(PZJ)\n** 王思聪(WSC)\n** 张柏芝(ZBZ)\n*/\n[/pre]', 0, 0, 0, '2019-12-25 12:50:38', NULL, NULL, '456', 'JavaScript', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (348, '如何用 JS 检测一张图片是否被正确加载了呢?', '[pre]\n<meta charset=utf8 />\n<script src=\"http://libs.baidu.com/jquery/1.9.0/jquery.js\"></script>\n\n<script type=\"text/javascript\">\n$(window).load(function(){\n  var load=$(\'#bdimg\').prop(\'complete\');\n  alert(load);\n})\n</script>\n<img src=\'http://www.baidu.com/img/bdlogoxx.png\' id=\"bdimg\"/>\n[/pre]\n图片明显是加载失败的,可是最后为何弹窗是true呢?', 0, 0, 0, '2019-12-25 14:23:13', NULL, NULL, '456', 'Node.js', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (349, ' iframe 传参问题', '需要在网站中用iframe嵌入了一个其他web应用， 然后在iframe里面点击这个web应用， 需要带入当前的用户信息过去。 搜了一下有iframe跨域单点登陆解决方法，但是目前场景不适合这种做法。\n我的想法是双方约定好加密公式，将用户信息通过url传过去， 对方再解密做验证， 不知道这种方法有什么弊端？ 还有我用什么方式能做到，点击iframe里面的每个url时 将这个参数带上？', 2, 0, 0, '2019-12-25 14:23:50', NULL, NULL, '456', 'Node.js', 1, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (350, ' iframe 传参问题', '需要在网站中用iframe嵌入了一个其他web应用， 然后在iframe里面点击这个web应用， 需要带入当前的用户信息过去。 搜了一下有iframe跨域单点登陆解决方法，但是目前场景不适合这种做法。\n我的想法是双方约定好加密公式，将用户信息通过url传过去， 对方再解密做验证， 不知道这种方法有什么弊端？ 还有我用什么方式能做到，点击iframe里面的每个url时 将这个参数带上？', 2, 0, 0, '2019-12-25 14:23:54', NULL, NULL, '456', 'JavaScript', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (351, 'JS 代码中 用户登录安全认证的问题', '前是用 AngularJS + Rails 架构，前端都扔给AngularJS，后端Rails来判断是否当前业务操作是否需要用户登录，如果需要，返回错误信息，比如401之类，前端AngularJS接收到请求认证之后，弹出modal dialog 登录窗口，请求用户进行登录，如果登录成功，需要继续执行之类的业务操作，这个怎么处理?\n\nG了大部分代码都是基于url登录页面来保存操作业务的url，然后登录完成之后再进行Redirect，像我这种 弹出窗口的，成功之后继续执行某个操作的好像没有。\n\n再G了一回，Ruby 中有类似的包装模式，执行完成之后，然后回调ControlName#MethodName，这样挺美好的。\n\n但是JS中怎么实现呢？这种弹出窗口根据业务需要可能不止一层，比如：先判断是否登录，再判断帐户余额是否够，再判断用户是否符合操作权限，一层一层下来，最终回调一开始要执行的业务操作，这中间怎么传递呢？又怎么回调执行呢？', 8, 0, 0, '2019-12-25 14:24:34', NULL, NULL, '456', 'JavaScript', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (352, '使用SpringBoot时的疑问及其解答', '1. Tomcat是怎么来的？\n\n　　在SpringBoot里面内嵌了Tomcat，即spring-boot-starter-web  -> spring-boot-starter-tomcat，导入了Tomcat\n\n2. SpringApplication.run是怎么启动的？\n\n初始化工作：准备环境，打印baner，创建容器\n加载项目代码完成自动配置\n打包项目到Tomcat\n启动Tomcat\n3. web.xml，SpringMvc等其它配置去哪儿了？\n\n　　自动配置\n\n4. spring-boot-starter-parent有什么用？\n\n　　是SpringBoot的父工程，管理了很多的基础依赖，如果我们要用里面的依赖，直接导入，不需要再写版本号。\n\n5. spring-boot-starter-web有什么用？\n\n　　用来集成web（SpringMvc），把web层需要的jar包都给你引进来了，包括：SpringMvc相关的包，日志相关包，json相关包，自动配置包，Tomcat包等等。\n\n6. @RestController的作用\n\n　　相当于@Controller + @ResponseBody\n\n7. @EnableAutoConfiguration的作用\n\n　　开启自动配置：通过一个导入选择器 AutoConfigurationImportSelector 会负责 spring.factories 文件中加载一些自动配置类。比如：前端控制器就通过一个         DispatcherServletAutoConfiguration 自动配置类完成，在这个类里面通过定义bean的方式定义了 DispatcherServlet 的实例对象。\n\n8. 项目打包方式为什么是jar？\n\n　　SpringBoot默认打jar包。\n\n9. dependencies与dependencyManagement的区别\n\n　　dependencies：父工程的dependencies标签下面的jar包会被子模块直接继承使用。\n\n　　dependencyManagement：声明/管理依赖的，父工程的 dependencyManagement 标签下面的jar包默认子模块是用不了的，如果子模块要用这个标签里面的jar包得显示的写出来，这个标签要用来统一管理jar包的版本号。', 0, 0, 0, '2019-12-25 14:25:32', NULL, NULL, '456', 'Java', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (353, 'LIST 线性表：ARRAYLIS，LINKEDLIST', '[pre]\npackage seday11.list;\n\nimport java.util.ArrayList;\nimport java.util.List;\n\n\n/**\n* @author xingsir\n* java.util.List 线性表\n* List是Collection常用的子接口，是一个可以重复的集合并且特点是有序。提供了一套通过下标操作元素的方法。\n* 常见实现类:\n* java.util.ArrayList:内部使用数组实现，查询性能更好，增删元素慢\n* java.util.LinkedList:内部使用链表实现，增删元素性能好，尤其首尾增删元素性能最好，但是查询元素效率慢\n* 对性能没有极端苛刻情况下通常使用ArrayList\n*/\npublic class ListDemo {\n\npublic static void main(String[] args) {\nList<String>list=new ArrayList<>();\nlist.add(\"A\");\nlist.add(\"B\");\nlist.add(\"C\");\nlist.add(\"D\");\nlist.add(\"E\");\nSystem.out.println(list);\n/*\n* E get(int index) 获取指定位置对应的元素\n*/\nString str=list.get(1);//得到位置1上的元素\nSystem.out.println(str);//得到B\n\nfor(String str1 : list) {//遍历\nSystem.out.println(\">>\"+str1);//打印\n}\n\n/*\n* E set(int index, E e)\n* 将给定元素设置到指定位置，返回值为原位置对应的元素(替换元素操作)\n*/\nSystem.out.println(list);\nString old = list.set(2, \"22\");//得到位置2上的元素,替换成22\nSystem.out.println(list);//打印\nSystem.out.println(old);//得到那个被替换的数\n\n}\n\n}\n[/pre]', 4, 0, 0, '2019-12-25 14:26:40', NULL, NULL, '456', 'Java', 0, 0, NULL, NULL, '小猫', 1);
INSERT INTO `article` VALUES (367, '是撒', '12312\nface[哈哈] ', 1, 0, 0, '2019-12-30 23:47:37', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 0);
INSERT INTO `article` VALUES (369, 'MySQL 导出数据时报错 UnicodeEncodeError', '在导出数据时，报错：\n\n\'ascii\' codec can\'t encode characters in position 9-10: ordinal not in range(128)', 3, 0, 0, '2020-01-01 18:02:55', NULL, NULL, 'admin', 'Mysql', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (370, 'node.js 作为中间层，社区有开源成熟的熔断方案吗', 'node.js 作为中间层，社区有开源成熟的熔断方案吗\nimg[/lt_56/attached/image/20200101/20200101182348_288.jpg] ', 5, 0, 0, '2020-01-01 18:23:53', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 1);
INSERT INTO `article` VALUES (371, 'testNode', '123123\n1img[/lt_56/attached/image/20200101/20200101182716_95.png] ', 1, 0, 0, '2020-01-01 18:27:24', NULL, NULL, 'admin', 'Node.js', 0, 0, NULL, NULL, '罗东荣', 0);
INSERT INTO `article` VALUES (372, 'Test_JAVAA', '132123123123123face[怒骂] ', 1, 0, 0, '2020-01-01 18:27:47', NULL, NULL, 'admin', 'Java', 0, 0, NULL, NULL, '罗东荣', 0);
INSERT INTO `article` VALUES (373, 'test_JS', 'img[/lt_56/attached/image/20200101/20200101182847_84.jpg] \n22222', 1, 0, 0, '2020-01-01 18:28:55', NULL, NULL, 'admin', 'JavaScript', 0, 0, NULL, NULL, '罗东荣', 0);
INSERT INTO `article` VALUES (374, '21312', '哈哈哈\nimg[/lt_56/attached/image/20200101/20200101182918_989.jpg] ', 1, 0, 0, '2020-01-01 18:29:29', NULL, NULL, 'admin', 'Mysql', 0, 0, NULL, NULL, '罗东荣', 0);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `articleID` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `articleIDAnduserName`(`articleID`, `userName`) USING BTREE COMMENT '唯一的',
  INDEX `userName`(`userName`) USING BTREE,
  CONSTRAINT `articleID` FOREIGN KEY (`articleID`) REFERENCES `article` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `http` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commentcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `articleid` int(11) NOT NULL,
  `creationdate` datetime(0) NOT NULL,
  `responderName` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `read` int(255) NULL DEFAULT 0,
  `like` int(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `articleID`(`articleid`) USING BTREE,
  INDEX `name22`(`responderName`) USING BTREE,
  CONSTRAINT `a` FOREIGN KEY (`articleid`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `name22` FOREIGN KEY (`responderName`) REFERENCES `user` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (55, 'admin', NULL, NULL, '小游戏用后端Node比较多', 338, '2019-12-31 10:10:02', '罗东荣', 1, 1);
INSERT INTO `comment` VALUES (56, '123456789', NULL, NULL, '222', 345, '2020-01-01 17:19:45', '小猫', 0, 0);

-- ----------------------------
-- Table structure for commentlike
-- ----------------------------
DROP TABLE IF EXISTS `commentlike`;
CREATE TABLE `commentlike`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleID` int(11) NOT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `commentID` int(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ac`(`articleID`, `commentID`) USING BTREE,
  INDEX `articleID`(`articleID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commentlike
-- ----------------------------
INSERT INTO `commentlike` VALUES (31, 364, 'admin', 48);
INSERT INTO `commentlike` VALUES (34, 364, 'admin', 51);
INSERT INTO `commentlike` VALUES (35, 364, 'admin', 49);
INSERT INTO `commentlike` VALUES (36, 364, 'admin', 50);
INSERT INTO `commentlike` VALUES (38, 365, 'admin', 54);
INSERT INTO `commentlike` VALUES (39, 338, 'admin', 55);

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `articleID` int(255) NULL DEFAULT 0,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `useraID`(`articleID`, `user`) USING BTREE,
  INDEX `articleID`(`articleID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (11, '色诱', 349, '罗东荣');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regTime` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '地球',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '(这人很赖签名都没有)' COMMENT '签名',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT './static/img/moren.jpg',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user`(`user`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  INDEX `img`(`img`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1, '罗东荣', NULL, NULL, '2019-12-13 14:40:39', '上海', '哈哈，你觉得这个论坛怎么样', './static/img//e301ef1c-002c-4a77-b260-53bf2b21f5dc-2222.jpg', '');
INSERT INTO `user` VALUES (4, '456', '123', 0, '小猫', NULL, NULL, '2019-12-13 14:40:42', '地球2222', '这人很懒没有签名', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (5, 'lp', '123', 0, 'lp', NULL, NULL, '2019-12-10 14:40:45', '地球', '这人很懒没有签名', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (6, '23123', '3123123', 0, '小狗', NULL, NULL, '2019-12-20 22:58:30', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (7, '12322222', '32', 0, '小猪', NULL, NULL, '2019-12-20 22:58:33', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (8, '2131', '3213', 0, '3123', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (10, '213', '3123', 0, '231', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (11, '3123123123123123123123', '3213', 0, '312312312312312', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (12, '12312', '3213', 0, '12312', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (16, '123', '3123', 0, '31223', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (17, '123333', '123123', 0, '123222', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (18, '112312312312', '123456', 0, '2', NULL, NULL, '2019-12-21 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (19, '15317994837', '1234567', 0, '123456', NULL, NULL, '2019-12-23 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (20, '13737148527', '123456', 0, '小荣', NULL, NULL, '2019-12-24 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);
INSERT INTO `user` VALUES (21, '123456789', '123456', 0, '东荣', NULL, NULL, '2020-01-01 00:00:00', '地球', '(这人很赖签名都没有)', './static/img/moren.jpg', NULL);

SET FOREIGN_KEY_CHECKS = 1;
