# luanqing-inputdialog

##参数简要说明

名称|说明|默认值|是否必须
--|--|--|--
type|输入类型number & text|number|非
maxLength|最大输入位数|9999|非
placeholder|未输入前的提示文字|请输入|非
allowEmpty|是否允许输入空时点确定按钮|false|非
callback|回调方法，将带回show传入的返回标题和输入内容|无|非
multi|多输入框|false|非
type2|输入类型number & text|number|非
maxLength2|最大输入位数|9999|非
placeholde2r|未输入前的提示文字|请输入|非
showInput(title,returnData)|显示输入弹框(传入标题和确认标签以及需要回传的数据)|无|非
showMessage(title,content,returnData,onConfirm,onCancel)|显示消息内容弹框(传入标题和显示的文字内容以及需要回传的数据和确定取消的点击事件,兼容html代码格式)|无|非
close()|隐藏弹框|无|非

##使用方法：
```
<template>
	<view class="content">
		<!-- 数值类型输入对话框（默认 或type传'number'） -->
		<luanqing-inputdialog ref="inputModal" @callback="callback"></luanqing-inputdialog>
		
		<!-- 文本类型输入对话框 -->
		<luanqing-inputdialog ref="textModal" type="text" @callback="callback"></luanqing-inputdialog>
	</view>
</template>

// 使用引用调动showInput显示输入型对话框
this.$refs.inputModal.showInput("请输入金额");
this.$refs.textModal.showInput("请输入消息");

// 组件内部函数  输入完成点击确定后将通过回调给 onInputCallBack返回输入数据

有两种显示方式：1|输入型  2|消息型   函数如下
showInput(title,returnData,gameStyle,large){
	console.log("显示输入型对话框")
	this.gameStyle = gameStyle;
	this.normalDialog = false;
	this.returnData = returnData;
	this.title = title;
	this.showModal = true;
	this.large = large;
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
},

```