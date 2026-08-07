function Check() {
    return passwordCheck() && emailCheck();
}

function passwordCheck() {
    var rs_userpwd = document.getElementById("rs_userpwd").value;
    var rs_qruserpwd = document.getElementById("rs_qruserpwd").value;
    if (rs_userpwd !== rs_qruserpwd) {
        alert("请检查！两次输入的密码不一致!");
        return false;
    }
    return true;
}

function emailCheck() {
    var rs_email = document.getElementById("rs_email").value;
    var emailPattern = /^[A-Za-z0-9+_.-]+@(.+)$/;
    if (!emailPattern.test(rs_email)) {
        alert("请检查！邮箱格式不正确!");
        return false;
    }
    return true;
}

