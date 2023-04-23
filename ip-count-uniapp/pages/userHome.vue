<template>
	<view class="container">
		<view class="flex flex-wrap diygw-col-24 items-center flex1-clz">
			<image src="/static/yh.png" class="diygw-image diygw-col-0 image-clz"
				style="height: 50px !important; width: 50px" mode="widthFix"></image>
			<view class="flex flex-wrap diygw-col-0 flex-direction-column items-start flex2-clz">
				<view class="diygw-col-0 text1-clz">
					{{ userInfo.userName }}
				</view>
				<view class="flex flex-wrap diygw-col-0 items-center flex5-clz">
					<image src="/static/zshy.png" class="diygw-image diygw-col-0"
						style="height: 20px !important; width: 20px" mode="widthFix"></image>
					<view class="diygw-col-0">
						{{ userInfo.grade }}
					</view>
				</view>
			</view>
			<text class="flex icon diygw-col-0 icon-clz diy-icon-right"></text>
		</view>
		<view class="flex diygw-col-24 olive list1-clz">
			<view class="diygw-list">
				<view style="" class="diygw-item col-33 flex-direction-column solid-bottom">
					<view class="content">
						<view class="title"> {{userInfo.appCount}} </view>
						<view class="text-sm remark"> 总项目数 </view>
					</view>
				</view>
				<view style="" class="diygw-item col-33 flex-direction-column solid-bottom">
					<view class="content">
						<view class="title"> {{userInfo.appRequestCount}} </view>
						<view class="text-sm remark"> 总访问数 </view>
					</view>
				</view>
				<view style="" class="diygw-item col-33 flex-direction-column solid-bottom">
					<view class="content">
						<view class="title"> {{userInfo.ipCount}} </view>
						<view class="text-sm remark"> 总IP数 </view>
					</view>
				</view>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 items-center flex8-clz" @click="openVipApi()">
			<view class="diygw-col-0 text6-clz"> 加入会员，立享超值权益 </view>
			<view class="diygw-col-0 text8-clz"> 开通会员 </view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex3-clz">
			<view class="diygw-col-0 text4-clz"> 我的账户 </view>
			<view class="flex diygw-col-24">
				<view class="diygw-grid col-4">
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/qb1.png"></image>
								<view class="diygw-tag badge grid-badge-0 red"> {{userInfo.money}} </view>
							</view>
							<view class="diygw-grid-title"> 我的钱包 </view>
						</view>
					</view>
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/gwd2.png"></image>
							</view>
							<view class="diygw-grid-title"> 应用订单 </view>
						</view>
					</view>
					<view class="diygw-grid-item" @click="signInApi()">
						<view class="diygw-grid-inner grid-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/icon8_qd.png"></image>
								<view class="diygw-tag badge grid-badge-0 red"> {{userInfo.signInStatus}} </view>
							</view>
							<view class="diygw-grid-title"> 每日签到 </view>
						</view>
					</view>
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid-item-clz" @click="contactAuthorApi()">
							<view class="diygw-grid-icon diygw-avatar grid-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/kf.png"></image>
							</view>
							<view class="diygw-grid-title"> 联系我们 </view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex4-clz">
			<view class="diygw-col-0 text3-clz"> 我的发布 </view>
			<view class="flex diygw-col-24">
				<view class="diygw-grid col-4">
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid2-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid2-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/bj.png"></image>
							</view>
							<view class="diygw-grid-title"> 发布 </view>
						</view>
					</view>
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid2-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid2-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/xh.png"></image>
							</view>
							<view class="diygw-grid-title"> 想做 </view>
						</view>
					</view>
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid2-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid2-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/dz2.png"></image>
							</view>
							<view class="diygw-grid-title"> 点赞 </view>
						</view>
					</view>
					<view class="diygw-grid-item">
						<view class="diygw-grid-inner grid2-item-clz">
							<view class="diygw-grid-icon diygw-avatar grid2-icon-clz">
								<image mode="aspectFit" class="diygw-avatar-img" src="/static/ls.png"></image>
							</view>
							<view class="diygw-grid-title"> 足迹 </view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex6-clz">
			<view class="flex flex-wrap diygw-col-24 items-center flex7-clz">
				<view class="diygw-col-0 text7-clz"> 设置 </view>
				<text class="flex icon3 diygw-col-0 diy-icon-right"></text>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex-clz" @click="exit()">
			<view class="flex flex-wrap diygw-col-24 items-center flex9-clz">
				<view class="diygw-col-0 text-clz"> 退出账号 </view>
				<text class="flex icon1 diygw-col-0 diy-icon-right"></text>
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
				userInfoNum: 1,
				userInfo: {
					"appCount": "0",
					"money": "0",
					"signInMoney": "no",
					"grade": "",
					"ipCount": "",
					"appRequestCount": "",
					"userName": ""
				},
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
			this.userInfoApi();
			uni.stopPullDownRefresh();
		},
		methods: {
			async init() {
				this.noticeApi();
				this.userInfoApi()
			},
			// 公告
			noticeApi() {
				this.$http.get(`http://api.9api.top:9999/utils/notice`, {}).then(res => {
					if (res.data.status == "0") {
						uni.showModal({
							title: '公告',
							// 提示文字
							content: res.data.msg,
							// 确认按钮的文字自定义
							confirmText: "确定",
							//取消字体的颜色
							cancelColor: '#000000',
							showCancel: false, // 是否显示取消按钮，默认为true
						})
					}
				})
			},
			// 开通vip
			openVipApi() {

				uni.showModal({
					title: '现加入QQ群即赠送终身会员!',
					// 提示文字
					content: '597727916',
					// 取消按钮的文字自定义
					cancelText: "取消",
					// 确认按钮的文字自定义
					confirmText: "复制群号",
					//删除字体的颜色
					confirmColor: 'red',
					//取消字体的颜色
					cancelColor: '#000000',
					success: (res) => {
						if (res.confirm) {
							// 执行确认后的操作
							let str = '597727916'
							this.copyTitle(str)
						} else {
							// 执行取消后的操作
						}
					}
				})
			},
			// 查询用户信息 API请求方法
			userInfoApi(param) {
				this.$http.get(`http://api.9api.top:9999/user/userInfo`, {}, {
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
						this.userInfo = res.data;
					}
				})
			},
			exit() {
				uni.showModal({
					title: '提示',
					content: '您确定需要退出登录吗!',
					success: function(res) {
						if (res.confirm) {
							uni.removeStorageSync("token");
							setTimeout(function() {
								uni.redirectTo({
									url: '/pages/login',
								});
							}, 1000);
						} else if (res.cancel) {
							//console.log('用户点击取消');
						}
					}
				});
			},
			// 签到
			signInApi() {
				this.$http.get(`http://api.9api.top:9999/utils/signIn`, {}, {
					'token': uni.getStorageSync("token")
				}).then(res => {
					if (res.code != 0) {
						// 登录失败
						uni.showToast({
							title: res.msg,
							icon: 'error',
							duration: 1500
						})
					} else if (res.code == 0) {
						uni.showToast({
							title: res.msg,
							icon: 'error',
							duration: 1500
						})

						setTimeout(() => {
							this.userInfoApi()
						}, 800);

					}
				})
			},
			// 联系我们
			contactAuthorApi() {
				uni.showModal({
					title: 'QQ群号',
					// 提示文字
					content: '597727916',
					// 取消按钮的文字自定义
					cancelText: "取消",
					// 确认按钮的文字自定义
					confirmText: "复制群号",
					//删除字体的颜色
					confirmColor: 'red',
					//取消字体的颜色
					cancelColor: '#000000',
					success: (res) => {
						if (res.confirm) {
							// 执行确认后的操作
							let str = '597727916'
							this.copyTitle(str)
						} else {
							// 执行取消后的操作
						}
					}
				})
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
			}

		}
	}
