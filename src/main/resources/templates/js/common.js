/*获取地址栏参数 */
$.request = (function () { 
    var apiMap = {}; 
    function request(queryStr) { 
        var api = {}; 
        if (apiMap[queryStr]) { return apiMap[queryStr]; } 
        api.queryString = (function () { 
            var urlParams = {}; 
            var e, 
            d = function (s) { return decodeURIComponent(s.replace(/\+/g, " ")); }, 
            q = queryStr.substring(queryStr.indexOf('?') + 1), 
            r = /([^&=]+)=?([^&]*)/g; 
            while (e = r.exec(q))     urlParams[d(e[1])] = d(e[2]);
            return urlParams; 
        })(); 
        api.getUrl = function () { 
            var url = queryStr.substring(0, queryStr.indexOf('?') + 1); 
            for (var p in api.queryString) { url += p + '=' + api.queryString[p] + "&";        } 
            if (url.lastIndexOf('&') == url.length - 1) { return url.substring(0, url.lastIndexOf('&')); } 
            return url; 
        } 
        apiMap[queryStr] = api; 
        return api; 
    } 
    $.extend(request, request(window.location.href)); 
    return request; 
})();

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function login(loginUrl) {
    if (loginUrl == null || loginUrl.length < 1) {
        loginUrl = "/login";
    }
    
    clearToken();
    clearCachUser();
    clearCachUserQRCode();
    
    var loginUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&redirect_uri=" + loginUrl+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    window.location.replace(loginUrl);
}

function getToken() {
    return 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqd3QtaWQiOiI1YTk3M2IwYy0yYjc5LTRmZGQtYTBiMS0yMWEyOWRhN2VlYzMiLCJvcGVuSWQiOiJvdVlVYzUyVTBTYm1JdFdwRmlBOV9oWk1RaUtrIiwiY3VzdG9tZXJJZCI6IjMiLCJleHAiOjE2MDAwMjQ3OTV9.zJrLQE9q7KwBpfwNej5Xcpcq-zWkNApR8jc5ROAvI7w';
    // return window.localStorage.getItem("token");
}

function setToken(token) {
    window.localStorage.setItem("token", token);
}

function clearToken() {
    window.localStorage.setItem("token", "");
}

function getUserId() {
    // return window.localStorage.getItem("userId");
    return 1;
}

function setUserId(id) {
    window.localStorage.setItem("userId", id);
}

function getOpenId() {
    return window.localStorage.getItem("openId");
}

function setOpenId(id) {
    window.localStorage.setItem("openId", id);
}

function getPusherId() {
    return window.localStorage.getItem("pusherId");
}

function setPusherId(id) {
    window.localStorage.setItem("pusherId", id);
}

function getPhoneNum() {
    return window.localStorage.getItem("phoneNum");
}

function setPhoneNum(num) {
    window.localStorage.setItem("phoneNum", num);
}

function showToast(msg) {
    $('.showtoast').remove();
    var _html = '<div class="showtoast">' + msg + '</div>';
    $("body").append(_html);
    setTimeout(function () {
        $('.showtoast').remove();
    }, 2000)
}

function showloading() {
    var _htm = '<div id="loadingToast">' +
        '<div class="weui-mask_transparent"></div>' +
        '<div class="weui-toast">' +
        '<i class="weui-loading weui-icon_toast"></i>' +
        '<p class="weui-toast__content">数据加载中</p>' +
        '</div>' +
        '</div>'
    $("body").append(_htm);
}

function hideloading() {
    $('#loadingToast').remove();
}

function getIp() {
    return 'http://xxf.showcoder.com';
}

function getRequest(url, token, params, success, fail) {
    var ajaxParam = {
        url: getIp() + url,
        type: "GET",
        timeout: 5000, //超时时间设置，单位毫秒
        success: function (data) {
            console.log('success get', url, params, data);
            if (typeof success == 'function') {
                success(data);
            }
        },
        error: function (data) {
            console.log('fail get', url, params, data);
            if (data.status == 1900) {
                login(location.href);
                return;
            }
            if (typeof fail == 'function') {
                fail(data)
            }
        }
    }
    if (token != null && token.length > 0) {
        ajaxParam.beforeSend = function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        };
    }
    if (params != null) {
        ajaxParam.data = params;
    }
    $.ajax(ajaxParam);
}

function postRequest(url, token, params, success, fail) {
    var ajaxParam = {
        url: getIp() + url,
        type: "POST",
        contentType: "application/json",
        success: function (data) {
            console.log('success post', url, params, data);
            if (typeof success == 'function') {
                success(data);
            }
        },
        error: function (data) {
            console.log('fail post', url, params, data);
            if (data.status == 1900) {
                login(location.href);
                return;
            }
            if (typeof fail == 'function') {
                fail(data)
            }
        }
    }
    if (token != null && token.length > 0) {
        ajaxParam.beforeSend = function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        };
    }
    if (params != null) {
        ajaxParam.dataType = "json";
        ajaxParam.data = JSON.stringify(params);
    }
    $.ajax(ajaxParam);
}

function postRequest2(url, token, params, success, fail) {
    var ajaxParam = {
        url: getIp() + url,
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        data: params,
        contentType: false,
        processData: false,
        success: function (data) {
            console.log('success post', url, params, data);
            if (typeof success == 'function') {
                success(data);
            }
        },
        error: function (data) {
            console.log('fail post', url, params, data);
            if (data.status == 1900) {
                login(location.href);
                return;
            }
            if (typeof fail == 'function') {
                fail(data)
            }
        }
    }
    if (token != null && token.length > 0) {

        ajaxParam.beforeSend = function (xhr) {
            xhr.setRequestHeader("Authorization", token);
        };
    }
    // if (params != null) {
    //     ajaxParam.dataType = "json";
    //     ajaxParam.data = JSON.stringify(params);
    // }
    $.ajax(ajaxParam);
}


function cachUser(name, avatar) {
    window.localStorage.setItem('user', JSON.stringify({
        name: name,
        avatar: avatar,
        // expiration: new Date().getTime() + 1800000
    }));
}

function getCachUser() {
    var userStr = window.localStorage.getItem('user');
    if (userStr == null || userStr == "") return null;
    var user = JSON.parse(userStr);
    if (user == null) return null;
    // if (user.expiration < new Date().getTime()) {
    //     return null;
    // }
    return user;
}

function clearCachUser() {
    window.localStorage.setItem('user', "");
}

function cachUserQRCode(qrcode) {
    window.localStorage.setItem('qrcode', qrcode);
}

function getCachUserQRCode() {
    return window.localStorage.getItem('qrcode');
}

function clearCachUserQRCode() {
    window.localStorage.setItem('qrcode', "");
}

window.onerror = function () {
    var errInfo = JSON.stringify(arguments);
    alert(errInfo);
    return true;
};

/**获取当前时间 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function getCurrentDate(time) {
    return time.Format("yyyy-MM-dd HH:mm:ss");
};
