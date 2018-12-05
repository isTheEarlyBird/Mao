
$(function() {
	
	$("#register").validate({
		rules : {
			"username" : {
				"required" : true,
				"isExist" : true
			},
			"password" : {
				"required" : true,
				"rangelength" : [ 6, 12 ]
			},
			"rePassword" : {
				"required" : true,
				"rangelength" : [ 6, 12 ],
				"equalTo" : "#password"
			},
			"name" : {
				"required" : true
			},
			"gender" : {
				"required" : true
			},
			"email" : {
				"required" : true,
				"email" : true
			}
		},
		messages : {
			"username" : {
				"required" : "会员名不能为空",
				"isExist": "会员名已存在"
			},
			"password" : {
				"required" : "密码不能为空",
				"rangelength" : "密码长度为6~12位"
			},
			"rePassword" : {
				"required" : "密码不能为空",
				"rangelength" : "密码长度为6~12位",
				"equalTo" : "两次密码不一致"
			},
			"name" : {
				"required" : "会员名不能为空"
			},
			"gender" : {
				"required" : "还没选性别呢"
			},
			"email" : {
				"required" : "邮箱不能为空",
				"email" : "邮箱格式不正确"
			}
		}
	});
	
});
