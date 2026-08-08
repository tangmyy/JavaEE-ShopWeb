/**
 * 购物车交互脚本
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

    // 监听每个单选项的变化
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
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data.loggedIn === false) {
                window.location.href = "login.jsp";
            } else if (data.success) {
                document.querySelector('.total-price').textContent = data.total;
            }
        })
        .catch(error => console.error('Error:', error));
}

function updateAllCheckedBox(isChecked) {
    fetch(`ShopCartController?serviceType=updateAllCheckedBox&commodityId=${isChecked}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data.loggedIn === false) {
                window.location.href = "login.jsp";
            } else if (data.success) {
                document.querySelector('.total-price').textContent = data.total;
            }
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
                // 添加购物车成功后跳转到购物车页面
                window.location.href = "myshopcart.jsp";
            } else {
                alert("添加购物车失败，请稍后再试！");
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
                    document.querySelector('main').innerHTML = '<h1>购物车空了，快去添加商品吧！</h1>';
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

    // 当数量为1且用户点击减号时，给出提示并返回
    if (Num === 1 && changeNum === -1) {
        alert("注意：商品数量不能为1，再减不可啦");
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
                alert("数量修改失败，请稍后再试！");
            }
        })
        .catch(error => console.error('Error:', error));
}