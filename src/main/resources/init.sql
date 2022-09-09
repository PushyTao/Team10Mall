drop database if exists furniture;
create database if not exists furniture DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use furniture;

drop table if exists fo_record;
drop table if exists oorder;
drop table if exists image;
drop table if exists shopcar;
drop table if exists comment;
drop table if exists furniture;
drop table if exists category;
drop table if exists user;

CREATE TABLE user(
 uid int primary key auto_increment comment '用户id',
 uname varchar(255) not null comment '用户昵称',
 passwd varchar(255) not null comment '用户密码',
 gender int default 1 comment'用户性别',
 birthday date not null comment'出生日期',
 phone_num BIGINT not null comment'电话号码',
 city varchar(255) not null comment'居住地',
 address varchar(255) not null comment'联系地址',
 role int default 1 comment'角色标识 0为管理员,1为普通用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table category(
	cid int primary key auto_increment comment '分类id',
	cname varchar(255) not null comment '分类名称',
	english_name varchar(255) not null comment'英文名称'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table furniture(
	fid int primary key auto_increment comment '家具id',
	fname varchar(255) not null comment '家具名称',
	price float not null comment'家具价格',
	cid int not null comment '家具种类id',
	del int default 0 comment'删除标记 0为未删除,1为已删除',
	constraint a foreign key category_furniture_cid (cid) REFERENCES category(cid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table shopcar(
	sid int primary key auto_increment comment'购物车id',
	fid int not null comment'家具id',
	uid int not null comment'用户id',
	count int default 1 comment'家具数量',
	del int default 0 comment'删除标记 0为未删除,1为已删除',
	constraint b foreign key user_shopcar_uid (uid) REFERENCES user(uid),
	constraint c foreign key furniture_shopcar_fid (fid) REFERENCES furniture(fid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table comment(
	cid int primary key auto_increment comment'评论id',
	content text not null comment'评论内容',
	ctime DATETIME not null comment'评论时间',
	uid int not null comment'评论用户id',
	fid int not null comment'评论家具id',
	parentid int default 0 comment'父评论id',
	del int default 0 comment'删除标记 0为未删除,1为已删除',
	constraint d foreign key user_comment_uid (uid) REFERENCES user(uid),
	constraint e foreign key furniture_comment_fid (fid) REFERENCES furniture(fid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table oorder(
	oid int primary key auto_increment comment'订单id',
	o_num varchar(255) not null comment'订单编号',
	o_name varchar(255) not null comment'姓名',
	phone_num bigint not null comment'手机号码',
	rec_addr varchar(255) not null comment'收货地址',
	o_time DATETIME not null comment'订单创建时间',
	total float not null comment'订单总金额',
	deliver_status int default 0 comment'发货标记 0为未发货,1为已发货',
	rec_status int default 0 comment'确认收货标记 0为未确认,1为已确认',
	uid int not null comment'用户id',
	del int default 0 comment'删除标记',
	constraint f foreign key user_order_uid (uid) REFERENCES user(uid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table fo_record(
	id int primary key auto_increment comment'记录表id',
	fid int not null comment'家具id',
	oid int not null comment'订单id',
	count int not null comment'数量',
	total float not null comment'总价',
	del int default 0 comment'删除标记',
	constraint g foreign key furniture_record_fid (fid) references furniture(fid),
	constraint h foreign key order_record_oid (oid) references oorder(oid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table image(
	iid int primary key auto_increment comment'主键id',
	cover varchar(255) not null comment'封面图片名称',
	one varchar(255) not null comment'第一张图片名称',
	two varchar(255) not null comment'第二张图片名称',
	three varchar(255) not null comment'第三张图片名称',
	four varchar(255) not null comment'第四张图片名称',
	fid int comment'家具id',
	del int default 0 comment'删除标记 0为未删除,1为已删除',
	constraint i foreign key furniture_image_fid (fid) references furniture(fid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user values(null,'hapehape','ruocai666',1,'1995-03-03',13188681111,'西安市','陕西省西安市莲湖区紫名小区',0);
insert into user values(null,'jackjack','ruocai666',0,'2000-03-03',12233987968,'北京市','北京市朝阳区龙湖小区',1);

insert into category values(null,'绿色客厅家具','living');
insert into category values(null,'环保厨房家具','kitchen');
insert into category values(null,'卧室家具直供','bedroom');
insert into category values(null,'环保实木家具','wood');
insert into category values(null,'现代简约灯具','modern');
insert into category values(null,'传统中式灯具','tradition');
insert into category values(null,'欧洲古典灯具','europe');
insert into category values(null,'美式古典灯具','us');

insert into furniture values(null,'实木沙发组合现代简约轻奢客厅家具乌金木贵妃转角布艺新中式真皮',5586.75,1,0);
insert into furniture values(null,'胡桃木全实木沙发组合家具客厅套装经济型三人真皮新中式木质沙发',2080.00,1,0);
insert into furniture values(null,'全友家居科技布鹅毛填充布艺沙发客厅现代简约大小户型家具102620',7344.90,1,0);
insert into furniture values(null,'新中式全实木沙发组合大小户型客厅全屋家具冬夏两用高箱储物沙发',9480.00,1,0);
insert into furniture values(null,'侘寂风生态硅胶皮沙发小户型乳胶免洗客厅组合家具极简意式沙发',4580.00,1,0);
insert into furniture values(null,'布艺沙发简约现代大小户型客厅转角科技布沙发可拆洗组合整装家具',1980.00,1,0);
insert into furniture values(null,'花梨木新中式全实木沙发组合轻奢中国风大小户型别墅家用客厅家具',1890.00,1,0);
insert into furniture values(null,'布艺沙发大户型客厅简约现代家具套装组合家用棉麻科技布乳胶沙发',2880.00,1,0);
insert into furniture values(null,'现代简约2021新款布艺沙发客厅家用小户型轻奢组合套装网红款家具',1120.00,1,0);

insert into furniture values(null,'家逸实木餐边柜简约现代一体靠墙碗柜酒柜子家用置物柜厨房茶水柜',1399.00,2,0);
insert into furniture values(null,'定制实木餐边柜酒柜一体靠墙家用客厅现代简约轻奢厨房窄型茶水柜',1859.00,2,0);
insert into furniture values(null,'林氏木业餐边柜轻奢厨房储物茶水柜子客厅靠墙碗柜家用家具KC1T',1450.00,2,0);
insert into furniture values(null,'全友家居橱柜家用厨房餐边柜储物柜岩板靠墙客厅餐厅家具127809',2182.00,2,0);
insert into furniture values(null,'全友家居置物收纳储物架书架杂物架厨房客厅家用简约家具DX115039',615.00,2,0);
insert into furniture values(null,'梵禾现代简约开放式厨柜整体厨房橱柜定做石英石台面全屋定制家具',1100.00,2,0);
insert into furniture values(null,'木禾梵品美式实木橱柜定制欧式法式整体厨房岛台原木全屋家具定做',2000.00,2,0);

insert into furniture values(null,'千骏轻奢真皮床北欧主卧现代简约软包轻奢大气1.8米卧室双人婚床',3480.00,3,0);
insert into furniture values(null,'千骏轻奢真皮床主卧双人床1.51.8米储物现代简约婚床软包家具皮床',3399.00,3,0);
insert into furniture values(null,'意式极简真皮床现代简约皮床主卧室新款婚床1.8米双人床科技布床',2698.00,3,0);
insert into furniture values(null,'美式实木床欧式真皮双人床主卧高档复古1.8/2米大床卧室家具组合',6864.00,3,0);
insert into furniture values(null,'林氏木业现代简约布艺床头软包主卧室1.5双人大床科技布家具LS204',1198.00,3,0);
insert into furniture values(null,'全友家居现代主卧1.8m头层牛皮双人大床卧室家具真皮床轻奢105251',2869.00,3,0);
insert into furniture values(null,'源氏木语全实木床卧室1.8橡木北欧床现代简约1.5米主卧双人床家具',3496.83,3,0);

insert into furniture values(null,'新中式沙发实木简约沙发现代客厅家具123沙发组合 橡胶木中式复古',2090.00,4,0);
insert into furniture values(null,'青水秀木新中式乌金木实木沙发组合客厅别墅大户型轻奢全实木家具',3680.00,4,0);
insert into furniture values(null,'源氏木语全实木床卧室1.8橡木北欧床现代简约1.5米主卧双人床家具',2770.00,4,0);
insert into furniture values(null,'新中式全实木沙发组合大小户型客厅全屋家具冬夏两用高箱储物沙发',9480.00,4,0);
insert into furniture values(null,'林氏木业儿童床小男孩1.5米单人床房实木脚卧室家具组合套装LS196',2379.00,4,0);
insert into furniture values(null,'新中式实木冬夏两用收纳储物沙发大小户型新中式客厅现代简约家具',2580.00,4,0);
insert into furniture values(null,'维莎日式实木床现代简约1.5/1.8米橡木双人床北欧环保卧室家具',3699.33,4,0);

insert into furniture values(null,'丹菲诺全铜灯具套餐天猫精灵智能语音2021年轻奢吊灯后现代全铜灯金色',430.00,5,0);
insert into furniture values(null,'天猫精灵语音控制灯智能餐厅吸顶灯全屋灯具大灯大气小米现代简约家电',861.00,5,0);
insert into furniture values(null,'帕莎灯饰简约后现代全铜餐厅吊灯轻奢大气长条饭厅岛吧台水晶灯具',1480.40,5,0);
insert into furniture values(null,'客厅灯简约现代大气家用led吸顶灯2021新款卧室灯具组合全屋套餐',168.00,5,0);
insert into furniture values(null,'雷士照明现代简约客厅灯led北欧吸顶灯具小米智能家用套餐卧室灯',1799.00,5,0);
insert into furniture values(null,'月影凯顿轻奢客厅吊灯现代简约大气水晶灯餐厅卧室北欧皇冠铜灯具',1471.08,5,0);
insert into furniture values(null,'飞利浦led客厅吸顶灯现代简约大气超薄智能卧室灯具全屋套餐怡轩',1199.00,5,0);

insert into furniture values(null,'简约现代客厅灯新款新中式吊灯中国风禅意大气创意餐厅卧室书房灯',225.00,6,0);
insert into furniture values(null,'新中式客厅吊灯大气复古中国风实木红木卧室餐厅东南亚艺术禅意灯',698.00,6,0);
insert into furniture values(null,'新中式吊灯黑胡桃实木餐厅吊灯具中国风简约卧室全铜轻奢客厅吊灯',1918.00,6,0);
insert into furniture values(null,'新中式吸顶灯禅意高端大气长方形灯具竹绘中国风极简传统客厅灯',668.00,6,0);
insert into furniture values(null,'雷士照明led吸顶灯新中式客厅灯木艺中国风传统禅意家用灯具套餐',399.00,6,0);
insert into furniture values(null,'宴会厅传统祥云大型大堂酒店水晶灯具工程定制售楼部大厅沙盘吊灯',100.00,6,0);
insert into furniture values(null,'大型大堂酒店水晶灯具工程定制售楼部大厅沙盘吊灯别墅中空传统',268.00,6,0);

insert into furniture values(null,'黄铜蛋白石吊灯古典中古客厅主灯欧洲设计师美式乡村卧室餐厅灯具',1180.00,7,0);
insert into furniture values(null,'VL Ring Crown欧洲法式蛋白石客厅吊灯主卧室设计师古典玻璃灯具',879.12,7,0);
insert into furniture values(null,'法式全铜古典蜡烛灯欧洲别墅复式楼书房复古客厅餐厅轻奢卧室灯具',7598.00,7,0);
insert into furniture values(null,'后现代黄铜蛋白石吊灯 古典客厅主灯欧洲设计包豪斯现代法式灯具',198.00,7,0);
insert into furniture values(null,'欧洲进O口代现代简约中国风工程过道创意家居树脂新中式古典灯具',439.00,7,0);
insert into furniture values(null,'欧洲古董灯具收藏 1910年德国黄铜吊灯 喇叭花玻璃灯罩 古典美',12500.00,7,0);
insert into furniture values(null,'新中式过道走廊阳台实木灯古典中国风别墅艺术灯具欧洲复古设计师',2186.00,7,0);

insert into furniture values(null,'DDLL 欧式古典客厅灯奢华大气卧室灯具套餐别墅新款美式复古吊灯',618.00,8,0);
insert into furniture values(null,'欧式吊灯客厅灯美式简约餐厅吊灯别墅复式楼奢华古典藏式套餐灯具',460.00,8,0);
insert into furniture values(null,'月影灯饰美式客厅吊灯轻奢乡村田园怀旧全铜餐厅卧室古典复古灯具',1202.40,8,0);
insert into furniture values(null,'法式古典水晶壁灯 美式乡村铁艺轻奢卧室客厅过道床头水晶壁灯具',227.04,8,0);
insert into furniture values(null,'尚寓燚居 美式复古餐厅吊灯乡村古典艺术老上海卧室全铜玻璃灯具',1250.00,8,0);
insert into furniture values(null,'现代中式别墅客厅全铜吊灯设计师个性异形美式简约古典装饰灯具',1279.88,8,0);
insert into furniture values(null,'欧式客厅吊灯美式复古餐厅灯复式楼别墅奢华大气古典全屋套餐灯具',457.92,8,0);

insert into image values(null,'39f86a57-81c6-4951-a1bc-6d4a4166b993.jpg','5b91b19b-62f4-45f4-9413-1f252612ee64.jpg','29421e69-8bbb-4db7-b295-fde10e0d48d4.jpg','0fb8b466-dab1-4923-94fc-406379dd319c.jpg','8f028ddb-0fa0-4965-96c6-b45c2a950dad.jpg',1,0);
insert into image values(null,'2c858cac-1f3d-417a-a84a-e8e8038923b8.jpg','a6aa5a43-5483-4542-9f0d-32645db72031.jpg','9b26a558-9d5b-44be-a6e0-de96c61305d6.jpg','370321be-d66e-4db7-bc5a-dccaadf705e8.jpg','6486b420-8110-469d-ad75-fa5d690ac13e.jpg',2,0);
insert into image values(null,'2abddfef-6215-4cf5-9bf1-859dd0a5620c.jpg','81bd44fa-f551-4d5c-ba0f-4e51495aad9a.jpg','a08b5b3a-7f42-4dd6-b7c5-07100834453a.jpg','1b5d7a51-0af6-46ab-9b8e-339fbfababae.jpg','2e7a3e0b-e2b3-40a8-8171-a545555c8183.jpg',3,0);
insert into image values(null,'7db83d6d-3d4d-4dd4-a4af-5e449136c4bc.jpg','c10c2c9a-704c-4753-82cc-5e5426387fd3.jpg','b6a901c9-6346-4c9a-9636-527f362454da.jpg','d3bdc34a-66f9-4e12-a7d4-7828600fa8c3.jpg','bebeef66-f5e9-4f66-8c90-2293ca0133c6.jpg',4,0);
insert into image values(null,'ff2255c0-0c45-4b63-949d-adb563d5c8a2.jpg','2e88e5be-e880-4834-90d4-5f0ed0ae7682.jpg','a4a0ab6e-e666-478d-90f4-7e8aa5052b2b.jpg','2d3de454-fee4-4ddf-928f-cdbeb6f298ee.jpg','0ea20b5f-cfbf-4211-aac8-5e347d557f3b.jpg',5,0);
insert into image values(null,'8110a07e-1a7a-4770-ab8c-2fc007fb5001.jpg','d282432f-77eb-46eb-863f-363b75e67f1f.jpg','2fabc5ff-01ee-472c-b28a-ead783793800.jpg','048924af-0b54-4b6c-826e-9ebab40e902d.jpg','b9e0c3c9-ee73-4b51-ba0b-11c7b328919e.jpg',6,0);
insert into image values(null,'d795b0f1-cf1b-48b2-9236-1fc81ffb3144.jpg','bec6e637-3ab8-4d6b-9d0f-22181bdf16bf.jpg','908d3c72-3f39-46f6-9ee1-a619ad68ce82.jpg','a4099c5e-66f7-408d-b028-909c7ee70274.jpg','5b2ea4fe-9b28-41c2-8fee-f67a029036ef.jpg',7,0);
insert into image values(null,'bfa729b0-7321-4151-9a25-a5b72f6a2598.jpg','a798de8e-64a4-4fa0-a528-260c38fde8a7.jpg','c2bb27a6-6183-421a-846a-a8a7958353b3.jpg','dcda7873-0539-4bc3-9ab4-7dbea1edf877.jpg','d759de18-d363-4004-8b86-95127d4d9a7d.jpg',8,0);
insert into image values(null,'5fc888c4-d072-4a7d-8dc9-b89d18dc7e5d.jpg','7e36c7e7-9509-4576-ac07-2c695a80629d.jpg','3380224c-4f46-4e9f-8de2-21656c9c9d04.jpg','353a7b93-6e1b-4811-bca8-45ee75b1e364.jpg','086eacdd-d5d8-4b33-89a2-2bb920fae4a4.jpg',9,0);
insert into image values(null,'b64280ef-b561-4bcd-aa0b-d98feb39a52e.jpg','4fa4c519-df9e-40d6-9646-5000efad3664.jpg','245040e3-fb05-4711-847b-e23b749a6894.jpg','ca5ac7a6-2b3d-4d07-a93b-00c184d4cb9d.jpg','8a5b57db-5fe4-4b0f-9539-b15cc29d9f94.jpg',10,0);
insert into image values(null,'cf73acac-a9b4-4a46-a091-f7d9150dbe28.jpg','ec8165ce-247d-424a-8d93-98462f69b4b8.jpg','065296aa-d772-4e48-80c2-5a7d5b79de16.jpg','96013e1d-4d2f-47d0-98d1-4835e90b8852.jpg','b6efa7aa-4fa3-40fa-8798-fe9ba970f469.jpg',11,0);
insert into image values(null,'e6942f89-f603-432b-a8ce-036048eb7af5.jpg','f4e2731a-adc7-4d14-b0d5-26c244b2136b.jpg','6d2b443e-a7d8-4c15-b01c-dbbf0dc11b30.jpg','b3cf27d4-7189-4748-81a0-369cae815698.jpg','74911774-5f3c-46e7-927a-564d194448ea.jpg',12,0);
insert into image values(null,'bebe9a07-cffc-43ef-9d72-8ff3826b1a68.jpg','c5ac0174-b0d3-43b2-9ab9-1c4003c00114.jpg','18cd1f02-7d84-4b13-a42d-f7be85dd3c19.jpg','2db511ce-d19f-467c-bcf5-4586ca2c9565.jpg','9b7ce2a1-e4db-4f0f-b329-efea56702ad9.jpg',13,0);
insert into image values(null,'c7e7796c-3c86-4fd6-961f-e0c9860e71f8.jpg','d1ab1f28-c612-4839-ae9c-4e62291e70fa.jpg','c7f3b54a-9351-4631-93eb-1bb79d6b82c9.jpg','cd1e0e89-13e0-47f8-8082-a98b0207923b.jpg','3ff619c6-9af5-457e-9691-91b3f86301d6.jpg',14,0);
insert into image values(null,'98bd12b7-d794-49a2-a180-bb3a11b779be.jpg','606c07c2-3517-4824-ba89-fea0887fb401.jpg','83aeaa50-2443-494f-a408-22fa0dfe42f0.jpg','41c152e5-b1d6-45f2-a532-1b425738c442.jpg','11fa5913-c46b-448a-9fa5-85a4d2e2acb0.jpg',15,0);
insert into image values(null,'552d5c2b-ccce-4557-b634-626e4ddd9563.jpg','1faf70d9-1ae8-4274-9464-1d9fa529d4b2.jpg','52fa0a71-decb-49fd-ba2b-992f856d6496.jpg','fa82742e-688c-4a68-9937-5dc3fa574324.jpg','9e17b34b-c361-4926-900a-3d641aa261e8.jpg',16,0);
insert into image values(null,'0ecb1bc8-a3ca-4296-a376-4182fe52aac6.jpg','6ac41b61-b387-4846-b86a-4297e79ce942.jpg','b50a7f55-51f8-4b35-8e2a-9aab347a5b07.jpg','b2fef3cd-1bdc-4b92-a611-3c683e74572d.jpg','5444ea1e-050c-43ce-97da-4d99b1e8c7cb.jpg',17,0);
insert into image values(null,'93ab4cdb-8722-403a-8a08-0f8cafde1cf8.jpg','0dd2d12d-7021-4d2e-998e-34bbb32d1ce9.jpg','f073aec6-6da7-44b4-a049-ecd1622a5c36.jpg','7b310b60-6bd5-43c3-8199-4999c1850e79.jpg','d087d56f-2a57-4f66-ac64-f1f27336868f.jpg',18,0);
insert into image values(null,'91c0a91f-05e4-421b-80d7-445fa4c65329.jpg','3255a04a-5e07-4fbc-b316-9aefda716784.jpg','9b05b946-a419-415a-a403-612ca6e95f6e.jpg','3754485b-9974-42bf-921e-0e806a4b4775.jpg','bfdc9131-0fc0-46b5-a40f-84b8d9261817.jpg',19,0);
insert into image values(null,'ea7b6107-fa87-4b55-be2b-15d84435d10c.jpg','75f162f9-2ce3-483c-8f52-b0dbf226bd83.jpg','e14fd861-19f6-4d34-811f-5083aee04032.jpg','75ecbae5-f4e5-4020-88bd-70cca76bcd77.jpg','abc4afe1-09d0-4a66-8d5d-70bf8a21630b.jpg',20,0);
insert into image values(null,'b4cfafb5-6aef-41cc-8ba1-cb671e523b24.jpg','359bfe51-031f-4ec7-a412-32ddda7106bd.jpg','d557b31e-55c6-43d7-b108-9100e98f3bb7.jpg','6bb9f38a-8fa1-4c9e-ba4b-9a73e93c50d0.jpg','ee77c76e-524c-4381-91ce-d72d7220a127.jpg',21,0);
insert into image values(null,'de64adff-874f-44db-ba9d-9d27fa03dd3a.jpg','dfd583ac-8196-402c-b895-c7a45ec0ccec.jpg','81be6aaf-1d96-44f1-9636-0275bea529c7.jpg','428b8cf8-e3a0-4e73-a5f0-842249279733.jpg','1bb64cf1-d911-4bfc-8598-b8816fed3d89.jpg',22,0);
insert into image values(null,'6e84973c-0023-4c2c-97c5-efbefc0913d3.jpg','27ccf8a6-0e3f-40bb-bc39-712fd5e3bf0b.jpg','2ad8481b-e5de-4a17-b115-319b18af308d.jpg','8b060af2-991c-4fee-8e36-55e75588290e.jpg','e9d4907f-cd40-43e9-b517-c009b0416a95.jpg',23,0);
insert into image values(null,'19b0b42d-5d95-4e00-b59a-559156718102.jpg','907ba038-a3a5-40cf-beb6-f5fe4d794c86.jpg','48fafd95-a239-4e1d-8ab8-3a78726775dc.jpg','6a3e6f16-9b0a-4ced-a6b8-161f7106d207.jpg','9f82ef97-5b9f-4c82-af7d-745225279a21.jpg',24,0);
insert into image values(null,'07e487cf-3be4-4312-94f5-6e88542f5c28.jpg','b2e1a83a-d853-41f5-8bce-d5e155d4ba42.jpg','ae649119-cdc4-48e4-a8c7-1adacdf3efc3.jpg','f4f4a5ab-e97e-41a6-a568-f7699b424f5b.jpg','654fcccc-5090-4810-80c7-c69ed373216b.jpg',25,0);
insert into image values(null,'954a4d07-8ff2-4df8-a158-591f6d8269eb.jpg','3d0db5da-96c9-4bb6-93b5-0088f50a1bcb.jpg','d88af8ca-0b2e-45d4-ad9a-385ebeda4d66.jpg','c82b40ba-73d3-40e3-940f-f8e9cc91fcd6.jpg','d837392b-9718-4c54-bc4d-5d4cc8ccca7c.jpg',26,0);
insert into image values(null,'ebf17c98-b439-4f44-a6ce-1682c63d701e.jpg','d45cb0b6-4a0e-4577-a6d2-cb90cb6f65eb.jpg','89712669-7c9f-40d1-8083-9a434ef9d0bc.jpg','2f1c81eb-e6e7-474c-90fd-9b17904db0e8.jpg','b311baf6-7bcd-493b-9024-6d775269224c.jpg',27,0);
insert into image values(null,'36062f6b-c613-4c11-bb14-5d3f4953226e.jpg','eccf1611-2c45-4b0e-8d66-dd06519f85fd.jpg','0f400ae7-5140-42ca-80df-280d76821132.jpg','a2111df3-de93-4c74-8f44-7bf960f0d6eb.jpg','6fe2e6c9-ea49-4353-a8b5-35103e785389.jpg',28,0);
insert into image values(null,'b0ff796a-fdda-4fa6-b95c-7c5e3cc7a7af.jpg','c0e38bc5-b99b-4495-9f54-4142dc618c5e.jpg','877722ab-c038-4d2b-8f88-28877bcc0bb2.jpg','7abb4886-e787-487d-bacb-d83eecf478e7.jpg','7625a3c9-df0f-456e-ade9-5e356844a6b6.jpg',29,0);
insert into image values(null,'a96dc878-4436-43b6-af46-70bed1f9ccc0.jpg','6b5c53ea-41ed-48ac-8290-5f8f90dcbd36.jpg','abc1ff03-1dbd-40cc-95bf-b54d89581790.jpg','3df37994-77df-46b2-986e-b279341e3c78.jpg','7816a385-b9f3-4452-a9f3-a6cff84a494f.jpg',30,0);
insert into image values(null,'11cabbf3-e8bc-49ee-b609-1497bfe4d4bc.jpg','244ee2ac-2229-415e-b78c-da28c7821234.jpg','edb3cedd-7832-42c1-a898-b81eff51b468.jpg','9b61c0e8-54d9-4d98-892c-ad598a2d7937.jpg','12763af2-d340-490e-9fd1-ff661ff6757d.jpg',31,0);
insert into image values(null,'bd185e6f-1adb-4477-8870-e1c83534c825.jpg','a16add56-77d7-4581-b4ab-c6033c3c05c7.jpg','4d176153-84c5-4f9c-96a5-50f35aebfca4.jpg','af557949-561e-474b-bb3d-a762f51a14e3.jpg','d412a77b-1871-4a91-a505-0aac1bb3b4ce.jpg',32,0);
insert into image values(null,'722724b4-aa94-40fa-9fab-88ae09fd7eea.jpg','756308f0-053a-447b-9e6f-a04196922cfe.jpg','a6859dde-f5dd-4134-8d4f-465627651c1e.jpg','0beb7940-6918-4c81-81ee-718e04a41c74.jpg','689b29d6-131b-44e1-8675-88ed42cd9967.jpg',33,0);
insert into image values(null,'b7145f76-e530-4ff1-baee-0c8520263205.jpg','5c71fe9b-4431-43d2-b7c6-99f29643fc59.jpg','f9f3ceaf-e535-4b86-8e8a-3e0a800c585a.jpg','1f9a367f-cc23-4e67-8549-a38c70e0b78d.jpg','cfdd28da-7625-4145-b95f-a157a622b33f.jpg',34,0);
insert into image values(null,'92f8a234-6ead-43c8-a2ca-6aa23aa18113.jpg','ef78fe7f-6b1a-4a10-b77b-5ae03afda7a3.jpg','051832dc-9fee-4841-b478-bf866b51530a.jpg','2d3d850b-e670-458f-8ec2-1165bc91c7f6.jpg','0a051788-a356-4893-890b-9cf67f71d7e9.jpg',35,0);
insert into image values(null,'e44be541-bf8d-497c-8e97-f421bd28bcfb.jpg','11ecc5c6-5726-4e7a-95c7-f6deb0e359ef.jpg','1488a0a9-6c33-4c46-be0b-bbfcd5f99d29.jpg','359c0c46-25b8-47e7-ab27-1f39b05b2cb1.jpg','18f88ccb-cbcf-4959-ad69-7cf5e737b627.jpg',36,0);
insert into image values(null,'bfef113e-b0e0-483c-b84b-33337522c06e.jpg','d8d415f3-77c3-46b3-b356-b1293e6df53c.jpg','e3d3a1b6-6d4d-4d01-a0f9-32f7334ec892.jpg','05e9bbdd-e537-4eff-bcd0-2ae18aad284d.jpg','077aeec0-e615-4338-a6ba-fbd36d7808fd.jpg',37,0);
insert into image values(null,'e67a4450-0a6f-4495-b54a-69bf2a55bed9.jpg','866ca0df-47b4-4a84-8e5f-b8f87ed945f4.jpg','f4c85c41-f79c-4559-939e-dc58747da2ff.jpg','3f082881-30f3-43aa-9354-d6a2fc916047.jpg','f27b5726-b7a5-4d91-9984-2efe9915b36f.jpg',38,0);
insert into image values(null,'009b1dec-fbfa-416e-a8c3-017fe17a9a57.jpg','621dc66e-2c17-4803-af05-b04c4087865e.jpg','052a1448-0b86-482c-a0b6-2fe153203822.jpg','d7b16685-c5fa-4b8b-a2da-55f2ff6cf981.jpg','21204e8e-6cf6-4101-b710-7969cf3e9067.jpg',39,0);
insert into image values(null,'eb4e00e8-c829-4e1b-97b0-8b7731fe05bb.jpg','6ef51099-42f0-4f7a-9e5f-17e957d9d6d2.jpg','aec7f5a7-9057-4ae6-b7db-6834cef82603.jpg','4a92a529-c8ac-41ba-938f-3b1036aa565d.jpg','33730fd4-6528-44b1-b03e-fe11a09a8d8e.jpg',40,0);
insert into image values(null,'5a69d65b-3d50-4369-99cc-c5fa7be0c4c6.jpg','28b51084-5e23-4d21-92bd-e3b1cdefa9c8.jpg','e0b2e59e-d34d-4586-b2bf-56dc33ecfdd8.jpg','674481f6-a88b-4c96-b483-dfa521130d4f.jpg','def51cca-d541-4635-bdda-6ad36c18889a.jpg',41,0);
insert into image values(null,'2bf2e6fb-7105-4e05-b2a2-9a27502dfc20.jpg','aec01043-2201-4fed-978c-f2485ef25c93.jpg','70265ebd-ed88-4a49-821a-81cb5e59e09d.jpg','c8f86ae6-7946-4cfa-8bb4-502b805f8901.jpg','8aca4e96-e0e1-4709-9529-531ee0b627c0.jpg',42,0);
insert into image values(null,'e389aa1e-3c5c-4077-ac66-9df18ad5930b.jpg','45ee302c-9f3d-4449-a879-6f2a83eeb357.jpg','af4d2475-c719-47e7-827b-67c597fab583.jpg','9eafa68b-9e77-4097-bfb8-f4fa53c5d904.jpg','a81eec50-4b14-45d0-9b72-d233e0651f21.jpg',43,0);
insert into image values(null,'a5e6f012-5244-4f5b-9bb2-4c6d345c381f.jpg','5cf47259-d0b2-4896-8fd0-453aa9ef8dd8.jpg','a13195f4-a0c2-4296-9e40-231c987fc321.jpg','b4f1570b-549e-44a9-a727-8b5ec73df095.jpg','83832ee5-0111-4f0d-8640-bab16fed6490.jpg',44,0);
insert into image values(null,'ff1735e8-b141-49bb-8c85-f4b7c57567c4.jpg','d950213f-4441-47b1-ad2a-e4baab809eb1.jpg','3f859358-dcfd-4141-9587-fbe7528de05f.jpg','d0159435-3c31-4024-bddc-d18c166f8ab3.jpg','f1094ed7-7706-4cde-92c7-18204a9fb765.jpg',45,0);
insert into image values(null,'9685bd74-2e00-4541-83f5-d101854eb93a.jpg','2ce78dfb-8759-4f61-8520-036a5fe81357.jpg','65f15b3b-3431-4d23-8f75-e2e5fead3649.jpg','5b9fb459-16fb-4f8e-9cfc-3f7be689803a.jpg','b9e0d48d-5bea-4413-a1fb-b4068544b237.jpg',46,0);
insert into image values(null,'e5b5ab41-ae53-449f-be55-d76c7708a4c4.jpg','059bb6e1-cac2-4b42-9770-1b673d7e7e06.jpg','8c36be7a-bc5a-4a24-9df6-b5c71efd4c29.jpg','afba0e9a-b403-4d02-b956-7c8a9fd15a63.jpg','258387f4-4934-40aa-922f-a953eead4511.jpg',47,0);
insert into image values(null,'78814b33-5f64-4af2-ab1a-b303aeb19a8c.jpg','2d03455a-5974-4c3d-adea-d9c2f525aba7.jpg','45443152-2d69-48c3-9bf5-58c68b97a073.jpg','96a2e574-70ab-48c7-bd88-96f4b6c95f28.jpg','716504f0-f750-45d4-9382-63c6538644af.jpg',48,0);
insert into image values(null,'7accfb30-78db-47ae-9e1e-dadf28b3c0dc.jpg','1cf2feb7-4b40-4554-a1ba-a9bcbc39cc11.jpg','19e3a6ef-3d37-4a70-87b4-6d6b437c64ac.jpg','bc534dd8-4edd-43c7-87d1-6af0a71a0a85.jpg','fd700aa0-e856-49ae-97da-22baf55a65a2.jpg',49,0);
insert into image values(null,'3554dcd3-c46d-42a5-8f8f-d85c58936f11.jpg','9c4e463e-c210-4904-8f0a-84b45c735d08.jpg','22317b53-7d38-4837-b0c5-85734b075b8c.jpg','7e852ebd-7a58-41cb-bc01-cbaeaaaa1a26.jpg','b30bce97-37cd-4e19-80fc-94560061de7b.jpg',50,0);
insert into image values(null,'34ae06af-b91a-402d-acd8-39463894e0fc.jpg','472e24a8-1e39-40d2-b13b-064abf68b4d9.jpg','1ebc57ce-4d6a-422e-bec8-82789c008925.jpg','15098743-b849-42e7-84c1-1f18b6bee006.jpg','804d6d39-5d54-419e-93f5-7118fb24738a.jpg',51,0);
insert into image values(null,'0feb3083-c279-4d52-8919-c37720348f54.jpg','849807f0-4597-4ed0-bacb-19104b42d7de.jpg','d9b5109d-087c-41e4-8cc1-009d614f63ba.jpg','4cb1a07d-eb79-4ac3-af5f-7f46ac40fd0f.jpg','99a3e966-2b72-41e9-95d7-3cbaab970f48.jpg',52,0);
insert into image values(null,'20fe2c65-4263-4a90-beb7-0bbffbaf1401.jpg','aa43d2db-6a1f-4634-8e3c-27e2802461f3.jpg','7d21ae35-9006-4c25-974f-6c58d9c2dfa2.jpg','ab34de2f-c7aa-4641-95b6-e07fe033f222.jpg','2eaab25d-ae74-4e95-be1c-a1892bcc6691.jpg',53,0);
insert into image values(null,'15ee1c09-c62c-484b-aa16-64ec3595d36f.jpg','6ec26aad-b501-4fbb-bbb3-8e3aa0b550c1.jpg','02f999ae-fe0f-405c-a91d-7e371790a74f.jpg','47d2d7c6-5157-4253-8e52-16d8b3578409.jpg','2382b291-89ac-4f28-a660-3af34d944c79.jpg',54,0);
insert into image values(null,'826d84c2-2314-48b3-ac1f-7fd2d0505142.jpg','8e784336-4d57-49c2-8b9b-012c3e9ff584.jpg','763bc6f4-a74f-47d7-af53-bdcf31ae092f.jpg','a9634cae-10e9-4f65-bf69-c28a42fe66c3.jpg','02d35b3a-49f8-449f-b2f2-50e8bfd0f803.jpg',55,0);
insert into image values(null,'35853f7f-8a3f-4975-91d2-b2000f5ff702.jpg','61c364e0-01d5-48ed-88ae-7c42a46a166c.jpg','ad87381b-f36a-4144-aae6-945ec74035e2.jpg','3072d11e-0866-4657-a162-bf2dfacea648.jpg','5084f998-3d93-430e-ab17-6104b5702638.jpg',56,0);
insert into image values(null,'5518b405-819b-4e23-8d3e-35cd4edf7855.jpg','105d8065-e94b-47af-8eba-c3c7a64b8b6b.jpg','1c6ce5ce-7006-4cd0-8be1-84991e3d0ec8.jpg','fa515b22-5cea-4838-bd65-c05db6bc3516.jpg','70299bba-da9e-47c5-ae33-2936f99bb9fc.jpg',57,0);
insert into image values(null,'439d64e1-8b21-4546-bc7b-c757a7450147.jpg','1673ace4-fd24-441d-ac24-6c81cbb8f8d4.jpg','9a698109-1651-4633-abb5-5f6b7da17a5a.jpg','e70fdd0c-4c23-4044-b9f7-e959893ddd2f.jpg','ecfb5e9b-63bf-4c1d-b869-db0c0ad88885.jpg',58,0);

