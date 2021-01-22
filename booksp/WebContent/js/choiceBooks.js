/**
 *选择图书，添加到购物车中
 **/

/*
var brs = document.getElementsByTagName("tr");
for(var i=0; i<brs.length;i++){
    console.log("i="+i);
    var vtd = brs[i].getElementsByTagName("td");
    for(var j = 0; j < vtd.length; j++){
        console.log("j="+j);
        console.log(vtd[j].firstElementChild);
        console.log("checked="+vtd[0].firstChild.checked);
    }
}
*/
//console.log(brs);
/*class Book {
    constructor(p_bid, p_name, p_price, p_image, p_stock) {
        this.bid = p_bid;
        this.name = p_name;
        this.price = p_price;
        this.image = p_image;
        this.stock = p_stock;
    }
}
*/
function addToCar(){
	var oTable = document.getElementById("b_table");
	var tRows = oTable.getElementsByClassName("odd");
	
	//console.log(tRows.length);
	for(var i = 0; i < tRows.length; i++){
		//debugger;
		var tCells = tRows[i].getElementsByTagName("td");
		if(tCells[0].firstElementChild.checked){
			var newRow = oTable.insertRow();
			var newCell = newRow.insertCell();
			newRow.style.display = "none";
			
			for(var j = 0; j < tCells.length; j++){
				switch(j){
					case 0:
						var inputElement_b_id = document.createElement("input");
						var b_id = tCells[j].firstElementChild.value;
						inputElement_b_id.setAttribute("type","hidden");
						inputElement_b_id.setAttribute("name","b_id");
						inputElement_b_id.setAttribute("value",b_id);
						newCell.appendChild(inputElement_b_id);
						break;
					case 1:
						var inputElement_b_name = document.createElement("input");
						var b_name = tCells[j].innerHTML;
						inputElement_b_name.setAttribute("type","hidden");
						inputElement_b_name.setAttribute("name","b_name");
						inputElement_b_name.setAttribute("value",b_name);
						newCell.appendChild(inputElement_b_name);
						break;
					case 2:
						var inputElement_b_price = document.createElement("input");
						var b_price = tCells[j].innerHTML;
						inputElement_b_price.setAttribute("type","hidden");
						inputElement_b_price.setAttribute("name","b_price");
						inputElement_b_price.setAttribute("value",b_price);
						newCell.appendChild(inputElement_b_price);					
						break;
					case 3:
						var inputElement_b_stock = document.createElement("input");
						var b_stock = tCells[j].innerHTML;
						inputElement_b_stock.setAttribute("type","hidden");
						inputElement_b_stock.setAttribute("name","b_stock");
						inputElement_b_stock.setAttribute("value",b_stock);
						newCell.appendChild(inputElement_b_stock);					
						break;
					case 4:
						var inputElement_b_image = document.createElement("input");
						var b_image = tCells[j].firstElementChild.value;
						inputElement_b_image.setAttribute("type","hidden");
						inputElement_b_image.setAttribute("name","b_image");
						inputElement_b_image.setAttribute("value",b_image);
						newCell.appendChild(inputElement_b_image);					
						break;
					default:
					//
				}
		    }
		}
		
	}
}

document.getElementById("shoppingId").onsubmit=addToCar;

