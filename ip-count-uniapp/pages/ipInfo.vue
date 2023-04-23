<template>
	<view class="container">
		<diy-navbar :isFixed="true" bgColor="green" :isBack="true">
			<!-- #ifndef VUE3 -->
			<block slot="backText"> 返回 </block>
			<block slot="content"> 查询IP </block>
			<!-- #endif -->
			<!-- #ifdef VUE3 -->
			<template v-slot:backText> 返回 </template>
			<template v-slot:content> 查询IP </template>
			<!-- #endif -->
		</diy-navbar>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<diy-sticky h5NavHeight="0" offset-top="0">
				<view class="flex diy-sticky-100 flex-wrap diygw-col-24 justify-center flex2-clz">
					<view class="diygw-form-item diygw-col-24">
						<view class="title"> 日期 </view>
						<view class="input">
							<picker name="date" @change="changeDate" :value="date" mode="date">
								<view class="picker-item" v-if="date">
									{{ date }}
								</view>
								<view class="picker-item placeholder" v-else> 请选择指定日期 </view>
							</picker>
						</view>
					</view>
					<view class="clearfix"></view>
				</view>
				<view class="clearfix"></view>
			</diy-sticky>
		</view>
		<view class="flex flex-wrap diygw-col-24 justify-center flex-clz">
			<view v-for="(item, index) in ipInfo.data" :key="index"
				class="flex flex-wrap diygw-col-24 flex-direction-column flex4-clz">
				<view class="flex flex-wrap diygw-col-24 flex5-clz">
					<view class="diygw-col-24 text6-clz"> 日期 </view>
					<view class="diygw-col-13 text7-clz">
						{{ item.ipInfo }}
					</view>
					<view class="diygw-col-13 text8-clz"> 访问次数 </view>
				</view>
				<view class="flex flex-wrap diygw-col-24 flex6-clz">
					<view class="diygw-col-24 text9-clz diygw-text-sm">
						{{ item.date }}
					</view>
					<view class="diygw-col-13 text10-clz diygw-text-sm">
						{{ item.ip }}
					</view>
					<view class="diygw-col-22 text11-clz diygw-text-sm">
						{{ item.requestCount }}
					</view>
					<view class="flex diygw-col-24 line1-clz">
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
				ipInfoNum: 1,
				appToken: '',
				ipInfo: {},
				date: ''
			};
		},
		onShow() {
			this.setCurrentPage(this);
		},
		onLoad(option) {

			this.appToken = option.token

			this.setCurrentPage(this);
			if (option) {
				this.setData({
					globalOption: this.getOption(option)
				});
			}

			this.init();
		},
		onPullDownRefresh() {
			// 新增接口 API请求方法
			this.ipInfoNum = 1;
			this.ipInfoApi();

			uni.stopPullDownRefresh();
		},
		methods: {
			async init() {
				await this.ipInfoApi();
			},
			// 新增接口 API请求方法
			ipInfoApi(param) {
				this.$http.get('http://api.9api.top:9999/ip/getTodayAllIp?token=' + this.appToken, {}, {
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
						if (Object.keys(res.data).length == 0) {
							uni.showToast({
								title: "查询空 !",
								icon: 'error',
								duration: 2000
							})
							setTimeout(() => {
								//location.href = "/#/pages/home"
							}, 1000);
						}
						this.ipInfo = res;
					}
				})
			},
			// 指定日期查询
			changeDate(evt) {
				this.date = evt.detail.value;
				this.$http.get(`http://api.9api.top:9999/ip/getIpAndDate?token=${this.appToken}&date=${this.date}`, {}, {
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
						if (Object.keys(res.data).length == 0) {
							uni.showToast({
								title: "查询空 !",
								icon: 'error',
								duration: 2000
							})
							setTimeout(() => {
								//location.href = "/#/pages/home"
							}, 1000);
						}
						this.ipInfo = res;
					}
				})
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex2-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 20px;
		margin-bottom: 10px;
		margin-right: 5px;
	}

	.button-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		flex: 1;
		font-size: 14px !important;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}

	.button-button-clz {
		margin: 3px !important;
	}

	.button2-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		flex: 1;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}

	.button2-button-clz {
		margin: 3px !important;
	}

	.button1-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		flex: 1;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}

	.button1-button-clz {
		margin: 3px !important;
	}

	.flex-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 5px;
		margin-bottom: 5px;
		margin-right: 5px;
	}

	.flex4-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.flex5-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.text6-clz {
		color: #371fd7;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}

	.text7-clz {
		color: #13e195;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}

	.text8-clz {
		color: #ba08f5;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}

	.flex6-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 5px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.text9-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}

	.text10-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}

	.text11-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}

	.line1-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.container {
		padding-left: 0px;
		padding-right: 0px;

		//background-image: url(/static/ipInfobjt.png);

		font-size: 12px;
	}

	.container {}
</style>
