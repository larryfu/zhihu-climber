var industrydata = {
    "10": "计算机/互联网/通信/电子",
    "11": "会计/金融/银行/保险",
    "12": "贸易/消费/制造/营运",
    "13": "制药/医疗",
    "14": "广告/媒体",
    "15": "房地产/建筑",
    "16": "专业服务/教育/培训",
    "17": "服务业",
    "18": "物流/运输",
    "19": "能源/原材料",
    "20": "政府/非赢利机构/其他",
    "1001": "计算机软件",
    "1002": "电子技术/半导体/集成电路",
    "1103": "金融/投资/证券",
    "1204": "贸易/进出口",
    "1205": "快速消费品(食品/饮料/化妆品)",
    "1206": "服装/纺织/皮革",
    "1607": "专业服务(咨询/人力资源/法律/财会)",
    "1308": "制药/生物工程",
    "1509": "建筑/建材/工程",
    "1710": "餐饮业",
    "1411": "广告/会展/公关",
    "1412": "媒体/出版/影视/媒体传播",
    "1213": "机械/设备/重工",
    "1414": "印刷/包装/造纸",
    "1915": "采掘业/冶炼",
    "1716": "娱乐/休闲/体育",
    "1617": "法律",
    "1918": "石油/化工/",
    "1919": "矿产/地质",
    "2020": "环保",
    "1621": "交通/运输",
    "1822": "物流/仓储",
    "1223": "批发/零售",
    "1624": "教育/培训院校",
    "1625": "学术/科研",
    "1526": "房地产",
    "1727": "生活服务",
    "2028": "政府/公共事业",
    "2029": "农/林/牧/渔",
    "1030": "通信/电信/网络设备",
    "1031": "互联网/电子商务",
    "1232": "汽车及零配件",
    "1633": "中介服务",
    "1034": "仪器仪表/工业自动化",
    "1935": "电气/电力/水利",
    "1036": "计算机硬件",
    "1037": "计算机服务(系统/数据服务/维修)",
    "1038": "通信/电信运营/增值服务",
    "1039": "网络游戏",
    "1140": "会计/审计",
    "1141": "银行",
    "1142": "保险",
    "1243": "玩具/礼品",
    "1244": "家具/家电",
    "1245": "办公用品及设备",
    "1346": "医疗/护理/卫生",
    "1347": "医疗设备/器械",
    "1448": "公关/市场推广/会展",
    "1449": "影视/媒体/艺术/文化传播",
    "1550": "家居/室内设计/装潢",
    "1551": "物业管理/商业中心",
    "1652": "检测/认证",
    "1753": "酒店/餐饮",
    "1754": "旅游度假",
    "1755": "美容/保健",
    "1856": "航天/航空",
    "1957": "原材料和加工",
    "2058": "非盈利机构",
    "2059": "跨领域经营",
    "1660": "外包服务",
    "1261": "奢侈品/收藏品/工艺品/珠宝",
    "1962": "新能源",
    "1163": "信托/担保/拍卖/典当",
    "1664": "租赁服务",
    "2065": "其他"
};

