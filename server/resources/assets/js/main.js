//pagination -datatables
function drawTable(tableId, table){
//	var table =  $('#'+tableId).DataTable( {
//         "bLengthChange": false,
//         "iDisplayLength": 15,
//         "info": false,
//         "aoColumnDefs": [
//                          { 'bSortable': false, 'aTargets': [ 4 ] }
//                       ]
//   } );
    
    if(tableId === "orderHistoryTable"){
    	 $('#pending, #approved, #rejected, #cancelled').click( function() {
    	        table.draw();
    	    } );
    	    $.fn.dataTable.ext.search.push(
    			    function( settings, data, dataIndex ) {
    			        var pending = document.getElementById('pending').checked;
    			         var approved = document.getElementById('approved').checked;
    			          var rejected = document.getElementById('rejected').checked;
    			          var cancelled = document.getElementById('cancelled').checked;
    			        
    			     
    			        var status = data[2].trim(); // use data for the order status column
    			 		

    			          if((pending && approved && rejected && cancelled) || (!pending && !approved && !rejected && !cancelled)){
    			        	  console.log(data[0].trim())
    			          	return true;
    			          }else if(pending && (status === "Pending")){
    			          		return true;
    			          }else if(approved && (status === "Approved")){
    			          		return true;
    			          }else if(rejected && (status === "Rejected")){
    			          		return true;
    			          }else if(cancelled && (status === "Cancelled")){
  			          			return true;
    		              }
    			        return false;
    			    }
    			);
    }
    $('#searchInput').keyup(function(){
        table.search($(this).val()).draw() ;
  })
    	$('.search-close').click(function(){
    		$('#searchInput').val('');
    		table.search('').draw();
	})
}
function escapeHtml(text) {
	      return text
	          .replace(/&/g, "&amp;")
	          .replace(/</g, "&lt;")
	          .replace(/>/g, "&gt;")
	          .replace(/"/g, "&quot;")
	          .replace(/\\'/g, "&#039;");
	    }


function changeQuantity(size, quantity) {
	
	var sizeObj = document.getElementById(size);
	var quantityObj = document.getElementById(quantity);
	var quantityValue = sizeObj.options[sizeObj.selectedIndex].value;
	document.getElementById(quantity).innerHTML = quantityValue;
}

function updateQuantity() {
	var sizeObj = document.getElementById("itemSizeId");
	if (sizeObj == null) {
		return;
	}
	var quantityObj = document.getElementById("quantity1");
	itemQuantity[sizeObj.selectedIndex] = quantityObj.textContent;
}

function clearTextBox(object) {
	var id = object.id;
	var value = document.getElementById(id).value;
	if (value.indexOf("Friend #") != -1) {
		document.getElementById(id).value = "";
	}
}

function validateTextBox(object, defaultValue) {
	var id = object.id;
	var value = document.getElementById(id).value;
	if (value.trim().length == 0) {
		document.getElementById(id).value = defaultValue;
		return;
	}
}

function increasePurchaseQuantity(id) {
	var text = document.getElementById(id);
	text.style.backgroundColor = "#ffffff";
	if (parseInt(text.value) >= 0 && parseInt(text.value) < 99) {
		text.value = parseInt(text.value) + 1;
	} else if (parseInt(text.value) >= 99) {
		text.value = 99;
	} else {
		text.value = 0;
	}
}

function decreasePurchaseQuantity(id) {
	var text = document.getElementById(id);
	text.style.backgroundColor = "#ffffff";
	if (parseInt(text.value) <= 99 && parseInt(text.value) > 0) {
		text.value = parseInt(text.value) - 1;
	} else if (parseInt(text.value) > 99) {
		text.value = 99;
	} else if (parseInt(text.value) <= 0) {
		text.value = 0;
	} else {
		text.value = 0;
	}
}

function clearRedBackground(id) {
	var textBox = document.getElementById(id);
	textBox.style.backgroundColor = "#ffffff";
}

function addItemToCart(itemCode, purchaseQty, sizeId, itemPrice, index) {
	if (index < 1) {
		return;
	}
	var purchaseElement = document.getElementById(purchaseQty);
	var sizeIdElement = document.getElementById(sizeId);
	var size = -1;
	var optionElement;
	if (sizeIdElement != null) {
		optionElement = sizeIdElement.options[sizeIdElement.selectedIndex];
		size = sizeIdElement.options[sizeIdElement.selectedIndex].text;
	}
	var qty = purchaseElement.value; // purchase quantity
	Number.isInteger = Number.isInteger
			|| function(value) {
				return typeof value === "number" && isFinite(value)
						&& Math.floor(value) === value;
			};
	if (!Number.isInteger(Number(qty))) {
		displayMessage("Invalid purchase quantity");
		purchaseElement.style.backgroundColor = "#ff0000";
		return;
	}
	var itemTotal = qty * itemPrice;

	var quantity = document.getElementById("quantity" + index); // existing
																// inventory
																// quantity
	// Check 1 - Size selected?
	if (quantity == null) {
		displayMessage("Please select a size first.");
		return;
	}
	var purchased = document.getElementById("purchased" + index); // purchase
																	// quantity
																	// (i.e.
																	// same as
																	// qty)
	// second check - is purchase qty <= 0
	if (parseInt(purchased.value) <= 0 || isNaN(Number(purchased.value))) {
		purchased.style.backgroundColor = "#ff0000";
		displayMessage("Invalid purchase quantity.");
		return;
	} else {
		purchased.style.backgroundColor = "#ffffff";

		// Check three is inventory sufficient
		if (parseInt(quantity.textContent) < parseInt(purchased.value)) {
			document.getElementById("addToCartBtn" + index).disabled = true;
			displayMessage("Your purchased quantity exceeds inventory limits.");
			purchased.value = 0;
			// Enable add to Cart now
			document.getElementById("addToCartBtn" + index).disabled = false;
			return;
		} else {
			document.getElementById("addToCartBtn" + index).disabled = false;

			// Check four is credit enough
			var prevUserBalance = parseFloat($('#userBalance').text().trim()
					.substring(1).replace(/,/g, ''));
			var cartCount = document.getElementById("cartCount");
			var value = cartCount.textContent;
			value = value.replace("(", "");
			value = value.replace(")", "");
			var tickImage = document.getElementById("tickImage" + index);

			if ((prevUserBalance - itemTotal) < 0) {
				displayMessage("Your cart total exceeds your balance!");
				purchased.value = 0;
			} else {

				$.ajax({
							type : "POST",
							url : "shoppingCart/addItem",
							data : {
								"purchasedQty" : qty,
								"itemCode" : itemCode,
								"size" : size
							},
							success : function(data) {
								if (!data) {
									displayMessage("Purchased quantity exceeds order limit!");
									purchased.value = 0;
								} else {
									cartCount.textContent = "("
											+ (parseInt(purchased.value) + parseInt(value))
											+ ")";
									quantity.textContent = parseInt(quantity.textContent)
											- parseInt(purchased.value);
									if(optionElement){
										optionElement.value = parseInt(quantity.textContent);
									}
									purchased.value = 0;
									displayMessage("The item has been added to your shopping cart successfully.");

									if (tickImage != null) {
										tickImage.style.visibility = "visible";
									}

									$('#userBalance')
											.text(
													'$'
															+ (prevUserBalance - itemTotal)
																	.toFixed(2)
																	.replace(
																			/(\d)(?=(\d{3})+\.)/g,
																			'$1,'));
									
									
								}
							}
						});

			}

		}
	}

}

function displayMessage(message) {
	var tooltipElement = document.getElementById("tool-tip-pop-up");
	$("#tool-tip-pop-up span").text(message);
	$("#tool-tip-pop-up").show();
	var timeout = function() {
		tooltipElement.style.display = "none";
	};
	setTimeout(timeout, 3000);
	clearTimeout(timeout);
}

function enableListView() {
	var singleItemId = document.getElementById("singleItemId");
	singleItemId.style.width = '100%';

}

function changeView(view) {
	var element = document.getElementById("gridListId");
	var viewElement = document.getElementById("view-input-box");
	var viewChosen;
	if (view == 1) {
		viewChosen = "gridView"

	} else {
		viewChosen = "listView";
	}
	element.className = viewChosen;
	viewElement.value = viewChosen;
	var headerElement = document.getElementById("hide-header");
	if (headerElement != null) {
		headerElement.className = viewChosen + "header";
	}

}

// Function to implement client side filtering.
function checkboxClicked() {
	var appearalChk = document.getElementById("appearalChk");
	var nonAppearalChk = document.getElementById("nonAppearalChk");
	var tier1Chk = document.getElementById("tier1Chk");
	var tier2Chk = document.getElementById("tier2Chk");
	var tier3Chk = document.getElementById("tier3Chk");

	var appearal = [];
	var tier = [];
	if (appearalChk.checked) {
		appearal.push("appearal");
	}
	if (nonAppearalChk.checked) {
		appearal.push("nonapp");
	}

	if (tier1Chk.checked) {
		tier.push("1");
	}
	if (tier2Chk.checked) {
		tier.push("2");
	}
	if (tier3Chk.checked) {
		tier.push("3");
	}

	var list = document.getElementById("gridListId");

	var liList = list.getElementsByTagName("li");
	for (var i = 0; i < liList.length; i++) {
		if (tier.length == 0 && appearal.length == 0) {
			liList[i].style.display = "block";
			continue;
		}
		var appearalFound = false;
		var tierFound = false;
		for (var j = 0; j < appearal.length; j++) {
			var name = liList[i].className;
			if (name.indexOf(appearal[j]) > -1) {
				appearalFound = true;
				break;
			}
		}
		for (var j = 0; j < tier.length; j++) {
			var name = liList[i].className;
			if (name.indexOf(tier[j]) > -1) {
				tierFound = true;
				break;
			}
		}
		if (tier.length > 0 && appearal.length > 0) {
			if (appearalFound & tierFound) {
				liList[i].style.display = "block";
			} else {
				liList[i].style.display = "none";
			}
		} else {
			if (appearalFound || tierFound) {
				liList[i].style.display = "block";
			} else {
				liList[i].style.display = "none";
			}
		}

	}

}

function enableGridView() {

}

function changeMainImage(imagePath, imageId) {

	var img = document.getElementById(imageId);
	img.src = imagePath;
}

function validatePage() {
	// var table = document.getElementById("itemDetailsTable");
	//
	// var i = table.rows.length - 1;
	// while(i > 0) {
	// var requested = document.getElementById("requested" + i);
	// var purchased = document.getElementById("purchased" + i);
	// var tickImage = document.getElementById("tickImage" + i);
	//
	// if(parseInt(requested.textContent) > 1 && parseInt(purchased.value) > 0)
	// {
	// tickImage.style.visibility = "visible";
	// }
	// i--;
	// }
}

function imgError(image) {
	image.onerror = "";
	image.style.visibility = "hidden";
	return true;
}

function pageSubmit(form) {
	document.getElementById(form).submit();
}

function loadItemPage() {

	for (var i = 1; i <= 5; i++) {
		var obj = document.getElementById("itemScollName" + i);
		var text = obj.textContent;
		if (text.length > 17) {
			text = text.substr(0, 17) + "..";
			obj.textContent = text;
		}
	}
}

function loadGroupGiftSetupPage() {
	computeCartSummary();
	hideGroupGiftAdminButtons();
	refreshBeadcrumb(1)();
}

function refreshPage() {
	var elementError = setElementError();
	var refresh = document.getElementById("refreshPage");
	if (refresh.value === 'no') {
		refresh.value = "yes";
	} else {
		refresh.value = 'no';
		location.reload();
		resetFormElements();
	}

}
function resetFormElements() {
	var rowCount = getFriendsInviteTableRows();
	for (var i = 1; i <= rowCount; i++) {
		var element = document.getElementById("name" + i);
		if (element === null) {
			continue;
		}
		element.value = '';
		element.setCustomValidity('');
		element = document.getElementById("email" + i);
		if (element === null) {
			continue;
		}

		element.value = '';
		element.setCustomValidity('');
	}
	document.getElementById("giftWrap").checked = false;
}

function nextItem() {
	var currentImageIndex = document.getElementById("currentImageIndex");
	var nextImageIndex = (parseInt(currentImageIndex.value) + 1);

	if (nextImageIndex <= productDetails.length) {
		var i = parseInt(nextImageIndex - 4);
		var j = 1;
		while (i <= nextImageIndex) {
			document.getElementById("form" + j).action = "/admin/item?itemCode="
					+ productDetails[i - 1][0];
			document.getElementById("productId" + j).value = productDetails[i - 1][0];
			var name = productDetails[i - 1][1].replace("&#39;", "'");
			document.getElementById("itemScollName" + j).textContent = name.substr(0,17)
					+ "..";
			document.getElementById("itemScollPrice" + j).textContent = productDetails[i - 1][2];
			document.getElementById("itemScollImg" + j).src = "/resources/image/item/item_"
					+ productDetails[i - 1][0] + ".png";
			i++;
			j++;
		}
		currentImageIndex.value = nextImageIndex;
	}

}

function previousItem() {
	var currentImageIndex = document.getElementById("currentImageIndex");
	var previousImageIndex = (parseInt(currentImageIndex.value) - 1);

	if (previousImageIndex >= 5) {
		var i = parseInt(previousImageIndex - 4);
		var j = 1;
		while (i <= previousImageIndex) {

			document.getElementById("form" + j).action = "/admin/item?itemCode="
					+ productDetails[i - 1][0];
			document.getElementById("productId" + j).value = productDetails[i - 1][0];
			document.getElementById("itemScollName" + j).textContent = productDetails[i - 1][1]
					.substr(0, 17)
					+ "..";
			document.getElementById("itemScollPrice" + j).textContent = productDetails[i - 1][2];
			document.getElementById("itemScollImg" + j).src = "/resources/image/item/item_"
					+ productDetails[i - 1][0] + ".png";
			i++;
			j++;
		}
		currentImageIndex.value = previousImageIndex;
	}

}

function updateCartItem(index, itemId, orderLimit, itemQty) {
	Number.isInteger = Number.isInteger
			|| function(value) {
				return typeof value === "number" && isFinite(value)
						&& Math.floor(value) === value;
			};
	var itemPrice = parseFloat($('#itemPrice' + index).text().trim().substring(
			1).replace(/,/g, ''))
	var prevItemTotal = parseFloat($('#itemTotal' + index).text().trim()
			.substring(1).replace(/,/g, ''))
	var purchased = $('#purchased' + index).val().trim()
	var prevCartCount = parseInt($('#cartCount').text().trim().substring(1)
			.slice(0, -1))
	var prevPurchased;
	if (Math.round(itemPrice) === 0) {
		var itemsCount = 0;
		for (var j = 0; j < $('#listTable input[type="text"]').length; j++) {
			var inputEleId = $('#listTable input[type="text"]')[j].id;
			if (inputEleId === $('#purchased' + index).attr('id')) {
				continue;
			}
			// var curItemTotal =
			// parseFloat($(inputEleId).parent().parent().next().text().trim()
			// .substring(1).replace(/,/g, ''))
			// var curItemPrice =
			// parseFloat($(inputEleId).parent().parent().prev().text().trim().substring(
			// 1).replace(/,/g, ''))
			itemsCount = itemsCount
					+ Math.round(parseFloat($('#' + inputEleId).parent()
							.parent().next().text().trim().substring(1)
							.replace(/,/g, ''))
							/ parseFloat($('#' + inputEleId).parent().parent()
									.prev().text().trim().substring(1).replace(
											/,/g, '')))
		}

		prevPurchased = prevCartCount - itemsCount;
	} else {
		prevPurchased = Math.round(prevItemTotal / itemPrice)
	}
	var prevCartTotal = parseFloat($('#cartTotal').text().trim().substring(1)
			.replace(/,/g, ''));

	if (!purchased || parseInt(purchased) < 1 || isNaN(purchased)
			|| !Number.isInteger(Number(purchased))) {
		showToolTip(purchased);
		$('#purchased' + index).val(prevPurchased)

	} else if (parseInt(purchased) > orderLimit && orderLimit != -1) {
		displayMessage("Purchased quantity exceeds order limit!");
		$('#purchased' + index).val(prevPurchased)
	} else if (parseInt(purchased) > itemQty) {
		displayMessage("Purchased quantity exceeds item quantity!");
		$('#purchased' + index).val(prevPurchased)
	} else {
		var prevUserBalance = parseFloat($('#userBalance').text().trim()
				.substring(1).replace(/,/g, ''))
		var newBalance = prevUserBalance + (prevPurchased * itemPrice)
				- (parseInt(purchased) * itemPrice)
		if (newBalance < 0) {
			var tooltipElement = document.getElementById("tool-tip-pop-up");
			$("#tool-tip-pop-up span").text(
					"Your cart total exceeds your balance!");
			$("#tool-tip-pop-up").show();
			var timeout = function() {
				tooltipElement.style.display = "none";
			};
			setTimeout(timeout, 3000);
			clearTimeout(timeout);
			$('#purchased' + index).val(prevPurchased)
		} else {
			$.ajax({
				type : "POST",
				url : "shoppingCart/updateItem",
				data : {
					"purchasedQty" : parseInt(purchased),
					"itemId" : itemId
				},
				success : function(data) {
					// alert('success '+data)
					$('#cartTotal').text(
							'$'
									+ data.toFixed(2).replace(
											/(\d)(?=(\d{3})+\.)/g, '$1,'));
					console.log("Previous cart count" + prevCartCount)
					console.log("Previous Purchased Count" + prevPurchased)
					console.log("new purchased" + parseInt(purchased))
					var newCartCount = parseInt(prevCartCount - prevPurchased
							+ parseInt(purchased));
					console.log("new cart count" + newCartCount)
					$('#cartCount').text("(" + newCartCount + ")");
					// updateCartCount();
					// response from controller
					// console.log("Total is: "+data);
					// $('#cartTotal').text('$'+data);
				}
			});

			var total = parseFloat($('#itemPrice' + index).text().trim()
					.substring(1).replace(/,/g, ''))
					* parseFloat($('#purchased' + index).val().trim())
			$('#itemTotal' + index).text(
					'$'
							+ total.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g,
									'$1,'))
			// $('#saveItem' + index).css("display", "none")
			// $('#updateItem' + index).css("display", "inline-block")
			var save = document.getElementById('saveItem' + index);
			var update = document.getElementById('updateItem' + index);
			save.style.display = "none"
			update.style.display = "inline-block"
			$('#userBalance').text(
					'$'
							+ newBalance.toFixed(2).replace(
									/(\d)(?=(\d{3})+\.)/g, '$1,'))
		}

		// $('#ruDelim'+index).css("display", "none")
		// updateTotal()
	}
}

function displayEditCartItem(index) {
	console.log("Input onclick")
	$('#purchased' + index).focus();
	var save = document.getElementById('saveItem' + index);
	var update = document.getElementById('updateItem' + index);
	save.style.display = "inline-block"
	update.style.display = "none"
	// $('#saveItem' + index).css("display", "inline-block")
	// $('#updateItem' + index).css("display", "none")
	// $('#ruDelim'+index).css("display", "inline-block")
	// $('#ruDelim'+index).show();
}

// function updateTotal(){
// //
// // var total = parseFloat($('#itemTotal'+index).text().trim().substring(1))
// // $('#cartTotal').text('some text')
// var total = 0.00;
// for(var i=1;i<=4;i++){
// if($('#itemTotal'+i).length){

// total = total + parseFloat($('#itemTotal'+i).text().trim().substring(1))
// console.log('updating total to: '+total)
// }
// }
// $('#cartTotal').text('$'+total.toFixed(2))
// updateCartCount();
// }

function checkItemQty(id) {
	console.log("Input changed!")
	var purchased = $('#purchased' + id).val().trim()
	if (!purchased || parseInt(purchased) < 1) {
		showToolTip(purchased);

	} else {
		$('#saveItem' + id).css("visibility", "visible")
	}
}

function removeItemFromCart(id) {
	if (confirm("Do you want to remove an item from cart?") == true) {
		var itemPrice = parseFloat($('#itemPrice' + id).text().trim()
				.substring(1))
		var prevItemTotal = parseFloat($('#itemTotal' + id).text().trim()
				.substring(1).replace(/,/g, ''))
		var purchased = parseInt($('#purchased' + id).val().trim())
		var prevCartCount = parseInt($('#cartCount').text().trim().substring(1)
			.slice(0, -1))
			var prevPurchased;
	if (Math.round(itemPrice) === 0) {
		var itemsCount = 0;
		for (var j = 0; j < $('#listTable input[type="text"]').length; j++) {
			var inputEleId = $('#listTable input[type="text"]')[j].id;
			if (inputEleId === $('#purchased' + id).attr('id')) {
				continue;
			}
			// var curItemTotal =
			// parseFloat($(inputEleId).parent().parent().next().text().trim()
			// .substring(1).replace(/,/g, ''))
			// var curItemPrice =
			// parseFloat($(inputEleId).parent().parent().prev().text().trim().substring(
			// 1).replace(/,/g, ''))
			itemsCount = itemsCount
					+ Math.round(parseFloat($('#' + inputEleId).parent()
							.parent().next().text().trim().substring(1)
							.replace(/,/g, ''))
							/ parseFloat($('#' + inputEleId).parent().parent()
									.prev().text().trim().substring(1).replace(
											/,/g, '')))
		}

		prevPurchased = prevCartCount - itemsCount;
	} else {
		prevPurchased = Math.round(prevItemTotal / itemPrice)
	}
		var prevCartCount = parseInt($('#cartCount').text().trim().substring(1)
				.slice(0, -1))
		var userBalance = parseFloat($('#userBalance').text().trim().substring(
				1).replace(/,/g, ''))
		var newBalance = userBalance + prevItemTotal;
		$.ajax({
			type : "GET",
			url : "shoppingCart/deleteItem/" + id,

			success : function(data) {
				// response from controller
				// alert('success')
				var newCartCount = parseInt(prevCartCount - prevPurchased)
				$('#cartCount').text("(" + newCartCount + ")");
				$('#userBalance').text(
						'$'
								+ newBalance.toFixed(2).replace(
										/(\d)(?=(\d{3})+\.)/g, '$1,'))
			}
		});

		// var itemPrice = $('#itemPrice'+index).text().trim()
		// var purchased = $('#purchased'+index).val().trim()
		var itemTotal = parseFloat($('#itemTotal' + id).text().trim()
				.substring(1).replace(/,/g, ''))
		console.log("Item Total" + itemTotal);
		console.log("Cart Total" + $('#cartTotal').text().trim().substring(1))
		var total = parseFloat(parseFloat($('#cartTotal').text().trim()
				.substring(1).replace(/,/g, ''))
				- itemTotal);
		console.log("New Total" + total)
		$('#cartTotal').text('$' + total.toFixed(2));

		// $('#cartItemDetail' + id).empty();
		var child = document.getElementById('cartItemDetail' + id);
		child.parentNode.removeChild(child);
		if($('#listTable tbody tr').length == 0){
			var emptyBoxEle = document.getElementById('emptyBox');
			var totalBox = document.getElementById('totalBox')
			var messageBox = document.getElementById('messageBox')
			var buyNow = document.getElementById('buyNow')
			var listTableEle = document.getElementById('listTable')

			emptyBoxEle.style.display = "block"
			totalBox.parentNode.removeChild(totalBox);
			messageBox.parentNode.removeChild(messageBox);
			buyNow.parentNode.removeChild(buyNow);
			listTableEle.parentNode.removeChild(listTableEle)
		}

	}

	// updateTotal()

}
function placeOrder(formId) {
	for (var i = 0; i < $('.saveIcon').length; i++) {
		if ($('.saveIcon')[i].style['display'] != "none") {
			displayMessage("Please save the item before placing the order.");
			return;
		}
	}
	var form = document.getElementById(formId);
	form.submit();
}
// function updateCartCount(){
// var count = 0;

// for(var i=1;i<=4;i++){
// if($('#purchased'+i).length){

// count = count + parseInt($('#purchased'+i).val().trim())
// console.log('updating count to: '+count)
// }
// }
// $('#cartCount').text('('+count+')')

// }

function getRemarks(accept) {

	if (!accept && !$("#adminRemarks").val()) {
		showToolTip();
		$('#adminRemarks').css('border-color', 'red');
	}
}

function toggleRemarksBorder() {
	$('#adminRemarks').css('border-color', '');
}

/* Functions for add-product.html page */
function showSize() {
	$(".nested-quantity-table").css("display", "table")
	$(".total-quantity").css("display", "none");
	// console.log("Showing size chart");
}

function hideSize() {
	$(".nested-quantity-table").css("display", "none");
	$(".total-quantity").css("display", "table-row");
	// console.log("Hiding size chart");
}

function showLimit() {
	$(".hide-limit").css("display", "table-row");
	// console.log("Showing limit field");
}

function hideLimit() {
	$(".hide-limit").css("display", "none");
	// console.log("Hiding limit field");
}

function clearTextField(elem) {
	if (elem.value == 0) {
		elem.value = '';
	}
}

function submitFormProduct() {
	document.getElementById("product-form").submit();
}

/* Functions for add-user.html page */

function userFormValidate() {
	// is login id there?

	$("#errors").css("display", "none");

	if (document.userForm.loginId.value == "") {
		displayErrorMessage("Login Id cannot be blank.");
		document.userForm.loginId.focus();
		return false;
	}

	if (document.userForm.manager.value == "") {
		displayErrorMessage("Manager Login Id cannot be blank.");
		document.userForm.manager.focus();
		return false;
	}

	submitFormUser();

}

function displayErrorMessage(message) {
	var errorElement = document.getElementById("error-message");
	errorElement.innerHTML = message;
	errorElement.style.visibility = 'visible';

}

function submitFormUser() {
	document.getElementById("user-form").submit();
}

function isFloat(n) {
	return n === +n && n !== (n | 0);
}
function isInteger(n) {
	return n === +n && n === (n | 0);
}

/* Functions for add item page */
function itemFormValidate() {

	// is product code there?
	var codeElement = document.getElementById("product-code");
	if (codeElement != null && codeElement.value.trim() == "") {
		displayErrorMessage("Item Code cannot be blank.");
		codeElement.value = "";
		codeElement.focus();
		return false;
	}

	var regex = new RegExp("^[a-zA-Z0-9]+$"); // allow only alphanumeric chars
												// and numbers
	if (codeElement != null && !regex.test(codeElement.value.trim())) {
		displayErrorMessage("Item code is invalid. Only alphabets and numbers are allowed.");
		codeElement.value = "";
		codeElement.focus();
		return false;
	}

	var nameElement = document.getElementById("product-name");
	if (nameElement.value.trim() == "") {
		displayErrorMessage("Product name cannot be blank.");
		nameElement.value = "";
		nameElement.focus();
		return false;
	}

	var costElem = document.getElementById("product-cost");
	var quanElem = document.getElementById("product-quantity");
	var orderLimElem = document.getElementById("product-order-limit");
	var quantityElems = document.getElementsByClassName("quantity-values");

	if (isNaN(quanElem.value) && !isInteger(costElem.value)) {
		displayErrorMessage("Quantity can only be integer");
		quanElem.focus();
		return false;
	}
	if (isNaN(orderLimElem.value) && !isInteger(orderLimElem.value)) {
		displayErrorMessage("Order Limit can only be integer");
		orderLimElem.focus();
		return false;
	}
	if (isNaN(costElem.value) && !isFloat(costElem.value)) {
		displayErrorMessage("Price field is invalid");
		costElem.focus();
		return false;
	}
	for (var i = 0; i < quantityElems.length; i++) {
		var elem = quantityElems[i];
		if (isNaN(elem.value) && !isInteger(elem.value)) {
			displayErrorMessage("Quantity field is invalid");
			elem.focus();
			return false;
		}
	}

	submitFormItem();

}

function submitFormItem() {
	document.getElementById("item-form").submit();
}

/* Functions for inventory.html */
function deleteItem(id, itemCode) {
	var answer = confirm("Are you sure you want to disable this item?");
	if (answer) {
		$.ajax({
			type : "POST",
			url : "inventory/removeItem",
			data : {
				"itemCode" : itemCode
			},
			success : function(data) {
				$("#status" + id).html("Disabled");
				// hide the red cross and show the tick
				$("#disable-item" + id).toggle();
				$("#enable-item" + id).toggle();
			}
		});
	}
}

function enableItem(id, itemCode) {
	var answer = confirm("Are you sure you want to enable this item?");
	if (answer) {
		$.ajax({
			type : "POST",
			url : "inventory/enableItem",
			data : {
				"itemCode" : itemCode
			},
			success : function(data) {
				$("#status" + id).html("Enabled");
				// hide the red cross and show the tick
				$("#disable-item" + id).toggle();
				$("#enable-item" + id).toggle();
			}
		});
	}
}

/* Functions for admin.html */
function deleteUser(id, userId) {
	var answer = confirm("Are you sure you want to make this user inactive? ");
	if (answer) {

		$.ajax({
			type : "POST",
			url : "/admin/removeUser",
			data : {
				"userId" : userId
			},
			success : function(data) {
				$("#status" + id).html("Inactive");
				$("#disable-user" + id).toggle();
				$("#enable-user" + id).toggle();
				$("#errors").css("display", "none");
				$("#success").css("display", "none");
			}
		});
	}
}

function enableUser(id, userId) {
	var answer = confirm("Are you sure you want to make this user active? ");
	if (answer) {

		$.ajax({
			type : "POST",
			url : "/admin/enableUser",
			data : {
				"userId" : userId
			},
			success : function(data) {
				$("#status" + id).html("Active");
				$("#disable-user" + id).toggle();
				$("#enable-user" + id).toggle();
				$("#errors").css("display", "none");
				$("#success").css("display", "none");
			}
		});
	}
}

/* Functions for add-credit.html page */
function submitFormCredit() {
	document.getElementById("credit-form").submit();
}

function addTotal(id, addAmount, action) {
	var buttonElem = document.getElementById(id);
	var buttons = document.getElementsByClassName("add-button");
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].style.backgroundColor = "rgb(165, 165, 165)";
	}
	buttonElem.style.backgroundColor = "#25aae1";
	var tot = 0.00;
	if (action === 'add') {
		tot = parseFloat($('#balance').text().trim().substring(1)) + addAmount;
	} else {
		if(addAmount <= parseFloat($('#balance').text().trim().substring(1)) )
		{
			tot = parseFloat($('#balance').text().trim().substring(1)) - addAmount;
		}else{
			tot = parseFloat($('#balance').text().trim().substring(1));
			displayMessage("Credit balance should not go below 0.");
		}
	}
	document.getElementById("new-total").textContent = "$"
			+ (Math.round(tot * 100) / 100);
	var amountElement = document.getElementById("amount");
	if (amountElement != null) {
		amountElement.value = addAmount;
	}
}

