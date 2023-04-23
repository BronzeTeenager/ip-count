<template>
	<view class="container">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<diy-sticky h5NavHeight="0" offset-top="0">
				<view class="flex diy-sticky-100 flex-wrap diygw-col-24 flex-direction-column flex25-clz">
					<view class="diygw-col-24 search1-clz">
						<view class="diygw-search">
							<view class="flex1 flex padding-xs solid radius">
								<text style="color: #555 !important" class="diy-icon-search"></text>
								<input class="flex1" name="search1" type="" v-model="likeStr" placeholder="请输入关键字" />
							</view>
							<text @click="selectLikeApi()" style="color: #333 !important"
								class="diy-icon-search margin-left-xs"></text>
						</view>
					</view>
					<view class="flex diygw-col-24 noticebar-clz">
						<diy-noticebar class="flex1 diy-notice-bar" color="#07c160" bgColor="#fff"
							leftIcon="diy-icon-notification">
							<block v-slot:content>
								<text class="diy-notice-item"> 本软件免费试用,有问题请反馈 | 温馨提示(长按可删除项目)</text>
							</block>
						</diy-noticebar>
					</view>
					<view class="clearfix"></view>
				</view>
				<view class="clearfix"></view>
			</diy-sticky>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex-clz">
			<view v-for="(item, index) in appInfo.data" :key="item.token" @click="appInfoApi(item.token)"
				class="flex flex-wrap diygw-col-24 justify-center items-center flex4-clz"
				@longpress='appLongtap(item.token,item.appName)'>
				<image src="/static/icon11_gj.png" class="diygw-image diygw-col-8 image1-clz"
					style="height: 75px !important; width: 100%" mode="aspectFit"></image>
				<view class="flex flex-wrap diygw-col-16 flex-direction-column justify-between flex5-clz">
					<view class="diygw-col-24 text6-clz diygw-text-sm">
						{{ item.appName }}
					</view>
					<view class="diygw-col-0 text7-clz diygw-text-xs">
						{{ item.notes }}
					</view>
					<view class="flex flex-wrap diygw-col-24 justify-between items-center flex6-clz">
						<view class="diygw-col-0 text7-clz diygw-text-md">
							总IP量: {{ item.ipCount }}
						</view>
						<view class="diygw-col-0 text8-clz diygw-text-md">
							总访问量: {{ item.requestCount }}
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="flex diygw-col-24 diygw-bottom">
			<view class="diygw-grid diygw-actions">
				<button @tap="navigateTo" data-type="page" data-url="/pages/home" class="diygw-action">
					<view class="diygw-grid-inner">
						<view class="diygw-grid-icon diygw-avatar diy-icon-home"> </view>
						<view class="diygw-grid-title"> 首页 </view>
					</view>
				</button>
				<button @tap="navigateTo" data-type="page" data-url="/pages/userHome" class="diygw-action">
					<view class="diygw-grid-inner">
						<view class="diygw-grid-icon diygw-avatar diy-icon-home"> </view>
						<view class="diygw-grid-title"> 个人中心 </view>
					</view>
				</button>
			</view>
		</view>
		<!-- 弹出添加app对话框 -->
		<view class="flex diygw-col-0 right-bottom floatbar-clz" @click="setAppName()">
			<view class="diygw-grid diygw-actions">
				<button class="diygw-action radius">
					<view class="diygw-grid-inner">
						<view class="diygw-grid-icon diygw-avatar grid-icon-clz">
							<image mode="aspectFit" class="diygw-avatar-img" src="/static/Q0GNKQ9XD8JEG6Y9.png"></image>
						</view>
						<view class="diygw-grid-title"></view>
					</view>
				</button>
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
				appInfo: {},
				// 模糊查询
				likeStr: '',
			}
		},
		onShow() {
			this.setCurrentPage(this)

		},
		onLoad(option) {
			this.setCurrentPage(this)
			if (option) {
				this.setData({
					globalOption: this.getOption(option)
				})
			}

			this.init();
		},
		onPullDownRefresh() {
			this.appAllInfoApi()
			uni.stopPullDownRefresh();
		},
		methods: {
			async init() {
				this.appAllInfoApi()
			},
			// 查询用户信息 API请求方法
			appAllInfoApi(param) {
				this.$http.get('http://api.9api.top:9999/app/getAllApp', {}, {
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
						this.appInfo = res;
					}
				})

			},
			appInfoApi(appToken) {
				//console.log(appToken)
				uni.navigateTo({
					// 方式一：此方式传参，刷新页面参数不丢失，参数在url上显示
					// 如果是对象或者数组参数可使用 JSON.stringfy()，将参数转化成字符串，然后获取时，通过 JSON.parse() 转化成对象
					url: `/pages/appInfo?token=` + appToken,
				})
			},
			// 模糊查询
			selectLikeApi() {
				if (this.likeStr == "") {
					// 刷新项目
					this.likeStr = ''
					this.appAllInfoApi()
					return;
				}
				this.$http.get('http://api.9api.top:9999/app/selectLikeAppName?str=' + this.likeStr, {}, {
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
						// 判断是否查询到
						if (Object.keys(res.data).length == 0) {
							uni.showToast({
								title: "没有该项目o!",
								icon: 'error',
								duration: 2000
							})
							setTimeout(() => {
								this.likeStr = ''
								this.appAllInfoApi()
							}, 1000);

						}

						this.appInfo = res;
					}
				})
			},
			// 添加app 弹出名称对话框
			setAppName() {
				uni.showModal({
					title: "请输入项目名称",
					showCancel: true,
					editable: true,
					success: (res) => {
						if (res.confirm) {
							if (res.content == '') {
								uni.showToast({
									title: '请输入项目名称',
									icon: 'error',
									duration: 1500
								})
							} else {
								setTimeout(() => {
									this.setAppNotes(res.content)
								}, 250);
							}
						}
					}
				});
			},
			// 添加app 弹出备注对话框
			setAppNotes(appName) {
				uni.showModal({
					title: "请输入项目备注(可空)",
					showCancel: true,
					editable: true,
					success: (res) => {
						if (res.confirm) {
							this.$http.post('http://api.9api.top:9999/app/add', {
								'appName': appName,
								'notes': res.content
							}, {
								'Content-type': 'application/json',
								'token': uni.getStorageSync("token")
							}).then((resp) => {
								// 判断添加是否成功
								if (resp.code === 0) {
									uni.showToast({
										title: resp.msg,
										icon: 'success',
										duration: 1000
									})
									// 刷新项目
									setTimeout(() => {
										this.likeStr = ''
										this.appAllInfoApi()
									}, 1000);
								} else {
									uni.showToast({
										title: resp.msg,
										icon: 'error',
										duration: 1500
									})
								}
							});
						}
					}
				});
			},
			// app长按事件
			appLongtap(appToken, appName) {
				uni.showModal({
					title: '温馨提示',
					// 提示文字
					content: `是否需要永久删除 ${appName}`,
					// 取消按钮的文字自定义
					cancelText: "取消",
					// 确认按钮的文字自定义
					confirmText: "删除",
					//删除字体的颜色
					confirmColor: 'red',
					//取消字体的颜色
					cancelColor: '#000000',
					success: (res) => {
						if (res.confirm) {
							// 执行确认后的操作
							this.$http.get(`http://api.9api.top:9999/app/deleteApp?token=${appToken}`, {}, {
								'token': uni.getStorageSync("token")
							}).then(res => {
								if (res.code != 0) {
									// 删除失败
									uni.showToast({
										title: res.msg,
										icon: 'error',
										duration: 1500
									})

								} else if (res.code == 0) {
									uni.showToast({
										title: res.msg,
										icon: 'success',
										duration: 1500
									})

									// 刷新项目
									setTimeout(() => {
										this.likeStr = ''
										this.appAllInfoApi()
									}, 800);
								}
							})
						}
					}
				})
			}
		}
	}
