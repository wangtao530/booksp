/**
 * 购物车价格实时计算
 */
function totalPrice(){
	//购物车中每一种书的总价格表
	var b_price_list = document.getElementsByClassName("ebt_price");
	var sum = 0;
	//根据购物车中每种书的总价格计算购物车中所有书的总价格
	for(var i = 0; i < b_price_list.length; i++){
	    var tmp = b_price_list[i].innerHTML;
		sum += parseFloat(tmp);
    }
	document.getElementById("count").innerHTML = sum;
}

window.onload = totalPrice;

function computePrice(obj){
	//步骤1：更新当前书的总价格 
	//获取当前元素的值,购物车中当前书的数量
	var num = obj.value;
	//当前节点的父节点
	var p_ele = obj.parentNode;
	//父节点的下一个兄弟节点
	var n_ele = p_ele.nextElementSibling;
	//下一兄弟节点的首个元素节点,当前书的单价
	var b_price = n_ele.firstElementChild;
	//下一兄弟节点的最后一个元素节点,用于显示当前书的总价格
	var t_price = n_ele.lastElementChild;
	//根据当前书的数量重新计算当前书的价格（数量*单价）
	t_price.innerHTML = parseFloat(num) * parseFloat(b_price.value);

	//步骤2：计算购物车中所有书的总价格
	totalPrice();
	//步骤3：更新完页面显示，与session中购物车同步
	//debugger;
	//获取要更新的图书ID
	var v_id = obj.parentNode.parentNode.firstElementChild.firstElementChild.value;
	//将更新后的数据添加到要提交的表单中
	var o_id = document.getElementById("u_b_id");
	var o_nums = document.getElementById("u_b_nums");
	o_id.setAttribute("value",v_id);
	o_nums.setAttribute("value",num);
	//提交表单，更新购物车中数据到session中
	document.getElementById("u_b_count").submit();
}

/**步骤4：绑定事件 */
var editList = document.getElementsByClassName("input-text");
for(var i = 0; i < editList.length; i++){
	editList[i].onblur = function(){computePrice(this);};
}

