<template>
	<view class="container">
		<diy-navbar :isFixed="true" bgColor="green" :isBack="true">
			<!-- #ifndef VUE3 -->
			<block slot="backText"> 返回 </block>
			<block slot="content"> 趋势分析 </block>
			<!-- #endif -->
			<!-- #ifdef VUE3 -->
			<template v-slot:backText> 返回 </template>
			<template v-slot:content> 趋势分析 </template>
			<!-- #endif -->
		</diy-navbar>

		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex-clz">
			<view v-for="(item, index) in trendInfo.data" :key="index"
				class="flex flex-wrap diygw-col-24 flex-direction-column flex3-clz">
				<view class="flex flex-wrap diygw-col-24 flex1-clz">
					<view class="diygw-col-24 text1-clz"> 日期 </view>
					<view class="diygw-col-13 text-clz"> IP量 </view>
					<view class="diygw-col-13 text2-clz"> 访问次数 </view>
				</view>
				<view class="flex flex-wrap diygw-col-24 flex2-clz">
					<view class="diygw-col-24 text3-clz diygw-text-sm">
						{{ item.date }}
					</view>
					<view class="diygw-col-13 text4-clz diygw-text-sm">
						{{ item.ipCount }}
					</view>
					<view class="diygw-col-22 text5-clz diygw-text-sm">
						{{ item.requestSum }}
					</view>
					<view class="flex diygw-col-24 line-clz">
						<view class="diygw-pzx" style="border-bottom: 1px solid #00fc3c"></view>
					</view>
				</view>
			</view>
		</view>

		<view class="clearfix"></view>
	</view>
</template>

<script>
	//create by: 邓志锋 <280160522@qq.com> <https://www.diygw.com> DIYGW可视化设计一键生成源码
	export default {
		data() {
			return {
				//用户全局信息
				userInfo: {},
				//页面传参
				globalOption: {},
				//自定义全局变量
				globalData: {},
				appToken: '',
				trendInfo: {
					code: 0,
					msg: '',
					data: [{
						date: '2022-11-19',
						ipCount: 0,
						requestSum: 0
					}]
				}
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
			this.trendInfoApi()
			uni.stopPullDownRefresh();
		},
		methods: {
			async init() {
				this.trendInfoApi();
			},
			// 新增接口 API请求方法
			trendInfoApi(param) {
				this.$http.get(`http://api.9api.top:9999/ip/getMonthIpCountAndSum?token=${this.appToken}`, {}, {
					'token': uni.getStorageSync("token"),
					sign: '4503fcdaae0b69bbd9c98b3d5e301ef7'
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
						this.trendInfo = res;
					}
				})
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 10px;
		margin-bottom: 5px;
		margin-right: 5px;
	}

	.flex3-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.flex1-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.text1-clz {
		color: #371fd7;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}

	.text-clz {
		color: #13e195;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}

	.text2-clz {
		color: #ba08f5;
		flex: 1;
		font-size: 13px !important;
		text-align: center;
	}

	.flex2-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 5px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.text3-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}

	.text4-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}

	.text5-clz {
		color: #ff0000;
		flex: 1;
		text-align: center;
	}

	.line-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 0px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.container {
		padding-left: 0px;
		padding-right: 0px;

		font-size: 12px;
	}

	.container {}
</style>