function floatTest(value) {
    if(/^(\-|\+)?([0-9]+(\.[0-9]+)?|Infinity)$/
      .test(value))
      return Number(value);
  return NaN;
}

function addOther(action) {
	
	var buttons = document.getElementsByClassName("add-button");
	for(var i = 0; i<buttons.length; i++){
		buttons[i].style.backgroundColor = "rgb(165, 165, 165)";
		}
	var otherAmtElement = document.getElementById('other-amt');
	var balanceElement = document.getElementById('balance');
	var balance = parseFloat(balanceElement.textContent.substring(1));
	var amount = 0;
	if (isNaN(parseFloat(otherAmtElement.value.trim())) || isNaN(floatTest(otherAmtElement.value.trim())) ) {
		tot = balance;
		displayMessage("Amount is invalid.")
	} else if (parseFloat(otherAmtElement.value.trim()) < 0) {
		tot = balance;
		displayMessage("Negative amount is not allowed")
	} else if (parseFloat(otherAmtElement.value.trim()) > balance
			&& action === 'remove') {
		tot = balance;
		displayMessage("Credit balance should not go below 0.")
	} else {
		var tot = 0.00;
		amount = parseFloat(otherAmtElement.value.trim())
		if (action === 'add') {
			tot = amount + balance;
		} else {
			tot = balance - amount;
		}
	}
	document.getElementById("new-total").value = "$"
			+ (Math.round(tot * 100) / 100);
	var amountElement = document.getElementById("amount");
	if (amountElement != null) {
		amountElement.value = amount;
	}
}

