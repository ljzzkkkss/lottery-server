(function () {
    $('.weui-tabbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
    });


    var app = new Vue({
        el: '#app',
        data: {
            ctx: '',
            newsLists:[],
            pageNum:1,
            none: false,
            pageSize:10,
            currentCategory: '名家推荐',
            bannerImgLists:[],
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
            this.loadNews();
            let that = this;
            $('.weui_tab_bd').on('scroll',function(){
                var scrollTop = $(this).scrollTop();
                var scrollHeight = $('.centerContentBody').height();
                var windowHeight = $(this).height();
                if(Math.abs(scrollTop + windowHeight -  scrollHeight) <= 1){
                    that.scrollBottom();
                 }
            });
            that.ctx = window.localStorage.getItem("ctx");
            if(null === that.ctx || '' === that.ctx){
                $.ajax('/getFileUrl', {
                    type: 'GET',
                    contentType: 'application/json',
                    success: res => {
                        if (res.code === '0000') {
                            window.localStorage.setItem("ctx",res.data);
                            that.ctx = res.data
                        } else {
                            showToast(res.message);
                        }
                    },
                    error: err => {
                        console.log(err);
                    }
                });
            }
            $.ajax('/banner/list', {
                type: 'GET',
                contentType: 'application/json',
                success: res => {
                    if (res.code === '0000') {
                        app.bannerImgLists = res.data;
                    } else {
                        showToast(res.message);
                    }
                },
                error: err => {
                    console.log(err);
                }
            });
        },
        methods: {
            changeTabs(item,dex){
                this.tabIndex = dex;
                this.pageNum = 1 ;
                this.currentCategory = item.name;
                this.none = false;
                this.newsLists = [];
                this.loadNews();
            },
            loadNews(){
                showloading();
                $.ajax('/article/list?category=' + this.currentCategory + '&pageIndex=' + this.pageNum + '&pageSize=' + this.pageSize, {
                    type: 'GET',
                    contentType: 'application/json',
                    success: res => {
                        // 隐藏加载中
                        hideloading();
                        if (res.code === '0000') {
                            let lists = res.data;
                            if(lists.length === 0){
                                app.none = true;
                            }else {
                                for (let i in lists) {
                                    app.newsLists.push(lists[i]);
                                }
                            }
                        } else {
                            showToast(res.message);
                        }
                    },
                    error: err => {
                        // 隐藏加载中
                        hideloading();
                        showToast("请稍后再试");
                        console.log(err);
                    }
                });
            },
            scrollBottom(){
                if(this.none){
                    showToast('没有更多了');
                }else{
                    this.pageNum++;
                    this.loadNews();
                }
            },
            goNewsDetail(id){
                window.location.href = '/news_detail?id='+id
            }
        }
    })

})();