var industry_data = {
    "10": "农，林，牧，渔业",
    "11": "采矿业",
    "12": "制造业",
    "13": "电力，热力，燃气及水生产和供应业",
    "14": "建筑业",
    "15": "批发和零售业",
    "16": "交通运输，仓储和邮政业",
    "17": "住宿和餐饮业",
    "18": "信息传输，软件和信息技术服务业",
    "19": "金融业",
    "20": "房地产业",
    "21": "租赁和商务服务业",
    "22": "科学研究和技术服务业",
    "23": "水利，环境和公共设施管理业",
    "24": "居民服务，修理和其他服务业",
    "25": "教育",
    "26": "卫生和社会工作",
    "27": "文化，体育和娱乐业",
    "28": "公共管理，社会保障和社会组织",
    "29": "国际组织",
    "1001": "农业",
    "1002": "畜牧业",
    "1003": "渔业",
    "1004": "农，林，牧，渔服务业",
    "1005": "林业",
    "1101": "有色金属矿采选业",
    "1102": "石油和天然气开采业",
    "1103": "其他采矿业",
    "1104": "煤炭开采和洗选业",
    "1105": "开采辅助活动",
    "1106": "非金属矿采选业",
    "1107": "黑色金属矿采选业",
    "1201": "黑色金属冶炼和压延加工业",
    "1202": "汽车制造业",
    "1203": "橡胶和塑料制品业",
    "1204": "其他制造业",
    "1205": "通用设备制造业",
    "1206": "计算机，通信和其他电子设备制造业",
    "1207": "废弃资源综合利用业",
    "1208": "食品制造业",
    "1209": "皮革，毛皮，羽毛及其制品和制鞋业",
    "1210": "纺织业",
    "1211": "造纸和纸制品业",
    "1212": "文教，工美，体育和娱乐用品制造业",
    "1213": "化学纤维制造业",
    "1214": "金属制品业",
    "1215": "电气机械和器材制造业",
    "1216": "烟草制品业",
    "1217": "家具制造业",
    "1218": "医药制造业",
    "1219": "非金属矿物制品业",
    "1220": "农副食品加工业",
    "1221": "专用设备制造业",
    "1222": "纺织服装，服饰业",
    "1223": "印刷和记录媒介复制业",
    "1224": "仪器仪表制造业",
    "1225": "化学原料和化学制品制造业",
    "1226": "金属制品，机械和设备修理业",
    "1227": "有色金属冶炼和压延加工业",
    "1228": "酒，饮料和精制茶制造业",
    "1229": "木材加工和木，竹，藤，棕，草制品业",
    "1230": "铁路，船舶，航空航天和其他运输设备制造业",
    "1231": "石油加工，炼焦和核燃料加工业",
    "1301": "电力，热力生产和供应业",
    "1302": "水的生产和供应业",
    "1303": "燃气生产和供应业",
    "1401": "建筑安装业",
    "1402": "房屋建筑业",
    "1403": "土木工程建筑业",
    "1404": "建筑装饰和其他建筑业",
    "1501": "零售业",
    "1502": "批发业",
    "1601": "道路运输业",
    "1602": "仓储业",
    "1603": "管道运输业",
    "1604": "航空运输业",
    "1605": "邮政业",
    "1606": "铁路运输业",
    "1607": "装卸搬运和运输代理业",
    "1608": "水上运输业",
    "1701": "餐饮业",
    "1702": "住宿业",
    "1801": "互联网和相关服务",
    "1802": "电信，广播电视和卫星传输服务",
    "1803": "软件和信息技术服务业",
    "1901": "保险业",
    "1902": "货币金融服务",
    "1903": "其他金融业",
    "1904": "资本市场服务",
    "2001": "房地产业",
    "2101": "商务服务业",
    "2102": "租赁业",
    "2201": "研究和试验发展",
    "2202": "专业技术服务业",
    "2203": "科技推广和应用服务业",
    "2301": "公共设施管理业",
    "2302": "生态保护和环境治理业",
    "2303": "水利管理业",
    "2401": "机动车，电子产品和日用产品修理业",
    "2402": "居民服务业",
    "2403": "其他服务业",
    "2501": "教育",
    "2601": "卫生",
    "2602": "社会工作",
    "2701": "新闻和出版业",
    "2702": "体育",
    "2703": "文化艺术业",
    "2704": "娱乐业",
    "2705": "广播，电视，电影和影视录音制作业",
    "2801": "中国共产党机关",
    "2802": "群众团体，社会团体和其他成员组织",
    "2803": "人民政协，民主党派",
    "2804": "国家机构",
    "2805": "社会保障",
    "2806": "基层群众自治组织",
    "2901": "国际组织"
};