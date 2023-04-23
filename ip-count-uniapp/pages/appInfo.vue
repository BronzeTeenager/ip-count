<template>
	<view class="container">
		<diy-navbar :isFixed="true" bgColor="" color="#4d217d" :isBack="true">
			<!-- #ifndef VUE3 -->
			<block slot="backText"> 返回 </block>
			<block slot="content"> {{appInfoInfo.appName}} </block>
			<!-- #endif -->
			<!-- #ifdef VUE3 -->
			<template v-slot:backText> 返回 </template>
			<template v-slot:content> {{appInfoInfo.appName}} </template>
			<!-- #endif -->
		</diy-navbar>
		<view class="flex flex-wrap diygw-col-24 justify-center flex1-clz">
			<view class="flex diygw-col-24 button3-clz" @click="openTrendInfoApi()">
				<button style="background-color: rgba(9, 248, 49, 0.41) !important" class="diygw-btn green radius-sm flex-sub margin-xs button3-button-clz">趋势分析</button>
			</view>
			<view class="flex diygw-col-24 button-clz" @click="getIpApi()">
				<button style="background-color: rgba(248, 156, 9, 0.45) !important" class="diygw-btn green radius-sm flex-sub margin-xs button-button-clz">查看IP</button>
			</view>
			<view class="flex diygw-col-24 button4-clz" @click="openprovinceInfo()">
				<button style="background-color: rgba(0, 115, 255, 0.32) !important" class="diygw-btn green radius-sm flex-sub margin-xs button4-button-clz">地域分布</button>
			</view>
		</view>
		<view class="diygw-form-item diygw-form-item-small diygw-col-24">
			<view class="title width-auto"> 在线人数: </view>
			<view class="input">
				<input class="flex1" :focus="inputFocus" name="input" comfirm-type="done" type="text" v-model="onlineCount"  />
			</view>
		</view>
		<view class="flex diygw-col-24 flex-wrap flex2-clz">
			<view class="flex flex-wrap diygw-col-12 justify-center flex3-clz">
				<view class="flex diygw-col-24 flex-direction-column justify-center flex-nowrap flex4-clz">
					<view class="diygw-col-24 text-clz"> 今日IP </view>
					<view class="diygw-col-24 text1-clz"> {{ appInfoInfo.todayIpNumber }} </view>
				</view>
			</view>
			<view class="flex flex-wrap diygw-col-12 justify-center flex5-clz">
				<view class="flex diygw-col-24 flex-direction-column justify-center flex-nowrap flex6-clz">
					<view class="diygw-col-24 text2-clz"> 今日请求次数 </view>
					<view class="diygw-col-24 text3-clz"> {{ appInfoInfo.todayRequestNumber }} </view>
				</view>
			</view>
		</view>
		<view class="flex diygw-col-24">
			<view class="diygw-pzx" style="border-bottom: 2px solid #fc06be"></view>
		</view>
		<view class="flex diygw-col-24 flex-wrap flex12-clz">
			<view class="flex flex-wrap diygw-col-12 justify-center flex13-clz">
				<view class="flex diygw-col-24 flex-direction-column justify-center flex-nowrap flex14-clz">
					<view class="diygw-col-24 text8-clz"> 昨日IP </view>
					<view class="diygw-col-24 text9-clz"> {{ appInfoInfo.yesterdayIpNumber }} </view>
				</view>
			</view>
			<view class="flex flex-wrap diygw-col-12 justify-center flex15-clz">
				<view class="flex diygw-col-24 flex-direction-column justify-center flex-nowrap flex16-clz">
					<view class="diygw-col-24 text10-clz"> 昨日请求次数 </view>
					<view class="diygw-col-24 text11-clz"> {{ appInfoInfo.yesterdayRequestNumber }} </view>
				</view>
			</view>
		</view>
		<view class="flex diygw-col-24">
			<view class="diygw-pzx" style="border-bottom: 2px solid #fc06be"></view>
		</view>
		<view class="flex diygw-col-24 flex-wrap flex7-clz">
			<view class="flex flex-wrap diygw-col-12 justify-center flex8-clz">
				<view class="flex diygw-col-24 flex-direction-column justify-center flex-nowrap flex9-clz">
					<view class="diygw-col-24 text4-clz"> 累计IP </view>
					<view class="diygw-col-24 text5-clz"> {{ appInfoInfo.ipCount }}  </view>
				</view>
			</view>
			<view class="flex flex-wrap diygw-col-12 justify-center flex10-clz">
				<view class="flex diygw-col-24 flex-direction-column justify-center flex-nowrap flex11-clz">
					<view class="diygw-col-24 text6-clz"> 累计请求次数 </view>
					<view class="diygw-col-24 text7-clz"> {{ appInfoInfo.requestCount }} </view>
				</view>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex19-clz">
			<view class="flex diygw-col-24 button5-clz" @click="setAppRequest()">
				<button style="background-color: rgba(184, 193, 21, 0.65) !important" class="diygw-btn radius-xs flex-sub margin-xs button5-button-clz">一键生成项目链接</button>
			</view>
			<view class="flex diygw-col-24 button1-clz" @click="setonlineRequest()">
				<button style="background-color: rgba(184, 193, 21, 0.65) !important" class="diygw-btn radius-xs flex-sub margin-xs button1-button-clz">一键生成心跳链接</button>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex-clz">
			<view class="flex diygw-col-24 button2-clz" @click="instructionsApi()">
				<button style="background-color: rgba(27, 193, 21, 0.65) !important" class="diygw-btn radius-xs flex-sub margin-xs button2-button-clz">使用说明</button>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex17-clz">
			<view class="diygw-col-24 search-clz">
				<view class="diygw-search">
					<view class="flex1 flex padding-xs solid radius search-search">
						<text style="color: #77ff00 !important" class="diy-icon-search"></text>
						<input class="flex1" name="search" type="" v-model="likeInput" placeholder="请您要查询的输入IP" />
					</view>
					<view style="color: #0020ff !important" class="diygw-tag margin-left-xs radius-xs" @click="selectLikeIpApi()"> 搜索 </view>
				</view>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 justify-center flex18-clz">
			<view v-for="(item, index) in ipInfo.data" :key="index" class="flex flex-wrap diygw-col-24 flex-direction-column flex20-clz">
				<view class="flex flex-wrap diygw-col-24 flex21-clz">
					<view class="diygw-col-24 text12-clz"> 日期 </view>
					<view class="diygw-col-13 text13-clz">
						{{ item.ipInfo }}
					</view>
					<view class="diygw-col-13 text14-clz"> 访问次数 </view>
				</view>
				<view class="flex flex-wrap diygw-col-24 flex22-clz">
					<view class="diygw-col-24 text15-clz diygw-text-sm">
						{{ item.date }}
					</view>
					<view class="diygw-col-13 text16-clz diygw-text-sm">
						{{ item.ip }}
					</view>
					<view class="diygw-col-22 text17-clz diygw-text-sm">
						{{ item.requestCount }}
					</view>
					<view class="flex diygw-col-24 line2-clz">
						<view class="diygw-pzx" style="border-bottom: 1px solid #00fc3c"></view>
					</view>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//用户全局信息
				userInfo: {},
				//页面传参
				globalOption: {},
				//自定义全局变量
				globalData: {},
				//在线统计
				onlineCount: 0,
				// 搜索输入框
				likeInput: '',
				appToken: '',
				appInfoInfo: {
					"token": "",
					"appName": "",
					"notes": "",
					"todayIpNumber": 0,
					"yesterdayIpNumber": 0,
					"todayRequestNumber": 0,
					"yesterdayRequestNumber": 0,
					"ipCount": 0,
					"requestCount": 0
				},
				// 搜索后的
				ipInfo: {}
			};
		},
		onShow() {
			this.setCurrentPage(this)
		},
		onLoad(option) {
			// 页面传参token
			this.appToken = option.token;

			this.appInfoInfoApi()
			this.onlineApi()

			this.setCurrentPage(this)
			if (option) {
				this.setData({
					globalOption: this.getOption(option)
				})
			}

			this.init();
		},
		onPullDownRefresh() {
			this.appInfoInfoApi()
			this.onlineApi()
			uni.stopPullDownRefresh();
		},
		methods: {
			async init() {
				//this.appInfoInfoApi()
			},
			// 新增接口 API请求方法
			appInfoInfoApi() {
				this.$http.get(`http://api.9api.top:9999/app/getOneApp?token=${this.appToken}`, {}, {
					'token': uni.getStorageSync("token")
				}).then(res => {
					if (res.code != 0) {
						// 登录失败
						uni.showToast({
							title: "登录失效,请重新登录!",
							icon: 'error',
							duration: 1500
						})
						setTimeout(function() {
							uni.redirectTo({
								url: '/pages/login',
							});
						}, 1000);
					} else if (res.code == 0) {
						this.appInfoInfo = res.data;
					}
				})
			},
		
			onlineApi() {
				this.$http.get(`http://api.9api.top:9999/online/getCount?appId=${this.appToken}`, {}, {
					'token': uni.getStorageSync("token")
				}).then(res => {
					if (res.code != 0) {
						// 登录失败
						uni.showToast({
							title: "登录失效,请重新登录!",
							icon: 'error',
							duration: 1500
						})
						setTimeout(function() {
							uni.redirectTo({
								url: '/pages/login',
							});
						}, 1000);
					} else if (res.code == 0) {
						this.onlineCount = res.data.count;
					}
				})
			},
			// 使用说明
			  instructionsApi() {
				  uni.navigateTo({
				  	url: '/pages/instructions',
				  });
			},
			setAppRequest() {
				let url = "http://api.9api.top:9999/ip/ipCount?token=" + this.appToken
				this.copyTitle(url)
			},
			setonlineRequest(){
				let url = "http://api.9api.top:9999/online/count?appId=" + this.appToken
				this.copyTitle(url)
			},
			// 复制
			copyTitle(txt) {
				var input = document.createElement("input"); // 创建一个新input标签
				input.setAttribute("readonly", "readonly"); // 设置input标签只读属性
				input.setAttribute("value", txt); // 设置input value值为需要复制的内容
				document.body.appendChild(input); // 添加input标签到页面
				input.select(); // 选中input内容
				input.setSelectionRange(0, 9999); // 设置选中input内容范围
				document.execCommand("copy"); // 复制
				document.body.removeChild(input); // 删除新创建的input标签
				uni.showToast({
					title: "复制成功!",
					icon: 'success',
					duration: 1000
				})
			},
			getIpApi() {
				uni.navigateTo({
					// 方式一：此方式传参，刷新页面参数不丢失，参数在url上显示
					// 如果是对象或者数组参数可使用 JSON.stringfy()，将参数转化成字符串，然后获取时，通过 JSON.parse() 转化成对象
					url: `/pages/ipInfo?token=` + this.appToken,
				})
			},
			openprovinceInfo() {
				uni.navigateTo({
					// 方式一：此方式传参，刷新页面参数不丢失，参数在url上显示
					// 如果是对象或者数组参数可使用 JSON.stringfy()，将参数转化成字符串，然后获取时，通过 JSON.parse() 转化成对象
					url: `/pages/provinceInfo?token=` + this.appToken,
				})
			},
			openTrendInfoApi(){
				uni.navigateTo({
					// 方式一：此方式传参，刷新页面参数不丢失，参数在url上显示
					// 如果是对象或者数组参数可使用 JSON.stringfy()，将参数转化成字符串，然后获取时，通过 JSON.parse() 转化成对象
					url: `/pages/trendInfo?token=` + this.appToken,
				})
			},
			// ip 模糊查询
			selectLikeIpApi() {

				this.ipInfo.data = []

				if (this.likeInput == "") {
					return;
				}

				this.$http.get(
					`http://api.9api.top:9999/ip/selectLikeIp?token=${this.appToken}&queryStr=${this.likeInput}`, {}, {
						'token': uni.getStorageSync("token")
					}).then(res => {
					if (res.code != 0) {
						// 登录失败
						uni.showToast({
							title: "登录失效,请重新登录!",
							icon: 'error',
							duration: 1500
						})
						setTimeout(function() {
							uni.redirectTo({
								url: '/pages/login',
							});
						}, 1000);
					} else if (res.code == 0) {
						this.ipInfo = res;
					}
				})
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex1-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: -2px;
		margin-right: 5px;
	}
	.button3-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		flex: 1;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}
	.button3-button-clz {
		margin: 3px !important;
	}
	.button-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		flex: 1;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}
	.button-button-clz {
		margin: 3px !important;
	}
	.button4-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		flex: 1;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}
	.button4-button-clz {
		margin: 3px !important;
	}
	.flex2-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 5px;
		margin-bottom: 10px;
		margin-right: 5px;
	}
	.text-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		font-size: 20px !important;
		margin-top: 5px;
		margin-bottom: 8px;
		text-align: center;
		margin-right: 5px;
	}
	.text1-clz {
		color: rgba(255, 0, 0, 0.99);
		font-size: 18px !important;
		text-align: center;
	}
	.text2-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		font-size: 20px !important;
		margin-top: 5px;
		margin-bottom: 8px;
		text-align: center;
		margin-right: 5px;
	}
	.text3-clz {
		color: #ff0000;
		font-size: 18px !important;
		text-align: center;
	}
	.flex12-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 10px;
		margin-bottom: 10px;
		margin-right: 5px;
	}
	.text8-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		font-size: 20px !important;
		margin-top: 5px;
		margin-bottom: 8px;
		text-align: center;
		margin-right: 5px;
	}
	.text9-clz {
		color: #ff0000;
		font-size: 18px !important;
		text-align: center;
	}
	.text10-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		font-size: 20px !important;
		margin-top: 5px;
		margin-bottom: 8px;
		text-align: center;
		margin-right: 5px;
	}
	.text11-clz {
		color: #ff0000;
		font-size: 18px !important;
		text-align: center;
	}
	.flex7-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 10px;
		margin-bottom: 10px;
		margin-right: 5px;
	}
	.text4-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		font-size: 20px !important;
		margin-top: 5px;
		margin-bottom: 8px;
		text-align: center;
		margin-right: 5px;
	}
	.text5-clz {
		color: #ff0000;
		font-size: 18px !important;
		text-align: center;
	}
	.text6-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		font-size: 20px !important;
		margin-top: 5px;
		margin-bottom: 8px;
		text-align: center;
		margin-right: 5px;
	}
	.text7-clz {
		color: #ff0000;
		font-size: 18px !important;
		text-align: center;
	}
	.button5-clz {
		color: #9c3add;
		flex: 1;
		font-family: 楷体;
	}
	.button5-button-clz {
		margin: 3px !important;
	}
	.button1-clz {
		color: #9c3add;
		flex: 1;
		font-family: 楷体;
	}
	.button1-button-clz {
		margin: 3px !important;
	}
	.button2-clz {
		color: #9c3add;
		font-family: 楷体;
	}
	.button2-button-clz {
		margin: 3px !important;
	}
	.search-clz {
		color: #112058;
	}
	.search-search {
		background-color: rgba(0, 0, 0, 0.6) !important;
		color: rgba(29, 162, 76, 0.99);
	}
	.flex18-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 10px;
		margin-bottom: 5px;
		margin-right: 5px;
	}
	.flex20-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}
	.flex21-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}
	.text12-clz {
		color: #371fd7;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}
	.text13-clz {
		color: #fafe08;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}
	.text14-clz {
		color: #ba08f5;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}
	.flex22-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 5px;
		margin-bottom: 0px;
		margin-right: 5px;
	}
	.text15-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}
	.text16-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}
	.text17-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}
	.line2-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}
	.container {
		padding-left: 0px;
		padding-right: 0px;

		background-image: url(/static/0cbf3b3ab3ac10e5c8ef98ca2a067207.jpg);
		font-size: 12px;
	}
	.container {
	}
</style>
