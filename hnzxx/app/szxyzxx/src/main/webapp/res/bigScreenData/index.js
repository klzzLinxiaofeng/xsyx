const baseurl = "https://www.dglbxsyx.net";
//http://www.dglbxsyx.net   https://www.dg.zykjedu.cn
var myMixin= {
    el: '#app',
    data: {
		healthyToday: {
			tooltip: {
				trigger: 'axis'
			},
			radar: [
				{
					indicator: [
						{text: '品牌', max: 100},
						{text: '内容', max: 100},
						{text: '可用性', max: 100},
						{text: '功能', max: 100},
						{text: '可用性', max: 100},
						{text: '功能', max: 100}
					],
					name: {
						formatter: '{value}',
						textStyle: {
							color: '#fff' // 文字颜色
						}
					},
				},
			],
			series: [
				{
					type: 'radar',
					tooltip: {
						trigger: 'item',
						color: '#fff',
					},
					areaStyle: {},
					data: [
						{
							value: [60, 73, 85,20,49, 40],
							name: '今日健康状态分布',
						}
					],
				},
			]
		},
		attendanceToday: {
			tooltip: {
				trigger: 'axis'
			},
			radar: [
				{
					indicator: [
						{text: '品牌', max: 100},
						{text: '内容', max: 100},
						{text: '可用性', max: 100},
						{text: '功能', max: 100},
						{text: '可用性', max: 100},
						{text: '功能', max: 100}
					],
					name: {
						formatter: '{value}',
						textStyle: {
							color: '#fff' // 文字颜色
						}
					},
				},
			],
			series: [
				{
					type: 'radar',
					tooltip: {
						trigger: 'item',
						color: '#fff',
					},
					areaStyle: {},
					data: [
						{
							value: [60,73,85,20,49,40],
							name: '今日年级出勤率分布',
						}
					],
				},
			]
		},
		courseOption: {
			grid:{
				top: '20',
				left:'0%',
				right:'10%',
				bottom:'10',
				containLabel:true
			},
			 xAxis: {
				type: 'category',
				boundaryGap: false,
				data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
			},
			yAxis: {
				type: 'value'
			},
			series: [{
				data: [820, 932, 901, 934, 1290, 1330, 1320],
				type: 'line',
				areaStyle: {},
				symbol: 'circle',     //设定为实心点
				symbolSize: 10,   //设定实心点的大小
				 itemStyle:{
					 borderRadius: [20],
						color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: '#2ba3f1'
					}, {
						offset: 1,
						color: '#28feed'
					}]),
				},
			}]
		},
		campusHealth: {
			grid:{
				top: '20',
				left:'0%',
				right:'10%',
				bottom:'10',
				containLabel:true
			},
			 xAxis: {
				type: 'category',
				boundaryGap: false,
				data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
			},
			yAxis: {
				type: 'value'
			},
			series: [{
				data: [820, 932, 901, 934, 1290, 1330, 1320],
				type: 'line',
				areaStyle: {},
				symbol: 'circle',     //设定为实心点
				symbolSize: 10,   //设定实心点的大小
				 itemStyle:{
					 borderRadius: [20],
						color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: '#2ba3f1'
					}, {
						offset: 1,
						color: '#28feed'
					}]),
				},
			}]
		},
		gradeHealthy: {
			tooltip: {
				show: true
			},
			 grid:{
				top: '20',
				left:'0%',
				right:'10%',
				bottom:'10',
				containLabel:true
			},
			xAxis: {
				type: 'value',
				boundaryGap: [0, 0.01]
			},
			yAxis: {
				type: 'category',
				left: "38%",
				data: ['巴西', '印尼', '美国', '印度', '中国']
			},
			series: [{
				data: [120, 200, 150, 80, 70, 110],
				type: 'bar',
				barWidth: "40%",
			   
				itemStyle:{
					 borderRadius: [20],
						color:new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
						offset: 0,
						color: '#28feed'
					}, {
						offset: 1,
						color: '#2ba3f1'
					}]),
				},
			   label: {
					show: true, 
					position: 'right',
					textStyle: { 
						color: '#fff',
						fontSize: 16
					}
				}
			}]
		},
		studentGrade: {
			tooltip: {
				show: true
			},
			xAxis: {
				type: 'value',
				boundaryGap: [0, 0.01]
			},
			grid:{
				top: '20',
				left:'0%',
				right:'10%',
				bottom:'10',
				containLabel:true
			},
			yAxis: {
				type: 'category',
				left: "40%",
				data: ['巴西', '印尼', '美国', '印度', '中国']
			},
			series: [{
				data: [120, 200, 150, 80, 70, 110],
				type: 'bar',
				barWidth: "38%",
				itemStyle:{
					 borderRadius: [20],
						color:new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
						offset: 0,
						color: '#28feed'
					}, {
						offset: 1,
						color: '#2ba3f1'
					}]),
				},
			   label: {
					show: true, 
					position: 'right',
					textStyle: { 
						color: '#fff',
						fontSize: 16
					}
				}
			}]
		},
		appDayNumOption: {
			tooltip: {
				show: true
			},
			xAxis: {
				type: 'value',
				boundaryGap: [0, 0.01]
			},
			grid:{
				top: '20',
				left:'0%',
				right:'10%',
				bottom:'10',
				containLabel:true
			},
			yAxis: {
				type: 'category',
				data: ['巴西', '印尼', '美国', '世界人口(万)']
			},
			series: [{
				data: [120, 200, 70, 110],
				type: 'bar',
				barWidth: "38%",
				left:0,
				itemStyle:{
						color:new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
						offset: 0,
						color: '#248ff7'
					}, {
						offset: 1,
						color: 'rgba(22,75,247,0.1)'
					}]),
				},
			   label: {
					show: true, 
					position: 'right',
					textStyle: { 
						color: '#fff',
						fontSize: 16
					}
				}
			}]
		},
		campusOption: {
			tooltip: {
				trigger: 'item',
				left: 20,
				formatter: '{a} <br/>{b}: {c} ({d}%)'
			},
			legend: {
				orient: 'vertical',
				left: "10%",
				top: "22%",
				itemGap: 12,
				textStyle: {
				    color: '#fff'
				},
			},
			series: [
				{
					name: '校园出勤状况',
					type: 'pie',
					radius: ['50%', '70%'],
					left: -80,
					right: "-40%",
					avoidLabelOverlap: false,
					label: {
						show: false,
						position: 'center'
					},
					emphasis: {
						label: {
							show: true,
							fontSize: '18',
							top: "90",
							fontWeight: 'bold',
							color: "#fff"
						}
					},
					labelLine: {
						show: false
					},
					data: [
						{value: 1048, name: '搜索引擎'},
						{value: 735, name: '直接访问'},
						{value: 580, name: '邮件营销'},
						{value: 484, name: '联盟广告'},
						{value: 300, name: '视频广告'}
					]
				}
			]
		},
		approveOption: {
			tooltip: {
				trigger: 'item'
			},
			legend: {
				orient: 'vertical',
				right: "20%",
				top: "22%",
				itemGap: 12,
				textStyle: {
				    color: '#fff'
				},
			},
			series: [
				{
					name: '本月审批公布',
					type: 'pie',
					radius: ['50%', '70%'],
					left: "-40%",
					avoidLabelOverlap: false,
					label: {
						show: false,
						position: 'center'
					},
					labelLine: {
						show: false
					},
					data: [
						{value: 1048, name: '搜索引擎'},
						{value: 735, name: '直接访问'},
						{value: 580, name: '邮件营销'},
						{value: 484, name: '联盟广告'},
						{value: 300, name: '视频广告'}
					]
				}
			]
		},
        titleOption: {
			tooltip: {
				trigger: 'item'
			},
			series: [
				{
					name: '教师职称分布',
					type: 'pie',
					radius: ['50%', '75%'],
					data: [
						{value: 1048, name: '搜索引擎'},
						{value: 735, name: '直接访问'},
						{value: 580, name: '邮件营销'},
						{value: 484, name: '联盟广告'},
						{value: 300, name: '视频广告'}
					],
					label: {
						normal: {
							show: true,
							formatter: "{b}  {c}人",
							textStyle: {
								fontSize: 13,
								color: "#fff"
							},
						},
					},
					labelLine: {
						normal: {
							show: true,
							lineStyle: {
								color: '#fff'
							},
							smooth: 0.2,
						}
					},
					emphasis: {
						itemStyle: {
						},
						areaStyle: {
							color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: 'rgba(232, 82, 58, 1)'
							}, {
								offset: 1,
								color: 'rgba(232, 82, 58,0.1)'
							}])
						},
					}
				},
				
			]
		},
		educationOption: {
			tooltip: {
				trigger: 'item'
			},
			series: [
				{
					name: '教师学历分布',
					type: 'pie',
					radius: ['50%', '75%'],
					data: [
						{value: 1048, name: '搜索引擎'},
						{value: 735, name: '直接访问'},
						{value: 580, name: '邮件营销'},
						{value: 484, name: '联盟广告'},
						{value: 300, name: '视频广告'}
					],
					label: {
						normal: {
							show: true,
							formatter: "{b}  {c}人",
							textStyle: {
								fontSize: 13,
								color: "#fff"
							},
						},
					},
					labelLine: {
						normal: {
							show: true,
							lineStyle: {
								color: '#fff'
							},
							smooth: 0.2,
						}
					},
					emphasis: {
						itemStyle: {
						},
						areaStyle: {
							color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
								offset: 0,
								color: 'rgba(232, 82, 58, 1)'
							}, {
								offset: 1,
								color: 'rgba(232, 82, 58,0.1)'
							}])
						},
					}
				},
				
			]
		},
		countNum: {"teacherLeaveCount":0,"studentLeaveCount":0},
		healthNum: 0,
		shidaoNum: 0,
		teacherInfo: {
			jzgCount: 0,
			stuCount: 0,
			teacherCount: 0,
		},
		stulist: [],
		tealist: [],
		
    },
    methods: {
		//获取班级数,及对应科目的班级平均分
		async getGradeChengJi(type) {
			let date={
				schoolYear:2021,
				schoolTrem:"215-2021-2",
				type:2
			};
			var res = await that.request(baseurl, 'GET','/JiaoWu/teamSubjectAvg', date);
			res = JSON.parse(res);
			if (res.info == "success") {
				if (res.responseData) {
					console.log(res.responseData);
					this.avgScoreList = res.responseData;
				}else{
					alert("失败");
				}
			}else{
				console.log(res.responseData);
			}
		},
		//年级成绩趋势
		async getGradeQuShi() {
			let date={
				schoolYear:2021,
				schoolTrem:"215-2021-2"
			};
			var res = await that.request(baseurl, 'GET','/JiaoWu/GradeSubjectAvg', date);
			res = JSON.parse(res);
			if (res.info == "success") {
				if (res.responseData) {
					console.log(res.responseData);
					this.avgScoreGradeList = res.responseData;
				}else{
					alert("失败");
				}
			}else{
				console.log(res.responseData);
			}
		},
		async getWeekStar() { //周明星学生
		    var res = await that.request(baseurl, 'GET', '/general/weekStar', {});
		    res = JSON.parse(res)
			console.log(res)
			this.stulist = res
		},
		async getTeacherStar() { // 优秀任课教师
		    var res = await that.request(baseurl, 'GET', '/huojiangFenXin/findByNumBigPing', {});
		    res = JSON.parse(res)
			console.log(res)
			this.tealist = res
		},
		async getTeacherStuCount() { // 学生数、教师数、教职工数
		    var res = await that.request(baseurl, 'GET', '/general/teacherStuCount', {});
		    res = JSON.parse(res)
			console.log(res)
			this.teacherInfo = Object.assign({}, this.teacherInfo,res)
		},
		async getleaveCount() { // 今日学生、老师请假
		    var res = await that.request(baseurl, 'GET', '/general/leaveCount', {});
		    res = JSON.parse(res)
			this.countNum = Object.assign({}, this.countNum,res)
		},
		async getApproveCount(type) { // 今日审批、本月审批 type=0:今日审批，type=1:本月审批
			var params = {type: type}
		    var res = await that.request(baseurl, 'GET', '/general/approveCount', params);
		    res = JSON.parse(res)
			// console.log(res)
			if(type==0) {
				this.appDayNumOption.yAxis.data = []
				this.appDayNumOption.series[0].data = []
				var namelist = []
				var valuelist = []
				res.forEach((item,index)=> {
					namelist.push(item.name)
					valuelist.push(item.value)
				})
				this.appDayNumOption.yAxis.data = namelist
				this.appDayNumOption.series[0].data = valuelist
				this.getEchartData2('appDayNumOption', this.appDayNumOption);//今日审批数
			}
			if(type==1) {
				this.approveOption.series[0].data = []
				var namelist = []
				res.forEach((item,index)=> {
					namelist.push({name: item.name+":  " + item.value + "%",value: item.value})
				})
				this.approveOption.series[0].data = namelist
				this.getEchartData2('approveOption', this.approveOption); //本月审批公布
			}
		},
		async getGradeStuCount() { // 学生年级分布
		    var res = await that.request(baseurl, 'GET', '/general/gradeStuCount', {});
		    res = JSON.parse(res)
			// console.log(res)
			this.studentGrade.yAxis.data = []
			this.studentGrade.series[0].data = []
			var namelist = []
			var valuelist = []
			res.forEach((item,index)=> {
				namelist.push(item.name)
				valuelist.push(item.value)
			})
			this.studentGrade.yAxis.data = namelist
			this.studentGrade.series[0].data = valuelist
			this.getEchartData2('studentGrade', this.studentGrade);//学生年级分布
		},

		async getTeacherTitleCount() { // 教师职称分布
		    var res = await that.request(baseurl, 'GET', '/general/teacherTitleCount', {});
		    res = JSON.parse(res)
			// console.log(res)
			this.titleOption.series[0].data = []
			var namelist = []
			res.forEach((item,index)=> {
				namelist.push({name: item.name,value: item.value})
			})
			this.titleOption.series[0].data = namelist
			this.getEchartData2('teacherTitle', this.titleOption);//教师职称分布
		},
		async getTeacherEducationCount() { // 教师学历分布
		    var res = await that.request(baseurl, 'GET', '/general/teacherEducationCount', {});
		    res = JSON.parse(res)
			// console.log(res)
			this.educationOption.series[0].data = []
			var namelist = []
			res.forEach((item,index)=> {
				namelist.push({name: item.name,value: item.value})
			})
			this.educationOption.series[0].data = namelist
			this.getEchartData2('education', this.educationOption);//教师学历分布
		},
		async getHealthGradeCount() { // 今日健康上报数
		    var res = await that.request(baseurl, 'GET', '/general/healthGradeCount', {});
		    res = JSON.parse(res)
			// console.log(res)
			this.gradeHealthy.yAxis.data = []
			this.gradeHealthy.series[0].data = []
			var namelist = []
			var valuelist = []
			res.forEach((item,index)=> {
				namelist.push(item.name)
				valuelist.push(item.value)
			})
			this.gradeHealthy.yAxis.data = namelist
			this.gradeHealthy.series[0].data = valuelist
			this.getEchartData2('gradeHealthy', this.gradeHealthy);//今日年级健康上报数
		},
		async getHealthTypeCount() { // 今日健康状态分布
		    var res = await that.request(baseurl, 'GET', '/general/healthTypeCount', {});
		    res = JSON.parse(res)
			// console.log(res)
			this.healthyToday.radar[0].indicator = []
			this.healthyToday.series[0].data[0].value = []
			var namelist = []
			var valuelist = []
			res.forEach((item,index)=> {
				namelist.push({text: item.name,max: 100})
				valuelist.push(item.value)
			})
			this.healthyToday.radar[0].indicator = namelist
			this.healthyToday.series[0].data[0].value = valuelist
			// console.log(this.healthyToday)
			this.getEchartData2('healthyToday', this.healthyToday);//今日健康状态分布
		},
		async getHealthDateCount() { // 校园健康上报趋势
		    var res = await that.request(baseurl, 'GET', '/general/healthDateCount', {});
		    res = JSON.parse(res)
			var datatime = new Date()
			// console.log()
			datatime = this.returnData(datatime);
			// console.log(res)
			this.campusHealth.xAxis.data = []
			this.campusHealth.series[0].data = []
			var namelist = []
			var valuelist = []
			res.forEach((item,index)=> {
				namelist.push(item.name)
				valuelist.push(item.value);
				if(item.name==datatime) {
					this.healthNum = item.value;
				}
			})

			this.campusHealth.xAxis.data = namelist
			this.campusHealth.series[0].data = valuelist
			
			this.getEchartData2('campusHealth', this.campusHealth);//校园健康上报趋势
		},
		async getKaoQin(){
			var res = await that.request(baseurl, 'GET', '/kaoqin/AllKaoQin', {});
			res = JSON.parse(res);
			var list=["正常","迟到","早退","请假"]
			var xianyuan=res['quanxiaoKaoqin'];
			var gradeList=res['gradeIdKaoQin'];
			var list2 =[];
			this.shidaoNum=xianyuan[0];
			for(var i=0;i<list.length;i++){
				list2.push({name: list[i],title: '测试',value:xianyuan[i]})
			}
			var namelist = [{text: '一年级',max: 100},
				{text: '二年级',max: 100},
				{text: '三年级',max: 100},
				{text:  '四年级',max: 100},
				{text: '五年级',max: 100},
				{text: '六年级',max: 100}
			]
			this.attendanceToday.radar[0].indicator = []
			this.attendanceToday.series[0].data[0].value = []

			this.attendanceToday.radar[0].indicator = namelist
			// console.log(res)
			this.attendanceToday.series[0].data[0].value = gradeList
			this.campusOption.series[0].data = list2;

			this.getEchartData2('campusOption', this.campusOption);//校园出勤状况
			this.getEchartData2('attendanceToday', this.attendanceToday);//今日年级出勤率分布

		},

		/*async getXynjcqCount() { // 校园出勤、年级出勤
		    var res = await that.request(baseurl, 'GET', '/general/xynjcqCount', {});
		    res = JSON.parse(res)
			// console.log(2323,res)
			if(res.xycq && res.xycq.length>0) {
				// this.healthNum = res[0].value;
				var shidao = 0;
				var num = 0;
				 res.xycq.forEach((x,idx)=> {
					num += x.value;
					if(x.name=="实到"){
						shidao = x.value;
					}
				})
				// console.log(num)
				var total = shidao/num*100;
				if(total==0) {
					this.shidaoNum = 0;
				}else {
					this.shidaoNum = (total).toFixed(1);
				}
				
			}
			this.attendanceToday.radar[0].indicator = []
			this.attendanceToday.series[0].data[0].value = []
			this.campusOption.series[0].data = []
			
			var namelist = []
			var valuelist = []
			res.njcq.forEach((item,index)=> {
				namelist.push({text: item.name,max: 100})
				valuelist.push(item.value)
			})
			this.attendanceToday.radar[0].indicator = namelist

			var list = [12,13,32,1,5,8]
			// console.log(res)
			this.attendanceToday.series[0].data[0].value = list

			res.xycq.forEach((i,idx)=> {
				list.push({name: i.name,title: '测试',value: i.value})
			})

			this.campusOption.series[0].data = list
			
			this.getEchartData2('campusOption', this.campusOption);//校园出勤状况
			this.getEchartData2('attendanceToday', this.attendanceToday);//今日年级出勤率分布
			
		},*/
		async getTeacherAtten() { // 教师到课率趋势
		    var res = await that.request(baseurl, 'GET', '/general/getTeacherAtten ', {});
		    res = JSON.parse(res)
			// console.log(res)
			this.courseOption.xAxis.data = []
			this.courseOption.series[0].data = []
			var namelist = []
			var valuelist = []
			res.forEach((item,index)=> {
				namelist.push(item.name)
				valuelist.push(item.value)
			})
			this.courseOption.xAxis.data = namelist
			this.courseOption.series[0].data = valuelist
			
			this.getEchartData2('courseOption', this.courseOption);//教师到课率趋势
		},
		
		
		
       getEchartData2(name, option) {
           const chart = this.$refs[name]
           if (chart) {
               const myChart = echarts.init(chart)
               var option = option;
               myChart.setOption(option)
               window.addEventListener("resize", function() {
                   myChart.resize()
               })
           }
           this.$on('hook:destroyed', () => {
               window.removeEventListener("resize", function() {
                   myChart.resize();
               });
           })
       },
	   
	   
	   
	   returnData: function(val) {
			let year = new Date(val).getFullYear()
			let month = new Date(val).getMonth() + 1
			let day = new Date(val).getDate()
			if (month >= 1 && month <= 9) { month = `0${month}` }
			if (day >= 1 && day <= 9) { day = `0${day}` }
			var data = `${year}-${month}-${day}`
			return data
	   },
    },
};

