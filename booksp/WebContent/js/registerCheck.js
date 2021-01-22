/**
 *网上书城，验证用户注册
 */
// JavaScript Document
function $(elementId){
	return document.getElementById(elementId);
}

/*set innerHTML*/
function setInnerHTML(elementId,htmlContent){
	$(elementId).innerHTML = htmlContent;
}

function myRegExp_3(reg,input_elementId,prompt_elementId,null_prompt,err_prompt){
	var reg = new RegExp(reg);
	//debugger;
	var obj = $(input_elementId);
	if(reg.test(obj.value)){
		setInnerHTML(prompt_elementId,"ok");
		return true;
	}
	if(obj.value == ''){
		setInnerHTML(prompt_elementId,null_prompt);
		return false;
	}
	setInnerHTML(prompt_elementId,err_prompt);
	return false;
}

function repassword_3(pwd_elementId,repwd_elementId,prompt_elementId,null_prompt,err_prompt){
	var pwd = $(pwd_elementId);	
	var repwd = $(repwd_elementId);
	if(pwd.value == repwd.value && repwd.value != ''){
		setInnerHTML(prompt_elementId,"ok");
		return true;
	}
	if(repwd.value == ''){
		setInnerHTML(prompt_elementId,null_prompt);
		return false;
	}
	setInnerHTML(prompt_elementId,err_prompt);
	return false;
}

$("userName").onblur = function(){
	var result = myRegExp_3(/^[a-zA-Z][a-zA-Z0-9]{3,19}$/,"userName","userName_prompt","用户名不能为空","用户名格式错误，请用大小写英文字母、数字，长度4-20个字符");
	if(result){
		//去数据库验证
	}
}

$("passWord").onblur = function(){
	myRegExp_3(/^[a-zA-Z0-9]{6,20}$/,"passWord","passWord_prompt","密码为必填项，请设置您的密码","密码格式错误，请用大小写英文字母、数字，长度6-20个字符");
}

$("rePassWord").onblur = function(){
	repassword_3("passWord","rePassWord","rePassWord_prompt","请再次输入您的密码","两次密码不一致，再输入");
}

$("email").onblur = function(){
	myRegExp_3(/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/,"email","email_prompt","电子邮件是必填项，请输入您的Email地址","电子邮件格式不正确，请重新输入");
}

function checkForm(){
	var username = myRegExp_3(/^[a-zA-Z][a-zA-Z0-9]{3,19}$/,"userName","userName_prompt","用户名不能为空","用户名格式错误，请用大小写英文字母、数字，长度4-20个字符");
	var pwd = myRegExp_3(/^[a-zA-Z0-9]{6,20}$/,"passWord","passWord_prompt","密码为必填项，请设置您的密码","密码格式错误，请用大小写英文字母、数字，长度6-20个字符");
	var rpwd = repassword_3("passWord","rePassWord","rePassWord_prompt","请再次输入您的密码","两次输入密码不一致，请重新输入");
	var email = myRegExp_3(/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/,"email","email_prompt","电子邮件是必填项，请输入您的Email地址","电子邮件格式不正确，请重新输入");
	if(email && username && pwd && rpwd){
		return true;
	}
	return false;
}
$("regForm").onsubmit = checkForm;