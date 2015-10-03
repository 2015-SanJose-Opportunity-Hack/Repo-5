
function validateTextBox(object, defaultValue) {
    var id = object.id;
    var value = document.getElementById(id).value;
    if(value.trim().length == 0) {
        document.getElementById(id).value = defaultValue;
        return;
    }
}


function changeMainImage(imagePath, imageId) {

    var img = document.getElementById(imageId);
    img.src = imagePath;
}



function refreshBeadcrumb(active) {
    return function changeBreadcrumb() {
        if(!(+active)) {
            return
        }
        var color_type;
        var data = ["Invite Friends", "Sign In", "Contribute", "Complete"];
        for (var i = 1; i <= 4; i++) {
            var element = document.getElementById("bread-crumb-complete-" + i);
            if (i < active) {
                color_type = "white-text";
                element.innerHTML = '<div class="bread-crumb-complete clean-left" ></div>' + data[i - 1];
            } else if (i === active) {
                if (i === 4) {
                    element.innerHTML = '<div class="bread-crumb-complete clean-left" ></div>' + data[i - 1];
                    color_type = "white-text";
                } else {
                    element.innerHTML = data[i - 1];
                    color_type = "active";
                }

            } else {
                element.innerHTML = data[i - 1];
                color_type = "inactive";
            }

            if( i == 1) {
                element.className = "clean-left " + color_type;
            } else {
                element.className = "keep-left " + color_type;
            }

        }
    }
}


function hideGroupGiftAdminButtons() {
    document.getElementById("editGiftBtn").style.display = "none";
    document.getElementById("cancelGiftBtn").style.display = "none";
    document.getElementById("wedding-content-logo").style.marginRight = "50px";
}


function imgError(image) {
    image.onerror = "";
    image.style.visibility = "hidden";
    return true;
}


function pageSubmit(form) {
    var formElement = document.getElementById(form).elements;
    for (var i=0;i<formElement.length;i++) {
        var element = formElement[i];
        if(element === null || element.validity === undefined) {
            continue;
        }
        if (!element.validity.valid) {
            return false;
        }
    }
    document.getElementById(form).submit();
    return true;
}

//function pageSubmit(form) {
//    var valid = true;
//    var formElement = document.getElementById(form).elements;
//    for (var i=0;i<formElement.length;i++) {
//        var element = formElement[i];
//        if(!(element instanceof HTMLInputElement && (element.type === 'text' || element.type === 'email'))) {
//            continue;
//        }
//        element.checkValidity();
//        console.log("valid : " + element.validity.valid);
//        console.log("type : " + element.type);
//        console.log("value : " + element.value);
//
//        if (element.validity.patternMismatch) {
//            valid = false;
//            element.setCustomValidity('Error');
//        } else {
//            element.setCustomValidity('');
//        }
//
//    }
//    if(valid) {
//        document.getElementById(form).submit();
//    }
//    return valid;
//}
function showItemImage() {
    $("#tooltip ").show();
}
function zoomInProduct() {
    var element = document.getElementById("zoom");
    var current =element.value;
    if(element.value === "zoom-in") {
        element.value = "zoom-out";
        document.getElementById("item-image").className = "item-image clean-left"
    } else {
        element.value = "zoom-in";
        document.getElementById("item-image").className = "item-image-zoom-in clean-left"
    }

}
function closeImage() {
    $("#tooltip").hide();
}

function showToolTip(element) {
    var tooltipElement = document.getElementById("tool-tip-pop-up");
    $("#tool-tip-pop-up").show();
    var timeout = function(){
        tooltipElement.style.display = "none";
    };

    setTimeout(timeout, 3000);
    clearTimeout(timeout);
}

function closeToolTip() {
    $("#tool-tip-pop-up").hide();
}


function zoomOutProduct(img) {
    img.style.width = "400px";
    img.style.height = "400px";
}

function getFriendsInviteTableRows() {
    var table = document.getElementById("friendInviteTable");
    var rows = table.rows.length;
    var row = Math.ceil(rows/2);
    return row;
}

