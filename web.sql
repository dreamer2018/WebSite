/*=============================================*/
/*    西邮Linux兴趣小组官网 数据库设计            */
/*    version：0.1                             */
/*    mysql：  5.x above                       */
/*    author: zhoupan                          */
/*    time:   2016.10.20 00：07                */
/*=============================================*/

DROP DATABASE IF EXISTS WebSite;
CREATE DATABASE WebSite;
USE WebSite;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS blog;
DROP TABLE IF EXISTS signup;
DROP TABLE IF EXISTS about;
DROP TABLE IF EXISTS link;
DROP TABLE IF EXISTS community;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS follow;

/*=================================================*/
/*      user            用户                     */
/*=================================================*/

CREATE TABLE user (
  id     INT PRIMARY KEY AUTO_INCREMENT,
  name   VARCHAR(20) UNIQUE NOT NULL,
  passwd VARCHAR(20)        NOT NULL
);

INSERT INTO user VALUES (1,'root','root');

/*=================================================*/
/*      events            活动                     */
/*=================================================*/
CREATE TABLE events (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  title      VARCHAR(50)  NOT NULL, # 活动标题
  content    TEXT         NOT NULL, # 活动内容
  poster_url VARCHAR(255) NOT NULL, # 活动海报url
  date       DATE         NOT NULL, # 活动日期
  time       TIME         NOT NULL, # 活动时间
  address    VARCHAR(40)  NOT NULL, #   活动地点
  tips       VARCHAR(30), #   标签
  reader      INT      DEFAULT 0 #  阅读量
);

/*=================================================*/
/*      blog            博客内容                    */
/*=================================================*/
CREATE TABLE blog (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  title   VARCHAR(50)  NOT NULL, # 文章标题
  author  VARCHAR(20)  NOT NULL, # 文章作者
  date    DATE         NOT NULL, # 日期
  time    TIME         NOT NULL, # 时间
  summary TEXT         NOT NULL, # 摘要
  url     VARCHAR(256) NOT NULL  # url
);
/*=================================================*/
/*      signup            报名                     */
/*=================================================*/
CREATE TABLE signup (
  id        INT PRIMARY KEY AUTO_INCREMENT,
  events_id INT         NOT NULL, # 事件id
  name      VARCHAR(20) NOT NULL, # 姓名
  email     VARCHAR(50) NOT NULL # 邮件
);
/*=================================================*/
/*      about            小组介绍                   */
/*=================================================*/
CREATE TABLE about (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  title       VARCHAR(50)  NOT NULL, # 标题
  content     TEXT         NOT NULL, # 文章
  picture_url VARCHAR(256) NOT NULL # 图片url
);

/*=================================================*/
/*      link             友链                       */
/*=================================================*/
CREATE TABLE links (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)  NOT NULL, # 链接名
  url   VARCHAR(256) NOT NULL  # 链接url
);


/*=================================================*/
/*      community            社区                  */
/*=================================================*/
CREATE TABLE community (
  id    INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)  NOT NULL, # 社区名
  url   VARCHAR(256) NOT NULL  # 社区url
);

/*=================================================*/
/*      address            地址                     */
/*=================================================*/
CREATE TABLE address (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  address  VARCHAR(50) NOT NULL,
  postcode VARCHAR(10)
);

/*=================================================*/
/*      follow            关注我们                  */
/*=================================================*/
CREATE TABLE follow (
  id     INT AUTO_INCREMENT PRIMARY KEY,
  weixin VARCHAR(256) NOT NULL # 微信二维码
);

/*=================================================*/
/*      donate            支持我们                  */
/*=================================================*/
CREATE TABLE donate (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  wei_name    VARCHAR(20)  NOT NULL, # 微信用户名
  weixin      VARCHAR(256) NOT NULL, # 微信二维码
  alipay_name VARCHAR(20)  NOT NULL, # 支付宝用户名
  alipay      VARCHAR(256) NOT NULL # 支付宝二维码
);