function validateCredit(form) {
	var newTotal = document.getElementById("new-total").textContent;
	if (newTotal == null || newTotal.trim().length == 0) {
		return false;
	} else {
		return pageSubmit(form);
	}
}

/* Functions for home page */
/* Getting quantity */
function modifyQuantity(elem, index) {
	var itemCode = elem.id;
	console.log(itemCode);
	var quantity = elem.options[elem.selectedIndex].value;
	console.log(quantity);
	document.getElementById('quantity' + index).innerHTML = quantity;
	

}

/* Pagination */

function viewNextPage() {
	var pageElement = document.getElementById('currentPage');
	var page = pageElement.$.ajax({
		type : "POST",
		url : "store/nextPage",
		data : {
			"currentPage" : page
		},
		success : function(data) {

		}
	});

}

function viewPreviousPage(page) {

}

/* functions for order details - admin */
function approveOrder(accept, adminId) {
	var adminRemarks = document.getElementById("adminRemarks");
	var orderNoEle = document.getElementById("orderNo");
	var orderNo = parseInt(orderNoEle.innerHTML);
	if (!accept && (!adminRemarks.value || adminRemarks.value.trim() == "")) {
		displayMessage("Please provide remarks!")
		// $('#adminRemarks').css('border-color', 'red');
		adminRemarks.style.borderColor = "red";
	} else {
		if(accept == 0){
			$('#accept').val('0');
		}else{
			$('#accept').val('1');
		}
		
		var form = document.getElementById('approvalForm');
		form.submit();
	}
}