function setHtmlElementAttribute() {
    return function(element, variable, value) {
        if(!(element instanceof  HTMLElement)) {
            return
        }
//        if(!(variable instanceof  "string")) {
//            return;
//        }
        element.style[variable] =value;
    }
}

function changeIndexOfElementFromTable() {
    var changeIndex = changeIndexOfElement();
    return function(tableId, ids) {
        var table = document.getElementById(tableId);
        var k = 1;
        for(var i = 0; i <= table.rows.length; i++) {
            var countIncr = false;
            for(var j = 0; j < ids.length; j++) {
                var element = document.getElementById(ids[j] + i);
                if(element === null) {
                    continue;
                }
                changeIndex(ids[j], element, k);
                countIncr = true;
            }
            if(countIncr) {
                k++;
            }
        }
    }
}

function changeIndexOfElement() {
    return function(id, element, newIndex) {
        if(element === null || !(element instanceof HTMLElement)) {
            return;
        }
        element.id = id + newIndex;
//        element.onchange = "distributeContribution('" + newIndex + "')"
    }
}

function  distributeTotalContribution(currentPosition, id, totalGift) {
    var elementError = setElementError();
    var rowCount = getFriendsInviteTableRows();
    var total = 0.0;
    var perHead = null;
    var element = null;
    return function() {
        for(var i = 1; i <= rowCount; i++) {
            element = document.getElementById(id + i);

            if(element === null)  {
                continue;
            }
            var amount = (+(element.value.replace("$", "")));
            if(amount == null || amount.length == 0 || isNaN(amount) || amount == "0") {
                elementError(element, "Gift contribution amount can not be empty or zero.", true);
                return;
            }
            if(i <= currentPosition) {
                if (amount < 0) {
                    elementError(element, "Gift contribution amount can not be negative number.", true);
                    return;
                } else if (amount < 1) {
                    elementError(element, "Gift contribution amount must be at least $1.00.", true);
                    return;
                } else if (amount >= (+totalGift)) {
                    elementError(element, "Gift contribution amount should be less than total gift cost.", true);
                    return;
                }
            }
            element.setCustomValidity('');
            if(i > currentPosition) {
                if(perHead === null) {
                    var balance = (+totalGift) - (+total);
                    perHead = (+balance) / ((+rowCount) - (+currentPosition));
                }
                if(i == rowCount) {
                    var balance = (+totalGift) - (+total);
                    element.value = "$" + balance.toFixed(2);
                } else {
                    element.value = "$" + perHead.toFixed(2);
                }
            } else {
                element.value = "$" + element.value.replace("$", "");
            }
            var currentValue = (+(element.value.replace("$", "")));

            elementError(element, "", false);
            total += currentValue;
            total = (+total.toFixed(2));
            var perHeadBalance = (((+totalGift) - (+total)) / ((+rowCount) - (+i)));
            if(((+totalGift) - (+total)) < 0) {
                elementError(element, "Total gift contribution amount should be equal to total gift cost.", true);
                return;
            } else if( perHeadBalance < 1 ){
                elementError(element, "Gift contribution distribution amount must be at least $1.00.", true);
                return;
            }
        }
        if(((+totalGift) - (+total)) > 0) {
            elementError(element, "Total gift contribution amount should be equal to total gift cost.", true);
            return;
        }
    }
}


function setElementError() {
    return function(element, message, flag) {
        if (!flag) {
            element.setCustomValidity('')
            element.style.border = "0px solid red";
            return;
        }
        element.setCustomValidity(message)
        element.style.border = "1px solid red";
        if(message.length > 1) {
            alert(message);
        }
    }
}

function validateName(element) {
    var elementError = setElementError();
    var value = element.value;
    var pattern = /^([\w-\.]+)(\]?)$/;
    if(pattern.test(value)) {
        elementError(element, '', false);
    } else {
        elementError(element, '', true);
    }
}

function validateEmail(element) {
    var elementError = setElementError();
    var value = element.value;
    var pattern = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if(pattern.test(value)) {
        elementError(element, '', false);
    } else {
        elementError(element, '', true);
    }
}