var app = new Vue({
    el: '#app',
	mixins: [myMixin],
    data: {
        baseurl: "https://www.dglbxsyx.net",
        // baseurl: "http://192.168.1.14:80",
        canteenUrl: "http://10.170.76.29:8090", //食堂正式ip
        // canteenUrl: "http://139.159.242.158:8090", //測試
        titleList: ["总务大数据", "行政大数据", "教务大数据", "德育大数据", "图书馆大数据"],
        titleIndex: 0,
        SchoolDaySumMoney: 0, //总消费金额
        SumAmout: 0, //当日总充值金额
		//日消费趋势图
        trendOption: {
            xAxis: {
                type: 'category',
                data: ['2020-12-28', '2020-12-29', '2020-12-30', '2020-12-31', '2020-01-04', '2020-01-05']
                    // data: []
            },
            yAxis: {
                type: 'value',
                splitLine: {
                    show: false
                },
            },
            series: [{
                data: [690, 710, 700, 850, 750, 1330, 1200],
                // data: [],
                type: 'line',
                itemStyle: {
                    // normal: { label: { show: true } },//折线显示数值
                    color: 'rgba(232, 82, 58, 1)'
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(232, 82, 58, 1)'
                    }, {
                        offset: 1,
                        color: 'rgba(232, 82, 58,0.1)'
                    }])
                },
            }]
        },
		//日充值趋势图
        rechargeOption: {
            xAxis: {
                type: 'category',
                // data: ['2020-12-28', '2020-12-29', '2020-12-30', '2020-12-31', '2020-01-04', '2020-01-05']
                data: []
            },
            yAxis: {
                type: 'value',
                splitLine: {
                    show: false
                },
            },
            series: [{
                // data: [820, 932, 901, 934, 1290, 1330, 1320],
                data: [],
                type: 'line',
                itemStyle: {
                    color: 'rgba(150, 249, 254, 1)'
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(150, 249, 254, 1)'
                    }, {
                        offset: 1,
                        color: 'rgba(150, 249, 254,0.1)'
                    }])
                },
            }]
        },
		//家校反馈趋势图
        feedbackOption: {
            xAxis: {
                type: 'category',
                data: ['2020-12-28', '2020-12-29', '2020-12-30', '2020-12-31', '2020-01-04', '2020-01-05']
                    // data: []
            },
            yAxis: {
                type: 'value',
                splitLine: {
                    show: false
                },
            },
            series: [{
                data: [2, 5, 3, 4, 6, 8, 7],
                // data: [],
                type: 'line',
                itemStyle: {
                    color: 'rgba(39, 126, 241, 1)'
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(39, 126, 241, 1)'
                    }, {
                        offset: 1,
                        color: 'rgba(39, 126, 241,0.1)'
                    }])
                },
            }]
        },
		avgScoreList:[], //班级科目平均分
		avgScoreGradeList:[],//年级成绩趋势
        monthList: [],
        replaceCardTotal: 0, //补卡人数
        stuTotal: 0,
        teacherTotal: 0,
        notCardTotal: 0, //报修未处理
        feedbackTotal: 0, //家校反馈统计
        HotelOption: {
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                right: 15,
                bottom: 20,
                padding: [5, 10, 5, 20, ],
                // data: ['食堂一楼', '食堂2楼外堂', '食堂2楼里堂'],
                data: [],
                textStyle: {
                    color: '#fff'
                },
                formatter: ""
                    // formatter: function(name) {
                    // 	var value = hotel_nameData.filter(v => v.name == name)[0].value
                    // 	var all = 0;
                    // 	hotel_nameData.forEach(function(item) {
                    // 		all = all + item.value
                    // 	})
                    // 	var result = (value / all * 100).toFixed(2) + '%'
                    // 	return name + ' : ' + result;
                    // },
            },
            series: [{
                name: '访问来源',
                type: 'pie',
                left: '0%',
                right: '30%',
                radius: ['40%', '60%'],
                avoidLabelOverlap: false,
                silent: false,
                animation: false,
                label: {
                    normal: {
                        show: true,
                        formatter: "{d}% ",
                        textStyle: {
                            fontSize: 13,
                            color: "#fff"
                        },
                    },
                },
                labelLine: {
                    normal: {
                        show: true,
                        lineStyle: {
                            color: '#fff'
                        },
                        smooth: 0.2,
                    }
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '20',
                        fontWeight: 'bold'
                    }
                },
                data: []
                    // data: [{
                    // 		value: 335,
                    // 		name: '食堂一楼'
                    // 	},
                    // 	{
                    // 		value: 310,
                    // 		name: '食堂2楼外堂'
                    // 	},
                    // 	{
                    // 		value: 234,
                    // 		name: '食堂2楼里堂'
                    // 	},
                    // ]
            }]
        },
        repairOption: { //报修统计
            xAxis: {
                type: 'category',
                // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                data: [],
                splitLine: {
                    show: false
                }
            },
            yAxis: {
                type: 'value',
                splitLine: {
                    show: false
                }
            },
            series: [{
                // data: [120, 200, 150, 80, 70, 110, 130],
                data: [],
                type: 'bar',
                showBackground: true,
                itemStyle: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1, [
                            { offset: 0, color: '#83bff6' },
                            { offset: 0.5, color: '#188df0' },
                            { offset: 1, color: '#188df0' }
                        ]
                    )
                },
            }]
        },
        currady: formatTime(new Date()).day.substring(0, 7),
        // currady: [getBeforeDate(30), formatTime(new Date()).day],
        educationalList: [111, 222], //班级列表
        educationalIdx: 0, //
        grade: "一年级",
        grade1: "二年级",
		//年级平均成绩趋势
        averageOption: {
            title: {
                text: '年级平均成绩趋势',
                textStyle: {
                    fontWeight: 'normal',
                    color: '#fff' //标题颜色
                },
            },
            legend: {
                orient: 'horizontal',
                x: 'right', //可设定图例在左、右、居中
                y: 'top', //可设定图例在上、下、居中
                padding: [10, 40, 0, 0],
                data: ['语文', '数学','英语'],
                textStyle: { //图例文字的样式
                    color: '#fff',
                    fontSize: 16
                },
            },
            xAxis: {
                type: 'category',
                data: ['期中', '期末']
                    // data: []
            },
            yAxis: {
                type: 'value',
                splitLine: {
                    show: false
                },
            },
            series: [{
                data: [0,0],
                // data: [],
                name: '语文',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        color: 'rgba(255, 192, 0, 1)' //  给折线添加颜色 
                    },
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 0.1, [{
                        offset: 0,
                        color: 'rgba(255, 192, 0, 0.5)'
                    }, {
                        offset: 1,
                        color: 'rgba(255, 192, 0, 0.1)'
                    }])
                },
            }, {
                data: [0,0],
                name: '数学',
                type: 'line',
                smooth: true,
                itemStyle: {
                    color: 'rgba(39, 126, 241, 1)'
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 0.1, [{
                        offset: 0,
                        color: 'rgba(39, 126, 241, 0.5)'
                    }, {
                        offset: 1,
                        color: 'rgba(39, 126, 241,0.1)'
                    }])
                },
            }, {
                data: [0,0],
                name: '英语',
                type: 'line',
                smooth: true,
                itemStyle: {
                    color: 'rgba(40, 255, 237, 1)'
                },
                areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 0.1, [{
                        offset: 0,
                        color: 'rgba(40, 255, 237, 0.5)'
                    }, {
                        offset: 1,
                        color: 'rgba(40, 255, 237, 0.1)'
                    }])
                },
            }]
        },
		averageOption1: {
			title: {
				text: '年级平均成绩趋势',
				textStyle: {
					fontWeight: 'normal',
					color: '#fff' //标题颜色
				},
			},
			legend: {
				orient: 'horizontal',
				x: 'right', //可设定图例在左、右、居中
				y: 'top', //可设定图例在上、下、居中
				padding: [10, 40, 0, 0],
				data: ['语文', '数学','英语'],
				textStyle: { //图例文字的样式
					color: '#fff',
					fontSize: 16
				},
			},
			xAxis: {
				type: 'category',
				data: ['期中', '期末']
				// data: []
			},
			yAxis: {
				type: 'value',
				splitLine: {
					show: false
				},
			},
			series: [{
				data: [0,0],
				// data: [],
				name: '语文',
				type: 'line',
				smooth: true,
				itemStyle: {
					normal: {
						color: 'rgba(255, 192, 0, 1)' //  给折线添加颜色
					},
				},
				areaStyle: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 0.1, [{
						offset: 0,
						color: 'rgba(255, 192, 0, 0.5)'
					}, {
						offset: 1,
						color: 'rgba(255, 192, 0, 0.1)'
					}])
				},
			}, {
				data: [0,0],
				name: '数学',
				type: 'line',
				smooth: true,
				itemStyle: {
					color: 'rgba(39, 126, 241, 1)'
				},
				areaStyle: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 0.1, [{
						offset: 0,
						color: 'rgba(39, 126, 241, 0.5)'
					}, {
						offset: 1,
						color: 'rgba(39, 126, 241,0.1)'
					}])
				},
			}, {
				data: [0,0],
				name: '英语',
				type: 'line',
				smooth: true,
				itemStyle: {
					color: 'rgba(40, 255, 237, 1)'
				},
				areaStyle: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 0.1, [{
						offset: 0,
						color: 'rgba(40, 255, 237, 0.5)'
					}, {
						offset: 1,
						color: 'rgba(40, 255, 237, 0.1)'
					}])
				},
			}]
		},
		//班级科目平均分
        averageRadarOption: {
            title: {
                text: '班级科目平均分',
                textStyle: {
                    fontWeight: 'normal',
                    color: '#fff' //标题颜色
                },
            },
            tooltip: {},
            legend: {
                orient: 'horizontal',
                x: 'right', //可设定图例在左、右、居中
                y: 'top', //可设定图例在上、下、居中
                padding: [10, 50, 0, 0],
               // data: ['语文', '数学'],
				data: [],
                textStyle: { //图例文字的样式
                    color: '#fff',
                    fontSize: 16
                },
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        backgroundColor: '#999',
                        borderRadius: 3,
                        padding: [3, 5]
                    }
                },
                indicator: [
                    /*{ name: '1班', max: 100 },
                    { name: '2班', max: 100 },
                    { name: '3班', max: 100 },
                    { name: '4班', max: 100 },
                    { name: '5班', max: 100 },
                    { name: '6班', max: 100 },
                    { name: '7班', max: 100 },
                    { name: '8班', max: 100 },*/
                ]
            },
            series: [{
                // name: '预算 vs 开销（Budget vs spending）',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: [/*{
                        value: [88, 76, 98, 69, 84, 95, 83, 95],
                        name: '语文'
                    },
                    {
                        value: [89, 92, 82, 83, 84, 86, 95, 66],
                        name: '数学'
                    },
                    {
                        value: [],
                        name: '英语'
                    }*/
                ]
            }]
        },
        averageRadarOption1: { //班级科目平均分
            title: {
                text: '班级科目平均分',
                textStyle: {
                    fontWeight: 'normal',
                    color: '#fff' //标题颜色
                },
            },
            tooltip: {},
            legend: {
                orient: 'horizontal',
                x: 'right', //可设定图例在左、右、居中
                y: 'top', //可设定图例在上、下、居中
                padding: [10, 50, 0, 0],
                data: [],
                textStyle: { //图例文字的样式
                    color: '#fff',
                    fontSize: 16
                },
            },
            radar: {
                // shape: 'circle',
                name: {
                    textStyle: {
                        color: '#fff',
                        backgroundColor: '#999',
                        borderRadius: 3,
                        padding: [3, 5]
                    }
                },
                indicator: [
                  /*  { name: '1班', max: 100 }*/
                ]
            },
            series: [{
                name: '预算 vs 开销（Budget vs spending）',
                type: 'radar',
                // areaStyle: {normal: {}},
                data: []
            }]
        },
        PassRateOption: { //合格率分布
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },

            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: []
            },
            series: [{
                // name: '2011年',
                type: 'bar',
                stack: '总量',
                label: {
                    show: true,
                    position: 'insideRight'
                },
                data: [72, 86, 78, 82, 96, 72]
            }, ]
        },

    },
    created() {
    	this.getGradeChengJi(2); //平均成绩
		this.getGradeQuShi();
        this.getSchoolDaySumMoney(); //总消费金额		
        this.getSumAmout(); //總充值	
        this.getReplaceCardTotal(); //补卡人数统计
        this.getFeedbackTotal(); //家校反馈统计
        this.getNotCardTotal(); //报修未处理
        this.getOnePassCardCount(); //一卡通统计
        var num = 6;

        do {
            var date1 = new Date();
            date1.setMonth(date1.getMonth() - num);
            var year1 = date1.getFullYear();
            var month1 = date1.getMonth() + 1;
            month1 = (month1 < 10 ? "0" + month1 : month1);
            let sDate = (year1.toString() + '-' + month1.toString());
            this.monthList.unshift(sDate)
            num--;
        } while (num >= 0);
    },
    mounted() {
        this.getAmountTrend(); // 日充值趋势图
        this.getSchoolDayHotelCodePoint(); //日消费区域分布
        this.getSchoolDaySumMoneyList(); //日消费趋势图
        this.getFeedbackMonthTotal(); //家校反馈月趋势图
        this.getrepairTotal(); //报修统计

        this.getEchartData('trend', this.trendOption);
        this.getEchartData('recharge', this.rechargeOption);
        this.getEchartData('feedback', this.feedbackOption);
        this.getEchartData('PassRate', this.PassRateOption); //合格率分布
        this.getEchartData('PassRate2', this.PassRateOption); //综合素质评分
		
		document.getElementById("app").style.display = "block";
		
		
		var setTime = setInterval(()=>{
			// console.log(32323);
			this.changeTitle(this.titleIndex)
		},1000 * 60 * 40)
		
		// clearInterval(setTime);
    },
    methods: {
        btn() {
            // 全屏
            var docElm = document.documentElement;
            if (docElm.webkitRequestFullScreen) {
                docElm.webkitRequestFullScreen();
            }
        },
        getEchartData(name, option) {
            const chart = this.$refs[name]
            if (chart) {
                const myChart = echarts.init(chart)
                var option = option;
                myChart.setOption(option)
                window.addEventListener("resize", function() {
                    myChart.resize()
                })
            }
            this.$on('hook:destroyed', () => {
                window.removeEventListener("resize", function() {
                    myChart.resize();
                });
            })
        },
        // 切换tab 
        changeTitle(index) {
            this.titleIndex = index;
			if(index==0) {
				this.getSchoolDaySumMoney(); //总消费金额
				this.getSumAmout(); //總充值	
				this.getReplaceCardTotal(); //补卡人数统计
				this.getFeedbackTotal(); //家校反馈统计
				this.getNotCardTotal(); //报修未处理
				this.getOnePassCardCount(); //一卡通统计
				// setInterval(() => {
				//     this.getSchoolDaySumMoney(); //总消费金额
				//     this.getSumAmout(); //總充值	
				//     this.getReplaceCardTotal(); //补卡人数统计
				//     this.getFeedbackTotal(); //家校反馈统计
				//     this.getNotCardTotal(); //报修未处理
				//     this.getOnePassCardCount(); //一卡通统计
				// }, 60000)
				// monthList
				var num = 6;
				
				do {
				    var date1 = new Date();
				    date1.setMonth(date1.getMonth() - num);
				    var year1 = date1.getFullYear();
				    var month1 = date1.getMonth() + 1;
				    month1 = (month1 < 10 ? "0" + month1 : month1);
				    let sDate = (year1.toString() + '-' + month1.toString());
				    this.monthList.unshift(sDate)
				    num--;
				} while (num >= 0);
			}
			if(index==1) {
				this.getleaveCount();//今日学生、老师请假
				this.getApproveCount(0);//今日审批
				this.getApproveCount(1);//本月审批
				this.getGradeStuCount();//学生年级分布
				this.getTeacherTitleCount();//教师职称分布
				this.getTeacherEducationCount();//教师学历分布
				this.getTeacherStuCount(); //学生数、教师数、教职工数
			}
			if(index==2) {
				this.getFindByTeamSubject();  //班级平均分
	/*			this.getEchartData('trend', this.trendOption);
				this.getEchartData('recharge', this.rechargeOption);
				this.getEchartData('feedback', this.feedbackOption);
				this.getEchartData('classAverage', this.averageOption); //年级成绩趋势 
				this.getEchartData('classAverage1', this.averageOption);
				alert("hhahah1");
				this.getFindByTeamSubject();  //班级平均分
				this.getEchartData('averageRadar', this.averageRadarOption); //班级平均分
				this.getEchartData('averageRadar1', this.averageRadarOption1);
				this.getEchartData('PassRate', this.PassRateOption); //合格率分布
				this.getEchartData('PassRate2', this.PassRateOption); //综合素质评分*/
			}
			if(index==3) {
				this.getKaoQin();
				//this.getXynjcqCount(); //校园出勤、年级出勤
				this.getTeacherAtten(); //教师到课率趋势
				this.getHealthGradeCount();//今日年级健康上报数
				this.getHealthTypeCount();//今日健康状态分布
				this.getHealthDateCount();//校园健康上报趋势
				this.getWeekStar(); // 周明星学生
				this.getTeacherStar(); //优秀任课教师
			}
        },
        toNext() { // 切换下一个
            // if (this.educationalIdx < this.educationalList.length - 1) {
            if (this.educationalIdx < 2) {
                this.educationalIdx = this.educationalIdx + 1;
                if (this.educationalIdx === 1) {
					this.grade = "三年级";
					this.grade1 = "四年级";
					for(let a=2;a<4;a++) {
						//三年级
						if (a === 2) {
							this.averageRadarOption.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption.series[0].data = this.avgScoreList[a]["dateValue"];
							this.getEchartData('averageRadar', this.averageRadarOption); //班级平均分

							//年级成绩趋势
							this.averageOption.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
							this.getEchartData('classAverage', this.averageOption);
						} else {
							this.averageRadarOption1.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption1.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption1.series[0].data = this.avgScoreList[a]["dateValue"];
							this.getEchartData('averageRadar1', this.averageRadarOption1);

							//年级成绩趋势
							this.averageOption1.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption1.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
							 //年级成绩趋势
							this.getEchartData('classAverage1', this.averageOption1);

						}
					}
                } else if (this.educationalIdx === 2) {
					this.grade = "五年级";
					this.grade1 = "六年级";
					for(let a=4;a<6;a++) {
						//五年级
						if (a === 4) {
							this.averageRadarOption.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption.series[0].data = this.avgScoreList[a]["dateValue"];
							//年级成绩趋势
							this.averageOption.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
							this.getEchartData('averageRadar', this.averageRadarOption);
							this.getEchartData('classAverage', this.averageOption);
						} else {
							this.averageRadarOption1.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption1.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption1.series[0].data = this.avgScoreList[a]["dateValue"];


							//年级成绩趋势
							this.averageOption1.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption1.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
							//班级平均分
							this.getEchartData('averageRadar1', this.averageRadarOption1);
							//年级成绩趋势
							this.getEchartData('classAverage1', this.averageOption1);
						}
					}

                }
            }
        },
        toLast() { //上一个
            if (this.educationalIdx > 0) {
                this.educationalIdx = this.educationalIdx - 1;
                if (this.educationalIdx === 1) {
					this.grade = "三年级";
					this.grade1 = "四年级";
					for(let a=2;a<4;a++) {
						if (a === 2) {
							this.averageRadarOption.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption.series[0].data = this.avgScoreList[a]["dateValue"];
							//年级成绩趋势
							this.averageOption.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
						} else {
							this.averageRadarOption1.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption1.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption1.series[0].data = this.avgScoreList[a]["dateValue"];

							//年级成绩趋势
							this.averageOption1.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption1.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
						}
					}
                    this.getEchartData('averageRadar', this.averageRadarOption); //班级平均分
                    this.getEchartData('averageRadar1', this.averageRadarOption1);
                    //年级成绩趋势 
                    this.getEchartData('classAverage', this.averageOption); //年级成绩趋势
                    this.getEchartData('classAverage1', this.averageOption1);

                } else if (this.educationalIdx === 0) {
					this.grade = "一年级";
						this.grade1 = "二年级";

                	for(let a=0;a<2;a++) {
						//一年级
						if (a === 0) {
							this.averageRadarOption.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption.series[0].data = this.avgScoreList[a]["dateValue"];

							//年级成绩趋势
							this.averageOption.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}

						} else {
							this.averageRadarOption1.radar.indicator = this.avgScoreList[a]["indicator"];
							this.averageRadarOption1.legend.data = this.avgScoreList[a]["legend"];
							this.averageRadarOption1.series[0].data = this.avgScoreList[a]["dateValue"];

							//年级成绩趋势
							this.averageOption1.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
							for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
								this.averageOption1.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
							}
						}
					}
                    this.getEchartData('averageRadar', this.averageRadarOption); //班级平均分
                    this.getEchartData('averageRadar1', this.averageRadarOption1);
                    //年级成绩趋势
                    this.getEchartData('classAverage', this.averageOption); //年级成绩趋势
                    this.getEchartData('classAverage1', this.averageOption1);
                }
            }
        },
		//默认显示一二年级
		async getFindByTeamSubject() {

			this.grade = "一年级";
			this.grade1 = "二年级";
			for(let a=0;a<2;a++) {
				//一年级
				if (a === 0) {

					this.averageRadarOption.radar.indicator = this.avgScoreList[a]["indicator"];
					this.averageRadarOption.legend.data = this.avgScoreList[a]["legend"]; //科目
					this.averageRadarOption.series[0].data = this.avgScoreList[a]["dateValue"];
					//年级成绩趋势
					this.averageOption.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
					for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
						this.averageOption.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
					}

				} else {
					this.averageRadarOption1.radar.indicator = this.avgScoreList[a]["indicator"];
					this.averageRadarOption1.legend.data = this.avgScoreList[a]["legend"];
					this.averageRadarOption1.series[0].data = this.avgScoreList[a]["dateValue"];
					//年级成绩趋势
					this.averageOption1.legend.data=this.avgScoreGradeList[a]["legend"]; //科目
					for(let i=0;i<this.avgScoreGradeList[a]["dateValue"].length;i++){
						this.averageOption1.series[i].data=this.avgScoreGradeList[a]["dateValue"][i];
					}
				}



			}
			this.getEchartData('averageRadar', this.averageRadarOption); //班级平均分
			this.getEchartData('averageRadar1', this.averageRadarOption1);
			this.getEchartData('classAverage', this.averageOption); //年级成绩趋势
			this.getEchartData('classAverage1', this.averageOption1);
		},

		async getSumAmout() { // 获取当日充值总金额
            var res = await that.request(baseurl, 'GET', '/general/getSumAmount', {});
            res = JSON.parse(res);
            if (res.info == "success") {
                if (res.responseData) {
                    this.SumAmout = res.responseData;
                }
            }
        },
        async getAmountTrend() { // 日充值趋势图
            this.rechargeOption.xAxis.data = [];
            this.rechargeOption.series[0].data = [];
            let endDate = formatTime(new Date()).day;
            let beginDate = getBeforeDate(30);
            let data = {
                beginDate: beginDate,
                endDate: endDate
            }
            var res = await that.request(this.baseurl, 'GET', '/general/amountTrend', data);
            res = JSON.parse(res)
            if (res.info == "success") {
                res.responseData.forEach(item => {
                    this.rechargeOption.xAxis.data.push(item.date);
                    this.rechargeOption.series[0].data.push(item.amount)
                })
                this.getEchartData('recharge', this.rechargeOption);
            }
        },
        async getReplaceCardTotal() { //补卡未处理人数统计
            var res = await that.request(this.baseurl, 'GET', '/general/replaceCardTotal', {});
            res = JSON.parse(res)
            if (res.info == "success") {
                this.replaceCardTotal = res.responseData;
            }
        },
        async getOnePassCardCount() { //一卡通统计
            var res = await that.request(this.baseurl, 'GET', '/general/onePassCardCount', {});
            res = JSON.parse(res)
            if (res.info == "success") {
                this.teacherTotal = res.responseData.teacherTotal;
                this.stuTotal = res.responseData.stuTotal;
            }
        },
        async getNotCardTotal() { //报修未处理
            var res = await that.request(this.baseurl, 'GET', '/general/repairUntreated', {});
            res = JSON.parse(res);
            if (res.info == "success") {
                this.notCardTotal = res.responseData.total;
            }
        },
        async getFeedbackTotal() { //家校反馈统计
            var res = await that.request(this.baseurl, 'GET', '/general/feedbackTotal', {});
            res = JSON.parse(res);
            if (res.info == "success") {
                this.feedbackTotal = res.responseData;
            }
        },
        async getFeedbackMonthTotal() { //家校反馈月趋势图
            this.feedbackOption.xAxis.data = [];
            this.feedbackOption.series[0].data = [];
            let endDate = formatTime(new Date()).day;
            let beginDate = getBeforeDate(30);
            let data = {
                beginDate: beginDate,
                endDate: endDate
            }
            var res = await that.request(this.baseurl, 'GET', '/general/feedbackMonthTotal', data);
            res = JSON.parse(res);
            if (res.info == "success") {
                res.responseData.forEach(item => {
                    this.feedbackOption.xAxis.data.push(item.date);
                    this.feedbackOption.series[0].data.push(item.total);
                })
                this.getEchartData('feedback', this.feedbackOption);
            }
        },
        async getSchoolDaySumMoney() { //总消费金额
            let query_dateBegin = formatTime(new Date()).day;
            let data = {
                query_dateBegin: query_dateBegin,
                query_dateEnd: query_dateBegin
            }
            var res = await that.request(this.baseurl, 'GET', '/general/schoolDaySumMoney', data);
            if (res.result === true) {
                this.SchoolDaySumMoney = res.data;
            }
        },
        async getSchoolDaySumMoneyList() { //日消费趋势图
            this.trendOption.xAxis.data = [];
            this.trendOption.series[0].data = [];
            let query_dateBegin = formatTime(new Date()).day;
            let beginDate = getBeforeDate(30);
            let data = {
                query_dateBegin: beginDate,
                query_dateEnd: query_dateBegin
            }
            var res = await that.request(baseurl, 'GET', '/general/schoolDaySumMoneyList', data);
            let result = JSON.parse(res);
            if (result.statusCode === 200) {
                result.data.forEach(item => {
                    this.trendOption.xAxis.data.push(item.key);
                    this.trendOption.series[0].data.push(item.value);
                })
                this.getEchartData('trend', this.trendOption);
            }
        },
        async getSchoolDayHotelCodePoint() { //日消费区域分布
            this.HotelOption.legend.data = [];
            this.HotelOption.series[0].data = [];
            let query_dateBegin = formatTime(new Date()).day;
            let beginDate = getBeforeDate(30);
            let data = {
                query_dateBegin: beginDate,
                query_dateEnd: query_dateBegin
            }
            var res = await that.request(this.baseurl, 'GET', '/general/schoolDayHotelCodePoint', data);
            // var res = {
            //     data: [{
            //         hotel_code: "001",
            //         hotel_name: "一楼餐厅",
            //         mac_point: 5
            //     }, {
            //         hotel_code: "002",
            //         hotel_name: "二楼外餐厅",
            //         mac_point: 25
            //     }, {
            //         hotel_code: "003",
            //         hotel_name: "二楼内餐厅",
            //         mac_point: 38
            //     }],
            //     error: "",
            //     result: true,
            //     statusCode: 200,
            // }
            res = JSON.parse(res);
            if (res.result === true) {
                let hotel_nameData = [];
                res.data.forEach(item => {
                    this.HotelOption.legend.data.push(item.hotel_name);
                    let obj = {};
                    obj.value = item.mac_point;
                    obj.name = item.hotel_name;
                    this.HotelOption.series[0].data.push(obj);
                    hotel_nameData.push(obj);
                })
                this.HotelOption.legend.formatter = function(name) {
                        var value = hotel_nameData.filter(v => v.name == name)[0].value
                        var all = 0;
                        hotel_nameData.forEach(function(item) {
                            all = all + item.value
                        })
						var num = (value / all * 100);
						// console.log(num)
						if(!isNaN(num)) {
							result = num.toFixed(1) + '%'
						}else {
							result = 0 + '%'
						}
                        // var result = (value / all * 100).toFixed(1) + '%'
						// console.log(result)
                        return name + ' : ' + result;
                    },
					// console.log(this.HotelOption)
                    this.getEchartData('daily', this.HotelOption);
            }
        },
        async getrepairTotal(currDay) { //报修统计  repairOption
            let query_dateBegin = this.currady + "-31";
            let beginDate = this.currady + "-01";
            let data = {
                beginDate: beginDate,
                endDate: query_dateBegin
            }
            let res = await that.request(this.baseurl, "GET", "/general/repairTotal", data);
            res = JSON.parse(res)
            this.repairOption.xAxis.data = [];
            this.repairOption.series[0].data = [];
            if (res.info === "success") {
                res.responseData.forEach(item => {
                    this.repairOption.xAxis.data.push(item.name);
                    this.repairOption.series[0].data.push(item.total);
                })
                this.getEchartData('repair', this.repairOption);
            }
        },
        getDay(value) { //选择报修统计月份
            this.getrepairTotal(this.currady)
        }
    },
});