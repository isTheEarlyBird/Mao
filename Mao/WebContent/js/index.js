$(function () {
    banner();
    category();
});
// 轮播图
function banner() {
    var $imgs = $(".banner-img ul");
    var $dots = $(".dots");
    var lastIndex = 0; //上一个图片id
    var index = 0; //当前图片id
    var timer; // 定时器
    // 模拟轮播图图片的数据
    var data = {
        data : [
            {"src" : "images/banner1.jpg"},
            {"src" : "images/banner2.jpg"},
            {"src" : "images/banner3.jpg"},
            {"src" : "images/banner4.jpg"}
        ]
    };
    // 到时候将自调用函数该成getData函数
    (function (data) {
        // template/轮播图片
        // $($imgs).html(template("bannerImg", data));
        $($imgs).html(Handlebars.compile($("#bannerImg").html())(data));
        // template/轮播长方形
        // $dots.children("ul").html(template("bannerDots", data));
        $dots.children("ul").html(Handlebars.compile($("#bannerDots").html())(data));
        // 居中轮播圆点
        $dots.css("marginLeft", -$dots.width() / 2);

        autoChange(data);
        clickDots();
    })(data);
    /*function getData(callback) {
        $.ajax({
            url : "...",
            data : "",
            dataType : "json",
            type : "get",
            success : function (data) {
                callback || callback(data);
            }
        })
    }*/

    // 点击长方形
    function clickDots() {
        var list = $(".dots li");
        list.on("click", function () {
            var $this = $(this);
            index = $this.attr("data-id");
            $this.addClass("now").siblings().removeClass("now");
            changeImg(index);
        });

    }
    // 自动播放图片
    function autoChange(data) {
        timer = setInterval(function () {
            index ++;
            if (index >= data.data.length) {
                index = 0;
            }
            changeImg(index);
            $(".dots li").eq(index).addClass("now").siblings().removeClass("now");
        }, 3000);

    }
    // 改变当前图片
    function changeImg(index) {
        $(".banner-img li").eq(lastIndex).fadeOut();
        $(".banner-img li").eq(index).fadeIn();
        lastIndex = index;
    }
}
// 分类的显示与隐藏
function category() {
    var $cateRight =  $(".cate_right");
    var id;
     $(".category li").on("mouseenter", function () {
         // 获取当前id
         id = $(this).attr("data-id");
         $cateRight.eq(id).css("display", "block");
     });
     $(".category li").on("mouseleave", function () {
         $cateRight.eq(id).css("display", "none");
     });
 }
// 商品区域
/*function goodsFirst() {
    // 模拟数据
    var data = {
        data : [
            {
                "id" : "00",
                "src" : "images/market1.jpg",
                "title" : "白猫柠檬薄荷洗洁精2kg * 2温和不伤手清新易过水",
                "money" : "22.9"
            },
            {
                "id" : "01",
                "src" : "images/market2.jpg",
                "title" : "轩尧雷笋400g",
                "money" : "15.8"
            },
            {
                "id" : "02",
                "src" : "images/market3.jpg",
                "title" : "怡达0添加山楂条380g 山楂制品 山楂蜜饯果脯 休闲零食",
                "money" : "17.9"
            },
            {
                "id" : "03",
                "src" : "images/market4.jpg",
                "title" : "葡萄枝子除尘掸可弯曲伸缩家用手柄鸡毛掸颜色随机",
                "money" : "29.9"
            },
            {
                "id" : "04",
                "src" : "images/market5.jpg",
                "title" : "GP超霸7号40粒电池 七号高能无汞碳性干电池儿童玩具遥控器AAA",
                "money" : "22"
            },
            {
                "id" : "05",
                "src" : "images/market6.jpg",
                "title" : "雕牌全渍净洗衣液1.7kg*2依兰幽香深层去渍 柔软舒适",
                "money" : "59.9"
            },
            {
                "id" : "06",
                "src" : "images/market7.jpg",
                "title" : "【日本】beautybar 24KT头黄金棒美容棒瘦脸震动V脸美容仪神器",
                "money" : "499"
            },
            {
                "id" : "07",
                "src" : "products/market8.jpg",
                "title" : "Shiseido/资生堂UNO男士泡沫洗面奶蓝色超保湿洁净洁面乳130g*2",
                "money" : "59"
            },
        ],
    };
    // $(".middleFirst").html(template("temp-goods1", data));
    $(".middleFirst").html(Handlebars.compile($("#temp-goods1").html())(data));
}*/
/*function goodsSecond() {
    // 模拟数据
    var data = {
        data : [
            {
                "id" : "08",
                "src" : "images/08-0.jpg",
                "title" : "6瓶 好健康goodhealth牡蛎片胶囊新西兰生蚝精oyster增大plus男性",
                "money" : "479"
            },
            {
                "id" : "09",
                "src" : "products/09-0.jpg",
                "title" : "中村树之惠进口润理肠胃天然足贴脚贴足底贴30片 西柚香型",
                "money" : "95"
            },
            {
                "id" : "10",
                "src" : "images/10-0.jpg",
                "title" : "【直营】美国进口钙尔奇钙片+维生素D矿物质150粒迷你颗粒",
                "money" : "119"
            },
            {
                "id" : "11",
                "src" : "images/11-0.jpg",
                "title" : "广州保税发澳洲原装进口TOM天然棉超薄进口卫生巾夜用275mm*10片",
                "money" : "59"
            },
            {
                "id" : "12",
                "src" : "images/12-0.jpg",
                "title" : "韩国进口正品爱茉莉86麦迪安Median清新美白去渍去口臭牙膏3支装",
                "money" : "48"
            },
            {
                "id" : "13",
                "src" : "images/13-0.jpg",
                "title" : "乔丹男鞋全黑色越野跑步鞋大网眼面透气运动鞋猫爪越野爬山旅游鞋",
                "money" : "179"
            },
            {
                "id" : "14",
                "src" : "images/14-0.jpg",
                "title" : "亏本清仓打折  卡拉羊拉杆箱密码箱行李箱20吋n24吋旅行箱",
                "money" : "129"
            },
            {
                "id" : "15",
                "src" : "images/15-0.jpg",
                "title" : "NIANJEEP吉普盾男士休闲夹克2018款纯棉短款夹克衫宽松大码外套男",
                "money" : "128"
            },
        ],
    };
    // $(".middleSecond").html(template("temp-goods2", data))
    $(".middleSecond").html(Handlebars.compile($("#temp-goods2").html())(data))
}*/