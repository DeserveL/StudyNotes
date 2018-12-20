new Vue({
    el: '#app',
    data: {
        username: '',
        startChat: false,
        messageList: [],
        onlineUsers: [],
        tempMessage: '',
        websock: null,
    },
    created() {
        //this.initWebSocket();
    },
    destroyed() {
        this.websock.close() //离开路由之后断开websocket连接
    },
    methods: {
        initWebSocket(username){ //初始化weosocket
            const wsuri = "ws://"+ window.location.host +"/chat/" + username;
            this.websock = new WebSocket(wsuri);
            this.websock.onmessage = this.websocketonmessage;
            this.websock.onopen = this.websocketonopen;
            this.websock.onerror = this.websocketonerror;
            this.websock.onclose = this.websocketclose;
        },
        websocketonopen(){ //连接建立之后执行send方法发送数据
            //let actions = {"test":"12345"};
            //this.websocketsend(JSON.stringify(actions));
        },
        websocketclose(e){  //关闭
            console.log('断开连接',e);
        },
        websocketonerror(){//连接建立失败重连
            this.initWebSocket();
        },
        websocketonmessage(e){ //数据接收
            const message = JSON.parse(e.data);
            console.log(e.data);
            this.messageList.push(message);
            this.onlineUsers = message.onlineUsers;
        },
        websocketsend(Data){//数据发送
            this.websock.send(Data);
        },
        login(){
            if(this.username == "" || this.username == null){
                alert("请输入昵称");
                return;
            }
            this.startChat = true;
            console.log(this.username);
            //开启连接
            this.initWebSocket(this.username);
        },
        logout(){
            this.startChat = false;
            //断开连接
            this.websock.close();
        },
        send(){
            if(this.tempMessage == "" || this.tempMessage == null){
                alert("消息为空");
                return;
            }
            let message = {};
            message.message = this.tempMessage;
            message.toUserName = "All_001";
            this.websocketsend(JSON.stringify(message));
            this.tempMessage = "";
        }
    }
})