/* functions for order history */
function filterOrders() {

	var pending = document.getElementById("pending");
	var approved = document.getElementById("approved");
	var rejected = document.getElementById("rejected");

	var filter = [];

	if (pending.checked) {
		filter.push("pending")
	}
	if (approved.checked) {
		filter.push("approved")
	}
	if (rejected.checked) {
		filter.push("rejected")
	}
	var list = document.getElementById("orderHistoryTable");

	var tableRows = list.getElementsByTagName("tr");

	for (var i = 1; i < tableRows.length; i++) {
		if (filter.length == 0) {
			tableRows[i].style.display = "table-row";
			continue;
		}
		var name = tableRows[i].getAttribute("id").toLowerCase();
		var show = 0;
		for (var j = 0; j < filter.length; j++) {
			if (name.indexOf(filter[j]) > -1) {
				show = 1;
			}
		}

		if (show == 1) {
			tableRows[i].style.display = "table-row";
		} else {
			tableRows[i].style.display = "none";
		}
	}
}

function cancelOrder(orderId, orderNo, orderTotal) {
	var prevUserBalance = parseFloat($('#userBalance').text().trim().substring(
			1).replace(/,/g, ''));
	if (confirm("Do you want to cancel the order?") == true) {
		var form = document.getElementById('cancelForm'+orderId);
		form.submit();
	}

}

