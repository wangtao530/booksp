/**
 * 实现页面登陆，要求过滤特殊字符，防止SQL注入
 */
function checkAllTextValid(form){
	console.log(form);
	var resultTag = 0;    //记录不含引号的文本框数量
	var flag = 0;		  //记录所有文本框的数量
	for(var i = 0; i < form.elements.length; i++){
		if(form.elements[i].type=="text"){
			flag = flag + 1;
			//正则表达式为过滤的特殊符号
			if(/^[^\|"'<>-]*$/.test(form.elements[i].value)){
				resultTag = resultTag + 1;
			}else{
				form.elements[i].select();
			}
			
		}
	}
	/**如果含引号的文本框数量等于全部文本框数量，则校验通过 */
	if(resultTag == flag){
		return true;
	}else{
		//字符串换行后面加上\符号(容易出bug,不用)
		alert("文本框中不能含有\n\n 1 单引号:' \n 2 双引号:\" \n 3 竖杠:| \n 4 尖括号:<> \n 5 破折号:- \n\n请检查输入！");
		return false;
	}
}
document.getElementById("loginForm").onsubmit=function(){
	//debugger;
	return checkAllTextValid(this);
}