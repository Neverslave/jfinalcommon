layui.config({
}).use(['form','layer','jquery','laydate','upload'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery,
		upload = layui.upload;

	//渲染laydate实例
	laydate.render({
		elem:'.newsTime',
		type:'datetime'
	})

	//渲染上传控件
	var uploadList =$('#uploadList');
	var uuid = generateUUID();
	var uploadListIns = upload.render({
		elem:'#upload',
		url:'/upload/',
		accept:'file',
		multiple:true,
		auto:false,
		data:{uuid:uuid},
		bindAction:'#testListAction',
		choose:function (obj) {
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                uploadList.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                var tr = uploadList.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = uploadList.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
	});
//=------------------------------

 	var addNewsArray = [],addNews;
 	form.on("submit(addNews)",function(data){
 		addNews = '{"newsName":"'+$(".newsName").val()+'",';  //文章名称
 		addNews += '"newsId":"'+new Date().getTime()+'",';	 //文章id
 		addNews += '"newsLook":"'+$(".newsLook option").eq($(".newsLook").val()).text()+'",'; //开放浏览
 		addNews += '"newsTime":"'+$(".newsTime").val()+'",'; //发布时间
 		addNews += '"newsAuthor":"'+$(".newsAuthor").val()+'",'; //文章作者
 		addNews += '"newsStatus":"'+ newsStatus +'"}'; //审核状态


 		//弹出loading

        $.post({
            url:'/addNews',
            data:addNews,
            success:function (res) {
                top.layer.msg("文章添加成功！");
                layer.closeAll("iframe");
                parent.location.reload();
            },
            error:function (res) {
                alert("添加失败");

            }

        });
 		return false;
 	})


	
})




//编辑器初始化
var E = window.wangEditor;
var editor = new E('#toolbar','#news_content');
editor.create();

function generateUUID() {
    var d = new Date().getTime();
    if (window.performance && typeof window.performance.now === "function") {
        d += performance.now(); //use high-precision timer if available
    }
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}