function sendReminder(orderNo){
	
		$.ajax({
			type : "GET",
			url : "order/reminder/"+orderNo,

			success : function(data) {
				console.log("Response: "+data)
				if(data == 1){
					displayMessage("Reminder sent to admins for order# "+orderNo)
					var reminderBtnEle = document.getElementById("reminderBtn"+orderNo);
					reminderBtnEle.disabled = true;
					reminderBtnEle.style.backgroundColor = "buttonface"
					
					
				}
			}
		});
	
	}

function filterItems(itemList){
	console.log('clicked');
	
	
	itemList.filter(function(item){
		
		var itemClass = item.elm.className;
		var appearalChk = document.getElementById("appearalChk");
		var nonAppearalChk = document.getElementById("nonAppearalChk");
		var tier1Chk = document.getElementById("tier1Chk");
		var tier2Chk = document.getElementById("tier2Chk");
		var tier3Chk = document.getElementById("tier3Chk");

		var appearal = [];
		var tier = [];
		if (appearalChk.checked) {
			appearal.push("appearal");
		}
		if (nonAppearalChk.checked) {
			appearal.push("nonapp");
		}

		if (tier1Chk.checked) {
			tier.push("1");
		}
		if (tier2Chk.checked) {
			tier.push("2");
		}
		if (tier3Chk.checked) {
			tier.push("3");
		}

		

			if (tier.length == 0 && appearal.length == 0) {
				return true;
			}
			var appearalFound = false;
			var tierFound = false;
			for (var j = 0; j < appearal.length; j++) {			
				if (itemClass.indexOf(appearal[j]) > -1) {
					appearalFound = true;
					break;
				}
			}
			for (var j = 0; j < tier.length; j++) {
				if (itemClass.indexOf(tier[j]) > -1) {
					tierFound = true;
					break;
				}
			}
			if (tier.length > 0 && appearal.length > 0) {
				if (appearalFound & tierFound) {
					return true;
				} else {
					return false;
				}
			} else {
				if (appearalFound || tierFound) {
					return true;
				} else {
					return false;
				}
			}

	});
	if($('#gridListId li').length ==0){
		$('#recordStatus').css('display', 'inline');
	}else{
		$('#recordStatus').css('display', 'none');
	}
}