</script>


<style lang="scss" scoped>
	.search1-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 17px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.noticebar-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		font-size: 11px !important;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
		font-style: italic;
	}

	.flex4-clz {
		margin-left: 8px;
		border-bottom-left-radius: 6px;
		box-shadow: 0px 1px 3px 3px rgba(111, 111, 111, 0.18);
		z-index: 1000;
		overflow: hidden;
		width: calc(100% - 8px - 8px) !important;
		border-top-left-radius: 6px;
		margin-top: 8px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
		margin-bottom: 8px;
		margin-right: 8px;
	}

	.image1-clz {
		margin-left: 5px;
		border-bottom-left-radius: 6px;
		overflow: hidden;
		width: calc(33.3333333333% - 5px - 5px) !important;
		border-top-left-radius: 6px;
		margin-top: 5px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
		margin-bottom: 5px;
		margin-right: 5px;
	}

	.flex5-clz {
		padding-top: 0px;
		z-index: 1000;
		padding-left: 5px;
		padding-bottom: 0px;
		padding-right: 5px;
	}

	.text5-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 5px) !important;
		margin-top: 5px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.text6-clz {
		margin-left: 5px;
		width: calc(100% - 5px - 0px) !important;
		margin-top: 5px;
		margin-bottom: 0px;
		margin-right: 0px;
	}

	.flex6-clz {
		z-index: 1000;
	}

	.text7-clz {
		margin-left: 5px;
		margin-top: 5px;
		margin-bottom: 5px;
		margin-right: 5px;
	}

	.text8-clz {
		margin-left: 5px;
		margin-top: 5px;
		margin-bottom: 5px;
		margin-right: 5px;
	}

	.floatbar-clz {
		bottom: 137px;
		width: 82px !important;
		right: 29px;
	}

	.grid-icon-clz {
		width: 55px !important;
		height: 55px !important;
		font-size: calc(55px - 4px) !important;
	}

	.container {
		padding-left: 0px;
		padding-right: 0px;
	}

	.container {
		padding-bottom: 80px;
	}
</style>
