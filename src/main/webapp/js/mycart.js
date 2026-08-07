/**
 *
 */
document.addEventListener('DOMContentLoaded', (event) => {
    const selectAllCheckbox = document.getElementById('select-all');
    const itemCheckboxes = document.querySelectorAll('.item-checkbox');

    // 监听全选框的变化
    selectAllCheckbox.addEventListener('change', function() {
        const isChecked = selectAllCheckbox.checked;
        itemCheckboxes.forEach(checkbox => {
            checkbox.checked = isChecked;
        });
        updateAllCheckedBox(isChecked);
    });

    // 监听每个单选框的变化
    itemCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            const allChecked = Array.from(itemCheckboxes).every(checkbox => checkbox.checked);
            selectAllCheckbox.checked = allChecked;
            updateByCheckedBox(checkbox.dataset.id);
        });
    });
});

function updateByCheckedBox(commodityId) {
    fetch(`ShopCartController?commodityId=${commodityId}&serviceType=updateByCheckedBox`)
        .then(response => response.text())
        .then(data => {
            console.log(data);
            // 可以根据需要更新页面或进行其他操作
        })
        .catch(error => console.error('Error:', error));
}

function updateAllCheckedBox(isChecked) {
    fetch(`ShopCartController?serviceType=updateAllCheckedBox&commodityId=${isChecked}`)
        .then(response => response.text())
        .then(data => {
            console.log(data);
            // 可以根据需要更新页面或进行其他操作
        })
        .catch(error => console.error('Error:', error));
}

function addToCart(commodityId) {
    fetch(`ShopCartController?commodityId=${commodityId}&serviceType=addToCart`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            if (data.loggedIn === false) {
                window.location.href = "login.jsp";
            } else if (data.success) {
                // 加入购物车成功后跳转到购物车页面
                window.location.href = "myshopcart.jsp";
            } else {
                alert("加入购物车失败，请稍后再试！");
            }
        })
        .catch(error => console.error('Error:', error));
}

function remove(commodityId) {
    fetch(`ShopCartController?commodityId=${commodityId}&serviceType=remove`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            if (data.loggedIn === false) {
                window.location.href = "login.jsp";
            } else if (data.success) {
                var itemElement = document.querySelector(`.item[data-id="${commodityId}"]`);
                itemElement.remove();
                document.querySelector('.total-price').textContent = data.total;
                if (data.cartEmpty) {
                    document.querySelector('main').innerHTML = '<h1>购物车空空如也，赶快去购物！</h1>';
                }
            } else {
                alert("移除失败，请稍后再试！");
            }
        })
        .catch(error => console.error('Error:', error));
}

function updateByNum(commodityId, changeNum) {
    // 获取当前商品数量
    var itemElement = document.querySelector(`.item[data-id="${commodityId}"]`);
    var Num = parseInt(itemElement.getAttribute('data-num'), 10);

    // 当数量为1并且用户点击减号时，弹出提示框
    if (Num === 1 && changeNum === -1) {
        alert("注意：宝贝的数量不能再减少咯！");
        return;
    }

    // 发送Ajax请求
    fetch(`ShopCartController?commodityId=${commodityId}&serviceType=updateByNum&changeNum=${changeNum}`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            if (data.loggedIn === false) {
                window.location.href = "login.jsp";
            } else if (data.success) {
                var newNum = Num + changeNum;
                itemElement.setAttribute('data-num', newNum);
                itemElement.querySelector('.num-display').textContent = newNum;
                document.querySelector('.total-price').textContent = data.total;
            } else {
                alert("更新数量失败，请稍后再试！");
            }
        })
        .catch(error => console.error('Error:', error));
}



// function addToCart(commodityId){
//     location.href = "ShopCartController?commodityId=" +commodityId +"&serviceType=addToCart";
// }
//
// function remove(commodityId){
//     location.href = "ShopCartController?commodityId=" +commodityId +"&serviceType=remove"
// }
// function updateByNum(commodityId, changeNum){
//     // 先取到具有某属性值的 元素 （在JavaScript中，可以使用 dataset 属性来访问自定义数据属性）
//     var input = document.querySelector(`input[data-id="${commodityId}"]`);
//     // 再取该元素的属性值
//     var Num = parseInt(input.getAttribute('data-num'), 10);
//     // 当数量为1并且用户点击减号时，弹出提示框
//     if (Num === 1 && changeNum === -1) {
//         alert("注意：宝贝的数量不能在减少咯！");
//         return;
//     }
//     location.href = "ShopCartController?commodityId=" + commodityId + "&serviceType=updateByNum&changeNum=" + changeNum;
// }