</script>

<style lang="scss" scoped>
	.flex1-clz {
		margin-left: 10px;
		width: calc(100% - 10px - 10px) !important;
		margin-top: 10px;
		margin-bottom: 0px;
		margin-right: 10px;
	}

	.image-clz {
		margin-left: 5px;
		border: 1px solid rgba(240, 240, 240, 0.17);
		border-bottom-left-radius: 30px;
		box-shadow: 0px 1px 3px rgba(242, 242, 242, 0.49);
		overflow: hidden;
		border-top-left-radius: 30px;
		margin-top: 0px;
		border-top-right-radius: 30px;
		border-bottom-right-radius: 30px;
		margin-bottom: 0px;
		margin-right: 5px;
	}

	.flex2-clz {
		flex: 1;
	}

	.text1-clz {
		margin-left: 0px;
		font-weight: bold;
		font-size: 16px !important;
		margin-top: 0px;
		margin-bottom: 5px;
		text-align: center;
		margin-right: 0px;
	}

	.flex5-clz {
		background-color: #dbe4e9;
		padding-top: 0px;
		border-bottom-left-radius: 20px;
		overflow: hidden;
		padding-left: 0px;
		padding-bottom: 0px;
		border-top-left-radius: 20px;
		border-top-right-radius: 20px;
		border-bottom-right-radius: 20px;
		padding-right: 3px;
	}

	.icon-clz {
		color: #979797;
	}

	.icon {
		font-size: 17px;
	}

	.list1-clz {
		background-color: #0f8f8f;
		margin-left: 5px;
		border-bottom-left-radius: 10px;
		overflow: hidden;
		width: calc(100% - 5px - 5px) !important;
		border-top-left-radius: 10px;
		margin-top: 10px;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 10px;
		margin-bottom: -3px;
		margin-right: 5px;
	}

	.flex8-clz {
		padding-top: 10px;
		border-bottom-left-radius: 8px;
		color: #ffffff;
		padding-left: 10px;
		padding-bottom: 10px;
		border-top-right-radius: 8px;
		margin-right: 10px;
		margin-left: 10px;
		overflow: hidden;
		width: calc(100% - 10px - 10px) !important;
		border-top-left-radius: 8px;
		margin-top: 15px;
		border-bottom-right-radius: 8px;
		background-image: linear-gradient(326deg, rgba(48, 51, 75, 1) 0%, rgba(52, 56, 88, 1) 33%, rgba(60, 63, 85, 1) 33%, rgba(48, 51, 75, 1) 100%);
		margin-bottom: 0px;
		padding-right: 10px;
	}

	.text6-clz {
		margin-left: 0px;
		flex: 1;
		font-size: 14px !important;
		margin-top: 5px;
		margin-bottom: 5px;
		margin-right: 0px;
	}

	.text8-clz {
		padding-top: 5px;
		border-bottom-left-radius: 30px;
		padding-left: 15px;
		font-size: 14px !important;
		padding-bottom: 5px;
		border-top-right-radius: 30px;
		margin-right: 0px;
		margin-left: 0px;
		overflow: hidden;
		border-top-left-radius: 30px;
		margin-top: 5px;
		border-bottom-right-radius: 30px;
		background-image: linear-gradient(135deg, #fccf31 10%, #f55555 100%);
		margin-bottom: 5px;
		padding-right: 15px;
	}

	.flex3-clz {
		padding-top: 10px;
		border-bottom-left-radius: 8px;
		padding-left: 10px;
		padding-bottom: 10px;
		border-top-right-radius: 8px;
		margin-right: 10px;
		background-color: #ffffff;
		margin-left: 10px;
		box-shadow: 0px 0px 18px rgba(142, 137, 137, 0.24);
		overflow: hidden;
		width: calc(100% - 10px - 10px) !important;
		border-top-left-radius: 8px;
		margin-top: 15px;
		border-bottom-right-radius: 8px;
		margin-bottom: 0px;
		padding-right: 10px;
	}

	.text4-clz {
		font-weight: bold;
		flex: 1;
		font-size: 14px !important;
	}

	.grid-item-clz {
		font-size: 12px;
		margin: 0px;
		padding: 10px;
	}

	.grid-icon-clz {
		width: 40px;
		height: 40px;
		font-size: calc(40px - 4px) !important;
	}

	.grid-badge-0 {
		padding-left: 0px;
		padding-right: 0px;
	}

	.flex4-clz {
		padding-top: 10px;
		border-bottom-left-radius: 8px;
		padding-left: 10px;
		padding-bottom: 10px;
		border-top-right-radius: 8px;
		margin-right: 10px;
		background-color: #ffffff;
		margin-left: 10px;
		box-shadow: 0px 0px 18px rgba(142, 137, 137, 0.24);
		overflow: hidden;
		width: calc(100% - 10px - 10px) !important;
		border-top-left-radius: 8px;
		margin-top: 15px;
		border-bottom-right-radius: 8px;
		margin-bottom: 0px;
		padding-right: 10px;
	}

	.text3-clz {
		font-weight: bold;
		flex: 1;
		font-size: 14px !important;
	}

	.grid2-item-clz {
		font-size: 12px;
		margin: 0px;
		padding: 10px;
	}

	.grid2-icon-clz {
		width: 30px;
		height: 30px;
		font-size: calc(30px - 4px) !important;
	}

	.flex6-clz {
		padding-top: 15px;
		border-bottom-left-radius: 8px;
		padding-left: 10px;
		padding-bottom: 15px;
		border-top-right-radius: 8px;
		margin-right: 10px;
		background-color: #ffffff;
		margin-left: 10px;
		box-shadow: 0px 0px 18px rgba(142, 137, 137, 0.24);
		overflow: hidden;
		width: calc(100% - 10px - 10px) !important;
		border-top-left-radius: 8px;
		margin-top: 15px;
		border-bottom-right-radius: 8px;
		margin-bottom: 0px;
		padding-right: 10px;
	}

	.text7-clz {
		font-weight: bold;
		flex: 1;
		font-size: 14px !important;
	}

	.icon3 {
		font-size: 16px;
	}

	.flex-clz {
		padding-top: 15px;
		border-bottom-left-radius: 8px;
		padding-left: 10px;
		padding-bottom: 15px;
		border-top-right-radius: 8px;
		margin-right: 10px;
		background-color: #ffffff;
		margin-left: 10px;
		box-shadow: 0px 0px 18px rgba(142, 137, 137, 0.24);
		overflow: hidden;
		width: calc(100% - 10px - 10px) !important;
		border-top-left-radius: 8px;
		margin-top: 15px;
		border-bottom-right-radius: 8px;
		margin-bottom: 0px;
		padding-right: 10px;
	}

	.text-clz {
		font-weight: bold;
		flex: 1;
		font-size: 14px !important;
	}

	.icon1 {
		font-size: 16px;
	}

	.container {
		padding-left: 0px;
		padding-right: 0px;
		background-color: #f4f4f4;

		font-size: 12px;
	}

	.container {
		padding-bottom: 80px;
	}
</style>
