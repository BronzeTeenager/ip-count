<template>
	<view class="container">

		<view class="flex diygw-col-24 flex-direction-column justify-center items-center flex-nowrap flex-clz">
			<view class="flex diygw-col-0 avatar-clz">
				<view class="diygw-avatar lg radius diygw-shadow bg-none">
					<image mode="aspectFit" class="diygw-avatar-img radius" src="/static//logo.jpg">
					</image>
				</view>
			</view>
		</view>
		<view class="diygw-col-24 logintitle-clz diygw-text-lg text-purple"> 注册页面 </view>
		<view class="diygw-form-item diygw-form-item-small diygw-col-24 userName-clz">
			<view class="input">
				<text class="diy-icon-my" style="color: #304ffe"></text>
				<input class="flex1" :focus="userNameFocus" name="userName" comfirm-type="done" type="text"
					v-model="userName" placeholder="请输入QQ邮箱" />
			</view>
		</view>
		<view class="diygw-form-item diygw-form-item-small diygw-col-24 password-clz">
			<view class="input">
				<text class="diy-icon-my" style="color: #304ffe"></text>
				<input class="flex1" :focus="passwordFocus" name="password" comfirm-type="done" type="text"
					v-model="password" placeholder="请输入密码" />
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex4-clz">
			<view class="diygw-form-item diygw-form-item-small diygw-col-15 checkCode-clz">
				<view class="input">
					<text class="diy-icon-my" style="color: #304ffe"></text>
					<input class="flex1" :focus="checkCodeFocus" name="checkCode" comfirm-type="done" type="text"
						v-model="checkCode" placeholder="请输入验证码" />
				</view>
			</view>
			<view class="flex diygw-col-9 button1-clz" >
				<button @tap="navigateTo" @click="emailCodeApi()"
					class="diygw-btn gradual-purple md radius margin-xs button1-button-clz">{{codeButtonName}}</button>
			</view>
		</view>
		<view class="flex diygw-col-24 button-clz">
			<button @tap="navigateTo" data-type="registerApi"
				class="diygw-btn gradual-purple md radius flex-sub margin-xs button-button-clz">点击注册</button>
		</view>
		<view class="flex flex-wrap diygw-col-24 justify-between flex1-clz">
			<view @tap="navigateTo" data-type="page" data-url="/pages/login" class="diygw-col-0 text-clz text-purple">
				登录账号 </view>
			<view @tap="navigateTo" data-type="page" data-url="/pages/resetPassword"
				class="diygw-col-0 text1-clz text-purple"> 忘记密码 </view>
		</view>
		<view class="flex flex-wrap diygw-col-24 justify-center items-center flex2-clz">
			<view class="flex diygw-col-0 line-clz">
				<view class="diygw-pzx" style="border-bottom: 1px solid #eee"></view>
			</view>
			<view class="diygw-col-0 text2-clz text-grey"> 第三方授权登录 </view>
			<view class="flex diygw-col-0 line1-clz">
				<view class="diygw-pzx" style="border-bottom: 1px solid #eee"></view>
			</view>
		</view>
		<view class="flex diygw-col-24 justify-center">
			<view class="diygw-avatar md margin-md radius bg-none">
				<image mode="aspectFit" class="diygw-avatar-img radius" src="/static/icon2_weixin.png"></image>
			</view>
			<view class="diygw-avatar md margin-md radius bg-none">
				<image mode="aspectFit" class="diygw-avatar-img radius" src="/static/icon2_wb.png"></image>
			</view>
			<view class="diygw-avatar md margin-md radius bg-none">
				<image mode="aspectFit" class="diygw-avatar-img radius" src="/static/icon2_QQ.png"></image>
			</view>
		</view>
		

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
				userNameFocus: false,
				userName: '',
				passwordFocus: false,
				password: '',
				checkCodeFocus: false,
				checkCode: '',

				codeButtonName: '发送验证码',
				count: 60,
				/*发送验证码按钮 :false禁用*/
				codeDisabled: true,
				/*判断是否发送验证码*/
				emailCodeFlag: false,
			};
		},
		onShow() {
			this.setCurrentPage(this);
		},
		onLoad(option) {
			this.setCurrentPage(this);
			if (option) {
				this.setData({
					globalOption: this.getOption(option)
				});
			}

			this.init();
		},
		methods: {
			async init() {},
			// 发送邮箱验证码
			emailCodeApi() {
				console.log("fdfdsf")
				let emailReg = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/
				if (!emailReg.test(this.userName)) {
					uni.showToast({
						title: "请输入正确QQ邮箱!",
						icon: 'error',
						duration: 1500
					})
					return;
				}

				if (this.codeDisabled == false) {
					uni.showToast({
						title: "倒计时中!",
						icon: 'error',
						duration: 1500
					})
					return;
				}

				this.$http.post(
					'http://api.9api.top:9999/utils/emailCode', {
						email: this.userName
					}, {
					}
				).then(res => {
					// 判断登录是否成功
					if (res.code === 0) {
						uni.showToast({
							title: res.msg,
							icon: 'success',
							duration: 1000
						})
						// 设置倒计时按钮
						let timeout = setInterval(() => {
							if (this.count < 1) {
								this.codeDisabled = true;
								this.codeButtonName = "发送验证码";
								this.count = 60;
								clearInterval(timeout);
							} else {
								this.codeDisabled = false;

								this.codeButtonName = this.count-- + "s后重发";
							}
						}, 1000);
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'error',
							duration: 1500
						})
					}

				});
			},
			// 注册
			registerApi() {
				let emailReg = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/
				if (!emailReg.test(this.userName)) {
					uni.showToast({
						title: "请输入正确QQ邮箱!",
						icon: 'error',
						duration: 1500
					})
					return;
				}
				let loginAndRegisterReg = /^\w{6,12}$/;
				if (!loginAndRegisterReg.test(this.password)) {
					uni.showToast({
						title: "密码长度在6-12之间!",
						icon: 'error',
						duration: 1500
					})
					return;
				}
				let checkCodeReg = /^\w{4}$/
				if (!checkCodeReg.test(this.checkCode)) {
					uni.showToast({
						title: "请输入正确验证码!",
						icon: 'error',
						duration: 1500
					})
					return;
				}

				this.$http.post(
					'http://api.9api.top:9999/user/register', {
						userName: this.userName,
						password: this.password,
						checkCode: this.checkCode
					}, {
						'Content-type': 'application/json'
					}
				).then(res => {
					// 判断登录是否成功
					if (res.code === 0) {
						uni.showToast({
							title: res.msg,
							icon: 'success',
							duration: 1000
						})
						// 跳转到登录页面
						setTimeout(function() {
							uni.redirectTo({
								url: '/pages/login',
							});
						}, 2000);
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'error',
							duration: 1500
						})
					}
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex-clz {
		z-index: 1000;
		background-repeat: no-repeat;
		background-size: contain;
		background-image: url(/static/group.png);
		background-position: center center;
		height: 180px;
	}

	.avatar-clz {
		border-bottom-left-radius: 6px;
		overflow: hidden;
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}

	.logintitle-clz {
		text-align: center;
	}

	.userName-clz {
		margin-left: 15px;
		border-bottom-left-radius: 50px;
		box-shadow: 0px 1px 3px 3px rgba(199, 181, 255, 0.2);
		overflow: hidden;
		width: calc(100% - 15px - 15px) !important;
		border-top-left-radius: 50px;
		margin-top: 25px;
		border-top-right-radius: 50px;
		border-bottom-right-radius: 50px;
		margin-bottom: 15px;
		margin-right: 15px;
	}

	.password-clz {
		margin-left: 15px;
		border-bottom-left-radius: 50px;
		box-shadow: 0px 1px 3px 3px rgba(199, 181, 255, 0.2);
		overflow: hidden;
		width: calc(100% - 15px - 15px) !important;
		border-top-left-radius: 50px;
		margin-top: 15px;
		border-top-right-radius: 50px;
		border-bottom-right-radius: 50px;
		margin-bottom: 15px;
		margin-right: 15px;
	}

	.checkCode-clz {
		margin-left: 15px;
		border-bottom-left-radius: 50px;
		box-shadow: 0px 1px 3px 3px rgba(199, 181, 255, 0.2);
		overflow: hidden;
		width: calc(62.5% - 15px - 15px) !important;
		border-top-left-radius: 50px;
		margin-top: 15px;
		border-top-right-radius: 50px;
		border-bottom-right-radius: 50px;
		margin-bottom: 15px;
		margin-right: 15px;
	}

	.button1-clz {
		margin-left: 10px;
		width: calc(37.5% - 10px - 10px) !important;
		margin-top: 10px;
		margin-bottom: 10px;
		text-align: center;
		margin-right: 10px;
	}

	.button1-button-clz {
		margin: 3px !important;
	}

	.button-clz {
		margin-left: 10px;
		width: calc(100% - 10px - 10px) !important;
		margin-top: 10px;
		margin-bottom: 10px;
		margin-right: 10px;
	}

	.button-button-clz {
		margin: 3px !important;
	}

	.flex1-clz {
		margin-left: 15px;
		z-index: 1000;
		width: calc(100% - 15px - 15px) !important;
		margin-top: 15px;
		margin-bottom: 15px;
		margin-right: 15px;
	}

	.text-clz {
		margin-left: 15px;
		font-size: 15px !important;
		margin-top: 0px;
		margin-bottom: 0px;
		text-align: center;
		margin-right: 15px;
	}

	.text1-clz {
		margin-left: 15px;
		font-size: 15px !important;
		margin-top: 0px;
		margin-bottom: 0px;
		text-align: center;
		margin-right: 15px;
	}

	.flex2-clz {
		margin-left: 15px;
		z-index: 1000;
		width: calc(100% - 15px - 15px) !important;
		margin-top: 11px;
		margin-bottom: 15px;
		margin-right: 15px;
	}

	.line-clz {
		flex: 1;
	}

	.text2-clz {
		margin-left: 8px;
		margin-top: 5px;
		margin-bottom: 5px;
		margin-right: 8px;
	}

	.line1-clz {
		flex: 1;
	}

	.container {
		padding-left: 0px;
		padding-right: 0px;

		background-size: contain;
	}

	.container {
		padding-bottom: 80px;
	}
</style>
