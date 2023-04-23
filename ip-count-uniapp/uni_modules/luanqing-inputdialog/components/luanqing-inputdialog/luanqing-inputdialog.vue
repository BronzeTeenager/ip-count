<template>
	<view class="modal" v-if="!normalDialog && showModal">
		<view :class="large?'incontent-large':'incontent'" v-if="showModal">
			<view class="modal_title">{{title}}</view>
						
			<radio-group v-if="gameStyle" class="radio" @change="onChangeRadio">
				<radio color="#F0AD4E" class="margin" value="2" checked="true">世界</radio>
				<radio color="#F0AD4E" class="margin" value="5">国家</radio>
				<radio color="#F0AD4E" class="margin" value="3">组队</radio>
				<radio color="#F0AD4E" class="margin" value="7">帮派</radio>
			</radio-group>
			
			<input v-if="type === 'number'" :maxlength="maxLength" type="number" v-model="inputText" :placeholder="placeholder" class="input_style text_center" />
			<textarea v-else :maxlength="maxLength" v-model="inputText" :placeholder="placeholder" class="input_area_style"/>
			<view v-if="multi" class="content margin_top_20">
				<input v-if="type2 === 'number'" :maxlength="maxLength2" type="number" v-model="inputText2" :placeholder="placeholder2" class="input_style text_center" />
				<textarea v-else :maxlength="maxLength2" v-model="inputText2" :placeholder="placeholder2" class="input_area_style"/>
			</view>
			
			<view>
				<view class="line_view"></view>
				<view class="btn_view">
					<view class="cancel_btn" @click="close">取消</view>
					<view class="line_ver"></view>
					<view class="cancel_confirm" @click="confirm">确认</view>
				</view>
			</view>
		</view>
	</view>
	
	<view class="modal" v-else-if="normalDialog && showModal">
		<view class="incontent">
			<view class="modal_title">{{title}}</view>
			
			<view v-html="content" class="dialog_content"></view>
			<view>
				<view class="line_view"></view>
				<view class="btn_view">
					<view class="cancel_btn" @click="close">取消</view>
					<view class="line_ver"></view>
					<view class="cancel_confirm" @click="confirm">确认</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				large:false,
				gameStyle:false,
				inputText:undefined,
				inputText2:undefined,
				returnData:undefined,
				showModal:false,
				messageType:2,
				title:'请输入',
				normalDialog:false,
				onConfirm:undefined,
				onCancel:undefined,
			}
		},
		props:{
			// 'number' | 'text'
			type:{
				type:String,
				default:'number'
			},
			maxLength:{
				type:Number,
				default:9999,
			},
			placeholder:{
				type:String,
				default:'请输入内容'
			},
			allowEmpty:{
				type:Boolean,
				default:false
			},
			multi:{
				type:Boolean,
				default:false
			},
			type2:{
				type:String,
				default:'number'
			},
			maxLength2:{
				type:Number,
				default:9999,
			},
			placeholder2:{
				type:String,
				default:'请输入内容'
			},
		},
		methods:{
			onChangeRadio(e){
				this.messageType = e.target.value;
			},
			showInput(title,returnData,gameStyle,large){
				console.log("显示输入型对话框")
				this.gameStyle = gameStyle;
				this.normalDialog = false;
				this.returnData = returnData;
				this.title = title;
				this.showModal = true;
				this.large = large;
				this.inputText = '';
			},
			showMessage(title,content,returnData,onConfirm,onCancel,large){
				console.log("显示消息型对话框")
				this.normalDialog = true;
				this.title = title;
				this.returnData = returnData;
				this.content = content;
				this.showModal = true;
				this.onConfirm = onConfirm;
				this.onCancel = onCancel;
				this.large = large;
				this.inputText = '';
			},
			close(){
				console.log("输入框关闭")
				this.showModal = false;
				if(this.onCancel){
					this.onCancel(this.returnData);
				}
			},
			confirm(){
				if((!this.inputText && !this.$props.allowEmpty) && !this.normalDialog){
					return;
				}
				
				let temp  = this.inputText;
				let temp2 = this.inputText2;
				const data = {returnData:this.returnData,content:temp,content2:temp2};
				console.error("类型=",this.messageType)
				if(this.gameStyle)
					data.mesType = this.messageType;
				if(this.onConfirm){
					this.onConfirm(data);
				}else{
					this.$emit('callback',data);
				}
				
				this.showModal = false;
			}
		}
	}
</script>

<style>
	.line_view{
		width: calc(70vw);
		height: 1rpx;
		background-color: #DDD;
	}
	.line_ver{
		height: 80rpx;
		width: 1rpx;
		background-color: #DDD;
	}
	.btn_view{
		width: calc(70vw);
		height: 80rpx;
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		align-items: center;
	}
	.cancel_confirm{
		color: #555;
		font-size: 0.95rem;
		font-weight: bold;
		display: flex;
		align-items: center;
		justify-content: center;
		width: calc(34vw);
		height: 80rpx;
	}
	.cancel_btn{
		color: #999;
		font-size: 0.95rem;
		font-weight: bold;
		display: flex;
		align-items: center;
		justify-content: center;
		width: calc(34vw);
		height: 80rpx;
	}
	.radio{
		margin-bottom: 15rpx;
		font-size: 0.80rem;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-between;
	}
	.margin{
		margin-left: 3rpx;
		margin-right: 3rpx;
	}
	.dialog_content{
		padding-left: 18rpx;
		padding-right: 18rpx;
		font-size: 0.85rem;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
	}
	.incontent{
		background-color: #FFF;
		border-radius: 15rpx;
		width: calc(70vw);
		min-height: calc(40vw);
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-between;
	}
	/* .incontent-large{
		background-color: #FFF;
		border-radius: 15rpx;
		width: calc(80vw);
		min-height: calc(45vw);
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-between;
	} */
	.input_area_style{
		width: 85%;
		height: 125rpx;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		padding: 8rpx 10rpx;
		border-radius: 10rpx;
		border: 1rpx solid #BBB;
		font-size: 0.85rem;
	}
	.input_style{
		width: 65%;
		padding: 8rpx 10rpx;
		border-radius: 10rpx;
		border: 1rpx solid #BBB;
		font-size: 0.85rem;
	}
	.content{
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.margin_bottom{
		margin-bottom: 12rpx;
	}
	.text_center{
		text-align: center;
	}
	.modal{
		z-index: 999;
		position: fixed;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		background:rgba(0, 0, 0, 0.4);
	    filter:alpha(opacity=60);  /*设置透明度为60%*/
		 /*非IE浏览器下设置透明度为60%*/
		/* opacity:0.6; */
		width: calc(100vw);
		height: calc(100vh);
		border: 1rpx solid #AAA;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.uni-flex-layout-row{
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-around;
	}
	.modal_title{
		color: #222;
		margin-top: 10rpx;
		margin-bottom: 20rpx;
		font-size: 1.0rem;
		font-weight: bold;
	}
</style>
