function passwordCheck() {
    var Ret_userpwd = document.getElementById("Ret_userpwd").value;
    var Ret_qruserpwd = document.getElementById("Ret_qruserpwd").value;

    if (Ret_userpwd !== Ret_qruserpwd) {
        alert("请检查！两次输入的密码不一致!");
        return false;
    }

    return true;
}


