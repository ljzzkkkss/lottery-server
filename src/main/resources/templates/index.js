(function () {
    $('.weui-tabbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
    });


    var app = new Vue({
        el: '#app',
        data: {
            newsLists:[
                {
                    id:0,
                    title:'这是一篇教育资讯的内容标题最多两行这是一篇教育资讯的内容标题最多两行',
                    time:'2020.08.23',
                    hitrate:'80%',
                    img:'/templates/img/banner.png'
                },
                {
                    id:1,
                    title:'这是一篇教育资讯的内容标',
                    time:'2020.08.23',
                    hitrate:'80%',
                    img:'/templates/img/banner.png'
                },
                {
                    id:3,
                    title:'普通类本科批次A阶段录取结果可查',
                    time:'2020.08.23',
                    hitrate:'80%',
                    img:'/templates/img/banner.png'
                },
            ],
            pageNum:1,
            totalPage:0,
            pageSize:5,
            bannerImgLists:[
                {
                    id:0,
                    picPath:'/templates/img/banner.png',
                },
                {
                    id:1,
                    picPath:'/templates/img/banner.png',
                }
            ],
            articleTabs:[
                {
                    id:0,
                    name:'名家推荐'
                },
                {
                    id:1,
                    name:'赛事资讯'
                },
                {
                    id:2,
                    name:'球队资讯'
                },
            ],
            tabIndex:0
        },
        mounted: function () {
            // this.bannerLists();
            // this.init();
            // 滚动页面时触发
            // window.addEventListener("scroll", this.scrollBottom);
            let that = this;
            $('.weui_tab_bd').on('scroll',function(){
                var scrollTop = $(this).scrollTop();
                var scrollHeight = $('.centerContentBody').height();
                var windowHeight = $(this).height();
                if(scrollTop + windowHeight >=  scrollHeight){
                    that.scrollBottom();
                 }
            })
        },
        methods: {
            changeTabs(id,dex){
                this.tabIndex = dex;
                this.pageNum = 1 ;
                this.courseLists = [];
                this.init(id);
            },
            init(){
                let that = this;
                var token = getToken();
                if (token == null || token.length < 1) {
                    login(location.href);
                    return;
                }
                var url = "/article/list";
                var jsonData = {
                    pageNum: this.pageNum,
                    pageSize:this.pageSize
                }
                showloading();
                getRequest(url, token, jsonData, function (data) {
                    // 隐藏加载中
                    hideloading();
                    if (data.code != 200) {
                        showToast("请稍后再试");
                        return;
                    }
                    let lists = data.result;
                    console.log(lists)
                    for(let i in lists){
                        let time = lists[i].createTime;
                        lists[i].createTime = time.match(/(\S*)T/)[1];;
                        that.newsLists.push(lists[i]);
                    }
                    console.log(that.newsLists)
                    that.totalPage = data.totalPage;
                }, function (data) {
                    // 隐藏加载中
                    hideloading();
                    showToast("请稍后再试");
                });
            },
            scrollBottom(){
                if(this.pageNum  > this.totalPage){
                    showToast('没有更多了');
                    return;
                }else{
                    this.pageNum++;
                    // this.init();
                }
            },
            goNewsDetail(id){
                window.location.href = 'news_detail.html?id='+id
            }
        }
    })

})();